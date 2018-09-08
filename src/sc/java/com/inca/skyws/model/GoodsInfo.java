package com.inca.skyws.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.inca.skyws.bean.User;
import com.inca.skyws.bean.annotation.Title;

public class GoodsInfo {
	private Integer id;
	private String goodsCode;
	private String goodsName;
	private String unit;
	private String spec;
	private BigDecimal price;
	private BigDecimal weight;
	private String pic;
	private String majPic;
	private String goodsProfile;
	private String clickNum;
	private UserInfo seller;
	private Integer stock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getMajPic() {
		return majPic;
	}

	public void setMajPic(String majPic) {
		this.majPic = majPic;
	}

	public String getGoodsProfile() {
		return goodsProfile;
	}

	public void setGoodsProfile(String goodsProfile) {
		this.goodsProfile = goodsProfile;
	}

	public String getClickNum() {
		return clickNum;
	}

	public void setClickNum(String clickNum) {
		this.clickNum = clickNum;
	}

	public UserInfo getSeller() {
		return seller;
	}

	public void setSeller(UserInfo seller) {
		this.seller = seller;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
