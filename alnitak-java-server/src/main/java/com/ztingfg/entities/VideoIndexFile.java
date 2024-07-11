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
@TableName("video_index_file")
public class VideoIndexFile {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 视频资源ID
     */
    @TableField("resource_id")
    private Long resourceId;

    /**
     * 视频质量
     */
    @TableField("quality")
    private String quality;

    /**
     * 目录名称
     */
    @TableField("dir_name")
    private String dirName;

    /**
     * 文件内容
     */
    @TableField("content")
    private String content;
}
