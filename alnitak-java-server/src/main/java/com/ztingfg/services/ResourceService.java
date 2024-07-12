package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.entities.Resource;
import com.ztingfg.mappers.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ResourceService extends ServiceImpl<ResourceMapper, Resource> implements IService<Resource> {

    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @jakarta.annotation.Resource
    private ResourceMapper resourceMapper;

    public void modifyTitle(Resource resource) {
        logger.info("Modifying title ={}", resource);
        lambdaUpdate().set(Resource::getTitle, resource.getTitle())
                .eq(Resource::getId, resource.getId()).update();
    }
}
