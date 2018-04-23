package com.kkwriter.gallery.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.kkwriter.gallery.entity.user.SysUser;

public interface SysUserRepository extends CrudRepository<SysUser, Integer> {
	
	SysUser findByUsername(String username);
	
}
