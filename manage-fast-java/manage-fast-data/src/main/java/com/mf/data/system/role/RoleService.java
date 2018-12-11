package com.mf.data.system.role;

import com.google.common.collect.Lists;
import com.mf.common.base.Page;
import com.mf.common.domain.role.Role;
import com.mf.common.domain.role.RoleCond;
import com.mf.common.domain.rolemenu.RoleMenu;
import com.mf.data.system.rolemenu.RoleMenuDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:15:58
 */
@Service
public class RoleService {

	private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleDao dao;    // 角色Dao

	@Autowired
	private RoleMenuDao roleMenuDao;

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(Role role) {
		long roleId = dao.saveReturnKey(role);

		List<RoleMenu> roleMenuList = Lists.newArrayList();

		role.getMenuIdList().forEach(menuId -> {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(menuId);
			roleMenu.setRoleId(roleId);
			roleMenuList.add(roleMenu);
		});

		roleMenuDao.insertBatch(roleMenuList);
		return roleId;
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<Role> list) {
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(Role role) {
		return dao.saveReturnKey(role);
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@Transactional
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除

		Arrays.stream(ids).forEach(id -> {
			roleMenuDao.deleteByRoleId(id);
		});
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public Role findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(Role role) {
		List<RoleMenu> roleMenuList = Lists.newArrayList();

		role.getMenuIdList().forEach(menuId -> {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(menuId);
			roleMenu.setRoleId(role.getId());
			roleMenuList.add(roleMenu);
		});

		roleMenuDao.deleteByRoleId(role.getId());
		roleMenuDao.insertBatch(roleMenuList);
		return dao.update(role);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<Role> queryPage(RoleCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<Role> queryList(RoleCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(RoleCond cond) {
		return dao.queryCount(cond);
	}
}