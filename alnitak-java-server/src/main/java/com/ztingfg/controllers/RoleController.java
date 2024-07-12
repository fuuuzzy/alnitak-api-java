package com.ztingfg.controllers;

import com.ztingfg.comment.GenericResult;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.entities.User;
import com.ztingfg.services.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/v1/role/getRoleInfo")
    public GenericResult<Object> getRoleInfo() {
        User user = RequestContext.getUser();
        return GenericResult.success(Map.of("role", user.getRoleInfo()));
    }

}
