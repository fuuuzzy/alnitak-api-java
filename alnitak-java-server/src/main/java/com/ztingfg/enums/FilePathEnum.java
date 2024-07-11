package com.ztingfg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件路径枚举
 *
 * @author yezhiqiu
 * @date 2021/08/04
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {
    /**
     * 头像路径
     */
    IMAGE("image/", "图片路径"),

    /**
     * 视频路径
     */
    VIDEO("video/", "视频路径");
    /**
     * 路径
     */
    private final String path;

    /**
     * 描述
     */
    private final String desc;

}
