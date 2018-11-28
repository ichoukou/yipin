package com.ytoxl.module.yipin.base.dataobject.resultmap;

public class DataItem {
	private String time;     //每条数据的时间
	private String context;  //每条数据状态
	private String operator; //操作人 

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
