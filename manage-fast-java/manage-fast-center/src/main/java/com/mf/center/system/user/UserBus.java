package com.mf.center.system.user;

import com.google.common.collect.Maps;
import com.mf.center.system.menu.MenuBus;
import com.mf.center.system.userrole.IUserRoleClient;
import com.mf.center.system.usertoken.UserTokenService;
import com.mf.common.base.Page;
import com.mf.common.domain.user.User;
import com.mf.common.domain.user.UserCond;
import com.mf.common.domain.userrole.UserRole;
import com.mf.common.domain.userrole.UserRoleCond;
import com.mf.common.utils.RedisClient;
import com.mf.common.utils.RedisUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import com.mf.common.domain.usertoken.UserTokenService;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
@Service
public class UserBus {

	private static Logger logger = LoggerFactory.getLogger(UserBus.class);

	@Autowired
	private IUserClient client;    // 系统用户Dao

	@Autowired
	private IUserRoleClient userRoleClient;

	@Autowired
	private UserTokenService userTokenService;

	@Autowired
	private MenuBus menuBus;

	@Autowired
	private RedisClient redisClient;

	public Map<String, Object> login(String userName, String password) {
		Map<String, Object> tokenInfo = Maps.newHashMap();

		//用户信息
		User user = client.findByUserName(userName);

		//账号不存在、密码错误
		if (user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
			tokenInfo.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			tokenInfo.put("msg", "账号或密码不正确");
			return tokenInfo;
		}

		//账号锁定
		if (user.getStatus() == 0) {
			tokenInfo.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			tokenInfo.put("msg", "账号已被锁定,请联系管理");
			return tokenInfo;
		}

		user.setPassword(null);
		user.setSalt(null);
		user.setMenuList(menuBus.getUserMenuList(user.getId()));
		user.setPermissions(client.getUserPermissions(user.getId()));

		UserRoleCond userRoleCond = new UserRoleCond();
		userRoleCond.setUserId(user.getId());
		List<UserRole> userRoles = userRoleClient.queryList(userRoleCond);
		user.setRoleIdList(userRoles.parallelStream().map(UserRole::getRoleId).collect(Collectors.toList()));

		//生成token，并保存到数据库
		tokenInfo = userTokenService.createToken(user.getId());
		user.setToken(tokenInfo.get("token").toString());


		redisClient.set(RedisUtils.genUserRedisKey(user.getId()), user);

		return tokenInfo;
	}

	public void logout(User user) {
		userTokenService.removeToken(user.getToken());
		redisClient.delete(RedisUtils.genUserRedisKey(user.getId()));
	}

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(User user) {
		return client.save(user);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<User> list) {
		return client.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(User user) {
		return client.saveReturnKey(user);
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@Transactional
	public int delete(Long ids[]) {
		return client.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public User findById(Long id) {
		User user = client.findById(id);
		return user;
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public void update(User user) {
		this.client.update(user);
		this.logout(user);
	}

	/**
	 * @方法说明:更新密码
	 **/
	@Transactional
	public int updatePwd(Long userId, String oldPwd, String newPwd) {
		User user = client.findById(userId);
		String oPwd = new Sha256Hash(oldPwd, user.getSalt()).toHex();
		if (!oPwd.equals(user.getPassword())) {
			//旧密码不对
			return -1;
		}

		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(newPwd, salt).toHex());
		user.setSalt(salt);
		return client.update(user);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public void updateExcludePwd(User user) {
		client.updateExcludePwd(user);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<User> queryPage(UserCond cond) {
		return client.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<User> queryList(UserCond cond) {
		return client.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(UserCond cond) {
		return client.queryCount(cond);
	}
}