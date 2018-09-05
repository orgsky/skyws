package com.inca.skyws.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.inca.skyws.bean.annotation.Title;

@Title("选项字典")
@Entity(name = "sys_option")
public class Option extends BaseEntity {
	private static final long serialVersionUID = -1319976809064787143L;

	public Option() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("关键字")
	@Column(nullable = false, unique = true)
	private String keyword;

	@Title("说明")
	private String summary;

	@OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
	private List<OptionItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<OptionItem> getItems() {
		return items;
	}

	public void setItems(List<OptionItem> items) {
		this.items = items;
	}

}
