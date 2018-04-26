package com.kkwrite.gallery;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
        //registry.addViewController("/").setViewName("forward:/homectrl/pagectrl");
		
		// 1. 首先访问 codeUrl 值对应的地址，redirect_uri 值对应的是其回调页面的地址，带有 code 参数
		String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
            + "?appid=wx6e6fe98f77e9d950"
            + "&redirect_uri=http://artlyt.com.cn/homectrl/prepage"
            + "&response_type=code"
            + "&scope=snsapi_base"
            + "&state=STATE#wechat_redirect";
		registry.addRedirectViewController("/", codeUrl);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 
}
