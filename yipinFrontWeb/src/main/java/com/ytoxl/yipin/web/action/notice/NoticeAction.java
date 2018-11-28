package com.ytoxl.yipin.web.action.notice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Notice;
import com.ytoxl.module.yipin.content.dataobject.Help;
import com.ytoxl.module.yipin.content.dataobject.HelpCategory;
import com.ytoxl.module.yipin.content.service.HelpService;
import com.ytoxl.module.yipin.content.service.NoticeService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * @author qixiaobing
 * 
 */
@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger(NoticeAction.class);
	private static final String NOTICE_LIST = "noticeList";

	@Autowired
	private HelpService helpService;

	@Autowired
	private NoticeService noticeService;

	private List<HelpCategory> helpCategorys;
	private Help help;

	/** 公告分页 */
	private BasePagination<Notice> noticePage;

	private Integer noticeId;

	private Notice notice;

	private Notice previousNotice;

	private Notice nextNotice;

	// 列表显示公告
	public String noticeList() {
		if (noticePage == null) {
			noticePage = new BasePagination<Notice>();
		}
		try {

			this.helpCategorys = helpService.listHelpCategorys();
			if (this.help != null && this.help.getHelpId() != null) {
				this.help = helpService.getContentByHelpId(this.help
						.getHelpId());
			}
			noticePage.setLimit(Notice.LIMIT_NOTICE);
			noticeService.searchNoticeList(noticePage, Notice.CHECKED_YES);
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage());
		}
		return NOTICE_LIST;
	}

	public String topNotice() {
		return "";
	}

	/**
	 * 获取公告详细信息
	 * 
	 * @return
	 */
	public String noticeDetail() {
		try {
			// 根据ID获取公告详细信息
			notice = noticeService.getNoticeCondition(noticeId);

			if (notice == null) {
				return ERROR;
			}

			this.helpCategorys = helpService.listHelpCategorys();
			if (this.help != null && this.help.getHelpId() != null) {
				this.help = helpService.getContentByHelpId(this.help
						.getHelpId());
			}

			// 获取上一篇公告信息
			previousNotice = noticeService.getPreviousNotice(noticeId,
					notice.getType());

			// 获取下一篇公告信息
			nextNotice = noticeService
					.getNextNotice(noticeId, notice.getType());

			// 获取公告内容
			if (notice.getContent() != null) {
				int imgNo = notice.getContent().indexOf("img");
				if (imgNo != -1) {
					int begin = notice.getContent().indexOf("src=\"", imgNo) + 5;
					int end = notice.getContent().indexOf("\"", begin + 5);
					if (begin > 4) {
						notice.setImgUrl(notice.getContent().substring(begin,
								end));
					}
				}
			}
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage());
		}
		return "noticeDetail";
	}

	public BasePagination<Notice> getNoticePage() {
		return noticePage;
	}

	public void setNoticePage(BasePagination<Notice> noticePage) {
		this.noticePage = noticePage;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Notice getPreviousNotice() {
		return previousNotice;
	}

	public void setPreviousNotice(Notice previousNotice) {
		this.previousNotice = previousNotice;
	}

	public Notice getNextNotice() {
		return nextNotice;
	}

	public void setNextNotice(Notice nextNotice) {
		this.nextNotice = nextNotice;
	}

	public List<HelpCategory> getHelpCategorys() {
		return helpCategorys;
	}

	public void setHelpCategorys(List<HelpCategory> helpCategorys) {
		this.helpCategorys = helpCategorys;
	}

	public Help getHelp() {
		return help;
	}

	public void setHelp(Help help) {
		this.help = help;
	}

}
