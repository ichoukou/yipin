package com.ytoxl.module.yipin.content.dataobject.tbl;

import java.util.Date;

/**
 * 我的吐槽 我的发现
 * 
 * @author lys
 * 
 */
public class SuggestTbl {
	protected Integer suggestId;
	protected Integer userId;
	protected Short type; // 0=我的发现，1=我的吐槽
	protected String content;
	protected Short status; // 1=未读，2=已读,3=禁止,4=已回复
	protected Date createTime;
	protected Integer revertUser; //回复者ID
	protected String revertContent; 
	protected Date revertTime;
	public Integer getSuggestId() {
		return suggestId;
	}
	public void setSuggestId(Integer suggestId) {
		this.suggestId = suggestId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getRevertUser() {
		return revertUser;
	}
	public void setRevertUser(Integer revertUser) {
		this.revertUser = revertUser;
	}
	public String getRevertContent() {
		return revertContent;
	}
	public void setRevertContent(String revertContent) {
		this.revertContent = revertContent;
	}
	public Date getRevertTime() {
		return revertTime;
	}
	public void setRevertTime(Date revertTime) {
		this.revertTime = revertTime;
	}
	

}
