package com.kkwrite.gallery.service.user;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.user.GlyUser;

public interface UserService {

	GlyUser queryUserByUserId(int userId) throws ServiceException;
	
	GlyUser queryUserByName(String username) throws ServiceException;
	
	GlyUser queryUserByOpenId(String openId) throws ServiceException;

	List<GlyUser> queryUsers(GlyUser record) throws ServiceException;
	
	int saveUser(GlyUser glyUser) throws ServiceException;
	
}