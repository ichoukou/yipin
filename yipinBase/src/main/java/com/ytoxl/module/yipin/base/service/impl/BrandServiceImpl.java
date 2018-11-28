/**
 * BrandServiceImpl.java
 * Created at Dec 4, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.module.yipin.base.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.ytoxl.module.core.common.constant.CommonConstants;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.core.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.mapper.BrandMapper;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.ProductService;

/**
 * <p>
 * ClassName: BrandServiceImpl
 * </p>
 * <p>
 * Description: 品牌Service实现类
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Dec 4, 2013
 * </p>
 */
/**
 * @author admin
 * 
 */
@Service
public class BrandServiceImpl implements BrandService {

    /**
     * <p>
     * Field logger: 日志
     * </p>
     */
    private Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    /**
     * <p>
     * Field brandMapper: 品牌Mapper
     * </p>
     */
    @Autowired
    private BrandMapper<Brand> brandMapper;

    /**
     * <p>
     * Field productService: 商品Service
     * </p>
     */
    @Autowired
    private ProductService productService;

    /**
     * (non-Javadoc)
     * <p>
     * Title: getBrandByBrandId
     * </p>
     * <p>
     * Description: 根据品牌ID查询品牌信息
     * </p>
     * 
     * @param brandId 品牌ID
     * @return Brand 品牌信息
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#getBrandByBrandId(java.lang.Integer)
     */
    @Override
    public Brand getBrandByBrandId(Integer brandId) throws YiPinStoreException {
        return this.brandMapper.getBrandByBrandId(brandId);
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: updateBrandByBrandId
     * </p>
     * <p>
     * Description: 更新品牌信息
     * </p>
     * 
     * @param brand 品牌信息
     * @return 修改记录数
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#updateBrandByBrandId(com.ytoxl.module.yipin.base.dataobject.Brand)
     */
    @Override
    public Integer updateBrandByBrandId(Brand brand) throws YiPinStoreException {
        brand.setFirstChar(brand.getFirstChar().toUpperCase());
        brand.setDescription(HtmlUtils.htmlEscape(brand.getDescription()));
        return this.brandMapper.update(brand);
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: addBrand
     * </p>
     * <p>
     * Description:品牌新增
     * </p>
     * 
     * @param brand 品牌
     * @return 新增记录数
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#addBrand(com.ytoxl.module.yipin.base.dataobject.Brand)
     */
    @Override
    public Integer addBrand(Brand brand) throws YiPinStoreException {
        brand.setFirstChar(brand.getFirstChar().toUpperCase());
        brand.setIsForbidden(Brand.ISFORBIDDEN_NO_FORBIDDEN);
        brand.setDescription(HtmlUtils.htmlUnescape(brand.getDescription()));
        Integer resultConunt=null;
        synchronized(this){
            if (!checkBrand(brand)) {
                resultConunt = this.brandMapper.add(brand);
            }
        }
        return resultConunt;
    }
    
    /**
     * <p>
     * Description: 校验品牌是否存在
     * </p>
     * 
     * @param brand 当前品牌
     * @return boolean
     */
    private boolean checkBrand(Brand brand) {
        boolean flag = false;
        try {
            Brand brandZ = new Brand();
            brandZ.setBrandId(brand.getBrandId());
            brandZ.setName(brand.getName());
            brandZ.setEnglishName("");
            Brand brandE = new Brand();
            brandE.setBrandId(brand.getBrandId());
            brandE.setName("");
            brandE.setEnglishName(brand.getEnglishName());
            if (StringUtils.isNotEmpty(brand.getName())
                    && StringUtils.isNotEmpty(brand.getEnglishName())) {
                brandZ = searchBrandByName(brandZ);
                brandE = searchBrandByName(brandE);
                if (brandZ != null || brandE != null)
                    flag = true;
            } else {
                brand = searchBrandByName(brand);
                if (brand != null)
                    flag = true;
            }
        } catch (YiPinStoreException e) {
            logger.error("checkBrand error", e.getMessage());
        }
        return flag;
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: searchBrands
     * </p>
     * <p>
     * Description: 品牌分页查询
     * </p>
     * 
     * @param brandPage 分页参数
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#searchBrands(com.ytoxl.module.core.common.pagination.BasePagination)
     */
    @Override
    public void searchBrands(BasePagination<Brand> brandPage) throws YiPinStoreException {
        Map<String, Object> searchParams = brandPage.getSearchParamsMap();
        searchParams.put(CommonConstants.PAGE_SORT, "a.createTime");
        searchParams.put(CommonConstants.PAGE_DIR, "desc");
        List<Brand> brans = this.brandMapper.searchBrands(searchParams);
        if (brandPage != null && brandPage.isNeedSetTotal()) {
            Integer total = this.brandMapper.searchBrandsTotal(searchParams);
            brandPage.setTotal(total);
        }
        brandPage.setResult(brans);
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: listBrands
     * </p>
     * <p>
     * Description: 查询所有品牌信息
     * </p>
     * 
     * @return 品牌集合
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#listBrands()
     */
    @Override
    public List<Brand> listBrands() throws YiPinStoreException {
        return this.brandMapper.getBrandList();
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: listBrandsBySellerId
     * </p>
     * <p>
     * Description: 查询卖家旗下品牌信息
     * </p>
     * 
     * @param sellerid 卖家ID
     * @return 品牌集合
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#listBrandsBySellerId(java.lang.Integer)
     */
    @Override
    public List<Brand> listBrandsBySellerId(Integer sellerid) throws YiPinStoreException {
        return this.brandMapper.listBrandsBySellerId(sellerid);
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: getBrandByBrandId4Front
     * </p>
     * <p>
     * Description: 根据ID获取品牌信息
     * </p>
     * 
     * @param brand 当前品牌
     * @return 品牌
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#getBrandByBrandId4Front(com.ytoxl.module.yipin.base.dataobject.Brand)
     */
    @Override
    public Brand getBrandByBrandId4Front(Brand brand) throws YiPinStoreException {
        return this.brandMapper.get(brand.getBrandId());
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: listBrandsByBrandPinYin
     * </p>
     * <p>
     * Description: 根据首字母查询品牌信息
     * </p>
     * 
     * @param brand 品牌
     * @return 品牌信息
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#listBrandsByBrandPinYin(com.ytoxl.module.yipin.base.dataobject.Brand)
     */
    @Override
    public List<Brand> listBrandsByBrandPinYin(Brand brand) throws YiPinStoreException {
        List<Brand> brands = brandMapper.listBrandsByBrandPinYin(brand);
    	UserInfo userInfo = brand.getUserInfo();
		if(userInfo != null && userInfo.getUserInfoId() != null){
			List<Brand> selectedBrands = this.listBrandsBySellerId(userInfo.getUser().getUserId());
			for (Iterator<Brand> iterator = brands.iterator(); iterator.hasNext();) {
				Brand brand1 = (Brand) iterator.next();
				for (Iterator<Brand> iterator2 = selectedBrands.iterator(); iterator2.hasNext();) {
					Brand brand2 = (Brand) iterator2.next();
					if(brand1.getBrandId().equals(brand2.getBrandId())){
						brand1.setIsChecked(true);
						break;
					}
				}
			}
		}
        return brands;
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: searchBrandByName
     * </p>
     * <p>
     * Description: 根据品牌名称查找品牌信息
     * </p>
     * 
     * @param brand 品牌
     * @return
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#searchBrandByName(com.ytoxl.module.yipin.base.dataobject.Brand)
     */
    @Override
    public Brand searchBrandByName(Brand brand) throws YiPinStoreException {
        return this.brandMapper.getBrandByName(brand);
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: listProductsByBrandIdAndStatus
     * </p>
     * <p>
     * Description: 根据品牌Id查询该品牌下的商品
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 商品集合
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#listProductsByBrandIdAndStatus(java.lang.Integer)
     */
    @Override
    public List<Product> listProductsByBrandIdAndStatus(Integer brandId) throws YiPinStoreException {
        if (brandId == null) {
            this.logger.error("获取品牌商品失败，没有在售商品");
            throw new YiPinStoreException("此品牌没有在售商品");
        }
        return   this.productService.listProductsByBrandIdAndStatus(brandId);
//        List<Product> products = this.productService.listProductsByBrandIdAndStatus(brandId);
//        return this.productService.checkSellOut(products);
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: updateIsForbbdenByBrandId
     * </p>
     * <p>
     * Description: 品牌是否禁用状态修改
     * </p>
     * 
     * @param brandId 品牌ID
     * @param isForBidden 是否禁用标识
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#updateIsForbbdenByBrandId(java.lang.Integer,
     *      java.lang.Short)
     */
    @Override
    public void updateIsForbbdenByBrandId(Integer brandId, Short isForBidden) throws YiPinStoreException {
        this.brandMapper.updateIsForbbdenByBrandId(brandId, isForBidden);
    }


    /**
     * (non-Javadoc)
     * <p>
     * Title: getPinyinName
     * </p>
     * <p>
     * Description: 根据品牌名称获取品牌首字母
     * </p>
     * 
     * @param name 品牌名称
     * @return 品牌名称
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#getPinyinName(java.lang.String)
     */
    @Override
    public String getPinyinName(String name) throws YiPinStoreException {
        String str = String.valueOf(name.charAt(0));
        if (!str.matches("[A-Za-z\\d\\u4E00-\\u9FA5]+")) {
            return null;
        }
        //拼音工具类的转换类型
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        StringBuffer strBuf = new StringBuffer();
        try {
            char ch = name.charAt(0);
            if (String.valueOf(ch).matches("[\\u4E00-\\u9FA5]+")) {
                String pinyinArray[] = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                for (String pinyinStr : pinyinArray) {
                    char firstLetter = pinyinStr.charAt(0);
                    if (strBuf.indexOf(String.valueOf(firstLetter)) == -1) {
                        strBuf.append(firstLetter);
                        strBuf.append(",");
                    }
                }
                strBuf.deleteCharAt(strBuf.length() - 1);
            } else {
                if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
                    ch = Character.toUpperCase(ch);
                }
                strBuf.append(ch);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new YiPinStoreException(e.getMessage());
        }
        return strBuf.toString();
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: listProductsByBrandId
     * </p>
     * <p>
     * Description:根据品牌ID查询商品
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 商品集合
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#listProductsByBrandId(java.lang.Integer)
     */
    @Override
    public List<Product> listProductsByBrandId(Integer brandId) throws YiPinStoreException {
        return this.brandMapper.listProductsByBrandId(brandId);
    }

    /**
     * <p>Title: getBrandIdByProductSkuId</p>
     * <p>Description: 根据商品SKUID获取品牌ID</p>
     * @param productSkuId 商品SKUID
     * @return  品牌ID
     * @throws YiPinStoreException
     * @see com.ytoxl.module.yipin.base.service.BrandService#getBrandIdByProductSkuId(java.lang.Integer)
     */
    @Override
    public Integer getBrandIdByProductSkuId(Integer productSkuId) throws YiPinStoreException {
        return this.brandMapper.getBrandIdByProductSkuId(productSkuId);
    }
    /**
     * @作者：罗典
     * @描述：根据城市ID获取下面所有品牌
     * @时间：20123-12-17
     * @参数：cityId 城市Id
     * */
    @Override
    public List<Brand> listBrandByCityId(Integer cityId)throws YiPinStoreException{
    	return this.brandMapper.listBrandByCityId(cityId);
    }
    
    /**
     * @作者：罗典
     * @描述：根据品牌ID查询城市Id
     * @参数：barndId 品牌ID
     * @返回：cityId 城市ID
     * */
    @Override
    public Integer searchCityIdByBrandId(Integer brandId) throws YiPinStoreException{
    	return brandMapper.searchCityIdByBrandId(brandId);
    }


}
