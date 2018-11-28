package com.ytoxl.module.yipin.content.service;

import java.util.List;
import java.util.Map;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Notice;

public interface NoticeService {
	/**
	 * 新增公告
	 * 
	 * @param notice
	 * @throws YiPinStoreException
	 */
	public void addNotice(Notice notice) throws YiPinStoreException;

	/**
	 * 修改公告信息
	 * 
	 * @param notice
	 * @throws YiPinStoreException
	 */
	public void updateNotice(Notice notice) throws YiPinStoreException;

	/**
	 * 根据ID删除公告
	 * 
	 * @param noticeId
	 * @throws YiPinStoreException
	 */
	public void deleteNoticeByIds(Integer... ids) throws YiPinStoreException;

	/**
	 * 审核公告
	 * 
	 * @param params
	 * @throws YiPinStoreException
	 */
	public void updateCheck(Map<String, Object> params)
			throws YiPinStoreException;

	/**
	 * 分页查询公告信息
	 * 
	 * @param noticePage
	 * @throws YiPinStoreException
	 */
	public void searchNotices(BasePagination<Notice> noticePage)
			throws YiPinStoreException;

	/**
	 * 分页查询公告信息时间排序
	 * 
	 * @param noticePage
	 * @throws YiPinStoreException
	 */
	public void searchNoticeList(BasePagination<Notice> noticePage,int status)
			throws YiPinStoreException;

	/**
	 * 根据公告ID，获取公告信息
	 * 
	 * @param noticeId
	 * @return
	 * @throws YiPinStoreException
	 */
	public Notice getNoticeById(Integer noticeId) throws YiPinStoreException;

	/**
	 * 前台-获取首页公告信息
	 * 
	 * @param noticePage
	 * @throws YiPinStoreException
	 */
	public List<Notice> getHomePageNotices() throws YiPinStoreException;

	/**
	 * 前台-上一篇公告
	 * 
	 * @param noticePage
	 * @throws YiPinStoreException
	 */
	public Notice getPreviousNotice(Integer noticeId,Short type)throws YiPinStoreException;

	/**
	 * 前台-下一篇公告
	 * 
	 * @param noticePage
	 * @throws YiPinStoreException
	 */
	public Notice getNextNotice(Integer noticeId,Short type)throws YiPinStoreException;
	
	/**
	 * 前台-获取公告详细信息
	 * @param noticeId
	 * @return
	 * @throws YiPinStoreException
	 */
	public Notice getNoticeCondition(Integer noticeId)throws YiPinStoreException;
}
