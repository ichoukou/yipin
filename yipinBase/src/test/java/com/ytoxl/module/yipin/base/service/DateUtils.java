package com.ytoxl.module.yipin.base.service;

import java.util.Calendar;
import java.util.Date;

import com.ytoxl.module.core.common.utils.DateUtil;

public class DateUtils {
	
	public static void main(String[] args) {
		System.out.println(DateUtil.toSeconds(DateUtil.add(new Date(), Calendar.MONTH, -1)));
		
	}

}
