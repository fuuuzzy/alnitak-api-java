package com.ztingfg.bo.article;

import com.ztingfg.entities.Article;
import lombok.Data;

@Data
public class ArticleTitle {

    private Long aid;

    private String title;

    public static ArticleTitle from(Article article) {
        ArticleTitle articleTitle = new ArticleTitle();
        articleTitle.setAid(article.getId());
        articleTitle.setTitle(article.getTitle());
        return articleTitle;
    }
}
