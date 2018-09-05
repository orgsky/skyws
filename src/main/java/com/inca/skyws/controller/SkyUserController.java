package com.inca.skyws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inca.skyws.bean.User;
import com.inca.skyws.exception.ExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.ApiResponse;
import com.inca.skyws.model.UserInfo;
import com.inca.skyws.service.GroupService;
import com.inca.skyws.service.UserService;

@Controller
@RequestMapping("/user")
public class SkyUserController {
	private static final Logger log = LoggerFactory.getLogger(SkyUserController.class);
	@Autowired
	UserService userService;
	@Autowired
	GroupService groupService;

	@RequestMapping("/persist")
	public ModelAndView saveAndUpdate(ModelAndView model, @RequestParam("headimg") MultipartFile file,
			HttpServletRequest req, @Valid User user, BindingResult result) {
		try {
			log.info("更新用户信息...");
			User saveUser = userService.saveUser(model, file, req, user);
			model.addObject("user", saveUser);
			log.info("更新用户信息end");
		} catch (SysException e) {
			ExceptionEnum en = e.getExceptionEnum();
			model.addObject("error", en.getCode() + ":" + en.getMsg());
		}
		model.setViewName("redirect:/mycenter");
		return model;
	}

	@RequestMapping("/users")
	public ModelAndView users(ModelAndView model, HttpServletRequest req) throws Exception {
		String keyword = req.getParameter("keyword");
		String searchType = req.getParameter("searchType");
		log.info("关键字:" + keyword);
		log.info("查找类型:" + searchType);
		if (StringUtils.isEmpty(keyword)) {
			model.addObject("users", new ArrayList<UserInfo>());
			model.setViewName("users");
			return model;
		}
		if ("1".equals(searchType)) {
			List<UserInfo> users = userService.findAllUsersByKeyword(keyword);
			model.addObject("users", users);
			model.setViewName("users");
		} else {
			List<UserInfo> users = groupService.findAllGroupsByKeyword(keyword);
			model.addObject("users", users);
			model.setViewName("groups");
		}
		return model;
	}

	@RequestMapping("/myfriends")
	@ResponseBody
	public ApiResponse myFriends() {
		try {
			log.info("我的好友数据...");
			List<UserInfo> groupInfo = userService.findAllMyFriends();
			log.info("我的好友数据end");
			return ApiResponse.sucess().putData(groupInfo);
		} catch (SysException e) {
			ExceptionEnum xe = e.getExceptionEnum();
			log.info("我的好友数据异常:" + xe.getCode() + ":" + xe.getMsg());
			return ApiResponse.fail().putMsg(xe.getCode() + ":" + xe.getMsg());
		} catch (Exception e) {
			log.info("我的好友数据异常:" + e.getMessage());
			return ApiResponse.fail().putMsg(e.getMessage());
		}
	}



}
