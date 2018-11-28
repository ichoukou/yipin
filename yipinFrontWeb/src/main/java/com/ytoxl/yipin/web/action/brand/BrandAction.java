/**
 * BrandAction.java
 * Created at Dec 3, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.yipin.web.action.brand;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.Sale;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.ProductService;
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
 */
public class BrandAction extends BaseAction {

    /**
     * <p>
     * Field serialVersionUID: 序列号
     * </p>
     */
    private static final long serialVersionUID = 1563032240216719019L;
    private static Logger logger = LoggerFactory.getLogger(BrandAction.class);

    public final String PRODUCT_DETAIL = "productDetail";

    @SuppressWarnings("serial")
	public final Map<Integer, String> PAGEMAP = new HashMap<Integer, String>(){
    	{
    		put(100000,"teaDetail"); //西湖龙井
    		put(100001,"mushroomDetail"); //猴头菇
    		put(100002,"fungusDetail"); //木耳
    		put(100003,"oilDetail"); //林蛙油
		}
    };
    private String page;
    /**
     * <p>
     * Field saleService: 销售Service
     * </p>
     */
    @Autowired
    private SaleService saleService;
    /**
     * <p>
     * Field brandService: 品牌Service
     * </p>
     */
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private ProductService productService;

    /**
     * <p>
     * Field brand: 品牌
     * </p>
     */
    private Brand brand;

    /**
     * <p>
     * Field sale: 销售商品
     * </p>
     */
    private Sale sale;
    /**
     * <p>
     * Field products: 在售商品
     * </p>
     */
    private List<Product> products;

    private boolean isPreSell;
    
    private Map<String,Product> map;
    /**
     * <p>
     * Description: 根据id 获取单个品牌
     * </p>
     * 
     * @return
     */
    public String getProductDetail(){
		try {
			isPreSell = false;
			brand = this.brandService.getBrandByBrandId(brand.getBrandId());
			sale = saleService.getSaleByBrandId(brand.getBrandId());
			map = productService.listProductsByBrandIdAndStatus2(brand.getBrandId());
			if(sale != null){
				Date nowTime = new Date();
				if (Sale.TYPE_PRE.equals(sale.getSellType())) {
					if (nowTime.compareTo(sale.getPreSelltime()) == -1) {
						isPreSell = true;
					}
				}
			}
			page = PAGEMAP.get(brand.getBrandId());
			super.setSeoConfigByUrlKey(ServletActionContext.getRequest().getServletPath(), brand);
		} catch(YiPinStoreException e) {
			logger.error("获取产品详情信息出错", e);
		}
        return PRODUCT_DETAIL;
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
     * Description: 在售商品
     * </p>
     * 
     * @return the products
     */
    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * <p>
     * Description: 在售商品
     * </p>
     * 
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * <p>
     * Description: 品牌
     * </p>
     * 
     * @param brand the brand to set
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * <p>
     * Description: 销售商品
     * </p>
     * 
     * @return the sale
     */
    public Sale getSale() {
        return this.sale;
    }

    /**
     * <p>
     * Description: 销售商品
     * </p>
     * 
     * @param sale
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

	public boolean getIsPreSell() {
		return isPreSell;
	}

	public void setPreSell(boolean isPreSell) {
		this.isPreSell = isPreSell;
	}

	public Map<String, Product> getMap() {
		return map;
	}

	public void setMap(Map<String, Product> map) {
		this.map = map;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	

}
