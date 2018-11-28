package com.ytoxl.module.yipin.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.CodeConstants;
import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductProp;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.ProductSkuOptionValue;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.dataobject.SkuOption;
import com.ytoxl.module.yipin.base.dataobject.SkuOptionValue;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.dataobject.ZoneSale;
import com.ytoxl.module.yipin.base.mapper.ProductMapper;
import com.ytoxl.module.yipin.base.mapper.ProductSkuMapper;
import com.ytoxl.module.yipin.base.mapper.ProductSkuOptionValueMapper;
import com.ytoxl.module.yipin.base.mapper.SkuOptionMapper;
import com.ytoxl.module.yipin.base.mapper.SkuOptionValueMapper;
import com.ytoxl.module.yipin.base.mapper.ZoneMapper;
import com.ytoxl.module.yipin.base.mapper.ZoneSaleMapper;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.PropService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.base.service.ZoneService;
import com.ytoxl.module.yipin.base.service.helper.ProductSkuComparator;

@Service
public class ProductServiceImpl implements ProductService {
	private static Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	@Autowired
    private ProductMapper<Product> productMapper;
    @Autowired
    private ProductSkuMapper<ProductSku> productSkuMapper;
    @Autowired
    private ProductSkuOptionValueMapper<ProductSkuOptionValue> productSkuOptionValueMapper;
    @Autowired
    private SkuOptionMapper<SkuOption> skuOptionMapper;
    @Autowired
    private SkuOptionValueMapper<SkuOptionValue> skuOptionValueMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ZoneMapper<Zone> zoneMapper;
    @Autowired
    private PropService propService;
    @Autowired
	private ZoneSaleMapper<ZoneSale> zoneSaleMapper;

    
	public ProductSku getProductSkuById(Integer id) throws YiPinStoreException {
		
		ProductSku ps = productMapper.getProductSkuById(id);
		if(ps == null){
			return null;
		}
		ps.setActivity(true);
		List<ProductSkuOptionValue> psovList = productSkuOptionValueMapper.listProductSkuOptionValuesByProductSkuId(ps.getProductSkuId());
		for (ProductSkuOptionValue psov : psovList) {
			String overrideSkuOptionValue = psov.getOverrideSkuOptionValue();
			String skuOptionValue = psov.getSkuOptionValue().getSkuOptionValue();
			String skuOptionName = psov.getSkuOptionValue().getSkuOption()
					.getSkuOptionName();
			if (StringUtils.isNotBlank(skuOptionName)) {
				String value = overrideSkuOptionValue == null ? skuOptionValue : overrideSkuOptionValue;
				if (skuOptionName.equals(ProductSku.SKUOPTIONNAME_SF)) {
					ps.setSkuSpecification(value);
				} else if (skuOptionName.equals(ProductSku.SKUOPTIONNAME_GL)) {
					ps.setSkuWeight(new BigDecimal(value));
				}
			}
		}
		if(ps.getProduct().getSellType() == Product.SELLTYPE_QG){
			ZoneSale zoneSale = zoneSaleMapper.getZoneByProductSku(id);
			if(zoneSale == null){
				ps.setProductSku_status_code(CodeConstants.PRODUCT_SOLD_OUT);
				ps.setActivity(false);
				return ps;
			}else{
				Date nowTime = new Date();
				int result1 = nowTime.compareTo(zoneSale.getSaleBeginTime());
				int result2 = nowTime.compareTo(zoneSale.getSaleEndTime());
				if(result1 == -1){
					ps.setProductSku_status_code(CodeConstants.PRODUCT_SOLD_OUT);
					ps.setActivity(false);
				}else if(result1 >= 0 && result2 <= 0){
						ps.setProductSku_status_code(CodeConstants.PRODUCT_SOLD);
						ps.setActivity(true);
				}else if(result2 == 1){
					ps.setProductSku_status_code(CodeConstants.PRODUCT_SOLD_OUT);
					ps.setActivity(false);
				}
			}
		}else if(ps.getProduct().getSellType() == Product.SELLTYPE_YS){
			ZoneSale zoneSale = zoneSaleMapper.getZoneSaleByProductId(ps.getProductId());
			if(zoneSale == null){
				ps.setProductSku_status_code(CodeConstants.PRODUCT_SOLD_OUT);
				ps.setActivity(false);
				return ps;
			}
		}
		return ps;
	}

	public ProductSku getProductSkuActivityStatus(Integer id, Integer num)
			throws YiPinStoreException {
		ProductSku ps = productMapper.getProductSkuById(id);
		ps.setActivity(true);
		int inventory = ps.getInventory();
		if (inventory == 0 || inventory > num) {
			ps.setErrorMsg("商品库存不足");
			ps.setActivity(false);
		}
		// SaleProduct saleProduct =
		// saleProductMapper.searchSaleByProductId(ps.getProductId());
		// if (saleProduct == null || "".equals(saleProduct)) {
		// ps.setErrorMsg("商品已下架");
		// ps.setActivity(false);
		// }else{
		// ps.setSellType(saleProduct.getSale().getSellType());
		// }
		return ps;
	}

	@Override
	public void searchProducts(BasePagination<Product> productPage)
			throws YiPinStoreException {
		Map<String, Object> searchParams = productPage.getSearchParamsMap();
		if (productPage.isNeedSetTotal()) {
			Integer total = productMapper.searchProductsCount(searchParams).size();
			productPage.setTotal(total);
		}
		List<Product> result = productMapper.searchProducts(searchParams);
		if (result.size() > 0) {
			handleResult(result);
		}
		productPage.setResult(result);
	}

	/**
	 * 分页查询卖家商品
	 * 
	 * @param productPage
	 * @param sellerId
	 */
	@Override
	public void searchSellerProducts(BasePagination<Product> productPage,
			Integer sellerId) throws YiPinStoreException {
		Map<String, Object> searchParams = productPage.getSearchParamsMap();
		searchParams.put("sellerId", sellerId);
		if (productPage.isNeedSetTotal()) {
			Integer total = productMapper.searchSellerProductsCount(
					searchParams).size();
			productPage.setTotal(total);
		}
		List<Product> result = productMapper.searchSellerProducts(searchParams);
		if (result.size() > 0) {
			handleResult(result);
		}
		productPage.setResult(result);
	}

	private void handleResult(List<Product> result) {
		for (Product product : result) {
			Short status = product.getStatus();
			if (status == Product.STATUS_CHECK_PEND || status == Product.STATUS_NO_PASS || status == Product.STATUS_DRAFT) {
				product.setSellStatus(Product.STATUS_DRAFT);// 草稿
			} else if (product.getInventory() > 0 && status == Product.STATUS_PASS) {
				product.setSellStatus(Product.STATUS_SELL);// 销售中
			} else if (product.getInventory() == 0 && status == Product.STATUS_PASS) {
				product.setSellStatus(Product.STATUS_SELLOUT);// 已售罄
			}  else if (status == Product.STATUS_DELETE ) {
				product.setSellStatus(status);// 商品已经删除
			}
		}
	}

	/**
	 * 获取商品信息用于修改页面
	 * 
	 * @param productId
	 * @param sellerId
	 * @return
	 */
	@Override
	public Product getProduct4Edit(Integer productId, Integer sellerId) throws YiPinStoreException {
		// 获取商品的基本属性信息
		Product product = productMapper.getProductByProductIdAndUserId(
				productId, sellerId);
		// 获取商品的颜色、尺寸信息
		List<SkuOption> skuOptions = productMapper.listProductSkuOptionsByProductId(productId);
		for (SkuOption skuOption : skuOptions) {
			List<SkuOptionValue> skuOptionValues = productMapper.listProductSkuOptionValuesBySkuOptionId(skuOption.getSkuOptionId(), productId);
			skuOption.setSkuOptionValues(skuOptionValues);
		}
		product.setSkuOptions(skuOptions);

		// 获取product_sku表数据
		List<ProductSku> productSkus = productSkuMapper.listProductSkusByProductId(productId);
		for (ProductSku productSku : productSkus) {
			// productSku = getProductSkuProps(productSku);
			// //获取product_sku_option_value表数据
			List<ProductSkuOptionValue> proSkuOptValues = productSkuOptionValueMapper.listProductSkuOptionValuesByProductSkuId(productSku.getProductSkuId());
			for (int i = 0; i < proSkuOptValues.size(); i++) {
				ProductSkuOptionValue psv = proSkuOptValues.get(i);
				String skuoptionname = psv.getSkuOptionValue().getSkuOption().getSkuOptionName();
				if (skuoptionname.equals(ProductSku.SKUOPTIONNAME_GL)) {
					productSku.setSkuWeight(new BigDecimal(psv.getOverrideSkuOptionValue()));
					proSkuOptValues.remove(i);
				}
			}
			productSku.setProductSkuOptionValues(proSkuOptValues);
		}
		product.setProductSkus(productSkus);
		return product;
	}

	/**
	 * @作者：罗典
	 * @描述：根据商家ID查询审核通过且库存大于0的商品
	 * @参数：sellerId 商家ID，brandId 品牌ID
	 * @时间：2013-12-10
	 * */
	public List<Product> listPassProBySellerId(Integer sellerId, Integer brandId)
			throws YiPinStoreException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sellerId", sellerId);
		map.put("brandId", brandId);
		return productMapper.listPassProBySellerId(map);
	}

	/**
	 * 售罄
	 * 
	 * @param product
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void sellOut(Product product) throws YiPinStoreException {
		// 更新商品库存为0
		List<Integer> productSkuIds = productSkuMapper.listProductSkuIdsByProductIdAndUserId(product.getProductId(),product.getUserId());
		if (productSkuIds != null && productSkuIds.size() > 0) {
			productSkuMapper.updateInventoryByIds(productSkuIds);
		}
	}


	@Override
	public List<ProductSku> listProductSkuDetail(Product product)
			throws YiPinStoreException {
		List<ProductSku> prodectSkuList = productMapper.searchProductSkuDetail(product.getProductId());
		for (ProductSku ps : prodectSkuList) {
			ps.setProduct(product);
			List<ProductSkuOptionValue> psovList = productSkuOptionValueMapper.listProductSkuOptionValuesByProductSkuId(ps.getProductSkuId());
			for (ProductSkuOptionValue psov : psovList) {
				String overrideSkuOptionValue = psov.getOverrideSkuOptionValue();
				String skuOptionValue = psov.getSkuOptionValue().getSkuOptionValue();
				String skuOptionName = psov.getSkuOptionValue().getSkuOption().getSkuOptionName();
				if (StringUtils.isNotBlank(skuOptionName)) {
					String value = overrideSkuOptionValue == null ? skuOptionValue: overrideSkuOptionValue;
					if (skuOptionName.equals(ProductSku.SKUOPTIONNAME_SF)) {
						ps.setSkuSpecification(value);
					} else if (skuOptionName.equals(ProductSku.SKUOPTIONNAME_GL)) {
						ps.setSkuWeight(new BigDecimal(value));
					}
				}
			}
		}
		return prodectSkuList;
	}

    public void updatequickProduct(Product product) throws YiPinStoreException {
		productSkuMapper.updateInventoryQuick(product.getProductSku());
	}
    
    @Override
    public Product getProductByProductId(Integer productId) throws YiPinStoreException {
        Product product = productMapper.get(productId);
        String productProp = product.getProductProp();
        if(productProp != null && !"".equals(productProp)){
        	JSONObject object = JSON.parseObject(productProp);
            JSONArray area = (JSONArray) object.get("area");
            JSONArray catalog = (JSONArray) object.get("catalog");
            JSONArray adaptPeople = (JSONArray) object.get("adaptPeople");
            String province = (String) JSON.parseObject(area.get(0).toString()).get("province");
            String city = (String) JSON.parseObject(area.get(0).toString()).get("city");
            String big = (String) JSON.parseObject(catalog.get(0).toString()).get("big");
            String small = (String) JSON.parseObject(catalog.get(0).toString()).get("small");
            String people = (String) JSON.parseObject(adaptPeople.get(0).toString()).get("people");
            List<Prop> propArea = propService.getPropByIds(new String[]{province,city});
            List<Prop> propCatalog = propService.getPropByIds(new String[]{big,small});
            List<Prop> peopleCatalog = propService.getPropByIds(people.split(CommonConstants.STR_SPLIT+" "));
            Map<String,List<Prop>> map = new HashMap<String, List<Prop>>();
            map.put("area", propArea);
            map.put("catalog", propCatalog);
            map.put("adaptPeople", peopleCatalog);
            product.setPropsMap(map);
        }
        //获取商品的颜色、尺寸信息
        List<ProductSku> prodectSkuList = listProductSkuDetail(product);
        product.setProductSkus(prodectSkuList);
        return product;
    }


/*	@Override
	public Product getProductByProductId(Integer productId)
			throws YiPinStoreException {
		Product product = productMapper.get(productId);
		// 获取商品的颜色、尺寸信息
		List<ProductSku> prodectSkuList = listProductSkuDetail(productId);
		product.setProductSkus(prodectSkuList);
		return product;
	}*/

	/**
	 * 保存审核结果审核
	 * 
	 * @param product
	 */
	@Override
	public void saveReviewResult(Product product) throws YiPinStoreException {
		productMapper.updateProductReviewResult(product);
	}

	/**
	 * 根据productId,sellerId删除一件商品
	 * 
	 * @param product
	 */
	@Override
	public void deleteProduct(Product product) throws YiPinStoreException {
		// 逻辑删除
		product.setStatus(Product.STATUS_DELETE);
		productMapper.updateProductStatus(product);
	}

	/**
	 * 获取SKU选项及相应值信息
	 * 
	 * @return
	 */
	@Override
	public List<SkuOption> getSkuOptions() throws YiPinStoreException {
		List<SkuOption> skuOptions = skuOptionMapper.listSkuOptions();
		for (SkuOption skuOption : skuOptions) {
			List<SkuOptionValue> skuOptionValues = skuOptionValueMapper.listSkuOptionValues(skuOption.getSkuOptionId());
			skuOption.setSkuOptionValues(skuOptionValues);
		}
		return skuOptions;
	}

	/**
	 * 得到商品信息副本用于预览、复制
	 * 
	 * @param product
	 * @return
	 */
	@Override
	public Product getProductCopy(Integer productId,Integer userId) throws YiPinStoreException {
		Product product = getProduct4Edit(productId, userId);
		product.setProductId(null);
		return product;
	}
    private String appendSkuWeightCode(String weightCode){
    	int weightLength = weightCode.length();
    	if(weightLength==1){
    		weightCode="000"+weightCode;
    	}else if(weightLength==2){
    		weightCode="00"+weightCode;
    	}else if(weightLength==3){
    		weightCode="0"+weightCode;
    	}else if(weightLength==4){
    		return weightCode;
    	}
    	return weightCode;
    }
    /**
     * 保存商品信息
     * 
     * @param product
     */
    @Override
    public void saveProduct(Product product) throws YiPinStoreException {
        if (product.getProductId() == null) {
            //保存操作
            productMapper.add(product);
            List<ProductSku> productSkus = product.getProductSkus();
            if (productSkus != null && productSkus.size() > 0) {
                for (ProductSku productSku : productSkus) {
                	String skucode = product.getUserId()+"-"+product.getProductId()+"-YCP"+appendSkuWeightCode(productSku.getSkuWeight().toString());
                    productSku.setProductId(product.getProductId());
                    productSku.setSkuCode(skucode);
                    productSkuMapper.add(productSku);

                    List<ProductSkuOptionValue> proSkuOptionValues = productSku.getProductSkuOptionValues();
                    if (proSkuOptionValues != null && proSkuOptionValues.size() > 0) {
                        for (ProductSkuOptionValue proSkuOptionValue : proSkuOptionValues) {
                            proSkuOptionValue.setProductSkuId(productSku.getProductSkuId());
                            productSkuOptionValueMapper.add(proSkuOptionValue);
                        }
                    }
                }
            }
            List<ProductProp> ppList = product.getPpList();
            for(ProductProp pp : ppList){
            	pp.setProductId(product.getProductId());
            	productMapper.addProductProp(pp);
            }
        } else {
            //修改操作
            productMapper.update(product);
            List<ProductProp> ppList = product.getPpList();
            productMapper.delProductProp(product.getProductId());
            for(ProductProp pp : ppList){
            	pp.setProductId(product.getProductId());
            	productMapper.addProductProp(pp);
            }
            
            List<ProductSku> originalProSkus = productSkuMapper.listProductSkusByProductId(product.getProductId());
            List<ProductSku> currentProSkus = product.getProductSkus();

            //得到新增的productsku
            Iterator<ProductSku> it1 = currentProSkus.iterator();
            while (it1.hasNext()) {
                ProductSku proSku = it1.next();
                //保存新增项
                if (proSku.getProductSkuId() == null) {
                	String skucode = product.getUserId()+"-"+product.getProductId()+"-YCP"+appendSkuWeightCode(proSku.getSkuWeight().toString());
                    proSku.setProductId(product.getProductId());
                    proSku.setSkuCode(skucode);
                    productSkuMapper.add(proSku);
                    
                    List<ProductSkuOptionValue> proSkuOptValues = proSku.getProductSkuOptionValues();
                    if (proSkuOptValues != null && proSkuOptValues.size() > 0) {
                        for (ProductSkuOptionValue proSkuOptValue : proSkuOptValues) {
                            proSkuOptValue.setProductSkuId(proSku.getProductSkuId());
                            productSkuOptionValueMapper.add(proSkuOptValue);
                        }
                    }
                }
            }

            //得到修改项
            Iterator<ProductSku> it2 = originalProSkus.iterator();
            while (it2.hasNext()) {
                ProductSku proSku = it2.next();
                for (ProductSku currentProSku : currentProSkus) {
                    //更新修改项
                    if (proSku.getProductSkuId().equals(currentProSku.getProductSkuId())) {
                    	String skucode = product.getUserId()+"-"+product.getProductId()+"-YCP"+appendSkuWeightCode(currentProSku.getSkuWeight().toString());
                    	currentProSku.setSkuCode(skucode);
                    	productSkuMapper.update(currentProSku);
                        //得到修改后的sku属性信息
                        List<ProductSkuOptionValue> proSkuOptValues = currentProSku.getProductSkuOptionValues();
                        //删除原有的sku属性信息保存更新后的信息
                        productSkuOptionValueMapper.delProductSkuOptionValueByProductSkuId(currentProSku.getProductSkuId());
                        if (proSkuOptValues != null && proSkuOptValues.size() > 0) {
                            for (ProductSkuOptionValue proSkuOptValue : proSkuOptValues) {
                                proSkuOptValue.setProductSkuId(currentProSku.getProductSkuId());
                                productSkuOptionValueMapper.add(proSkuOptValue);
                            }
                        }
                        //从originalProSkus集合中移除修改项
                        it2.remove();
                        break;
                    }
                }
            }

            //得到删除项
            if (originalProSkus.size() > 0) {
                productSkuMapper.logicDelBatch(originalProSkus);
            }
        }
    }

	/**
	 * 获取商家商品
	 * 
	 * @param productId
	 * @param sellerId
	 * @return
	 */
	@Override
	public Product getProductByProductIdUserId(Integer productId, Integer userId)
			throws YiPinStoreException {
		Product product = productMapper.getProductByProductIdAndUserId(
				productId, userId);
		// 获取商品的颜色、尺寸信息
		List<SkuOption> skuOptions = productMapper
				.listProductSkuOptionsByProductId(productId);
		for (SkuOption skuOption : skuOptions) {
			List<SkuOptionValue> skuOptionValues = productMapper
					.listProductSkuOptionValuesBySkuOptionId(
							skuOption.getSkuOptionId(), productId);
			skuOption.setSkuOptionValues(skuOptionValues);
			String skuOptionName = skuOption.getSkuOptionName();
			// if (ProductSku.SKUOPTIONNAME_COLOR.equals(skuOptionName.trim()))
			// {
			// product.setProductSkuColorNum(skuOptionValues.size());
			// } else if
			// (ProductSku.SKUOPTIONNAME_GL.equals(skuOptionName.trim())) {
			// product.setProductSkuSizeNum(skuOptionValues.size());
			// }
		}
		product.setSkuOptions(skuOptions);

		// 获取product_sku表数据
		List<ProductSku> productSkus = productSkuMapper
				.listProductSkusByProductId(productId);
		for (ProductSku productSku : productSkus) {
			// 获取product_sku_option_value表数据
			List<ProductSkuOptionValue> proSkuOptValues = productSkuOptionValueMapper
					.listProductSkuOptionValuesByProductSkuId(productSku
							.getProductSkuId());
			productSku.setProductSkuOptionValues(proSkuOptValues);

			for (ProductSkuOptionValue proSkuOptValue : proSkuOptValues) {
				String skuOptionName = proSkuOptValue.getSkuOptionValue()
						.getSkuOption().getSkuOptionName();
				String skuOptionValue = proSkuOptValue.getSkuOptionValue()
						.getSkuOptionValue();
				String overrideSkuOptionValue = proSkuOptValue
						.getOverrideSkuOptionValue();
				if (overrideSkuOptionValue != null) {
					skuOptionValue = overrideSkuOptionValue;
				}
				// if
				// (ProductSku.SKUOPTIONNAME_COLOR.equals(skuOptionName.trim()))
				// {
				// productSku.setProductSkuColor(skuOptionValue);
				// } else if
				// (ProductSku.SKUOPTIONNAME_GL.equals(skuOptionName.trim())) {
				// productSku.setProductSkuSize(skuOptionValue);
				// }
			}
		}
		product.setProductSkus(productSkus);
		return product;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: checkSellOut
	 * </p>
	 * <p>
	 * Description:售罄图片设置
	 * </p>
	 * 
	 * @param products
	 *            商品集合
	 * @return 商品集合
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#checkSellOut(java.util.List)
	 */
	@Override
	public List<Product> checkSellOut(List<Product> products)
			throws YiPinStoreException {
		if (products == null || products.size() < 1) {
			throw new YiPinStoreException("本品牌未添加商品信息");
		}

		List<Integer> producIds = new ArrayList<Integer>();
		for (Product product : products) {
			if (product != null) {
				producIds.add(product.getProductId());
			}
		}
		// 获取商品库存信息
		List<ProductSku> productSkus = this.productSkuMapper.listBrandProducts(producIds);

		if (productSkus != null) {
			for (ProductSku sku : productSkus) {
				if (sku != null && sku.getInventory() < 1) { // 如果库存小于1
					for (Product product : products) {
						if (product != null && product.getProductId().intValue() == sku.getProductId().intValue()) {
							product.setImageUrls(null); // 设置图片为空，显示售罄图片
							break;
						}
					}
				}
			}
		}
		return products;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: listProductsByBrandIdAndStatus
	 * </p>
	 * <p>
	 * Description: 购买商品陈列展示数据
	 * </p>
	 * 
	 * @param brandId
	 *            品牌ID
	 * @return 商品集合
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.SaleProductService#listProductsByBrandIdAndStatus(java.lang.Integer)
	 */
	@Override
	public List<Product> listProductsByBrandIdAndStatus(Integer brandId)
			throws YiPinStoreException {
		try {
			return this.productMapper.listProductsByBrandIdAndStatus(brandId);
		} catch (DataAccessException e) {
			logger.error("error:", e.getMessage());
			throw new YiPinStoreException("获取品牌商品失败！");
		}
	}

	/**
	 * 减库存
	 * 
	 * @param productSkuId
	 * @param num
	 */
	@Override
	public Integer updateInventory4Reduce(Integer productSkuId, Integer num)
			throws YiPinStoreException {
		Integer count = productSkuMapper.changeProductSkuInventory(
				productSkuId, num);
		return count;
	}

	/**
	 * 取消订单时,归还库存
	 * 
	 * @param productSkuId
	 * @param num
	 * @return
	 */
	@Override
	public Integer updateInventory4Revert(Integer productSkuId, Integer num)
			throws YiPinStoreException {
		Integer count = productSkuMapper.revertProductSkuInventory(
				productSkuId, num);
		return count;
	}

	/**
	 * <p>
	 * Description: 根据查询商品 ksuCode
	 * </p>
	 * 
	 * @param skuCode
	 * @return 商品集合
	 */
	@Override
	public Map<String, Product> searchProductBySkuCode()
			throws YiPinStoreException {
		List<Product> list = productMapper.searchProductBySkuCode();
		HashMap<String, Product> map = new HashMap<String, Product>();
		for (int i = 0; i < list.size(); i++) {
			// if (!map.containsKey(list.get(0))) {
			map.put(list.get(i).getSkuCode(), list.get(i));
			// }
		}

		return map;
	}

	@Override
	public Map<String, Product> listProductsByBrandIdAndStatus2(Integer brandId)
			throws YiPinStoreException {
		List<Product> list = productMapper
				.listProductsByBrandIdAndStatus(brandId);
		HashMap<String, Product> map = new HashMap<String, Product>();
		/*
		 * for (int i = 0; i < list.size(); i++) {
		 * map.put(list.get(i).getProductSku().getSkuCode(), list.get(i)); //
		 * map.put(list.get(i).getSkuCode(),list.get(i)); }
		 */
		for (Product product : list) {
			if (null != product && StringUtils.isNotBlank(product.getSkuCode())) {
				map.put(product.getSkuCode(), product);
			}
		}
		return map;
	}

	/**
	 * 根据skuCode查询商品信息集合
	 * 
	 * @param skuCodes
	 * @return
	 */
	@Override
	public Map<String, ProductSku> listProductSkusBySkuCode()
			throws YiPinStoreException {
		String[] strs = new String[] { "HZLG01", "HZLG02", "JLWY01", "JLWY02",
				"JLME01", "JLME02", "JLHG01", "JLHG02" };
		List<String> skuCodes = Arrays.asList(strs);
		List<ProductSku> productSkus = productSkuMapper
				.listProductSkusBySkuCode(skuCodes);
		Map<String, ProductSku> map = new HashMap<String, ProductSku>();
		for (ProductSku proSku : productSkus) {
			String skuCode = proSku.getSkuCode();
			if (!map.containsKey(skuCode)) {
				map.put(skuCode, proSku);
			}
		}
		return map;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getBrandIdBySkuId
	 * </p>
	 * <p>
	 * Description: 根据商品skuId获取品牌ID
	 * </p>
	 * 
	 * @param productSkuId
	 *            商品品牌SKUID
	 * @return 品牌ID
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#getBrandIdBySkuId(java.lang.Integer)
	 */
	@Override
	public Integer getBrandIdBySkuId(Integer productSkuId)
			throws YiPinStoreException {
		return this.productMapper.getBrandIdBySkuId(productSkuId);
	}

	/**
	 * 前台获取商品详细信息
	 * @param productOrSkuId
	 * @param outId
	 * @param zoneType
	 * @return
	 */
	@Override
	public Product getProductById4Front(Integer skuId) throws YiPinStoreException {
		Product product = productMapper.getProductBySkuId(skuId);
		if(product == null){
			throw new YiPinStoreException(CodeConstants.CART_PRODUCT_DROP);
		}
		if(Product.SELLTYPE_QG.equals(product.getSellType())){
			ZoneSale zoneSale = zoneSaleMapper.getSpecialZoneSaleBySkuId(skuId);
			if(zoneSale == null){
				throw new YiPinStoreException(CodeConstants.CART_PRODUCT_DROP);
			}
			product.setZone(zoneSale.getZone());
			product.getProductSku().setSaleBeginTime(zoneSale.getSaleBeginTime());
			product.getProductSku().setSaleEndTime(zoneSale.getSaleEndTime());
			setProductSkuProps(product.getProductSku());
			setSkuSaleStatus(product.getProductSku());
			product.setPropsMap(getProductCatologOrArea(product.getProductId()).getPropsMap());
			return product;
		}
		
		if(Product.SELLTYPE_YS.equals(product.getSellType())){
			Date nowTime = new Date();
			if(nowTime.compareTo(product.getPreDeliveryTime()) == 1){
				throw new YiPinStoreException(CodeConstants.CART_PRODUCT_DROP);
			}
			List<ZoneSale> list = zoneSaleMapper.listZoneSalesByProductIdZoneType(product.getProductId(), Zone.ZONE_TYPE_SALE);
			
			if(list.size() == 0){
				throw new YiPinStoreException(CodeConstants.CART_PRODUCT_DROP);
			}
			product.setZone(list.get(0).getZone());
		}
		List<ProductSku> productSkus = listProductSkus(product.getProductId());
        product.setProductSkus(productSkus);
        product.setDefaultProductSku(getDefaultProductSku(productSkus));
        product.setTotalInventory(getTotalInventory(productSkus));
        product.setPropsMap(getProductCatologOrArea(product.getProductId()).getPropsMap());
		return product;
	}
	
	@Override
	public List<ProductSku> listProductSkus(Integer productId)
			throws YiPinStoreException {
		//查询productSku信息
		List<ProductSku> productSkus = productSkuMapper.listProductSkusByProductId(productId);
		for (ProductSku productSku : productSkus) {
			setProductSkuProps(productSku);
        }
		return productSkus;
	}

	@Override
	public void setSkuSaleStatus(ProductSku productSku)
			throws YiPinStoreException {
		Date nowTime = new Date();
		int result1 = nowTime.compareTo(productSku.getSaleBeginTime());
		int result2 = nowTime.compareTo(productSku.getSaleEndTime());
		if(result1 == -1){
			productSku.setSaleStatus(ProductSku.SALE_STATUS_PEND);
		}else if(result1 >= 0 && result2 <= 0){
			if(productSku.getInventory() == 0){
				productSku.setSaleStatus(ProductSku.SALE_STATUS_STOCKOUT);
			}else{
				productSku.setSaleStatus(ProductSku.SALE_STATUS_ON);
			}
		}else if(result2 == 1){
			productSku.setSaleStatus(ProductSku.SALE_STATUS_EXPIRED);
		}
	}
	
	@Override
	public void setProductSkuProps(ProductSku productSku) throws YiPinStoreException {
		List<ProductSkuOptionValue> proSkuOptValues = productSkuOptionValueMapper
				.listProductSkuOptionValuesByProductSkuId(productSku.getProductSkuId());
		productSku.setProductSkuOptionValues(proSkuOptValues);
		//设置productSku的属性值
		for (ProductSkuOptionValue proSkuOptValue : proSkuOptValues) {
			SkuOptionValue skuOptionValue = proSkuOptValue.getSkuOptionValue();
			Integer skuOptionId = skuOptionValue.getSkuOptionId();
			String skuValue = skuOptionValue.getSkuOptionValue();
		    //自定义属性值
		    String overrideSkuOptionValue = proSkuOptValue.getOverrideSkuOptionValue();
		    if (overrideSkuOptionValue != null) {
		        skuValue = overrideSkuOptionValue;
		    }
		    //区分规则、重量
		    if (SkuOption.SKUOPTION_SPECIFICATION_ID.equals(skuOptionId)) {
		        productSku.setSkuSpecification(skuValue);
		    } else if (SkuOption.SKUOPTION_WEIGHT_ID.equals(skuOptionId)) {
		        productSku.setSkuWeight(new BigDecimal(skuValue));
		    }
		}
		//计算运费
		productSku.setPostage(calPostage(productSku));
	}
	
	@Override
	public BigDecimal calPostage(ProductSku productSku) throws YiPinStoreException {
		BigDecimal postage = new BigDecimal(0);
		BigDecimal weight = productSku.getSkuWeight();
		if(weight == null){
			weight = new BigDecimal(0);
		}
		if(weight.compareTo(new BigDecimal(1000)) == -1){
			postage = new BigDecimal(10);
		}else{
			postage = new BigDecimal(20);
		}
		return postage;
	}
	
	@Override
	public ProductSku getDefaultProductSku(List<ProductSku> productSkus)
			throws YiPinStoreException {
		if(productSkus.size() == 1){
			return productSkus.get(0);
		}
		List<ProductSku> list = new ArrayList<ProductSku>();
		ProductSku defaultSku = null;
		for(ProductSku proSku : productSkus){
			if(proSku.getIsDefault() != null){
				if(proSku.getInventory() > 0){
					return proSku;
				}
				defaultSku = proSku;
			}else{
				if(proSku.getInventory() > 0){
					list.add(proSku);
				}
			}
		}
		if(list.size() > 0){
			ProductSku[] proSkuArray = new ProductSku[list.size()];
			Arrays.sort(list.toArray(proSkuArray), new ProductSkuComparator());
			return proSkuArray[0];
		}
		return defaultSku;
	}

	@Override
	public Integer getTotalInventory(List<ProductSku> productSkus) throws YiPinStoreException {
		Integer totalInventory = 0;
		for(ProductSku sku : productSkus){
			totalInventory += sku.getInventory();
		}
		return totalInventory;
	}
    
    /**
	 * 根据商品的id将商品的 hits加1 商品详情页面用
	 * @param product
	 */
    @Override
	public void updateProductHits(Product product) throws YiPinStoreException {
		productMapper.updateProductHits(product);
	}

	@Override
	public List<Product> getProductByZoneSale() throws YiPinStoreException {

		List<Product> productSale = productMapper.getProductByZoneSale();
		return productSale;
	}

	/**
	 * <p>
	 * Description: 根据专区普通类型查找商品
	 * </p>
	 * 
	 * @param
	 * @return 商品集合
	 */
	@Override
	public List<Product> getProductByZoneDefault() throws YiPinStoreException {
		List<Product> productDefault = productMapper.getProductByZoneDefault();
		return productDefault;
	}

	/**
	 * <p>
	 * Description: 根据专区抢购类型查找商品sku
	 * </p>
	 * 
	 * @param
	 * @return 商品集合
	 */
	@Override
	public List<ProductSku> getProductByZoneSpecial(List<Integer> zoneIds)
			throws YiPinStoreException {
		return productMapper.getProductByZoneSpecial(zoneIds);
	}

	@Override
	public List<ProductSku> listProductSkusByMap(Map<String, Object> params)
			throws YiPinStoreException {
		return productSkuMapper.listProductSkusByMap(params);
	}

	@Override
	public int countProductSkusByMap(Map<String, Object> params) {
		return productSkuMapper.countProductSkusByMap(params);
	}

	@Override
	public List<Prop> listPropsOfProduct(Integer productId) {
		return productSkuMapper.listPropsOfProduct(productId);
	}

	/**
	 * <p>
	 * Title: listProductByBarndFirstChar
	 * </p>
	 * <p>
	 * Description: 根据品牌首字母获取商品
	 * </p>
	 * 
	 * @param firstChar  首字母
	 * @param saleType 销售类型
	 * @return 商品信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#listProductByBarndFirstChar(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, List<Product>> listProductByBarndFirstChar(
			String firstChar, String saleType) throws YiPinStoreException {
		
		Map<String, List<Product>> map = new LinkedHashMap<String, List<Product>>();
		
		List<Product> products = null;
		Short type = Short.parseShort(saleType);
		// 销售
		if (Product.SELLTYPE_XS.equals(type)) {
			products = this.productMapper.listProductByBarndFirstCharAndXs(firstChar);
		}
		// 预售
		if (Product.SELLTYPE_YS.equals(type)) {
			products = this.productMapper.listProductByBarndFirstCharAndYs(firstChar);
		}
		List<Product> productList = new ArrayList<Product>(products.size());

		if (products != null) {

			// 设置商品规格
			if (!Product.SELLTYPE_QG.equals(type)) { // 非抢购
				for (Product p : products) {
					p.setProductSku(this.getDefaultProductSku(this.listProductSkus(p.getProductId())));
					productList.add(p);
				}
			}
		}
		Map<Integer, List<Product>> pMap = new LinkedHashMap<Integer, List<Product>>();
		if (productList != null) {
			for (Product p : productList) {
				if (p != null && p.getUserId() != null) {
					pMap.put(p.getUserId(), new ArrayList<Product>());
				}
			}
		}

		for (Iterator<Entry<Integer, List<Product>>> iter = pMap.entrySet().iterator(); iter.hasNext();) {
			Entry<Integer, List<Product>> entry = iter.next();
			Integer key = (Integer) entry.getKey();
			List<Product> plist = (List<Product>) entry.getValue();
			UserInfo user = this.userInfoService.getUserInfoById(key);
			if (productList != null) {
				for (Product p : productList) {
					if (p != null && p.getUserId() != null) {
						boolean flag = true;
						if (p.getUserId().equals(key)) {
							for(Product pro : plist){
								if(pro.getProductId()!=null && pro.getProductId().equals(p.getProductId())){
									flag = false;
								}
							}
							
							if(flag){
								plist.add(p);
							}
						}
					}
				}
			}
			if (user != null && StringUtils.isNotBlank(user.getCompanyName())) {
				map.put(user.getUserId() + user.getCompanyName(), plist);
			}
		}
		return map;
	}

	/**
	 * <p>
	 * Title: listProductByBarndFirstChar
	 * </p>
	 * <p>
	 * Description: 根据品牌首字母获取商品SKU
	 * </p>
	 * 
	 * @param firstChar
	 *            首字母
	 * @return 商品SKU
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#listProductByBarndFirstChar(java.lang.String)
	 */
	@Override
	public Map<String, List<ProductSku>> listProductByBarndFirstChar(
			String firstChar) throws YiPinStoreException {

		Map<String, List<ProductSku>> map = new LinkedHashMap<String, List<ProductSku>>();

		List<ProductSku> products = null;

		// 抢购
		products = this.productSkuMapper.listProductByBarndFirstCharAndQg(firstChar);

		List<ProductSku> productList = new ArrayList<ProductSku>(
				products.size());

		Map<Integer, List<ProductSku>> pMap = new LinkedHashMap<Integer, List<ProductSku>>();
		if (productList != null) {
			for (ProductSku p : products) {
				if (p != null && p.getUserId() != null) {
					pMap.put(p.getUserId(), new ArrayList<ProductSku>());
				}
			}
		}
		for (Iterator<Entry<Integer, List<ProductSku>>> iter = pMap.entrySet()
				.iterator(); iter.hasNext();) {
			Entry<Integer, List<ProductSku>> entry = iter.next();
			Integer key = (Integer) entry.getKey();
			List<ProductSku> plist = (List<ProductSku>) entry.getValue();
			UserInfo user = this.userInfoService.getUserInfoById(key);
			if (productList != null) {
				for (ProductSku p : products) {
					if (p != null && p.getUserId() != null) {
						if (p.getUserId().equals(key)) {
							this.setProductSkuProps(p);
							plist.add(p);
						}
					}
				}
			}
			if (user != null && StringUtils.isNotBlank(user.getCompanyName())) {
				map.put(user.getUserId() + user.getCompanyName(), plist);
			}
		}
		return map;
	}

	/**
	 * 抢购1 预售2专区3 根据专区类型查找商品
	 * 
	 * @param product
	 */
	@Override
	public List<Product> getProductByZoneId(List<Integer> zoneIds) {
		// List<Zone> zlist = zoneMapper.getZoneList();
		// List<Zone> zlist = zoneMapper.getZoneListBypreAndDefault();
		// List<Integer> zoneIds = new ArrayList<Integer>();
		// Integer zoneId = null;
		// for(Zone z:zlist){
		// zoneId = z.getZoneId();
		// }
		// zoneIds.add(zoneId);
		return productMapper.getProductByZoneId(zoneIds);
	}

	/**
	 * 抢购1 预售2专区3 根据专区类型查找商品
	 * 
	 * @param product
	 * @throws YiPinStoreException
	 */
	@Override
	public List<Product> getProductByZoneId(Zone zone, Integer limit)
			throws YiPinStoreException {
		List<Product> products = new ArrayList<Product>();
		
		if(Zone.ZONE_TYPE_SPECIAL.equals(zone.getZoneType())){
			products = productMapper.getProductByZoneSpecial(zone.getZoneId(),limit);
			Integer count = getCountProductByZoneSpecial(zone.getZoneId());
			zone.setCount(count);
			
		}else{
			products = productMapper.getProductByZoneId(zone, limit);
			Integer count = getCountProductByZoneId(zone.getZoneId());
			zone.setCount(count);
		}
		for (Product pro : products) {
			
			if(Product.SELLTYPE_QG.equals(pro.getSellType())){
				setSkuSaleStatus(pro.getProductSku());
			}else{
				List<ProductSku> productSkus = listProductSkus(pro.getProductId());
				pro.setDefaultProductSku(getDefaultProductSku(productSkus));
			}
		}
		return products;
	}

	@Override
	public Product getProductCatologOrArea(Integer productId) throws YiPinStoreException {
		Product product = productMapper.get(productId);
		if(product == null){
			return product;
		}
        String productProp = product.getProductProp();
        if(productProp != null && !"".equals(productProp)){
        	Map<String,List<Prop>> map = new HashMap<String, List<Prop>>();
        	JSONObject object = JSON.parseObject(productProp);
        	JSONArray area = (JSONArray) object.get(Product.TYPE_AREA);
            String province = (String) JSON.parseObject(area.get(0).toString()).get("province");
            String city = (String) JSON.parseObject(area.get(0).toString()).get("city");
            List<Prop> propArea = propService.getPropByIds(new String[]{province,city});
            map.put("area", propArea);
            JSONArray catalog = (JSONArray) object.get(Product.TYPE_CATALOG);
            String big = (String) JSON.parseObject(catalog.get(0).toString()).get("big");
            String small = (String) JSON.parseObject(catalog.get(0).toString()).get("small");
            List<Prop> propCatalog = propService.getPropByIds(new String[]{big,small});
            map.put("catalog", propCatalog);
        	JSONArray adaptPeople = (JSONArray) object.get(Product.TYPE_PEOPLE);
            String people = (String) JSON.parseObject(adaptPeople.get(0).toString()).get("people");
            List<Prop> peopleCatalog = propService.getPropByIds(people.split(CommonConstants.STR_SPLIT+" "));
            map.put("adaptPeople", peopleCatalog);
            product.setPropsMap(map);
        }
        return product;
	}

	/**
	 * <p>
	 * Title: listProductByZonId
	 * </p>
	 * <p>
	 * Description: 查询专区商品
	 * </p>
	 * 
	 * @param zoneId 专区ID
	 * @return
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#listProductByZonId(java.lang.String)
	 */
	@Override
	public Map<String, List<Product>> listProductByZonId(String zoneId)
			throws YiPinStoreException {
		Map<String, List<Product>> map = new LinkedHashMap<String, List<Product>>();

		List<Product> products = null;
		Zone zone = this.zoneMapper.getZoneById(new Integer(zoneId));
		Short type = zone.getZoneType();
		// 销售
		if (Zone.ZONE_TYPE_DEFAULT.equals(type)) {
			products = this.productMapper.listProductByZoneIdAndXs(zone.getZoneId());
		}else if (Zone.ZONE_TYPE_SALE.equals(type)) {
			products = this.productMapper.listProductByZoneIdAndYs(zone.getZoneId());
		}else{
			return null;
		}
		List<Product> productList = new ArrayList<Product>(products.size());

		if (products != null) {
			for (Product p : products) {
				// 设置商品规格
				if (!Zone.ZONE_TYPE_SPECIAL.equals(type)) { // 非抢购
					p.setProductSku(this.getDefaultProductSku(this.listProductSkus(p.getProductId())));
					p.setRank(this.zoneSaleMapper.getRankByProductIdAndZoneId(p.getProductId(),new Integer(zoneId)));
					productList.add(p);
				}
			}
			
		}
		Map<Integer, List<Product>> pMap = new LinkedHashMap<Integer, List<Product>>();
		if (productList != null) {
			for (Product p : productList) {
				if (p != null && p.getUserId() != null) {
					pMap.put(p.getUserId(), new ArrayList<Product>());
				}
			}
		}

		for (Iterator<Entry<Integer, List<Product>>> iter = pMap.entrySet()
				.iterator(); iter.hasNext();) {
			Entry<Integer, List<Product>> entry = iter.next();
			Integer key = (Integer) entry.getKey();
			List<Product> plist = (List<Product>) entry.getValue();
			UserInfo user = this.userInfoService.getUserInfoById(key);
			if (productList != null) {
				for (Product p : productList) {
					if (p != null && p.getUserId() != null) {
						if (p.getUserId().equals(key)) {
							plist.add(p);
						}
					}
				}
			}
			if (user != null && StringUtils.isNotBlank(user.getCompanyName())) {
				map.put(user.getUserId() + user.getCompanyName(), plist);
			}
		}
		return map;
	}

	/**
	 * <p>
	 * Title: listProductSkuByZoneId
	 * </p>
	 * <p>
	 * Description: 查询专区SKU
	 * </p>
	 * 
	 * @param zoneId 专区ID
	 * @return
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#listProductSkuByZoneId(java.lang.String)
	 */
	@Override
	public Map<String, List<ProductSku>> listProductSkuByZoneId(String zoneId)
			throws YiPinStoreException {

		Map<String, List<ProductSku>> map = new LinkedHashMap<String, List<ProductSku>>();

		List<ProductSku> products = null;

		// 抢购商品SKU
		products = this.productSkuMapper.listProductSkuByZoneId(new Integer(zoneId));

		List<ProductSku> productList = new ArrayList<ProductSku>(
				products.size());

		Map<Integer, List<ProductSku>> pMap = new LinkedHashMap<Integer, List<ProductSku>>();
		if (productList != null) {
			for (ProductSku p : products) {
				if (p != null && p.getUserId() != null) {
					pMap.put(p.getUserId(), new ArrayList<ProductSku>());
				}
			}
		}
		for (Iterator<Entry<Integer, List<ProductSku>>> iter = pMap.entrySet()
				.iterator(); iter.hasNext();) {
			Entry<Integer, List<ProductSku>> entry = iter.next();
			Integer key = (Integer) entry.getKey();
			List<ProductSku> plist = (List<ProductSku>) entry.getValue();
			UserInfo user = this.userInfoService.getUserInfoById(key);
			if (productList != null) {
				for (ProductSku p : products) {
					if (p != null && p.getUserId() != null) {
						if (p.getUserId().equals(key)) {
							this.setProductSkuProps(p);
							plist.add(p);
						}
					}
				}
			}
			if (user != null && StringUtils.isNotBlank(user.getCompanyName())) {
				map.put(user.getUserId() + user.getCompanyName(), plist);
			}
		}
		return map;
	}

	@Override
	public ProductSku listProductSkusByProductId(Integer productId) throws YiPinStoreException {
		List<ProductSku> productSkus = productSkuMapper.listProductSkusByProductId(productId);
		ProductSku productSku=getDefaultProductSku(productSkus);
		return productSku;
	}
	/**
	 * 查询抢购专区总数量
	 */
  @Override
   public Integer getCountProductByZoneSpecial(Integer zoneId) throws YiPinStoreException{
	  return this.productMapper.getCountProductByZoneSpecial(zoneId);
  }
    /**
     * 查询普通专区总数量
     */
  @Override
   public Integer getCountProductByZoneId(Integer zoneId) throws YiPinStoreException{
	  return this.productMapper.getCountProductByZoneId(zoneId);
  }

	@Override
	public Map<String,String> getOverrideSkuOptionValue(Integer productSkuId)
			throws YiPinStoreException {
		return productSkuMapper.getOverrideSkuOptionValue(productSkuId);
	}

	/** 
	 * <p>Title: listProductByBarndFirstChar</p>
	 * <p>Description: </p>
	 * @param zoneId
	 * @param firstChar
	 * @param saleType
	 * @return
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ProductService#listProductByBarndFirstChar(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, List<Product>> listProductByBarndFirstChar(
			String zoneId, String firstChar, String saleType)
			throws YiPinStoreException {
		
		Map<String, List<Product>> map = new LinkedHashMap<String, List<Product>>();
		
		List<Product> products = null;
		Short type = Short.parseShort(saleType);
		// 销售
		if (Product.SELLTYPE_XS.equals(type)) {
			products = this.productMapper.listProductByBarndFirstCharAndXs(firstChar);
		}
		// 预售
		if (Product.SELLTYPE_YS.equals(type)) {
			products = this.productMapper.listProductByBarndFirstCharAndYs(firstChar);
		}
		List<Product> productList = new ArrayList<Product>(products.size());

		if (products != null) {

			// 设置商品规格
			if (!Product.SELLTYPE_QG.equals(type)) { // 非抢购
				for (Product p : products) {
					p.setProductSku(this.getDefaultProductSku(this.listProductSkus(p.getProductId())));
					if(StringUtils.isNotBlank(zoneId)){
						p.setRank(this.zoneSaleMapper.getRankByProductIdAndZoneId(p.getProductId(),new Integer(zoneId)));
					}
					productList.add(p);
				}
			}
		}
		Map<Integer, List<Product>> pMap = new LinkedHashMap<Integer, List<Product>>();
		if (productList != null) {
			for (Product p : productList) {
				if (p != null && p.getUserId() != null) {
					pMap.put(p.getUserId(), new ArrayList<Product>());
				}
			}
		}

		for (Iterator<Entry<Integer, List<Product>>> iter = pMap.entrySet().iterator(); iter.hasNext();) {
			Entry<Integer, List<Product>> entry = iter.next();
			Integer key = (Integer) entry.getKey();
			List<Product> plist = (List<Product>) entry.getValue();
			UserInfo user = this.userInfoService.getUserInfoById(key);
			if (productList != null) {
				for (Product p : productList) {
					if (p != null && p.getUserId() != null) {
						if (p.getUserId().equals(key)) {
								plist.add(p);
						}
					}
				}
			}
			if (user != null && StringUtils.isNotBlank(user.getCompanyName())) {
				map.put(user.getUserId() + user.getCompanyName(), plist);
			}
		}
		return map;	}
	
}
