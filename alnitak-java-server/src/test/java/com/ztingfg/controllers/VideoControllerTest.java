package com.ztingfg.controllers;

import com.ztingfg.services.VideoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoControllerTest {

    @Resource
    private VideoService videoService;

    @Test
    void getVideo() {
        System.out.println("123");
        VideoDesc video = videoService.getVideo(1L);
    }
}