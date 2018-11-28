package com.ytoxl.module.yipin.order.dataobject.resultmap;

/**
 * 用来在批量导入时记录订单中对应的包裹号及快递公司信息
 * @author Administrator
 *
 */
public class PackageExpress {

	private String packageNo;
	
	private String expressName;
	
	private String expressNo;

	public PackageExpress(){
		
	}
	
	public PackageExpress(String packageNo,String expressName,String expressNo){
		this.packageNo = packageNo;
		this.expressName = expressName;
		this.expressNo = expressNo;
	}
	
	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

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
	
}
