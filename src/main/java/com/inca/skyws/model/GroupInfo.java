package com.inca.skyws.model;

import java.io.Serializable;
import java.util.List;

public class GroupInfo implements Serializable {
	private static final long serialVersionUID = -2543754145523886277L;
	private Integer id;
	private String groupCode;
	private String groupName;
	private Integer sequence;
	private UserInfo owner;

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

	public UserInfo getOwner() {
		return owner;
	}

	public void setOwner(UserInfo owner) {
		this.owner = owner;
	}


	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
