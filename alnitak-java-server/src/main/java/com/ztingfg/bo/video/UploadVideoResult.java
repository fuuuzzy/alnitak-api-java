package com.ztingfg.bo.video;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UploadVideoResult {

    private Long id;

    private LocalDateTime createdAt;

    private Long vid;

    private String title;

    private Long duration;

    private Long status;

}
