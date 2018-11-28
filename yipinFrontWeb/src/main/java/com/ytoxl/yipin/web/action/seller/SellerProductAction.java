/**
 * SellerProductAction.java
 * Created at Dec 6, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.yipin.web.action.seller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ytoxl.module.user.service.UserService;
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
 * ClassName: SellerProductAction
 * </p>
 * <p>
 * Description: 商品购买Action
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Dec 6, 2013
 * </p>
 */
public class SellerProductAction extends BaseAction {

    /**
     * <p>
     * Field serialVersionUID: 序列号
     * </p>
     */
    private static final long serialVersionUID = 902506193496711943L;

    /**
     * <p>
     * Field logger: 日志
     * </p>
     */
    private static Logger logger = LoggerFactory.getLogger(SellerProductAction.class);

    /**
     * <p>
     * Field SELLERPRODUCT: 品牌在售商品
     * </p>
     */
    private final String SELLERPRODUCT = "sellerProduct";
    /**
     * <p>
     * Field SELLERPRODUCT: 销售类型
     * </p>
     */
    private final String SELLERTYPE = "sellerType";
    
    /**
     * <p>Field LOGINURL: 登录页面</p>
     */
    private final String LOGINURL ="loginPath";

    /**
     * <p>Field ORDERURL: 订单页面</p>
     */
    private final String ORDERURL ="orderPath";
    
    /**
     * <p>Field INDEXURL: 首页</p>
     */
    private final String INDEXURL ="index";
    
    /**
     * <p>Field productId: 商品ID</p>
     */
    private Integer productId;
    
    /**
     * <p>Field productSkuId: 商品SkuId</p>
     */
    private Integer productSkuId;

    /**
     * <p>Description:  商品SkuId</p>
     * @return the productSkuId
     */
    public Integer getProductSkuId() {
        return this.productSkuId;
    }

    /**
     * <p>Description:  商品SkuId</p>
     * @param productSkuId the productSkuId to set
     */
    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
    }

    /**
     * <p>Field sendUrl: 支付页面链接</p>
     */
    private String sendUrl ;
    
    @Value("${loginUrl}")
    private String loginUrl;
    
    @Value("${targetUrl}")
    private String targetUrl;

    /**
     * <p>
     * Field brandService: 品牌Service
     * </p>
     */
    @Autowired
    private BrandService brandService;

    /**
     * <p>
     * Field saleService: 商品销售Service
     * </p>
     */
    @Autowired
    private SaleService saleService;
    
    /**
     * <p>Field userService: 用户Service</p>
     */
    @Autowired
    private UserService userService;
    
    /**
     * <p>Field productService: 商品Service</p>
     */
    @Autowired
    private ProductService productService;

    /**
     * <p>
     * Field brandId: 品牌ID
     * </p>
     */
    private Integer brandId;

    /**
     * <p>
     * Field sale: 品牌销售信息
     * </p>
     */
    private Sale sale;

    /**
     * <p>
     * Field products: 在售商品
     * </p>
     */
    private List<Product> products;
    
    /**
     * <p>Field brand: 品牌</p>
     */
    private Brand brand;
    
    /**
     * <p>Field isEnable: 是否预售标记</p>
     */
    private boolean isEnable;

    /**
     * <p>
     * Description: 获取品牌在售商品信息
     * </p>
     * 
     * @return SELLERPRODUCT
     */
    public String sellerProduct() {
        try {
            this.isEnable = false ; 
            this.sale = this.saleService.getSaleByBrandIdAndStatus(this.brandId);
            this.brand = this.brandService.getBrandByBrandId(this.brandId);
            this.products = this.brandService.listProductsByBrandIdAndStatus(this.brandId);
            if(this.sale !=null && this.sale.getSellType().shortValue() == 1){
              Date currentDate =   new Date();
                if(currentDate.before(this.sale.getPreSelltime())){
                    this.isEnable=true;
                }
            }
            
        } catch (YiPinStoreException e) {
            logger.error("sellerProduct loading error!", e.getMessage());
        }
        return SELLERPRODUCT;
    }
    
    /**
     * <p>Description: 跳转购买页面并选中指定商品</p>
     * @return SELLERPRODUCT
     */
    public String selectProduct(){
        try {
            this.isEnable = false ; 
            this.brandId  = this.productService.getBrandIdBySkuId(this.productSkuId);
            this.sale = this.saleService.getSaleByBrandIdAndStatus(this.brandId);
            this.brand = this.brandService.getBrandByBrandId(this.brandId);
            if(Brand.ISFORBIDDEN_FORBIDDEN == this.brand.getIsForbidden().shortValue()){
                throw new YiPinStoreException("品牌已禁用");
            }
            this.products = this.brandService.listProductsByBrandIdAndStatus(this.brandId);
            if(this.sale !=null && this.sale.getSellType().shortValue() == 1){
              Date currentDate =   new Date();
                if(currentDate.before(this.sale.getPreSelltime())){
                    this.isEnable=true;
                }
            }
            
        } catch (YiPinStoreException e) {
            logger.error("selectProduct loading error!", e.getMessage());
            return INDEXURL;
        }
        return SELLERPRODUCT;
    }
    
    
    /**
     * <p>Description: 是否预售标记</p>
     * @return the isEnable
     */
    public boolean isEnable() {
        return isEnable;
    }

    /**
     * <p>Description: 是否预售标记</p>
     * @param isEnable the isEnable to set
     */
    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * <p>
     * Description: 获取品牌在售商品信息
     * </p>
     * 
     * @return SELLERPRODUCT
     */
    public String sellerTypeByBrandId() throws YiPinStoreException{
		this.sale  = this.saleService.getSaleByBrandId(sale.getBrandId());
        return SELLERTYPE;
    }
    
    /**
     * <p>Description: 购买页面用户跳转</p>
     * @return
     */
    public String send(){
        try{
             this.sendUrl = loginUrl
                 +URLEncoder.encode(ServletActionContext.getRequest().getContextPath()+targetUrl, "UTF-8");
             this.userService.getCurrentUser();
        }catch (Exception e) {
            logger.error("send error",e.getMessage());
            return this.LOGINURL;
        }
        return this.ORDERURL;
    }
    /**
     * <p>Description: 支付页面链接</p>
     * @return the sendUrl
     */
    public String getSendUrl() {
        return this.sendUrl;
    }

    /**
     * <p>Description: 支付页面链接</p>
     * @param sendUrl the sendUrl to set
     */
    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
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
     * Description: 品牌ID
     * </p>
     * 
     * @return the brandId
     */
    public Integer getBrandId() {
        return this.brandId;
    }

    /**
     * <p>
     * Description: 品牌ID
     * </p>
     * 
     * @param brandId the brandId to set
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>
     * Description: 品牌销售信息
     * </p>
     * 
     * @return the sale
     */
    public Sale getSale() {
        return this.sale;
    }

    /**
     * <p>
     * Description: 品牌销售信息
     * </p>
     * 
     * @param sale the sale to set
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }
    
    /**
     * <p>Description:  品牌</p>
     * @return the brand
     */
    public Brand getBrand() {
        return this.brand;
    }

    /**
     * <p>Description:  品牌</p>
     * @param brand the brand to set
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    /**
     * <p>Description:  商品ID</p>
     * @return the productId
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * <p>Description:  商品ID</p>
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}
