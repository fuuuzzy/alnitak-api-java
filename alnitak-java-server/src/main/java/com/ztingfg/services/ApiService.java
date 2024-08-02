package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.entities.Api;
import com.ztingfg.mappers.ApiMapper;
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
public class ApiService extends ServiceImpl<ApiMapper, Api> implements IService<Api> {

    @Resource
    private ApiMapper apiMapper;

    public List<Api> getRoleApi(String code) {
        return apiMapper.getRoleApi(code);
    }
}
