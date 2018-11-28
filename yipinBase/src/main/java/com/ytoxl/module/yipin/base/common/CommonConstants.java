package com.ytoxl.module.yipin.base.common;

public class CommonConstants {
	//分页常量
	public final static String PAGE_START = "start";
	public final static String PAGE_LIMIT = "limit";
	public final static String PAGE_SORT = "sort";
	public final static String PAGE_DIR = "dir";
	public final static Integer PAGE_DEFAULT_TOTAL=10000;
	
	//freemarker 文件后缀
	public final static String FREEMARK_POSTFIX = ".ftl";
	//freemarker 运单模版名称
	public final static String FREEMARK_WAYBILLNAME = "WayBillTemplate";
	
	//购物车对应的cookie名称
	public final static String COOKIE_SHOPPINGCART = "_cookie_shoppingcart";
	
	// 分隔符
	public static final String STR_SPLIT = ",";
	
	public static final String ENDTIME = "endTime";
	public static final String ENDTIME_VALUE = " 24:00:00";
	
	public static final String REFUND_NO_PRE = "TH";  //退货编码前缀
	public static final String CANCEL_NO_PRE = "TK";  //退款编码前缀
	
	public static final String CONSTANT_YTO_CODE = "yuantong"; //圆通快递 查询物流用
	
	public static final String EXPRESS_CONNECT = "->";
	
	public static final String CONSTANT_ZERO = "0";
	
	//查询商品分类
	public static final int TYPE_YUANCHANDI = 1;//原产地
	public static final int TYPE_FENLEI = 2;//分类
	public static final int TYPE_QIANGGOU = 3;//抢购分区
	public static final int TYPE_YUSHOU = 4;//预售分区
	public static final int TYPE_PUTONG = 5;//普通分区
	
	//prop表code分隔符
	public static final String LEVEL_CODE_SPLIT = "-";
	
}
