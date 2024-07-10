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
@TableName("review")
public class Review {

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
     * 审核状态
     */
    @TableField("status")
    private Long status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 审核人
     */
    @TableField("uid")
    private Long uid;

    /**
     * 类型:0视频、1文章
     */
    @TableField("type")
    private Byte type;
}
