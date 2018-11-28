package com.ytoxl.module.yipin.base.dataobject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ytoxl.module.yipin.base.dataobject.tbl.ProductTbl;

/**
 * <p>
 * ClassName: Product
 * </p>
 * <p>
 * Description: 商品
 * </p>
 * <p>
 * Author: 
 * </p>
 * <p>
 * Date: Dec 12, 2013
 * </p>
 */

public class Product extends ProductTbl {

	public static final Short STATUS_DRAFT = 1; // 草稿
	public static final Short STATUS_CHECK_PEND = 2; // 待审核
	public static final Short STATUS_PASS = 3; // 审核通过
	public static final Short STATUS_NO_PASS = 4; // 审核不通过
	public static final Short STATUS_SELL = 6; // 销售中
	public static final Short STATUS_SELLOUT = 7; // 已售磬
	public static final Short STATUS_DELETE = 9; // 删除
	public static final Short SELLTYPE_YS = 2;// 预售类型
	public static final Short SELLTYPE_XS = 1;// 销售类型
	public static final Short SELLTYPE_QG = 3;// 抢购类型
	public static final String TYPE_AREA = "area";//产地
	public static final String TYPE_CATALOG = "catalog";//分类
	public static final String TYPE_PEOPLE = "adaptPeople";

	// 后台商品状态
	public static final Short[] STATUSES = new Short[] { STATUS_DRAFT,STATUS_SELL, STATUS_SELLOUT,STATUS_DELETE };
	// 后台商品状态
	public static final Short[] FRONTSTATUSES = new Short[] { STATUS_DRAFT,STATUS_SELL, STATUS_SELLOUT };
	// 审核状态
	public static final Short[] REVIEWSTATUSES = new Short[] {
			STATUS_CHECK_PEND, STATUS_PASS, STATUS_NO_PASS };
	// //销售类型
	// public static final Short[] STATUS_SELLTYPE = new Short[] { SELLTYPE_XS,
	// SELLTYPE_YS, SELLTYPE_QG };
	public static final Short IS_SHOW_INVENTORY_NO = 0;// 不显示库存
	public static final Short IS_SHOW_INVENTORY_YES = 1;// 显示库存
	public static final Short[] ISSHOWINVENTORYS = new Short[] {
			IS_SHOW_INVENTORY_NO, IS_SHOW_INVENTORY_YES };

	protected Brand brand; // 关联品牌
	protected List<ProductSku> productSkus; // 关联商品SKU
	protected List<SkuOption> skuOptions; // 商品SKU信息
	protected Integer salesNum; // 商品销售次数
	protected Integer productSkuColorNum;
	protected Integer productSkuSizeNum;
	protected ProductSku productSku;
	// 图片集合
	protected List<String> imageList;
	protected String defaultImage;
	protected int inventory;// 商品库存
	protected int sellStatus;
	protected Date preSellTime;// 预发售时间
	protected String skuCode;
	protected String name;// 商品名称
	// 商品原产地、分类、适宜人群
//	protected List<Prop> props;
	protected Map<String,List<Prop>> propsMap;
	protected Zone zone;
	protected Integer totalInventory;// 总库存
	protected ProductSku defaultProductSku;// 前台默认展示sku
	protected List<ProductProp> ppList; 
	/**
	 * <p>Field zoneId: 专区ID</p>
	 */
	private Integer zoneId; 

	/*	*//**
	 * 获取默认productSku
	 * 
	 * @return
	 */
	/*
	 * public ProductSku getDefaultProductSku(){
	 * if(productSkus!=null&&productSkus.size()>0){ for(ProductSku proSku :
	 * productSkus){
	 * if(ProductSku.STATUS_ISDEFAULT.equals(proSku.getIsDefault())){ return
	 * proSku; } } } return null; }
	 */

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	

	/**
	 * <p>
	 * Field productSkuId: 商品SKUID
	 * </p>
	 */
	private Integer productSkuId;
	
	/**
	 * <p>Field skuSpecification: 商品规格</p>
	 */
	private String skuSpecification;

	/**
	 * <p>
	 * Field rank: 商品权重
	 * </p>
	 */
	private Integer rank;

	/**
	 * <p>
	 * Field unitPrice: 单价
	 * </p>
	 */
	private BigDecimal unitPrice;

	/**
	 * 获取商品第一张默认图片，添加相应前后缀可以访问缩略图
	 * 
	 * @return
	 */
	public String getDefaultImage() {
		String thumbnail = null;
		if (StringUtils.isNotEmpty(imageUrls)) {
			String[] thumbnails = imageUrls.split(",");
			thumbnail = thumbnails != null && thumbnails.length > 0 ? thumbnails[0]
					: "";
		}
		return thumbnail;
	}

	public List<String> getImageList() {
		List<String> list = new ArrayList<String>();
		String[] str = imageUrls.split(",");
		list = Arrays.asList(str);
		return list;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<ProductSku> getProductSkus() {
		return productSkus;
	}

	public void setProductSkus(List<ProductSku> productSkus) {
		this.productSkus = productSkus;
	}

	public int getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(int sellStatus) {

		this.sellStatus = sellStatus;
	}

	public Date getPreSellTime() {
		return preSellTime;
	}

	public void setPreSellTime(Date preSellTime) {
		this.preSellTime = preSellTime;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public List<SkuOption> getSkuOptions() {
		return skuOptions;
	}

	public void setSkuOptions(List<SkuOption> skuOptions) {
		this.skuOptions = skuOptions;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getProductSkuColorNum() {
		return productSkuColorNum;
	}

	public void setProductSkuColorNum(Integer productSkuColorNum) {
		this.productSkuColorNum = productSkuColorNum;
	}

	public Integer getProductSkuSizeNum() {
		return productSkuSizeNum;
	}

	public void setProductSkuSizeNum(Integer productSkuSizeNum) {
		this.productSkuSizeNum = productSkuSizeNum;
	}

	@Override
	public String toString() {
		return productId + "-->" + name;
	}

	public ProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Map<String, List<Prop>> getPropsMap() {
		return propsMap;
	}

	public void setPropsMap(Map<String, List<Prop>> propsMap) {
		this.propsMap = propsMap;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Integer getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	public ProductSku getDefaultProductSku() {
		return defaultProductSku;
	}

	public void setDefaultProductSku(ProductSku defaultProductSku) {
		this.defaultProductSku = defaultProductSku;
	}

	/**
	 * <p>
	 * Description: 商品权重
	 * </p>
	 * 
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}

	/**
	 * <p>
	 * Description: 商品权重
	 * </p>
	 * 
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * <p>
	 * Description: 单价
	 * </p>
	 * 
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	/**
	 * <p>
	 * Description: 单价
	 * </p>
	 * 
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * <p>
	 * Description: 商品SKUID
	 * </p>
	 * 
	 * @return the productSkuId
	 */
	public Integer getProductSkuId() {
		return this.productSkuId;
	}

	/**
	 * <p>
	 * Description: 商品SKUID
	 * </p>
	 * 
	 * @param productSkuId
	 *            the productSkuId to set
	 */
	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}
	
	/**
	 * <p>Description: 商品规格</p>
	 * @return the skuSpecification
	 */
	public String getSkuSpecification() {
		return this.skuSpecification;
	}
	/**
	 * <p>Description: 商品规格</p>
	 * @param skuSpecification the skuSpecification to set
	 */
	public void setSkuSpecification(String skuSpecification) {
		this.skuSpecification = skuSpecification;
	}
	
	public List<ProductProp> getPpList() {
		return ppList;
	}

	public void setPpList(List<ProductProp> ppList) {
		this.ppList = ppList;
	}

}
