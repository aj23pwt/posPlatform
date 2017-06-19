package com.greencloud.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
 * Shiro配置
 */

@Configuration
public class ShiroConfiguration {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm() {  
        MyShiroRealm realm = new MyShiroRealm(); 
        return realm;
    }
	
	@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
	
	@Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
	
	/**
	 * 安全管理器 
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(myShiroRealm);
		return manager;
	}
	
	 @Bean
	 public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
	 }
	
	/**
	 *  Shiro 的 Web 过滤器 
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		bean.setLoginUrl("/");
		//用户访问未对其授权的资源时，所显示的连接
		bean.setUnauthorizedUrl("/auth/unauthorized");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
		//filterChainDefinitionMap.put("/", "anon");//anon 可以理解为不拦截
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}
}
