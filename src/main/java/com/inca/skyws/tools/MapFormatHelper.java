package com.inca.skyws.tools;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MapFormatHelper {
	
	final Log log = LogFactory.getLog(getClass());
	
	public final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	public final SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final SimpleDateFormat timestampSdf = new SimpleDateFormat("HH:mm:ss");
	
	
	/**
	 * map转jdbc插入数组
	 * @param map map值
	 * @param keys 键值
	 * @param types 类型
	 * @return
	 */
	public Object[] toArray(Map<String,Object> map,String[] keys, Integer[] types){
		if(StringUtils.isEmpty(keys)){
			return null;
		}
		Object[] o = new Object[keys.length];
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			if(StringUtils.isEmpty(key)){
				o[i] = null;
				continue;
			}
			Object value_obj = map.get(key);
			if(StringUtils.isEmpty(value_obj)){
				o[i] = null;
				continue;
			}
			Integer type = types[i];
			if(StringUtils.isEmpty(type)){
				o[i] = null;
				continue;
			}
			if(type == Constants.VARCHAR){
				o[i] = value_obj.toString();
			}else if(type == Constants.INTEGER){
				o[i] = Integer.parseInt(value_obj.toString());
			}else if(type == Constants.BIGDECIMAL){
				o[i] = new BigDecimal(value_obj.toString());
			}else if(type == Constants.DATE){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					o[i] =  sdf.parse(value_obj.toString());
				} catch (Exception e) {
					o[i] = null;
				}
			}else if(type == Constants.TIME){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					o[i] =  sdf.parse(value_obj.toString());
				} catch (Exception e) {
					o[i] = null;
				}
			}else if(type == Constants.TIMESTAMP){
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				try {
					o[i] =  sdf.parse(value_obj.toString());
				} catch (Exception e) {
					o[i] = null;
				}
			}
			map.put(key, o[i]);
		}
		return o;
	}
	/**
	 * 从map中取int
	 * @param map
	 * @param key
	 * @return
	 */
	public Integer getIntValue(Map<String,Object> map,String key){
		Object int_obj = getValue(map, key);
		if(StringUtils.isEmpty(int_obj)){
			return null;
		}
		return Integer.parseInt(int_obj.toString());
	}
	
	/**
	 * 获取字符串类型
	 * @param map
	 * @param key
	 * @return
	 */
	public String getStringValue(Map<String,Object> map,String key){
		Object int_obj = getValue(map, key);
		if(StringUtils.isEmpty(int_obj)){
			return null;
		}
		return int_obj.toString();
	}
	
	/**
	 * 获取BigDecimal类型
	 * @param map
	 * @param key
	 * @return
	 */
	public BigDecimal getBigDecimalValue(Map<String,Object> map,String key){
		Object int_obj = getValue(map, key);
		if(StringUtils.isEmpty(int_obj)){
			return null;
		}
		BigDecimal bd = new BigDecimal(int_obj.toString());
		return bd;
	}
	
	/**
	 * 获取日期格式
	 * @param map
	 * @param key
	 * @param type Constants.DATE、Constants.TIME、Constants.TIMESTAMP
	 * @return
	 */
	public Date getDateValue(Map<String,Object> map,String key,Integer type){
		if(StringUtils.isEmpty(type)){
			return null;
		}
		Object int_obj = getValue(map, key);
		if(StringUtils.isEmpty(int_obj)){
			return null;
		}
		Date date = null;
		if(type == Constants.DATE){
			try {
				date = dateSdf.parse(int_obj.toString());
			} catch (Exception e) {
				log.error(e);
			}
		}else if(type == Constants.TIME){
			try {
				date = timeSdf.parse(int_obj.toString());
			} catch (Exception e) {
				log.error(e);
			}
		}else if(type == Constants.TIMESTAMP){
			try {
				date = timestampSdf.parse(int_obj.toString());
			} catch (Exception e) {
				log.error(e);
			}
		}
		return date;
	}

	private Object getValue(Map<String, Object> map, String key)  {
		if(StringUtils.isEmpty(map)){
			return null;
		}
		if(StringUtils.isEmpty(key)){
			return null;
		}
		return map.get(key);
	}


	/**
	 * @Description:
	 * @param:
	 * @returns: 
	 * @throws: 
	 * @author: zrq
	 * @Date: 2017/9/16 14:33
	 */
	public String fetchStringValue(Map<String,Object> map,String key){
		if(map==null||map.isEmpty()|| org.apache.commons.lang3.StringUtils.isBlank(key)||map.get(key)==null|| org.apache.commons.lang3.StringUtils.isBlank(map.get(key).toString())){
			return null;
		}
		return map.get(key).toString();
	}
	public Integer fetchIntegerValue(Map<String,Object> map,String key){
		if(map==null||map.isEmpty()|| org.apache.commons.lang3.StringUtils.isBlank(key)||map.get(key)==null|| org.apache.commons.lang3.StringUtils.isBlank(map.get(key).toString())){
			return null;
		}
		return new BigDecimal(map.get(key).toString()).intValue();
	}
	public Double fetchDoubleValue(Map<String,Object> map,String key){
		if(map==null||map.isEmpty()|| org.apache.commons.lang3.StringUtils.isBlank(key)||map.get(key)==null|| org.apache.commons.lang3.StringUtils.isBlank(map.get(key).toString())){
			return null;
		}
		return new BigDecimal(map.get(key).toString()).doubleValue();
	}
	public BigDecimal fetchBigDecimalValue(Map<String,Object> map,String key){
		if(map==null||map.isEmpty()|| org.apache.commons.lang3.StringUtils.isBlank(key)||map.get(key)==null|| org.apache.commons.lang3.StringUtils.isBlank(map.get(key).toString())){
			return null;
		}
		return new BigDecimal(map.get(key).toString());
	}
	public Map<String,Object> getMap(HttpServletRequest request){
		Map<String,Object> sourceMap=new HashMap<>();
		if(request==null){
			return sourceMap;
		}
		for(Map.Entry<String,String[]> data: request.getParameterMap().entrySet()){
			System.out.println(data.getKey()+"==="+ Arrays.toString(data.getValue()));
			sourceMap.put(data.getKey(),data.getValue()[0]);
		}
		return sourceMap;
	}

}
