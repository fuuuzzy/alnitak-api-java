package com.ztingfg.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VideoUpdateRequest {
    @NotBlank(message = "id不能为空")
    @NotNull(message = "id不能为空")
    private Long vid;
    @NotBlank(message = "标题不能为空")
    @NotNull(message = "标题不能为空")
    private String title;
    @NotBlank(message = "封面不能为空")
    @NotNull(message = "封面不能为空")
    private String cover;
    private String desc;
    private boolean copyright;
}
