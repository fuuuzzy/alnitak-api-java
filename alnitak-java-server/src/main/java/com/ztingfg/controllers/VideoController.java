package com.ztingfg.controllers;

import com.ztingfg.bo.video.*;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.cqrs.cmd.VideoUpdate;
import com.ztingfg.entities.Video;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.services.VideoService;
import com.ztingfg.vo.SearchVideoRequest;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VideoController {

    @Resource
    private VideoService videoService;

    @PostMapping("/v1/video/uploadVideoInfo")
    public GenericResult<Object> updateVideo(@RequestBody Video video) {
        videoService.updateVideo(video);
        return GenericResult.success();
    }

    @GetMapping("/v1/video/getVideoStatus")
    public GenericResult<Map<String, VideoInfo>> getVideoInfo(@RequestParam("vid") Long vid) {
        VideoInfo videoInfo = videoService.getVideoInfo(vid);
        return GenericResult.success(Map.of("video", videoInfo));
    }

    @GetMapping("/v1/video/getUploadVideo")
    public GenericResult<Map<String, Object>> getVideos(Pagination pagination) {
        PaginationResult<Video> videos = videoService.getVideos(pagination);
        return GenericResult.success(Map.of("total", videos.getTotal(), "videos", videos.getData()));
    }

    @PutMapping("/v1/video/editVideoInfo")
    public GenericResult<Object> updateVideo(@Valid @RequestBody VideoUpdate update) {
        videoService.updateVideo(update);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/video/deleteVideo/{vid}")
    public GenericResult<Object> removeVideo(@PathVariable("vid") Long vid) {
        videoService.removeById(vid);
        return GenericResult.success();
    }

    @GetMapping("/v1/video/getAllVideoList")
    public GenericResult<Object> getVideoTitle() {
        List<VideoTitle> videoTitles = videoService.getVideoTitle();
        return GenericResult.success(Map.of("videos", videoTitles));
    }

    @GetMapping("/v1/video/getVideoById")
    public GenericResult<Map<String, VideoDesc>> getVideoById(@RequestParam("vid") Long vid) {
        VideoDesc videoDesc = videoService.getVideoById(vid);
        return GenericResult.success(Map.of("videos", videoDesc));
    }

    @GetMapping("/v1/video/getResourceQuality")
    public GenericResult<Map<String, List<String>>> getResourceQuality(@RequestParam("resourceId") Long resourceId) {
        List<String> qualities = videoService.getResourceQuality(resourceId);
        return GenericResult.success(Map.of("quality", qualities));
    }

    @GetMapping("/v1/video/getVideoFile")
    public GenericResult<Map<String, String>> getVideoFile(@RequestParam("resourceId") Long resourceId,
                                                           @RequestParam("quality") String quality) {
        String url = videoService.getVideoFile(resourceId, quality);
        return GenericResult.success(Map.of("quality", url));
    }

    @GetMapping("/v1/video/getVideoByUser")
    public GenericResult<Map<String, Object>> getVideoByUser(@RequestParam("userId") Long userId, Pagination pagination) {
        PaginationResult<Video> paginationResult = videoService.getVideoByUser(userId, pagination);
        return GenericResult.success(Map.of("videos", paginationResult.getData(), "total", paginationResult.getTotal()));
    }

    @GetMapping("/v1/video/getHotVideo")
    public GenericResult<Map<String, Object>> getHotVideo(Pagination pagination) {
        List<VideoDesc> hotVideo = videoService.getHotVideo(pagination);
        return GenericResult.success(Map.of("videos", hotVideo));
    }

    @GetMapping("/v1/video/getVideoListByPartition")
    public GenericResult<Map<String, Object>> getVideoListByPartition(@RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                      @RequestParam("partitionId") Long partitionId) {
        List<VideoDesc> hotVideo = videoService.getVideoListByPartition(size, partitionId);
        return GenericResult.success(Map.of("videos", hotVideo));
    }

    @GetMapping("/v1/video/getRelatedVideoList")
    public GenericResult<Map<String, Object>> getRelatedVideoList(@RequestParam("vid") Long vid) {
        VideoDesc videoDesc = videoService.getVideoById(vid);
        return GenericResult.success(Map.of("videos", videoDesc));
    }

    @PostMapping("/v1/video/searchVideo")
    public GenericResult<Map<String, Object>> searchVideo(@RequestBody SearchVideoRequest request) {
        List<VideoDesc> videoDesc = videoService.searchVideo(request);
        return GenericResult.success(Map.of("videos", videoDesc));
    }

    @GetMapping("/v1/video/getReviewList")
    public GenericResult<Map<String, Object>> getReviewList(Pagination pagination) {
        PaginationResult<VideoReview> reviewList = videoService.getReviewList(pagination);
        return GenericResult.success(Map.of("list", reviewList.getData(), "total", reviewList.getTotal()));
    }

    @GetMapping("/v1/video/getReviewResourceList")
    public GenericResult<Map<String, Object>> getReviewResourceList(@RequestParam("vid") Long vid) {
        List<com.ztingfg.entities.Resource> resources = videoService.getReviewResourceList(vid);
        return GenericResult.success(Map.of("resources", resources));
    }

    @GetMapping("/v1/video/getVideoListManage")
    public GenericResult<Map<String, Object>> getVideoListManage(Pagination pagination) {
        PaginationResult<VideoResult> videoResults = videoService.getVideoListManage(pagination);
        return GenericResult.success(Map.of("list", videoResults.getData(), "total", videoResults.getTotal()));
    }

    @DeleteMapping("/v1/video/deleteVideoManage/{vid}")
    public GenericResult<Object> deleteVideoManage(@PathVariable("vid") String vid) {
        videoService.removeById(vid);
        return GenericResult.success();
    }

    @GetMapping("/v1/video/getResourceQualityManage")
    public GenericResult<Object> getResourceQualityManage(@RequestParam("resourceId") Long resourceId) {
        List<String> quality = videoService.getResourceQuality(resourceId);
        return GenericResult.success(Map.of("quality", quality));
    }

    @GetMapping("/v1/video/getVideoFileManage")
    public GenericResult<Map<String, String>> getVideoFileManage(@RequestParam("resourceId") Long resourceId,
                                                                 @RequestParam("quality") String quality) {
        String url = videoService.getVideoFile(resourceId, quality);
        return GenericResult.success(Map.of("quality", url));
    }
}
