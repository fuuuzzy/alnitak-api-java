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
@TableName("msg_whisper")
public class MsgWhisper {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 用户ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 好友ID
     */
    @TableField("fid")
    private Long fid;

    /**
     * 发送者
     */
    @TableField("from_id")
    private Long fromId;

    /**
     * 接受者
     */
    @TableField("to_id")
    private Long toId;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 已读状态
     */
    @TableField("status")
    private Boolean status;
}
