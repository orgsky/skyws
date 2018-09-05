package com.inca.skyws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inca.skyws.bean.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findOneByUsercodeOrPhoneOrEmailOrUsername(String account, String phone, String email, String username);
	@Query("select u from com.inca.skyws.bean.User u where concat(u.usercode,u.username,email,phone) like :keyword") 
	List<User> findAllUsersByKeyword(String keyword);
	
	User findOneByUsercode(String usercode);

}
