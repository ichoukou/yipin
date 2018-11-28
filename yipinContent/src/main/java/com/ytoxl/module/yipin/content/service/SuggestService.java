package com.ytoxl.module.yipin.content.service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Suggest;

public interface SuggestService {
	
	/**
	 * 添加我的发现/吐槽
	 * @param suggest
	 * @throws YiPinStoreException
	 */
	public void addSuggest(Suggest suggest) throws YiPinStoreException;
	
	/**
	 * 分页查询我的发现/吐槽
	 * @param suggestPage
	 * @throws YiPinStoreException
	 */
	public void searchSuggests(BasePagination<Suggest> suggestPage) throws YiPinStoreException;
	
	/**
	 * 删除我的发现/吐槽
	 * @param id
	 * @throws YiPinStoreException
	 */
	public void deleteSuggestById(Integer id)throws YiPinStoreException;
	
	/**
	 * 更新我的发现/吐槽信息
	 * @param suggest
	 * @throws YiPinStoreException
	 */
	public void updateSuggestById(Suggest suggest) throws YiPinStoreException;
	
	/**
	 * 根据ID获取 吐槽/发现
	 * @param SuggestId
	 * @return
	 * @throws YiPinStoreException
	 */
	public  Suggest  getSuggestById(Integer SuggestId)throws YiPinStoreException;

	/**
	 * 管理页面分页查询我的发现/吐槽
	 * @param suggestPage
	 * @throws YiPinStoreException
	 */
	public void searchSuggestsByCondition(BasePagination<Suggest> suggestPage) throws YiPinStoreException;
	
	/**
	 * 禁止重复提交内容
	 * @param suggest
	 * @return
	 * @throws YiPinStoreException
	 */
	public Boolean checkSuggestByUserIdAndContent(Suggest suggest)throws YiPinStoreException;
	
}
