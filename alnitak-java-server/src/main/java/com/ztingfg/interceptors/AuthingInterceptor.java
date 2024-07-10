package com.ztingfg.interceptors;

import com.google.common.base.Strings;
import com.ztinfg.Consts;
import com.ztinfg.utils.StringUtil;
import com.ztingfg.bo.TokenInfo;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.entities.User;
import com.ztingfg.helpers.Applications;
import com.ztingfg.services.TokenService;
import com.ztingfg.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.Optional;

public class AuthingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        TokenService tokenService = Applications.getBean(TokenService.class);
        UserService userService = Applications.getBean(UserService.class);
        String token = Optional.ofNullable(request.getHeader(Consts.ACCESS_TOKEN_HEADER))
                .orElseGet(() -> {
                    String queryString = request.getQueryString();
                    Map<String, String> queryParams = StringUtil.parseQueryParams(queryString);
                    return queryParams.get(Consts.ACCESS_TOKEN_QUERY);
                });
        if (Strings.isNullOrEmpty(token)) {
            throw new BusinessException(BizStatus.IllegalToken);
        }

        TokenInfo tokenInfo = tokenService.verifyToken(token);

        User user = userService.queryAccountById(tokenInfo.getAccountId());
        RequestContext.setUser(user);
        return true;
    }
}
