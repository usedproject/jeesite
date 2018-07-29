package com.zhidisoft.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.Workordermanage;
import com.zhidisoft.bos.service.IWorkordermanageService;
import com.zhidisoft.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class WorkOrderManageAction extends BaseAction<Workordermanage> {
	@Resource
	private IWorkordermanageService workordermangeService;
	public String add() throws IOException{
		String f = "1";
		try{
			workordermangeService.save(model);
		}catch(Exception e){
			e.printStackTrace();
			f = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}
	
	public void isExist(){
		String id = model.getId();
		Map<String,Boolean> result=new HashMap<>();
		result.put("result", true);
		java2json(result, new String[]{});
	}
}
