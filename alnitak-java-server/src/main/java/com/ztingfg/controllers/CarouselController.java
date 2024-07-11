package com.ztingfg.controllers;

import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.Carousel;
import com.ztingfg.services.CarouselService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarouselController {

    @Resource
    private CarouselService carouselService;

    @GetMapping("/v1/carousel/getCarousel")
    public GenericResult<Map<String, List<Carousel>>> getPartitionList(@RequestParam(value = "partitionId") Long partitionId) {
        List<Carousel> list = carouselService.lambdaQuery()
                .eq(Carousel::getPartitionId, partitionId)
                .list();
        return GenericResult.success(Map.of("carousels", list));
    }
}
