package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.bo.review.ArticleReview;
import com.ztingfg.bo.review.VideoReview;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.entities.Article;
import com.ztingfg.entities.Review;
import com.ztingfg.entities.Video;
import com.ztingfg.enums.ResourceTypeEnum;
import com.ztingfg.enums.ReviewStatusEnum;
import com.ztingfg.mappers.ReviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class ReviewService extends ServiceImpl<ReviewMapper, Review> implements IService<Review> {

    @Resource
    private ReviewMapper reviewMapper;

    @Resource
    private VideoService videoService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private ArticleService articleService;

    public void reviewArticleApproved(ArticleReview articleReview) {
        articleService.lambdaUpdate()
                .set(Article::getStatus, ReviewStatusEnum.PASS.getCode())
                .eq(Article::getId, articleReview.getAid());

        Review review = new Review();
        review.setCid(articleReview.getAid());
        review.setStatus(ReviewStatusEnum.PASS.getCode());
        review.setRemark(ReviewStatusEnum.PASS.getName());
        review.setUid(RequestContext.getUser().getId());
        review.setType(ResourceTypeEnum.ARTICLE.getCode());
        save(review);
    }

    public void reviewArticleFailed(ArticleReview articleReview) {
        articleService.lambdaUpdate()
                .set(Article::getStatus, articleReview.getStatus())
                .eq(Article::getId, articleReview.getAid());

        Review review = new Review();
        review.setCid(articleReview.getAid());
        review.setStatus(articleReview.getStatus());
        review.setRemark(articleReview.getRemark());
        review.setUid(RequestContext.getUser().getId());
        review.setType(ResourceTypeEnum.ARTICLE.getCode());
        save(review);
    }

    public void reviewVideoApproved(VideoReview videoReview) {
        videoService.lambdaUpdate()
                .set(Video::getStatus, ReviewStatusEnum.PASS.getCode())
                .eq(Video::getId, videoReview.getVid());

        resourceService.lambdaUpdate()
                .set(com.ztingfg.entities.Resource::getStatus, ReviewStatusEnum.PASS.getCode())
                .eq(com.ztingfg.entities.Resource::getVid, videoReview.getVid());

        Review review = new Review();
        review.setCid(videoReview.getVid());
        review.setStatus(ReviewStatusEnum.PASS.getCode());
        review.setRemark(ReviewStatusEnum.PASS.getName());
        review.setUid(RequestContext.getUser().getId());
        review.setType(ResourceTypeEnum.VIDEO.getCode());
        save(review);
    }

    public void reviewVideoFailed(VideoReview videoReview) {
        videoService.lambdaUpdate()
                .set(Video::getStatus, videoReview.getStatus())
                .eq(Video::getId, videoReview.getVid());

        resourceService.lambdaUpdate()
                .set(com.ztingfg.entities.Resource::getStatus, videoReview.getStatus())
                .eq(com.ztingfg.entities.Resource::getVid, videoReview.getVid());

        Review review = new Review();
        review.setCid(videoReview.getVid());
        review.setStatus(videoReview.getStatus());
        review.setRemark(videoReview.getRemark());
        review.setUid(RequestContext.getUser().getId());
        review.setType(ResourceTypeEnum.VIDEO.getCode());
        save(review);
    }
}
