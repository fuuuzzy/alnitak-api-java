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
@TableName("msg_like")
public class MsgLike {

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
     * 发送用户ID
     */
    @TableField("sid")
    private Long sid;

    /**
     * 类型:0视频、1文章
     */
    @TableField("type")
    private Byte type;
}
