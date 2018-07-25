package com.zhidisoft.utils;
/**
 * 这是用于返回Ztree节点的方法
 * @author 张磊
 * @date 2018年3月16日
 */
public class ZtreeNode {
	
	private Integer id;
	private Integer pId;//父权限id
	private String name;//权限名
	private boolean nocheck;//节点是否隐藏
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	
	
}
