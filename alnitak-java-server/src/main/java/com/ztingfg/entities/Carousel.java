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
@TableName("carousel")
public class Carousel {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 创建者
     */
    @TableField("uid")
    private Long uid;

    /**
     * 图片链接
     */
    @TableField("img")
    private String img;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 指向的链接
     */
    @TableField("url")
    private String url;

    /**
     * 主题色
     */
    @TableField("color")
    private String color;

    /**
     * 是否启用
     */
    @TableField("use")
    private Boolean use;

    /**
     * 分区ID
     */
    @TableField("partition_id")
    private Long partitionId;
}
