package com.kkwrite.gallery;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 尝试自动查找默认的 servlet(不在拦截静态资源).
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/jsp", ".jsp");
	}
	
	/**
	 * 默认登录页面
	 */
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 转到首页
		registry.addViewController("/").setViewName("forward:/homectrl/pagectrl");
		// 添加视图
        super.addViewControllers(registry);
    } 
}
