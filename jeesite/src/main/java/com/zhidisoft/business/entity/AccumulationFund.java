package com.zhidisoft.business.entity;

import java.util.Date;

public class AccumulationFund {
    private Integer id;

    private String idcard;

    private String accountno;

    private String paydate;

    private Double paymoney;

    private Double proxyfee;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;
    
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public Double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Double paymoney) {
        this.paymoney = paymoney;
    }

    public Double getProxyfee() {
        return proxyfee;
    }

    public void setProxyfee(Double proxyfee) {
        this.proxyfee = proxyfee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AccumulationFund [id=" + id + ", idcard=" + idcard + ", accountno=" + accountno + ", paydate=" + paydate
				+ ", paymoney=" + paymoney + ", proxyfee=" + proxyfee + ", status=" + status + ", createby=" + createby
				+ ", createtime=" + createtime + ", updateby=" + updateby + ", updatetime=" + updatetime + ", remark="
				+ remark + ", name=" + name + "]";
	}
    
    
}