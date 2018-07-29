package com.zhidisoft.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.Decidedzone;
import com.zhidisoft.bos.domain.Region;
import com.zhidisoft.bos.domain.Subarea;
import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.service.ISubAreaService;
import com.zhidisoft.bos.service.IUserService;
import com.zhidisoft.bos.utils.BosUtils;
import com.zhidisoft.bos.utils.FileUtils;
import com.zhidisoft.bos.utils.MD5Utils;
import com.zhidisoft.bos.utils.PageBean;
import com.zhidisoft.bos.web.action.base.BaseAction;

import javassist.compiler.ast.NewExpr;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<Subarea> {
	

	@Resource
	private ISubAreaService subAreaService;
	private HSSFSheet sheet;
	
	public String saveSubArea() {
		subAreaService.save(model);
		return "list";
	}
	
	public void listSubArea() {
		//提取参数
		String addresskey = model.getAddresskey();
		Region region = model.getRegion();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.createAlias("region", "r");
		if (StringUtils.isNotBlank(addresskey)) {
			detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		if (region!=null) {
			String city = region.getCity();
			String province = region.getProvince();
			String district = region.getDistrict();
			if (StringUtils.isNotBlank(city)) {
				detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if (StringUtils.isNotBlank(province)) {
				detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if (StringUtils.isNotBlank(district)) {
				detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		subAreaService.listSubArea(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows","detachedCriteria","subareas","decidedzone"});
	}

	public void exportSubArea() {
		List<Subarea> subareas= subAreaService.findAll();
		//读取模板并复制
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/temp/subareaTemp.xls");
		File file=new File("D:\\EnglishEclipse\\bos-parent\\bos-web\\src\\main\\java\\temp\\subareas.xls");
		copy(resourceAsStream, file);
		try {
			HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet styleSheet = workbook.getSheet("style");
			HSSFSheet subareasSheet = workbook.getSheet("subareas");
			Cell yellowCell = getCellByName(workbook, styleSheet,BosUtils.yellowStyle);
			Cell blueCell = getCellByName(workbook,styleSheet, BosUtils.blueStyle);
			CellStyle yellowCellStyle = yellowCell.getCellStyle();
			CellStyle blueCellStyle = blueCell.getCellStyle();
			Map<String, Integer> positionMap= initPosition(workbook,subareasSheet,BosUtils.subareaTempArr);
			int lastRowNum = subareasSheet.getLastRowNum();
			for(int i=0;i<subareas.size();i++){
				HSSFRow row = subareasSheet.createRow(lastRowNum+i+1);
				row.createCell(positionMap.get("single")).setCellValue(subareas.get(i).getSingle());
				row.getCell(positionMap.get("single")).setCellStyle(yellowCellStyle);
				row.createCell(positionMap.get("addresskey")).setCellValue(subareas.get(i).getAddresskey());
				row.getCell(positionMap.get("addresskey")).setCellStyle(blueCellStyle);
				row.createCell(positionMap.get("regionid")).setCellValue(subareas.get(i).getRegion().getId());
				row.getCell(positionMap.get("regionid")).setCellStyle(yellowCellStyle);
				row.createCell(positionMap.get("provice")).setCellValue(subareas.get(i).getRegion().getName());
				row.getCell(positionMap.get("provice")).setCellStyle(blueCellStyle);
				row.createCell(positionMap.get("startnum")).setCellValue(subareas.get(i).getStartnum());
				row.getCell(positionMap.get("startnum")).setCellStyle(yellowCellStyle);
				row.createCell(positionMap.get("position")).setCellValue(subareas.get(i).getPosition());
				row.getCell(positionMap.get("position")).setCellStyle(blueCellStyle);
				row.createCell(positionMap.get("endnum")).setCellValue(subareas.get(i).getEndnum());;
				row.getCell(positionMap.get("endnum")).setCellStyle(yellowCellStyle);
			}
			String filename = "分区数据.xls";
			String contentType = ServletActionContext.getServletContext().getMimeType(filename);
			ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().setContentType(contentType);
			
			//获取客户端浏览器类型
			String agent = ServletActionContext.getRequest().getHeader("User-Agent");
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	private Map<String, Integer> initPosition(HSSFWorkbook workbook,Sheet sheet, String[] subareaTempArr) {
		Map<String, Integer> positionMap=new HashMap<String, Integer>();
		for (String name : subareaTempArr) {
			Cell cell = getCellByName(workbook,sheet, name);
			if (cell!=null) {
				positionMap.put(name, cell.getColumnIndex());
			}
		}
		return positionMap;
	}

	public void copy(InputStream in,File file) {
		try {
			FileOutputStream fileOutputStream=new FileOutputStream(file);
			 byte[] b = new byte[1024];
		        int n=0;
		        while((n=in.read(b))!=-1){
		        	fileOutputStream.write(b, 0, n);
		        }
		        
		        fileOutputStream.close();
		        in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Cell getCellByName(HSSFWorkbook workbook,Sheet sheet,String name) {
		int nameIndex = workbook.getNameIndex(name);
		HSSFName nameAt = workbook.getNameAt(nameIndex);
		AreaReference[] arefs = AreaReference.generateContiguous(nameAt.getRefersToFormula());
		CellReference[] allReferencedCells = arefs[0].getAllReferencedCells();
		int row = allReferencedCells[0].getRow();
		short col = allReferencedCells[0].getCol();
		Cell cell = sheet.getRow(row).getCell(col);
		return cell;
	}
	
	public void ListNotAssociated() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		List<Subarea> subareas = subAreaService.findNotAssociated(detachedCriteria);
		java2json(subareas, new String[]{"decidedzone","startnum","endnum","single","region"});
	}
	
	public void associationSubarea() {
		String id = model.getId();
		List<Subarea> subareas= subAreaService.findAssociatedById(id);
		java2json(subareas, new String[]{"decidedzone","subareaId","subareas"});
	}
	
	public void findSubareasGroupByProvince() {
		List<Object> list=subAreaService.findSubareasGroupByProvince();
		java2json(list, new String[]{});
	}
}
	
	
