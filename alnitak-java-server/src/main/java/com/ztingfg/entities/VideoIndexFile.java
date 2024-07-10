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
@TableName("video_index_file")
public class VideoIndexFile {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
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
