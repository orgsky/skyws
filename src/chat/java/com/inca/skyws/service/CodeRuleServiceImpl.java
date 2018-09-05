package com.inca.skyws.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inca.skyws.bean.CodeRule;
import com.inca.skyws.constants.RuleEnum;
import com.inca.skyws.exception.CommonExceptionEnum;
import com.inca.skyws.exception.SysException;
import com.inca.skyws.repository.CodeRuleDao;

@Transactional
@Service(CodeRuleService.NAME)
public class CodeRuleServiceImpl implements CodeRuleService {
	private static final Logger log = LoggerFactory.getLogger(CodeRuleServiceImpl.class);

	@Autowired
	CodeRuleDao ruleDao;

	@Override
	public String genCode(Class<? extends Object> clz) throws SysException {
		log.info("创建编号的功能：" + clz);
		if (clz == null) {
			throw new SysException(CommonExceptionEnum.CM_NOT_EMPTY);
		}
		String func = clz.getSimpleName().toLowerCase();
		RuleEnum defRule = RuleEnum.findByFunc(func);
		log.info("功能默认编号规则：" + defRule);
		if (defRule == null) {
			throw new SysException(CommonExceptionEnum.CM_NO_RULE);
		}
		String prefix = defRule.getPrefix();
		CodeRule codeRule = ruleDao.findOneByPrefix(prefix);
		log.info("用户自定义规则：" + codeRule);
		if (codeRule != null) {
			String date = new SimpleDateFormat(codeRule.getFormat()).format(new Date());
			codeRule.setValue(codeRule.getValue() + 1);
			String nextValue = getNextValue(prefix,date,codeRule.getLength(), codeRule.getValue());
			return nextValue;
		}
		log.info("还没有功能记录，新增：" + codeRule);
		codeRule = new CodeRule();
		String date = new SimpleDateFormat(defRule.getFormat()).format(new Date());
		codeRule.setFormat(defRule.getFormat());
		codeRule.setLength(defRule.getLength());
		codeRule.setPrefix(prefix);
		codeRule.setValue(1);
		ruleDao.save(codeRule);
		String nextValue = getNextValue(prefix, date, defRule.getLength(), 0);
		return nextValue;
	}

	private String getNextValue(String prefix, String date, Integer len, Integer value) {
		Integer needSize = (len - value.toString().length());
		String nextValue = "";
		for (int i = 0; i < needSize; i++) {
			nextValue += "0";
		}
		nextValue = nextValue + (value + 1);
		return prefix + date + nextValue;
	}

}
