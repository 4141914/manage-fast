package com.mf.center.system.userrole;

import com.mf.common.domain.userrole.UserRole;
import com.mf.common.domain.userrole.UserRoleCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:10:27
 */
@RestController
@RequestMapping("api/userRole")
public class UserRoleAction {

	private static Logger logger = LoggerFactory.getLogger(UserRoleAction.class);

	@Autowired
	private UserRoleBus service; // 用户与角色对应关系Service

	/**
	 * @方法说明:按条件查询列表页面
	 **/
	@RequestMapping("/queryList")
	public List<UserRole> queryList(@RequestBody UserRoleCond cond) {
		return service.queryList(cond);
	}
}