package com.inca.skyws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inca.skyws.bean.Message;
import com.inca.skyws.bean.User;

public interface MessageDao extends JpaRepository<Message, Integer> {

	List<Message> findAllByChatTypeAndToAndFrom(Integer chatType,User to,User from);

}
