/**
 * BrandService.java
 * Created at Dec 4, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.module.yipin.base.service;

import java.util.List;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Product;

/**
 * <p>
 * ClassName: BrandService
 * </p>
 * <p>
 * Description: 品牌Service
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Dec 4, 2013
 * </p>
 */
public interface BrandService {

    /**
     * <p>
     * Description: 根据首字母来查询品牌
     * </p>
     * 
     * @param brand 品牌
     * @return 品牌集合
     * @throws YiPinStoreException
     */
    public List<Brand> listBrandsByBrandPinYin(Brand brand) throws YiPinStoreException;
    

    /**
     * <p>
     * Description: 根据品牌ID查询品牌
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 品牌
     * @throws YiPinStoreException
     */
    public Brand getBrandByBrandId(Integer brandId) throws YiPinStoreException;

    /**
     * <p>
     * Description: 修改品牌
     * </p>
     * 
     * @param brand 品牌
     * @return 修改记录数
     * @throws YiPinStoreException
     */
    public Integer updateBrandByBrandId(Brand brand) throws YiPinStoreException;

    /**
     * <p>
     * Description: 新增品牌
     * </p>
     * 
     * @param brand 品牌
     * @return 新增记录数
     * @throws YiPinStoreException
     */
    public Integer addBrand(Brand brand) throws YiPinStoreException;

    /**
     * <p>
     * Description: 管理员品牌列表分页查询
     * </p>
     * 
     * @param brandPage 分页参数
     * @throws YiPinStoreException
     */
    public void searchBrands(BasePagination<Brand> brandPage) throws YiPinStoreException;

    /**
     * <p>
     * Description: 品牌列表查询
     * </p>
     * 
     * @return 品牌集合
     * @throws YiPinStoreException
     */
    public List<Brand> listBrands() throws YiPinStoreException;


    /**
     * <p>
     * Description: 查询卖家可售品牌
     * </p>
     * 
     * @param sellerid 卖家ID
     * @return 品牌集合
     * @throws YiPinStoreException
     */
    public List<Brand> listBrandsBySellerId(Integer sellerid) throws YiPinStoreException;

    /**
     * 根据品牌的id查询品牌 前台品牌详情
     * 
     * @param brand 品牌
     * @return 品牌
     * @throws YiPinStoreException
     */
    public Brand getBrandByBrandId4Front(Brand brand) throws YiPinStoreException;

    /**
     * <p>
     * Description: 根据品牌名称查询精确的品牌名
     * </p>
     * 
     * @param brand 品牌
     * @return
     * @throws YiPinStoreException
     */
    public Brand searchBrandByName(Brand brand) throws YiPinStoreException;

    /**
     * <p>
     * Description: 根据品牌Id查询该品牌下的商品
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 品牌旗下商品集合
     * @throws YiPinStoreException
     */
    public List<Product> listProductsByBrandIdAndStatus(Integer brandId) throws YiPinStoreException;
    
    
    /**
     * <p>
     * Description: 根据品牌Id查询该品牌下的商品
     * </p>
     * 
     * @param brandId 品牌ID
     * @param sellType 销售类型
     * @return 品牌旗下商品集合
     * @throws YiPinStoreException
     */
    public List<Product> listProductsByBrandId(Integer brandId)throws YiPinStoreException;

    /**
     * <p>
     * Description: 品牌禁用
     * </p>
     * 
     * @param brandId 品牌ID
     * @param isForBidden 是否禁用
     * @throws YiPinStoreException
     */
    public void updateIsForbbdenByBrandId(Integer brandId, Short isForBidden) throws YiPinStoreException;


    /**
     * <p>
     * Description: 获取品牌名称首字母用作查询索引
     * </p>
     * 
     * @param name 品牌名称
     * @return 首字母
     * @throws YiPinStoreException
     */
    public String getPinyinName(String name) throws YiPinStoreException;
    
    
    /**
     * <p>Description: 根据商品SKUID获取品牌ID</p>
     * @param productSkuId 商品SKUID
     * @return  品牌ID
     * @throws YiPinStoreException
     */
    public Integer getBrandIdByProductSkuId(Integer productSkuId) throws YiPinStoreException;

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
}
