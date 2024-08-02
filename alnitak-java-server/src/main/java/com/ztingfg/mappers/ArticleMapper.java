package com.ztingfg.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztingfg.bo.article.ArticleInfo;
import com.ztingfg.entities.Article;
import com.ztingfg.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleInfo> getArticles(@Param("aid") Long aid,
                                  @Param("page") Pagination pagination,
                                  @Param("status") Long status);
}
