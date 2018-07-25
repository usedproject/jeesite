package com.zhidisoft.business.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.business.entity.Person;
import com.zhidisoft.business.service.PersonService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

@Controller
@RequestMapping("/business/person")
public class PersonController {
	
	@Resource
	PersonService personService;
	/**
	 * 人才页面跳转功能
	 * @return
	 */
	@RequestMapping("/toPerson")
	public String toHuman() {
		return "personInfo/listPerson";
	}
	
	/**
	 * 人才列表分页功能
	 * @param person  前端传入的查询条件封装的结果
	 * @param page		第几页
	 * @param rows		查几行
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(Person person,Integer page,Integer rows) {
		List<Person> persons=personService.getListByPage(person,page,rows);
		Integer total =personService.count(person);
		PageResult pageResult=new PageResult();
		pageResult.setRows(persons);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是完成简历下载模块
	 * @param name			文件名
	 * @param response		响应
	 * @throws IOException  
	 */
	@RequestMapping("/download")
	public void download(String name,HttpServletResponse response) throws IOException{
		String name1=new String(name.getBytes("utf-8"),"iso8859-1");
		response.setHeader("content-disposition", "attachment;filename="+name1);
		response.setContentType("application/octet-stream");
		FileInputStream in=new FileInputStream("F:\\"+name);
		byte[] bs=new byte[1024];
		int len=0;
		ServletOutputStream outputStream = response.getOutputStream();
		while((len=in.read(bs))!=-1){
			outputStream.write(bs, 0, len);
		}
		outputStream.flush();
		outputStream.close();
		in.close();
	}
	/**
	 * 这是增加人才信息的页面跳转功能
	 * @return
	 */
	@RequestMapping("/toAddPerson")
	public String toAddPerson() {
		return "personInfo/addPerson";
	}
	
	@RequestMapping("/selectByIdOrName")
	@ResponseBody
	public JsonResult selectByIdOrName(String name,String idcard) {
		Customer customer=personService.selectByIdOrName(name,idcard);
		if (customer!=null) {
			return JsonResult.buildSuccessResult("", customer);
		}else{
			return JsonResult.buildFailureResult("无此员工");
		}
	}
	
	/**
	 * 这是完成人才模块的增加信息功能
	 * @param person	参数封装到这个对象
	 * @param resume	简历对象
	 * @param session   获得user对象
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(Person person,MultipartFile resume,HttpSession session) throws IllegalStateException, IOException {
		if (resume==null) {
			return JsonResult.buildFailureResult("上传文件失败");
		}
		String idcard = person.getIdcard();
		Person person2=personService.selectByIdCard(idcard);
		if (person2!=null) {
			String resumeurl = person2.getResumeurl();
			File file=new File("F://"+resumeurl);
			file.delete();
			personService.delete(person2);
		}
		String filename = resume.getOriginalFilename();
		resume.transferTo(new File("F://"+filename));
		person.setResumeurl(filename);
		SystemUser user=(SystemUser) session.getAttribute("user");
		person.setCreateby(user.getId());
		int i= personService.add(person);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加成功");
		}else {
			return JsonResult.buildFailureResult("添加失败");
		}
	}
	
	/**
	 * 这是进行编辑页面的跳转功能
	 * @return
	 */
	@RequestMapping("/toEditPerson")
	public String toEditPerson(Integer id,Model model) {
		Person person=personService.selectById(id);
		model.addAttribute("person", person);
		return "personInfo/editPerson";
	}
	
	/**
	 * 这是通过页面进行修改人才信息的功能
	 * @param url
	 * @param resume
	 * @param person
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public JsonResult edit(String url,MultipartFile resume,Person person, HttpSession session) throws IllegalStateException, IOException {
		if (resume.isEmpty()) {
			person.setResumeurl(url);
		}else {
			File file=new File("F://"+url);
			file.delete();
			String filename = resume.getOriginalFilename();
			resume.transferTo(new File("F://"+filename));
			person.setResumeurl(filename);
		}
		SystemUser user=(SystemUser) session.getAttribute("user");
		person.setUpdateby(user.getId());
		int i=personService.update(person);
		if (i>0) {
			return JsonResult.buildSuccessResult("修改成功");
		}else {
			return JsonResult.buildFailureResult("修改失败");
		}
	}
}
