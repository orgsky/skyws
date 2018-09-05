package com.inca.skyws.tools;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateHelper {

	final Log log = LogFactory.getLog(UpdateHelper.class);

	public UpdateHelper() {
		super();
	}

	@Autowired
	JdbcTemplate jdbc;

	public void doUpdate(String sql) throws Exception {
		doUpdate(sql);
	}

	public void doUpdate(String sql, Object[] args) throws Exception {
		doUpdate(sql, args);
	}

	public void doUpdate(String sql, List<Object[]> args) throws Exception {
		doUpdate(sql, args);
	}

}
