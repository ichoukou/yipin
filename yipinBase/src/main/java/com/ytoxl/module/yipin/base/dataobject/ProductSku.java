package com.ytoxl.module.yipin.base.dataobject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.ytoxl.module.yipin.base.dataobject.tbl.ProductSkuTbl;

public class ProductSku extends ProductSkuTbl {
	
	public static final short ISDELETE_NO = 0; //否
	public static final short ISDELETE_YES = 1; //是
	public static final  String SKUOPTIONNAME_SF = "规格";
	public static final  String SKUOPTIONNAME_GL = "重量";
	/**未开抢**/
	public static final Short SALE_STATUS_PEND = 1;
	/**进行中**/
	public static final Short SALE_STATUS_ON = 2;
	/**已抢光**/
	public static final Short SALE_STATUS_STOCKOUT = 3;
	/**已过期**/
	public static final Short SALE_STATUS_EXPIRED = 4;
	
	protected List<ProductSkuOptionValue> productSkuOptionValues;
	protected String productSku_status_code;//商品sku购买状态验证码
	protected Product product; // 该sku对应的商品
	protected Sale sale;
	protected String skuColor;//商品颜色
	protected String skuSpecification; //规格
	protected BigDecimal skuWeight;//重量
	protected Integer skuWeightId;
	protected String skuOptionValue;
	protected String overrideSkuOptionValue;
	protected String skuOptionName;
	protected boolean isActivity; //是否可用
	protected String errorMsg;
	protected Integer buyAmount;
	protected BigDecimal postage;
	/**抢购开始时间**/
	protected Date saleBeginTime;
	/**抢购结束时间**/
	protected Date saleEndTime;
	/**销售状态**/
	protected Short SaleStatus;
	public static final Short STATUS_ISDEFAULT = 1; //是否默认
	
	//sku颜色用于商品导入
	protected String productSkuColor;
	//sku尺寸用于商品导入
	protected String productSkuSize;
	/**
	 * <p>Field userId: 用户ID</p>
	 */
	private Integer userId;
	
	/**
	 * <p>Description: 用户ID</p>
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * <p>Description: TODO</p>
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * <p>Description: TODO</p>
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * <p>Description: TODO</p>
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	protected List<Prop> props;//商品的产地/分类
	
	public List<ProductSkuOptionValue> getProductSkuOptionValues() {
		return productSkuOptionValues;
	}

	public void setProductSkuOptionValues(
			List<ProductSkuOptionValue> productSkuOptionValues) {
		this.productSkuOptionValues = productSkuOptionValues;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSkuColor() {
		return skuColor;
	}

	public void setSkuColor(String skuColor) {
		this.skuColor = skuColor;
	}

	public String getSkuOptionValue() {
		return skuOptionValue;
	}

	public void setSkuOptionValue(String skuOptionValue) {
		this.skuOptionValue = skuOptionValue;
	}

	public String getSkuOptionName() {
		return skuOptionName;
	}

	public void setSkuOptionName(String skuOptionName) {
		this.skuOptionName = skuOptionName;
	}

	public boolean getIsActivity() {
		return isActivity;
	}

	public void setActivity(boolean isActivity) {
		this.isActivity = isActivity;
	}

	public String getProductSkuColor() {
		return productSkuColor;
	}

	public void setProductSkuColor(String productSkuColor) {
		this.productSkuColor = productSkuColor;
	}

	public String getProductSkuSize() {
		return productSkuSize;
	}

	public void setProductSkuSize(String productSkuSize) {
		this.productSkuSize = productSkuSize;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public String getOverrideSkuOptionValue() {
		return overrideSkuOptionValue;
	}

	public void setOverrideSkuOptionValue(String overrideSkuOptionValue) {
		this.overrideSkuOptionValue = overrideSkuOptionValue;
	}

	public String getSkuSpecification() {
		return skuSpecification;
	}

	public void setSkuSpecification(String skuSpecification) {
		this.skuSpecification = skuSpecification;
	}

	public BigDecimal getSkuWeight() {
		return skuWeight;
	}

	public void setSkuWeight(BigDecimal skuWeight) {
		this.skuWeight = skuWeight;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

//	public Short getSellType() {
//		return sellType;
//	}
//
//	public void setSellType(Short sellType) {
//		this.sellType = sellType;
//	}

	public Integer getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(Integer buyAmount) {
		this.buyAmount = buyAmount;
	}

	public Integer getSkuWeightId() {
		return skuWeightId;
	}

	public void setSkuWeightId(Integer skuWeightId) {
		this.skuWeightId = skuWeightId;
	}
	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}
	public Date getSaleBeginTime() {
		return saleBeginTime;
	}

	public void setSaleBeginTime(Date saleBeginTime) {
		this.saleBeginTime = saleBeginTime;
	}

	public Date getSaleEndTime() {
		return saleEndTime;
	}

	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	public Short getSaleStatus() {
		return SaleStatus;
	}

	public void setSaleStatus(Short saleStatus) {
		SaleStatus = saleStatus;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public String getProductSku_status_code() {
		return productSku_status_code;
	}

	public void setProductSku_status_code(String productSku_status_code) {
		this.productSku_status_code = productSku_status_code;
	}
	
}