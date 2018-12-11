package com.mf.center.system.usertoken;

import com.google.common.collect.Maps;
import com.mf.common.utils.RedisClient;
import com.mf.common.utils.RedisUtils;
import com.mf.common.utils.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 系统用户Token
 *
 * @author lijianan
 * @email 4141914@gmail.com
 * @date 2018-04-10 22:21:59
 */
@Service
public class UserTokenService {

	private static Logger logger = LoggerFactory.getLogger(UserTokenService.class);

	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Autowired
	private RedisClient redisClient;

	/**
	 * @方法说明:按token查找单个实体
	 **/
	public UserToken findByToken(String token) {
		UserToken userToken = redisClient.get(RedisUtils.genTokenRedisKey(token), UserToken.class);
		return userToken;
	}


	public void removeToken(String token) {
		redisClient.delete(RedisUtils.genTokenRedisKey(token));
	}


	public Map<String, Object> createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		UserToken userToken = new UserToken();
		userToken.setId(userId);
		userToken.setToken(token);
		userToken.setUpdateTime(now);
		userToken.setExpireTime(expireTime);

		redisClient.set(RedisUtils.genTokenRedisKey(token), userToken);

		Map<String, Object> tokenInfo = Maps.newHashMap();
		tokenInfo.put("token", token);
		tokenInfo.put("expire", EXPIRE);

		return tokenInfo;
	}

}