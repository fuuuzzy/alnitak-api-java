package com.ztingfg.bo.video;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoInfo {

    private Long vid;

    private String title;

    private String cover;

    private String desc;

    private Boolean copyright;

    private Long status;

    private Long partitionId;

    private String tags;

    private Long duration;

    private List<VideoResource> resource;

}
