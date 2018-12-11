package com.mf.center.system.rolemenu;

import com.mf.common.base.Page;
import com.mf.common.domain.rolemenu.RoleMenu;
import com.mf.common.domain.rolemenu.RoleMenuCond;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-12-11 15:28:54
 */
@FeignClient("mf-data/roleMenu")
public interface IRoleMenuClient {

	/**
     * @方法说明:新增记录
     **/
	@RequestMapping("save")
	Integer save(@RequestBody RoleMenu roleMenu);

	/**
     * @方法说明:批量插入记录
     **/
	@RequestMapping("insertBatch")
	int[] insertBatch(@RequestBody List<RoleMenu> list);

	/**
     * @方法说明:新增记录并返回主键
     **/
	@RequestMapping("/saveReturnKey")
	Long saveReturnKey(@RequestBody RoleMenu roleMenu);

	/**
     * @方法说明:删除记录(多条)
     **/
	@RequestMapping("/delete")
	Integer delete(@RequestParam("ids[]") Long ids[]);

	/**
     * @方法说明:按ID查找单个实体
     **/
	@RequestMapping("/findById")
	RoleMenu findById(@RequestParam("id") Long id);

	/**
     * @方法说明:更新记录
     **/
	@RequestMapping("/update")
	Integer update(@RequestBody RoleMenu roleMenu);

	/**
     * @方法说明:按条件查询分页列表
     **/
	@RequestMapping("/queryPage")
	Page<RoleMenu> queryPage(@RequestBody RoleMenuCond cond);

	/**
     * @方法说明:按条件查询不分页列表(使用范型)
     **/
	@RequestMapping("/queryList")
	List<RoleMenu> queryList(@RequestBody RoleMenuCond cond);

	/**
     * @方法说明:按条件查询记录个数
     **/
	@RequestMapping("/queryCount")
	Integer queryCount(@RequestBody RoleMenuCond cond);
}