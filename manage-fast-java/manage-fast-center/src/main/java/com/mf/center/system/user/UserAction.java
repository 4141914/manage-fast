package com.mf.center.system.user;

import com.mf.center.common.AbstractController;
import com.mf.center.system.usertoken.UserToken;
import com.mf.common.base.Page;
import com.mf.common.domain.user.User;
import com.mf.common.domain.user.UserCond;
import com.mf.common.utils.RedisClient;
import com.mf.common.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
@RestController
@RequestMapping("api/user")
public class UserAction extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(UserAction.class);

	@Autowired
	private UserBus bus; // 系统用户Service

	@Autowired
	private RedisClient redisClient;

	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public Map<String, Object> login(@RequestParam("username") String userName, @RequestParam("password") String password) throws IOException {
		return bus.login(userName, password);
	}

	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public void logout() {
		bus.logout(getUser());
	}

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/save")
	public Long save(@RequestBody User user) {
		user.setCreateTime(new Date());
		user.setCreateUserId(getUserId());
		return bus.save(user);
	}

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/findUserInfo")
	public User findUserInfo(@RequestParam(required = false, value = "token") String token) {
		if (token == null) {
			return null;
		}
		UserToken userToken = redisClient.get(RedisUtils.genTokenRedisKey(token), UserToken.class);

		if (userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()) {
			return null;
		}

		//查询用户信息
		User user = redisClient.get(RedisUtils.genUserRedisKey(userToken.getId()), User.class);
		if (user == null) {
			return null;
		}
		return user;
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@RequestMapping("/delete")
	public Integer delete(@RequestParam("ids[]") Long ids[]) {
		return bus.delete(ids);
	}

	/**
	 * @方法说明:修改记录
	 **/
	@RequestMapping("/update")
	public void update(@RequestBody User user) {
		bus.update(user);
	}

	/**
	 * @方法说明:更新密码
	 **/
	@RequestMapping("/updatePwd")
	public int updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
		return bus.updatePwd(getUserId(), oldPwd, newPwd);
	}

	/**
	 * @方法说明:移动端修改用户资料
	 **/
	@RequestMapping("/updateExcludePwd")
	public void updateExcludePwd(@RequestBody User user) {
		bus.updateExcludePwd(user);
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 **/
	@RequestMapping("/queryPage")
	public Page<User> queryPage(@RequestBody UserCond cond) {
		return bus.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询列表页面
	 **/
	@RequestMapping("/queryList")
	public List<User> queryList(@RequestBody UserCond cond) {
		return bus.queryList(cond);
	}

	/**
	 * @方法说明:跳转到详细页面
	 **/
	@RequestMapping("/findById")
	public User findById(@RequestParam("id") Long id) {
		return bus.findById(id);
	}
}