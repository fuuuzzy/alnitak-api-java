package com.ztingfg.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztingfg.bo.article.ArticleInfo;
import com.ztingfg.bo.article.ArticleTitle;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.Article;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.services.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/v1/article/uploadArticleInfo")
    public GenericResult<Object> uploadArticleInfo(@RequestBody Article article) {
        articleService.save(article);
        return GenericResult.success();
    }

    @GetMapping("/v1/article/getArticleStatus")
    public GenericResult<Map<String, Object>> getArticleStatus(@RequestParam("aid") Long aid) {
        Article article = articleService.lambdaQuery()
                .eq(Article::getId, aid).one();
        return GenericResult.success(Map.of("article", article));
    }

    @GetMapping("/v1/article/getAllArticleList")
    public GenericResult<Map<String, Object>> getAllArticleList() {
        List<ArticleTitle> list = articleService.lambdaQuery()
                .list()
                .stream().map(ArticleTitle::from).toList();
        return GenericResult.success(Map.of("article", list));
    }

    @GetMapping("/v1/article/getArticleById")
    public GenericResult<Map<String, Object>> getArticleById(@RequestParam("aid") Long aid) {
        List<ArticleInfo> articleInfo = Optional.ofNullable(articleService.getArticleById(aid)).orElseGet(ArrayList::new);
        return GenericResult.success(Map.of("article", articleInfo.getFirst()));
    }

    @GetMapping("/v1/article/getArticleByUser")
    public GenericResult<Map<String, Object>> getArticleByUser(@RequestParam("userId") Long uid, Pagination pagination) {
        Page<Article> page = articleService.lambdaQuery()
                .eq(Article::getUid, uid)
                .page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return GenericResult.success(Map.of("article", page.getRecords(), "total", page.getTotal()));
    }

    @GetMapping("/v1/article/getUploadArticle")
    public GenericResult<Map<String, Object>> getUploadArticle(Pagination pagination) {
        Page<Article> page = articleService.lambdaQuery()
                .page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return GenericResult.success(Map.of("list", page.getRecords(), "total", page.getTotal()));
    }

    @GetMapping("/v1/article/getRandomArticleList")
    public GenericResult<Map<String, Object>> getRandomArticleList(@RequestParam("size") Long size) {
        List<ArticleInfo> articles = articleService.getArticleById(null);
        return GenericResult.success(Map.of("articles", articles));
    }

    @PutMapping("/v1/article/editArticleInfo")
    public GenericResult<Object> editArticleInfo(@RequestBody Article article) {
        articleService.updateById(article);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/api/deleteArticle/{a-id}")
    public GenericResult<Object> deleteApi(@PathVariable("a-id") Long aId) {
        articleService.removeById(aId);
        return GenericResult.success();
    }

    @GetMapping("/v1/article/getReviewArticleList")
    public GenericResult<Object> getReviewArticleList(Pagination pagination) {
        PaginationResult<ArticleInfo> page = articleService.getReviewArticleList(pagination);
        return GenericResult.success(Map.of("list", page.getData(), "total", page.getTotal()));
    }

    @GetMapping("/v1/article/getArticleListManage")
    public GenericResult<Object> getArticleListManage(Pagination pagination) {
        PaginationResult<ArticleInfo> page = articleService.getArticleListManage(pagination);
        return GenericResult.success(Map.of("list", page.getData(), "total", page.getTotal()));
    }

    @DeleteMapping("/v1/article/deleteArticleManage/{a-id}")
    public GenericResult<Object> deleteArticleManage(@PathVariable("a-id") Long aId) {
        articleService.removeById(aId);
        return GenericResult.success();
    }
}
