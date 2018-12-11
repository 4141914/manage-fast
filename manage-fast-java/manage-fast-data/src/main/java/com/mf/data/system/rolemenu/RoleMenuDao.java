package com.mf.data.system.rolemenu;

import com.mf.common.base.Page;
import com.mf.common.domain.rolemenu.RoleMenu;
import com.mf.common.domain.rolemenu.RoleMenuCond;
import com.mf.common.utils.SqlUtil;
import com.mf.data.common.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:16:59
 */
@Repository
public class RoleMenuDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 **/
	public RoleMenuDao() {
		String selectSql = "SELECT T.id,T.role_id,T.menu_id FROM sys_role_menu T WHERE 1=1";
		select.append(selectSql);

		String insertSql = "INSERT INTO sys_role_menu (id,role_id,menu_id) VALUES "
				+ "(:id,:roleId,:menuId) ";
		insert.append(insertSql);
	}

	/**
	 * @方法说明:新增记录
	 **/
	public int save(RoleMenu roleMenu) {
		String sql = "REPLACE INTO sys_role_menu (id,role_id,menu_id) VALUES (?,?,?) ";
		Object[] params = {roleMenu.getId(), roleMenu.getRoleId(), roleMenu.getMenuId()};
		// logger.debug(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	public int[] insertBatch(List<RoleMenu> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	public long saveReturnKey(RoleMenu roleMenu) {
		return saveKey(roleMenu, insert.toString(), "id");
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM sys_role_menu WHERE id" + SqlUtil.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int deleteByRoleId(Long roleId) {
		String sql = "DELETE FROM sys_role_menu WHERE role_id = ?";
		Object[] params = {roleId};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public RoleMenu findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id},
				new BeanPropertyRowMapper<>(RoleMenu.class));
	}

	/**
	 * @方法说明:更新记录
	 **/
	public int update(RoleMenu roleMenu) {
		String sql = "UPDATE sys_role_menu SET role_id=?, menu_id=? WHERE ID=? ";
		Object[] params = {roleMenu.getRoleId(), roleMenu.getMenuId(), roleMenu.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 **/
	public Page<RoleMenu> queryPage(RoleMenuCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY T.ID");//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),
		// cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, RoleMenu.class);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<RoleMenu> queryList(RoleMenuCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(RoleMenu.class));
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(RoleMenuCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM sys_role_menu T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

	/**
	 * @方法说明:逻辑删除记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE sys_role_menu SET delete_remark = 1 WHERE ID" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}