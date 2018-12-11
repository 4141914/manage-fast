package com.mf.center.system.role;

import com.mf.common.base.Page;
import com.mf.common.domain.role.Role;
import com.mf.common.domain.role.RoleCond;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-12-11 15:27:44
 */
@FeignClient("mf-data/role")
public interface IRoleClient {

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("save")
	Integer save(@RequestBody Role role);

	/**
	 * @方法说明:批量插入记录
	 **/
	@RequestMapping("insertBatch")
	int[] insertBatch(@RequestBody List<Role> list);

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@RequestMapping("/saveReturnKey")
	Long saveReturnKey(@RequestBody Role role);

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@RequestMapping("/delete")
	Integer delete(@RequestParam("ids[]") Long ids[]);

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	@RequestMapping("/findById")
	Role findById(@RequestParam("id") Long id);

	/**
	 * @方法说明:更新记录
	 **/
	@RequestMapping("/update")
	Integer update(@RequestBody Role role);

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	@RequestMapping("/queryPage")
	Page<Role> queryPage(@RequestBody RoleCond cond);

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	@RequestMapping("/queryList")
	List<Role> queryList(@RequestBody RoleCond cond);

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	@RequestMapping("/queryCount")
	Integer queryCount(@RequestBody RoleCond cond);
}