package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.entities.LikeVideo;
import com.ztingfg.mappers.LikeVideoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class LikeVideoServiceImp extends ServiceImpl<LikeVideoMapper, LikeVideo> implements IService<LikeVideo> {

}
