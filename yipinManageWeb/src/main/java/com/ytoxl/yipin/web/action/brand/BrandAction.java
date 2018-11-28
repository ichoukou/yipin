/**
 * BrandAction.java
 * Created at Dec 3, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.yipin.web.action.brand;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.core.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.City;
//import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.Sale;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.CityService;
import com.ytoxl.module.yipin.base.service.SaleService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * <p>
 * ClassName: BrandAction
 * </p>
 * <p>
 * Description: 品牌Action
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Dec 3, 2013
 * </p>
 */
public class BrandAction extends BaseAction {

	/**
	 * <p>
	 * Field serialVersionUID: 序列号
	 * </p>
	 */
	private static final long serialVersionUID = 1563032240216719019L;

	/**
	 * <p>
	 * Field logger: 日志
	 * </p>
	 */
	private static Logger logger = LoggerFactory.getLogger(BrandAction.class);

	/**
	 * <p>
	 * Field brandService: 品牌Service
	 * </p>
	 */
	@Autowired
	private BrandService brandService;

	/**
	 * <p>
	 * Field pagebrands: 品牌分页信息
	 * </p>
	 */
	private BasePagination<Brand> pagebrands;
	

	/**
	 * <p>
	 * Field cityService: 城市信息Service
	 * </p>
	 */
	@Autowired
	private CityService cityService;
	
	/**
	 * <p>
	 * Field saleService: 销售Service
	 * </p>
	 */
	@Autowired
	private SaleService saleService;

	/**
	 * <p>
	 * Field LIST_BRANDS: 品牌列表
	 * </p>
	 */
	public final String LIST_BRANDS = "listBrands";

	/**
	 * <p>
	 * Field ALTER_BRAND: 品牌修改
	 * </p>
	 */
	public final String ALTER_BRAND = "alterBrand";

	/**
	 * <p>
	 * Field LIST_USERBRANDS: 管理员品牌列表
	 * </p>
	 */
	public final String LIST_USERBRANDS = "listUserBrands";
	

	/**
	 * <p>
	 * Field opert: 用户操作
	 * </p>
	 */
	private String opert;

	/**
	 * <p>
	 * Field brand: 品牌
	 * </p>
	 */

	private String nextAction;
	private List<Brand> brands;
	private List<Brand> brandList;
	private Brand brand;

	/**
	 * <p>
	 * Description: 根据品牌拼音查询品牌
	 * </p>
	 * 
	 * @return ResultConstants.LIST_BRANDS
	 */
	public String listBrands() {

		try {
			this.brands = this.brandService.listBrandsByBrandPinYin(this
					.getBrand());
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage());
		}
		return LIST_BRANDS;
	}
	

	/**
	 * <p>
	 * Description: 管理员品牌列表
	 * </p>
	 * 
	 * @return LIST_USERBRANDS
	 */
	public String listUserBrans() {
		try {
			if (pagebrands == null) {
				pagebrands = new BasePagination<Brand>();
			}
			brandService.searchBrands(pagebrands);
		} catch (YiPinStoreException e) {
			logger.error("listUserBrans error", e.getMessage());
		}
		return LIST_USERBRANDS;
	}

	/**
	 * <p>
	 * Description: 根据id 获取单个品牌
	 * </p>
	 * 
	 * @return ALTER_BRAND
	 */
	public String singleBrand() {

		if (brand != null) {
			try {
				brand = this.brandService.getBrandByBrandId(brand.getBrandId());
			} catch (YiPinStoreException e) {
				logger.error("singleBrand error", e.getMessage());
			}
		}
		return ALTER_BRAND;
	}

	/**
	 * <p>
	 * Description: 获取品牌的首字符
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String getBrandPinYinByName() {

		try {
			String brandPinYin = this.brandService.getPinyinName(brand
					.getFirstChar());
			setMessage("pinyin", brandPinYin);// 添加或修改成功
		} catch (YiPinStoreException e) {
			logger.error("getBrandPinYinByName error", e.getMessage());
		}

		return JSONMSG;
	}
	
	
	/**
	 * <p>Description: 获取所有城市信息</p>
	 * @return List<City> 城市信息集合 
	 */
	public List<City> getCitys(){
		List<City> citys = new ArrayList<City>();
		try {
			citys = this.cityService.searchAllCity(); // 获取所有城市信息
		} catch (YiPinStoreException e) {
			logger.error("获取全部城市出错", e);
		}
		return citys;
	}

	/**
	 * <p>
	 * Description: 修改或添加单个品牌
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String singleBrandSset() {
		
		if (opert != null) {
			if (opert.equals("edit")) {
				try {
					if (!checkBrand(brand)) {
						this.brandService.updateBrandByBrandId(brand);
						setMessage(Boolean.TRUE.toString(), "2");// 添加或修改成功
					} else {
						setMessage(Boolean.TRUE.toString(), "1");// 品牌重复
					}
				} catch (YiPinStoreException e) {
					logger.error("getBrandPinYinByName error", e.getMessage());
				}
			} else {
				try {
				    Integer flag = this.brandService.addBrand(brand);
					if (null!= flag) {
						setMessage(Boolean.TRUE.toString(), "2");// 添加或修改成功
					} else {
						setMessage(Boolean.TRUE.toString(), "1");// 品牌重复
					}
				} catch (YiPinStoreException e) {
					logger.error("getBrandPinYinByName error", e.getMessage());
				}
			}
		}
		
		return JSONMSG;
	}

	/**
	 * <p>
	 * Description: 校验品牌是否存在
	 * </p>
	 * 
	 * @param brand 当前品牌
	 * @return boolean
	 */
	public boolean checkBrand(Brand brand) {
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
				brandZ = brandService.searchBrandByName(brandZ);
				brandE = brandService.searchBrandByName(brandE);
				if (brandZ != null || brandE != null)
					flag = true;
			} else {
				brand = brandService.searchBrandByName(brand);
				if (brand != null)
					flag = true;
			}
		} catch (YiPinStoreException e) {
			logger.error("checkBrand error", e.getMessage());
		}
		return flag;
	}

	/**
	 * <p>
	 * Description: 品牌禁用
	 * </p>
	 * 
	 * @return JSONMSG
	 */
    public String forbiddenBrand() {

//		List<Product> listProduct = null;
		try {
//			listProduct = this.brandService.listProductsByBrandId(brand
//					.getBrandId());
			
			Sale sale = this.saleService.getSaleByBrandId(brand.getBrandId());
			if (sale != null  &&  Brand.ISFORBIDDEN_FORBIDDEN.shortValue()
			        == this.brand.getIsForbidden().shortValue()) {
				setMessage(Boolean.TRUE.toString(), "请先下架此品牌的在售商品");// 品牌还存在商品
			} else {
				brandService.updateIsForbbdenByBrandId(brand.getBrandId(),
						brand.getIsForbidden());
				if (brand.getIsForbidden() == 1) {
					setMessage(Boolean.TRUE.toString(), "该品牌已禁用");
				} else {
					setMessage(Boolean.TRUE.toString(), "该品牌已激活");
				}
			}
		} catch (YiPinStoreException e) {
			logger.error("forbiddenBrand error", e.getMessage());
		}

		return JSONMSG;
	}

	/**
	 * <p>
	 * Description: 品牌分页信息
	 * </p>
	 * 
	 * @return the pagebrands
	 */
	public BasePagination<Brand> getPagebrands() {
		return this.pagebrands;
	}

	/**
	 * <p>
	 * Description: 品牌分页信息
	 * </p>
	 * 
	 * @param pagebrands
	 *            the pagebrands to set
	 */
	public void setPagebrands(BasePagination<Brand> pagebrands) {
		this.pagebrands = pagebrands;
	}

	/**
	 * <p>
	 * Description: 用户操作
	 * </p>
	 * 
	 * @return the opert
	 */
	public String getOpert() {
		return this.opert;
	}

	/**
	 * <p>
	 * Description: 用户操作
	 * </p>
	 * 
	 * @param opert
	 *            the opert to set
	 */
	public void setOpert(String opert) {
		this.opert = opert;
	}

	/**
	 * <p>
	 * Description: 品牌
	 * </p>
	 * 
	 * @return the brand
	 */
	public Brand getBrand() {
		return this.brand;
	}

	/**
	 * <p>
	 * Description: 品牌
	 * </p>
	 * 
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}

}
