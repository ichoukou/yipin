package com.ytoxl.module.yipin.base.service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Product;


public interface DocNumSystemService {
	
	/**
	 * 各种单据号码生成
	 * @throws UhomeStoreException
	 */
	public String getOrderNum () throws YiPinStoreException;
	
	
}
