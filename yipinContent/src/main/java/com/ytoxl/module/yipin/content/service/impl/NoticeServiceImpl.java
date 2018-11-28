package com.ytoxl.module.yipin.content.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Notice;
import com.ytoxl.module.yipin.content.mapper.NoticeMapper;
import com.ytoxl.module.yipin.content.service.NoticeService;

/**
 * 公告service实现
 * 
 * @author qixiaobing
 * 
 */
@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeMapper<Notice> noticeMapper;

	@Override
	public void addNotice(Notice notice) throws YiPinStoreException {
		noticeMapper.add(notice);
	}

	@Override
	public void updateNotice(Notice notice) throws YiPinStoreException {
		noticeMapper.update(notice);
	}
	
	@Override
	public void deleteNoticeByIds(Integer... ids) throws YiPinStoreException {
		noticeMapper.deleteNoticeByIds(ids);
	}

	@Override
	public void updateCheck(Map<String, Object> params)
			throws YiPinStoreException {
		noticeMapper.updateCheck(params);
	}

	@Override
	public Notice getNoticeById(Integer noticeId) throws YiPinStoreException {
		return noticeMapper.get(noticeId);
	}

	@Override
	public void searchNotices(BasePagination<Notice> noticePage)
			throws YiPinStoreException {
		Map<String, Object> paramsMap = noticePage.getSearchParamsMap();
		if (noticePage.isNeedSetTotal()) {
			Integer total = noticeMapper.searchNoticesCount(paramsMap);
			noticePage.setTotal(total);
		}
		noticePage.setResult(noticeMapper.searchNotices(paramsMap));
	}

	@Override
	public List<Notice> getHomePageNotices() throws YiPinStoreException {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("status", 1);
		paramsMap.put("start", 0);
		paramsMap.put("limit", 4);
		return noticeMapper.searchNotices(paramsMap);
	}
	
	@Override
	public void searchNoticeList(BasePagination<Notice> noticePage,int status)
			throws YiPinStoreException {
		Map<String, Object> paramsMap = noticePage.getSearchParamsMap();
		paramsMap.put("status", status);
		paramsMap.put("type", 1);
		if (noticePage.isNeedSetTotal()) {
			Integer total = noticeMapper.searchNoticesCount(paramsMap);
			noticePage.setTotal(total);
		}
		noticePage.setResult(noticeMapper.searchNoticeList(paramsMap));
	}
	
	@Override
	public Notice getPreviousNotice(Integer noticeId,Short type) throws YiPinStoreException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("noticeId", noticeId);
		map.put("type", type);
		return noticeMapper.previousNotice(map);
	}
	
	@Override
	public Notice getNextNotice(Integer noticeId,Short type) throws YiPinStoreException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("noticeId", noticeId);
		map.put("type", type);
		return noticeMapper.nextNotice(map);
	}

	@Override
	public Notice getNoticeCondition(Integer noticeId)
			throws YiPinStoreException {
		return noticeMapper.getNoticeCondition(noticeId);
	}
}
