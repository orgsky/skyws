package com.inca.skyws.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inca.skyws.bean.User;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.UserInfo;

public interface UserService {
	public static final String NAME = "userService";

	public User findUserByAccount(String account);

	public User saveUser(ModelAndView model, MultipartFile file, HttpServletRequest req,User user) throws SysException;

	
	public List<UserInfo> findAllUsersByKeyword(String keyword) throws Exception;

	public List<UserInfo> findAllMyFriends() throws Exception;
	
	User findUserByUsercode(String usercode);

}
