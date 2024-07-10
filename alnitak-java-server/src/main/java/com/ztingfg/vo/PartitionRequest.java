package com.ztingfg.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartitionRequest {
    @NotBlank(message = "分区名不能为空")
    @NotNull(message = "分区名不能为空")
    private String content;
    private String parentId;
}
