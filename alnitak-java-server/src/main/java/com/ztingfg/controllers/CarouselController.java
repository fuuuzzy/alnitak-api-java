package com.ztingfg.controllers;

import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.Carousel;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.pagination.PaginationResult;
import com.ztingfg.services.CarouselService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarouselController {

    @Resource
    private CarouselService carouselService;

    @GetMapping("/v1/carousel/getCarousel")
    public GenericResult<Map<String, List<Carousel>>> getCarousel(@RequestParam(value = "partitionId") Long partitionId) {
        List<Carousel> list = carouselService.lambdaQuery()
                .eq(Carousel::getPartitionId, partitionId)
                .list();
        return GenericResult.success(Map.of("carousels", list));
    }

    @PostMapping("/v1/carousel/addCarousel")
    public GenericResult<Object> addCarousel(@RequestBody Carousel carousel) {
        carouselService.save(carousel);
        return GenericResult.success();
    }

    @PostMapping("/v1/carousel/getCarouselList")
    public GenericResult<Map<String, Object>> getCarouselList(@RequestBody Pagination pagination) {
        PaginationResult<Carousel> paginationResult = carouselService.getCarouselList(pagination);
        return GenericResult.success(Map.of("list", paginationResult.getData(), "total", paginationResult.getTotal()));
    }

    @PutMapping("/v1/carousel/editCarousel")
    public GenericResult<Object> editCarousel(@RequestBody Carousel carousel) {
        carouselService.updateById(carousel);
        return GenericResult.success();
    }
}
