package com.inca.skyws.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inca.skyws.bean.Group;
import com.inca.skyws.exception.ExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.ApiResponse;
import com.inca.skyws.model.GroupInfo;
import com.inca.skyws.service.GroupService;

@Controller
@RequestMapping("/group")
public class SkyGroupController {
	private static final Logger log = LoggerFactory.getLogger(SkyGroupController.class);
	@Autowired
	GroupService groupService;

	@RequestMapping("/saveGroup")
	@ResponseBody
	public ApiResponse saveGroup(Group group) {
		try {
			log.info("更新群信息...");
			GroupInfo groupInfo = groupService.save(group);
			log.info("更新群信息end");
			return ApiResponse.sucess().putData(groupInfo);
		} catch (SysException e) {
			ExceptionEnum xe = e.getExceptionEnum();
			log.info("更新群信息异常:" + xe.getCode() + ":" + xe.getMsg());
			return ApiResponse.fail().putMsg(xe.getCode() + ":" + xe.getMsg());
		}
	}

	@RequestMapping("/mygroup")
	@ResponseBody
	public ApiResponse mygroup(ModelAndView model, HttpServletRequest req) {
		try {
			log.info("查询我的群信息...");
			List<GroupInfo> groups = groupService.findAllMyGroups();
			log.info("查询我的群信息end");
			return ApiResponse.sucess().putData(groups);
		} catch (SysException e) {
			ExceptionEnum xe = e.getExceptionEnum();
			log.info("查询我的群信息异常:" + xe.getCode() + ":" + xe.getMsg());
			return ApiResponse.fail().putMsg(xe.getCode() + ":" + xe.getMsg());
		} catch (Exception e) {
			log.info("查询我的群信息异常:" + e.getMessage(), e);
			return ApiResponse.fail().putMsg(e.getMessage());
		}
	}

}
