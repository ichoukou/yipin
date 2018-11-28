package com.ytoxl.module.yipin.order.dataobject.resultmap;

public class OrderCheckModel {

	private Boolean result;
	private Integer productSkuId;
	private Integer num;
	private String code;
	
	public OrderCheckModel(){}
	
	public OrderCheckModel(Boolean result,Integer productSkuId,Integer num,String code){
		this.result = result;
		this.productSkuId = productSkuId;
		this.num = num;
		this.code = code;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Integer getProductSkuId() {
		return productSkuId;
	}
	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}
