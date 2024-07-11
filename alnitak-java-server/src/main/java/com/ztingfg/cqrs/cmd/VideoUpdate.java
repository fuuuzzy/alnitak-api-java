package com.ztingfg.cqrs.cmd;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoUpdate {

    @NotNull(message = "视频ID不能为空")
    private Long vid;

    private String title;

    private String cover;

    private String desc;

    private String tags;

}
