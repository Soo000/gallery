package com.kkwriter.gallery.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kkwriter.gallery.service.user.CustomUserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource(name = "customUserServiceImpl")
	private CustomUserService customUserService;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/lib/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/druid/**").permitAll()
				.antMatchers("/**").hasAnyRole("ROOT", "ADMIN")
				.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID")
			.and().csrf().ignoringAntMatchers("/druid/**");
	}
	
}
