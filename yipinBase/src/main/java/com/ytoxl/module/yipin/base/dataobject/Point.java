package com.ytoxl.module.yipin.base.dataobject;

import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.tbl.PointTbl;

public class Point extends PointTbl {
	
	public static final Short TYPE_MINUS = 0; //扣减积分
	public static final Short TYPE_ADD = 1;	//增加积分
	public static final Short POINTSOURCE_RETURNORDER = 1; //退货
	public static final Short POINTSOURCE_ADDORDER = 0; //新增订单
	public static final Short POINTSOURCE_REGISTER = 2; // 注册用户
	public static final Short POINTSOURCE_SPLIT = 3; // 我的吐槽
	public static final Short POINTSOURCE_FIND = 4; // 我的发现
	public static final Short POINTSOURCE_CANCEL = 5; // 待发货退款
	public static final Integer POINTSOURCE_REGISTER_SCOPE=20;//注册20
	
	protected List<PointDetail> pointDetails;

	public List<PointDetail> getPointDetails() {
		return pointDetails;
	}

	public void setPointDetails(List<PointDetail> pointDetails) {
		this.pointDetails = pointDetails;
	}
}
