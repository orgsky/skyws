package com.inca.skyws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inca.skyws.bean.User;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.UserInfo;
import com.inca.skyws.repository.UserDao;
import com.inca.skyws.system.LoginUser;
import com.inca.skyws.tools.FilePathUtils;
import com.inca.skyws.tools.SelectHelper;

@Transactional
@Service(UserService.NAME)
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserDao userDao;
	@Autowired
	FilePathUtils pathUtils;
	@Autowired
	SelectHelper select;

	@Override
	public User findUserByAccount(String account) {
		return userDao.findOneByUsercodeOrPhoneOrEmailOrUsername(account, account, account, account);
	}

	@Override
	public User saveUser(ModelAndView model, MultipartFile file, HttpServletRequest req, User user)
			throws SysException {
		log.info("更新用户信息...");
		User savedUser = userDao.findById(user.getId()).get();
		String username = user.getUsername();
		String fileName = pathUtils.saveFile(file, username);
		savedUser.setHeadimg("/" + fileName);
		savedUser.setNickname(user.getNickname());
		savedUser.setUsername(username);
		savedUser.setIdcard(user.getIdcard());
		savedUser.setBirthdate(new Date());
		savedUser.setSex(user.getSex());
		savedUser.setPhone(user.getPhone());
		savedUser.setEmail(user.getEmail());
		savedUser.setProfession(user.getProfession());
		savedUser.setQq(user.getQq());
		savedUser.setAddress(user.getAddress());
		savedUser.setEducation(user.getEducation());
		savedUser = userDao.save(savedUser);
		log.info("更新用户信息end");
		return savedUser;
	}

	@Override
	public List<UserInfo> findAllUsersByKeyword(String keyword) throws Exception {
		String sql = "select a.id,a.usercode,a.username from sys_user a where a.id not in (select b.id from sys_relation b where b.owner_id=1) and a.id<>" + LoginUser.getLoginUser().getId()
				+ " and (a.usercode like '%" + keyword + "%' or a.username like '%" + keyword + "%' or a.email like '%"
				+ keyword + "%' or a.phone like '%" + keyword + "%') order by a.id";
		List<Map<String, Object>> friendsIt = select.doQuery(sql);
		ObjectMapper mapper = new ObjectMapper();
		String reqBody = mapper.writeValueAsString(friendsIt);
		List<UserInfo> users = mapper.readValue(reqBody, new TypeReference<List<User>>() {
		});
		return users;
	}

	@Override
	public List<UserInfo> findAllMyFriends() throws Exception {
		StringBuffer sql=new StringBuffer("select a.friend_id id,b.username,b.usercode from sys_relation a ");
		sql.append("left join sys_user b on b.id=a.friend_id ");
		sql.append("where  a.relation_type= 1 ");
		sql.append("and a.owner_id="+LoginUser.getLoginUser().getId()+"  order by a.id ");
		List<Map<String, Object>> friendsIt = select.doQuery(sql.toString());
		ObjectMapper mapper = new ObjectMapper();
		String reqBody = mapper.writeValueAsString(friendsIt);
		List<User> friends = mapper.readValue(reqBody, new TypeReference<List<User>>() {
		});
		List<UserInfo> userInfos=new ArrayList<UserInfo>();
		for (User f : friends) {
			UserInfo uif=new UserInfo();
			BeanUtils.copyProperties(f, uif);
			userInfos.add(uif);
		}
		return userInfos;
	}

	@Override
	public User findUserByUsercode(String usercode) {
		return userDao.findOneByUsercode(usercode);
	}

}
