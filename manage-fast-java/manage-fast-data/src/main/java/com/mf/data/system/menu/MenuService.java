package com.mf.data.system.menu;

import com.mf.common.base.Page;
import com.mf.common.domain.menu.Menu;
import com.mf.common.domain.menu.MenuCond;
import com.mf.common.utils.Constant;
import com.mf.data.system.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:19:26
 */
@Service
public class MenuService {

	private static Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	private MenuDao dao;    // 菜单管理Dao

	@Autowired
	private UserService userService;

	/**
	 * @param parentId 父菜单ID
	 * @方法说明:根据父菜单，查询子菜单
	 **/
	public List<Menu> queryListParentId(Long parentId) {
		return dao.queryListParentId(parentId);
	}

	/**
	 * 根据父菜单，查询子菜单
	 *
	 * @param parentId   父菜单ID
	 * @param menuIdList 用户菜单ID
	 */
	private List<Menu> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<Menu> menuList = queryListParentId(parentId);
		if (menuIdList == null) {
			return menuList;
		}

		List<Menu> userMenuList = new ArrayList<>();
		for (Menu menu : menuList) {
			if (menuIdList.contains(menu.getId())) {
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	public List<Menu> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if (userId == Constant.SUPER_ADMIN) {
			return getAllMenuList(null);
		}

		//用户菜单列表
		List<Long> menuIdList = userService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<Menu> getAllMenuList(List<Long> menuIdList) {
		//查询根菜单列表
		List<Menu> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(Menu menu) {
		return dao.save(menu);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<Menu> list) {
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(Menu menu) {
		return dao.saveReturnKey(menu);
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@Transactional
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public Menu findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(Menu menu) {
		return dao.update(menu);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<Menu> queryPage(MenuCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<Menu> queryList(MenuCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(MenuCond cond) {
		return dao.queryCount(cond);
	}

	/**
	 * 递归
	 */
	private List<Menu> getMenuTreeList(List<Menu> menuList, List<Long> menuIdList) {
		List<Menu> subMenuList = new ArrayList<Menu>();

		for (Menu entity : menuList) {
			//目录
			if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {
				entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}

		return subMenuList;
	}
}