package com.zhidisoft.business.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Salary {
    private Integer id;

    private String idcard;

    private String paycard;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date paydate;

    private Double basesalary;

    private Double bonuspay;

    private Double overtimepay;

    private Double shebaopay;

    private Double gongjijinpay;

    private Double taxpay;

    private Double totalpay;

    private Double mustpay;

    private Double proxyfee;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;

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

    public String getPaycard() {
        return paycard;
    }

    public void setPaycard(String paycard) {
        this.paycard = paycard;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Double getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(Double basesalary) {
        this.basesalary = basesalary;
    }

    public Double getBonuspay() {
        return bonuspay;
    }

    public void setBonuspay(Double bonuspay) {
        this.bonuspay = bonuspay;
    }

    public Double getOvertimepay() {
        return overtimepay;
    }

    public void setOvertimepay(Double overtimepay) {
        this.overtimepay = overtimepay;
    }

    public Double getShebaopay() {
        return shebaopay;
    }

    public void setShebaopay(Double shebaopay) {
        this.shebaopay = shebaopay;
    }

    public Double getGongjijinpay() {
        return gongjijinpay;
    }

    public void setGongjijinpay(Double gongjijinpay) {
        this.gongjijinpay = gongjijinpay;
    }

    public Double getTaxpay() {
        return taxpay;
    }

    public void setTaxpay(Double taxpay) {
        this.taxpay = taxpay;
    }

    public Double getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(Double totalpay) {
        this.totalpay = totalpay;
    }

    public Double getMustpay() {
        return mustpay;
    }

    public void setMustpay(Double mustpay) {
        this.mustpay = mustpay;
    }

    public Double getProxyfee() {
        return proxyfee;
    }

    public void setProxyfee(Double proxyfee) {
        this.proxyfee = proxyfee;
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
}