package com.ytoxl.module.yipin.content.dataobject;

import com.ytoxl.module.yipin.content.dataobject.tbl.HelpTbl;


public class Help extends HelpTbl {
	private String helpCategoryName;
	private String helpName;
	
	public String getHelpName() {
		return helpName;
	}
	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}
	public String getHelpCategoryName() {
		return helpCategoryName;
	}
	public void setHelpCategoryName(String helpCategoryName) {
		this.helpCategoryName = helpCategoryName;
	}
	
	

}
