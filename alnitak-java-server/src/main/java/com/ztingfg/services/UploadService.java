package com.ztingfg.services;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.ztinfg.utils.VideoUtil;
import com.ztingfg.bo.video.UploadVideoResult;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.entities.User;
import com.ztingfg.entities.Video;
import com.ztingfg.entities.VideoIndexFile;
import com.ztingfg.mappers.ResourceMapper;
import com.ztingfg.mappers.VideoIndexFileMapper;
import com.ztingfg.mappers.VideoMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UploadService {

    @Value("${local.upload.path}")
    private String localPath;

    @Value("${local.upload.url}")
    private String localUrl;

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private VideoIndexFileMapper videoIndexFileMapper;

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    public String uploadFile(MultipartFile file, String path) {
        File directory = new File(localPath + path);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new BusinessException(BizStatus.mkdirDirectoryError);
        }
        String originalFilename = file.getOriginalFilename();
        if (Strings.isNullOrEmpty(originalFilename)) {
            throw new BusinessException(BizStatus.FilenameNotExists);
        }
        try {
            String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
            String extension = "." + Files.getFileExtension(originalFilename);
            String filename = md5 + extension;
            if (!exists(path + filename)) {
                File f = new File(localPath + path + filename);
                file.transferTo(f);
            }
            return localUrl + path + filename;
        } catch (Exception e) {
            throw new BusinessException(BizStatus.FileUploadError);
        }
    }

    private boolean exists(String filePath) {
        return new File(localPath + filePath).exists();
    }

    /**
     * 获取md5值
     *
     * @param inputStream 文件流
     * @return {@link String} md5
     */
    @Deprecated(since = "not use", forRemoval = true)
    private String getMd5(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            return new String(Base64.getUrlEncoder().encode(md5.digest()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("文件md5失败，error ={}", e.getMessage());
            throw new BusinessException(BizStatus.FileUploadError);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("文件流关闭失败，error ={}", e.getMessage());
            }
        }
    }

    @Transactional
    public UploadVideoResult uploadVideo(MultipartFile videoFile, String path) {
        User user = RequestContext.getUser();
        try {
            Long duration = VideoUtil.getVideoDuration(videoFile.getInputStream());
            Video video = Video.builder()
                    .title(System.currentTimeMillis() + "")
                    .cover("")
                    .copyright(false)
                    .uid(user.getId())
                    .duration(duration.doubleValue())
                    .status(0L).build();
            videoMapper.insert(video);
            com.ztingfg.entities.Resource resource = com.ztingfg.entities.Resource.builder()
                    .status(0L)
                    .title(System.currentTimeMillis() + "")
                    .vid(video.getId())
                    .uid(user.getId())
                    .duration(duration.doubleValue()).build();
            resourceMapper.insert(resource);

            String url = uploadFile(videoFile, path);

            VideoIndexFile videoIndexFile = VideoIndexFile.builder()
                    .dirName(path)
                    .content(url)
                    .resourceId(resource.getId())
                    .build();
            videoIndexFileMapper.insert(videoIndexFile);

            return UploadVideoResult.builder()
                    .id(resource.getId())
                    .createdAt(LocalDateTime.now())
                    .duration(duration)
                    .vid(video.getId())
                    .status(resource.getStatus())
                    .title(resource.getTitle())
                    .build();
        } catch (IOException e) {
            logger.error("上传视频失败，error ={}", e.getMessage());
            throw new BusinessException(BizStatus.FileUploadError);
        }
    }

    public UploadVideoResult appendUploadVideo(MultipartFile video, String path, Long vid) {
        try {
            User user = RequestContext.getUser();
            Long duration = VideoUtil.getVideoDuration(video.getInputStream());

            String url = uploadFile(video, path);

            com.ztingfg.entities.Resource resource = com.ztingfg.entities.Resource.builder()
                    .status(0L)
                    .title(System.currentTimeMillis() + "")
                    .vid(vid)
                    .uid(user.getId())
                    .duration(duration.doubleValue()).build();
            resourceMapper.insert(resource);

            VideoIndexFile videoIndexFile = VideoIndexFile.builder()
                    .dirName(path)
                    .content(url)
                    .resourceId(resource.getId())
                    .build();
            videoIndexFileMapper.insert(videoIndexFile);

            return UploadVideoResult.builder()
                    .id(resource.getId())
                    .createdAt(LocalDateTime.now())
                    .duration(duration)
                    .vid(vid)
                    .status(resource.getStatus())
                    .title(resource.getTitle())
                    .build();
        } catch (IOException e) {
            logger.error("追加视频失败，error ={}", e.getMessage());
            throw new BusinessException(BizStatus.FileUploadError);
        }
    }
}
