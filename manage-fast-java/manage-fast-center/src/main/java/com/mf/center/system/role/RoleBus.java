package com.mf.center.system.role;

import com.mf.common.base.Page;
import com.mf.common.domain.role.Role;
import com.mf.common.domain.role.RoleCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:15:58
 */
@Service
public class RoleBus {

	private static Logger logger = LoggerFactory.getLogger(RoleBus.class);

	@Autowired
	private IRoleClient client;    // 角色Dao

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(Role role) {
		return client.save(role);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<Role> list) {
		return client.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(Role role) {
		return client.saveReturnKey(role);
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@Transactional
	public int delete(Long ids[]) {
		return client.delete(ids);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	public Role findById(Long id) {
		return client.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(Role role) {
		return client.update(role);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<Role> queryPage(RoleCond cond) {
		return client.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<Role> queryList(RoleCond cond) {
		return client.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(RoleCond cond) {
		return client.queryCount(cond);
	}
}