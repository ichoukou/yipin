package com.ytoxl.module.yipin.content.service.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.content.dataobject.Suggest;
import com.ytoxl.module.yipin.content.mapper.SuggestMapper;
import com.ytoxl.module.yipin.content.service.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService {
	@Autowired
	private SuggestMapper<Suggest> suggestMapper;
	// 自定义分页数量
	@Value("${limit_user_suggest}")
	private Integer defaultLimit;
	@Autowired
	private UserInfoService userInfoService;

	@Override
	public void addSuggest(Suggest suggest) throws YiPinStoreException {
		suggest.setStatus(Suggest.STATUS_UNREAD);
		suggestMapper.add(suggest);
	}

	/**
	 * 分页查询我的发现/吐槽
	 * @param suggestPage
	 * @throws YiPinStoreException
	 */
	@Override
	public void searchSuggests(BasePagination<Suggest> suggestPage)
			throws YiPinStoreException {
		suggestPage.setLimit(defaultLimit);
		CustomUserDetails customUserDetail =userInfoService.checkUserIsLogin();
		if(null!=customUserDetail){
			Map<String, Object> searchParams = suggestPage.getSearchParamsMap();
			searchParams.put("userId", customUserDetail.getUserId());
			if (suggestPage.isNeedSetTotal()) {
				Integer total = suggestMapper.searchSuggestsCount(searchParams);
				suggestPage.setTotal(total);
			}
			Collection<Suggest> result = suggestMapper.searchSuggests(searchParams);
			suggestPage.setResult(result);
		}
	}

	/**
	 * 删除我的发现/吐槽
	 */
	@Override
	public void deleteSuggestById(Integer id) throws YiPinStoreException {
		suggestMapper.del(id);
	}

	/**
	 * 更新我的发现/吐槽信息
	 * @param id
	 * @throws YiPinStoreException
	 */
	@Override
	public void updateSuggestById(Suggest suggest) throws YiPinStoreException {
		suggestMapper.update(suggest);
	}

	@Override
	public Suggest getSuggestById(Integer SuggestId) throws YiPinStoreException {
		return suggestMapper.getSuggestById(SuggestId);
	}

	@Override
	public void searchSuggestsByCondition(BasePagination<Suggest> suggestPage)
			throws YiPinStoreException {
		Map<String, Object> searchParams = suggestPage.getSearchParamsMap();
		if (suggestPage.isNeedSetTotal()) {
			Integer total = suggestMapper.getSuggestsCountByCondition(searchParams);
			suggestPage.setTotal(total);
		}
		Collection<Suggest> result = suggestMapper
				.getSuggestsByCondition(searchParams);
		suggestPage.setResult(result);
	}

	@Override
	public Boolean checkSuggestByUserIdAndContent(Suggest suggest)
			throws YiPinStoreException {
		Boolean flag=false;
		Integer suggestId = suggestMapper.checkSuggestByUserIdAndContent(suggest);
		if(null==suggestId){
			flag=true;
		}
		return flag;
	}
}
