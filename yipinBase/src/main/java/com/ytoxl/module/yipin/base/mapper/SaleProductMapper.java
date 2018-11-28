package com.ytoxl.module.yipin.base.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.SaleProduct;

/**
 * @作者：罗典
 * @描述：商品销售实体mapper
 * @时间：2013-12-06
 * */
public interface SaleProductMapper<T extends SaleProduct> extends BaseSqlMapper<T> {
	/**
	 * @作者：罗典
	 * @描述：根据销售ID删除所有相关商品销售信息
	 * @时间：2013-12-17
	 * @参数：saleId 销售ID
	 * */
	public int deleteBySaleId(Integer saleId);
	/**
	 * @作者：罗典
	 * @描述：根据销售ID查询全部详情
	 * @时间：2013-12-16
	 * @参数：saleId 销售Id
	 * */
	public List<SaleProduct> listAllBySaleId(Integer saleId);
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
    public void addSaleProduct(SaleProduct saleProduct);

    /**
     * @作者：罗典
     * @描述：修改商品预售
     * @时间：2013-12-06
     * */
    public int updateSaleProductById(SaleProduct saleProduct);
    
    public SaleProduct searchSaleByProductId(int productId) throws DataAccessException;
    
    public List<SaleProduct> searchSaleByProductIds(List<Product> productList) throws DataAccessException;

}
