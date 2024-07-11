package com.ztingfg.bo.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoResource {

    private LocalDateTime createdAt;

    private Long vid;

    private Long id;

    private String title;

    private Long duration;

    private Long status;
}