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
@TableName("menu")
public class Menu {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单访问路径
     */
    @TableField("path")
    private String path;

    /**
     * 前端组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 介绍
     */
    @TableField("desc")
    private String desc;

    /**
     * 菜单排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单标题
     */
    @TableField("title")
    private String title;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 在菜单中隐藏
     */
    @TableField("hidden")
    private Boolean hidden;

    /**
     * 缓存页面
     */
    @TableField("keep_alive")
    private Boolean keepAlive;
}
