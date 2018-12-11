package com.mf.common.domain.user;

import com.mf.common.base.BaseCondition;

import java.util.Date;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
public class UserCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() {
		add(id, "AND T.id = ?");
		add(username, "AND T.username LIKE ?", 3);
		add(password, "AND T.password LIKE ?", 3);
		add(salt, "AND T.salt LIKE ?", 3);
		add(email, "AND T.email LIKE ?", 3);
		add(mobile, "AND T.mobile LIKE ?", 3);
		add(status, "AND T.status = ?");
		add(createUserId, "AND T.create_user_id = ?");
		add(createTime, "AND T.create_time = ?");
		add(branchId, "AND T.branch_id = ?");

	}

	//页面查询条件可以自行增减、把不用条件清理掉

	//
	private Long id;
	//用户名
	private String username;
	//密码
	private String password;
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
	//所属单位(0为不属于任何单位)
	private Long branchId;


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

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
}