package com.ytoxl.yipin.web.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.content.dataobject.Suggest;
import com.ytoxl.module.yipin.content.service.SuggestService;
import com.ytoxl.yipin.web.action.BaseAction;

public class SuggestAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SuggestAction.class);
	
	private static final String SEARCH_SUGGESTS = "searchSuggests";
	@Autowired
	private SuggestService suggestService;
	@Autowired
	private UserInfoService userInfoService;
	
	private BasePagination<Suggest> suggestPage;
	private Suggest suggest;
	private String nextAction;
	/**
	 * 显示添加发现页面
	 * @return
	 */
	public String editSuggest(){
		return SUCCESS;
	}
	/**
	 * 显示添加吐槽页面
	 * @return
	 */
	public String mySpitslot(){
		return "mySpitslot";
	}
	/**
	 * 分页显示我的发现/我的吐槽 
	 * @return
	 */
	public String searchSuggest(){
		if (suggestPage == null) {
			suggestPage=new BasePagination<Suggest>();
		}
		try {
			suggestService.searchSuggests(suggestPage);
		} catch (YiPinStoreException e) {
			logger.error("分页查询数据错误",e);
		}
		return SEARCH_SUGGESTS;
	}
	
	/**
	 * 判断用户是否登录
	 * @return
	 */
	public CustomUserDetails checkMyLogin(){
		CustomUserDetails customUserDetail=null;
		try {
			customUserDetail = userInfoService.checkUserIsLogin();
		} catch (YiPinStoreException e) {
			logger.error("当前用户没有登录",e.getMessage());
		}
		return customUserDetail;
	}
	
	/**
	 * ajax提交我的发现/吐槽
	 * @return
	 * @throws YiPinStoreException 
	 */
	public String ajaxSaveSuggest(){
		CustomUserDetails customUserDetail=checkMyLogin();
				suggest.setUserId(customUserDetail.getUserId());
				try {
					Boolean flag = suggestService.checkSuggestByUserIdAndContent(suggest);
					if(flag){
						suggestService.addSuggest(suggest);
						if(suggest.getType()==0){
							setMessage("提交发现成功！");
						}else{
							setMessage("提交吐槽成功！");
						}
					}else{
						setMessage("禁止重复提交！");
					}
					
				} catch (YiPinStoreException e) {
					logger.error("添加发现/吐槽失败",e);
				}
		return JSONMSG;
	}

	
	/**
	 * 判断用户是否登陆
	 * @throws YiPinStoreException 
	 */
	public String checkIsLogin(){
		CustomUserDetails detail=checkMyLogin();
		if(detail == null){
			if(suggest.getType()==0){
				setMessage(Boolean.FALSE.toString(), "登录后才可以提交发现哦!");
			}else{
				setMessage(Boolean.FALSE.toString(), "您还没有登录，登录后才可以吐槽哦~");
			}
			
		}else {
			setMessage(Boolean.TRUE.toString(), "");
		}
		return JSONMSG;
	}
	
	public void setSuggest(Suggest suggest) {
		this.suggest = suggest;
	}
	public Suggest getSuggest() {
		return suggest;
	}

	public BasePagination<Suggest> getSuggestPage() {
		return suggestPage;
	}

	public void setSuggestPage(BasePagination<Suggest> suggestPage) {
		this.suggestPage = suggestPage;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
}
