package com.zhidisoft.business.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Customer {
    private Integer id;

    private String name;

    private String idcard;

    private String sex;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String phone;

    private String email;

    private String address;

    private String zipcode;

    private String school;

    private String specialty;

    private String graduation;

    private Integer companyid;

    private String customertype;

    private String issalary;

    private String isshebao;

    private String isgongjijin;

    private String status;

    private String delflag;

    private Date createtime;

    private Integer createby;

    private Date updatetime;

    private Integer updateby;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getIssalary() {
        return issalary;
    }

    public void setIssalary(String issalary) {
        this.issalary = issalary;
    }

    public String getIsshebao() {
        return isshebao;
    }

    public void setIsshebao(String isshebao) {
        this.isshebao = isshebao;
    }

    public String getIsgongjijin() {
        return isgongjijin;
    }

    public void setIsgongjijin(String isgongjijin) {
        this.isgongjijin = isgongjijin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", idcard=" + idcard + ", sex=" + sex + ", birthday="
				+ birthday + ", phone=" + phone + ", email=" + email + ", address=" + address + ", zipcode=" + zipcode
				+ ", school=" + school + ", specialty=" + specialty + ", graduation=" + graduation + ", companyid="
				+ companyid + ", customertype=" + customertype + ", issalary=" + issalary + ", isshebao=" + isshebao
				+ ", isgongjijin=" + isgongjijin + ", status=" + status + ", delflag=" + delflag + ", createtime="
				+ createtime + ", createby=" + createby + ", updatetime=" + updatetime + ", updateby=" + updateby
				+ ", remark=" + remark + "]";
	}
    
    
}