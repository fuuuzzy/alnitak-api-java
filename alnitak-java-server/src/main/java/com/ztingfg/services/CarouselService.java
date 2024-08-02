package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.entities.Carousel;
import com.ztingfg.mappers.CarouselMapper;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
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
public class CarouselService extends ServiceImpl<CarouselMapper, Carousel> implements IService<Carousel> {

    public PaginationResult<Carousel> getCarouselList(Pagination pagination) {
        Page<Carousel> page = lambdaQuery().page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return PaginationResult.from(page.getRecords(), page.getTotal());
    }
}
