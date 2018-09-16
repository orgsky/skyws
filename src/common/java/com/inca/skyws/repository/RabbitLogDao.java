package com.inca.skyws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inca.skyws.bean.RabbitLog;

public interface RabbitLogDao extends JpaRepository<RabbitLog, Integer> {

}