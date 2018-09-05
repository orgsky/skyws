package com.inca.skyws.service;

import com.inca.skyws.exception.SysException;

/**
 * 
    * @ClassName: CodeRuleService
    * @Description: 根据功能创建编号
    * @author szw
    * @date 2018年8月25日
    *
 */
public interface CodeRuleService {
	public static final String NAME = "codeRuleService";
	
	/**
	 * 
	    * @Title: genCode
	    * @Description: 实体
	    * @param clz
	    * @return
	    * @throws SysException    参数
	    * @return String    返回类型
	    * @throws
	 */
	String genCode(Class<? extends Object> clz) throws SysException;
}
