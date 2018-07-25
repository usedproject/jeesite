package com.zhidisoft.business.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SocialInsurance {
    private Integer id;

    private String idcard;

    private String sbcard;

    private Double basepay;

    private Double mustpay;

    private String personratio;

    private String companyratio;

    private Double yanglao;

    private Double yiliao;

    private Double gongshang;

    private Double shiye;

    private Double shengyu;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date paydate;

    private Double proxyfee;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;
    
    //增加字段
    private Customer customer;

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

    public String getSbcard() {
        return sbcard;
    }

    public void setSbcard(String sbcard) {
        this.sbcard = sbcard;
    }

    public Double getBasepay() {
        return basepay;
    }

    public void setBasepay(Double basepay) {
        this.basepay = basepay;
    }

    public Double getMustpay() {
        return mustpay;
    }

    public void setMustpay(Double mustpay) {
        this.mustpay = mustpay;
    }

    public String getPersonratio() {
        return personratio;
    }

    public void setPersonratio(String personratio) {
        this.personratio = personratio;
    }

    public String getCompanyratio() {
        return companyratio;
    }

    public void setCompanyratio(String companyratio) {
        this.companyratio = companyratio;
    }

    public Double getYanglao() {
        return yanglao;
    }

    public void setYanglao(Double yanglao) {
        this.yanglao = yanglao;
    }

    public Double getYiliao() {
        return yiliao;
    }

    public void setYiliao(Double yiliao) {
        this.yiliao = yiliao;
    }

    public Double getGongshang() {
        return gongshang;
    }

    public void setGongshang(Double gongshang) {
        this.gongshang = gongshang;
    }

    public Double getShiye() {
        return shiye;
    }

    public void setShiye(Double shiye) {
        this.shiye = shiye;
    }

    public Double getShengyu() {
        return shengyu;
    }

    public void setShengyu(Double shengyu) {
        this.shengyu = shengyu;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
    
}