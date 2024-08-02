package com.ztingfg.controllers;

import com.ztingfg.bo.FollowCount;
import com.ztingfg.bo.Following;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.services.RelationService;
import com.ztingfg.vo.FollowRequest;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RelationController {

    @Resource
    private RelationService relationService;

    @GetMapping("/v1/relation/getFollowings")
    public GenericResult<Map<String, List<Following>>> getFollowings(@RequestParam(value = "userId") Long userId,
                                                                     Pagination pagination) {
        List<Following> list = relationService.getFollowings(userId, pagination);
        return GenericResult.success(Map.of("users", list));
    }

    @GetMapping("/v1/relation/getFollowers")
    public GenericResult<Map<String, List<Following>>> getFollowers(@RequestParam(value = "userId") Long userId,
                                                                    Pagination pagination) {
        List<Following> list = relationService.getFollowers(userId, pagination);
        return GenericResult.success(Map.of("users", list));
    }

    @GetMapping("/v1/relation/getFollowCount")
    public GenericResult<FollowCount> getFollowCount(@RequestParam(value = "userId") Long userId) {
        FollowCount followCount = relationService.getFollowCount(userId);
        return GenericResult.success(followCount);
    }

    @GetMapping("/v1/relation/getUserRelation")
    public GenericResult<Long> getUserRelation(@RequestParam(value = "userId") Long userId) {
        Long relation = relationService.getUserRelation(RequestContext.getUser().getId(), userId);
        return GenericResult.success(relation);
    }

    @PostMapping("/v1/relation/follow")
    public GenericResult<Object> follow(@RequestBody FollowRequest request) {
        relationService.follow(RequestContext.getUser().getId(), request.getId());
        return GenericResult.success();
    }

}
