package com.ztingfg.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztingfg.entities.Api;
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
public interface ApiMapper extends BaseMapper<Api> {

    List<Api> getRoleApi(@Param("code") String code);

}
