package com.ytoxl.yipin.web.action.content;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Help;
import com.ytoxl.module.yipin.content.dataobject.HelpCategory;
import com.ytoxl.module.yipin.content.service.HelpService;
import com.ytoxl.yipin.web.action.BaseAction;


public class HelpAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1563032240216719019L;
	private static Logger logger = LoggerFactory.getLogger(HelpAction.class);
	private static final String LIST_HELPS = "listHelps";
	
	private List<HelpCategory> helpCategorys;
	private Help help;
	@Autowired
	private HelpService helpService;

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

	public String listHelps(){
		try {
			this.helpCategorys=helpService.listHelpCategorys();
		} catch (YiPinStoreException e) {
			// TODO Auto-generated catch block
			logger.error("获取帮助信息出错",e);
		}
		return LIST_HELPS;
	}
	
	public String updateContent(){
		try {
			helpService.updateContentById(this.help);
			setMessage(Boolean.TRUE.toString(), "更新成功");
		} catch (YiPinStoreException e) {
			// TODO Auto-generated catch block
			logger.error("更新失败",e);
			setMessage(Boolean.FALSE.toString(), "更新失败");
		}
		return JSONMSG;
	}
	
	public String getContentByHelpId(){
		try {
			setMessage(Boolean.TRUE.toString(), helpService.getContentByHelpId(this.getHelp().getHelpId()).getContent());
		} catch (YiPinStoreException e) {
			logger.error("根据id获取帮助内容失败",e);
			setMessage(Boolean.FALSE.toString(), "");
			
		
		}
		return JSONMSG;
	}
	
}
