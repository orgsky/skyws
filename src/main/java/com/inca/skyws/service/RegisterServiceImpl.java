package com.inca.skyws.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inca.skyws.bean.User;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.repository.UserDao;

@Transactional
@Service(RegisterService.NAME)
public class RegisterServiceImpl implements RegisterService {
	private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Autowired
	@Lazy
	BCryptPasswordEncoder encoder;
	@Autowired
	UserDao userDao;
	@Autowired
	CodeRuleService ruleService;

	@Override
	public void process(User user) throws SysException, Exception {
		ObjectMapper om = new ObjectMapper();
		log.info("前后台验证通过的用户信息：" + om.writeValueAsString(user));
		String code = ruleService.genCode(User.class);
		user.setUsercode(code);
		String rawPwd = user.getPassword();
		user.setWebpwd(rawPwd);
		user.setPassword(encoder.encode(rawPwd));
		user.setSex(1);
		userDao.save(user);
	}

}
