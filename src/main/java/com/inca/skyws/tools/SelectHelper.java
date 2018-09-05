package com.inca.skyws.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SelectHelper {
	final Log log = LogFactory.getLog(getClass());
	
	public SelectHelper(){
		super();
	}
	
	@Autowired
	JdbcTemplate jdbc;
	
	/**
	 * 查询sql
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> doQuery(String sql) throws Exception{
		log.info("SelectHelper.sql:"+sql);
		List<Map<String, Object>> records = jdbc.queryForList(sql);
		return records;
	}
	
	/**
	 * 查询sql
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> doQueryOne(String sql) throws Exception{
		log.info("SelectHelper.sql:"+sql);
		List<Map<String, Object>> records = jdbc.queryForList(sql);
		if(records!=null&&records.size()>0){
			return records.get(0);
		}
		return null;
	}
	
	/**
	 * 查询sql
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> doQuery(String sql,Object[] args) throws Exception{
		log.info("SelectHelper.sql:"+sql);
		List<Map<String, Object>> records = jdbc.queryForList(sql, args);
		return records;
	}
	
	/**
	 * 查询sql
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> doQueryOne(String sql,Object[] args) throws Exception{
		log.info("SelectHelper.sql:"+sql);
		List<Map<String, Object>> records = jdbc.queryForList(sql, args);
		if(records!=null&&records.size()>0&&!records.isEmpty()){
			return records.get(0);
		}
		return null;
	}

	/**
	 * 构造索引
	 * @param records
	 * @param indexColumnList
	 * @return
	 */
	public Map<String,List<Integer>> buildIndex(List<Map<String,Object>> records,List<String> indexColumnList){
		Map<String,List<Integer>> map = new HashMap<String, List<Integer>>();
		if(records==null) return map;
		
		for(int row = 0;row<records.size();row++){
			Map<String,Object> record = records.get(row);
			StringBuffer keyBuffer = new StringBuffer();
			for(String column:indexColumnList){
				Object objvalue = record.get(column);
				String value = "";
				if(objvalue!=null){
					value = objvalue.toString();
				}
				keyBuffer.append(value);
				keyBuffer.append(":");
			}
			String key = keyBuffer.toString();
			List<Integer> rowList = map.get(key);
			if(rowList==null){
				rowList = new ArrayList<>();
			}
			rowList.add(row);
			map.put(key, rowList);
		}
		return map;
	}
	
	/**
	 * 查询索引
	 * @param indexMap
	 * @param indexValueList
	 * @return
	 */
	public List<Integer> getIndexValue(Map<String,List<Integer>> indexMap,List<Object> indexValueList){
		StringBuffer keyBuffer = new StringBuffer();
		for(Object indexValue:indexValueList){
			String value = "";
			if(indexValue!=null){
				value = indexValue.toString();
			}
			keyBuffer.append(value);
			keyBuffer.append(":");
		}
		String key = keyBuffer.toString();
		return indexMap.get(key);
	}
	
	
	public Object getObjectByArray(Object objects[], int index) {
		Object obj = objects[index];
		return obj;
	}
	
	
	

}
