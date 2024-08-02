package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("role")
public class Role {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 角色名
     */
    @TableField("name")
    private String name;

    /**
     * 角色代码
     */
    @TableField("`code`")
    private String code;

    /**
     * 介绍
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 角色首页
     */
    @TableField("home_page")
    private String homePage;
}
