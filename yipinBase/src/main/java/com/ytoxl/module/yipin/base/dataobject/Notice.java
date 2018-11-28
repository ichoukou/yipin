package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.NoticeTbl;

public class Notice extends NoticeTbl{
	public static final Short TYPE_REBATE = 2;	//打拆公告
	public static final Short TYPE_NETWORK = 1;	//网站公告
	public static final Short CHECKED_YES = 1;	//已审核
	public static final Short CHECKED_NO = 0;	//未审核
	public static final Short DELETED=1;        //已删除
	public static final Short NOT_DELETE=0;     //未删除
	public static final Short TYPE_1=0;        //1-网站公告；
	public static final Short TYPE_2=1;     //2-打折预报；
	public static final Short TOP_LIMIT=1;     //获取首个公告
	public static final int LIMIT_NOTICE=20;
	public static final String DEFAULT_TIME = "0000-00-00 00:00:00"; //默认时间
	private String userName;     //用户名
	private String imgUrl;	 //图片地址
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
