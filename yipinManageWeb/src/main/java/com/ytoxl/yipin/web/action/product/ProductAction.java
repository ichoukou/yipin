package com.ytoxl.yipin.web.action.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductProp;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.ProductSkuOptionValue;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.dataobject.SkuOption;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.PropService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.yipin.web.action.BaseAction;

@SuppressWarnings("serial")
public class ProductAction  extends BaseAction{
	private static Logger logger = LoggerFactory.getLogger(ProductAction.class);
	
	private static final String SEARCH_SELLER_PRODUCTS = "searchSellerProducts";
	private static final String SEARCH_PRODUCTS = "searchProducts";
	private static final String VIEW_PRODUCT = "viewProduct";
	private static final String EDIT_PRODUCT = "editProduct";
	private static final String LIST_PRODUCT_SKU_DETAIL = "listProductSkuDetail";
	private static final String SEA_PRODUCT = "seaProduct";

	private static final String LIST_PRODUCT_SKU = "listProductSku";

	private static final String SHOW_PRODUCT = "showProduct";

	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private PropService propService;
	private List<SkuOption> skuOptions; // 商品SKU信息
	private BasePagination<Product> productPage;
	private Product product;
	private List<ProductSku> productSkuList;
	private List<Prop> propList;
//	private Prop[] prop;
	private int mark;//查看、审核表示
	private String selectKey;
	private Integer level;
	private String[] area;
	private String[] catalog;
	private String adaptPeople;
	/**
	 * <p>Field productlist: 商品集合</p>
	 */
	private Map<String,List<Product>> productlist ;
	
	/**
	 * <p>Field productSkulist: 商品sku集合</p>
	 */
	private Map<String,List<ProductSku>> productSkulist ;

	/**
	 * <p>Field firstChar: 首字符</p>
	 */
	private String firstChar; 
	
	/**
	 * <p>Field saleType: 商品售卖类型</p>
	 */
	private String saleType;
	
	/**
	 * <p>Field zoneId: 专区ID</p>
	 */
	private String zoneId;
	

	/**
	 * 分页查询商品（管理员） 
	 * @return
	 */
	public String searchProducts(){
		try {
			if (productPage == null) {
				productPage = new BasePagination<Product>();
			}
			productService.searchProducts(productPage);
		} catch (YiPinStoreException e) {
			logger.error("查询商品出错", e);
		}
		return SEARCH_PRODUCTS;
	}
	
	/**
	 * 点击商品信息时获取id，查询商品的sku信息
	 * @return
	 */
	public String listProductSkuDetail(){
		try {
			productSkuList = productService.listProductSkuDetail(product);
		} catch (YiPinStoreException e) {
			logger.error("获取商品sku信息出错", e);
		}
		return LIST_PRODUCT_SKU_DETAIL;
	}
	
	/**
	 * 查看商品
	 * @return
	 */
	public String view(){
		try {
			product = productService.getProductByProductId(product.getProductId());
		} catch (YiPinStoreException e) {
			logger.error("获取商品出错", e);
		}
		return VIEW_PRODUCT;
	}
	
	/**
	 * 审核商品（管理员）
	 * @return
	 */
	public String review(){
		try {
			product = productService.getProductByProductId(product.getProductId());
		} catch (YiPinStoreException e) {
			logger.error("获取商品出错", e);
		}
		return VIEW_PRODUCT;
	}
	
	/**
	 * 保存审核结果（管理员）
	 * @return
	 */
	public String saveReviewResult(){
		try {
			productService.saveReviewResult(product);
			setMessage(Boolean.TRUE.toString(),"审核成功");
		} catch (YiPinStoreException e) {
			logger.error("审核商品出错", e);
			setMessage(Boolean.FALSE.toString(), "审核商品出错");
		}
		return JSONMSG;
	}
	
	/**
	 * <p>Description: 根据品牌首字符查找商品信息</p>
	 * @return 商品列表
	 */
	public String listProducts(){
		try {
			this.productlist=this.productService.listProductByBarndFirstChar(zoneId,firstChar,saleType);
		} catch (YiPinStoreException e) {
			logger.error("查询商品出错", e);
			setMessage(Boolean.FALSE.toString(), "查询商品出错");
		}
		return SEA_PRODUCT;
	}
	
	/**
	 * <p>Description: 根据品牌首字符查找商品SKU信息</p>
	 * @return 商品列表
	 */
	public String listProductsSku(){
		try {
			this.productSkulist=this.productService.listProductByBarndFirstChar(firstChar);
		} catch (YiPinStoreException e) {
			logger.error("查询商品出错", e);
			setMessage(Boolean.FALSE.toString(), "查询商品出错");
		}
		return LIST_PRODUCT_SKU;
	}
	
	/**
	 * <p>Description: 根据品牌首字符查找商品信息</p>
	 * @return 商品列表
	 */
	public String showProducts(){
		try {
			this.productlist=this.productService.listProductByZonId(this.zoneId);
			if(this.productlist ==null){
				this.productSkulist=this.productService.listProductSkuByZoneId(this.zoneId);
			}
		} catch (YiPinStoreException e) {
			logger.error("查询商品出错", e);
			setMessage(Boolean.FALSE.toString(), "查询商品出错");
		}
		return SHOW_PRODUCT;
	}
	
	/**
	 * 查询商品(卖家)
	 * @return
	 */
	public String searchSellerProducts(){
		try {
			if (productPage == null) {
				productPage = new BasePagination<Product>();
			}
			//获取当前登陆商家ID
			Integer sellerId = userInfoService.searchUserIdByUserType();
			productService.searchSellerProducts(productPage, sellerId);

		} catch (YiPinStoreException e) {
			logger.error("查询商品出错", e);
		}
		return SEARCH_SELLER_PRODUCTS;
	}
	
	/**
	 * 编辑商品
	 * @return
	 */
	public String edit(){
		if(product!=null&&product.getProductId()!=null){
			try {
				//获取当前登陆商家ID
				Integer userId = userInfoService.searchUserIdByUserType();
				product = productService.getProduct4Edit(product.getProductId(), userId);
			} catch (YiPinStoreException e) {
				logger.error("编辑商品出错", e);
			}
		}
		return EDIT_PRODUCT;
	}
	
	public String addProduct(){
		return edit();
	}
	
	/**
	 * 保存商品
	 * @return
	 */
	public String saveProduct(){
		try {
			//获取当前登陆商家ID
			Integer userId = userInfoService.searchUserIdByUserType();
			product.setUserId(userId);
			String cc = "{\"area\":[{\"province\":\""+area[0]+"\",\"city\":\""+area[1]+"\"}]," +
					"\"catalog\":[{\"big\":\""+catalog[0]+"\",\"small\":\""+catalog[1]+"\"}]," +
							"\"adaptPeople\":[{\"people\":\""+adaptPeople+"\"}]}";
			product.setProductProp(cc);
			List<ProductProp> productPropList = new ArrayList<ProductProp>();
			ProductProp ppArea = new ProductProp();
			ppArea.setPropId(Integer.parseInt(area[0]));
			productPropList.add(ppArea);
			ProductProp ppCatalog = new ProductProp();
			ppCatalog.setPropId(Integer.parseInt(catalog[0]));
			productPropList.add(ppCatalog);
			product.setPpList(productPropList);
			List<ProductSku> productSkus = product.getProductSkus();
			productService.saveProduct(product);
			String str = "";
			for(int i=0; productSkus!=null&&i<productSkus.size(); i++){
				ProductSku productSku = productSkus.get(i);
				String id = "";
				List<ProductSkuOptionValue> proSkuValues = productSku.getProductSkuOptionValues();
				for(int j=0; proSkuValues!=null&&j<proSkuValues.size(); j++){
					ProductSkuOptionValue proSkuValue = proSkuValues.get(j);
					if(proSkuValue!=null){
						id += proSkuValue.getSkuOptionValueId() + "_";
					}
				}
				if(id.length() > 0){
					if(id.indexOf('_') != id.length() - 1 ){
						id = id.substring(0, id.length() -1);	
					}
				}else{
					id = "_";
				}
				str += "{\"id\":\""+id+"\",\"productSkuId\":\""+productSku.getProductSkuId()+"\"," +
						"\"unitPrice\":\""+productSku.getUnitPrice()+"\"," +
						"\"inventory\":\""+productSku.getInventory()+"\"," +
						"\"skuCode\":\""+productSku.getSkuCode()+"\"},";
			}
			if(str.length() > 0){
				str = "[" + str.substring(0, str.length()-1) + "]";	
			}
			str = "{\"productId\":\""+product.getProductId()+"\",\"productSkus\":"+str+"}";
			setMessage(Boolean.TRUE.toString(),str);
		} catch (YiPinStoreException e) {
			logger.error("保存商品出错", e);
			setMessage(Boolean.FALSE.toString(), e.getMessage());	
		}
    	return JSONMSG;
	}
	/**
	 * 快速保存
	 * @return
	 */
	public String quickSave(){
		try {
			Integer sellerId = userInfoService.searchUserIdByUserType();
			product.setUserId(sellerId);
			productService.updatequickProduct(product);
			setMessage(Boolean.TRUE.toString(),"修改成功");
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage());
			setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
		}
		return JSONMSG;
	}
	/**
	 * 删除商品
	 */
	public String delete(){
		try {
			//获取当前登陆商家ID
			Integer userId = userInfoService.searchUserIdByUserType();
			product.setUserId(userId);
			productService.deleteProduct(product);
			setMessage(Boolean.TRUE.toString(),"删除成功");
		} catch (YiPinStoreException e) {
			logger.error("删除商品出错", e);
			setMessage(Boolean.FALSE.toString(), "删除商品出错");
		}
    	return JSONMSG;
	}
	/**
	 * 售罄
	 * @return
	 */
	public String sellOut(){
		try {
			//获取当前登陆商家ID
			Integer userId = userInfoService.searchUserIdByUserType();
			product.setUserId(userId);
			productService.sellOut(product);
			setMessage(Boolean.TRUE.toString(),"售罄成功");
		} catch (YiPinStoreException e) {
			logger.error("售罄操作出错", e);
			setMessage(Boolean.FALSE.toString(), "售罄操作出错");
		}
		return JSONMSG;
	}
	/**
	 * 快速编辑商品
	 * @return
	 */
	public String quickEdit(){
		return "quickEditProduct";
	}
	/**
	 * 提交审核
	 */
	public String submitReview(){
		try {
			//获取当前登陆商家ID
			Integer userId = userInfoService.searchUserIdByUserType();
			product.setUserId(userId);
			String cc = "{\"area\":[{\"province\":\""+area[0]+"\",\"city\":\""+area[1]+"\"}]," +
					"\"catalog\":[{\"big\":\""+catalog[0]+"\",\"small\":\""+catalog[1]+"\"}]," +
							"\"adaptPeople\":[{\"people\":\""+adaptPeople+"\"}]}";
			product.setProductProp(cc);
			List<ProductProp> productPropList = new ArrayList<ProductProp>();
			ProductProp ppArea = new ProductProp();
			ppArea.setPropId(Integer.parseInt(area[0]));
			productPropList.add(ppArea);
			ProductProp ppCatalog = new ProductProp();
			ppCatalog.setPropId(Integer.parseInt(catalog[0]));
			productPropList.add(ppCatalog);
			product.setPpList(productPropList);
			productService.saveProduct(product);
			
			setMessage(Boolean.TRUE.toString(),new String[]{"提交成功",product.getProductId().toString()});
		} catch (YiPinStoreException e) {
			logger.error("提交商品出错", e);
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		}
		return JSONMSG;
	}
	
	/**
	 * 提交审核并复制
	 */
	public String submitReviewAndCopy(){
		try {
			//获取当前登陆商家ID
			Integer userId = userInfoService.searchUserIdByUserType();
			product = productService.getProductCopy(product.getProductId(),userId);
		} catch (YiPinStoreException e) {
			logger.error("提交审核并复制操作出错", e);
		}
		return EDIT_PRODUCT;
	}
	
	/**
	 * 获取全部品牌
	 * @return
	 */
	public List<Brand> getBrands() {
		List<Brand> brands = new ArrayList<Brand>();
		try {
			brands = brandService.listBrands();
		} catch (YiPinStoreException e) {
			logger.error("获取全部全牌出错", e);
		}
		return brands;
	}

	/**
	 * 获取卖家可售品牌
	 * @return
	 */
	public List<Brand> getSellerBrands() {
		List<Brand> sellerBrands = new ArrayList<Brand>();
		try {
			//获取当前登陆商家ID
			Integer userId = userInfoService.searchUserIdByUserType();
			sellerBrands = brandService.listBrandsBySellerId(userId);
		} catch (YiPinStoreException e) {
			logger.error("获取卖家可售品牌出错", e);
		}
		return sellerBrands;
	}

	/**
	 * 获取系统默认sku数据
	 * @return
	 */
	public List<SkuOption> getSkuOptions() {
		try {
			skuOptions = productService.getSkuOptions();
		} catch (YiPinStoreException e) {
			logger.error("获取系统默认sku数据出错", e);
		}
		return skuOptions;
	}
	public String getPropMsg(){
		try {
			Prop prop = new Prop();
			if(level == null){
				prop.setParentId(Integer.parseInt(selectKey));
			}else{
				prop.setCode(selectKey);
				prop.setLevel(level);
			}
			prop.setStatus(Prop.STATUS_NORMAL);
			propList = propService.listByProp(prop);
			Map<String,Object> map = new HashMap<String,Object>(); 
			map.put("data", propList);
			map.put("status",200);
			String propJson = JSON.toJSONString(map);
			setMessage(propJson);
		} catch (YiPinStoreException e) {
			logger.error("获取分类、原产地失败", e);
		}
		return JSONMSG;
	}
	/**
	 * 商品状态
	 * @return
	 */
	public Short[] getStatuses() {
		return Product.STATUSES;
	}
	
	/**
	 * 审核状态
	 * @return
	 */
	public Short[] getReviewStatuses() {
		return Product.REVIEWSTATUSES;
	}
	
	public Short[] getFrontStatuses() {
		return Product.FRONTSTATUSES;
	}
	
	/**
	 * 是否显示库存
	 * @return
	 */
	public Short[] getIsShowInventorys(){
		return Product.ISSHOWINVENTORYS;
	}
	
	/**
	 * 不显示库存
	 * @return
	 */
	public Short getIsShowInventoryNo(){
		return Product.IS_SHOW_INVENTORY_NO;
	}

	public BasePagination<Product> getProductPage() {
		return productPage;
	}

	public void setProductPage(BasePagination<Product> productPage) {
		this.productPage = productPage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductSku> getProductSkuList() {
		return productSkuList;
	}

	public void setProductSkuList(List<ProductSku> productSkuList) {
		this.productSkuList = productSkuList;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public void setSkuOptions(List<SkuOption> skuOptions) {
		this.skuOptions = skuOptions;
	}

	public List<Prop> getPropList() {
		return propList;
	}

	public void setPropList(List<Prop> propList) {
		this.propList = propList;
	}

	public String getSelectKey() {
		return selectKey;
	}

	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String[] getArea() {
		return area;
	}

	public void setArea(String[] area) {
		this.area = area;
	}

	public String[] getCatalog() {
		return catalog;
	}

	public void setCatalog(String[] catalog) {
		this.catalog = catalog;
	}

	public String getAdaptPeople() {
		return adaptPeople;
	}

	public void setAdaptPeople(String adaptPeople) {
		this.adaptPeople = adaptPeople;
	}
	
	/**
	 * <p>Description: 首字符</p>
	 * @return the firstChar
	 */
	public String getFirstChar() {
		return this.firstChar;
	}

	/**
	 * <p>Description: 首字符</p>
	 * @param firstChar the firstChar to set
	 */
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
	/**
	 * <p>Description: 商品集合</p>
	 * @return the productlist
	 */
	public Map<String, List<Product>> getProductlist() {
		return this.productlist;
	}

	/**
	 * <p>Description: 商品集合</p>
	 * @param productlist the productlist to set
	 */
	public void setProductlist(Map<String, List<Product>> productlist) {
		this.productlist = productlist;
	}

	/**
	 * <p>Description: 商品售卖类型</p>
	 * @return the saleType
	 */
	public String getSaleType() {
		return this.saleType;
	}

	/**
	 * <p>Description: 商品售卖类型</p>
	 * @param saleType the saleType to set
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	/**
	 * <p>Description: 商品sku集合</p>
	 * @return the productSkulist
	 */
	public Map<String, List<ProductSku>> getProductSkulist() {
		return this.productSkulist;
	}

	/**
	 * <p>Description: 商品sku集合</p>
	 * @param productSkulist the productSkulist to set
	 */
	public void setProductSkulist(Map<String, List<ProductSku>> productSkulist) {
		this.productSkulist = productSkulist;
	}
	
	/**
	 * <p>Description: 专区ID</p>
	 * @return the zoneId
	 */
	public String getZoneId() {
		return this.zoneId;
	}

	/**
	 * <p>Description: 专区ID</p>
	 * @param zoneId the zoneId to set
	 */
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

}
