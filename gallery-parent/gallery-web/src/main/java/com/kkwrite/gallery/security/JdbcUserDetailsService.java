package com.kkwrite.gallery.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kkwrite.gallery.exception.NotAnyAuthoritiesException;
import com.kkwrite.gallery.service.user.UserService;

/**
 * 自定义 UserDetailsService 实现自定认证 
 * @author Ke.Wang
 * @date 2018年5月18日 下午2:28:48
 */
public class JdbcUserDetailsService implements UserDetailsService {

	private UserService userService;
	
	public JdbcUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("userService = " + userService);
		/*// 用户查询 SQL
		String userSql = "select username, password from gly_user where username = ?";
		List<Map<String, Object>> users = this.queryForList(userSql, username);
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("用户不存在");
		} else if (users.size() > 1) {
			throw new BadCredentialsException("用户不唯一异常");
		}
		
		Map<String, Object> userMap = users.get(0);
		String password = (String) userMap.get("password");
		// 用户权限查询 SQL
		String authSql = "select * from gly_authority where username = ?";
		List<Map<String, Object>> authorities = this.queryForList(authSql, username);
		if (authorities == null || authorities.size() <= 0) {
			throw new NotAnyAuthoritiesException("没有任何权限");
		}
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(authorities.size());
		for (Map<String, Object> authority: authorities) {
			String authorityValue = (String) authority.get("authority");
			if (authorityValue != null) {
				grantedAuthorities.add(new SimpleGrantedAuthority(authorityValue));
			}
		}*/
		String password = "";
		List<Map<String, Object>> authorities = new ArrayList<Map<String, Object>>();
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(authorities.size());
		return new User(username, password, grantedAuthorities);
	}

}
