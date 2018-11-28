package com.ytoxl.module.yipin.order.dataobject.resultmap;

public class CartItem {
	private Integer productSkuId; //商品id
	private Integer num;	         //商品数量 
	
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
}
