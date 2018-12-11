package com.mf.center.system.menu;

import com.google.common.collect.Maps;
import com.mf.center.common.AbstractController;
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

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:19:26
 */
@RestController
@RequestMapping("api/menu")
public class MenuAction extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(MenuAction.class);

	@Autowired
	private MenuBus bus; // 菜单管理bus

	/**
	 * @方法说明:导航菜单
	 */
	@RequestMapping("/nav")
	public HashMap<Object, Object> nav() {

		List<Menu> menuList = getUser().getMenuList();
		Set<String> permissions = getUser().getPermissions();

		HashMap<Object, Object> map = Maps.newHashMap();
		map.put("menuList", menuList);
		map.put("permissions", permissions);

		return map;
	}

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/save")
	public Long save(@RequestBody Menu menu) {
		return bus.save(menu);
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
	public Integer update(@RequestBody Menu menu) {
		return bus.update(menu);
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 **/
	@RequestMapping("/queryPage")
	public Page<Menu> queryPage(@RequestBody MenuCond cond) {
		return bus.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询列表页面
	 **/
	@RequestMapping("/queryList")
	public List<Menu> queryList(@RequestBody MenuCond cond) {
		return bus.queryList(cond);
	}
}