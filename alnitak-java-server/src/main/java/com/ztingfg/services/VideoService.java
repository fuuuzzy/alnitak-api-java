package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.entities.Video;
import com.ztingfg.mappers.VideoMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class VideoService extends ServiceImpl<VideoMapper, Video> implements IService<Video> {

    private static final String uploadVideoCoverPath = "/upload/video/cover/";

    public String uploadVideoCover(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String extension = Files.getFileExtension(originalFilename);
            String filename = Files.getNameWithoutExtension(originalFilename);
            String name = Hashing.md5()
                    .hashString(filename, StandardCharsets.UTF_8).toString();
            File f = new File(uploadVideoCoverPath + name + extension);

            try {
                file.transferTo(f);
            } catch (IOException e) {
                throw new BusinessException(BizStatus.uploadVideoImageError);
            }
            return "http://localhost:8080" + uploadVideoCoverPath + name + extension;
        }
        throw new BusinessException(BizStatus.FilenameNotExists);
    }
}
