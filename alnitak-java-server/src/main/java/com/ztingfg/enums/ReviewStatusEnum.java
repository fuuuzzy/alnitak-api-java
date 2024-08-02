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
public enum ReviewStatusEnum {

    PASS("通过", 1L),

    NO_PASS("不通过", 2L),

    NO_AUDITS("待审核", 0L);

    private final String name;

    private final Long code;

}
