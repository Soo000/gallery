package com.kkwrite.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false);
	}

	/**
	 * 自定义jdbc权限认证，使用自定义表
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		JdbcUserDetailsManager judm = auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
		judm.setEnableGroups(false); // TODO 不启用组权限
		
		//judm.setRolePrefix("ROLE_"); // 设置权限名称的前缀，这里设置了，数据库的该字段可以不用加 ROLE_
		
		// 查询用户
		String selectUsersSql = "select username, password, is_valid " // 这3个字段名字可以自定义，单顺序不能改变，数量也不能少
				+ "from gly_user where username = ? and is_valid = 1";
		judm.setUsersByUsernameQuery(selectUsersSql);
		
		// 查询用户权限
		String selectAuthsSql = "select username, authority from gly_authority where username = ?";
		judm.setAuthoritiesByUsernameQuery(selectAuthsSql);
	}
	
	/**
	 * http 请求权限验证
	 */
	protected void configure(HttpSecurity http) throws Exception {
		// 禁用 csrf 保护
		http.csrf().disable();
		// 启用匿名认证, 并设置匿名用户权限
		http.anonymous().authorities("ROLE_USER");
		
		// 注册页面
		http.authorizeRequests().mvcMatchers("/jsp/signup/signup.jsp").access("permitAll");
		// 支付成功回调页面
		http.authorizeRequests().mvcMatchers("/jsp/weixin/payResult.jsp").access("permitAll");
		
		// mvcMatchers(...) 不支持通配符
		http.authorizeRequests().mvcMatchers("/").access("permitAll");
		// 首页之前
		http.authorizeRequests().mvcMatchers("/homectrl/prepage").access("permitAll");
		// 首页
		http.authorizeRequests().mvcMatchers("/homectrl/pagectrl").access("permitAll");
		// 分类
		http.authorizeRequests().mvcMatchers("/categoriesctrl/pagectrl").access("permitAll");
		// 产品详情首页
		http.authorizeRequests().mvcMatchers("/prodetailsctrl/pagectrl").access("permitAll");
		// 更多评论
		http.authorizeRequests().mvcMatchers("/prodetailsctrl/morevaluates").access("permitAll");
		// 微信接口回调：调用微信用户授权接口回调不需要登录验证
		http.authorizeRequests().mvcMatchers("/weixinctrl/authorizeback").access("permitAll");
		
		// 登录页面不需要认证
		http.authorizeRequests().mvcMatchers("/loginctrl/prelogin").access("permitAll");
		// 登录提交处理不需要认证
		http.authorizeRequests().mvcMatchers("/loginctrl/dologin").access("permitAll");
		// 登录失败url不需要认证
		http.authorizeRequests().mvcMatchers("/sys/loginFail").access("permitAll");
		// 登出不需要认证
		http.authorizeRequests().mvcMatchers("/logout").access("permitAll");
		
		// 配置购物车页面需要的角色
		http.authorizeRequests().mvcMatchers("/cartctrl/pagectrl").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/cartctrl/addtocart").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/cartctrl/removefromcart").hasRole("USER");
		
		// 配置我的需要的角色
		http.authorizeRequests().mvcMatchers("/myselfctrl/pagectrl").hasRole("USER");
		
		// 配置订单需要的角色
		http.authorizeRequests().mvcMatchers("/orderctrl/pagectrl").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/orderctrl/placeorder").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/orderctrl/submitorder").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/orderctrl/payorder").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/orderctrl/myorder").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/orderctrl/setorderstatus").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/orderctrl/deleteorder").hasRole("USER");
		
		// 配置收货地址需要的角色
		http.authorizeRequests().mvcMatchers("/addressctrl/pagectrl").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/addressctrl/preadd").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/addressctrl/add").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/addressctrl/edit").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/addressctrl/deladd").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/addressctrl/save").hasRole("USER");
		http.authorizeRequests().mvcMatchers("/addressctrl/getprocitvil").hasRole("USER");

		// 配置调用支付的jsp页面权限
		http.authorizeRequests().mvcMatchers("/jsp/weixin/callPay.jsp").hasRole("USER");
		
		// 其它都需要认证
		http.authorizeRequests().antMatchers("/**").authenticated();
		
		// 使用自定义登录页面
		http.formLogin()
			.loginPage("/loginctrl/prelogin") // 定义登录页面的 url
			.loginProcessingUrl("/loginctrl/dologin") // 登录提交的 url
			//.failureForwardUrl("/sys/loginFail") // 登录失败转发的 url 
			//.failureUrl("/html/loginFailed.html") // 登录失败重定向的 url
			//.defaultSuccessUrl("/jsp/home/home.jsp") // 如果直接访问登录页面，成功后重定向到这个页面；否则从哪里跳转到登录页面，跳回到原处；
			//.defaultSuccessUrl("/jsp/home/home.jsp", true) // 登录成功后一定重定向到指定的 url
			/*.successHandler(new AuthenticationSuccessHandler() { // 登录成功之后的 handler；不实现该 handler，登录成功，从哪里来回哪去
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					System.out.println("[ begin ] AuthenticationSuccessHandler.onAuthenticationSuccess()");
					//response.sendRedirect("/homectrl/pagectrl");
					
					System.out.println("[ end ] AuthenticationSuccessHandler.onAuthenticationSuccess()");
				}
			})*/
			.failureHandler(new AuthenticationFailureHandler() { // 登录失败之后的 handler
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					System.err.println("[ begin ] AuthenticationSuccessHandler.onAuthenticationSuccess()");
					System.err.println("[ run ] AuthenticationSuccessHandler.onAuthenticationSuccess()，失败原因 =" + exception.getMessage());
					response.sendRedirect("/loginctrl/prelogin");
					System.err.println("[ end ] AuthenticationSuccessHandler.onAuthenticationSuccess()");
				}
			})
			.permitAll();
		
		/*
		 * 退出
		 * logout 默认的 url 是 /logout
		 * 如果启用csrf, 则请求方式是 post, 否则请求方式是 Get, Put, Post, Delete 
		 */
		//http.logout();
		
		// 自定义退出
		http.logout()
			.logoutUrl("/sys/doLogout") // 定义退出页面的 url
			//.logoutRequestMatcher(new AntPathRequestMatcher("/sys/logout", "GET")) // 退出时可以使用  RequestMatcher
			.clearAuthentication(true) // 是否清除认证信息
			//.deleteCookies("") // 删除 cookies, 根据登录时设置的删除
			.invalidateHttpSession(true) // 使 httpSession 失效
			//.logoutSuccessUrl("/html/logoutSuccess.html") // 自带的退出成功重定向 
			.addLogoutHandler(new LogoutHandler() { // 退出处理类
				@Override
				public void logout(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) {
				System.out.println("[ begin ] LogoutHandler.logout().");
				System.out.println("退出...");
				System.out.println("[ end ] LogoutHandler.logout().");
			}})
			.logoutSuccessHandler(new LogoutSuccessHandler() { // 退出成功之后处理类
				@Override
				public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					System.out.println("[ begin ] LogoutSuccessHandler.onLogoutSuccess().");
					response.sendRedirect("/html/logoutSuccess.html");
					System.out.println("[ end ] LogoutSuccessHandler.onLogoutSuccess().");
				}
			});
	}
	
}
