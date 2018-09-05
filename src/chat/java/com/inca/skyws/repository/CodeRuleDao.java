package com.inca.skyws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inca.skyws.bean.CodeRule;

public interface CodeRuleDao extends JpaRepository<CodeRule, Integer> {

	CodeRule findOneByPrefix(String code);

}
