package com.inca.skyws.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inca.skyws.bean.annotation.Title;

@Title("系统功能编码")
@Entity(name = "sys_code_rule")
public class CodeRule extends BaseEntity {
	private static final long serialVersionUID = -1589442370144746975L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("前缀")
	private String prefix;

	@Title("格式")
	private String format;

	@Title("值长度")
	private Integer length;

	@Title("当前值")
	private Integer value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
