package com.greencloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.greencloud.interceptor.MyInterceptor;

/** 
 * 系统配置
 */

@Configuration
public class CurrencyConfiguration extends WebMvcConfigurerAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyConfiguration.class);
	
	@Autowired
	private JsonToMapper jsonToMapper;
	@Autowired
	private SysParamet sysParamet;
	
	/**
	 * 配置json处理
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(jsonToMapper);
		logger.info("json格式化配置");
		return converter;
	}
	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(sysParamet.getMaxUploadSize());
		resolver.setDefaultEncoding(sysParamet.getEncoding());
		logger.info("文件上传配置");
		return resolver;
	}
	
	/**
	 * 拦截器配置
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
		logger.info("初始化spring拦截器MyInterceptor---暂时不配置");
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
    }

	@Bean(name="druid-stat-interceptor")
	public DruidStatInterceptor druidStatInterceptor() {
		DruidStatInterceptor interceptor = new DruidStatInterceptor();
		return interceptor;
	}
	
	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		logger.info("初始化druid-spring监控");
		BeanNameAutoProxyCreator autoProxyCreator = new BeanNameAutoProxyCreator();
		autoProxyCreator.setProxyTargetClass(true);
		String[] beanNames = {"*Mapper","*Service","*Controller"};
		autoProxyCreator.setBeanNames(beanNames);
		String[] interceptorNames = {"druid-stat-interceptor"};
		autoProxyCreator.setInterceptorNames(interceptorNames);
		return autoProxyCreator;
	}
}
