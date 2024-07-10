package com.ztingfg.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.hash.Hashing;
import com.ztinfg.utils.StringUtil;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.entities.User;
import com.ztingfg.mappers.UserMapper;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.vo.RegisterRequest;
import com.ztingfg.vo.SessionRequest;
import com.ztingfg.vo.UserRole;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {

    @Resource
    private UserMapper userMapper;

    @Value("${account.app.salt}")
    private String appKey;

    @Resource
    private SendService sendService;

    public User queryAccountById(Long uid) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<>(User.class)
                .eq(User::getId, uid));
        if (user == null) {
            throw new BusinessException(BizStatus.AccountNotExists);
        }
        return user;
    }

    public User isAccountExists(String email) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<>(User.class)
                .eq(User::getEmail, email));

        if (user == null) {
            throw new BusinessException(BizStatus.AccountNotExists);
        }
        return user;
    }

    public User validateAccount(SessionRequest sessionRequest) {
        User user = isAccountExists(sessionRequest.getEmail());

        String password = generatePassword(user.getEmail(), user.getSalt(), sessionRequest.getPassword());

        if (user.getPassword().equals(password)) {
            return user;
        }

        throw new BusinessException(BizStatus.AccountPasswordNotMatch);
    }

    public String generatePassword(String email, String salt, String password) {
        return Hashing.hmacMd5(appKey.getBytes(StandardCharsets.UTF_8))
                .hashString(email + salt + password, Charset.defaultCharset()).toString();
    }

    public void register(RegisterRequest registerRequest) {
        String code = registerRequest.getCode();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        sendService.validateCode(email, code);

        String salt = StringUtil.randStr(32);
        String dbPassword = generatePassword(email, salt, password);

        User user = User.builder().email(email).password(dbPassword)
                .salt(salt).build();
        try {
            this.save(user);
        } catch (Exception e) {
            throw new BusinessException(BizStatus.AccountExists);
        }
    }

    public void editUserInfo(Long uid, User user) {
        User u = getById(uid);
        u.setAvatar(user.getAvatar());
        u.setUsername(user.getUsername());
        u.setGender(user.getGender());
        u.setBirthday(user.getBirthday());
        u.setSign(user.getSign());
        u.setSpaceCover(user.getSpaceCover());
        updateById(u);
    }

    public PaginationResult<User> getUserListManage(Pagination pagination) {
        PageMethod.startPage(pagination.getPage(), pagination.getPageSize());
        List<User> users = list();
        PageInfo<User> pageInfo = PageInfo.of(users);
        return PaginationResult.from(users, pageInfo.isHasNextPage(), pageInfo.getTotal());
    }

    public void editUserRole(UserRole userRole) {
        User user = getById(userRole.getUid());
        user.setRole(userRole.getCode());
        updateById(user);
    }
}