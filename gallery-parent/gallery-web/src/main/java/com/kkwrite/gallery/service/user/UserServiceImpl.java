package com.kkwrite.gallery.service.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.user.GlyUserMapper;
import com.kkwrite.gallery.pojo.user.GlyUser;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyUserMapper glyUserMapper;

	@Override
	public GlyUser queryUserByUserId(int userId) throws ServiceException {
		logger.debug("[ begin ] UserServiceImpl.queryUserByUserId(), userId = " + userId);
		GlyUser user = glyUserMapper.selectByPrimaryKey(userId);
		logger.debug("[ end ] UserServiceImpl.queryUserByUserId().");
		return user;
	}

	@Override
	public GlyUser queryUserByName(String username) throws ServiceException {
		logger.debug("[ begin ] UserServiceImpl.queryUserByName(), username = " + username);
		
		GlyUser param = new GlyUser();
		param.setUsername(username);
		List<GlyUser> users = queryUsers(param);
		if (users == null || users.size() <= 0) {
			throw new ServiceException("没有查询到用户名为 " + username + " 的用户");
		}
		if (users.size() > 1) {
			throw new ServiceException("查询到用户名为 " + username + " 的用户不只一个");
		}
		
		logger.debug("[ end ] UserServiceImpl.queryUserByName().");
		return users.get(0);
	}

	@Override
	public List<GlyUser> queryUsers(GlyUser record) throws ServiceException {
		logger.debug("[ begin ] UserServiceImpl.queryUsers(), record = " + record);
		List<GlyUser> users = glyUserMapper.selectSelective(record);
		logger.debug("[ end ] UserServiceImpl.queryUsers().");
		return users;
	}

}
