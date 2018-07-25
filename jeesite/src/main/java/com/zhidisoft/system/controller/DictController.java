package com.zhidisoft.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.system.entity.SystemDict;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.system.service.SystemDictService;
import com.zhidisoft.utils.JsonResult;

@Controller
@RequestMapping("/system/dict")
public class DictController {
	@Resource
	SystemDictService systemDictService;
	
	/**
	 * 跳转到展示列表的jsp页面
	 * @return
	 */
	@RequestMapping("/toList")
	public String userSearch() {
		return "dictInfo/listDict";
	}
	
	
	/**
	 * 分页查询数据字典
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getDictList(Integer page, Integer rows, String dictName, String dictStatus) {
		List<SystemDict> dictList = systemDictService.getDictList(page, rows, dictName, dictStatus);
		Integer total = systemDictService.getDictCount(dictName, dictStatus);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", dictList);
		map.put("total", total);
		return map;
	} 
	
	/**
	 * 获取数据字典中的所有status
	 */
	@RequestMapping("/listDictStatus")
	@ResponseBody
	public List<Map<String, Object>> listDictStatus() {
		List<String> statusList = systemDictService.getDictStatus();
		List<Map<String ,Object>> list = new ArrayList<Map<String ,Object>>();
		for(String str : statusList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", str);
			list.add(map);
		}
		return list;
	}

	
	/**
	 * 获取数据字典中所有的name
	 * @return
	 */
	@RequestMapping("/listDictName")
	@ResponseBody
	public List<Map<String, Object>> listDictName() {
		List<String> nameList = systemDictService.getDictName();
		List<Map<String ,Object>> list = new ArrayList<Map<String ,Object>>();
		for(String str : nameList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", str);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 跳转到添加数据字典的jsp页面
	 */
	@RequestMapping("/toAddDict")
	public String toAddDict() {
		return "dictInfo/addDict";
	}
	
	/**
	 * 向数据字典中添加记录
	 * @param systemDict
	 * @param session
	 * @return
	 */
	@RequestMapping("/addDict")
	@ResponseBody
	public JsonResult addDict(SystemDict systemDict, HttpSession session) {
		//获取当前session中用户对象
		SystemUser user = (SystemUser) session.getAttribute("user");
		//设定创建者的id
		systemDict.setCreateby(user.getId());
		//设定创建的时间
		systemDict.setCreatetime(new Date());
		Integer i = systemDictService.addDict(systemDict);
		if(i<0) {
			return JsonResult.buildFailureResult("添加失败");
		}
		return JsonResult.buildSuccessResult("添加成功");
	}
	
	/**
	 * 跳转到修改数据字典的jsp页面，将id存放到map中
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEditDict")
	public String toEditDict(Model model, String id) {
		model.addAttribute("id", id);
		return "dictInfo/editDict";
	}
	
	/**
	 * 修改数据字典的记录
	 * @param systemDict
	 * @param id
	 * @return
	 */
	@RequestMapping("/editDict")
	@ResponseBody
	public JsonResult editDict(SystemDict systemDict, String id, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		systemDict.setUpdateby(user.getId());
		systemDict.setUpdatetime(new Date());
		Integer i = systemDictService.editDict(systemDict, id);
		if(i<1) {
			return JsonResult.buildFailureResult("修改失败");
		}
		return JsonResult.buildSuccessResult("修改成功");
	}
	
	/**
	 * 根据id删除数据字典中的记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/removeDict")
	@ResponseBody
	public JsonResult removeDict(String id) {
		Integer i = systemDictService.removeDictById(id);
		if(i<1) {
			return JsonResult.buildFailureResult("删除失败");
		}
		return JsonResult.buildSuccessResult("删除成功");
	}
	

	/**
	 * 这是通过数据字典中的字段进行相关查询的功能，用于页面展示下拉框
	 * @param name
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<SystemDict> selectAll(String name) {
		List<SystemDict> dicts=systemDictService.selectAll(name);
		return dicts;
	}

}
