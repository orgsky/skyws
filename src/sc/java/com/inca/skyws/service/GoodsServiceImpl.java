package com.inca.skyws.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.inca.skyws.bean.Goods;

@Transactional
@Service(GoodsService.NAME)
public class GoodsServiceImpl implements GoodsService {
	private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);

	@Override
	public Goods save(Goods goods) {
		log.info("商品信息:" + goods);
		return goods;
	}

}
