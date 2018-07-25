package com.zhidisoft.business.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonJob {
    private Integer id;

    private String idcard;

    private Integer companyid;

    private String jobtype;

    private Double companyprice;

    private Double personprice;

    private String jobcontent;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;

    private String contracturl;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String remark;

    private String name;
    
    private String companyName;
    
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

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public Double getCompanyprice() {
        return companyprice;
    }

    public void setCompanyprice(Double companyprice) {
        this.companyprice = companyprice;
    }

    public Double getPersonprice() {
        return personprice;
    }

    public void setPersonprice(Double personprice) {
        this.personprice = personprice;
    }

    public String getJobcontent() {
        return jobcontent;
    }

    public void setJobcontent(String jobcontent) {
        this.jobcontent = jobcontent;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getContracturl() {
        return contracturl;
    }

    public void setContracturl(String contracturl) {
        this.contracturl = contracturl;
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

	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "PersonJob [id=" + id + ", idcard=" + idcard + ", companyid=" + companyid + ", jobtype=" + jobtype
				+ ", companyprice=" + companyprice + ", personprice=" + personprice + ", jobcontent=" + jobcontent
				+ ", starttime=" + starttime + ", endtime=" + endtime + ", contracturl=" + contracturl + ", status="
				+ status + ", createby=" + createby + ", createtime=" + createtime + ", updateby=" + updateby
				+ ", updatetime=" + updatetime + ", remark=" + remark + ", name=" + name + "]";
	}
    
    
}