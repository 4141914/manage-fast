package com.mf.common.domain.menu;

import java.io.Serializable;
import java.util.List;


/**
 * 菜单管理
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:19:26
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	//父菜单ID，一级菜单为0
	private Long parentId;
	//菜单名称
	private String name;
	//菜单URL
	private String url;
	//授权(多个用逗号分隔，如：user:list,user:create)
	private String perms;
	//类型   0：目录   1：菜单   2：按钮
	private Integer type;
	//菜单图标
	private String icon;
	//排序
	private Integer orderNum;

	//辅助字段，不存数据库
	private List<Menu> list;

	private String parentName;

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
	 * 设置：父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：父菜单ID，一级菜单为0
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置：菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：菜单名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：菜单URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}

	/**
	 * 获取：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public String getPerms() {
		return perms;
	}

	/**
	 * 设置：类型   0：目录   1：菜单   2：按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取：类型   0：目录   1：菜单   2：按钮
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置：菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取：菜单图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public List<Menu> getList() {
		return list;
	}

	public void setList(List<Menu> list) {
		this.list = list;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
