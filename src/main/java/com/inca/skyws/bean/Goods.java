package com.inca.skyws.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.inca.skyws.bean.annotation.Title;

@Title("商品表")
@Entity(name = "pub_goods")
public class Goods extends BaseEntity {
	private static final long serialVersionUID = 7422039013630421256L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("商品编码")
	@Column(nullable = false, unique = true)
	private String goodsCode;

	@Title("商品名")
	@Column(nullable = false)
	private String goodsName;

	@Title("单位")
	@Column(nullable = false)
	private String unit;

	@Title("规格")
	@Column(nullable = false)
	private String spec;

	@Title("价格")
	@Column(precision = 20, scale = 10)
	private BigDecimal price;

	@Title("重量")
	@Column(precision = 20, scale = 10)
	private BigDecimal weight;

	@Title("图片")
	private String pic;

	@Title("宣传图片")
	private String majPic;

	@Title("商品介绍")
	@Lob
	private String goodsProfile;

	@Title("点击次数")
	private String clickNum;

	@Title("商品评论")
	@OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
	private List<GoodsReview> reviews;

	@Title("卖家")
	@OneToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
	private User seller;

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

	public List<GoodsReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<GoodsReview> reviews) {
		this.reviews = reviews;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

}
