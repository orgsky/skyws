package com.inca.skyws.service;

import java.util.List;

import com.inca.skyws.bean.Group;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.GroupInfo;
import com.inca.skyws.model.UserInfo;

public interface GroupService {
	public static final String NAME = "groupService";

	public List<GroupInfo> findAllMyGroups() throws SysException, Exception;

	public GroupInfo save(Group group) throws SysException;

	List<UserInfo> findAllGroupsByKeyword(String keyword) throws Exception;
}
