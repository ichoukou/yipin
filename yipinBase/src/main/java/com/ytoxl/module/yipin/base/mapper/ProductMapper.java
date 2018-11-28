package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductProp;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.SkuOption;
import com.ytoxl.module.yipin.base.dataobject.SkuOptionValue;
import com.ytoxl.module.yipin.base.dataobject.Zone;

/**
 * 产品
 * 
 * @author user
 * 
 * @param <T>
 */
public interface ProductMapper<T extends Product> extends BaseSqlMapper<T> {

    public ProductSku getProductSkuById(Integer id) throws DataAccessException;
	/**
	 * 查询抢购专区总数量
	 */
   public Integer getCountProductByZoneSpecial(@Param("zoneId") Integer zoneId) throws DataAccessException;
    /**
     * 查询普通专区总数量
     */
   public Integer getCountProductByZoneId(@Param("zoneId") Integer zoneId) throws DataAccessException;
    
    /**
     * 管理员后台搜索查询商品
     * 
     * @param map
     * @return
     */
    public List<Product> searchProducts(Map<String, Object> map) throws DataAccessException;

    public List<Product> searchProductsCount(Map<String, Object> map) throws DataAccessException;

    /**
     * 根据商品id获取sku信息
     * 
     * @param productId
     * @return
     * @throws DataAccessException
     */
    public List<ProductSku> searchProductSkuDetail(Integer productId) throws DataAccessException;

    /**
     * 更新商品审核结果
     * 
     * @param product
     */
    public void updateProductReviewResult(Product product) throws DataAccessException;

    /**
     * 卖家后台搜索查询商品
     * 
     * @param map
     * @return
     */
    public List<Product> searchSellerProducts(Map<String, Object> map) throws DataAccessException;

    public List<Product> searchSellerProductsCount(Map<String, Object> map) throws DataAccessException;

    /**
     * 卖家更新商品状态
     * 
     * @param product
     */
    public void updateProductStatus(Product product) throws DataAccessException;

    /**
     * 查询一条卖家商品信息
     * 
     * @param productId
     * @param sellerId
     */
    public Product getProductByProductIdAndUserId(@Param("productId") Integer productId, @Param("userId") Integer userId)
            throws DataAccessException;
    /**
	 * @作者：罗典
	 * @描述：根据商家ID查询审核通过且库存大于0的商品
	 * 审核通过3
	 * 库存大于0 productSku.inventory
	 * */
	public List<Product> listPassProBySellerId(Map<String,Integer> map) throws YiPinStoreException;
    /**
     * 根据productId查询商品SKU选项数据
     * 
     * @param productId
     * @return
     */
    public List<SkuOption> listProductSkuOptionsByProductId(Integer productId) throws DataAccessException;

    /**
     * 根据skuOptionId查询商品SKU选项值数据
     * 
     * @param skuOptionId
     * @return
     */
    public List<SkuOptionValue> listProductSkuOptionValuesBySkuOptionId(@Param("skuOptionId") Integer skuOptionId,
            @Param("productId") Integer productId) throws DataAccessException;

    /**
     * <p>
     * Description: 购买商品陈列展示数据
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 商品集合
     */
    public List<Product> listProductsByBrandIdAndStatus(Integer brandId) throws YiPinStoreException;
	 /**
     * <p>
     * Description: 根据查询商品 ksuCode
     * </p>
     * 
     * @param skuCode 
     * @return 商品集合
     */
    public List<Product> searchProductBySkuCode() throws YiPinStoreException;
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
    public List<Product>  getProductByZoneDefault()throws YiPinStoreException;
    /**
     * <p>
     * Description: 根据专区抢购类型查找商品
     * </p>
     * 
     * @param 
     * @return 商品集合
     */
    public List<ProductSku>  getProductByZoneSpecial(List<Integer> zoneIds)throws YiPinStoreException;
    /**
     * <p>
     * Description: 根据专区抢购类型查找商品
     * </p>
     * 
     * @param 
     * @return 商品集合
     */
    public List<Product>  getProductByZoneSpecial(@Param("zoneId")Integer zoneId, @Param("limit")Integer limit) throws YiPinStoreException;
  	
    /**
     * <p>Description: 根据商品skuId获取品牌ID</p>
     * @param productSkuId 商品skuId
     * @return 品牌ID
     * @throws YiPinStoreException 
     */
    public Integer getBrandIdBySkuId(Integer productSkuId)throws YiPinStoreException;
    
    /**
     * 根据productId查询商品信息
     * @param productId
     * @return
     */
    public Product getProductBySkuId(Integer skuId) throws DataAccessException;
    
    /**
     * 查询普通专区商品信息
     * @param productId
     * @return
     */
	public Product getZoneProduct(@Param("productId") Integer productId,
			@Param("zoneId") Integer zoneId, @Param("zoneType") Short zoneType) throws DataAccessException;
 
    /**
     * 查询抢购商品信息
     * @param productSkuId
     * @return
     */
    public Product getScareBuyProductByProductSkuId(@Param("productSkuId")Integer productSkuId, @Param("zoneId")Integer zoneId) throws DataAccessException;
    
    /**
	 * 根据商品的id将商品的 hits加1 商品详情页面用
	 * @param product
	 */
	public void updateProductHits(Product product) throws DataAccessException;
	/**
	 *   	 抢购1 预售2专区3 根据专区Id查找商品 
	 */
	
	public List<Product> getProductByZoneId(List<Integer>  zoneIds) throws DataAccessException;
	
	/**
	 *   	 抢购1 预售2专区3 根据专区Id查找商品 
	 */
	
	public List<Product> getProductByZoneId(@Param("zone")Zone zone, @Param("limit")Integer limit) throws DataAccessException;
	
	/**
	 * <p>Description: 根据品牌首字母查询商品信息</p>
	 * @param firstChar 品牌首字母
	 * @return 商品集合
	 */
	public List<Product> listProductByBarndFirstChar(String firstChar);

	/**
	 * <p>Description: 根据品牌首字母查询商品信息(销售)</p>
	 * @param firstChar
	 * @return
	 */
	public List<Product> listProductByBarndFirstCharAndXs(String firstChar);

	/**
	 * <p>Description: 根据品牌首字母查询商品信息(预售)</p>
	 * @param firstChar
	 * @return
	 */
	public List<Product> listProductByBarndFirstCharAndYs(String firstChar);

	/**
	 * <p>Description: 根据品牌首字母查询商品信息(抢购)</p>
	 * @param firstChar
	 * @return
	 */
	public List<Product> listProductByBarndFirstCharAndQg(String firstChar);
	
	public void addProductProp(ProductProp productProp) throws DataAccessException; 
	
	public void delProductProp(Integer productId) throws DataAccessException;

	/**
	 * <p>Description: 根据专区ID查询商品  销售</p>
	 * @param zoneId 专区ID
	 * @return 商品集合
	 */
	public List<Product> listProductByZoneIdAndXs(Integer zoneId);

	/**
	 * <p>Description: 根据专区ID查询商品  预售</p>
	 * @param zoneId 专区ID
	 * @return 商品集合
	 */
	public List<Product> listProductByZoneIdAndYs(Integer zoneId);

}
