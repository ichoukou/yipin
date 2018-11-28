package com.ytoxl.module.yipin.order.dataobject.resultmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于批量导入记录订单号以及订单状态是否需要更新
 * @author Administrator
 *
 */
public class OrderPackageExpressInfo {

	private String orderNo;
	
	private String expressName;
	
	private String expressNo;
	
//	private List<PackageExpress> packageList = new ArrayList<PackageExpress>();

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

//	public List<PackageExpress> getPackageList() {
//		return packageList;
//	}
//
//	public void setPackageList(List<PackageExpress> packageList) {
//		this.packageList = packageList;
//	}
}
