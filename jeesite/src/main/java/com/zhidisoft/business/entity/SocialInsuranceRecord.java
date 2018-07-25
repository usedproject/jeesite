package com.zhidisoft.business.entity;

import java.util.Date;

public class SocialInsuranceRecord {
    private Integer id;

    private String sdcard;

    private Double yanglao;

    private Double yiliao;

    private Double gongshang;

    private Double shiye;

    private Double shengyu;

    private Double paymoney;

    private String paymonth;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;
    
    private SocialInsurance socialInsurance;
    
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSdcard() {
        return sdcard;
    }

    public void setSdcard(String sdcard) {
        this.sdcard = sdcard;
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

    public Double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Double paymoney) {
        this.paymoney = paymoney;
    }

    public String getPaymonth() {
        return paymonth;
    }

    public void setPaymonth(String paymonth) {
        this.paymonth = paymonth;
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

	public SocialInsurance getSocialInsurance() {
		return socialInsurance;
	}

	public void setSocialInsurance(SocialInsurance socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "SocialInsuranceRecord [id=" + id + ", sdcard=" + sdcard + ", yanglao=" + yanglao + ", yiliao=" + yiliao
				+ ", gongshang=" + gongshang + ", shiye=" + shiye + ", shengyu=" + shengyu + ", paymoney=" + paymoney
				+ ", paymonth=" + paymonth + ", status=" + status + ", createby=" + createby + ", createtime="
				+ createtime + ", updateby=" + updateby + ", updatetime=" + updatetime + ", remark=" + remark
				+ ", socialInsurance=" + socialInsurance + ", customer=" + customer + "]";
	}

	
    
}