package com.mf.common.domain.rolemenu;

import com.mf.common.base.BaseCondition;

/**
 * 角色与菜单对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:16:59
 */
public class RoleMenuCond extends BaseCondition {

    /**
     * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
     **/
    @Override
    public void addCondition() {
        add(id, "AND T.id = ?");
        add(roleId, "AND T.role_id = ?");
        add(menuId, "AND T.menu_id = ?");
        
    }

    //页面查询条件可以自行增减、把不用条件清理掉

    //
    private Long id;
    //角色ID
    private Long roleId;
    //菜单ID
    private Long menuId;


    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置：菜单ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：菜单ID
     */
    public Long getMenuId() {
        return menuId;
    }

}