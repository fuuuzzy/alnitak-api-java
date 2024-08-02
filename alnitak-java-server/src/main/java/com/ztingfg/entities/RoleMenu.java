package com.ztingfg.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author fuuuzzy
 * @since 2024-07-10
 */
@Getter
@Setter
@TableName("role_menu")
public class RoleMenu {

    @TableId
    private Long menuId;

    private Long roleId;

    public static RoleMenu from(Long menuId, Long roleId) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.menuId = menuId;
        roleMenu.roleId = roleId;
        return roleMenu;
    }
}
