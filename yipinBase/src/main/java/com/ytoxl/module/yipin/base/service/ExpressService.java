package com.ytoxl.module.yipin.base.service;

import java.util.List;
import java.util.Map;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;


/**
 * 快递公司
 */
public interface ExpressService {
	/**
	 * 查询所有产品类别信息
	 * @return
	 * @throws YiPinStoreException 
	 */
	public List<Express> listExpresses() throws YiPinStoreException;
	
	/**
	 * 查询运单详细信息
	 * @return
	 * @throws YiPinStoreException 
	 */
	public List<Map<Object,Object>> getExpressDetailInfo(String mailNo) throws YiPinStoreException;
	
	/**
	 * 调用快递100API返回运单信息
	 * @param comCode
	 * @param mailNo
	 * @return
	 */
	public ExpressMessage getExpressDetailInfoFromKuaidi100(String companyCode, String mailNo) throws YiPinStoreException;
}
