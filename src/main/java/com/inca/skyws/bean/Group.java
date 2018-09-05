package com.inca.skyws.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.inca.skyws.bean.annotation.Title;

@Title("聊天群")
@Entity(name = "sys_group")
public class Group extends BaseEntity {
	private static final long serialVersionUID = 7865892382773067178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("群编码")
	@Column(nullable = false, unique = true)
	private String groupCode;

	@Title("群名")
	@Column(nullable = false)
	private String groupName;

	@Title("群主ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

	@Title("群人员")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_group_member", joinColumns = {
			@JoinColumn(name = "group_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "member_id", referencedColumnName = "id") })
	private List<User> members;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

}
