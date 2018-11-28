package com.ytoxl.yipin.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.google.code.jcaptcha4struts2.core.validation.JCaptchaValidator;

/** 自定义用户认证过滤器
 * @author zengzhiming
 *
 */
public class CustomUsernamePasswordAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		//先判断验证码是否正确,然后在生成短信注册码
		//boolean flag = JCaptchaValidator.validate();
		if (!request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());  
        }  
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        username = username.trim();
        CustomUsernamePasswordAuthenticationToken authRequest =  new CustomUsernamePasswordAuthenticationToken(username, password);
        // Place the last username attempted into HttpSession for views
        HttpSession session = request.getSession(false);
        if (session != null || getAllowSessionCreation()) {
            request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
        }
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
 } 
	
	
	
	
}
