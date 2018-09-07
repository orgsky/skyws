package com.inca.skyws.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inca.skyws.model.GoodsInfo;
import com.inca.skyws.model.UserInfo;
import com.inca.skyws.system.LoginUser;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

	@RequestMapping("/mygoods")
	public String home(Model model, HttpServletRequest req) {
		String keycode = req.getParameter("keycode");
		log.info("keycode=" + keycode);
		String keyname = req.getParameter("keyname");
		log.info("keyname=" + keyname);
		List<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		LoginUser loginUser = LoginUser.getLoginUser();
		for (int row = 0; row < 8; row++) {
			GoodsInfo goods = new GoodsInfo();
			goods.setId(row);
			goods.setSpec("1台/箱");
			goods.setUnit("台");
			goods.setClickNum("20" + row);
			goods.setMajPic("/static/img/tv.jpg");
			goods.setPic("/static/img/tv.jpg");
			goods.setGoodsCode("60SU470A");
			goods.setGoodsName("夏普 (SHARP)LCD-70SU575A");
			goods.setWeight(new BigDecimal("80"));
			goods.setPrice(new BigDecimal("3999.00"));
			goods.setStock(99 + row);
			UserInfo user = new UserInfo();
			user.setId(loginUser.getId());
			user.setUsercode(loginUser.getUsercode());
			user.setUsername(loginUser.getUsername());
			goodsList.add(goods);
		}
		model.addAttribute("goodsList", goodsList);
		return "mygoodslist";
	}

	@RequestMapping("/goodsinfo")
	public String goodsInfo(Model model, HttpServletRequest req) {
		String id = req.getParameter("id");
		log.info("id=" + id);
		LoginUser loginUser = LoginUser.getLoginUser();
		GoodsInfo goods = new GoodsInfo();
		goods.setId(Integer.valueOf(id));
		goods.setSpec("1台/箱");
		goods.setUnit("台");
		goods.setClickNum("20" + Integer.valueOf(id));
		goods.setMajPic("/static/img/tv.jpg");
		goods.setPic("/static/img/tv.jpg");
		goods.setGoodsCode("60SU470A");
		goods.setGoodsName("夏普 (SHARP)LCD-70SU575A");
		goods.setWeight(new BigDecimal("80"));
		goods.setPrice(new BigDecimal("3999.00"));
		goods.setStock(99 + Integer.valueOf(id));
		UserInfo user = new UserInfo();
		user.setId(loginUser.getId());
		user.setUsercode(loginUser.getUsercode());
		user.setUsername(loginUser.getUsername());
		model.addAttribute("goods", goods);
		return "goodsinfo";
	}

}
