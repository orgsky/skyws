package com.inca.skyws.tools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.inca.skyws.exception.CommonExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.service.UserServiceImpl;

@Configuration
public class FilePathUtils {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	@Value("${web.unx.skyfiles}")
	private String unxPath;
	@Value("${web.win.skyfiles}")
	private String winPath;

	public String getFileStore() throws SysException {
		String finalPath =((System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) ? winPath : unxPath)+sdf.format(new Date()) + File.separator;
		File file = new File(finalPath);
		if (!file.exists()) {
			boolean createFlag = file.mkdirs();
			if (!createFlag) {
				throw new SysException(CommonExceptionEnum.CM_FAIL);
			}
		}
		return finalPath;
	}
	
	public String saveFile(MultipartFile file, String username) throws SysException {
		try {
			log.info("保存文件...");
			String path = getFileStore();
			String oriname = file.getOriginalFilename();
			log.info(username + "上传" + oriname);
			String sufix = oriname.substring(oriname.lastIndexOf('.'));
			log.info("sufix=" + sufix);
			String newName = UUID.randomUUID() + sufix;
			log.info("新文件名:" + newName);
			file.transferTo(new File(path + newName));
			log.info("保存文件end");
			return sdf.format(new Date())+"/"+newName;
		} catch (IllegalStateException | IOException e) {
			throw new SysException(CommonExceptionEnum.CM_FAIL);
		}
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("102400kb");
		factory.setMaxRequestSize("1024000kb");
		return factory.createMultipartConfig();

	}
}
