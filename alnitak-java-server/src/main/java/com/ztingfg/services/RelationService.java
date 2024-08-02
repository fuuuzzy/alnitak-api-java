package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.bo.FollowCount;
import com.ztingfg.bo.Following;
import com.ztingfg.entities.Relation;
import com.ztingfg.mappers.RelationMapper;
import com.ztingfg.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class RelationService extends ServiceImpl<RelationMapper, Relation> implements IService<Relation> {

    @Resource
    private RelationMapper relationMapper;

    public List<Following> getFollowings(Long userId, Pagination pagination) {
        return relationMapper.getFollowings(userId, pagination);
    }

    public List<Following> getFollowers(Long userId, Pagination pagination) {
        return relationMapper.getFollowers(userId, pagination);
    }

    public FollowCount getFollowCount(Long userId) {
        return relationMapper.getFollowCount(userId);
    }

    public Long getUserRelation(Long userId, Long targetUserId) {
        Relation one = lambdaQuery().eq(Relation::getUid, userId)
                .eq(Relation::getTargetUid, targetUserId)
                .one();
        if (one != null) {
            return one.getRelation();
        }

        one = lambdaQuery().eq(Relation::getTargetUid, userId)
                .eq(Relation::getUid, targetUserId)
                .one();

        return one == null ? 0 : one.getRelation();
    }

    public void follow(Long userId, Long targetUserId) {
        Relation one = lambdaQuery().eq(Relation::getUid, userId)
                .eq(Relation::getTargetUid, targetUserId)
                .one();

        if (one != null) {
            lambdaUpdate()
                    .set(Relation::getRelation, 2)
                    .eq(Relation::getUid, userId)
                    .eq(Relation::getTargetUid, targetUserId)
                    .ne(Relation::getRelation, 1);
            return;
        }

        one = lambdaQuery().eq(Relation::getTargetUid, userId)
                .eq(Relation::getUid, targetUserId)
                .one();
        if (one != null) {
            lambdaUpdate()
                    .set(Relation::getRelation, 2)
                    .eq(Relation::getTargetUid, userId)
                    .eq(Relation::getUid, targetUserId)
                    .ne(Relation::getRelation, 1);
            return;
        }

        Relation relation = new Relation();
        relation.setUid(userId);
        relation.setTargetUid(targetUserId);
        relation.setRelation(1L);
        save(relation);
    }
}
