package com.ytoxl.module.yipin.content.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.content.dataobject.AdvPosition;

/** 广告位
 * @author zengzhiming
 *
 * @param <T>
 */
public interface AdvPositionMapper  <T extends AdvPosition> extends BaseSqlMapper<T>{
	
	/** 返回广告位集合
	 * @return
	 */
	public List<AdvPosition> listPosition()throws DataAccessException;
	
	/** 添加廣告位
	 * @return
	 */
	public int add(T t) throws DataAccessException;
	
	/**根據code 查詢廣告位
	 * @return
	 * @throws DataAccessException
	 */
	public AdvPosition  getPositionByCode(String code)throws DataAccessException;
}
