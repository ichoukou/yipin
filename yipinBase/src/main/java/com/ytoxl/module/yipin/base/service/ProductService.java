package com.ytoxl.module.yipin.base.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.dataobject.SkuOption;
import com.ytoxl.module.yipin.base.dataobject.Zone;


public interface ProductService {
	/**
	 * 查询抢购专区总数量
	 */
   public Integer getCountProductByZoneSpecial(Integer zoneId) throws YiPinStoreException;
    /**
     * 查询普通专区总数量
     */
   public Integer getCountProductByZoneId(Integer zoneId) throws YiPinStoreException;
	
	/**
	 * 根据productSkuId查询productSku信息
	 * @param id
	 * @return
	 * @throws YiPinStoreException
	 */
	public ProductSku getProductSkuById(Integer id) throws YiPinStoreException;
	/**
	 * 当点击提交订单时根据productskuId和库存验证商品的状态
	 * @param id
	 * @param num
	 * @return
	 * @throws YiPinStoreException
	 */
	public ProductSku getProductSkuActivityStatus(Integer id,Integer num) throws YiPinStoreException;
	
	/**
	 * 管理员分页查询商品信息
	 * @param productPage
	 * @throws YiPinStoreException
	 */
	public void searchProducts(BasePagination<Product> productPage) throws YiPinStoreException;
	
	/**
	 * 根据商品id获取商品的sku信息，如售价、库存、颜色、尺寸等
	 * @param productId
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<ProductSku> listProductSkuDetail(Product product) throws YiPinStoreException;

	/**
	 * 根据商品ID查找一类商品
	 * @param productId
	 * @return
	 */
	public Product getProductByProductId(Integer productId) throws YiPinStoreException;
	
	/**
	 * 保存审核结果审核
	 * @param product
	 */
	public void saveReviewResult(Product product) throws YiPinStoreException;
	
	/**
	 * 查询卖家商品
	 * @param productPage
	 * @param sellerId
	 */
	public void searchSellerProducts(BasePagination<Product> productPage, Integer sellerId) throws YiPinStoreException;
	
	/**
	 * @作者：罗典
	 * @描述：根据商家ID查询审核通过且库存大于0的商品
	 * @参数：sellerId 商家ID，brandId 品牌ID
	 * @时间：2013-12-10
	 * */
	public List<Product> listPassProBySellerId(Integer sellerId,Integer brandId) throws YiPinStoreException;
	
	/**
	 * 根据商品ID删除一件商品，同时删除对应的SKU。
	 * @param productId
	 */
	public void deleteProduct(Product product) throws YiPinStoreException;
	
	/**
	 * 获取SKU选项信息
	 * @return
	 */
	public List<SkuOption> getSkuOptions() throws YiPinStoreException;	
	
	/**
	 * 得到商品信息副本用于预览、复制
	 * @param product
	 * @return
	 */
	public Product getProductCopy(Integer productId,Integer userId) throws YiPinStoreException;
	
	/**
	 * 保存商品
	 * @param product
	 */
	public void saveProduct(Product product) throws YiPinStoreException;
	
	/**
	 * 获取商家商品
	 * @param productId
	 * @param sellerId
	 * @return
	 */
	public Product getProductByProductIdUserId(Integer productId, Integer userId) throws YiPinStoreException;
	
	/**
	 * 获取商品信息用于修改页面
	 * @param productId
	 * @return
	 */
	public Product getProduct4Edit(Integer productId, Integer userId) throws YiPinStoreException;
	
	/**
	 * 售馨
	 * @param product
	 * @throws UhomeStoreException
	 */
	public void sellOut(Product product) throws YiPinStoreException;
	
	 /**
     * <p>
     * Description: 购买商品陈列展示数据
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 商品集合
     * @throws YiPinStoreException
     */
    public List<Product> listProductsByBrandIdAndStatus(Integer brandId) throws YiPinStoreException;
    public Map<String,Product> listProductsByBrandIdAndStatus2(Integer brandId) throws YiPinStoreException;
    
    
    /**
     * <p>
     * Description: 商品陈列售罄展示数据图片替换
     * </p>
     * 
     * @param products 商品集合
     * @return 商品集合
     * @throws YiPinStoreException
     */
    public List<Product> checkSellOut(List<Product> products) throws YiPinStoreException;
    
    /**
     * 减库存
     * @param productSkuId
     * @param num
     */
    public Integer updateInventory4Reduce(Integer productSkuId, Integer num) throws YiPinStoreException;
    
    /**
     * 取消订单时,归还库存
     * @param productSkuId
     * @param num
     * @return
     */
    public Integer updateInventory4Revert(Integer productSkuId, Integer num) throws YiPinStoreException;
    /**
     * <p>
     * Description: 根据查询商品 ksuCode
     * </p>
     * 
     * @param skuCode 
     * @return 商品集合
     */
    public Map<String,Product> searchProductBySkuCode() throws YiPinStoreException;
    
    /**
     * 根据skuCode查询商品信息集合
     * @param skuCodes
     * @return
     */
    public Map<String, ProductSku> listProductSkusBySkuCode() throws YiPinStoreException;
   
   
    /**
     * <p>Description: 根据商品skuId获取品牌ID</p>
     * @param productSkuId 品牌ID
     * @return  
     * @throws YiPinStoreException
     */
    public Integer getBrandIdBySkuId(Integer productSkuId)throws YiPinStoreException;
    /**
     * <p>
     * Description: 根据专区预售类型查找商品
     * </p>
     * 
     * @param 
     * @return 商品集合
     */
    public List<Product>  getProductByZoneSale()throws YiPinStoreException;
    /**
     * <p>
     * Description: 根据专区普通类型查找商品
     * </p>
     * 
     * @param 
     * @return 商品集合
     */
    public List<Product>  getProductByZoneDefault() throws YiPinStoreException;
    /**
     * <p>
     * Description: 根据专区抢购类型查找商品
     * </p>
     * 
     * @param 
     * @return 商品集合
     */
    public List<ProductSku>  getProductByZoneSpecial(List<Integer> zoneIds) throws YiPinStoreException;
    
   
    /**
     * 根据分类或者原产地查询商品信息集合
     * 
     * @param 参数类型
     * 			Map
     * 
     * @param code
     * 			分类或者原产地code
     * @param isHaveInventory
     * 			是否只是查询有库存商品(isHaveInventory=1只查询有库存商品)
     * @param orderType
     * 			排序(0:默认排序,1:按销量降序,2:按浏览量降序,3:按价格从低到高,4:按价格从高到低)
     * @param pageNo 
     * 			分页号
     * @param pageSize
     * 			每页显示数目 
     * @param skuType 
     * 			查询的规格类型(1:默认,2:价格最低)
     * @return
     */
    List<ProductSku> listProductSkusByMap(Map<String, Object> paramsMap) throws YiPinStoreException;
	
    /**
     * 查询分类或者原产地查询商品信息总数
     *
     * @param 参数类型
     * 			Map
     *
     * @param code
     * 			分类或者原产地code
     * @param isHaveInventory
     * 			是否只是查询有库存商品(isHaveInventory=1只查询有库存商品)
     * @param orderType
     * 			排序(0:默认排序,1:按销量降序,2:按浏览量降序,3:按价格从低到高,4:按价格从高到低)
     * @return
     */
    int countProductSkusByMap(Map<String, Object> paramsMap);
  
    /**
     * 前台获取商品详细信息
     * @param id
     * @param type
     * @return
     */
    public Product getProductById4Front(Integer skuId) throws YiPinStoreException;
	
	/**
	 * 根据productId获取productSku数据(含sku属性数据)
	 * @param productId
	 * @return
	 */
	public List<ProductSku> listProductSkus(Integer productId) throws YiPinStoreException;
	
	/**
	 * 设置sku销售状态
	 * @param productSku
	 * @return
	 */
	public void setSkuSaleStatus(ProductSku productSku) throws YiPinStoreException;
	
	/**
	 * 设置sku属性数据
	 * @param productSku
	 * @return
	 */
	public void setProductSkuProps(ProductSku productSku) throws YiPinStoreException;
	
	/**
	 * 计算运费
	 * @param productSku
	 * @return
	 */
	public BigDecimal calPostage(ProductSku productSku) throws YiPinStoreException;
	
	/**
	 * 获取默认展示sku
	 * @param productSkus
	 * @return
	 */
	public ProductSku getDefaultProductSku(List<ProductSku> productSkus) throws YiPinStoreException;

	/**
	 * 
	 * @param productSkus
	 * @return
	 */
	public Integer getTotalInventory(List<ProductSku> productSkus) throws YiPinStoreException;
	
    /**
	 * 根据商品的id将商品的 hits加1 商品详情页面用
	 * @param product
	 */
	public void updateProductHits(Product product) throws YiPinStoreException;
	
	/**
	 *  抢购1 预售2专区3 根据专区Id查找商品 
	 * @param product
	 */
	public List<Product> getProductByZoneId(List<Integer> zoneIds) throws DataAccessException;
	/**
	 *  抢购1 预售2专区3 根据专区Id查找商品 
	 * @param product
	 * @throws YiPinStoreException 
	 */
	public List<Product> getProductByZoneId(Zone zone, Integer limit) throws DataAccessException, YiPinStoreException;
	
	/**
	 * 根据商品的id查询商品产地
	 * 
	 * @param productId
	 * @return
	 */
	public List<Prop> listPropsOfProduct(Integer productId);
	
	/**
	 * 快速更新商品信息
	 * @param product
	 */
	public void updatequickProduct(Product product) throws YiPinStoreException;
	
	/**
	 * <p>
	 * Description: 根据品牌首字母查找商品信息
	 * </p>
	 * 
	 * @param firstChar  首字母
	 *           
	 * @param saleType  销售类型
	 * @return 商品信息
	 * @throws YiPinStoreException
	 */
	public Map<String,List<Product>> listProductByBarndFirstChar(String firstChar, String saleType)throws YiPinStoreException;
	/**
	 * <p>Description: 根据品牌首字母查找商品SKU信息</p>
	 * @param firstChar
	 * @return SKU信息
	 * @throws YiPinStoreException
	 */
	public Map<String, List<ProductSku>> listProductByBarndFirstChar(String firstChar)throws YiPinStoreException;
	/**
	 * <p>Description: 查询专区商品</p>
	 * @param zoneId 专区ID
	 * @return 商品集合
	 */
	public Map<String, List<Product>> listProductByZonId(String zoneId)throws YiPinStoreException;
	/**
	 * 根据productId和类型获取分类或者原产地信息
	 * @param productId
	 * @param type
	 * @return
	 * @throws YiPinStoreException
	 */
	public Product getProductCatologOrArea(Integer productId) throws YiPinStoreException;
	/**
	 * <p>Description: 查询专区SKU</p>
	 * @param zoneId 专区ID
	 * @return SKU集合
	 */
	public Map<String, List<ProductSku>> listProductSkuByZoneId(String zoneId)throws YiPinStoreException;
	
	/**
	 * 根据商品ID获取sku(默认sku库存为0时，取库存不为0且价格最小的sku， 如没有就直接用默认sku)
	 * @param productId
	 * 			产品id
	 * @throws YiPinStoreException 
	 * **/
	public ProductSku listProductSkusByProductId(Integer productId) throws YiPinStoreException  ;
	
	/**
	 * 查询sku的值
	 * @param productSkuId
	 * @return
	 * @throws YiPinStoreException
	 */
	public Map<String, String> getOverrideSkuOptionValue(Integer productSkuId) throws YiPinStoreException;
	/**
	 * <p>Description: TODO</p>
	 * @param zoneId
	 * @param firstChar
	 * @param saleType
	 * @return
	 */
	public Map<String, List<Product>> listProductByBarndFirstChar(
			String zoneId, String firstChar, String saleType)throws YiPinStoreException;
}
