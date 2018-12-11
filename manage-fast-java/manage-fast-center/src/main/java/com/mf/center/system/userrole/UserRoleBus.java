package com.mf.center.system.userrole;

import com.mf.common.domain.userrole.UserRole;
import com.mf.common.domain.userrole.UserRoleCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:10:27
 */
@Service
public class UserRoleBus {

	private static Logger logger = LoggerFactory.getLogger(UserRoleBus.class);

	@Autowired
	private IUserRoleClient client;    // 用户与角色对应关系Dao

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<UserRole> queryList(UserRoleCond cond) {
		return client.queryList(cond);
	}
}