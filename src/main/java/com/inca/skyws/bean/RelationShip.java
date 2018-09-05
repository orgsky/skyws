package com.inca.skyws.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("人员关系表")
@Entity(name = "sys_relation")
@Table()
public class RelationShip extends BaseEntity {
	private static final long serialVersionUID = 455260862317041377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@Title("用户ID")
	private User owner;

	@ManyToOne
	@Title("好友ID")
	private User friend;

	@ManyToOne
	@Title("友群ID")
	private Group group;

	@Title("好友类型")
	@Column(name = "relation_type", precision = 1)
	@OptionKey(name = "SYS_RELATION_TYPE")
	private Integer relationType;

	@Title("亲密度")
	@Column(precision = 1)
	@OptionKey(name = "PUB_INTIMACY")
	private Integer intimacy;

	@Title("群昵称")
	private String qlName;

	@Title("建立时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date buildTime;

	@Title("废除时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public Integer getIntimacy() {
		return intimacy;
	}

	public void setIntimacy(Integer intimacy) {
		this.intimacy = intimacy;
	}

	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getQlName() {
		return qlName;
	}

	public void setQlName(String qlName) {
		this.qlName = qlName;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}