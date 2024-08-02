package com.ztingfg.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztingfg.bo.FollowCount;
import com.ztingfg.bo.Following;
import com.ztingfg.entities.Relation;
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
public interface RelationMapper extends BaseMapper<Relation> {

    List<Following> getFollowings(@Param("userId") Long userId, @Param("page") Pagination pagination);

    List<Following> getFollowers(@Param("userId") Long userId, @Param("page") Pagination pagination);

    FollowCount getFollowCount(@Param("userId") Long userId);
}
