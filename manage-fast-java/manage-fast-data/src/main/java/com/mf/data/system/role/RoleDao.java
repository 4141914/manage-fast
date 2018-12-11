package com.mf.data.system.role;

import com.mf.common.base.Page;
import com.mf.common.domain.role.Role;
import com.mf.common.domain.role.RoleCond;
import com.mf.common.utils.SqlUtil;
import com.mf.data.common.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:15:58
 */
@Repository
public class RoleDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 **/
	public RoleDao() {
		String selectSql = "SELECT T.id,T.role_name,T.remark,T.create_user_id,T.create_time FROM sys_role T WHERE 1=1";
		select.append(selectSql);

		String insertSql = "INSERT INTO sys_role (id,role_name,remark,create_user_id,create_time) VALUES "
				+ "(:id,:roleName,:remark,:createUserId,:createTime) ";
		insert.append(insertSql);
	}

	/**
	 * @方法说明:新增记录
	 **/
	public int save(Role role) {
		String sql = "REPLACE INTO sys_role (id,role_name,remark,create_user_id,create_time) VALUES (?,?,?,?,?) ";
		Object[] params = {role.getId(), role.getRoleName(), role.getRemark(), role.getCreateUserId(), role.getCreateTime()};
		// logger.debug(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	public int[] insertBatch(List<Role> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	public long saveReturnKey(Role role) {
		return saveKey(role, insert.toString(), "id");
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM sys_role WHERE id" + SqlUtil.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public Role findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id},
				new BeanPropertyRowMapper<>(Role.class));
	}

	/**
	 * @方法说明:更新记录
	 **/
	public int update(Role role) {
		String sql = "UPDATE sys_role SET role_name=?, remark=?, create_user_id=?, create_time=? WHERE ID=? ";
		Object[] params = {role.getRoleName(), role.getRemark(), role.getCreateUserId(), role.getCreateTime(), role.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 **/
	public Page<Role> queryPage(RoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY T.ID");//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),
		// cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Role.class);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<Role> queryList(RoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Role.class));
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(RoleCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM sys_role T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

	/**
	 * @方法说明:逻辑删除记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE sys_role SET delete_remark = 1 WHERE ID" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}