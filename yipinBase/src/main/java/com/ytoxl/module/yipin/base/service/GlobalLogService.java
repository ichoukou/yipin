package com.ytoxl.module.yipin.base.service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;




public interface GlobalLogService {
	
	/**
	 * 记录日志到数据库中
	 * @param logType 日志所属类型
	 * @param logKey 日志关键字
	 * @param logContent 日志内容
	 * @throws YiPinStoreException
	 */
	public void recordLog(Short logType,String logKey,String logContent);
	
	
}
