package com.inca.skyws.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inca.skyws.bean.annotation.Title;

@Title("行政区划")
@Entity(name = "pub_division")
public class Division extends BaseEntity {
	private static final long serialVersionUID = 4782390048166898509L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("全称")
	private String fullName;

	@Title("名称")
	private String name;

	@Title("上级ID")
	@Column(name = "parent_id")
	private Division parent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Division getParent() {
		return parent;
	}

	public void setParent(Division parent) {
		this.parent = parent;
	}

}
