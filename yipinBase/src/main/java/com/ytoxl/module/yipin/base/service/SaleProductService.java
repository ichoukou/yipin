package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.SaleProduct;

/**
 * @作者：罗典
 * @描述：商品销售实体mapper
 * @时间：2013-12-06
 * */
public interface SaleProductService {
	/**
	 * @作者：罗典
	 * @描述：根据销售ID删除所有相关商品销售信息
	 * @时间：2013-12-17
	 * @参数：saleId 销售ID
	 * */
	public int deleteBySaleId(Integer saleId);
	/**
	 * @作者：罗典
	 * @描述：根据销售商品ID查询全部详情
	 * @时间：2013-12-16
	 * @参数：saleProductId 销售商品Id
	 * */
	public List<SaleProduct> listAllBySaleId(Integer saleProductId);
    /**
     * @作者：罗典
     * @描述：查询商品预售(传入对象为空时，查询所有)
     * @时间：2013-12-06
     * */
    public List<SaleProduct> listBySaleProduct(SaleProduct saleProduct);

    /**
     * @作者：罗典
     * @描述：新增商品预售
     * @时间：2013-12-06
     * */
    public void saveSaleProduct(SaleProduct saleProduct);

    /**
     * @作者：罗典
     * @描述：修改商品预售
     * @时间：2013-12-06
     * */
    public int updateSaleProductById(SaleProduct saleProduct);

}
