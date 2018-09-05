package com.inca.skyws.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.inca.skyws.system.LoginUser;

@Component
public class InsertHelper {

	final Log log = LogFactory.getLog(getClass());

	@Autowired
	JdbcTemplate jdbc;

	public List<Integer> doInsert(String tableName, String seqName, List<Map<String, Object>> args) throws Exception {
		log.info("InsertHelper.tableName:" + tableName);
		List<Integer> idList = new ArrayList<Integer>();

		String insertSql = null;// insert语句
		List<Object[]> insertArgs = new ArrayList<>();
		List<String> insertColumnList = new ArrayList<>();// insert字段
		HashMap<String, Object> otherValueMap = new HashMap<>();// 产品/平台字段对应值
		for (Map<String, Object> map : args) {

			if (insertSql == null) {// 构造insert语句
				LoginUser loginUser = LoginUser.getLoginUser();
				StringBuffer columnBuffer = new StringBuffer();
				StringBuffer valueBuffer = new StringBuffer();
				List<String> columnList = new ArrayList<>();
				// 普通字段
				for (String column : map.keySet()) {
					columnBuffer.append(column);
					columnBuffer.append(",");
					valueBuffer.append("?,");
					columnList.add(column);
					insertColumnList.add(column);
				}
				// 平台级字段
				Map<String, Object> ptColumnMap = new HashMap<>();
				ptColumnMap.put("_create_time", new Date());
				ptColumnMap.put("_last_modify_time", new Date());
				ptColumnMap.put("_deleted", false);
				ptColumnMap.put("_version", 1);
				ptColumnMap.put("sequence", 1);
				if (loginUser != null) {
					ptColumnMap.put("_create_user_id", loginUser.getId());
				}
				for (String column : ptColumnMap.keySet()) {
					if (!columnList.contains(column)) {
						columnBuffer.append(column);
						columnBuffer.append(",");
						valueBuffer.append("?,");
						insertColumnList.add(column);
						otherValueMap.put(column, ptColumnMap.get(column));
					}
				}

				// 主键ID
				if (!insertColumnList.contains("id")) {
					columnBuffer.append("id,");
					valueBuffer.append("?,");
				}

				String column = columnBuffer.substring(0, columnBuffer.length() - 1);
				String value = valueBuffer.substring(0, valueBuffer.length() - 1);
				StringBuffer buffer = new StringBuffer();
				buffer.append("insert into ");
				buffer.append(tableName);
				buffer.append("(");
				buffer.append(column);
				buffer.append(") values (");
				buffer.append(value);
				buffer.append(")");

				insertSql = buffer.toString();
			}

			// 绑定参数
			Object[] columnArgs = null;
			if (insertColumnList.contains("id")) {
				columnArgs = new Object[insertColumnList.size()];
			} else {
				columnArgs = new Object[insertColumnList.size() + 1];
				// id
				Integer id = getSequenceId(seqName);
				idList.add(id);
				columnArgs[insertColumnList.size()] = id;
			}

			for (int i = 0; i < insertColumnList.size(); i++) {
				String column = insertColumnList.get(i);
				Object value = null;
				if (map.containsKey(column)) {
					value = map.get(column);
				} else {
					value = otherValueMap.get(column);
				}
				columnArgs[i] = value;
			}
			insertArgs.add(columnArgs);
		}
		if (insertArgs.size() > 0) {
			log.info("InsertHelper.doInsert");
			log.info("insertSql=" + insertSql);
			log.info(insertArgs.size());
			jdbc.batchUpdate(insertSql, insertArgs);
		}
		log.info("insert success..");
		return idList;
	}

	/**
	 * 取序列号
	 * 
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public Integer getSequenceId(String seqName) throws Exception {
		String seqsql = "select nextval('" + seqName + "')";
		Integer id = jdbc.queryForObject(seqsql, Integer.class);
		return id;
	}

	/**
	 * insert
	 * 
	 * @param tableName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public List<Integer> doInsert(String tableName, List<Map<String, Object>> args) throws Exception {
		log.info("InsertHelper.tableName:" + tableName);

		// 取ID
		String seqName = tableName + "_id_seq";
		return doInsert(tableName, seqName, args);
	}

	/**
	 * insert
	 * 
	 * @param tableName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Integer doInsert(String tableName, Map<String, Object> args) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(args);
		List<Integer> idList = doInsert(tableName, list);
		Integer id = null;
		if (idList != null && idList.size() > 0) {
			id = idList.get(0);
		}
		return id;
	}
}
