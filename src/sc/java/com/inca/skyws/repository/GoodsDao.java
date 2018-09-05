package com.inca.skyws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inca.skyws.bean.Goods;

public interface GoodsDao extends JpaRepository<Goods, Integer> {

}
