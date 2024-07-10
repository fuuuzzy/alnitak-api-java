package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Getter
@Setter
@TableName("article")
public class Article {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 封面图
     */
    @TableField("cover")
    private String cover;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 内容简介
     */
    @TableField("content_desc")
    private String contentDesc;

    /**
     * 用户ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 是否为原创
     */
    @TableField("copyright")
    private Boolean copyright;

    /**
     * 点击量
     */
    @TableField("clicks")
    private Long clicks;

    /**
     * 审核状态
     */
    @TableField("status")
    private Long status;

    /**
     * 分区ID
     */
    @TableField("partition_id")
    private Long partitionId;

    /**
     * 标签
     */
    @TableField("tags")
    private String tags;
}
