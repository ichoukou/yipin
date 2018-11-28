package com.ytoxl.module.yipin.content.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Links;

/**
 * @作者：罗典
 * @描述：友情链接操作service层
 * @时间：2013-12-03
 * */
public interface LinksService {
	/**
	 * @作者：罗典
	 * @描述：查询友情链接(传入对象为空时，查询所有)
	 * @时间：2013-12-04
	 * */
	public List<Links> queryByLinks(Links links) throws YiPinStoreException;
	
	/**
	 * 
	 * @描述：查询友情链接根据Id
	 * 
	 * */
	public Links queryLinksById(Integer linkId) throws YiPinStoreException;
   
	/**
	 * @作者：罗典
	 * @描述：新增友情链接
	 * @时间：2013-12-04
	 * */
	public void saveLinks(Links links) throws YiPinStoreException;

	public Links getLinksByName(String name) throws YiPinStoreException;
	/**
	 * @作者：罗典
	 * @描述：修改友情链接
	 * @时间：2013-12-04
	 * */
	public int updateLinksById(Links links) throws YiPinStoreException;

	/**
	 * @作者：罗典
	 * @描述：根据ID删除友情链接
	 * @时间：2013-12-04
	 * */
	public int deleteById(Integer linkId) throws YiPinStoreException;
}
