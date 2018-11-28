package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

public class GlobalLogTbl {
	
	protected Integer logId;
	/**日志关键字**/
	protected String logKey;
	/**日志内容**/
	protected String logContent;
	/**日志类型**/
	protected Short logType;
	/**日志创建时间**/
	protected Date logCreateTime;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getLogKey() {
		return logKey;
	}
	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public Short getLogType() {
		return logType;
	}
	public void setLogType(Short logType) {
		this.logType = logType;
	}
	public Date getLogCreateTime() {
		return logCreateTime;
	}
	public void setLogCreateTime(Date logCreateTime) {
		this.logCreateTime = logCreateTime;
	}
	
	
}
