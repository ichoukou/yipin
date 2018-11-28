package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.SkuOptionValue;


/**
 * 产品SKU
 * @author huayucai
 * 
 * @param <T>
 */
public interface SkuOptionValueMapper<T extends SkuOptionValue> extends BaseSqlMapper<T> {
	

	/**
	 * 查询产品规格值
	 * @param skuOptionsId
	 * @return
	 */
	public List<SkuOptionValue> listSkuOptionValues(Integer skuOptionId) throws DataAccessException;
	
}
