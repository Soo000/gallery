package com.kkwriter.gallery.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.kkwriter.gallery.entity.user.SysUser;

/**
 * @author lisha
 */
public interface SysUserRepository extends CrudRepository<SysUser, Integer> {

	/**
	 * 通过用户名查询用户
	 * @param username 用户名
	 * @return 用户对象
	 */
	SysUser findByUsername(String username);
	
}
