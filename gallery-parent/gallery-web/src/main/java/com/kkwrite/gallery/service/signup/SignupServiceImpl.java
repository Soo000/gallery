package com.kkwrite.gallery.service.signup;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.user.AuthoritiesMapper;
import com.kkwrite.gallery.mapper.user.GlyUserMapper;
import com.kkwrite.gallery.mapper.user.UsersMapper;
import com.kkwrite.gallery.pojo.BasePojo;
import com.kkwrite.gallery.pojo.user.Authorities;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.pojo.user.Users;

@Service("signupService")
public class SignupServiceImpl implements SignupService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GlyUserMapper GlyUserMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private AuthoritiesMapper authoritiesMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public boolean signup(String phoneNum, String password) throws ServiceException {
		logger.debug("[ begin ] SignupServiceImpl.signup().");
		
		try {
			// 查询用户是否存在 TODO
			
			GlyUser glyUser = new GlyUser();
			glyUser.setUsername(phoneNum);
			glyUser.setPhoneNum(phoneNum);
			glyUser.setUserType(1); // TODO
			glyUser.setUserLevel(1); // TODO
			glyUser.setScore(0); // TODO
			glyUser.setIsValid(BasePojo.IS_VALID_Y);
			GlyUserMapper.insertSelective(glyUser);
			
			Users users = new Users();
			users.setUsername(phoneNum);
			users.setPassword(password);
			users.setEnabled(true);
			usersMapper.insertSelective(users);
			
			Authorities authorities = new Authorities();
			authorities.setUsername(phoneNum);
			authorities.setAuthority("ROLE_USER"); // TODO
			authoritiesMapper.insertSelective(authorities);
			
			return true;
		} catch (Exception e) {
			logger.error("[ run ] SignupServiceImpl.signup(), 插入用户相关表出错！");
			e.printStackTrace();
			throw new ServiceException("插入用户相关表出错，详细原因" + e.getMessage());
		} finally {
			logger.debug("[ end ] SignupServiceImpl.signup().");
		}
	}

}
