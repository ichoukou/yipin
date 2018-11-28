package com.ytoxl.module.yipin.content.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.AdvPosition;

/**广告位Service
 * @author zengzhiming
 *
 */
public interface AdvPositionService {
	/** 返回广告位集合
	 * @return
	 */
	public List<AdvPosition> listPosition();

	/** 添加廣告位
	 * @return
	 */
	public int add(AdvPosition adv) throws YiPinStoreException;
	
	/**根據code 查詢廣告位
	 * @return
	 * @throws DataAccessException
	 */
	public AdvPosition  getPositionByCode(String code)throws YiPinStoreException;
}
