package com.mf.data.system.menu;

import com.mf.common.base.Page;
import com.mf.common.domain.menu.Menu;
import com.mf.common.domain.menu.MenuCond;
import com.mf.common.utils.SqlUtil;
import com.mf.data.common.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单管理
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:19:26
 */
@Repository
public class MenuDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 **/
	public MenuDao() {
		String selectSql = "SELECT T.id,T.parent_id,T.name,T.url,T.perms,T.type,T.icon,T.order_num,pt.name parentName FROM sys_menu T LEFT JOIN sys_menu pt ON pt.id = t.parent_id WHERE 1=1 ";
		select.append(selectSql);

		String insertSql = "INSERT INTO sys_menu (id,parent_id,name,url,perms,type,icon,order_num) VALUES "
				+ "(:id,:parent_id,:name,:url,:perms,:type,:icon,:order_num) ";
		insert.append(insertSql);
	}

	/**
	 * @方法说明:根据父菜单，查询子菜单
	 **/
	public List<Menu> queryListParentId(Long parentId) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" and t.parent_id = ? order by order_num asc");
		return jdbcTemplate.query(sb.toString(), new Object[]{parentId}, new BeanPropertyRowMapper<>(Menu.class));
	}

	/**
	 * @方法说明:新增记录
	 **/
	public int save(Menu menu) {
		String sql = "REPLACE INTO sys_menu (id,parent_id,name,url,perms,type,icon,order_num) VALUES (?,?,?,?,?,?,?,?) ";
		Object[] params = {menu.getId(), menu.getParentId(), menu.getName(), menu.getUrl(), menu.getPerms(), menu.getType(), menu.getIcon(), menu.getOrderNum()};
		// logger.debug(SqlUtil.showSql(sql, params));//显示SQL语句
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	public int[] insertBatch(List<Menu> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	public long saveReturnKey(Menu menu) {
		return saveKey(menu, insert.toString(), "id");
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM sys_menu WHERE id" + SqlUtil.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public Menu findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id},
				new BeanPropertyRowMapper<>(Menu.class));
	}

	/**
	 * @方法说明:更新记录
	 **/
	public int update(Menu menu) {
		String sql = "UPDATE sys_menu SET parent_id=?, name=?, url=?, perms=?, type=?, icon=?, order_num=? WHERE ID=? ";
		Object[] params = {menu.getParentId(), menu.getName(), menu.getUrl(), menu.getPerms(), menu.getType(), menu.getIcon(), menu.getOrderNum(), menu.getId()};
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 **/
	public Page<Menu> queryPage(MenuCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY T.ID");//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),
		// cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Menu.class);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 **/
	public List<Menu> queryList(MenuCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Menu.class));
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(MenuCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM sys_menu T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

	/**
	 * @方法说明:逻辑删除记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE sys_menu SET delete_remark = 1 WHERE ID" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}