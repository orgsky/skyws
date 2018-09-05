package com.inca.skyws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inca.skyws.bean.RelationShip;
import com.inca.skyws.bean.User;

public interface RelationShipDao extends JpaRepository<RelationShip, Integer> {

	List<RelationShip> findAllByOwnerAndFriend(User curUser, User friend);

}
