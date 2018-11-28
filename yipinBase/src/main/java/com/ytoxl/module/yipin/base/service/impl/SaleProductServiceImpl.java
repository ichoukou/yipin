package com.ytoxl.module.yipin.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.dataobject.SaleProduct;
import com.ytoxl.module.yipin.base.mapper.SaleProductMapper;
import com.ytoxl.module.yipin.base.service.SaleProductService;

/**
 * @作者：罗典
 * @描述：商品销售实体mapper
 * @时间：2013-12-06
 * */
@Service
public class SaleProductServiceImpl implements SaleProductService {
    @Autowired
    private SaleProductMapper<SaleProduct> saleProductMapper;
    /**
	 * @作者：罗典
	 * @描述：根据销售ID删除所有相关商品销售信息
	 * @时间：2013-12-17
	 * @参数：saleId 销售ID
	 * */
    @Override
	public int deleteBySaleId(Integer saleId){
		return saleProductMapper.deleteBySaleId(saleId);
	}
    /**
	 * @作者：罗典
	 * @描述：根据销售商品ID查询全部详情
	 * @时间：2013-12-16
	 * @参数：saleId 销售商品Id
	 * */
    @Override
	public List<SaleProduct> listAllBySaleId(Integer saleId){
		return saleProductMapper.listAllBySaleId(saleId);
	}
    /**
     * @作者：罗典
     * @描述：查询商品预售(传入对象为空时，查询所有)
     * @时间：2013-12-06
     * */
    @Override
    public List<SaleProduct> listBySaleProduct(SaleProduct saleProduct) {
        return saleProductMapper.listBySaleProduct(saleProduct);
    }

    /**
     * @作者：罗典
     * @描述：新增商品预售
     * @时间：2013-12-06
     * */
    @Override
    public void saveSaleProduct(SaleProduct saleProduct) {
        saleProductMapper.addSaleProduct(saleProduct);

    }

    /**
     * @作者：罗典
     * @描述：修改商品预售
     * @时间：2013-12-06
     * */
    @Override
    public int updateSaleProductById(SaleProduct saleProduct) {
        return saleProductMapper.updateSaleProductById(saleProduct);
    }

    public SaleProductMapper<SaleProduct> getSaleProductMapper() {
        return saleProductMapper;
    }

    public void setSaleProductMapper(SaleProductMapper<SaleProduct> saleProductMapper) {
        this.saleProductMapper = saleProductMapper;
    }


}
