package com.ztingfg.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztingfg.bo.MenuTree;
import com.ztingfg.comment.RequestContext;
import com.ztingfg.cqrs.cmd.RoleMenuUpdate;
import com.ztingfg.entities.Menu;
import com.ztingfg.entities.RoleMenu;
import com.ztingfg.entities.User;
import com.ztingfg.mappers.MenuMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IService<Menu> {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuService roleMenuService;

    public List<MenuTree> getUserMenu() {
        User user = RequestContext.getUser();
        List<Menu> menus = menuMapper.getUserMenu(user.getId());
        List<MenuTree> menuTrees = new ArrayList<>();
        buildTree(menuTrees, menus, 0L);
        return menuTrees;
    }

    private void buildTree(List<MenuTree> menuTrees, List<Menu> menus, Long parentId) {
        for (Menu menu : menus) {
            if (Objects.equals(menu.getParentId(), parentId)) {
                MenuTree menuTree = MenuTree.from(menu);
                List<MenuTree> menuTreeChildren = new ArrayList<>();
                buildTree(menuTreeChildren, menus, menu.getId());
                menuTree.setChildren(menuTreeChildren);
                menuTrees.add(menuTree);
            }
        }
    }

    public List<MenuTree> getMenuTree() {
        List<Menu> menus = list();
        List<MenuTree> menuTrees = new ArrayList<>();
        buildTree(menuTrees, menus, 0L);
        return menuTrees;
    }

    public List<MenuTree> getRoleMenu(String code) {
        List<Menu> menus = menuMapper.getRoleMenu(code);
        List<MenuTree> menuTrees = new ArrayList<>();
        buildTree(menuTrees, menus, 0L);
        return menuTrees;
    }

    @Transactional
    public void editRoleMenu(RoleMenuUpdate roleMenuUpdate) {
        roleMenuService.removeById(roleMenuUpdate.getId());
        List<RoleMenu> list = roleMenuUpdate.getMenuIds().stream()
                .map(m -> RoleMenu.from(roleMenuUpdate.getId(), m))
                .toList();
        roleMenuService.saveBatch(list);
    }
}
