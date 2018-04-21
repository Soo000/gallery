package com.kkwrite.gallery.service.user;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.user.GlyUser;

public interface UserService {

	public GlyUser queryUserByUserId(int userId) throws ServiceException;
	
	public GlyUser queryUserByName(String username) throws ServiceException;

	public List<GlyUser> queryUsers(GlyUser record) throws ServiceException;
	
}