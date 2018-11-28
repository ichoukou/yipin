package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.Sale;

/**
 * @作者：罗典
 * @描述：商品销售实体mapper
 * @时间：2013-12-06
 * */
public interface SaleMapper<T extends Sale> extends BaseSqlMapper<T> {
	/**
	 * @作者：罗典
	 * @描述：根据ID查询销售信息
	 * @时间：2013-12-13
	 * */
	public Sale getSaleById(Integer saleId);
	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象为空时，查询所有)
	 * @时间：2013-12-06
	 * */
	public List<Sale> listBySale(Sale sale);

	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象为空时，查询所有)
	 * @时间：2013-12-06
	 * */
	public List<Sale> listSaleWithBrand(Sale sale);
	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象为空时，查询所有),分页查询
	 * @时间：2013-12-06
	 * */
	public List<Sale> searchSaleWithBrandByLimit(Map<String, Object> map);

	/**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象为空时，查询所有),分页统计
	 * @时间：2013-12-06
	 * */
	public Integer countSaleWithBrand(Map<String, Object> map);
	/**
	 * @作者：罗典
	 * @描述：新增商品预售
	 * @时间：2013-12-06
	 * */
	public void addSale(Sale sale);

	/**
	 * @作者：罗典
	 * @描述：预售信息状态修改
	 * @时间：2013-12-10
	 * @参数：status: 状态。 list<String> 销售ID集合
	 * */
	public int updateStatusByIds(Map<String, Object> map);

	/**
	 * @作者：罗典
	 * @描述：修改商品预售
	 * @时间：2013-12-06
	 * */
	public int updateSaleById(Sale sale);

	/**
	 * <p>
	 * Description: 根据品牌ID和销售状态获取销售实体
	 * </p>
	 * 
	 * @param brandId
	 *            品牌ID
	 * @return 销售商品
	 * @throws DataAccessException
	 */
	public Sale getSaleByBrandIdAndStatus(Integer brandId)
			throws DataAccessException;

	/**
	 * <p>
	 * Description: 根据品牌ID获取销售实体
	 * </p>
	 * 
	 * @return 销售对象
	 * @throws DataAccessException
	 */
	public Sale getSaleByBrandId(Integer brandId) throws DataAccessException;

}
