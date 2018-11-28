package com.ytoxl.module.yipin.content.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.content.dataobject.Suggest;

public interface SuggestMapper <T extends Suggest> extends BaseSqlMapper<T> {
	/**
	 * 分页查询建议
	 * @param map
	 * @return
	 */
	public List<Suggest> searchSuggests(Map<String, Object> searchParams) throws DataAccessException;
	public Integer searchSuggestsCount(Map<String, Object> searchParams) throws DataAccessException;
	
	/**
	 * 管理页面 根据条件检索 吐槽/发现
	 * @param map
	 * @return
	 */
	public List<Suggest> getSuggestsByCondition(Map<String, Object> searchParams) throws DataAccessException;
	public Integer getSuggestsCountByCondition(Map<String, Object> searchParams) throws DataAccessException;
	
	/**
	 * 根据ID获取 吐槽/发现
	 * @param SuggestId
	 * @return
	 * @throws DataAccessException
	 */
	public Suggest getSuggestById(Integer SuggestId)throws DataAccessException;
	
	/**
	 * 禁止重复提交内容
	 * @param SuggestId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer checkSuggestByUserIdAndContent(Suggest suggest)throws DataAccessException;
	
}
