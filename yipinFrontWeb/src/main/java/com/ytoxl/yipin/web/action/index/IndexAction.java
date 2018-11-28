package com.ytoxl.yipin.web.action.index;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.oscache.util.StringUtil;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.utils.CookieUtils;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.yipin.web.action.BaseAction;

/** 基本action用于暂时login
 * @author zengzhiming
 *
 */
public class IndexAction extends BaseAction{
 
	private static final long serialVersionUID = -5124613538370970690L;
	private static Logger logger = LoggerFactory.getLogger(IndexAction.class);
	public static final String LOGIN="login";
	public static final String REGISTER="register";
	private String index;
	private String cookieName;
	private String userName;
	private static final String COOKIE_REMOVE_SUCCESS="1";
	private static final String COOKIE_REMOVE_FAILED="2";
	@Autowired
	private UserService userService;
	
	
	/**注册登陆
	 * @return
	 */
	public String show(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//首先注册登陆 需要判断是否已经登陆如果登陆跳转到首页
		try {
			String refererUrl = request.getHeader("Referer");
			String targetUrl = request.getParameter("targetUrl");
			if(StringUtils.isEmpty(refererUrl)||refererUrl.indexOf("/show-register")!=-1||refererUrl.indexOf("/show-login")!=-1){//如果為空 則跳轉到首頁
				refererUrl=request.getContextPath()+"/index.htm";
			}
			if(!StringUtil.isEmpty(targetUrl)){
				ServletActionContext.getRequest().getSession().setAttribute("targetUrl", targetUrl);
			}
			ServletActionContext.getRequest().getSession().setAttribute("url", refererUrl);
			//System.out.println(URLEncoder.encode("http://localhost/front/buy-100006.htm", "UTF-8"));
			CustomUserDetails user = userService.getCurrentUser();
			if(user!=null){//只有当用户存在的情况下
				//用户登陆
				if(index.equals("myyipin")){
					response.sendRedirect(request.getContextPath()+"/order/order-myOrders.htm");
				}else{
					if(!StringUtil.isEmpty(targetUrl)){
						refererUrl = targetUrl;
					}
					response.sendRedirect(refererUrl);
				}
			}
		} catch (YtoxlUserException e) {
			logger.error("用户未登录:",e.getMessage());
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("用户登录跳转异常:",e.getMessage());
			return SUCCESS;
		}finally{
			
		}
		return SUCCESS;
	}
	/**首页 登陆成功
 	 * @return
	 */
	@SuppressWarnings("finally")
	public String index(){
		try {
			HttpServletResponse  response = ServletActionContext.getResponse();
			HttpSession session = ServletActionContext.getRequest().getSession();
			Object obj = session.getAttribute("register");//得到注册时标示
			if(obj!=null){
				session.removeAttribute("register");
				response.sendRedirect(obj.toString());
			}
			String refererUrl = (String) session.getAttribute("url");//获得远程地址
			String targetUrl  = (String) session.getAttribute("targetUrl");//获得目标地址
			if(!StringUtil.isEmpty(targetUrl)){
				refererUrl = targetUrl;
			}
			if(!StringUtil.isEmpty(refererUrl)){
				if(refererUrl.indexOf("/index.htm")<0){
					response.sendRedirect(refererUrl);
				}
			}
			session.removeAttribute("targetUrl");//登陆成功就移除
		} catch (IOException e) {
			logger.error("index IOException",e.getMessage());
		}
		return SUCCESS;//任何异常都跳转进首页
	}
	/**后台添加删除cookie的公共方法 
	 * @return
	 */
	public String cookies(){
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		try {
			if(index.equals("add")){
				CookieUtils.addCookie(response, cookieName, userName, 10*24*60*60);
			}else{
				CookieUtils.removeCookie(request, response, cookieName);
			}
		} catch (Exception e) {
			logger.error("index cookies",e.getMessage());
			setMessage(COOKIE_REMOVE_FAILED, "cookie 删除异常!");
		}
		setMessage(COOKIE_REMOVE_SUCCESS, "cookie 删除成功!");
		return JSONMSG;
	}
 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCookieName() {
		return cookieName;
	}
	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
 
}
