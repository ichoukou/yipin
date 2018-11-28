package com.ytoxl.yipin.web.action.content;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Point;
import com.ytoxl.module.yipin.base.dataobject.PointDetail;
import com.ytoxl.module.yipin.base.service.PointService;
import com.ytoxl.module.yipin.content.dataobject.Suggest;
import com.ytoxl.module.yipin.content.service.SuggestService;
import com.ytoxl.yipin.web.action.BaseAction;

public class SuggestAction extends BaseAction {

	private static final long serialVersionUID = 1563032240216719019L;
	private static Logger logger = LoggerFactory.getLogger(SuggestAction.class);
	private static final String SEARCH_TSUKKOMI = "result_tsukkomi";
	private static final String SEARCH_DISCOVER = "result_discover";
	private BasePagination<Suggest> suggestPage;
	private Integer suggestId;
	private Short status;
	private String nextAction;
	private PointDetail pointDetail;
	@Autowired
	private PointService pointService;

	@Autowired
	private SuggestService suggestService;
	@Autowired
	private UserService userService;
	private Suggest suggest;

	/**
	 * 检索吐槽/发现
	 * 
	 * @return
	 */
	public String searchSuggests() {
		String result = SEARCH_TSUKKOMI;
		
		try {
			// 获取检索条件
			// 首次进入页面场合
			if (suggestPage == null) {
				suggestPage = new BasePagination<Suggest>();
				Map<String, String> searchParams = new HashMap<String, String>();
				searchParams.put("type",
						String.valueOf(Suggest.TYPE_MYSPLIT_SUGGEST));
				searchParams.put("pointSource",
						String.valueOf(PointDetail.POINT_SOURCE_TSUKKOMI));
				searchParams.put("status", null);
				suggestPage.setParams(searchParams);
			}

			// 非首次进入页面场合
			else if (suggestPage != null
					&& StringUtils.isNotBlank(suggestPage.getParams().get(
							"type"))) {

				// 进入吐槽页面场合
				if (suggestPage.getParams().get("type")
						.equals(String.valueOf(Suggest.TYPE_MYSPLIT_SUGGEST))) {
					suggestPage.getParams().put("pointSource",
							String.valueOf(PointDetail.POINT_SOURCE_TSUKKOMI));
				}

				// 进入发现页面场合
				else {
					suggestPage.getParams().put("pointSource",
							String.valueOf(PointDetail.POINT_SOURCE_DISCOVER));
					result = SEARCH_DISCOVER;
				}
			}

			// 根据条件获取检索结果集
			suggestService.searchSuggestsByCondition(suggestPage);

		} catch (YiPinStoreException e) {
			logger.error("分页查询我的发现/吐槽:", e);
		}
		return result;
	}

	/**
	 * 修改吐槽/发现状态
	 * 
	 * @return
	 */
	public String updateSuggestStatus() {

		try {
			// 获取当前登录用户
			CustomUserDetails detail = userService.getCurrentUser();
			// 获取修改对象
			if (suggest == null) {
				setMessage(Boolean.FALSE.toString(), "操作失败！");
				return JSONMSG;
			}

			// 根据ID获取记录
			Suggest bean = new Suggest();
			bean = suggestService.getSuggestById(suggest.getSuggestId());

			// 当记录不是禁止状态时做状态更新操作
			if (Suggest.STATUS_DEL.equals(bean.getStatus())) {
				setMessage(Boolean.TRUE.toString());
				return JSONMSG;
			}

			// 已回复的吐槽不做已读操作
			else if (Suggest.STATUS_READ.equals(suggest.getStatus())
					&& Suggest.STATUS_REVERT.equals(bean.getStatus())) {
				setMessage(Boolean.TRUE.toString());
				return JSONMSG;
			} else {

				// 做回复操作场合
				String content = null;
				if (StringUtils.isNotBlank(suggest.getRevertContent())) {
					suggest.setStatus(Suggest.STATUS_REVERT);
					content = StringEscapeUtils.escapeHtml(suggest.getRevertContent());
				}

				// 设置回复人ID
				suggest.setRevertUser(detail.getUserId());

				// 更新记录
				suggestService.updateSuggestById(suggest);

				// 设置成功返回信息
				setMessage(Boolean.TRUE.toString(), content == null?"操作成功！":content);
			}

		} catch (YiPinStoreException e) {
			logger.error("吐槽/发现更新：", e);

			// 设置失败返回信息
			setMessage(Boolean.FALSE.toString(), "操作失败！");
		} catch (YtoxlUserException e) {
			logger.error("获取当前登陆信息异常：", e);
		}
		return JSONMSG;
	}

	/**
	 * 添加积分
	 * 
	 * @return
	 */
	public String addPointDetail() {

		// 获取新增对象
		if (suggest == null) {
			setMessage(Boolean.FALSE.toString(), "操作失败！");
			return JSONMSG;
		}
		try {

			// 添加积分
			pointService
					.addPointByUserId(suggest.getUserId(),
							Suggest.TYPE_MYSPLIT_SUGGEST.equals(suggest
									.getType()) ? Point.POINTSOURCE_SPLIT
									: Point.POINTSOURCE_FIND, suggest
									.getPoint(), Point.TYPE_ADD, suggest
									.getSuggestId());

			// 设置成功返回信息
			setMessage(Boolean.TRUE.toString(), "操作成功！");
		} catch (YiPinStoreException e) {

			// 设置失败返回信息
			setMessage(Boolean.TRUE.toString(), "操作失败！");
		}
		return JSONMSG;
	}

	/********************** GET && SET ***************************/

	public BasePagination<Suggest> getSuggestPage() {
		return suggestPage;
	}

	public void setSuggestPage(BasePagination<Suggest> suggestPage) {
		this.suggestPage = suggestPage;
	}

	public SuggestService getSuggestService() {
		return suggestService;
	}

	public void setSuggestService(SuggestService suggestService) {
		this.suggestService = suggestService;
	}

	public Integer getSuggestId() {
		return suggestId;
	}

	public void setSuggestId(Integer suggestId) {
		this.suggestId = suggestId;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public PointDetail getPointDetail() {
		return pointDetail;
	}

	public void setPointDetail(PointDetail pointDetail) {
		this.pointDetail = pointDetail;
	}

	public Suggest getSuggest() {
		return suggest;
	}

	public void setSuggest(Suggest suggest) {
		this.suggest = suggest;
	}

}
