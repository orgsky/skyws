package com.inca.skyws.socket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.inca.skyws.bean.Message;
import com.inca.skyws.repository.GroupDao;
import com.inca.skyws.repository.MessageDao;
import com.inca.skyws.repository.UserDao;

@Service
public class WebSocketService {

	@Autowired
	MessageDao msgDao;
	@Autowired
	UserDao userDao;
	@Autowired
	GroupDao groupDao;

	@Autowired
	private SimpMessagingTemplate template;

	public void sendTalkMessage(String destination, InMessage im) throws InterruptedException {
		OutMessage om = new OutMessage(im.getContent());
		om.setFrom(im.getFrom());
		om.setFromId(im.getFromId());
		om.setFromName(im.getFromName());
		Integer type = im.getType();
		om.setType(type);
		
		om.setTo(im.getTo());
		om.setToId(im.getToId());
		om.setToName(im.getToName());
		om.setToImg(im.getToImg());
		template.convertAndSend(destination, om);

		// 保存消息
		Message msg = new Message();
		msg.setFrom(userDao.getOne(im.getFromId()));
		msg.setTo(userDao.findOneByUsercode(im.getTo()));
		msg.setFromTime(new Date());
		msg.setContent(im.getContent());
		if (type != null) {
			if (type == 1 || type == 2) {
				msg.setChatType(1);// 单聊
			}
		}
		msg.setMsgType(1);// 文字
		msg.setToTime(new Date());
		msgDao.save(msg);
	}
}
