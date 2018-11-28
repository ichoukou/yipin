package com.ytoxl.module.yipin.content.dataobject;

import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.yipin.content.dataobject.tbl.SuggestTbl;

public class Suggest extends SuggestTbl {
	public static final Short STATUS_UNREAD = 1; // 未读
	public static final Short STATUS_READ = 2; // 已读
	public static final Short STATUS_DEL = 3; // 禁止
	public static final Short STATUS_REVERT = 4; // 已回复

	public static final Short TYPE_MYSPLIT_SUGGEST = 1; // 我的吐槽
	public static final Short TYPE_MYFIND_SUGGEST = 0; // 我的发现

	/** 吐槽得分 */
	protected Integer point;

	protected User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
