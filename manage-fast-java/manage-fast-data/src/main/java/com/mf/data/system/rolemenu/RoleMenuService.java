package com.mf.data.system.rolemenu;

import com.mf.common.base.Page;
import com.mf.common.domain.rolemenu.RoleMenu;
import com.mf.common.domain.rolemenu.RoleMenuCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:16:59
 */
@Service
public class RoleMenuService {

	private static Logger logger = LoggerFactory.getLogger(RoleMenuService.class);

	@Autowired
	private RoleMenuDao dao;    // 角色与菜单对应关系Dao

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(RoleMenu roleMenu) {
		return dao.save(roleMenu);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<RoleMenu> list) {
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(RoleMenu roleMenu) {
		return dao.saveReturnKey(roleMenu);
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
	public RoleMenu findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(RoleMenu roleMenu) {
		return dao.update(roleMenu);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<RoleMenu> queryPage(RoleMenuCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<RoleMenu> queryList(RoleMenuCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(RoleMenuCond cond) {
		return dao.queryCount(cond);
	}
}