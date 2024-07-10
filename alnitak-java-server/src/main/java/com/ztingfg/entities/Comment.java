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
@TableName("comment")
public class Comment {

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
     * 所属用户
     */
    @TableField("uid")
    private Long uid;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 提及的用户名
     */
    @TableField("at_usernames")
    private String atUsernames;

    /**
     * 提及的用户的ID
     */
    @TableField("at_user_ids")
    private String atUserIds;

    /**
     * 回复的用户的ID
     */
    @TableField("reply_user_id")
    private Long replyUserId;

    /**
     * 回复用户的用户名
     */
    @TableField("reply_user_name")
    private String replyUserName;

    /**
     * 所属评论ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 类型:0视频、1文章
     */
    @TableField("type")
    private Byte type;
}
