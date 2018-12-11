package com.mf.center.system.role;

import com.mf.center.common.AbstractController;
import com.mf.common.base.Page;
import com.mf.common.domain.role.Role;
import com.mf.common.domain.role.RoleCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:15:58
 */
@RestController
@RequestMapping("api/role")
public class RoleAction extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(RoleAction.class);

	@Autowired
	private RoleBus bus; // 角色Service

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/save")
	public Long save(@RequestBody Role role) {
		role.setCreateTime(new Date());
		role.setCreateUserId(getUserId());
		return bus.save(role);
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
	public Integer update(@RequestBody Role role) {
		return bus.update(role);
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 **/
	@RequestMapping("/queryPage")
	public Page<Role> queryPage(@RequestBody RoleCond cond) {
		return bus.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询列表页面
	 **/
	@RequestMapping("/queryList")
	public List<Role> queryList(@RequestBody RoleCond cond) {
		return bus.queryList(cond);
	}
}