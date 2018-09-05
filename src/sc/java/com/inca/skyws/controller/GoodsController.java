package com.inca.skyws.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);


	@RequestMapping("/")
	public String home(Model model, HttpServletRequest req) {
		log.info("访问商城主页....");
		return "goods";
	}

	

}
