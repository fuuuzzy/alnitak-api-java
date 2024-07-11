package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
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
@Builder
@TableName("resource")
public class Resource {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT)
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 所属视频
     */
    @TableField("vid")
    private Long vid;

    /**
     * 所属用户
     */
    @TableField("uid")
    private Long uid;

    /**
     * 分P使用的标题
     */
    @TableField("title")
    private String title;

    /**
     * 视频时长
     */
    @TableField("duration")
    private Double duration;

    /**
     * 审核状态
     */
    @TableField("status")
    private Long status;
}
