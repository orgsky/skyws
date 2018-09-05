package com.inca.skyws.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.inca.skyws.bean.User;
import com.inca.skyws.exception.ExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.service.RegisterService;
import com.inca.skyws.service.UserService;
import com.inca.skyws.system.LoginUser;

@Controller
public class SkyController {
	private static final Logger log = LoggerFactory.getLogger(SkyController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	RegisterService registerService;

	@RequestMapping("/")
	public String home(Model model, HttpServletRequest req) {
		log.info("访问商城主页....");
		List<Map<String, List<Integer>>> goodsList = new ArrayList<Map<String, List<Integer>>>();
		int totalPage = 5;
		for (int page = 0; page < totalPage; page++) {
			Map<String, List<Integer>> group = new HashMap<String, List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(4);
			group.put("group", list);
			goodsList.add(group);
		}
		model.addAttribute("goodsList", goodsList);
		LoginUser loginUser = LoginUser.getLoginUser();
		if (loginUser != null) {
			System.out.println("LoginUser---1---" + loginUser.getUsername());
		}
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest req, HttpServletResponse rep) {

		LoginUser loginUser = LoginUser.getLoginUser();
		if (loginUser != null) {
			System.out.println("LoginUser---2---" + loginUser.getUsername());
		}
		return "login";
	}

	@RequestMapping("/mycenter")
	public String mycenter(Model model, HttpServletRequest req) {
		log.info("访问个人中心....");
		LoginUser loginUser = LoginUser.getLoginUser();
		if (loginUser != null) {
			System.out.println("LoginUser---3---" + loginUser.getUsername());
		}
		return "mycenter";
	}

	@RequestMapping("/403")
	public String error(Model model, HttpServletRequest req) {
		log.info("访问个人中心....");
		LoginUser loginUser = LoginUser.getLoginUser();
		if (loginUser != null) {
			System.out.println("LoginUser---4---" + loginUser.getUsername());
		}
		return "error";
	}

	@RequestMapping("/toReg")
	public String toReg(Model model, HttpServletRequest req) {
		model.addAttribute("user", new User());
		log.info("注册....");
		LoginUser loginUser = LoginUser.getLoginUser();
		if (loginUser != null) {
			System.out.println("LoginUser---5--" + loginUser.getUsername());
		}
		return "register";
	}

	@RequestMapping("/register")
	public ModelAndView register(@Valid User user, BindingResult result, Model model) {
		ModelAndView view = new ModelAndView();
		try {
			log.info("用户注册....");
			if (result.hasErrors()) {
				view.setViewName("register");
				return view;
			}
			registerService.process(user);
			view.addObject("user", user);
			view.setViewName("redirect:/login");
			log.info("用户注册end");
		} catch (SysException e) {
			view.setViewName("register");
			ExceptionEnum expEnum = e.getExceptionEnum();
			view.addObject("error", expEnum.getCode() + ":" + expEnum.getMsg());
		} catch (Exception e) {
			view.setViewName("register");
			view.addObject("error", e.getMessage());
		}
		return view;
	}

}
