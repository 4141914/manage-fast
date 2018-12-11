package com.mf.data.system.user;

import com.google.common.collect.Lists;
import com.mf.common.base.Page;
import com.mf.common.domain.menu.Menu;
import com.mf.common.domain.menu.MenuCond;
import com.mf.common.domain.user.User;
import com.mf.common.domain.user.UserCond;
import com.mf.common.domain.userrole.UserRole;
import com.mf.common.utils.Constant;
import com.mf.common.utils.RedisClient;
import com.mf.common.utils.RedisUtils;
import com.mf.data.system.menu.MenuService;
import com.mf.data.system.userrole.UserRoleDao;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统用户
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:14:39
 */
@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao dao;    // 系统用户Dao

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RedisClient redisClient;

	/**
	 * @方法说明:按UserName查找单个实体
	 **/
	public User findByUserName(String userName) {
		return dao.findByUserName(userName);
	}

	public Set<String> getUserPermissions(long userId) {
		List<String> permsList;

		//系统管理员，拥有最高权限
		if (userId == Constant.SUPER_ADMIN) {
			List<Menu> menuList = menuService.queryList(new MenuCond());
			permsList = new ArrayList<>(menuList.size());
			for (Menu menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = dao.queryAllPerms(userId);
		}
		//用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		return permsSet;
	}

	/**
	 * @方法说明:查询用户的所有菜单ID
	 **/
	public List<Long> queryAllMenuId(Long id) {
		return dao.queryAllMenuId(id);
	}

	/**
	 * @方法说明:新增记录
	 **/
	@Transactional
	public long save(User user) {
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);

		long userId = dao.saveReturnKey(user);

		List<UserRole> userRoleList = Lists.newArrayList();

		user.getRoleIdList().forEach(roleId -> {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoleList.add(userRole);
		});

		userRoleDao.insertBatch(userRoleList);
		return userId;
	}

	/**
	 * @方法说明:批量插入记录
	 **/
	@Transactional
	public int[] insertBatch(List<User> list) {
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:新增记录并返回主键
	 **/
	@Transactional
	public long saveReturnKey(User user) {
		return dao.saveReturnKey(user);
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
	public User findById(Long id) {
		User user = dao.findById(id);
		return user;
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public void update(User user) {
		List<UserRole> userRoleList = Lists.newArrayList();
		if (null != user.getRoleIdList() && user.getRoleIdList().size() > 0) {
			user.getRoleIdList().forEach(roleId -> {
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(roleId);
				userRoleList.add(userRole);
			});
			userRoleDao.deleteByUserId(user.getId());
			userRoleDao.insertBatch(userRoleList);
		}
		if (user.getPassword() != null) {
			String salt = RandomStringUtils.randomAlphanumeric(20);
			user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
			user.setSalt(salt);
			dao.update(user);
		} else {
			dao.updateExcludePwd(user);
		}
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Transactional
	public void updateExcludePwd(User user) {
		//查询用户信息
		User redisUser = redisClient.get(RedisUtils.genUserRedisKey(user.getId()), User.class);
		redisUser.setAvatar(user.getAvatar());
		redisUser.setAvatarUrl(user.getAvatarUrl());
		redisUser.setName(user.getName());
		redisUser.setGender(user.getGender());
		redisUser.setEmail(user.getEmail());

		redisClient.set(RedisUtils.genUserRedisKey(redisUser.getId()), redisUser);
		dao.updateExcludePwd(user);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	public Page<User> queryPage(UserCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 **/
	public List<User> queryList(UserCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	public int queryCount(UserCond cond) {
		return dao.queryCount(cond);
	}
}