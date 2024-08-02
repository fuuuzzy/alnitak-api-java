package com.ztingfg.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FollowRequest {
    @NotBlank(message = "用户不能为空")
    @NotNull(message = "用户不能为空")
    private Long id;
}
