package com.zhidisoft.business.entity;

import java.util.Date;

public class Replay {
    private Integer id;

    private Integer messageid;

    private String status;

    private Date replaytime;

    private Integer replayby;

    private String remark;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReplaytime() {
        return replaytime;
    }

    public void setReplaytime(Date replaytime) {
        this.replaytime = replaytime;
    }

    public Integer getReplayby() {
        return replayby;
    }

    public void setReplayby(Integer replayby) {
        this.replayby = replayby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}