package com.zhidisoft.bos.service;

import java.util.List;

import com.zhidisoft.bos.domain.Decidedzone;
import com.zhidisoft.bos.utils.PageBean;

public interface IDecidedZoneService {

	void addDecidedZone(Decidedzone model, String[] subareaId);

	void queryByPageBean(PageBean<Decidedzone> pageBean);

}
