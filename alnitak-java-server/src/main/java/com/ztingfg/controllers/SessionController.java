package com.ztingfg.controllers;

import com.ztingfg.bo.SessionInfo;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.services.SendMailService;
import com.ztingfg.services.SessionService;
import com.ztingfg.vo.RefreshTokenRequest;
import com.ztingfg.vo.SendCodeRequest;
import com.ztingfg.vo.SessionEmailRequest;
import com.ztingfg.vo.SessionRequest;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SessionController {

    @Resource
    private SessionService sessionService;

    @Resource
    private SendMailService sendService;

    @PostMapping("/v1/auth/login")
    public GenericResult<SessionInfo> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        SessionInfo session = sessionService.createSession(sessionRequest);
        return GenericResult.success(session);
    }

    @PostMapping("/v1/auth/login/email")
    public GenericResult<SessionInfo> createSession(@Valid @RequestBody SessionEmailRequest sessionRequest) {
        SessionInfo session = sessionService.createSession(sessionRequest);
        return GenericResult.success(session);
    }

    @PostMapping("/v1/verify/getEmailCode")
    public GenericResult<Object> sendCode(@Valid @RequestBody SendCodeRequest sendCodeRequest) {
        sendService.send(sendCodeRequest.getEmail());
        return GenericResult.success();
    }

    @PostMapping("/v1/auth/updateToken")
    public GenericResult<Object> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        SessionInfo session = sessionService.refreshToken(refreshTokenRequest);
        return GenericResult.success(session);
    }

    @PostMapping("/v1/auth/logout")
    public GenericResult<Object> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return GenericResult.success();
    }
}
