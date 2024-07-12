package com.ztingfg.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztingfg.bo.video.VideoDesc;
import com.ztingfg.bo.video.VideoInfo;
import com.ztingfg.bo.video.VideoResult;
import com.ztingfg.bo.video.VideoReview;
import com.ztingfg.entities.Video;
import com.ztingfg.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
public interface VideoMapper extends BaseMapper<Video> {

    VideoInfo getVideoInfo(@Param("vid") Long vid);

    List<VideoDesc> getVideo(@Param("vid") Long vid, @Param("page") Pagination pagination,
                             @Param("partitionId") Long partitionId);

    List<VideoReview> getReviewList(Pagination pagination);

    List<VideoResult> getVideoListManage(Pagination pagination);
}
