package com.ztingfg.controllers;

import com.ztingfg.bo.video.UploadVideoResult;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.enums.FilePathEnum;
import com.ztingfg.services.UploadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/v1/upload/video")
    public GenericResult<Map<String, UploadVideoResult>> uploadVideo(@RequestParam("video") MultipartFile video) {
        UploadVideoResult result = uploadService.uploadVideo(video, FilePathEnum.VIDEO.getPath());
        return GenericResult.success(Map.of("resource", result));
    }

    @PostMapping("/v1/upload/image")
    public GenericResult<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile image) {
        String url = uploadService.uploadFile(image, FilePathEnum.IMAGE.getPath(), false);
        return GenericResult.success(Map.of("url", url));
    }

    @PostMapping("/v1/upload/video/{vid}")
    public GenericResult<Map<String, UploadVideoResult>> appendUploadVideo(@RequestParam("video") MultipartFile video, @PathVariable("vid") Long vid) {
        UploadVideoResult result = uploadService.appendUploadVideo(video, FilePathEnum.VIDEO.getPath(), vid);
        return GenericResult.success(Map.of("resource", result));
    }
}
