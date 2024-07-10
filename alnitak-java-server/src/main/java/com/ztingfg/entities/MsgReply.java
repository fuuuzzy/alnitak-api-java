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
@TableName("msg_reply")
public class MsgReply {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 内容ID
     */
    @TableField("cid")
    private Long cid;

    /**
     * 所属用户ID
     */
    @TableField("uid")
    private Long uid;

    /**
     * 关联用户ID
     */
    @TableField("sid")
    private Long sid;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 上级回复内容
     */
    @TableField("target_reply_content")
    private String targetReplyContent;

    /**
     * 根评论内容
     */
    @TableField("root_content")
    private String rootContent;

    /**
     * 评论ID
     */
    @TableField("comment_id")
    private Long commentId;

    /**
     * 类型:0视频、1文章
     */
    @TableField("type")
    private Byte type;
}
