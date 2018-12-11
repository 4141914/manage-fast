package com.mf.center.system.userrole;

import com.mf.common.domain.userrole.UserRole;
import com.mf.common.domain.userrole.UserRoleCond;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-12-11 15:40:38
 */
@FeignClient("mf-data/userRole")
public interface IUserRoleClient {

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	@RequestMapping("/queryList")
	List<UserRole> queryList(@RequestBody UserRoleCond cond);

}