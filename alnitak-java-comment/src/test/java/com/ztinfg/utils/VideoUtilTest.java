package com.ztinfg.utils;

import com.google.common.base.Throwables;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class VideoUtilTest {

    @Test
    void getVideoDuration() {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("/Users/g/Downloads/undefined - 001 - Château Tayac.mp4")) {
            grabber.start();
            long lengthInFrames = grabber.getLengthInFrames();
            int width = grabber.getImageWidth();
            int height = grabber.getImageHeight();
            double rate = grabber.getFrameRate();
            System.out.println("时长" + Math.round(lengthInFrames / rate) + " 宽 " + width + " 高 " + height + "帧率 " + rate);
            Assertions.assertTrue(true);
        } catch (IOException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }
}