package com.ytoxl.yipin.web.action.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.PropService;
import com.ytoxl.module.yipin.base.service.ZoneService;
import com.ytoxl.yipin.web.action.BaseAction;
 
@SuppressWarnings("serial")
public class ProductAction extends BaseAction {
	
	private static Logger logger = LoggerFactory.getLogger(ProductAction.class);
	private static final String PRODUCT_DETAIL = "productDetail";
	private static final String SCARE_BUY_DETAIL = "scareBuyDetail";
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ZoneService zoneService;
	@Autowired 
	PropService propService;
	
	
	/**根据分类或者原产地或者专区查询商品的请求参数**/
	private String code;//分类或者原产地code
	private int propId;//分类或者原产地id
	private int isHaveInventory;//是否只是查询有库存商品(isHaveInventory=1只查询有库存商品)
	private int orderType;//排序(0:默认排序,1:按销量降序,2:按浏览量降序,3:按价格从低到高,4:按价格从高到低)
	private int type;//请求页面分类(1:原产地，2:分类，3:抢购专区，4:预售专区、5:普通专区)
	private Integer zoneId;//专区id
	private int limit=50;//每页显示数目
	private List<ProductSku> productSkus;//商品分页集合
	private BasePagination<ProductSku> productSkuPage;//分页对象
	private List<Prop> props;//查询原产地或者分类的商品列表时，递归查询当前传递的原产地或者分类所有父类
	private Prop currentProp;//当前原产地或者分类
	private long currentTimestamp;//当前时间的时间戳
	
	private Product product;
	private Integer productSkuId;
	private Integer outId;
	private Zone zone;
	
	/**
	 *  根据分类或者原产地或者专区查询商品信息集合
	 * 
	 * @return
	 */
	public String listProducts(){
		try{
			currentProp= propService.get(propId);
			if(currentProp!=null){
				code=currentProp.getCode();//当前专区的code
			}
			
			//当查询抢购专区，预售专区，普通专区时，需要专区名称
			if(type==CommonConstants.TYPE_QIANGGOU||type==CommonConstants.TYPE_YUSHOU||type==CommonConstants.TYPE_PUTONG){
				zone=zoneService.getZoneByZoneId(zoneId);
			}
			//当查询原产地或者分类时，需要向上递归查询出所有父级原产地或者分类(根据propId，向上递归查询prop表)
			else{
				if(currentProp!=null){
					props=new LinkedList<Prop>();
					props.add(currentProp);
					Prop prop;
					int propId=currentProp.getParentId();
					while(true){
						prop=propService.get(propId);
						if(prop==null){
							break;
						}else{
							if(prop.getParentId()==0){ 
								break; 
							}
							props.add(prop);
							propId=prop.getParentId();
						}
					}
				}
			}
			if(props!=null&&!props.isEmpty()){
				Collections.reverse(props);
			}

			if(productSkuPage==null){
				productSkuPage=new BasePagination<ProductSku>();
			}
			productSkuPage.setLimit(limit);
			
			//将url中的排序字段和desc转化成数字
			String sort = productSkuPage.getSort();
			String dir = productSkuPage.getDir();
			
			//设置排序类型
			if("100".equals(sort)){//默认排序
				orderType=0;
			}else if("200".equals(sort)){//按销量降序排序
				orderType=1;
			}else if("300".equals(sort)){//按浏览量降序排序
				orderType=2;
			}else if("400".equals(sort)){//按价格排序
				if("100000".equals(dir)){
					orderType=3;//升序
				}
				else{
					orderType=4;//降序
				}
			}
			
			if (productSkuPage.getParams() == null) {
				Map<String,String> params=new HashMap<String,String>();
				params.put("code", code+"");
				params.put("isHaveInventory", isHaveInventory+"");
				params.put("orderType", orderType+"");//排序类型
				params.put("type", type+"");
				if(type==CommonConstants.TYPE_QIANGGOU){//抢购专区
					params.put("zoneType", 1+"");	
				}else if(type==CommonConstants.TYPE_YUSHOU){//预售专区
					params.put("zoneType", 2+"");
				}else if(type==CommonConstants.TYPE_PUTONG){//普通专区
					params.put("zoneType", 3+"");
				}
				params.put("zoneId", zoneId+"");
				productSkuPage.setParams(params);
			} 
			
			Map<String, Object> paramsMap = productSkuPage.getSearchParamsMap();
			int total=productService.countProductSkusByMap(paramsMap);

			
			//查询商品列表
			productSkus=productService.listProductSkusByMap(paramsMap);
			ProductSku productSku;
			for(ProductSku sku:productSkus){
				//1:查询商品sku
				if(type==3){//抢购专区
					productSku=productService.getProductSkuById(sku.getProductSkuId());
				}else{
					productSku=productService.listProductSkusByProductId(sku.getProductId());
					sku.setProductSkuId(productSku.getProductSkuId());
					sku.setUnitPrice(productSku.getUnitPrice());
					sku.setMarketPrice(productSku.getMarketPrice());
					sku.setInventory(productSku.getInventory());
					sku.setSalesQuantity(productSku.getSalesQuantity());
					sku.setSkuOptionValue(productSku.getSkuOptionValue());
				}
				//2:查询商品sku的覆盖值并设置
				Map<String,String> skuMap=productService.getOverrideSkuOptionValue(productSku.getProductSkuId());
				sku.setOverrideSkuOptionValue(skuMap.get("overrideSkuOptionValue"));
				sku.setSkuOptionValue(skuMap.get("skuOptionValue")); 
				//3:查询商品产地并设置
				Product pp = productService.getProductCatologOrArea(sku
						.getProductId());
				if(pp!=null){
					if(pp.getPropsMap()!=null){
						sku.setProps(pp.getPropsMap().get("area"));
					}
				}
			} 
			
			productSkuPage.setResult(productSkus);
			productSkuPage.setTotal(total);
			
			if("asc".equals(productSkuPage.getDir())){
				productSkuPage.setDir("100000");
			}else if("desc".equals(productSkuPage.getDir())){
				productSkuPage.setDir("100001");
			}
			super.setSeoConfigByUrlKey(ServletActionContext.getRequest().getServletPath(), currentProp);
		} catch (YiPinStoreException e) {
			logger.error("listProducts: ", e);
		}
		
		currentTimestamp=System.currentTimeMillis();
   
		return "success";	
	}
	
	/**
	 * 商品详情
	 * @return
	 */
	public String productDetail(){
		try {
			product = productService.getProductById4Front(productSkuId);
			if(Product.SELLTYPE_XS.equals(product.getSellType())){
				if(type == CommonConstants.TYPE_YUANCHANDI || type == CommonConstants.TYPE_FENLEI){
					Prop prop = propService.get(outId);
					if(prop != null){
						props = propService.listParentProps(prop.getCode(), prop.getLevel());
						props.add(prop);
					}
				}else if(type == CommonConstants.TYPE_PUTONG){
					product.setZone(zoneService.getZoneByZoneId(outId));
				}
			}
			productService.updateProductHits(product);
			if(Product.SELLTYPE_QG.equals(product.getSellType())){
				return SCARE_BUY_DETAIL;
			}
		} catch (YiPinStoreException e) {
			 logger.error("获取商品详情失败", e);
			 return "error";
		}
		return PRODUCT_DETAIL;
	}
	
	public Short getIsDefault(){
		return ProductSku.STATUS_ISDEFAULT;
	}
	
	/**
	 * 普通
	 * @return
	 */
	public Short getSellTypeXS(){
		return Product.SELLTYPE_XS;
	}
	
	/**
	 * 预售
	 * @return
	 */
	public Short getSellTypeYS(){
		return Product.SELLTYPE_YS;
	}
	
	/**
	 * 抢购
	 * @return
	 */
	public Short getSellTypeQG(){
		return Product.SELLTYPE_QG;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getIsHaveInventory() {
		return isHaveInventory;
	}
	public void setIsHaveInventory(int isHaveInventory) {
		this.isHaveInventory = isHaveInventory;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
 
	public int getPageSize() {
		return limit;
	}

	public void setPageSize(int pageSize) {
		this.limit = pageSize;
	}

	public List<ProductSku> getProductSkus() {
		return productSkus;
	}

	public void setProductSkus(List<ProductSku> productSkus) {
		this.productSkus = productSkus;
	}


	public BasePagination<ProductSku> getProductSkuPage() {
		return productSkuPage;
	}

	public void setProductSkuPage(BasePagination<ProductSku> productSkuPage) {
		this.productSkuPage = productSkuPage;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public Prop getCurrentProp() {
		return currentProp;
	}

	public void setCurrentProp(Prop currentProp) {
		this.currentProp = currentProp;
	}
	
	/**
	 * 商品类别-分类
	 * @return
	 */
	public int getTypeFenLei() {
		return CommonConstants.TYPE_FENLEI;
	}
	
	/**
	 * 商品类别-原产地
	 * @return
	 */
	public int getTypeYuanChanDi() {
		return CommonConstants.TYPE_YUANCHANDI;
	}
	
	/**
	 * 商品类别-抢购专区
	 * @return
	 */
	public int getTypeQiangGou() {
		return CommonConstants.TYPE_QIANGGOU;
	}
	
	/**
	 * 商品类别-预售专区
	 * @return
	 */
	public int getTypeYuShou() {
		return CommonConstants.TYPE_YUSHOU;
	}
	
	/**
	 * 商品类别-普通专区
	 * @return
	 */
	public int getTypePuTong() {
		return CommonConstants.TYPE_PUTONG;
	}

	public long getCurrentTimestamp() {
		return currentTimestamp;
	}
	
	
}
