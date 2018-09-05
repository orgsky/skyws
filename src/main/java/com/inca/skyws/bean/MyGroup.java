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

@Title("我的分组")
@Entity(name = "sys_mygroup")
public class MyGroup extends BaseEntity {
	private static final long serialVersionUID = -6971163279838623938L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("分组编码")
	@Column(nullable = false, unique = true)
	private String groupCode;

	@Title("分组名称")
	@Column(nullable = false, unique = true)
	private String groupName;

	@Title("组头像")
	@Column(nullable = false, unique = true)
	private String pic;

	@Title("拥有者ID")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

	@Title("好友列表")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_mygroup_friend", joinColumns = {
			@JoinColumn(name = "mygroup_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "friend_id", referencedColumnName = "id") })
	private List<User> friends;

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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

}
