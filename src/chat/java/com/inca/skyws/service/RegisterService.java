package com.inca.skyws.service;

import com.inca.skyws.bean.User;
import com.inca.skyws.exception.SysException;

public interface RegisterService {
	public static final String NAME = "registerService";

	void process(User user) throws SysException, Exception;
}
