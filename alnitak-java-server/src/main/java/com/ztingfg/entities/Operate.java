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
@TableName("operate")
public class Operate {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 请求ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 请求方法
     */
    @TableField("method")
    private String method;

    /**
     * 请求路径
     */
    @TableField("path")
    private String path;

    /**
     * 请求状态
     */
    @TableField("status")
    private Long status;

    /**
     * 延迟
     */
    @TableField("latency")
    private Long latency;

    /**
     * 代理
     */
    @TableField("agent")
    private String agent;

    /**
     * 错误信息
     */
    @TableField("error_message")
    private String errorMessage;

    /**
     * 请求Body
     */
    @TableField("body")
    private String body;

    /**
     * 响应Msg
     */
    @TableField("msg")
    private String msg;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
}
