package com.ztinfg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public final class VideoUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoUtil.class);

    public static Long getVideoDuration(InputStream inputStream) {
        return 10L;
        //try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream)) {
        //    // 打开视频文件
        //    grabber.start();
        //    // 获取视频时长（单位：秒）
        //    return grabber.getLengthInTime() / (1000 * 1000);
        //} catch (Exception e) {
        //    logger.error("获取视频时长失败，error ={}", e.getMessage());
        //    return 0L;
        //}
    }

    private VideoUtil() {
    }
}