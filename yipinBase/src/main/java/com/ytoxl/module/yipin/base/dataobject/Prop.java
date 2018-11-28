package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.PropTbl;

/**
 * @作者：罗典
 * @时间：2014-01-10
 * @描述：层级分类属性实体
 * */
public class Prop extends PropTbl {
	// 原产地
	public static final int LEVEL_PLACE = 0;
	// 大区
	public static final int LEVEL_REGION = 1;
	// 省份
	public static final int LEVEL_PROVINCE = 2;
	// 城市
	public static final int LEVEL_CITY = 3;

	// 原产编码
	public static final String CODE_PLACE = "10000";
	// 商品分类
	public static final String CODE_PRODUCT = "10001";
	// 适用人群
	public static final String CODE_PEOPLE = "10002";

	// 有效
	public static final int STATUS_NORMAL = 1;
	// 无效
	public static final int STATUS_INVALID = 2;
	// 父级编码
	public String parentCode;
	// 分页查询起始页
	public Integer start ;
	// 分页查询显示条数
	public Integer limit;
	
	public int getStatusNormal() {
		return Prop.STATUS_NORMAL;
	}

	public int getStatusInvalid() {
		return Prop.STATUS_INVALID;
	}

	public int getLevel_region() {
		return LEVEL_REGION;
	}

	public int getLevel_province() {
		return LEVEL_PROVINCE;
	}

	public int getLevel_city() {
		return LEVEL_CITY;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
