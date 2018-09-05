package com.inca.skyws.service;

import com.inca.skyws.exception.SysException;

public interface ChatService {
	public static final String NAME = "chatService";

	public Boolean joinParty(String partCode) throws SysException;
}
