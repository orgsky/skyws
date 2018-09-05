package com.inca.skyws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inca.skyws.bean.Group;
import com.inca.skyws.bean.RelationShip;
import com.inca.skyws.bean.User;
import com.inca.skyws.exception.ChatExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.repository.GroupDao;
import com.inca.skyws.repository.RelationShipDao;
import com.inca.skyws.repository.UserDao;
import com.inca.skyws.socket.InMessage;
import com.inca.skyws.socket.WebSocketService;
import com.inca.skyws.system.LoginUser;
import com.inca.skyws.tools.SelectHelper;

@Transactional
@Service(ChatService.NAME)
public class ChatServiceImpl implements ChatService {
	private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
	@Autowired
	GroupDao groupDao;
	@Autowired
	SelectHelper select;
	@Autowired
	UserDao userDao;
	@Autowired
	RelationShipDao shipDao;
	@Autowired
	WebSocketService socketService;

	@Override
	public Boolean joinParty(String partCode) throws SysException {
		log.info("加好友参数:" + partCode);
		InMessage in = new InMessage();
		in.setFrom(LoginUser.getLoginUser().getUsercode());
		in.setFromId(LoginUser.getLoginUser().getId());
		in.setFromName(LoginUser.getLoginUser().getUsername());
		User curUser = userDao.findOneByUsercode(LoginUser.getLoginUser().getUsercode());
		try {
			if (StringUtils.startsWithIgnoreCase(partCode, "QL")) {
				// 找到群信息
				Group group = groupDao.findOneByGroupCode(partCode);
				// 群用友人
				User owner = group.getOwner();
				in.setType(5);
				// 发信息给他
				socketService.sendTalkMessage("/talk/" + owner.getUsercode(), in);
				// 群里增加人员
				List<User> members = group.getMembers();
				if (members == null) {
					members = new ArrayList<User>();
				}
				members.add(curUser);
				groupDao.save(group);
				// 我的关系里,增加此群
				RelationShip ship = new RelationShip();
				ship.setOwner(curUser);
				ship.setGroup(group);
				ship.setRelationType(2);//群友
				ship.setIntimacy(1);//好友
				ship.setBuildTime(new Date());
				shipDao.save(ship);
			} else if (StringUtils.startsWithIgnoreCase(partCode, "YH")) {
				// 找到此人
				User friend = userDao.findOneByUsercode(partCode);
				in.setType(3);
				socketService.sendTalkMessage("/talk/" + partCode, in);
				// 建立关系
				RelationShip ship = new RelationShip();
				ship.setOwner(curUser);
				ship.setFriend(friend);
				ship.setRelationType(1);//好友
				ship.setIntimacy(1);//好友
				ship.setBuildTime(new Date());
				shipDao.save(ship);
				
				ship = new RelationShip();
				ship.setOwner(friend);
				ship.setFriend(curUser);
				ship.setRelationType(1);//好友
				ship.setIntimacy(1);//好友
				ship.setBuildTime(new Date());
				shipDao.save(ship);
			}
			return true;
		} catch (InterruptedException e) {
			throw new SysException(ChatExceptionEnum.CHAT_SEND_FAIL);
		}
	}

}
