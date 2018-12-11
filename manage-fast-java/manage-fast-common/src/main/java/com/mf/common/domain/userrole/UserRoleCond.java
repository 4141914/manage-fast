package com.mf.common.domain.userrole;

import com.mf.common.base.BaseCondition;

/**
 * 用户与角色对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:10:27
 */
public class UserRoleCond extends BaseCondition {

    /**
     * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
     **/
    @Override
    public void addCondition() {
        add(id, "AND T.id = ?");
        add(userId, "AND T.user_id = ?");
        add(roleId, "AND T.role_id = ?");
        
    }

    //页面查询条件可以自行增减、把不用条件清理掉

    //
    private Long id;
    //用户ID
    private Long userId;
    //角色ID
    private Long roleId;


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
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
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

}