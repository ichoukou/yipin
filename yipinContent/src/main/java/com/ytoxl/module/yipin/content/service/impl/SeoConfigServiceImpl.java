package com.ytoxl.module.yipin.content.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.TemplateUtils;
import com.ytoxl.module.yipin.content.dataobject.SeoConfig;
import com.ytoxl.module.yipin.content.mapper.SeoConfigMapper;
import com.ytoxl.module.yipin.content.service.SeoConfigService;
@Service
public class SeoConfigServiceImpl implements SeoConfigService{

	private static Logger logger = LoggerFactory.getLogger(SeoConfigServiceImpl.class);
	@Autowired
	private SeoConfigMapper<SeoConfig> seoConfigMapper;
	@Autowired
	private TemplateUtils templateUtils;
	@Override
	public List<SeoConfig> listSeoConfigs() throws YiPinStoreException {
		return seoConfigMapper.listSeoConfigs();
	}
	@Override
	public void searchSeoConfigs(BasePagination<SeoConfig> seoConfigPage)
			throws YiPinStoreException {
		Map<String, Object> searchParams = seoConfigPage.getSearchParamsMap();
		if(seoConfigPage.isNeedSetTotal()){
			Integer total = seoConfigMapper.searchSeoConfigCount(searchParams);
			seoConfigPage.setTotal(total);
		}
		Collection<SeoConfig> result = seoConfigMapper.searchSeoConfigs(searchParams);
		seoConfigPage.setResult(result);
	}
	@Override
	public void deleteSeoConfig(Integer seoConfigId) throws YiPinStoreException {
		seoConfigMapper.del(seoConfigId);
	}
	@Override
	public Integer addSeoConfig(SeoConfig seoConfig) throws YiPinStoreException {
		return seoConfigMapper.add(seoConfig);
	}
	@Override
	public void updateSeoConfig(SeoConfig seoConfig) throws YiPinStoreException {
		seoConfigMapper.update(seoConfig);
	}

	@Override
	public SeoConfig getSeoConfigBySeoConfigId(Integer seoConfigId)
			throws YiPinStoreException {
		return seoConfigMapper.get(seoConfigId);
	}

	public String[] getSeoConfigByUrlKey(String urlKey, Object data
			) throws YiPinStoreException {
		if(com.ytoxl.module.core.common.utils.StringUtils.isBlank(urlKey)){
			logger.error("参数urlKey异常：{}",urlKey);
			throw new YiPinStoreException("参数异常!");
		}
		SeoConfig seoConfig = null;
		if(urlKey.indexOf("-")>0){
			String urlKey1 = urlKey.split("-")[0];
			String urlKey2 = urlKey.split("-")[1];
			urlKey = urlKey1 + "-" + urlKey2;
		}
		seoConfig = seoConfigMapper.getSeoConfigByUrlKey(urlKey);
		if(seoConfig==null){
			logger.error("seoConfig信息不存在,urlKey：{}",urlKey);
//			throw new YiPinStoreException("seoConfig信息不存在!");
			return null;
		}
		String seoTitle = null;
		try{
		seoTitle = templateUtils.getFreeMarkerText(seoConfig.getTitle(), data);
		}catch(Exception e){
			logger.error("FreeMarker模板seoTitle转换异常：{}",e.getMessage());
		}
		String seoKeyWords = null;
		try{
			seoKeyWords = templateUtils.getFreeMarkerText(seoConfig.getKeyWords(), data);
		}catch(Exception e){
			logger.error("FreeMarker模板KeyWords转换异常：{}",e.getMessage());
		}
		String seoDescription = null;
		try{
			seoDescription = templateUtils.getFreeMarkerText(seoConfig.getDescription(), data);
		}catch(Exception e){
			logger.error("FreeMarker模板seoDescription转换异常：{}",e.getMessage());
		}
		return new String[]{seoTitle,seoKeyWords,seoDescription};
	}
	@Override
	public SeoConfig repeatUrlKey(String urlKey) throws YiPinStoreException {
		SeoConfig seoConfig = seoConfigMapper.getSeoConfigByUrlKey(urlKey);
		return seoConfig;
	}

	
}
