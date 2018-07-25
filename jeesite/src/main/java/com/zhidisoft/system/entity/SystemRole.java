package com.zhidisoft.system.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SystemRole {
    private Integer id;

    private String rolename;

    private Integer sortnum;

    private String status;

    private Integer createby;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createtime;

    private Integer updateby;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatetime;

    private String rolenote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

    public String getRolenote() {
        return rolenote;
    }

    public void setRolenote(String rolenote) {
        this.rolenote = rolenote;
    }

	@Override
	public String toString() {
		return "SystemRole [id=" + id + ", rolename=" + rolename + ", sortnum=" + sortnum + ", status=" + status
				+ ", createby=" + createby + ", createtime=" + createtime + ", updateby=" + updateby + ", updatetime="
				+ updatetime + ", rolenote=" + rolenote + "]";
	}
    
    
}