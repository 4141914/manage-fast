package com.mf.center.system.user;

import com.mf.common.base.Page;
import com.mf.common.domain.user.User;
import com.mf.common.domain.user.UserCond;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-12-11 15:50:43
 */
@FeignClient("mf-data/user")
public interface IUserClient {

	/**
	 * @方法说明:按UserName查找单个实体
	 **/
	@RequestMapping("/findByUserName")
	User findByUserName(@RequestParam("userName") String userName);

	@RequestMapping("/getUserPermissions")
	Set<String> getUserPermissions(@RequestParam("userName") Long userId);

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("save")
	Long save(@RequestBody User user);

	/**
	 * @方法说明:批量插入记录
	 **/
	@RequestMapping("insertBatch")
	int[] insertBatch(@RequestBody List<User> list);

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@RequestMapping("/saveReturnKey")
	Long saveReturnKey(@RequestBody User user);

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@RequestMapping("/delete")
	Integer delete(@RequestParam("ids[]") Long ids[]);

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	@RequestMapping("/findById")
	User findById(@RequestParam("id") Long id);

	/**
	 * @方法说明:更新记录
	 **/
	@RequestMapping("/update")
	Integer update(@RequestBody User user);

	/**
	 * @方法说明:移动端修改用户资料
	 **/
	@RequestMapping("/updateExcludePwd")
	void updateExcludePwd(@RequestBody User user);

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	@RequestMapping("/queryPage")
	Page<User> queryPage(@RequestBody UserCond cond);

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	@RequestMapping("/queryList")
	List<User> queryList(@RequestBody UserCond cond);

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	@RequestMapping("/queryCount")
	Integer queryCount(@RequestBody UserCond cond);
}