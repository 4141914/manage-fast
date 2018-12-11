package com.mf.data.system.userrole;

import com.mf.common.base.Page;
import com.mf.common.domain.userrole.UserRole;
import com.mf.common.domain.userrole.UserRoleCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:10:27
 */
@Service
public class UserRoleService {

	private static Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	@Autowired
	private UserRoleDao dao;    // 用户与角色对应关系Dao

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(UserRole userRole) {
		return dao.save(userRole);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<UserRole> list) {
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(UserRole userRole) {
		return dao.saveReturnKey(userRole);
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
	public UserRole findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(UserRole userRole) {
		return dao.update(userRole);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<UserRole> queryPage(UserRoleCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<UserRole> queryList(UserRoleCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(UserRoleCond cond) {
		return dao.queryCount(cond);
	}
}