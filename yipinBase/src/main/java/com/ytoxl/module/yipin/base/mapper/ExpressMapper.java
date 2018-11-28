package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.Express;

/**
 * @author wangguoqing
 *
 */
public interface ExpressMapper<T extends Express> extends BaseSqlMapper<T> {
	/**
	 * 查询快递公司数据
	 * @param productId
	 * @return
	 */
	public List<Express> listExpresses() throws DataAccessException;
	
	/**
	 * 根据快递公司名称查询
	 * @param expressName
	 * @return
	 * @throws DataAccessException
	 */
	public List<Express> searchExpress(@Param("expressName") String expressName) throws DataAccessException;
}
