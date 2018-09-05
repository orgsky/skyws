package com.inca.skyws.bean;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.inca.skyws.bean.annotation.Title;

@Title("角色表")
@Entity(name = "sys_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = -4827798506877738634L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("角色编号")
	@Column(nullable = false, unique = true)
	private String roleCode;

	@Title("角色名")
	@Column(nullable = false)
	private String roleName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	private List<User> users;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	@Basic(fetch = FetchType.EAGER)
	private List<Authorization> auths;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Authorization> getAuths() {
		return auths;
	}

	public void setAuths(List<Authorization> auths) {
		this.auths = auths;
	}

}
