package com.inca.skyws.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrPageCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	private static final Logger log = LoggerFactory.getLogger(ErrPageCustomizer.class);
	public void customize(ConfigurableServletWebServerFactory server) {
		log.info("自定义错误页面");
		server.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
	}

}
