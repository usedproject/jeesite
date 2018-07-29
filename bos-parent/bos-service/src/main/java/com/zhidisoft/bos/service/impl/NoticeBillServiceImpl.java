package com.zhidisoft.bos.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.crm.CXFService;
import com.zhidisoft.bos.dao.IDecidedZoneDao;
import com.zhidisoft.bos.dao.INoticeBillDao;
import com.zhidisoft.bos.dao.IWorkBillDao;
import com.zhidisoft.bos.domain.Decidedzone;
import com.zhidisoft.bos.domain.Noticebill;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.domain.Workbill;
import com.zhidisoft.bos.service.INoticeBillService;
import com.zhidisoft.bos.utils.BosUtils;

@Service
public class NoticeBillServiceImpl implements INoticeBillService {
	
	@Resource
	private INoticeBillDao noticeBillDao;
	
	@Resource
	private CXFService crmservice;
	
	@Resource
	private IWorkBillDao workBillDao;
	
	@Resource
	private IDecidedZoneDao decidedZoneDao ;
	/**
	 * 保存通知单，并进行自动分单
	 */
	@Transactional(readOnly=false)
	public void save(Noticebill model) {
		noticeBillDao.save(model);
		User user = BosUtils.getLoginUser();
		model.setUser(user);
		//判断是否能够自动搜索到定区编号
		String pickaddress = model.getPickaddress();
		String decidedzoneId = crmservice.findDecidedzoneIdByAddress(pickaddress);
		if (decidedzoneId!=null) {
			//查询到了定区id，可以完成自动分单
			Decidedzone decidedzone = decidedZoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);//业务通知单关联取派员对象
			//设置分单类型为：自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			//为取派员产生一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间
			workbill.setNoticebill(model);//工单关联页面通知单
			workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
			workbill.setRemark(model.getRemark());//备注信息
			workbill.setStaff(staff);//工单关联取派员
			workbill.setType(Workbill.TYPE_1);//工单类型
			workBillDao.save(workbill);
			//调用短信平台，发送短信
		}else{
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}

}
