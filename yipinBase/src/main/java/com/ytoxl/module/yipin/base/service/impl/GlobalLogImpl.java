package com.ytoxl.module.yipin.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ytoxl.module.yipin.base.dataobject.GlobalLog;
import com.ytoxl.module.yipin.base.mapper.GlobalLogMapper;
import com.ytoxl.module.yipin.base.service.GlobalLogService;

@Service
public class GlobalLogImpl implements GlobalLogService {
	protected static Logger log = LoggerFactory.getLogger(GlobalLogImpl.class);
	
	@Autowired
	private GlobalLogMapper<GlobalLog> globalLogMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void recordLog(Short logType, String logKey, String logContent){
		try {
			GlobalLog log = new GlobalLog();
			log.setLogType(logType);
			log.setLogKey(logKey);
			log.setLogContent(logContent);
			globalLogMapper.add(log);
		} catch (Exception e) {
			log.error("日志类型："+logType+ "  日志关键字:"+logKey+" 日志内容："+logContent);
		}
		
	}
}
