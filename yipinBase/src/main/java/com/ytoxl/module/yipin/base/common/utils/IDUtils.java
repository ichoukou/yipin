package com.ytoxl.module.yipin.base.common.utils;

import org.apache.commons.id.uuid.UUID;

public class IDUtils {

	/**
	 * 使用apache id获得uuid
	 * @return
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
}
