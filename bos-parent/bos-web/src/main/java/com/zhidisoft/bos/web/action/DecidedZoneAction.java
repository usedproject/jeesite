package com.zhidisoft.bos.web.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.crm.CXFService;
import com.zhidisoft.bos.crm.Customer;
import com.zhidisoft.bos.domain.Decidedzone;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.service.IDecidedZoneService;
import com.zhidisoft.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<Decidedzone> {
	

	@Resource
	private IDecidedZoneService decidedZoneService;
	@Resource
	private CXFService crmService;
	
	private String[] subareaId;
	
	public String[] getSubareaId() {
		return subareaId;
	}


	private List<String> customerIds;
	
	
	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}


	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}


	public void addDecidedZone() {
		decidedZoneService.addDecidedZone(model,subareaId);
	}
	
	public void listDecidedZone() {
		decidedZoneService.queryByPageBean(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows","detachedCriteria","subareas","decidedzones"});
	}
	
	public void findNoAssociated() {
		List<Customer> list = crmService.findNoSocciated();
		java2json(list, new String[]{"decidedzoneId","address","station"});
	}
	
	public void findAssociated() {
		String id = model.getId();
		List<Customer> list = crmService.findSocciatedById(id);
		java2json(list, new String[]{"decidedzoneId","address","station"});
	}
	
	public String assigncustomerstodecidedzone() {
		String id = model.getId();
		crmService.assigncustomerstodecidedzone(id, customerIds);
		return "list";
	}
	
	public void associationCustomer() {
		String id = model.getId();
		List<Customer> customers = crmService.findSocciatedById(id);
		if (customers==null) {
			customers=new ArrayList<Customer>();
		}
		java2json(customers, new String[]{"address","decidedzoneId","telephone"});
	}
}
	
	
