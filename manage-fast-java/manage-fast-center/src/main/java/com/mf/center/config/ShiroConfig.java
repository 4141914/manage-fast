package com.mf.center.config;

import com.mf.center.system.oauth2.OAuth2Filter;
import com.mf.center.system.oauth2.OAuth2Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean("sessionManager")
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdCookieEnabled(true);
		return sessionManager;
	}

	@Bean("securityManager")
	public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(oAuth2Realm);
		securityManager.setSessionManager(sessionManager);

		return securityManager;
	}

	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);

		//oauth过滤
		Map<String, Filter> filters = new HashMap<>();
		filters.put("oauth2", new OAuth2Filter());
		shiroFilter.setFilters(filters);

		Map<String, String> filterMap = new LinkedHashMap<>();
		filterMap.put("/api/user/login", "anon");
		filterMap.put("/api/user/loginByMobile", "anon");
		filterMap.put("/api/user/findUserInfo", "anon");
		filterMap.put("/api/partner/save", "anon");
		filterMap.put("/api/user/appRegister", "anon");
		filterMap.put("/api/teachActivity/queryAllList", "anon");
		filterMap.put("/api/teach/queryList", "anon");
		filterMap.put("/api/partner/qrCode", "anon");
		filterMap.put("/api/upload/uploadImg", "anon");
		filterMap.put("/api/executeExpress/queryPageByIndex", "anon");
		filterMap.put("/api/progressReport/queryPageByIndex", "anon");
		filterMap.put("/api/consultation/queryListByIndex", "anon");        //首页查询资讯列表
		filterMap.put("/api/**", "oauth2");
//		filterMap.put("/**", "anon");
		shiroFilter.setFilterChainDefinitionMap(filterMap);

		return shiroFilter;
	}

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
