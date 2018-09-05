package com.inca.skyws.service;

import com.inca.skyws.bean.Goods;

public interface GoodsService {
	public static final String NAME = "goodsService";

	Goods save(Goods goods);

}
