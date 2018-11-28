package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.ProductCategoryTbl;


public class ProductCategory extends ProductCategoryTbl{
	protected Integer planNum; //排期个数

	public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}
}
