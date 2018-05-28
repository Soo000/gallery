package com.kkwriter.gallery.service.user;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kkwriter.gallery.entity.user.SysUser;
import com.kkwriter.gallery.repository.user.SysUserRepository;

/**
 * @author lisha
 */
@Service
public class CustomUserServiceImpl implements CustomUserService {
	@Resource(name = "sysUserRepository")
	private SysUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("该用户不存在！");
		}
		return user;
	}
}
