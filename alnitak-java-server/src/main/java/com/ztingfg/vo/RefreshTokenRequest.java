package com.ztingfg.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenRequest {

    @NotNull(message = "刷新Token不能为空")
    @NotBlank(message = "刷新Token不能为空")
    private String refreshToken;

}
