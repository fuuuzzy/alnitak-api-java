package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.bo.video.*;
import com.ztingfg.comment.BizStatus;
import com.ztingfg.comment.exception.BusinessException;
import com.ztingfg.cqrs.cmd.VideoUpdate;
import com.ztingfg.entities.Video;
import com.ztingfg.entities.VideoIndexFile;
import com.ztingfg.mappers.VideoMapper;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.vo.SearchVideoRequest;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class VideoService extends ServiceImpl<VideoMapper, Video> implements IService<Video> {

    private static final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private ResourceService resourceService;

    @Resource
    private VideoIndexFileService videoIndexFileService;

    public void updateVideo(Video video) {
        logger.info("video ={}", video);
        if (video.getId() != null) {
            Video v = getById(video.getId());
            v.setTitle(video.getTitle());
            v.setCover(video.getCover());
            v.setDesc(video.getDesc());
            v.setCopyright(video.getCopyright());
            v.setTags(video.getTags());
            updateById(v);
        }
        throw new BusinessException(BizStatus.IllegalParams);
    }

    public void updateVideo(VideoUpdate video) {
        Video v = getById(video.getVid());
        v.setTitle(video.getTitle());
        v.setCover(video.getCover());
        v.setDesc(video.getDesc());
        v.setTags(video.getTags());
        updateById(v);
    }

    public VideoInfo getVideoInfo(Long vid) {
        return videoMapper.getVideoInfo(vid);
    }

    public PaginationResult<Video> getVideos(Pagination pagination) {
        Page<Video> page = page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return PaginationResult.from(page.getRecords(), page.getTotal());
    }

    public List<VideoTitle> getVideoTitle() {
        return lambdaQuery()
                .select(Video::getId, Video::getTitle)
                .list()
                .stream().map(video -> new VideoTitle(video.getId(), video.getTitle())).toList();
    }

    public VideoDesc getVideoById(Long vid) {
        List<VideoDesc> video = videoMapper.getVideo(vid, null, null);
        if (Optional.ofNullable(video).isPresent()) {
            return video.getFirst();
        }
        return null;
    }

    public List<String> getResourceQuality(Long resourceId) {
        return videoIndexFileService.lambdaQuery().select(VideoIndexFile::getQuality)
                .eq(VideoIndexFile::getResourceId, resourceId)
                .list()
                .stream().map(VideoIndexFile::getQuality)
                .toList();
    }

    public String getVideoFile(Long resourceId, String quality) {
        return videoIndexFileService.lambdaQuery().select(VideoIndexFile::getContent)
                .eq(VideoIndexFile::getResourceId, resourceId)
                .eq(VideoIndexFile::getQuality, quality)
                .one().getContent();
    }

    public PaginationResult<Video> getVideoByUser(Long userId, Pagination pagination) {
        Page<Video> page = lambdaQuery().eq(Video::getUid, userId)
                .page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return PaginationResult.from(page.getRecords(), page.getTotal());
    }

    public List<VideoDesc> getHotVideo(Pagination pagination) {
        return videoMapper.getVideo(null, pagination, null);
    }

    public List<VideoDesc> getVideoListByPartition(int size, Long partitionId) {
        Pagination pagination = new Pagination();
        pagination.setPageSize(size);
        return videoMapper.getVideo(null, pagination, partitionId);
    }

    public List<VideoDesc> searchVideo(SearchVideoRequest request) {
        return videoMapper.getVideo(null, null, null);
    }

    public PaginationResult<VideoReview> getReviewList(Pagination pagination) {
        Long count = lambdaQuery().count();
        List<VideoReview> videoReviews = videoMapper.getReviewList(pagination);
        return PaginationResult.from(videoReviews, count);
    }

    public List<com.ztingfg.entities.Resource> getReviewResourceList(Long vid) {
        return resourceService.lambdaQuery()
                .eq(com.ztingfg.entities.Resource::getVid, vid)
                .eq(com.ztingfg.entities.Resource::getStatus, 0)
                .list();
    }

    public PaginationResult<VideoResult> getVideoListManage(Pagination pagination) {
        Long count = lambdaQuery().count();
        List<VideoResult> videoResults = videoMapper.getVideoListManage(pagination);
        return PaginationResult.from(videoResults, count);
    }
}
