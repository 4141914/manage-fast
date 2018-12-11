package com.mf.common.domain.role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:15:58
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

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

	//以下为辅助字段
	//菜单集合
	private List<Long> menuIdList;

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

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}
}
