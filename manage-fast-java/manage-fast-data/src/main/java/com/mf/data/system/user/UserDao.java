package com.mf.data.system.user;

import com.google.common.collect.Lists;
import com.mf.common.base.Page;
import com.mf.common.domain.menu.Menu;
import com.mf.common.domain.rolemenu.RoleMenu;
import com.mf.common.domain.user.User;
import com.mf.common.domain.user.UserCond;
import com.mf.common.utils.SqlUtil;
import com.mf.data.common.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
@Repository
public class UserDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 **/
	public UserDao() {
		String selectSql = "SELECT T.id,T.username,T.password,T.salt,T.email,T.mobile,T.status,T.create_user_id,T.create_time,T.name,T.gender,T.avatar FROM sys_user T WHERE 1=1";
		select.append(selectSql);

		String insertSql = "INSERT INTO sys_user (id,username,password,salt,email,mobile,status,create_user_id,create_time,name,gender,avatar) VALUES "
				+ "(:id,:username,:password,:salt,:email,:mobile,:status,:createUserId,:createTime,:name,:gender,:avatar) ";
		insert.append(insertSql);
	}


	/**
	 * @方法说明:查询用户的所有权限
	 **/
	public List<String> queryAllPerms(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select m.perms from sys_user_role ur ");
		sql.append("LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id ");
		sql.append("LEFT JOIN sys_menu m on rm.menu_id = m.id ");
		sql.append("where ur.user_id = ?");

		return jdbcTemplate.queryForList(sql.toString(), String.class, id);
	}

	/**
	 * @方法说明:按UserName查找单个实体
	 **/
	public User findByUserName(String userName) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.username=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{userName},
				new BeanPropertyRowMapper<>(User.class));
	}


	/**
	 * @方法说明:按UserName查找单个实体
	 **/
	public User findByMobile(String mobile) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.mobile=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{mobile},
				new BeanPropertyRowMapper<>(User.class));
	}

	/**
	 * @方法说明:查询用户的所有菜单ID
	 **/
	public List<Long> queryAllMenuId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select rm.id,rm.role_id,rm.menu_id from sys_user_role ur ");
		sql.append("LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id ");
		sql.append("where ur.user_id = ? ");

		List<RoleMenu> query = jdbcTemplate.query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(RoleMenu.class));

		List<Long> menuIdList = Lists.newArrayList();
		query.forEach(roleMenu -> {
			if (!menuIdList.contains(roleMenu.getMenuId()))
				menuIdList.add(roleMenu.getMenuId());
		});

		return menuIdList;
	}

	/**
	 * @方法说明:新增记录
	 **/
	public int save(User user) {
		String sql = "REPLACE INTO sys_user (id,username,password,salt,email,mobile,status,create_user_id,create_time,name,gender,avatar) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		Object[] params = {user.getId(), user.getUsername(), user.getPassword(), user.getSalt(), user.getEmail(), user.getMobile(),
				user.getStatus(), user.getCreateUserId(), user.getCreateTime(), user.getName(), user.getGender(),
				user.getAvatar()};
		// logger.debug(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	public int[] insertBatch(List<User> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	public long saveReturnKey(User user) {
		return saveKey(user, insert.toString(), "id");
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM sys_user WHERE id" + SqlUtil.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public User findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id},
				new BeanPropertyRowMapper<>(User.class));
	}

	/**
	 * @方法说明:更新记录
	 **/
	public int update(User user) {
		String sql = "UPDATE sys_user SET username=?, password=?, salt=?, email=?, mobile=?, status=?, create_user_id=?, create_time=?,name=?,avatar=?,gender=? WHERE ID=? ";
		Object[] params = {user.getUsername(), user.getPassword(), user.getSalt(), user.getEmail(), user.getMobile(),
				user.getStatus(), user.getCreateUserId(), user.getCreateTime(), user.getName(),
				user.getAvatar(), user.getGender(), user.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:更新记录
	 **/
	public int updateExcludePwd(User user) {
		String sql = "UPDATE sys_user SET username=?, salt=?, email=?, mobile=?, status=?, create_user_id=?, create_time=?,name=?,avatar=?,gender=? WHERE ID=? ";
		Object[] params = {user.getUsername(), user.getSalt(), user.getEmail(), user.getMobile(),
				user.getStatus(), user.getCreateUserId(), user.getCreateTime(), user.getName(),
				user.getAvatar(), user.getGender(), user.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 **/
	public Page<User> queryPage(UserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY T.ID");//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),
		// cond.getArray()));//显示SQL语句
		Page<User> userPage = queryPage(sb.toString(), cond, User.class);
		userPage.getContent().forEach(user -> {
			user.setPassword(null);
			user.setSalt(null);
		});
		return userPage;
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<User> queryList(UserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		List<User> userList = jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(User.class));
		userList.forEach(user -> {
			user.setPassword(null);
			user.setSalt(null);
		});
		return userList;
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(UserCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM sys_user T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

	/**
	 * @方法说明:逻辑删除记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE sys_user SET delete_remark = 1 WHERE ID" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}