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
@TableName("danmaku")
public class Danmaku {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 视频ID
     */
    @TableField("vid")
    private Long vid;

    /**
     * 分集ID
     */
    @TableField("part")
    private Long part;

    /**
     * 时间
     */
    @TableField("time")
    private Double time;

    /**
     * 类型:0滚动
     */
    @TableField("type")
    private Long type;

    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 内容
     */
    @TableField("text")
    private String text;

    /**
     * 用户ID
     */
    @TableField("uid")
    private Long uid;
}
