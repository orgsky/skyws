package com.inca.editor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inca.editor.ActionEnter;

/**
 * Created by ldb on 2017/4/9.
 */
@Controller
public class UEditorController {
	private static final Logger log = LoggerFactory.getLogger(UEditorController.class);

	@RequestMapping("/")
	private String showPage() {
		return "index";
	}

	@RequestMapping("/ueditor")
	public void config(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		try {
			log.info("百度富文本编辑器请求资源：" + request.getParameter("action"));
			String exec = new ActionEnter(request).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.info("百度富文本编辑器请求资源，异常：" + e.getMessage(), e);
		}
	}

}
