package com.ztingfg.controllers;

import com.ztingfg.comment.GenericResult;
import com.ztingfg.services.ResourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @PutMapping("/v1/resource/modifyTitle")
    public GenericResult<Object> modifyTitle(@RequestBody com.ztingfg.entities.Resource resource) {
        resourceService.modifyTitle(resource);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/resource/deleteResource/{rid}")
    public GenericResult<Object> deleteResource(@PathVariable("rid") Long rid) {
        resourceService.removeById(rid);
        return GenericResult.success();
    }

}
