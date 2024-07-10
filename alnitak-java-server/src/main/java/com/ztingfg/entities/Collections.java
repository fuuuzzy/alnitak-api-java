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
@TableName("collection")
public class Collections {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 所属用户
     */
    @TableField("uid")
    private Long uid;

    /**
     * 收藏夹名称
     */
    @TableField("name")
    private String name;

    /**
     * 简介
     */
    @TableField("desc")
    private String desc;

    /**
     * 封面
     */
    @TableField("cover")
    private String cover;

    /**
     * 是否公开
     */
    @TableField("open")
    private Boolean open;
}
