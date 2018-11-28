package com.ytoxl.module.yipin.base.dataobject;

import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.tbl.SkuOptionTbl;

public class SkuOption extends SkuOptionTbl {
	
	//前台商品详细信息展示  颜色和规格
	public static String SKUOPTION_WT = "skuOptionWt";
	public static String SKUOPTION_SPECIFICATION="skuOptionSpecification";
	
	/**规格ID**/
	public static final Integer SKUOPTION_SPECIFICATION_ID = 100000;
	/**重量ID**/
	public static final Integer SKUOPTION_WEIGHT_ID = 100001;
	
	protected List<SkuOptionValue> skuOptionValues;
	
	public List<SkuOptionValue> getSkuOptionValues() {
		return skuOptionValues;
	}

	public void setSkuOptionValues(List<SkuOptionValue> skuOptionValues) {
		this.skuOptionValues = skuOptionValues;
	}

}
