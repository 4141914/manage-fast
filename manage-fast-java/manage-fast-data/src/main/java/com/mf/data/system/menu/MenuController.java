package com.mf.data.system.menu;

import com.mf.common.base.Page;
import com.mf.common.domain.menu.Menu;
import com.mf.common.domain.menu.MenuCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:19:26
 */
@RestController
@RequestMapping("menu")
public class MenuController {

	private static Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService service; // 菜单管理Service

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/save")
	public Long save(@RequestBody Menu menu) {
		return service.save(menu);
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
	public Integer update(@RequestBody Menu menu) {
		return service.update(menu);
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 **/
	@RequestMapping("/queryPage")
	public Page<Menu> queryPage(@RequestBody MenuCond cond) {
		return service.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询列表页面
	 **/
	@RequestMapping("/queryList")
	public List<Menu> queryList(@RequestBody MenuCond cond) {
		return service.queryList(cond);
	}

	@RequestMapping("/getUserMenuList")
	public List<Menu> getUserMenuList(@RequestParam("userId") Long userId) {
		return service.getUserMenuList(userId);
	}
}