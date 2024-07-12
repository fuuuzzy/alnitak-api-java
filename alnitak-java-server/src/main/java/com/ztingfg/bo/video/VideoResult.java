package com.ztingfg.bo.video;

import com.ztingfg.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoResult {

    private Long vid;

    private Long uid;

    private String title;

    private String cover;

    private String desc;

    private LocalDateTime createdAt;

    private boolean copyright;

    private String tags;

    private Long duration;

    private Long clicks;

    private Long partition;

    private User author;

}
