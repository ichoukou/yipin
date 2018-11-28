package com.ytoxl.module.yipin.base.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.service.helper.ProductSkuComparator;

public class ProductServiceTest extends BaseTest {
	protected static Logger log = LoggerFactory
			.getLogger(ProductServiceTest.class);
	@Autowired
	private ProductService productService;

	@Test
	public void infoTest() {
		try {
			ProductSku ps = productService.getProductSkuById(100026);
			System.out.print("-------------" + ps);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void aa(){
		try {
			Product product = productService.getProductByProductId(100087);
			System.out.println(product.getInventory());
		} catch (YiPinStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListPassProBySellerId() throws Exception {
		productService.listPassProBySellerId(3,3);
	}
	
	@Test
	public void testGetDefaultProductSku() throws Exception {
		ProductSku sku1 = new ProductSku();
		sku1.setUnitPrice(new BigDecimal(10));
		sku1.setInventory(10);
		ProductSku sku2 = new ProductSku();
		sku2.setUnitPrice(new BigDecimal(30));
		sku2.setInventory(10);
		ProductSku sku3 = new ProductSku();
		sku3.setUnitPrice(new BigDecimal(2));
		sku3.setInventory(10);
		
		List<ProductSku> list = new ArrayList<ProductSku>();
		list.add(sku3);list.add(sku2);list.add(sku1);
//		ProductSku[] proSkuArray = new ProductSku[list.size()];
//		Arrays.sort(list.toArray(proSkuArray), new ProductSkuComparator());
//		return;
		
		ProductSku sku = productService.getDefaultProductSku(list);
		log.info("===============defaultSku===================" + sku.getUnitPrice());
//		for(ProductSku proSku : proSkuArray){
//			log.info("===============defaultSku===================" + proSku.getUnitPrice());
//		}
	}

}
