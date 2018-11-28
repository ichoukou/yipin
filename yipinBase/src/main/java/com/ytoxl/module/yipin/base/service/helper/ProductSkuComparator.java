package com.ytoxl.module.yipin.base.service.helper;

import java.util.Comparator;

import com.ytoxl.module.yipin.base.dataobject.ProductSku;

public class ProductSkuComparator implements Comparator<ProductSku> {

	@Override
	public int compare(ProductSku o1, ProductSku o2) {
		int result = o1.getUnitPrice().compareTo(o2.getUnitPrice());
		return result;
	}

}
