package com.inca.skyws.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("用户表")
@Entity(name = "sys_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = -2244951885482110027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("编号")
	@Column(nullable = false, unique = true)
	private String usercode;

	@Title("姓名")
	@NonNull
	@NotEmpty(message = "用户名不能为空")
	@Column(nullable = false)
	private String username;

	@Title("昵称")
	@NonNull
	@NotEmpty(message = "请取一个昵称")
	@Column(nullable = false)
	private String nickname;

	@Title("密码")
	private String webpwd;

	@Title("加密密码")
	@NonNull
	@NotEmpty(message = "密码不能为空")
	@Length(min = 6, message = "密码长度不能少于6位")
	private String password;

	@Title("生日")
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Title("身份证")
	@Column(unique = true)
	private String idcard;

	@Title("性别")
	@Column(precision = 1, nullable = false)
	private Integer sex;

	@Title("居住地址")
	private String address;

	@Title("手机号")
	@NonNull
	@NotEmpty(message = "手机号不能为空")
	@Length(min = 7, max = 11, message = "手机号不正确")
	@Column(unique = true)
	private String phone;

	@Title("e-mail")
	@Email
	@NotEmpty(message = "邮箱不能为空")
	@Column(unique = true)
	private String email;

	@Title("学历")
	private String education;

	@Title("专业")
	private String profession;

	@Title("头像")
	private String headimg;

	@Title("QQ")
	private String qq;

	@Title("在线状态")
	@OptionKey(name = "SYS_ONLINE_STATUS")
	private Integer onlineStatus;

	@Title("推荐人")
	@OneToOne
	@JoinColumn(name = "ref_user_id", referencedColumnName = "id")
	private User refuser;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<Role> roles;

	@Title("商品评论")
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
	private List<GoodsReview> reviews;

	@Title("我的分组")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private List<MyGroup> myGroups;

	@Title("所属分组")
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "friends")
	private List<MyGroup> joinMyGroups;

	@Title("我的群")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private List<Group> groups;

	@Title("所属群")
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
	private List<Group> joinGroups;

	@Title("购物车")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private List<Cart> carts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public User getRefuser() {
		return refuser;
	}

	public void setRefuser(User refuser) {
		this.refuser = refuser;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<GoodsReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<GoodsReview> reviews) {
		this.reviews = reviews;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
	}

	public List<MyGroup> getJoinMyGroups() {
		return joinMyGroups;
	}

	public void setJoinMyGroups(List<MyGroup> joinMyGroups) {
		this.joinMyGroups = joinMyGroups;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getJoinGroups() {
		return joinGroups;
	}

	public void setJoinGroups(List<Group> joinGroups) {
		this.joinGroups = joinGroups;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

}
