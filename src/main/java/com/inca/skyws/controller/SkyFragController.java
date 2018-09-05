package com.inca.skyws.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.inca.skyws.bean.User;
import com.inca.skyws.repository.UserDao;
import com.inca.skyws.system.LoginUser;

@Controller
@RequestMapping("/frag")
public class SkyFragController {
	private static final Logger log = LoggerFactory.getLogger(SkyFragController.class);
	@Autowired
	UserDao userDao;

	@RequestMapping("/")
	public ModelAndView chat(ModelAndView model, HttpServletRequest req) {
		String parameter = req.getParameter("name");
		log.info("请求界面:" + parameter);
		if (parameter == null) {
			return model;
		}
		LoginUser loginUser = LoginUser.getLoginUser();
		User user = new User();
		if (loginUser != null) {
			user = userDao.findById(loginUser.getId()).get();
		}
		model.addObject("user", user);
		model.setViewName(parameter);
		return model;
	}
}
