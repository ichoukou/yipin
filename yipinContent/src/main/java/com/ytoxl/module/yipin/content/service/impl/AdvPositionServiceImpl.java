package com.ytoxl.module.yipin.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.AdvPosition;
import com.ytoxl.module.yipin.content.mapper.AdvPositionMapper;
import com.ytoxl.module.yipin.content.service.AdvPositionService;

/**广告位ServiceImpl
 * @author zengzhiming
 *
 */
@Service
public class AdvPositionServiceImpl implements AdvPositionService{

	@Autowired
	private AdvPositionMapper<AdvPosition> advPositionMapper;
	
	@Override
	public List<AdvPosition> listPosition() {
		return advPositionMapper.listPosition();
	}
	
	/** 添加廣告位
	 * @return
	 */
	public int add(AdvPosition adv) throws YiPinStoreException{
		return advPositionMapper.add(adv);
	}
	
	/**根據code 查詢廣告位
	 * @return
	 * @throws DataAccessException
	 */
	public AdvPosition  getPositionByCode(String code)throws YiPinStoreException{
		return advPositionMapper.getPositionByCode(code);
	}
}
