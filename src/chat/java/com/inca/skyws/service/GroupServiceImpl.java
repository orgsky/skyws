package com.inca.skyws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inca.skyws.bean.Group;
import com.inca.skyws.bean.User;
import com.inca.skyws.exception.ChatExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.GroupInfo;
import com.inca.skyws.model.UserInfo;
import com.inca.skyws.repository.GroupDao;
import com.inca.skyws.repository.RelationShipDao;
import com.inca.skyws.repository.UserDao;
import com.inca.skyws.system.LoginUser;
import com.inca.skyws.tools.SelectHelper;

@Transactional
@Service(GroupService.NAME)
public class GroupServiceImpl implements GroupService {
	private static final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);
	@Autowired
	GroupDao groupDao;
	@Autowired
	SelectHelper select;
	@Autowired
	UserDao userDao;
	@Autowired
	RelationShipDao shipDao;
	@Autowired
	CodeRuleService ruleService;

	@Override
	public List<GroupInfo> findAllMyGroups() throws Exception {
		List<GroupInfo> groupInfos = new ArrayList<GroupInfo>();
		User owner = userDao.findById(LoginUser.getLoginUser().getId()).get();
		String sql = "select distinct a.group_id from sys_relation a  where a.owner_id=" + owner.getId()
				+ " and a.relation_type=2 union select b.id group_id from sys_group b where b. owner_id="+ owner.getId();
		List<Map<String, Object>> maps = select.doQuery(sql);
		if (maps == null || maps.size() <= 0) {
			return groupInfos;
		}
		List<Integer> groupIds = new ArrayList<Integer>();
		for (Map<String, Object> map : maps) {
			Integer groupId = (Integer) map.get("group_id");
			groupIds.add(groupId);
		}
		List<Group> groups = groupDao.findAllById(groupIds);
		for (Group group : groups) {
			UserInfo ownerInfo = new UserInfo();
			BeanUtils.copyProperties(owner, ownerInfo);
			GroupInfo groupInfo = new GroupInfo();
			BeanUtils.copyProperties(group, groupInfo);
			groupInfo.setOwner(ownerInfo);
			groupInfos.add(groupInfo);
		}
		return groupInfos;
	}

	@Override
	public GroupInfo save(Group group) throws SysException {
		log.info("创建用户群...");
		Integer id = group.getOwner().getId();
		if (id == null) {
			throw new SysException(ChatExceptionEnum.CHAT_NO_OWNER);
		}
		Integer groupId = group.getId();
		User owner = userDao.findById(id).get();
		Group savedGroup = null;
		if (groupId != null) {
			// 修改信息
			savedGroup = groupDao.getOne(groupId);
			savedGroup.setSequence(group.getSequence());
			savedGroup.setGroupName(group.getGroupName());
		}else {
			String code = ruleService.genCode(Group.class);
			group.setGroupCode(code);
			group.setOwner(owner);
			savedGroup = groupDao.save(group);
		}
		// 实体ObjectMapper解析报错,转轻量级bean
		UserInfo ownerInfo=new UserInfo();
		BeanUtils.copyProperties(owner, ownerInfo);
		GroupInfo groupInfo = new GroupInfo();
		BeanUtils.copyProperties(savedGroup, groupInfo);
		groupInfo.setOwner(ownerInfo);
		log.info("创建用户群end");
		return groupInfo;
	}
	
	
	@Override
	public List<UserInfo> findAllGroupsByKeyword(String keyword) throws Exception {
		String sql = "select a.id,a. group_code  usercode,a. group_name username from sys_group a where a.owner_id<>" + LoginUser.getLoginUser().getId()
				+ " and (a.group_code like '%" + keyword + "%' or a.group_name like '%" + keyword +"%') order by a.id";
		List<Map<String, Object>> friendsIt = select.doQuery(sql);
		ObjectMapper mapper = new ObjectMapper();
		String reqBody = mapper.writeValueAsString(friendsIt);
		List<UserInfo> users = mapper.readValue(reqBody, new TypeReference<List<User>>() {
		});
		return users;
	}

}
