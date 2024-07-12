package com.ztingfg.services;

import com.ztingfg.vo.RegisterRequest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("420021");
        registerRequest.setEmail("ztingfg@foxmail.com");
        userService.register(registerRequest);
    }
}