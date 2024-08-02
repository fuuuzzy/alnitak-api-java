package com.ztingfg.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.entities.MsgAnnounce;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.services.MsgAnnounceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MsgAnnounceController {

    @Resource
    private MsgAnnounceService msgAnnounceService;

    @GetMapping("/v1/message/getAnnounce")
    public GenericResult<Map<String, Object>> getAnnounce(Pagination pagination) {
        Page<MsgAnnounce> page = msgAnnounceService.lambdaQuery().page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return GenericResult.success(Map.of("list", page.getRecords(), "total", page.getTotal()));
    }

    @PostMapping("/v1/message/addAnnounce")
    public GenericResult<Object> addAnnounce(@RequestBody MsgAnnounce msgAnnounce) {
        msgAnnounceService.save(msgAnnounce);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/message/deleteAnnounce/{msg-id}")
    public GenericResult<Object> deleteAnnounce(@PathVariable("msg-id") Long msgId) {
        msgAnnounceService.removeById(msgId);
        return GenericResult.success();
    }
}
