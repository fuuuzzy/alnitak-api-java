package com.ztingfg.bo.video;

import com.ztingfg.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDesc {

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

    private Long partitionId;

    private User author;

    private List<VideoResource> resources;
}
