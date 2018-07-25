package com.zhidisoft.business.entity;

import java.util.Date;

public class Person {
    private Integer id;

    private String idcard;

    private String jobintension;

    private String jobtype;

    private Double forprice;

    private String foraddress;

    private String worked;

    private String personinfo;

    private String resumeurl;

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

    public String getJobintension() {
        return jobintension;
    }

    public void setJobintension(String jobintension) {
        this.jobintension = jobintension;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public Double getForprice() {
        return forprice;
    }

    public void setForprice(Double forprice) {
        this.forprice = forprice;
    }

    public String getForaddress() {
        return foraddress;
    }

    public void setForaddress(String foraddress) {
        this.foraddress = foraddress;
    }

    public String getWorked() {
        return worked;
    }

    public void setWorked(String worked) {
        this.worked = worked;
    }

    public String getPersoninfo() {
        return personinfo;
    }

    public void setPersoninfo(String personinfo) {
        this.personinfo = personinfo;
    }

    public String getResumeurl() {
        return resumeurl;
    }

    public void setResumeurl(String resumeurl) {
        this.resumeurl = resumeurl;
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
		return "Person [id=" + id + ", idcard=" + idcard + ", jobintension=" + jobintension + ", jobtype=" + jobtype
				+ ", forprice=" + forprice + ", foraddress=" + foraddress + ", worked=" + worked + ", personinfo="
				+ personinfo + ", resumeurl=" + resumeurl + ", status=" + status + ", createby=" + createby
				+ ", createtime=" + createtime + ", updateby=" + updateby + ", updatetime=" + updatetime + ", remark="
				+ remark + ", name=" + name + "]";
	}
    
    
}