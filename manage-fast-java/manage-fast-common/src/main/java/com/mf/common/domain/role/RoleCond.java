package com.mf.common.domain.role;

import com.mf.common.base.BaseCondition;

import java.util.Date;

/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:15:58
 */
public class RoleCond extends BaseCondition {

    /**
     * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
     **/
    @Override
    public void addCondition() {
        add(id, "AND T.id = ?");
        add(roleName, "AND T.role_name LIKE ?", 3);
        add(remark, "AND T.remark LIKE ?", 3);
        add(createUserId, "AND T.create_user_id = ?");
        add(createTime, "AND T.create_time = ?");
        
    }

    //页面查询条件可以自行增减、把不用条件清理掉

    //
    private Long id;
    //角色名称
    private String roleName;
    //备注
    private String remark;
    //创建者ID
    private Long createUserId;
    //创建时间
    private Date createTime;


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
     * 设置：角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取：角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：创建者ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建者ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

}