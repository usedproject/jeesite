package com.zhidisoft.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.Region;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.service.IRegionService;
import com.zhidisoft.bos.service.IStaffService;
import com.zhidisoft.bos.utils.BosUtils;
import com.zhidisoft.bos.utils.PageBean;
import com.zhidisoft.bos.utils.PinYin4jUtils;
import com.zhidisoft.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
public class RegionAction extends BaseAction<Region> {
	
	String q;

	public void setQ(String q) {
		this.q = q;
	}
	
	//接收删除的ids
	private String ids;
	
	private File uploadFile;
	
	
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	

	@Resource
	private IRegionService regionService;
	
	public void selectRegion() {
		List<Region> regions=null;
		if (StringUtils.isNotBlank(q)) {
			regions=regionService.findByQ(q);
		}else{
			regions=regionService.findAll();
		}
		java2json(regions, new String[]{"subareas","province","city","district","postcode","shortcode","citycode"});
	}

	public String upload() {
		try {
			if (uploadFile==null) {
				return null;
			}
			HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(uploadFile));
			if (hssfWorkbook.getSheet("region")==null) {
				return null;
			}
			Map<String, Short> initArrar = initArrar(hssfWorkbook);
			HSSFSheet sheet = hssfWorkbook.getSheet("Sheet1");
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			List<Region> regions=new ArrayList<>();
			for(int i=firstRowNum+1;i<=lastRowNum;i++){
				Region region = new Region();
				region.setCity(sheet.getRow(i).getCell(initArrar.get("city")).getStringCellValue());
				region.setDistrict(sheet.getRow(i).getCell(initArrar.get("district")).getStringCellValue());
				region.setId(sheet.getRow(i).getCell(initArrar.get("id")).getStringCellValue());
				region.setPostcode(sheet.getRow(i).getCell(initArrar.get("postcode")).getStringCellValue());
				region.setProvince(sheet.getRow(i).getCell(initArrar.get("provice")).getStringCellValue());
				//处理城市编码以及短码
				String province = sheet.getRow(i).getCell(initArrar.get("provice")).getStringCellValue();
				String city = sheet.getRow(i).getCell(initArrar.get("city")).getStringCellValue();
				String district = sheet.getRow(i).getCell(initArrar.get("district")).getStringCellValue();
				province = province.substring(0, province.length() - 1);
				city = city.substring(0, city.length() - 1);
				district = district.substring(0, district.length() - 1);
				String info = province + city + district;
				String[] headByString = PinYin4jUtils.getHeadByString(info);
				String shortcode = StringUtils.join(headByString);
				//城市编码---->>shijiazhuang
				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
				
				region.setShortcode(shortcode);
				region.setCitycode(citycode);
				regions.add(region);
			}
			if (BosUtils.listIsNotEmpty(regions)) {
				regionService.saveBatch(regions);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
	
	public Map<String, Short> initArrar(HSSFWorkbook hssfWorkbook) {
		Map<String, Short> arrayMap=new HashMap<>();
		String[] strings= BosUtils.regionTempArr;
		for (String s : strings) {
			int nameIndex = hssfWorkbook.getNameIndex(s);
			HSSFName nameAt = hssfWorkbook.getNameAt(nameIndex);
			String refersToFormula = nameAt.getRefersToFormula();
			AreaReference[] generateContiguous = AreaReference.generateContiguous(refersToFormula);
			CellReference[] allReferencedCells = generateContiguous[0].getAllReferencedCells();
			short col = allReferencedCells[0].getCol();
			arrayMap.put(s, col);
		}
		
		return arrayMap;
	}
	
	
	public String listRegion() {
		regionService.pageQuery(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows","detachedCriteria","subareas"});
		return null;
	}
}
