package com.ztingfg.controllers;

import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.Partition;
import com.ztingfg.services.PartitionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PartitionController {

    @Resource
    private PartitionService partitionService;

    @GetMapping("/v1/partition/getPartitionList")
    public GenericResult<Map<String, List<Partition>>> getPartitionList(@RequestParam(value = "type", required = false, defaultValue = "1") Integer type) {
        List<Partition> list = partitionService.lambdaQuery()
                .eq(Partition::getType, type)
                .list();
        return GenericResult.success(Map.of("partitions", list));
    }

    @PostMapping("/v1/partition/addPartition")
    public GenericResult<Object> addPartition(@RequestBody Partition partition) {
        partitionService.save(partition);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/partition/deletePartition/{pid}")
    public GenericResult<Object> deletePartition(@PathVariable Long pid) {
        partitionService.removeById(pid);
        return GenericResult.success();
    }
}
