package com.ztingfg.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.cqrs.cmd.RoleHomeUpdate;
import com.ztingfg.entities.Role;
import com.ztingfg.entities.User;
import com.ztingfg.pagination.Pagination;
import com.ztingfg.services.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/v1/role/getRoleList")
    public GenericResult<Object> getRoleList(@RequestBody Pagination pagination) {
        Page<Role> page = roleService.lambdaQuery()
                .page(Page.of(pagination.getPage(), pagination.getPageSize()));
        return GenericResult.success(Map.of("list", page.getRecords(), "total", page.getTotal()));
    }

    @GetMapping("/v1/role/getAllRoleList")
    public GenericResult<Object> getAllRoleList() {
        List<Role> list = roleService.lambdaQuery().list();
        return GenericResult.success(Map.of("roles", list));
    }

    @PostMapping("/v1/role/addRole")
    public GenericResult<Object> addRole(@RequestBody Role role) {
        roleService.save(role);
        return GenericResult.success();
    }

    @PutMapping("/v1/role/editRole")
    public GenericResult<Object> editRole(@RequestBody Role role) {
        roleService.updateById(role);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/role/deleteRole/{r-id}")
    public GenericResult<Object> deleteRole(@PathVariable("r-id") Long rId) {
        roleService.removeById(rId);
        return GenericResult.success();
    }

    @PutMapping("/v1/role/editRoleHome")
    public GenericResult<Object> editRoleHome(@RequestBody RoleHomeUpdate roleHomeUpdate) {
        roleService.lambdaUpdate()
                .set(Role::getHomePage, roleHomeUpdate.getHome())
                .eq(Role::getId, roleHomeUpdate.getId()).update();
        return GenericResult.success();
    }

}
