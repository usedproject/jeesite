package com.zhidisoft.bos.web.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.service.IStaffService;
import com.zhidisoft.bos.utils.BosUtils;
import com.zhidisoft.bos.utils.PageBean;
import com.zhidisoft.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
public class StaffAction extends BaseAction<Staff> {
	
	//接收分页参数
	private int page;
	private int rows;
	
	//接收删除的ids
	private String ids;
	
	
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Resource
	private IStaffService staffService;
	
	public String addStaff() {
		JSONObject jsonObject=new JSONObject();
		try {
			staffService.addStaff(model);
			jsonObject.put("success", true);
		} catch (Exception e) {
			jsonObject.put("success", false);
			e.printStackTrace();
		}
		try {
			BosUtils.getResponse().getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String listStaff() {
		staffService.pageQuery(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows","detachedCriteria","decidedzones"});
		return null;
	}
	
	@RequiresPermissions("staff-delete")
	public String deleteStaff() {
		//获取参数
		String[] split = ids.split(",");
		JSONObject jsonObject=new JSONObject();
		try {
			staffService.deleteStaffByIds(split);
			jsonObject.put("success", true);
		} catch (Exception e) {
			jsonObject.put("success", false);
		}
		try {
			BosUtils.getResponse().getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String editStaff() {
		JSONObject jsonObject=new JSONObject();
		try {
			//查询数据库中的数据，因为有属性传不过来
			Staff staff= staffService.findById(model.getId());
			staff.setName(model.getName());
			staff.setTelephone(model.getTelephone());
			staff.setHaspda(model.getHaspda());
			staff.setStandard(model.getStandard());
			staff.setStation(model.getStation());
			staffService.editStaff(staff);
			jsonObject.put("success", true);
		} catch (Exception e) {
			jsonObject.put("success", false);
			e.printStackTrace();
		}
		try {
			BosUtils.getResponse().getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void listNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		List<Staff> staffs= staffService.findNotDelete(detachedCriteria);
		java2json(staffs, new String[]{"telephone","haspda","deltag","station","standard","decidedzones"});
	}

}
