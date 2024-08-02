package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.bo.article.ArticleInfo;
import com.ztingfg.entities.Article;
import com.ztingfg.mappers.ArticleMapper;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> implements IService<Article> {

    @Resource
    private ArticleMapper articleMapper;

    public List<ArticleInfo> getArticleById(Long aid) {
        return articleMapper.getArticles(aid, null, null);
    }

    public PaginationResult<ArticleInfo> getReviewArticleList(Pagination pagination) {
        long count = count();
        List<ArticleInfo> articles = articleMapper.getArticles(null, pagination, 0L);
        return PaginationResult.from(articles, count);
    }

    public PaginationResult<ArticleInfo> getArticleListManage(Pagination pagination) {
        long count = count();
        List<ArticleInfo> articles = articleMapper.getArticles(null, pagination, null);
        return PaginationResult.from(articles, count);
    }
}
