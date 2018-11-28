package com.ytoxl.module.yipin.content.service;

import java.util.List;
import java.util.Map;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Help;
import com.ytoxl.module.yipin.content.dataobject.HelpCategory;


public interface HelpService {
	/**
	 * 查询帮助列表
	 * @param suggestPage
	 * @throws YiPinStoreException
	 */
	public List<HelpCategory> listHelpCategorys() throws YiPinStoreException;
	
	/**
	 * 更新帮助内容
	 * @param id
	 * @throws UhomeStoreException
	 */
	public void updateContentById(Help help) throws YiPinStoreException;
	
	/**
	 * 查询帮助内容
	 * @param id
	 * @throws UhomeStoreException
	 */
	public Help getContentByHelpId(Integer helpId) throws YiPinStoreException;
	
	/**
	 * 商品详情页面  在帮助菜单中抓取 内容
	 * @return
	 * @throws UhomeStoreException
	 */
	public Map<String,List<HelpCategory>> getHelp4ProductDetail() throws YiPinStoreException;
}
