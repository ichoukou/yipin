package com.ytoxl.yipin.web.action.content;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Links;
import com.ytoxl.module.yipin.content.service.LinksService;
import com.ytoxl.yipin.web.action.BaseAction;

public class LinksAction extends BaseAction{
	
	private static final long serialVersionUID = -710695379756662711L;
	private static Logger logger = LoggerFactory.getLogger(LinksAction.class);
	@Autowired
	private LinksService linksService;

	private List<Links> linksList;
	
	/**
	 * 查询链接
	 * @return
	 */
	public String listLinks(){
		try {
			   linksList = linksService.queryByLinks(null);
			} catch (YiPinStoreException e) {
				logger.error("查询友情链接出错", e);
		}
		return "listLinks";
	}

	public List<Links> getLinksList() {
		return linksList;
	}

	public void setLinksList(List<Links> linksList) {
		this.linksList = linksList;
	}
	
}
