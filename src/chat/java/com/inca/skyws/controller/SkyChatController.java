package com.inca.skyws.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inca.skyws.exception.ExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.ApiResponse;
import com.inca.skyws.service.ChatService;
import com.inca.skyws.service.UserService;
import com.inca.skyws.system.LoginUser;

@Controller
@RequestMapping("/chat")
public class SkyChatController {

	private static final Logger log = LoggerFactory.getLogger(SkyChatController.class);
	@Autowired
	UserService userService;
	@Autowired
	ChatService chatService;

	@RequestMapping("/")
	public String chat(Model model, HttpServletRequest req) {
		log.info(req.getSession().getId());
		try {
			model.addAttribute("loginUser", LoginUser.getLoginUser());
			model.addAttribute("userList", userService.findLatestChatFriends());
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
		}
		return "chat";
	}
	@RequestMapping("/joinParty")
	@ResponseBody
	public ApiResponse joinParty(Model model, HttpServletRequest req) {
		try {
			String partCode = req.getParameter("partCode");
			log.info("加好友partCode:" + partCode);
			log.info("加好友信息...");
			Boolean flag = chatService.joinParty(partCode);
			log.info("加好友信息end");
			return ApiResponse.sucess().putData(flag);
		} catch (SysException e) {
			ExceptionEnum xe = e.getExceptionEnum();
			log.info("加好友信息异常:" + xe.getCode() + ":" + xe.getMsg());
			return ApiResponse.fail().putMsg(xe.getCode() + ":" + xe.getMsg());
		}
	}
	
	

}
