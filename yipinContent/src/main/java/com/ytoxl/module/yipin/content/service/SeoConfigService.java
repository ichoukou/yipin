package com.ytoxl.module.yipin.content.service;

import java.util.List;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.SeoConfig;

/**
 * SEO
 * @author zpf
 *
 */
public interface SeoConfigService {
	/**
	 * 根据urlkey查询seo模板信息
	 * @param urlKey
	 * @param data
	 * @return
	 * @throws YiPinStoreException
	 */
	public String[] getSeoConfigByUrlKey(String urlKey,Object data) throws YiPinStoreException;
	
	/**
	 * 判断urlKey是否重复
	 * @param urlKey
	 * @return boolean true :非重复 false:重复
	 * @throws YiPinStoreException
	 */
	public SeoConfig repeatUrlKey(String urlKey)throws YiPinStoreException;
		

	/**
	 * 查询所有的SEO记录
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<SeoConfig> listSeoConfigs()throws YiPinStoreException;
	
	
	/**
	 * 分页查询SEO
	 * @param seoConfigPage
	 * @throws YiPinStoreException
	 */
	public void searchSeoConfigs(BasePagination<SeoConfig> seoConfigPage) throws YiPinStoreException;
	
	/**
	 * 根据ID删除某条记录
	 * @param seoConfigId
	 * @throws YiPinStoreException
	 */
	public void deleteSeoConfig(Integer seoConfigId)throws YiPinStoreException;
	
	/**
	 * 添加SEO记录
	 * @param seller
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer addSeoConfig(SeoConfig seoConfig) throws YiPinStoreException;
	
	/**
	 * 更新SEO记录
	 * @param seller
	 * @throws YiPinStoreException
	 */
	public void updateSeoConfig(SeoConfig seoConfig) throws YiPinStoreException;
	
	/**
	 * 根据ID获取一条数据
	 * @param seoConfigId
	 * @return
	 * @throws YiPinStoreException
	 */
	public SeoConfig getSeoConfigBySeoConfigId(Integer seoConfigId)throws YiPinStoreException;
}
