package com.ytoxl.module.yipin.content.service;

import java.util.List;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.MailQueue;

/**
 * @author zengzhiming
 *
 */
public interface SendMailService{
	
	public String getMailTitle(Short type, Object data)throws YiPinStoreException;
	/**
	 * 获取发送邮件的内容  
	 * @param type
	 * @param data   FreeMarker动态合并内容的model
	 * @return
	 * @throws UhomeStoreException
	 */
	public String getMailContent(Short type,Object data)throws YiPinStoreException;
	
	public void saveDataToMailQueue(MailQueue mq)throws YiPinStoreException;
	
	public void sendMail(String to,String subject,String conetent) throws YiPinStoreException;
	
	public void sendMails(List<MailQueue> mqs) throws YiPinStoreException;
}
