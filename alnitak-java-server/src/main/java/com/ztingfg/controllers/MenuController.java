package com.ztingfg.controllers;

import com.ztingfg.bo.MenuTree;
import com.ztingfg.comment.GenericResult;
import com.ztingfg.cqrs.cmd.RoleMenuUpdate;
import com.ztingfg.entities.Menu;
import com.ztingfg.services.MenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/v1/menu/getUserMenu")
    public GenericResult<Map<String, List<MenuTree>>> getUserMenu() {
        List<MenuTree> menuTrees = menuService.getUserMenu();
        return GenericResult.success(Map.of("menus", menuTrees));
    }

    @PostMapping("/v1/menu/getMenuTree")
    public GenericResult<Map<String, List<MenuTree>>> getMenuTree() {
        List<MenuTree> menuTrees = menuService.getMenuTree();
        return GenericResult.success(Map.of("menus", menuTrees));
    }

    @PostMapping("/v1/menu/addMenu")
    public GenericResult<Object> addMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return GenericResult.success();
    }

    @PostMapping("/v1/menu/editMenu")
    public GenericResult<Object> editMenu(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return GenericResult.success();
    }

    @DeleteMapping("/v1/menu/deleteMenu/{menu-id}")
    public GenericResult<Object> deleteMenu(@PathVariable("menu-id") Long menuId) {
        menuService.removeById(menuId);
        return GenericResult.success();
    }

    @GetMapping("/v1/menu/getRoleMenu")
    public GenericResult<Object> getRoleMenu(@RequestParam("code") String code) {
        List<MenuTree> menuTrees = menuService.getRoleMenu(code);
        return GenericResult.success(Map.of("menus", menuTrees));
    }

    @PutMapping("/v1/menu/editRoleMenu")
    public GenericResult<Object> editRoleMenu(@RequestBody RoleMenuUpdate roleMenuUpdate) {
        menuService.editRoleMenu(roleMenuUpdate);
        return GenericResult.success();
    }
}
