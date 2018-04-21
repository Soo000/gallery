package com.kkwrite.gallery.service.signup;

import com.kkwrite.gallery.exception.ServiceException;

public interface SignupService {

	public boolean signup(String phoneNum, String password) throws ServiceException;
	
}