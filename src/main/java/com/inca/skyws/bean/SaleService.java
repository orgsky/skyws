package com.inca.skyws.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("售后申请表")
@Entity(name = "pub_sale_service")
public class SaleService extends BaseEntity {
	private static final long serialVersionUID = 2276866124950718818L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("退货原因")
	private String backReason;

	@Title("退货数量")
	@Column(precision = 12, scale = 2)
	private BigDecimal backqty;

	@Title("退款金额")
	@Column(precision = 12, scale = 2)
	private BigDecimal bakcmoney;

	@Title("退款说明")
	private String remark;

	@Title("订单细单ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_dtl_id")
	private OrderDtl orderDtl;

	@Title("买方ID")
	@Column(name = "owner_id", nullable = false)
	private User owner;

	@Title("卖方ID")
	@Column(name = "seller_id", nullable = false)
	private User seller;

	@Title("售后状态")
	@Column(name = "refund_status", precision = 3)
	@OptionKey(name = "PUB_SALEERVICE_STATUS")
	private Integer refundStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Title("退款申请时间")
	private Date refundTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Title("退款完成时间")
	private Date finishTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public BigDecimal getBackqty() {
		return backqty;
	}

	public void setBackqty(BigDecimal backqty) {
		this.backqty = backqty;
	}

	public BigDecimal getBakcmoney() {
		return bakcmoney;
	}

	public void setBakcmoney(BigDecimal bakcmoney) {
		this.bakcmoney = bakcmoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OrderDtl getOrderDtl() {
		return orderDtl;
	}

	public void setOrderDtl(OrderDtl orderDtl) {
		this.orderDtl = orderDtl;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}
