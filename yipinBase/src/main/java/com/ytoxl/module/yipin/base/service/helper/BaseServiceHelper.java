package com.ytoxl.module.yipin.base.service.helper;

import com.ytoxl.module.core.common.utils.StringUtils;

/**
 * 把各个service中和业务耦合的类似工具的方法独立封装
 * 
 * @author longyong
 * 
 */
public class BaseServiceHelper {

	/**
	 * 构造运单信息查询的报文
	 * 
	 * @param queryString
	 * @param mailNo
	 * @return
	 */
	public static String createMailNoXML(String queryString, String mailNo) {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<order><mailNo>").append(mailNo)
				.append("</mailNo></order>");
		return StringUtils.replace(queryString, "{1}", stringBuffer.toString());
	}
}
