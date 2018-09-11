package com.inca.skyws.socket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.inca.skyws.bean.Group;
import com.inca.skyws.bean.Message;
import com.inca.skyws.bean.User;
import com.inca.skyws.repository.GroupDao;
import com.inca.skyws.repository.MessageDao;
import com.inca.skyws.repository.UserDao;
import com.inca.skyws.tools.UpdateHelper;

@Service
public class WebSocketService {

	@Autowired
	MessageDao msgDao;
	@Autowired
	UserDao userDao;
	@Autowired
	GroupDao groupDao;
	@Autowired
	UpdateHelper update;

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
		msg.setFromTime(new Date());
		msg.setContent(im.getContent());
		if (type != null) {
			if (type == 1) {
				msg.setTo(userDao.findOneByUsercode(im.getTo()));
				msg.setChatType(type);// 单聊
			} else if (type == 2) {
				/*
				 * User user = new User(); Group group =
				 * groupDao.findOneByGroupCode(im.getTo()); user.setId(group.getId());
				 * msg.setTo(user);
				 */
				msg.setChatType(type);// 单聊
			}
		}
		msg.setMsgType(1);// 文字
		msg.setToTime(new Date());
		Message save = msgDao.save(msg);
		Integer id = save.getId();
		try {
			if (type == 2){
				Group group = groupDao.findOneByGroupCode(im.getTo());
				update.doUpdate("update sys_message set to_id="+group.getId()+" where id="+id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
