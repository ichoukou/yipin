package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.PointDetailTbl;

/**
 * @author zengzhiming
 *
 */
public class PointDetail extends PointDetailTbl{
	/**积分来源:0=订单  1=退货  2=注册 3=吐槽 4=发现	 */
	public static final Short POINT_SOURCE_ORDER=0;
	public static final Short POINT_SOURCE_RETURN=1;
	public static final Short POINT_SOURCE_REGISTER=2;
	public static final Short POINT_SOURCE_TSUKKOMI=3;
	public static final Short POINT_SOURCE_DISCOVER=4;
	
}
