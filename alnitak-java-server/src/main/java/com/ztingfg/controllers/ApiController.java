package com.ztingfg.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.Api;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.services.ApiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiService apiService;

    @PostMapping("/v1/api/getApiList")
    public GenericResult<Map<String, Object>> getApiList(@RequestBody Pagination pagination) {
        Page<Api> page = apiService.lambdaQuery().page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return GenericResult.success(Map.of("list", page.getRecords(), "total", page.getTotal()));
    }

    @GetMapping("/v1/api/getAllApiList")
    public GenericResult<Map<String, Object>> getAllApiList() {
        List<Api> list = apiService.lambdaQuery().list();
        return GenericResult.success(Map.of("list", list));
    }

    @GetMapping("/v1/api/getRoleApi")
    public GenericResult<Map<String, Object>> getRoleApi(@RequestParam("code") String code) {
        List<Api> list = apiService.getRoleApi(code);
        return GenericResult.success(Map.of("list", list));
    }

    @PostMapping("/v1/api/addAPI")
    public GenericResult<Object> addAPI(@RequestBody Api api) {
        apiService.save(api);
        return GenericResult.success();
    }

    @PutMapping("/v1/api/editApi")
    public GenericResult<Object> editApi(@RequestBody Api api) {
        apiService.updateById(api);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/api/deleteApi/{a-id}")
    public GenericResult<Object> deleteApi(@PathVariable("a-id") Long aId) {
        apiService.removeById(aId);
        return GenericResult.success();
    }
}
