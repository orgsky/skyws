package com.inca.skyws.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inca.skyws.bean.annotation.OptionKey;
import com.inca.skyws.bean.annotation.Title;
import com.inca.skyws.bean.listener.BaseBeanListener;

@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class, BaseBeanListener.class })
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7893479728684777618L;

	@Title("创建时间")
	@Basic(fetch = FetchType.LAZY)
	private Integer _createUserId;

	@Title("创建时间")
	@Basic(fetch = FetchType.LAZY)
	@CreatedDate
	@JsonIgnore
	private Date _createTime;

	@Title("最后修改时间")
	@Basic(fetch = FetchType.LAZY)
	@LastModifiedDate
	@JsonIgnore
	private Date _lastModifyTime;

	@Title("版本")
	@Basic(fetch = FetchType.LAZY)
	@Version
	@JsonIgnore
	private Integer _version;

	@Title("序号")
	@Basic(fetch = FetchType.LAZY)
	private Integer sequence;

	@Title("删除状态")
	@Column(nullable = true)
	@JsonIgnore
	private Boolean _deleted;

	@Title("状态")
	@OptionKey(name = "SYS_STATUS")
	private Integer status;

	@Title("备注")
	private String memo;

	public Date get_createTime() {
		return _createTime;
	}

	public void set_createTime(Date _createTime) {
		this._createTime = _createTime;
	}

	public Date get_lastModifyTime() {
		return _lastModifyTime;
	}

	public void set_lastModifyTime(Date _lastModifyTime) {
		this._lastModifyTime = _lastModifyTime;
	}

	public Integer get_version() {
		return _version;
	}

	public void set_version(Integer _version) {
		this._version = _version;
	}

	public Boolean get_deleted() {
		return _deleted;
	}

	public void set_deleted(Boolean _deleted) {
		this._deleted = _deleted;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer get_createUserId() {
		return _createUserId;
	}

	public void set_createUserId(Integer _createUserId) {
		this._createUserId = _createUserId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
