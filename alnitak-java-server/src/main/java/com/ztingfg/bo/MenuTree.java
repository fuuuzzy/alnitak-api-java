package com.ztingfg.bo;

import com.ztingfg.entities.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTree {

    private Long id;
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单访问路径
     */
    private String path;

    /**
     * 前端组件路径
     */
    private String component;

    /**
     * 介绍
     */
    private String desc;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 父菜单ID
     */
    private Long parentId;

    private Meta meta;

    private List<MenuTree> children;

    public static MenuTree from(Menu menu) {
        Meta m = new Meta(menu.getTitle(), menu.getHidden(), menu.getKeepAlive(), menu.getIcon());

        return new MenuTree(menu.getId(), menu.getName(), menu.getPath(), menu.getComponent(), menu.getDesc(), menu.getSort(), menu.getParentId(), m, null);
    }

    public record Meta(String title, Boolean hidden, Boolean keepAlive, String icon) {
    }
}
