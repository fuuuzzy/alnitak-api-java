package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@TableName("user")
@Builder
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    @JsonProperty("uid")
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 用户名
     */
    @TableField("username")
    @JsonProperty("name")
    private String username;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @TableField("password")
    @JsonIgnore
    private String password;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 空间封面
     */
    @TableField("space_cover")
    private String spaceCover;

    /**
     * 性别:0未知、1男、3女
     */
    @TableField("gender")
    private Byte gender;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDateTime birthday;

    /**
     * 个性签名
     */
    @TableField("sign")
    private String sign;

    /**
     * 客户端IP
     */
    @TableField("client_ip")
    @JsonIgnore
    private String clientIp;

    /**
     * 角色ID
     */
    @TableField("role")
    @JsonIgnore
    private String role;

    /**
     * 颜值
     */
    @TableField("salt")
    @JsonIgnore
    private String salt;
}
