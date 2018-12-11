package com.mf.center.system.rolemenu;

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
public class RoleMenuBus {

	private static Logger logger = LoggerFactory.getLogger(RoleMenuBus.class);

	@Autowired
	private IRoleMenuClient client;    // 角色与菜单对应关系Dao

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(RoleMenu roleMenu) {
		return client.save(roleMenu);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<RoleMenu> list) {
		return client.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(RoleMenu roleMenu) {
		return client.saveReturnKey(roleMenu);
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@Transactional
	public int delete(Long ids[]) {
		return client.delete(ids);//物理删除
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public RoleMenu findById(Long id) {
		return client.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(RoleMenu roleMenu) {
		return client.update(roleMenu);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<RoleMenu> queryPage(RoleMenuCond cond) {
		return client.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<RoleMenu> queryList(RoleMenuCond cond) {
		return client.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(RoleMenuCond cond) {
		return client.queryCount(cond);
	}
}