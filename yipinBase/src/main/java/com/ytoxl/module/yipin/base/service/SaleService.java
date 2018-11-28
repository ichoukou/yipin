package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Sale;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;

/**
 * @作者：罗典
 * @描述：商品销售实体service
 * @时间：2013-12-06
 * */
public interface SaleService {

	/**
	 * @作者：罗典
	 * @描述：根据销售ID查询卖家及商品列表
	 * @时间：2013-12-17
	 * @参数：销售ID
	 * */
	public List<UserInfo> loadProductList(Integer saleId)  throws YiPinStoreException;
	/**
	 * @作者：罗典
	 * @描述：根据销售ID修改销售信息
	 * @时间：2013-12-17
	 * @参数：销售ID
	 * */
	public boolean updateSaleBySale(Sale sale) throws YiPinStoreException;
	/**
	 * @作者：罗典
	 * @描述：根据销售ID查询销售所有详情
	 * @时间：2013-12-06
	 * @参数：saleId 销售ID
	 * */
	public Sale getSaleById(Integer saleId) throws YiPinStoreException;
	
	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象Sale为空时，查询所有)
	 * @时间：2013-12-06
	 * @参数：salePage 分页条件， sale 查询附加条件
	 * */
	public void searchSales(BasePagination<Sale> salePage) throws YiPinStoreException;
	
	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象为空时，查询所有)
	 * @时间：2013-12-06
	 * */
	public List<Sale> listBySale(Sale sale)throws YiPinStoreException ;
	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象为空时，查询所有)
	 * @时间：2013-12-06
	 * */
	public List<Sale> listSaleWithBrand(Sale sale)throws YiPinStoreException ;
	
	/**
	 * @作者：罗典
	 * @描述：新增商品预售
	 * @时间：2013-12-06
	 * */
	public void saveSale(Sale sale)throws YiPinStoreException ;
	  /**
	 * @作者：罗典
	 * @描述：预售信息状态修改
	 * @时间：2013-12-10
	 * @参数：status: 状态。 list<String> 销售ID集合
	 * */
    public int updateStatusByIds(short status,List<String> list);
	/**
	 * @作者：罗典
	 * @描述：修改商品预售
	 * @时间：2013-12-06
	 * */
	public int updateSaleById(Sale sale)throws YiPinStoreException ;

    /**
     * <p>
     * Description: 根据品牌ID和销售类型获取销售实体
     * </p>
     * 
     * @param brandId 品牌ID
     * @return 销售实体
     */
    public Sale getSaleByBrandIdAndStatus(Integer brandId)throws YiPinStoreException ;
    /**
     * <p>
     * Description: 根据品牌ID获取销售实体
     * </p>
     * 
     * @param map 查询参数
     * @return 销售对象
     */
    public Sale getSaleByBrandId(Integer brandId)throws YiPinStoreException ;
    
}
