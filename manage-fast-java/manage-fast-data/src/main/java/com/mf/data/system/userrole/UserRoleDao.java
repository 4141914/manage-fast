package com.mf.data.system.userrole;

import com.mf.common.base.Page;
import com.mf.common.domain.userrole.UserRole;
import com.mf.common.domain.userrole.UserRoleCond;
import com.mf.common.utils.SqlUtil;
import com.mf.data.common.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:10:27
 */
@Repository
public class UserRoleDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 **/
	public UserRoleDao() {
		String selectSql = "SELECT T.id,T.user_id,T.role_id FROM sys_user_role T WHERE 1=1";
		select.append(selectSql);

		String insertSql = "INSERT INTO sys_user_role (id,user_id,role_id) VALUES "
				+ "(:id,:userId,:roleId) ";
		insert.append(insertSql);
	}

	/**
	 * @方法说明:新增记录
	 **/
	public int save(UserRole userRole) {
		String sql = "REPLACE INTO sys_user_role (id,user_id,role_id) VALUES (?,?,?) ";
		Object[] params = {userRole.getId(), userRole.getUserId(), userRole.getRoleId()};
		// logger.debug(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	public int[] insertBatch(List<UserRole> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	public long saveReturnKey(UserRole userRole) {
		return saveKey(userRole, insert.toString(), "id");
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM sys_user_role WHERE id" + SqlUtil.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int deleteByUserId(Long userId) {
		String sql = "DELETE FROM sys_user_role WHERE user_id = ?";
		Object[] params = {userId};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public UserRole findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id},
				new BeanPropertyRowMapper<>(UserRole.class));
	}

	/**
	 * @方法说明:更新记录
	 **/
	public int update(UserRole userRole) {
		String sql = "UPDATE sys_user_role SET user_id=?, role_id=? WHERE ID=? ";
		Object[] params = {userRole.getUserId(), userRole.getRoleId(), userRole.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 **/
	public Page<UserRole> queryPage(UserRoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY T.ID");//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),
		// cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, UserRole.class);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<UserRole> queryList(UserRoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(UserRole.class));
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(UserRoleCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM sys_user_role T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

	/**
	 * @方法说明:逻辑删除记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE sys_user_role SET delete_remark = 1 WHERE ID" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}