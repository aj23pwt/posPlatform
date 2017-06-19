package com.greencloud.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greencloud.dao.vo.SysUser;
import com.greencloud.service.SysUserService;

/** 
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	SysUserService sysUserService;
	
	/**
	 * 权限认证，为当前登录的Subject授予角色和权限 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("##################执行Shiro权限认证##################");
		//获取当前登录输入的用户名
		String loginName = (String)super.getAvailablePrincipal(principalCollection);
		SysUser sysUser = new SysUser();
		sysUser.setAccount(loginName);
		sysUser = sysUserService.selectByAccountAndPassword(sysUser);
		if(null != sysUser){
			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
			return info;
		}
		return null;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE)); 
        //查出是否有此用户
        SysUser sysUser = new SysUser();
		sysUser.setAccount(token.getUsername());
		sysUser = sysUserService.selectByAccountAndPassword(sysUser);
        if(sysUser!=null){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(sysUser.getAccount(), sysUser.getPassword(), getName());
        }
		return null;
	}

}
