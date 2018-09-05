package com.inca.skyws.system;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginUser extends User implements Serializable {

	private static final long serialVersionUID = 2924103846185142989L;

	private Integer id;
	private String usercode;
	private String username;
	private String nickname;
	private String webpwd;
	private String idcard;
	private String address;
	private String phone;
	private String email;
	private String education;
	private String profession;
	private String headimg;
	private String qq;
	private Date birthdate;
	private Date busiDate;
	private Integer sex;
	private User refuser;

	public LoginUser() {
		this("nologin", "nopassword", new ArrayList<>());
	}

	public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public static LoginUser getLoginUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (!ObjectUtils.isEmpty(context)) {
			Authentication auth = context.getAuthentication();
			if (!ObjectUtils.isEmpty(auth)) {
				Object principal = auth.getPrincipal();
				if (!ObjectUtils.isEmpty(principal) && principal instanceof LoginUser) {
					return (LoginUser) principal;
				}
			}
		}
		return null;
	}

	@JsonIgnore
	public Date getBillBusiDate() {
		if (ObjectUtils.isEmpty(busiDate)) {
			return new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat newsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dates = sdf.format(busiDate);
		String dates1 = sdf1.format(new Date());
		String newdates = dates + " " + dates1;
		Date newdate = null;
		try {
			newdate = newsdf.parse(newdates);
		} catch (ParseException e) {
			newdate = new Date();
		}
		return newdate;
	}

	public Date getBusiDate() {
		return busiDate;
	}

	public void setBusiDate(Date busiDate) {
		this.busiDate = busiDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWebpwd() {
		return webpwd;
	}

	public void setWebpwd(String webpwd) {
		this.webpwd = webpwd;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public User getRefuser() {
		return refuser;
	}

	public void setRefuser(User refuser) {
		this.refuser = refuser;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}
