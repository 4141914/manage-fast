package com.mf.center.system.oauth2;

import com.mf.center.system.usertoken.UserToken;
import com.mf.center.system.usertoken.UserTokenService;
import com.mf.common.domain.user.User;
import com.mf.common.utils.RedisClient;
import com.mf.common.utils.RedisUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OAuth2Realm extends AuthorizingRealm {

	@Autowired
	private UserTokenService userTokenService;

	@Autowired
	private RedisClient redisClient;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof OAuth2Token;
	}

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(user.getPermissions());
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String accessToken = (String) token.getPrincipal();

		//根据accessToken，查询用户信息
		UserToken tokenEntity = userTokenService.findByToken(accessToken);
		//token失效
		if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
			throw new IncorrectCredentialsException("token失效，请重新登录");
		}

		//查询用户信息
		User user = redisClient.get(RedisUtils.genUserRedisKey(tokenEntity.getId()), User.class);
		if (user == null) {
			userTokenService.removeToken(tokenEntity.getToken());
			throw new IncorrectCredentialsException("token失效，请重新登录");
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
		return info;
	}
}
