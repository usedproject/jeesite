package com.zhidisoft.system.entity;

import java.util.Date;

public class SystemFunction {
    private Integer id;

    private String funcname;

    private String funcurl;

    private Integer functype;

    private SystemFunction parentfunction;

    private String iconclass;

    private String iconurl;

    private Integer sortnum;

    private String status;

    private Integer createby;

    private Date createtime;

    private Integer updateby;

    private Date updatetime;

    private String funcnote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncname() {
        return funcname;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname;
    }

    public String getFuncurl() {
        return funcurl;
    }

    public void setFuncurl(String funcurl) {
        this.funcurl = funcurl;
    }

    public Integer getFunctype() {
        return functype;
    }

    public void setFunctype(Integer functype) {
        this.functype = functype;
    }

    public SystemFunction getParentfunction() {
		return parentfunction;
	}

	public void setParentfunction(SystemFunction parentfunction) {
		this.parentfunction = parentfunction;
	}


    public String getIconclass() {
        return iconclass;
    }

    public void setIconclass(String iconclass) {
        this.iconclass = iconclass;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public Integer getSortnum() {
        return sortnum;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
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

    public String getFuncnote() {
        return funcnote;
    }

    public void setFuncnote(String funcnote) {
        this.funcnote = funcnote;
    }

	@Override
	public String toString() {
		return "SystemFunction [id=" + id + ", funcname=" + funcname + ", funcurl=" + funcurl + ", functype=" + functype
				+ ", parentfunction=" + parentfunction + ", iconclass=" + iconclass + ", iconurl=" + iconurl
				+ ", sortnum=" + sortnum + ", status=" + status + ", createby=" + createby + ", createtime="
				+ createtime + ", updateby=" + updateby + ", updatetime=" + updatetime + ", funcnote=" + funcnote + "]";
	}

	
    
    
}