package com.inca.skyws.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inca.skyws.bean.User;
import com.inca.skyws.service.UserService;
import com.inca.skyws.system.LoginUser;

@Service
public class SkyUserDetailsService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(SkySecurityConfig.class);
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User user = userService.findUserByAccount(account);
		log.info("查到的用户：" + user);
		if (user == null) {
			throw new UsernameNotFoundException("没此用户");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_BUYER"));
		LoginUser loginUser = new LoginUser(account, user.getPassword(),true,true,true,true, authorities);
		BeanUtils.copyProperties(user, loginUser);
		return loginUser;
	}

}