package com.ztingfg.controllers;

import com.ztingfg.bo.video.VideoDesc;
import com.ztingfg.services.UserService;
import com.ztingfg.services.VideoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoControllerTest {

    @Resource
    private UserService userService;

    @Resource
    private VideoService videoService;

    @Test
    void getVideo() {
        VideoDesc videoById = videoService.getVideoById(4L);
        Assertions.assertNotNull(videoById);
    }
}