package com.ytoxl.yipin.web.action.links;

import java.util.List;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Links;
import com.ytoxl.module.yipin.content.service.LinksService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * @作者：罗典
 * @描述：友情链接action
 * @时间：2013-12-04
 * */
@Controller
public class LinksAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LinksAction.class);
	private Links links;
	private String clientid;
	private String rand;
	private String tempName;
	// 查询友情链接的结果集
	private List<Links> linksList;
	@Autowired
	private LinksService linksService;

	/**
	 * @作者：罗典
	 * @描述：页面加载
	 * @时间：2013-12-04
	 * */
	public String searchLinks() {
		Links links = new Links();
		try {
			linksList = linksService.queryByLinks(links);
		} catch (YiPinStoreException e) {
			logger.error("查询友情链接出错", e);
		}
		return SUCCESS;

	}

	/**
	 * @作者：罗典
	 * @描述：保存友情链接
	 * @时间：2013-12-04
	 * */
	public String saveLinks() {
		try {
		  if(links != null && 0 != links.getLinkId()){
				linksService.updateLinksById(links);
		  }else{
			linksService.saveLinks(links);
		  }
		  linksList = linksService.queryByLinks(links);
		} catch (YiPinStoreException e) {
			logger.error("保存友情链接出错", e);
		}
		return SUCCESS;
	}

	/**
	 * @作者：罗典
	 * @描述：根据ID查询友情链接信息
	 * @时间：2013-12-04
	 * */
	public String queryLinksByLinkId() {
		
		try {
			links = linksService.queryLinksById(links.getLinkId());
			if (links != null) {
				String json = JSONObject.fromObject(links).toString();
				setMessage(json);
			}
		} catch (YiPinStoreException e) {
			logger.error("通过id查询友情链接出错", e);
		}
		
		return JSONMSG;
	}

	/**
	 * @作者：罗典
	 * @描述：校验网站名
	 * @时间：2013-12-04
	 * */
	/**
	 * 验证网站名称是否重复
	 * @return
	 */
	public String checkName(){
		try {
			Boolean isUseable = false;
			links = linksService.getLinksByName(links.getName());
			if(links==null){
				links = new Links();
				isUseable = true;
			}
			setMessage(isUseable.toString());
		} catch (YiPinStoreException e) {
			logger.error("校验网站名出错", e);
		}
		return JSONMSG;
	}
	
	/**
	 * @作者：罗典
	 * @描述：删除友情链接
	 * @时间：2013-12-04
	 * */
	public String deleteLinks(){
		try {
			linksService.deleteById(links.getLinkId());
		} catch (YiPinStoreException e) {
			logger.error("删除友情链接出错", e);
		}
		return SUCCESS;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public List<Links> getLinksList() {
		return linksList;
	}

	public void setLinksList(List<Links> linksList) {
		this.linksList = linksList;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public LinksService getLinksService() {
		return linksService;
	}

	public void setLinksService(LinksService linksService) {
		this.linksService = linksService;
	}
}
