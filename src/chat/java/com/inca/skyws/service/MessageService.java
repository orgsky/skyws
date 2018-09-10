package com.inca.skyws.service;

import java.util.List;

import com.inca.skyws.exception.SysException;
import com.inca.skyws.model.MessageInfo;

public interface MessageService {
	public static final String NAME = "messageService";

	public List<MessageInfo> getTheParterMessages(String partCode) throws SysException, Exception;
}
