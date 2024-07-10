package com.ztingfg.services;

import com.ztingfg.bo.SessionInfo;
import com.ztingfg.bo.TokenInfo;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.entities.User;
import com.ztingfg.vo.RefreshTokenRequest;
import com.ztingfg.vo.SessionEmailRequest;
import com.ztingfg.vo.SessionRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private SendService sendService;

    public SessionInfo createSession(SessionRequest sessionRequest) {
        User user = userService.validateAccount(sessionRequest);
        return getSession(user);
    }

    private SessionInfo getSession(User user) {
        long timeMillis = System.currentTimeMillis();
        TokenInfo tokenInfo = TokenInfo.from(user.getId(), timeMillis + 3600 * 1000L);
        TokenInfo refreshToken = TokenInfo.from(user.getId(), timeMillis + 24 * 3600 * 1000L);
        String token = tokenService.createToken(tokenInfo);
        String refresh = tokenService.createToken(refreshToken);
        return SessionInfo.builder().token(token).refreshToken(refresh).build();
    }

    public SessionInfo createSession(SessionEmailRequest sessionRequest) {
        User user = userService.isAccountExists(sessionRequest.getEmail());
        sendService.validateCode(sessionRequest.getEmail(), sessionRequest.getCode());
        return getSession(user);
    }

    public SessionInfo refreshToken(RefreshTokenRequest refreshTokenRequest) {
        TokenInfo tokenInfo = tokenService.verifyToken(refreshTokenRequest.getRefreshToken());
        if (System.currentTimeMillis() > tokenInfo.getExpirationTime()) {
            throw new BusinessException(BizStatus.IllegalToken);
        }
        User user = userService.queryAccountById(tokenInfo.getAccountId());
        long timeMillis = System.currentTimeMillis();
        String token = tokenService.createToken(user.getId(), timeMillis + 3600 * 1000L);
        String refresh = tokenService.createToken(user.getId(), timeMillis + 24 * 3600 * 1000L);
        return SessionInfo.builder().token(token).refreshToken(refresh).build();
    }
}
