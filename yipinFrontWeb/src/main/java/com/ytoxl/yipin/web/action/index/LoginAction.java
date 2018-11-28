package com.ytoxl.yipin.web.action.index;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.code.jcaptcha4struts2.core.validation.JCaptchaValidator;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.common.utils.Md5EncryptionUtils;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.yipin.web.action.BaseAction;

/** 登陆action
 * @author zengzhiming
 *
 */
public class LoginAction extends BaseAction{
	/**	 */
	private static final long serialVersionUID = -430675913156316603L;
	private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
	/**账号重复 */
	public static final String ACCOUNT_REPETITION="2";
	/**邮箱重复 */
	public static final String EMAIL_REPETITION="4";
	/**账号异常*/
	public static final String ACCOUNT_EXCEPTION="1";
	/**账号注册成功*/
	public static final String ACCOUNT_REGISTER_SUCCESS="6";
	/**账号注册用户名错误*/
	public static final String ACCOUNT_USERNAME_ERROR="3";
	/**验证码	 */
	public static final String VERIFICATION_ERROR="5";
	/**验证码	 */
	private String jCaptchaResponse;
	/**用户名	 */
	private String username;
	/**密码	 */
	private String password;
	@Autowired
	private UserService userService;
	//@Autowired
	//private SendMailService sendMailService;
	@Autowired
	private UserInfoService userInfoService;
	/**加密盐值	 */
	@Value("${password_salt}")
	private String passwordSaltAction;
	
	private static final String  EMAIL="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	private static final String  TELEPHONE="^1\\d{10}$";
	private static final String  EMAIL_INDEX = "/@+/";
	private static final String INFO="info";
	public String defaultIndex(){
		return SUCCESS;
	}

	/** email注册
	 * @return
	 */
	public String email(){
		boolean flag = JCaptchaValidator.validate();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(flag){//验证码
			username = StringUtils.trimToEmpty(username);
			if(!userService.repeatUsername(username)){
				 setMessage(ACCOUNT_REPETITION);//用户账号重复
				 return JSONMSG;
			}else {
				//验证一把/
		    	Pattern email = Pattern.compile(EMAIL);
				if(email.matcher(username).matches()){//如果是邮箱
					if(!userService.repeatEmail(username)){
						 setMessage(EMAIL_REPETITION);//用户账号重复
						 return JSONMSG;
					}
				}
				User user = new User();
				user.setUsername(username);//不管是邮件还是手机号都可以作为用户名
				user.setPassword(Md5EncryptionUtils.MD5SaltPassword(password, passwordSaltAction));
				if(email.matcher(username).matches()){//如果是邮箱
					user.setEmail(username);
				}
	        	try {
					userInfoService.addRegister(user);
					//sendMailService.sendMail(user.getEmail(), "注册成功", "恭喜你成为一品网会员。");
					setMessage(ACCOUNT_REGISTER_SUCCESS);//注册成功
					if(!email.matcher(username).matches()){//如果是手机 在session中放一个变量
						ServletActionContext.getRequest().getSession().setAttribute("register",request.getContextPath()+"/register-perfectInfo.htm");
					}
				} catch (YiPinStoreException e) {
					setMessage(ACCOUNT_EXCEPTION);
					logger.error("LoginAction email YiPinStoreException :", e);
				} catch (YtoxlUserException e) {
					setMessage(ACCOUNT_EXCEPTION);
					logger.error("LoginAction email YtoxlUserException :", e);
				}
			}	
		}else{
			setMessage(VERIFICATION_ERROR);//输入的验证码不正确
			return JSONMSG;
		}
		return JSONMSG;
	}
	/**完善用户资料
	 * @return
	 */
	public String perfectInfo(){
		
		return INFO;
	}

	public String getjCaptchaResponse() {
		return jCaptchaResponse;
	}

	public void setjCaptchaResponse(String jCaptchaResponse) {
		this.jCaptchaResponse = jCaptchaResponse;
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

	public String getPasswordSaltAction() {
		return passwordSaltAction;
	}

	public void setPasswordSaltAction(String passwordSaltAction) {
		this.passwordSaltAction = passwordSaltAction;
	}
	
	
}
