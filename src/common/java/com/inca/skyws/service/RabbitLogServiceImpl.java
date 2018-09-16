package com.inca.skyws.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inca.skyws.bean.RabbitLog;
import com.inca.skyws.rabbit.RabbitMessage;
import com.inca.skyws.repository.RabbitLogDao;

@Transactional
@Service(RabbitLogService.NAME)
public class RabbitLogServiceImpl implements RabbitLogService {
	@Autowired
	RabbitLogDao expLogDao;

	@Override
	public RabbitLog saveRabbitLog(RabbitMessage msg, String exp) {
		RabbitLog log = new RabbitLog();
		log.setClzName(msg.getClass().getName());
		log.setExpTime(new Date());
		log.setMsg(exp);
		log.setThreadId(Thread.currentThread().getId() + "");
		log.setComeFrom(msg.getComeFrom());
		log.setDeliveryStatus(0);// 存库未投递
		return expLogDao.save(log);
	}

	private RabbitLog updateRabbitLog(RabbitLog log, Integer deliveryStatus) {
		log.setDeliveryStatus(deliveryStatus);
		return expLogDao.save(log);
	}

	@Override
	public RabbitLog getRabbitLog(Integer logId) {
		return expLogDao.getOne(logId);
	}

	@Override
	public RabbitLog saveRabbitLog(RabbitMessage msg) {
		return saveRabbitLog(msg, null);
	}

	@Override
	public boolean deleteRabbitLog(Integer logId) {
		try {
			expLogDao.delete(expLogDao.getOne(logId));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void savedNotSend(RabbitLog log) {
		updateRabbitLog(log, 0);
	}

	@Override
	public void sendNotAck(RabbitLog log) {
		updateRabbitLog(log, 1);
	}

	@Override
	public void sendNotArrive(RabbitLog log) {
		updateRabbitLog(log, 2);
	}

	@Override
	public void acceptNotAck(RabbitLog log) {
		updateRabbitLog(log, 3);
	}

	@Override
	public void ackAndFinish(RabbitLog log) {
		updateRabbitLog(log, 4);
		deleteRabbitLog(log.getId());
	}

	@Override
	public void acceptNotFinish(RabbitLog log) {
		updateRabbitLog(log, 5);
	}

}
