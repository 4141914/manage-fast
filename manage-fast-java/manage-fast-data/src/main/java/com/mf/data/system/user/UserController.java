package com.mf.data.system.user;

import com.mf.common.base.Page;
import com.mf.common.domain.user.User;
import com.mf.common.domain.user.UserCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
@RestController
@RequestMapping("user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service; // 系统用户Service


	/**
	 * @方法说明:按UserName查找单个实体
	 **/
	@RequestMapping("/findByUserName")
	public User findByUserName(@RequestParam("userName") String userName) {
		return service.findByUserName(userName);
	}

	@RequestMapping("/getUserPermissions")
	public Set<String> getUserPermissions(@RequestParam("userName") Long userId) {
		return service.getUserPermissions(userId);
	}

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/save")
	public Long save(@RequestBody User user) {
		return service.save(user);
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@RequestMapping("/delete")
	public Integer delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

	/**
	 * @方法说明:修改记录
	 **/
	@RequestMapping("/update")
	public void update(@RequestBody User user) {
		service.update(user);
	}

	/**
	 * @方法说明:移动端修改用户资料
	 **/
	@RequestMapping("/updateExcludePwd")
	public void updateExcludePwd(@RequestBody User user) {
		service.updateExcludePwd(user);
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 **/
	@RequestMapping("/queryPage")
	public Page<User> queryPage(@RequestBody UserCond cond) {
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询列表页面
	 **/
	@RequestMapping("/queryList")
	public List<User> queryList(@RequestBody UserCond cond) {
		return service.queryList(cond);
	}

	/**
	 * @方法说明:跳转到详细页面
	 **/
	@RequestMapping("/findById")
	public User findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}
}