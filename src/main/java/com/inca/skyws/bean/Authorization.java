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

import com.inca.skyws.bean.annotation.Title;

@Title("权限表")
@Entity(name = "sys_auth")
public class Authorization extends BaseEntity {

	private static final long serialVersionUID = -4265033953352099083L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("角色编号")
	@Column(nullable = false, unique = true)
	private String authCode;

	@Title("角色名")
	@Column(nullable = false)
	private String authName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_auth", joinColumns = {
			@JoinColumn(name = "auth_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	

}
