/**
 * BrandMapper.java
 * Created at Dec 4, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Product;

/**
 * <p>
 * ClassName: BrandMapper
 * </p>
 * <p>
 * Description: 品牌Mapper
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Dec 4, 2013
 * </p>
 */
public interface BrandMapper<T extends Brand> extends BaseSqlMapper<T> {

    /**
     * <p>
     * Description: 查询所有的品牌
     * </p>
     * 
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> getBrandList() throws DataAccessException;
    
    /**
     * <p>
     * Description: 查询所有的品牌和城市
     * </p>
     * 
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> getBrandAndCityList(List<Integer> cityIds) throws DataAccessException;

    /**
     * <p>
     * Description: 根据卖家ID获取品牌信息
     * </p>
     * 
     * @param sellerId 卖家ID
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> getBrandListBySeller(Integer sellerId) throws DataAccessException;

    /**
     * <p>
     * Description: 根据品牌的排序和所要的个数返回品牌列表
     * </p>
     * 
     * @param num 品牌排序个数
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> getBrandListByRank(Integer num) throws DataAccessException;

    /**
     * <p>
     * Description: 根据品牌的名字查询品牌
     * </p>
     * 
     * @param name 品牌名称
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> searchBrandByName(String name) throws DataAccessException;

    /**
     * <p>
     * Description: 按名字查询精确的品牌
     * </p>
     * 
     * @param brand 品牌
     * @return 品牌
     * @throws DataAccessException
     */
    public Brand getBrandByName(Brand brand) throws DataAccessException;

    /**
     * <p>
     * Description: 根据品牌id查询品牌
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 品牌
     * @throws DataAccessException
     */
    public Brand getBrandByBrandId(Integer brandId) throws DataAccessException;

    /**
     * <p>
     * Description: 根据品牌id查询品牌 不查详细字段
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 品牌
     * @throws DataAccessException
     */
    public Brand getBrandById(Integer brandId) throws DataAccessException;

    /**
     * <p>
     * Description: 查询卖家可售品牌
     * </p>
     * 
     * @param sellerId 卖家ID
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> listBrandsBySellerId(Integer sellerId) throws DataAccessException;

    /**
     * <p>
     * Description: 根据首字母来查询品牌
     * </p>
     * 
     * @param brand 品牌
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> listBrandsByBrandPinYin(Brand brand) throws DataAccessException;

    /**
     * <p>
     * Description: 管理员查询品牌列表
     * </p>
     * 
     * @param map 查询参数
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> searchBrands(Map<String, Object> map) throws DataAccessException;

    /**
     * <p>
     * Description: 管理员查询品牌列表
     * </p>
     * 
     * @return 品牌集合
     * @throws DataAccessException
     */
    public List<Brand> getDistinctList() throws DataAccessException;

    /**
     * <p>
     * Description: 查询管理员下面所有的品牌的总数
     * </p>
     * 
     * @param map 查询参数
     * @return 品牌总数
     * @throws DataAccessException
     */
    public int searchBrandsTotal(Map<String, Object> map) throws DataAccessException;

    /**
     * <p>
     * Description: 品牌禁用
     * </p>
     * 
     * @param brandId 品牌ID
     * @param isForbidden 是否禁用
     * @throws DataAccessException
     */
    public void updateIsForbbdenByBrandId(@Param("brandId") Integer brandId, @Param("isForbidden") Short isForbidden)
            throws DataAccessException;

    /**
     * <p>
     * Description: 根据品牌ID获取商品集合
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 商品集合
     */
    public List<Product> listProductsByBrandId(Integer brandId)throws DataAccessException;

    /**
     * <p>Description: 根据商品SKUID获取品牌ID</p>
     * @param productSkuId  商品SKUID
     * @return 商品品牌ID
     */
    public Integer getBrandIdByProductSkuId(Integer productSkuId);
    /**
     * @作者：罗典
     * @描述：根据城市ID获取下面所有品牌
     * @时间：20123-12-17
     * @参数：cityId 城市Id
     * */
    public List<Brand> listBrandByCityId(Integer cityId)throws YiPinStoreException;
    /**
     * @作者：罗典
     * @描述：根据品牌ID查询城市Id
     * @参数：barndId 品牌ID
     * @返回：cityId 城市ID
     * */
    public Integer searchCityIdByBrandId(Integer brandId) throws YiPinStoreException;
    
	/**
	 * <p>
	 * Description: 根据商品ID查询品牌首字符
	 * </p>
	 * 
	 * @param productId
	 *            商品ID
	 * @return 品牌首字符
	 * @throws YiPinStoreException
	 */
    public String  getFirstCharByProductId(Integer productId) throws YiPinStoreException;

	/**
	 * <p>Description: TODO</p>
	 * @param productSkuId
	 * @return
	 */
	public String getFirstCharByProductSkuId(Integer productSkuId)throws YiPinStoreException;
}
