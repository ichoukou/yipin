package com.ytoxl.yipin.web.action.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.code.jcaptcha4struts2.core.validation.JCaptchaValidator;
import com.opensymphony.oscache.util.StringUtil;
import com.ytoxl.module.core.common.utils.EncodeUtils;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.common.utils.Md5EncryptionUtils;
import com.ytoxl.module.user.dataobject.Urole;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UroleService;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.CookieUtils;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.dataobject.MailTemplate;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.content.service.SendMailService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * UserAction用户
 * 
 * @author user
 * 
 */
public class UserAction extends BaseAction {
	/**	 */
	private static final long serialVersionUID = -7725629863752967951L;
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	private static final String user_stuas = "0";
	private static final String Status_deniy = "1";// 账号被禁用被禁用 、用户的账号重复
	private static final String pass_img_deniy = "3";// 验证码不正确
	private static final String pass_img_access = "4";// 验证码通过
	private static final String pass_staus_deniy = "5";// 密码不正确、用户的邮箱不正确
	private static final String pass_name_access = "6";// 验证全部通过 、重置密码成功
	private static final String Status_deniy_lgoin = "8";// 禁止登陆
	private static final String pass_name_excption = "7";// 注册异常
	private static final String role_error = "9";//角色错误
	private static final String quotes = "\"";
	private String remeberName;
	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SendMailService sendEmailService;
	@Autowired
	private UroleService uroleService;
	private UserInfo userinfo;

	/** 判断错误次数 */
	private Integer opertNum;
	/** 用户名 */
	private String username;
	/** 盐值 */
	@Value("${password_salt}")
	private String passwordSaltAction;
	/** 密码 */
	private String password;
	/** 新密码 */
	private String newPassword;
	private String newPswEn;

	/** 验证码 */
	private String jCaptchaResponse;
	/** 邮箱 */
	private String email;
	/** 邮件地址 */
	@Value("${jspvar._domain}")
	private String mailUrl;

	@Value("${jspvar._domainName}")
	private String domainName;
	/** 发送邮件的时间 */
	private String sendTime;
	@Value("${resetPasswordTime}")
	private String resetPasswordTime;
	/** 找回密码 提示信息 */
	private String findPasswordInfo;
	private String active;

	// private String urlBeforeLogin;

	//---------------第三方登录-----------
	/**账号重复 */
	public static final String ACCOUNT_REPETITION="2";
	/**账号异常*/
	public static final String ACCOUNT_EXCEPTION="1";
	/**账号注册成功*/
	public static final String ACCOUNT_REGISTER_SUCCESS="6";
	/**验证码	 */
	public static final String VERIFICATION_ERROR="5";
	private String openId;
	private String nickName;
	private String thirdType;//0-QQ 1-微博 2-微信 3-来往 4-易信
	
	/**
	 * 登陆
	 * 
	 * @return
	 */
	public String checkUserName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if (opertNum > 3) {
				// ServletActionContext.getRequest().setAttribute("jCaptchaResponse",
				// jCaptchaResponse);
				boolean flag = JCaptchaValidator.validate();
				if (flag) {
					setMessage(pass_img_access);// 验证码输入正确 4
				} else {
					setMessage(pass_img_deniy);// 输入的验证码不正确3
					return JSONMSG;
				}
			}
			username = StringUtils.trimToEmpty(username);
			User user = userService.getByName(username);
			if (user == null) {
				setMessage(user_stuas);// 账号不存在
				return JSONMSG;
			}
			if (user.getStatus().equals(user_stuas)) {
				setMessage(Status_deniy);// 账号被禁用
				return JSONMSG;
			}
			if (user.getCreateByUserId() != 0) {
				setMessage(Status_deniy_lgoin);// 账号不能登录
				return JSONMSG;
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				if (!Md5EncryptionUtils.MD5SaltPassword(password,
						passwordSaltAction).equals(user.getPassword())) {
					setMessage(pass_staus_deniy);// 密码不正确
					return JSONMSG;
				}
			}
			List<Integer> roles = uroleService.listNormalUroleIdsByUserId(user.getUserId());
			if(!roles.contains(UserInfo.USER_ROLE_BUYER)){
				setMessage(role_error);//角色不对
				return JSONMSG;
			}
			setMessage(pass_name_access);
		} catch (Exception e) {
			logger.error("checkUserName Exception", e.getMessage());
			setMessage(pass_name_excption);
			return JSONMSG;
		}
		//处理引号
		logger.error("判断引号 :",username);
		if((username.indexOf(quotes)==0)&&(username.lastIndexOf(quotes)==username.length()-1)){
			username = username.substring(1,username.length()-1);
		}
		if (!StringUtils.isEmpty(remeberName)) {
			logger.error("cookie生成之前 :",username);
			try {
				CookieUtils.addCookie(response, "yipinusername",URLEncoder.encode(username, "utf-8"),10 * 24 * 60 * 60);
			} catch (UnsupportedEncodingException e) {
				logger.error("cookie记住用户名失败 :",e);
			}
		} else {
			CookieUtils.removeCookie(request, response, "yipinusername");
		}
		return JSONMSG;
	}
	/**
	 * 我的资料
	 * 
	 * @return
	 */
	public String userInfo() {
		try {
			// 获取当前用户id
			Integer userId = userService.getCurrentUser().getUserId();
			userinfo = userInfoService.getUserInfoById(userId);
			username = userService.getCurrentUser().getUsername();
		} catch (YtoxlUserException e) {
			logger.error("UserAction userInfo YtoxlUserException :",
					e.getMessage());
		} catch (YiPinStoreException e) {
			logger.error("UserAction userInfo YiPinStoreException :",
					e.getMessage());
		}
		return "userInfo";
	}

	/**
	 * 获取服务条款
	 * 
	 * @return
	 */
	public String getServiceTerms() {
		return "getServiceTerms";
	}
	/**
	 * 更新密码
	 * 
	 * @return
	 */
	public String updatePassword() throws YtoxlUserException {
		try {
			// 获取当前用户userName
			User user = userService.getByName(userService.getCurrentUser()
					.getUsername());
			if (StringUtils.isNotEmpty(password)
					&& StringUtils.isNotEmpty(user.getPassword())
					&& StringUtils.isNotEmpty(newPassword)) {
				String uActive = Md5EncryptionUtils.MD5SaltPassword(password,
						passwordSaltAction);
				if (uActive.equals(user.getPassword())) {
					userService
							.updatePswByUserId(user.getUserId(), newPassword);

					setMessage("status", "success");
					return JSONMSG;
				}
			}

		} catch (NumberFormatException n) {
			logger.error("修改密码是转换数据异常:", n.getMessage());
			setMessage("editPwdErr", "密码修改失败");
			return JSONMSG;
		} catch (Exception e) {
			logger.error("密码修改失败:", e.getMessage());
			setMessage("editPwdErr", "密码修改失败");
			return JSONMSG;
		}
		return JSONMSG;

	}

	public String changePsw(){
		return "changePsw";
	}

	// 修改用户信息 和用户详细详细信息
	public String updateUserInfo() {
		try {
			// 判断当前用户是否登录 并且只能修改自己的信息
			CustomUserDetails customUserDetail = userService.getCurrentUser();
			if (userinfo != null) {
				if (customUserDetail != null
						&& customUserDetail.getUserId().equals(
								userinfo.getUserId())) {
					// 当前角色是买家
					List<Urole> uroles = customUserDetail.getUroles();
					boolean isUpdate = true;
					for (Urole urole : uroles) {
						if (!urole.getUroleId()
								.equals(UserInfo.USER_ROLE_BUYER)) {
							isUpdate = false;
						}
					}
					if (isUpdate) {
						userInfoService.updateUserAndUserInfo(userinfo);
						return "userInfoSuccess";
					}
				}
			}
			setMessage("更新失败");
		} catch (YiPinStoreException e) {
			logger.error("更新失败,", e.getMessage());
			return "userInfo";
		} catch (YtoxlUserException e) {
			logger.error("更新失败,", e.getMessage());
			return "userInfo";
		}
		return "userInfo";
	}

	public boolean validateEqualsPwd(String newActive, String uActiveEn) {
		if (StringUtil.isEmpty(newActive) || StringUtil.isEmpty(uActiveEn)) {
			return false;
		} else {
			String newAct = Md5EncryptionUtils.MD5SaltPassword(newPassword,
					passwordSaltAction);
			String uActEn = Md5EncryptionUtils.MD5SaltPassword(newPswEn,
					passwordSaltAction);
			if (newAct.equals(uActEn)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 找回密码首页
	 * 
	 * @return
	 */
	public String findPassWord() {
		return "passWordstep1";
	}
	
	/**
	 * 找回密码验证账号
	 * @return
	 */
	public String ajaxCheckEmail(){
		CustomUserDetails customUserDetail = null;
		try {
			customUserDetail = userService.getCurrentUser();
		} catch (YtoxlUserException e1) {
			logger.error("找回密码验证账户用户登录", e1);
		}
		// 找回密码 第二步如果用户没有登录 执行登录流程
		if (customUserDetail == null) {
			if (userService.repeatUsername(username)) {
				setMessage(Boolean.FALSE.toString(), "该用户不存在");
			} else {
				User user = userService.getByName(username);
				email = user.getEmail();
				if (StringUtils.isEmpty(email)) {
					setMessage(Boolean.FALSE.toString(), "您没有设置找回密码邮箱，请联系客服！");
				} else {
					setMessage(Boolean.TRUE.toString(), "");
				}
			}
		}
		return JSONMSG;
	}
	/**
	 * 找回密码验证验证码
	 * @return
	 */
	public String ajaxCheckJCaptchaResponse(){
			CustomUserDetails customUserDetail = null;
			try {
				customUserDetail = userService.getCurrentUser();
			} catch (YtoxlUserException e1) {
				logger.error("找回密码验证码处验证用户登录", e1);
			}
			// 找回密码 第二步如果用户没有登录 执行登录流程
			if (customUserDetail == null) {
				try {
					ServletActionContext.getRequest().setAttribute(
							"jCaptchaResponse", jCaptchaResponse);
					boolean flag = JCaptchaValidator.validate();
					if (!flag) {
						setMessage(Boolean.FALSE.toString(), "验证码输入错误");
					} else {
						setMessage(Boolean.TRUE.toString(), "");
					}
				} catch (Exception e) {
					logger.error("找回密码验证码异常", e);
				}
			}
		return JSONMSG;
	}
	/**
	 * 找回密码操作
	 * 
	 * @return
	 */
	public String passwordBackQueryMsg() {
		try {
			CustomUserDetails customUserDetail = null;
			try {
				customUserDetail = userService.getCurrentUser();
			} catch (YtoxlUserException e1) {
				logger.error("找回密码操作，用户未登录", e1);
			}
			// 找回密码 第二步如果用户没有登录 执行登录流程
			if (customUserDetail == null) {
				/*ServletActionContext.getRequest().setAttribute(
						"jCaptchaResponse", jCaptchaResponse);
				boolean flag = JCaptchaValidator.validate();
				if (flag) {
					if (!userService.repeatUsername(username)) {*/
						User user = null;
						user = userService.getByName(username);
						if (User.STATUS_ABLE == user.getStatus()) {
							email = user.getEmail();
							StringBuilder sb = new StringBuilder();
							sb.append(mailUrl)
									.append("/user/resetpassword.htm?username=")
									.append(EncodeUtils.base64Encode(user
											.getUsername()));
							// 将用户的username和常量md5加密
							sb.append("&")
									.append("active=")
									.append(EncodeUtils.base64Encode(Md5EncryptionUtils
											.MD5SaltPassword(
													user.getUsername(),
													passwordSaltAction)));
							// 添加时间戳
							String timesConstant = System.currentTimeMillis()
									+ ":" + domainName;
							sb.append("&")
									.append("sendTime=")
									.append(EncodeUtils
											.base64Encode(timesConstant));
							String findPassUrl = sb.toString();
							try {
								Map<String, String> map = new HashMap<String, String>();
								map.put("userName", username);
								map.put("findPassUrl", findPassUrl);
								map.put("domainName", domainName);
								String text = sendEmailService.getMailContent(
										MailTemplate.TYPE_REPASSWORD, map);
								sendEmailService.sendMail(user.getEmail(),
										"找回密码", text);
							} catch (YiPinStoreException e) {
								logger.error("用户邮箱的连接地址出错",e);
							}
							return "mailmsg";// 发送邮件跳转到邮件已发送页面
						} else {
							findPasswordInfo = "该用户未激活或被禁用";
							return "noActivate";
						}
					/*} else {
						return "accoutError";// 账号不存在
					}
				} else {
					return "verificationError";// 验证码错误
				}*/
			} else {
				return SUCCESS;
			}
		} catch (Exception e) {
			return SUCCESS;
		}
	}

	/**
	 * 检查url有效的时间
	 * 
	 * @return
	 */
	private boolean checkUrlVaildTime() {
		if (StringUtils.isNotEmpty(sendTime)) {
			String sendEmailTime = EncodeUtils.base64Decode(sendTime)
					.split(":")[0];
			long sdTime = Long.parseLong(sendEmailTime);
			long curTime = System.currentTimeMillis();
			int pTime = Integer.parseInt(resetPasswordTime);
			long passwordTime = sdTime + pTime * 60 * 60 * 1000;
			return curTime < passwordTime;
		}
		return false;
	}

	/**
	 * 检查有注册邮箱的唯一性
	 * 
	 * @return
	 */
	public String checkEmailVaild() {
		try {
			if (StringUtils.isNotEmpty(userinfo.getEmail())) {
				String userName = userService.getCurrentUser().getUsername();
				User user=userService.getByName(userName);
				// 排除当前用户邮箱
				if (userinfo.getEmail().equals(user.getEmail())) {
					setMessage("status", "true");
					return JSONMSG;
				} else {
					// 验证其他邮箱
					boolean status;
					status = userService.repeatEmail(userinfo.getEmail());
					if (status) {
						setMessage("status", "true");
						return JSONMSG;
					} else {
						setMessage("status", "false");
						return JSONMSG;
					}
				}
			}
		} catch (YtoxlUserException e) {
			logger.error("验证邮箱出现异常", e);
			setMessage("status", "error");
			return JSONMSG;
		}
		setMessage("status", "error");
		return JSONMSG;
	}

	/**
	 * 检查密码正确性
	 * 
	 * @return
	 */
	public String checkPwdVaild() {
		try {
			if (StringUtils.isNotEmpty(password)) {
				CustomUserDetails user=userService.getCurrentUser();
				String userPwd = userService.getByName(user.getUsername()).getPassword();
				String uActive = Md5EncryptionUtils.MD5SaltPassword(password,
						passwordSaltAction);
				if (userPwd.equals(uActive)) {
					setMessage("status", "true");
					return JSONMSG;
				} else {
					setMessage("status", "false");
					return JSONMSG;
				}
			}
		} catch (YtoxlUserException e) {
			logger.error("检查密码正确性出现异常", e);
			e.printStackTrace();

		}
		setMessage("status", "error");
		return active;

	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	public String resetPassword() {
		if (!checkUrlVaildTime()) {
			// 过期
			findPasswordInfo = "url已经过期！";
			return "updateInfo";
		}
		return "passWordStep3";
	}

	// 邮件跳转页面的提交
	public String updatePasswordByMail() throws YtoxlUserException {
		// 获取用户名称、时间戳、加密字符串
		// 1.判断时间是否过期
		// 2.验证url是否正确
		// 3.重置密码
		try {
			if (checkUrlVaildTime()) {
				String uName = EncodeUtils.base64Decode(username);
				User u = userService.getByName(uName);
				String uActive = Md5EncryptionUtils.MD5SaltPassword(
						u.getUsername(), passwordSaltAction);
				active = EncodeUtils.base64Decode(active);
				if (null != u && uActive.equals(active)) {
					// 修改密码
					userService.updatePswByUserId(u.getUserId(), password);
					findPasswordInfo = "重置密码成功！";
					return "updateSuccess";
				} else {
					// Url被改了
					findPasswordInfo = "url被修改过！";
					return "updateInfo";
				}
			} else {
				// 过期
				findPasswordInfo = "url已经过期！";
				return "updateInfo";
			}
		} catch (NumberFormatException n) {
			logger.error("修复密码是转换数据异常:", n);
			findPasswordInfo = "修改密码失败！";
			return "updateInfo";
		} catch (Exception e) {
			logger.error("密码修复失败:", e);
			findPasswordInfo = "修改密码失败！";
			return "updateInfo";
		}
	}

	/**
	 * 第三方 登录
	 * */
	public String thirdLogin(){
		try
		{
			String usernameThird = "";
			if("0".equals(thirdType)){
				usernameThird="Login_QQ_"+openId;
			}else if("1".equals(thirdType)){
				usernameThird="Login_WEIBO_"+openId;
			}else{
				usernameThird="Login_QQ_"+openId;
			}
			if(!userService.repeatUsername(usernameThird)||!userService.repeatEmail(usernameThird)){
				 setMessage(ACCOUNT_REPETITION);//用户账号重复
				 return JSONMSG;
			}else {
				User user = new User();
				user.setUsername(usernameThird);
				user.setNickName(nickName);
				user.setPassword(Md5EncryptionUtils.MD5SaltPassword(openId, passwordSaltAction));
				userInfoService.addRegister(user);
				setMessage(ACCOUNT_REGISTER_SUCCESS);//注册成功
			}	
			/*if(!userService.repeatUsername(openId)||!userService.repeatEmail(openId)){
				 setMessage(ACCOUNT_REPETITION);//用户账号重复
				 return JSONMSG;
			}else {
				User user = new User();
				user.setUsername(openId);
				user.setNickName(nickName);
				user.setPassword(Md5EncryptionUtils.MD5SaltPassword(openId, passwordSaltAction));
				userInfoService.addRegister(user);
				setMessage(ACCOUNT_REGISTER_SUCCESS);//注册成功
			}*/
		}
		catch (YiPinStoreException e) {
			setMessage(ACCOUNT_EXCEPTION);
			logger.error("UserAction qqLogin YiPinStoreException :", e.getMessage());
		} 
		catch (YtoxlUserException e) {
			setMessage(ACCOUNT_EXCEPTION);
			logger.error("UserAction qqLogin YtoxlUserException :", e.getMessage());
		}
		return JSONMSG;
	}
	
	public String getRemeberName() {
		return remeberName;
	}

	public void setRemeberName(String remeberName) {
		this.remeberName = remeberName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOpertNum() {
		return opertNum;
	}

	public void setOpertNum(Integer opertNum) {
		this.opertNum = opertNum;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPswEn() {
		return newPswEn;
	}

	public void setNewPswEn(String newPswEn) {
		this.newPswEn = newPswEn;
	}

	public String getjCaptchaResponse() {
		return jCaptchaResponse;
	}

	public void setjCaptchaResponse(String jCaptchaResponse) {
		this.jCaptchaResponse = jCaptchaResponse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getFindPasswordInfo() {
		return findPasswordInfo;
	}

	public void setFindPasswordInfo(String findPasswordInfo) {
		this.findPasswordInfo = findPasswordInfo;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getThirdType() {
		return thirdType;
	}
	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}
	
}
