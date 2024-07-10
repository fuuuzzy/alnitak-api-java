package com.ztingfg.controllers;

import com.ztingfg.entities.User;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.services.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoControllerTest {

    @Resource
    private UserService userService;

    @Test
    void getVideo() {
        Pagination pagination = new Pagination();
        PaginationResult<User> userListManage = userService.getUserListManage(pagination);
    }
}