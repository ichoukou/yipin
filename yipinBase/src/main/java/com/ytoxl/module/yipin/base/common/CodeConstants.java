package com.ytoxl.module.yipin.base.common;

public class CodeConstants {
	public static final String INFO_CODE_PRE = "I_";//页面显示
	public static final String EXCEPTION_CODE_PRE = "E_";//程序中使用
	
	/**
	 * 参数错误，适用于所有service中的所有方法验证必传参数错误时报错。
	 */
	
	//购物车异常参数
	/**设置购物数据异常**/
	public static final String CART_DATA_ERROR = "CART_DATA_ERROR";
	/**商品已下架**/
	public static final String CART_PRODUCT_DROP = "CART_PRODUCT_DROP";
	/**商品已售罄**/
	public static final String CART_PRODUCT_SOLDOUT = "CART_PRODUCT_SOLDOUT";
	/**商品数量库存不足**/
	public static final String CART_PRODUCT_SHORTAGE = "CART_PRODUCT_SHORTAGE";
	
	/**生成订单时候数据异常**/
	public static final String ORDER_CREATE_PARAMETER_ERROR = "ORDER_CREATE_PARAMETER_ERROR";
	/**生成订单时生成订单号异常**/
	public static final String ORDER_CREATE_ORDERNO_ERROR = "ORDER_CREATE_ORDERNO_ERROR";
	
	/**商品查询异常**/
	public static final String ORDER_PRODUCTSKU_ERROR = "ORDER_PRODUCTSKU_ERROR";
	/**订单重复提交异常**/
	public static final String ORDER_REPEAT_SUBMIT = "ORDER_REPEAT_SUBMIT"; 
	
	//提交订单时候没有登陆异常编码
	public static final String UNLOGIN_ERROR = "UNLOGIN_ERROR";
	
	//登陆超时异常编码
	public static final String LOGIN_TIMEOUT_ERROR = "LOGIN_TIMEOUT_ERROR";
	//商品已经不在特卖期异常编码
	public static final String PRODUCT_NOTACTIVITY_ERROR = "PRODUCT_NOTACTIVITY_ERROR";
	//商品库存不足异常编码
	public static final String PRODUCT_UNDERSTOCK_ERROR = "PRODUCT_UNDERSTOCK_ERROR";
	//商品库存不足异常编码
	public static final String ORDER_ADDRESS_ERROR = "ORDER_ADDRESS_ERROR";
	//管理员和卖家禁止购买
	public static final String MANAGE_SELLER_FORBID = "MANAGE_SELLER_FORBID";
	//商品库存
	public static final String PRODUCT_INVENTORY = "PRODUCT_INVENTORY";
	//商品下架或者不可售
	public static final String PRODUCT_SOLD_OUT = "PRODUCT_SOLD_OUT";
	//商品可售
	public static final String PRODUCT_SOLD = "PRODUCT_SOLD";
	/**新增商品skuCode重复异常编码**/
	public static final String E_SKUCODE_REPEART_ERROR = "E_SKUCODE_REPEART_ERROR";
	/**商品sku编码为空异常编码**/
	public static final String E_SKUCODE_EMPTY_ERROR = "E_SKUCODE_EMPTY_ERROR";
	
}
