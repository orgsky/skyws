package com.inca.skyws.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;

@Title("订单表")
@Entity(name = "pub_order_doc")
public class OrderDoc extends BaseEntity {
	private static final long serialVersionUID = -2431377132781295704L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Title("订单编号")
	@Column(nullable = false, unique = true)
	private String orderNo;

	@Title("买方ID")
	@Column(name = "owner_id", nullable = false)
	private User owner;

	@Title("卖方ID")
	@Column(name = "seller_id", nullable = false)
	private User seller;

	@Title("收货地址ID")
	@Column(name = "address_id", nullable = false)
	private Address address;

	@Title("收货地址")
	private String realAddress;

	@Title("下单时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;

	@Title("发货时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;

	@Title("总件数")
	@Column(precision = 16, scale = 4)
	private BigDecimal qty;

	@Title("总重量")
	@Column(precision = 16, scale = 4)
	private BigDecimal weight;

	@Title("支付金额")
	@Column(precision = 16, scale = 4)
	private BigDecimal pay;

	@Title("支付方式")
	@OptionKey(name = "PUB_PAYMENT")
	private Integer payment;

	@Title("支付状态")
	@OptionKey(name = "PUB_PAY_STATUS") // 1-待付款 2-已付款 3-已退款
	private Integer payStatus;

	@Title("订单状态")
	@OptionKey(name = "PUB_ORDER_STATUS")
	private Integer orderStatus;

	@Title("单据类型")
	@OptionKey(name = "PUB_ORDER_TYPE")
	private Integer orderType;

	@Title("原单ID")
	@Column(name = "ori_order_id", nullable = false)
	private OrderDoc oriOrder;

	@Title("快递公司")
	private String expressCompany;

	@Title("快递单号")
	private String expressNo;

	@Title("运费")
	@Column(precision = 16, scale = 4)
	private BigDecimal expressMoney;

	@Title("订单细单")
	@OneToMany(mappedBy = "orderDoc")
	private List<OrderDtl> orderDtls;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRealAddress() {
		return realAddress;
	}

	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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

	public BigDecimal getPay() {
		return pay;
	}

	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderDoc getOriOrder() {
		return oriOrder;
	}

	public void setOriOrder(OrderDoc oriOrder) {
		this.oriOrder = oriOrder;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public BigDecimal getExpressMoney() {
		return expressMoney;
	}

	public void setExpressMoney(BigDecimal expressMoney) {
		this.expressMoney = expressMoney;
	}

	public List<OrderDtl> getOrderDtls() {
		return orderDtls;
	}

	public void setOrderDtls(List<OrderDtl> orderDtls) {
		this.orderDtls = orderDtls;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

}
