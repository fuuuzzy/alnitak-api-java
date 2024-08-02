package com.ztinfg.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public final class VideoUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoUtil.class);

    public static Long getVideoDuration(InputStream inputStream) throws FrameGrabber.Exception {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream)) {
            grabber.start();
            long lengthInFrames = grabber.getLengthInFrames();
            double rate = grabber.getFrameRate();
            long duration = Math.round(lengthInFrames / rate);
            logger.info("视频的时长 {}", duration);
            return duration;
        }
    }

    private VideoUtil() {
    }
}