package com.inca.skyws.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.inca.skyws.bean.annotation.Title;

@Title("商品评论表")
@Entity(name = "pub_goods_review")
public class GoodsReview extends BaseEntity {
	private static final long serialVersionUID = -8681907499599530592L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("商品")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id", nullable = false)
	private Goods goods;

	@Title("买家ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyer_id", nullable = false)
	private User buyer;

	@Title("评论内容")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
