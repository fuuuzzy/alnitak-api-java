package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

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
@TableName("casbin_rule")
public class CasbinRule {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("ptype")
    private String ptype;

    @TableField("v0")
    private String v0;

    @TableField("v1")
    private String v1;

    @TableField("v2")
    private String v2;

    @TableField("v3")
    private String v3;

    @TableField("v4")
    private String v4;

    @TableField("v5")
    private String v5;
}
