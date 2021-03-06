package com.djcps.wms.sysurl.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.sysurl.dao.SysUrlDao;
import com.djcps.wms.sysurl.model.SysUrlPO;
import com.djcps.wms.sysurl.service.SysUrlService;
import com.google.gson.Gson;


/**
 * 系统url业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月13日
 */
@Service
public class SysUrlServiceImpl implements SysUrlService {
	
	@Autowired
	private SysUrlDao sysUrlDao;
	
	@Override
	public int batchInsertSysUrl(List<SysUrlPO> sysUrlList) {
		return sysUrlDao.batchInsertSysUrl(sysUrlList);
	}

	@Override
	public int updateSysUrl(SysUrlPO sysUrlList) {
		return sysUrlDao.updateSysUrl(sysUrlList);
	}

	@Override
	public List<SysUrlPO> getALLSysUrl() {
		return sysUrlDao.getALLSysUrl();
	}

	@Override
	public int batchReplaceSysUrl(List<SysUrlPO> sysUrlList) {
		return sysUrlDao.batchReplaceSysUrl(sysUrlList);
	}

	@Override
	public int batchUpdateSysUrl(List<SysUrlPO> sysUrlList) {
		return sysUrlDao.batchUpdateSysUrl(sysUrlList);
	}


}
