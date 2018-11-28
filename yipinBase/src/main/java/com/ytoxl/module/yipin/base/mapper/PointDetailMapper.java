package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.PointDetail;

public interface PointDetailMapper<T extends PointDetail> extends BaseSqlMapper<T> {
	/**
	 * 根据积分ID查询积分明细
	 * @param userId	用户ID
	 * @return			积分明细集合
	 */
	public List<PointDetail> listPointDetailsByPointId(Map<String,Object> map) throws DataAccessException;
	/**
	 * 根据积分ID统计积分总数
	 * @param userId	用户ID
	 */
	public Integer countPointDetailsByPointId(Map<String,Object> map) throws DataAccessException;
	
}
