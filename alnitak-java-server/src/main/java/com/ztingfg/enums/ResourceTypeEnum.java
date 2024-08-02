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
public enum ResourceTypeEnum {

    ARTICLE("文章", 1),

    VIDEO("视频", 2);

    private final String name;

    private final Integer code;

}
