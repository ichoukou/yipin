package com.ytoxl.module.yipin.base.dataobject;

import java.util.Date;
import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.tbl.SaleTbl;

/**
 * @作者：罗典
 * @描述：销售信息实体
 * @时间：2013-12-06
 * */
public class Sale extends SaleTbl {

    public static final Short TYPE_PRE = 1; // 预售
	public static final Short TYPE_SELL = 2; // 销售
	
    public static final Short STATUS_UNRELEASE = 1; // 未发布
	public static final Short STATUS_RELEASE = 2; // 已经发布(预售中)
	public static final Short STATUS_ONSELL = 3; // 销售中
	public static final Short STATUS_OFF_SHELF = 4; //已下架
	
	
	
	// 销售信息列表
	private List<SaleProduct> saleProductList;
	// 所属品牌
	private Brand brand;
	// 查询预售结束时间(仅作查询条件使用)
	private Date endTime;
	// 查询预售起始时间(仅作查询条件使用)
	private Date startTime;
	// 品牌名(仅作关联查询页面展示用)
	private String brandName;
	// 所属城市Id
	private Integer cityId;
	// 销售状态
	private List<Short> statusList;
	
	public short getStatus_unRelease(){
		return STATUS_UNRELEASE;
	}
	
	public short getStatus_Release(){
		return STATUS_RELEASE;
	}
	
	public short getStatus_Onsell(){
		return STATUS_ONSELL;
	}
	
	public short getStatus_off_shelf(){
		return STATUS_OFF_SHELF;
	}
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public List<SaleProduct> getSaleProductList() {
		return saleProductList;
	}
	public void setSaleProductList(List<SaleProduct> saleProductList) {
		this.saleProductList = saleProductList;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public List<Short> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Short> statusList) {
		this.statusList = statusList;
	}
	
}
