package com.ytoxl.module.yipin.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.Sale;
import com.ytoxl.module.yipin.base.dataobject.SaleProduct;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.mapper.SaleMapper;
import com.ytoxl.module.yipin.base.mapper.SaleProductMapper;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.SaleProductService;
import com.ytoxl.module.yipin.base.service.SaleService;
import com.ytoxl.module.yipin.base.service.UserInfoService;

/**
 * @作者：罗典
 * @描述：商品销售实体service
 * @时间：2013-12-06
 * */
@Service
public class SaleServiceImpl implements SaleService {
    
    @Autowired
    private SaleMapper<Sale> saleMapper;
    @Autowired
    private SaleProductMapper<SaleProduct> saleProductMapper;
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * @作者：罗典
	 * @描述：根据品牌ID查询卖家及商品列表
	 * @时间：2013-12-17
	 * @参数：销售ID
	 * */
	public List<UserInfo> loadProductList(Integer brandId)  throws YiPinStoreException{
//		Sale sale = saleMapper.getSaleById(saleId);
		List<UserInfo> userInfoResult= (List<UserInfo>)userInfoService.listSellersByBrandId(brandId);
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		userInfos.addAll(userInfoResult);
		for (int i = 0;i<userInfoResult.size();i++) {
			UserInfo userInfo = userInfoResult.get(i);
			List<Product> productList = productService.listPassProBySellerId(userInfo.getUser().getUserId(),brandId);
			userInfos.get(i).setProductList(productList);
		}
		return userInfos;
	}
	
	/**
	 * @作者：罗典
	 * @描述：根据销售ID修改销售信息
	 * @时间：2013-12-17
	 * @参数：销售ID
	 * */
	public boolean updateSaleBySale(Sale sale) throws YiPinStoreException{
		saleMapper.updateSaleById(sale);
		saleProductService.deleteBySaleId(sale.getSaleId());
		for (SaleProduct saleProduct : sale.getSaleProductList()) {
            saleProduct.setSaleId(sale.getSaleId());
            saleProductMapper.addSaleProduct(saleProduct);
        }
		return false;
	}
    /**
	 * @作者：罗典
	 * @描述：根据销售ID查询销售所有详情
	 * @时间：2013-12-06
	 * @参数：saleId 销售ID
	 * */
	public Sale getSaleById(Integer saleId) throws YiPinStoreException{
		Sale sale = new Sale();
		sale = saleMapper.getSaleById(saleId);
		SaleProduct saleProductCondition = new SaleProduct();
		saleProductCondition.setSaleId(saleId);
		List<SaleProduct> saleProductList = saleProductService.listBySaleProduct(saleProductCondition);
		for(SaleProduct saleProduct : saleProductList){
			Product product = productService.getProductByProductId(saleProduct.getProductId());
			saleProduct.setProduct(product);
		}
		sale.setSaleProductList(saleProductList);
		return sale;
	}
    /**
	 * @作者：罗典
	 * @描述：查询商品预售(传入对象Sale为空时，查询所有)
	 * @时间：2013-12-06
	 * @参数：salePage 分页条件， sale 查询附加条件
	 * */
	@Override
	public void searchSales(BasePagination<Sale> salePage) throws YiPinStoreException {
        if (salePage.isNeedSetTotal()) {
            Integer total = saleMapper.countSaleWithBrand(salePage.getSearchParamsMap());
            salePage.setTotal(total);
        }
        Collection<Sale> result = saleMapper.searchSaleWithBrandByLimit(salePage.getSearchParamsMap());
        List<Sale> saleList = new ArrayList<Sale>();
        for(Sale saleInfo : result){
        	saleList.add(this.getSaleById(saleInfo.getSaleId()));
        }
        salePage.setResult(saleList);
		
	}
	
    /**
     * @作者：罗典
     * @描述：查询商品预售(传入对象为空时，查询所有)
     * @时间：2013-12-06
     * */
    public List<Sale> listBySale(Sale sale) throws YiPinStoreException {
        return saleMapper.listBySale(sale);
    }
	  /**
	 * @作者：罗典
	 * @描述：预售信息状态修改
	 * @时间：2013-12-10
	 * @参数：status: 状态。 list<String> 销售ID集合
	 * */
    public int updateStatusByIds(short status,List<String> list){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("list", list);
		return saleMapper.updateStatusByIds(map);
    }
    
    /**
     * @作者：罗典
     * @描述：查询商品预售(传入对象为空时，查询所有)
     * @时间：2013-12-06
     * */
    public List<Sale> listSaleWithBrand(Sale sale) throws YiPinStoreException {
        return saleMapper.listSaleWithBrand(sale);
    }

    /**
     * @作者：罗典
     * @描述：新增商品预售
     * @时间：2013-12-06
     * */
    public void saveSale(Sale sale) throws YiPinStoreException {
        saleMapper.addSale(sale);
        for (SaleProduct saleProduct : sale.getSaleProductList()) {
            saleProduct.setSaleId(sale.getSaleId());
            saleProductMapper.addSaleProduct(saleProduct);
        }
    }

    /**
     * @作者：罗典
     * @描述：修改商品预售
     * @时间：2013-12-06
     * */
    public int updateSaleById(Sale sale) throws YiPinStoreException {
        return saleMapper.updateSaleById(sale);
    }

    /**
     * <p>
     * Title: getSaleByBrandIdAndStatus
     * </p>
     * <p>
     * Description: 根据品牌ID和销售类型获取销售实体
     * </p>
     * 
     * @param brandId 品牌ID
     * @param sellType 销售类型
     * @return 销售对象
     * @see com.ytoxl.module.yipin.base.service.SaleService#getSaleByBrandIdAndStatus(java.lang.Integer)
     */
    @Override
    public Sale getSaleByBrandIdAndStatus(Integer brandId) {
        return this.saleMapper.getSaleByBrandIdAndStatus(brandId);
    }

    /**
     * <p>
     * Description: 根据品牌ID获取销售实体
     * </p>
     * 
     * @param map 查询参数
     * @return 销售对象
     */
    @Override
    public Sale getSaleByBrandId(Integer brandId) {
        return saleMapper.getSaleByBrandId(brandId);
    }

}
