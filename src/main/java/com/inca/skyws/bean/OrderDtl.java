package com.inca.skyws.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("订单明细表")
@Entity(name = "pub_order_dtl")
public class OrderDtl extends BaseEntity {
	private static final long serialVersionUID = 1285538426704202795L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("订单总单")
	@ManyToOne
	private OrderDoc orderDoc;

	@Title("商品")
	@Column(name = "goods_id", nullable = false)
	private Goods goods;

	@Title("买家备注")
	@Column(name = "buyer_memo")
	private String buyerMemo;

	@Title("卖家回复")
	@Column(name = "seller_memo")
	private String sellerMemo;

	@Title("数量")
	@Column(precision = 16, scale = 4)
	private BigDecimal qty;

	@Title("重量")
	@Column(precision = 16, scale = 4)
	private BigDecimal weight;

	@Title("单价")
	@Column(precision = 16, scale = 4)
	private BigDecimal unitPrice;

	@Title("金额")
	@Column(precision = 16, scale = 4)
	private BigDecimal money;

	@Title("退货数量")
	@Column(precision = 16, scale = 4)
	private BigDecimal backQty;

	@Title("退货状态")
	@Column(precision = 16, scale = 4)
	@OptionKey(name = "PUB_PAY_STATUS") // 1-待付款 2-已付款 3-已退款
	private Integer backStatus;

	@Title("退货原因")
	private String backReason;

	@Title("退货完成时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date backFinishTime;

	@Title("退货运费")
	@Column(precision = 16, scale = 4)
	private BigDecimal backExpFee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderDoc getOrderDoc() {
		return orderDoc;
	}

	public void setOrderDoc(OrderDoc orderDoc) {
		this.orderDoc = orderDoc;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getBackQty() {
		return backQty;
	}

	public void setBackQty(BigDecimal backQty) {
		this.backQty = backQty;
	}

	public Integer getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(Integer backStatus) {
		this.backStatus = backStatus;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public Date getBackFinishTime() {
		return backFinishTime;
	}

	public void setBackFinishTime(Date backFinishTime) {
		this.backFinishTime = backFinishTime;
	}

	public BigDecimal getBackExpFee() {
		return backExpFee;
	}

	public void setBackExpFee(BigDecimal backExpFee) {
		this.backExpFee = backExpFee;
	}

}
