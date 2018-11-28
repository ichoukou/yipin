package com.ytoxl.module.yipin.base.dataobject;


import com.ytoxl.module.yipin.base.dataobject.tbl.MailTemplateTbl;

/**
 * @author zengzhiming
 *
 */
public class MailTemplate extends MailTemplateTbl {
	public static final Short TYPE_SPECIAL_SELLER=1;   //特卖
	public static final Short TYPE_SEC_KILL=2;  //秒杀
	public static final Short TYPE_BRAND_SUBSCRIBE=3;  //品牌预约
	public static final Short TYPE_ALL=4;                //所有
	public static final Short TYPE_REPASSWORD=5;  //找回密码
	public static final Short TYPE_REGISTER=6; //新用户注册
}
