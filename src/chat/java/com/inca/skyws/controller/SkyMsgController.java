package com.inca.skyws.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inca.skyws.bean.User;
import com.inca.skyws.exception.ExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.ApiResponse;
import com.inca.skyws.service.MessageService;
import com.inca.skyws.service.UserService;
import com.inca.skyws.socket.InMessage;
import com.inca.skyws.socket.WebSocketService;

@Controller
public class SkyMsgController {

	private static final Logger log = LoggerFactory.getLogger(SkyMsgController.class);
	@Autowired
	WebSocketService socketService;
	@Autowired
	UserService userService;
	@Autowired
	MessageService msgService;

	@MessageMapping("/singleChat")
	public void singleChat(InMessage im) {
		try {
			String toCode = im.getTo();
			User user = userService.findUserByUsercode(im.getFrom());
			im.setFromId(user.getId());
			im.setFromName(user.getUsername());
			if (StringUtils.startsWithIgnoreCase(toCode, "QL")) {
				im.setType(2);
				
			} else if (StringUtils.startsWithIgnoreCase(toCode, "YH")) {
				im.setType(1);
			}
			
			socketService.sendTalkMessage("/talk/" + toCode, im);
		} catch (InterruptedException e) {
			log.info("单聊异常:" + e.getMessage(), e);
		}
	}
	
	
	@RequestMapping("/latestrecord")
	@ResponseBody
	public ApiResponse latestrecord(HttpServletRequest req) {
		try {
			String partCode = req.getParameter("partCode");
			log.info("查询用户:" + partCode + "的聊天记录");
			return ApiResponse.sucess().putData(msgService.getTheParterMessages(partCode));
		} catch (SysException e) {
			ExceptionEnum xe = e.getExceptionEnum();
			log.info("查询用户异常:" + xe.getCode() + ":" + xe.getMsg());
			return ApiResponse.fail().putMsg(xe.getCode() + ":" + xe.getMsg());
		} catch (Exception e) {
			return ApiResponse.fail().putMsg(e.getMessage());
		}
	}
}
