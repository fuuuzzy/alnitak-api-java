package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("video")
public class Video {

    @TableId(value = "id", type = IdType.AUTO)
    @JsonProperty("vid")
    private Long id;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    @JsonIgnore
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    @JsonIgnore
    private LocalDateTime deletedAt;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    @TableField("cover")
    private String cover;

    /**
     * 视频简介
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 用户ID
     */
    @TableField("uid")
    @JsonIgnore
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

    /**
     * 视频时长
     */
    @TableField("duration")
    private Double duration;
}
