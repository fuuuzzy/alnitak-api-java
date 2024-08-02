package com.ztingfg.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ztingfg.entities.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getUserMenu(@Param("userId") Long id);

    List<Menu> getRoleMenu(String code);
}
