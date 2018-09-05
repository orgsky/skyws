package com.inca.skyws.bean.listener;

import java.util.Date;

import javax.persistence.PrePersist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.inca.skyws.bean.BaseEntity;
import com.inca.skyws.system.LoginUser;

@Component
public class BaseBeanListener {
	private static final Logger log = LoggerFactory.getLogger(BaseBeanListener.class);

	@PrePersist
	public void touchForCreate(Object obj) throws Exception {
		log.info("当前实体:" + obj);
		if (obj instanceof BaseEntity) {
			BaseEntity target = (BaseEntity) obj;
			Date createTime = target.get_createTime();
			if (createTime == null) {
				target.set_createTime(new Date());
			}
			target.set_lastModifyTime(new Date());
			LoginUser loginUser = LoginUser.getLoginUser();
			if (loginUser != null && loginUser.getId() != null) {
				target.set_createUserId(loginUser.getId());
			}
			if (target.get_version() == null) {
				target.set_version(0);
			}
			if (target.getSequence() == null) {
				target.setSequence(0);
			}
			if (target.get_deleted() == null) {
				target.set_deleted(false);
			}
			if (target.getStatus() == null) {
				target.setStatus(0);
			}
		}
	}
}
