package com.mf.center.system.menu;

import com.mf.common.base.Page;
import com.mf.common.domain.menu.Menu;
import com.mf.common.domain.menu.MenuCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单管理
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:19:26
 */
@Service
public class MenuBus {

	private static Logger logger = LoggerFactory.getLogger(MenuBus.class);

	@Autowired
	private IMenuClient client;    // 菜单管理Dao

	public List<Menu> getUserMenuList(Long userId) {
		return client.getUserMenuList(userId);
	}

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(Menu menu) {
		return client.save(menu);
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<Menu> list) {
		return client.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(Menu menu) {
		return client.saveReturnKey(menu);
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
	public Menu findById(Long id) {
		return client.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public int update(Menu menu) {
		return client.update(menu);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<Menu> queryPage(MenuCond cond) {
		return client.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<Menu> queryList(MenuCond cond) {
		return client.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(MenuCond cond) {
		return client.queryCount(cond);
	}
}