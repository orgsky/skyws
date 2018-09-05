package com.inca.skyws.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("收发货地址")
@Entity(name = "pub_address")
public class Address extends BaseEntity {
	private static final long serialVersionUID = 7629257266303136986L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("拥有人ID")
	@Column(name = "owner_id", nullable = false)
	private User owner;

	@Title("地址类型")
	@Column(precision = 1, nullable = false)
	@OptionKey(name = "SYS_ADDR_TYPE")
	private Integer addrType;

	@Title("区划")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "divisid")
	private Division divis;

	@Title("定位地址")
	private String mapAddr;

	@Title("详细地址")
	private String address;

	@Title("地址经度")
	@Column(name = "lng", precision = 20, scale = 10)
	private BigDecimal lng;

	@Title("地址纬度")
	@Column(name = "lat")
	private BigDecimal lat;

	@Title("是否默认")
	@Column(precision = 1)
	@OptionKey(name = "SYS_DEFAUT_FLAG")
	private Integer isDefault;

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

	public Integer getAddrType() {
		return addrType;
	}

	public void setAddrType(Integer addrType) {
		this.addrType = addrType;
	}

	public Division getDivis() {
		return divis;
	}

	public void setDivis(Division divis) {
		this.divis = divis;
	}

	public String getMapAddr() {
		return mapAddr;
	}

	public void setMapAddr(String mapAddr) {
		this.mapAddr = mapAddr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
