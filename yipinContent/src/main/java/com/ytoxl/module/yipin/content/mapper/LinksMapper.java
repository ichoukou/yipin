package com.ytoxl.module.yipin.content.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.content.dataobject.Links;

/**
 * @作者：罗典
 * @描述：友情链接实体mapper
 * @时间：2013-12-03
 * */
public interface LinksMapper<T extends Links> extends BaseSqlMapper<T> {
	/**
	 * @作者：罗典
	 * @描述：查询友情链接(传入对象为空时，查询所有)
	 * @时间：2013-12-04
	 * */
	public List<Links> queryByLinks(Links links) throws DataAccessException;
	/**
	 * 根据网站名称查询数据
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
	public Links getLinksByName(String name)throws DataAccessException;
}
