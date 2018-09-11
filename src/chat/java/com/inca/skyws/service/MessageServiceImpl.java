package com.inca.skyws.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inca.skyws.bean.Group;
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
	public List<MessageInfo> getTheParterMessages(String partCode) throws Exception {
		List<Message> msgs = new ArrayList<Message>();
		List<MessageInfo> msgsInfo=new ArrayList<MessageInfo>();
		if (StringUtils.startsWithIgnoreCase(partCode, "QL")) {
			Group group = groupDao.findOneByGroupCode(partCode);
			List<Integer> msgIds = new ArrayList<Integer>();
			List<Map<String, Object>> maps = select.doQuery("select a.* from sys_message a where a.to_id="+group.getId()+" and a.chat_type=2 order by a.id");
			for (Map<String, Object> map : maps) {
				MessageInfo info = new MessageInfo();
				BeanUtils.copyProperties(map, info);
				Integer fromId = (Integer) map.get("from_id");
				Integer toId = (Integer) map.get("to_id");

				User from = userDao.findById(fromId).get();
				UserInfo fromInfo = new UserInfo();
				BeanUtils.copyProperties(from, fromInfo);
				info.setFrom(fromInfo);

				UserInfo toInfo = new UserInfo();

				toInfo.setId(group.getId());
				toInfo.setUsercode(partCode);
				toInfo.setUsername(group.getGroupName());
				toInfo.setType(2);
				info.setTo(toInfo);
				Date fromTime = (Date) map.get("from_time");
				String fromTimeS = sdf.format(fromTime);
				info.setFromTime(fromTimeS);
				Date toTime = (Date) map.get("to_time");
				String toTimeS = sdf.format(toTime);
				info.setToTime(toTimeS);
				info.setChatType(2);
				info.setMsgType(1);
				info.setContent((String) map.get("content"));
				msgsInfo.add(info);
			}
		} else if (StringUtils.startsWithIgnoreCase(partCode, "YH")) {
			User friend = userDao.findOneByUsercode(partCode);
			String sql = "select a.* from sys_message a where ((a.to_id="+LoginUser.getLoginUser().getId()+" and a.from_id="+friend.getId()+") or (a.to_id="+friend.getId()+" and a.from_id="+LoginUser.getLoginUser().getId()+")) and a.chat_type=1 order by a.id";
			List<Map<String, Object>> maps = select.doQuery(sql);
			User mine = userDao.getOne(LoginUser.getLoginUser().getId());
			log.info("mine:"+mine.getId());
			for (Map<String, Object> map : maps) {
				MessageInfo info = new MessageInfo();
				BeanUtils.copyProperties(map, info);
				Integer fromId = (Integer) map.get("from_id");
				Integer toId = (Integer) map.get("to_id");

				User from = userDao.findById(fromId).get();
				UserInfo fromInfo = new UserInfo();
				BeanUtils.copyProperties(from, fromInfo);
				info.setFrom(fromInfo);

				UserInfo toInfo = new UserInfo();

				toInfo.setId(friend.getId());
				toInfo.setUsercode(partCode);
				toInfo.setUsername(friend.getUsername());
				toInfo.setType(2);
				info.setTo(toInfo);
				Date fromTime = (Date) map.get("from_time");
				String fromTimeS = sdf.format(fromTime);
				info.setFromTime(fromTimeS);
				Date toTime = (Date) map.get("to_time");
				String toTimeS = sdf.format(toTime);
				info.setToTime(toTimeS);
				info.setChatType(2);
				info.setMsgType(1);
				info.setContent((String) map.get("content"));
				msgsInfo.add(info);
			}
		}
		return msgsInfo;
	}

}
