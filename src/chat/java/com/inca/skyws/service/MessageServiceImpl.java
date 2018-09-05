package com.inca.skyws.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inca.skyws.bean.Message;
import com.inca.skyws.bean.User;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.MessageInfo;
import com.inca.skyws.model.UserInfo;
import com.inca.skyws.repository.GroupDao;
import com.inca.skyws.repository.MessageDao;
import com.inca.skyws.repository.UserDao;
import com.inca.skyws.system.LoginUser;
import com.inca.skyws.tools.SelectHelper;

@Transactional
@Service(MessageService.NAME)
public class MessageServiceImpl implements MessageService {
	private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	private static SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired
	GroupDao groupDao;
	@Autowired
	SelectHelper select;
	@Autowired
	UserDao userDao;
	@Autowired
	MessageDao msgDao;

	@Override
	public List<MessageInfo> getTheParterMessages(String partCode) throws SysException {
		User friend = userDao.findOneByUsercode(partCode);
		User mine = userDao.getOne(LoginUser.getLoginUser().getId());
		List<Message> msgs = new ArrayList<Message>();
		if (StringUtils.startsWithIgnoreCase(partCode, "QL")) {
			msgs.addAll(msgDao.findAllByChatTypeAndToAndFrom(2, mine, friend));
		} else if (StringUtils.startsWithIgnoreCase(partCode, "YH")) {
			msgs.addAll(msgDao.findAllByChatTypeAndToAndFrom(1, mine, friend));
		}
		List<MessageInfo> msgsInfo=new ArrayList<MessageInfo>();
		for (Message msg : msgs) {
			MessageInfo info=new MessageInfo();
			User from = msg.getFrom();
			UserInfo fromInfo=new UserInfo();
			BeanUtils.copyProperties(from, fromInfo);
			info.setFrom(fromInfo);
			
			User to = msg.getTo();
			UserInfo toInfo=new UserInfo();
			BeanUtils.copyProperties(to, toInfo);
			info.setFrom(toInfo);
			Date fromTime = msg.getFromTime();
			String fromTimeS = sdf.format(fromTime);
			info.setFromTime(fromTimeS);
			Date toTime = msg.getToTime();
			String toTimeS = sdf.format(toTime);
			info.setToTime(toTimeS);
			info.setChatType(msg.getChatType());
			info.setMsgType(msg.getMsgType());
			info.setContent(msg.getContent());
			msgsInfo.add(info);
		}
		return msgsInfo;
	}

}
