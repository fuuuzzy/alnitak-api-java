package com.ztingfg.entities;

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

    private Long menuId;

    private Long roleId;
}
