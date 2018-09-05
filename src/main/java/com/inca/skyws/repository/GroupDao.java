package com.inca.skyws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inca.skyws.bean.Group;
import com.inca.skyws.bean.User;

public interface GroupDao extends JpaRepository<Group, Integer> {

	List<Group> findAllByOwnerIn(List<User> owners) throws Exception;

	Group findOneByGroupCode(String groupCode);

}
