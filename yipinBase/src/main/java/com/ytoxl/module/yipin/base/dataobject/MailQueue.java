package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.MailQueueTbl;

/**
 * @author zengzhiming
 *
 */
public class MailQueue extends MailQueueTbl {
	public static final Short STATUS_DEFAULT=0;    //表示未发送
	public static final Short STATUS_SEND_SUCCESS=1; //发送成功
	public static final Short STATUS_SEND_FAIL=2;    //发送失败
}
