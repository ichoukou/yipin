package com.ytoxl.module.yipin.base.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.FormNumber;

public interface FormNumberMapper<T extends FormNumber> extends BaseSqlMapper<T> {

	public FormNumber getFormNumberByPrefix(String formPrefix) throws DataAccessException;
	
	public Integer updateIndex(@Param("formNumberId") Integer formNumberId,@Param("formIndex") Integer formIndex) throws DataAccessException;;
}
