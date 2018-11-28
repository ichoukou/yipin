package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.Prop;


/**
 * 产品SKU
 * @author huayucai
 * 
 * @param <T>
 */
public interface ProductSkuMapper<T extends ProductSku> extends BaseSqlMapper<T> {
	
	/**
	 * 根据skuCode查询数据
	 * @param skuCode
	 * @return
	 */
	public ProductSku getBySkuCode(ProductSku productSku) throws DataAccessException;
	
	/**
	 * 逻辑删除多条SKU数据
	 * @param productSkus
	 */
	public void logicDelBatch(List<ProductSku> productSkus) throws DataAccessException;
	
	/**
	 * 根据productId, sellerId查询商品SKU信息
	 * @param productId
	 * @param sellerId
	 * @return
	 */
	public List<Integer> listProductSkuIdsByProductIdAndUserId(@Param("productId") Integer productId, @Param("userId") Integer userId) throws DataAccessException;
	
	/**
	 * 更新库存为0
	 * @param productSkuIds
	 */
	public void updateInventoryByIds(List<Integer> productSkuIds) throws DataAccessException;

	/**
	 * 根据productId查询商品SKU信息
	 * @param productId
	 * @return
	 */
	public List<ProductSku> listProductSkusByProductId(Integer productId)
			throws DataAccessException;

	/**
	 * 根据productId删除商品SKU信息
	 * @param productId
	 */
	public void delProductSkuByProductId(Integer productId)
			throws DataAccessException;
	
	/**
	 * 根据购物车中的productIds获取商品信息
	 * @param produc
	 * @return
	 * @throws DataAccessException
	 */
//	public List<ProductSku> listShoppingCartProducts(List<Integer> producIds) throws DataAccessException;
	
	/**
	 * 减库存
	 * @param productSkuId
	 * @param num
	 * @return
	 */
	public Integer changeProductSkuInventory(@Param("productSkuId") Integer productSkuId, @Param("num") Integer num)  throws DataAccessException;
	
	/**
	 * 发货时  添加销售量
	 * @param productSkuId
	 * @param num
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateSaleQualtity(@Param("productSkuId") Integer productSkuId,@Param("num") Integer num)throws DataAccessException;
	
	/**
	 * 取消订单根据库存类型和产品id，去归还库存
	 * @param type
	 * @param productSkuId
	 * @param num
	 * @return
	 * @throws DataAccessException
	 */
	public Integer revertProductSkuInventory(@Param("productSkuId") Integer productSkuId, @Param("num") Integer num)  throws DataAccessException;
	
	/**
	 * 根据skuId获取sku对象
	 * @param productSkuId
	 * @return
	 * @throws DataAccessException
	 */
	public ProductSku getProductSkuById(@Param("productSkuId") Integer productSkuId) throws DataAccessException;
	/**
	 * 根据SKUId和productId获取sku对象
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public ProductSku getProductSkuByIdMap(Map<String ,Object> map) throws DataAccessException;
	
	/**
	 * 根据skuId获取库存
	 * @param productSkuId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getProductSkuInventoryByProductSkuId(Integer productSkuId)throws DataAccessException;
	
	
    /**
     * <p>
     * Description: 获取商品库存
     * </p>
     * 
     * @param item 商品ID集合
     * @return 商品sku集合
     * @throws DataAccessException
     */
    public List<ProductSku> listBrandProducts(List<Integer> item) throws DataAccessException;
    
    /**
     * 根据skuCode查询商品信息集合
     * @param skuCodes
     * @return
     */
    public List<ProductSku> listProductSkusBySkuCode(List<String> skuCodes) throws DataAccessException;
    
    /**
     * 根据分类或者原产地查询商品信息集合
     * 
     * @param params
     * @return
     * @throws DataAccessException
     */
    List<ProductSku> listProductSkusByMap(Map<String, Object> params)  throws DataAccessException;
 
	/**
	 * 根据分类或者原产地查询商品信息总数
	 * 
	 * @param params
	 * @return
	 */
	int countProductSkusByMap(Map<String, Object> params);

	 /**
	    * 快速编辑库存
	    * @param sku
	    * @throws DataAccessException
	    */
	    public void updateInventoryQuick(ProductSku sku) throws DataAccessException;
	
	/**
	 * 根据商品id查询商品产地
	 * 
	 * @param productId
	 * @return
	 */
	List<Prop> listPropsOfProduct(Integer productId);

	/**
	 * <p>Description: 根据首字符查询专区SKU</p>
	 * @param firstChar
	 * @return
	 */
	public List<ProductSku> listProductByBarndFirstCharAndQg(String firstChar);

	/**
	 * <p>Description: 查询专区SKU</p>
	 * @param integer
	 * @return
	 */
	public List<ProductSku> listProductSkuByZoneId(Integer integer);

	/**
	 * 查询sku的值
	 * @param productSkuId 
	 * @return
	 * @throws YiPinStoreException
	 */
	public Map<String, String> getOverrideSkuOptionValue(Integer productSkuId);
}
