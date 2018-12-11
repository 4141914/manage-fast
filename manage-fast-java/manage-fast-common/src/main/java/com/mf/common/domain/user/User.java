package com.mf.common.domain.user;

import com.mf.common.domain.menu.Menu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	//用户名
	private String username;
	//密码
	private String password;
	//姓名
	private String name;
	//盐
	private String salt;
	//邮箱
	private String email;
	//手机号
	private String mobile;
	//状态  0：禁用   1：正常
	private Byte status;
	//创建者ID
	private Long createUserId;
	//创建时间
	private Date createTime;
	//性别1男 0女
	private Byte gender;
	//头像
	private String avatar;

	//以下为辅助字段
	private String avatarUrl;//头像

	//在session中获取的user对象有以下属性个属性
	private List<Menu> menuList;
	private Set<String> permissions;
	private String token;
	private List<Long> roleIdList;//角色id集合


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
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 获取：盐
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置：状态  0：禁用   1：正常
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * 获取：状态  0：禁用   1：正常
	 */
	public Byte getStatus() {
		return status;
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

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
