package com.ztingfg.controllers;

import com.ztingfg.bo.review.ArticleReview;
import com.ztingfg.bo.review.VideoReview;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.Review;
import com.ztingfg.services.ReviewService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    @PostMapping("/v1/review/reviewArticleApproved")
    public GenericResult<Object> reviewArticleApproved(@RequestBody ArticleReview articleReview) {
        reviewService.reviewArticleApproved(articleReview);
        return GenericResult.success();
    }

    @PostMapping("/v1/review/reviewArticleFailed")
    public GenericResult<Object> reviewArticleFailed(@RequestBody ArticleReview articleReview) {
        reviewService.reviewArticleFailed(articleReview);
        return GenericResult.success();
    }

    @GetMapping("/v1/review/getArticleReviewRecord")
    public GenericResult<Object> getArticleReviewRecord(@RequestParam("aid") Long aid) {
        List<Review> list = reviewService.lambdaQuery().eq(Review::getCid, aid).list();
        return GenericResult.success(Map.of("review", list));
    }

    @PostMapping("/v1/review/reviewVideoApproved")
    public GenericResult<Object> reviewVideoApproved(@RequestBody VideoReview videoReview) {
        reviewService.reviewVideoApproved(videoReview);
        return GenericResult.success();
    }

    @PostMapping("/v1/review/reviewVideoFailed")
    public GenericResult<Object> reviewVideoFailed(@RequestBody VideoReview videoReview) {
        reviewService.reviewVideoFailed(videoReview);
        return GenericResult.success();
    }

    @GetMapping("/v1/review/getVideoReviewRecord")
    public GenericResult<Object> getVideoReviewRecord(@RequestParam("vid") Long vid) {
        List<Review> list = reviewService.lambdaQuery().eq(Review::getCid, vid).list();
        return GenericResult.success(Map.of("review", list));
    }
}
