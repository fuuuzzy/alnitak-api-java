package com.ztingfg.bo.article;

import com.ztingfg.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInfo {
    private Long aid;

    private Long uid;

    private String title;

    private String cover;
    private String content;
    private String tags;
    private Long clicks;
    private Long partitionId;
    private Long status;
    private Boolean copyright;
    private LocalDateTime createdAt;

    private User author;

}
