package com.ytoxl.yipin.web.action.sale;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.City;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.Sale;
import com.ytoxl.module.yipin.base.dataobject.SaleProduct;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.CityService;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.SaleProductService;
import com.ytoxl.module.yipin.base.service.SaleService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * @作者：罗典
 * @描述：预售信息请求
 * @时间：2013-12-06
 * */
@Controller
public class SaleAction extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(SaleAction.class);
	private static final long serialVersionUID = 1L;
	
	public static final String SEARCH_SALES = "searchSales";
	
	// 预售信息
	private Sale sale;
	// 预售编辑界面展示时预售信息
	private Sale saleInfo;
	// 商家信息
	private List<UserInfo> userInfos;
	/*// 编辑时关联的商品用户信息
	private List<UserInfo> userInfoList;*/
	// 预售信息集合
	private List<Sale> sales;
	// 商家ID列表(一键发布，发布，下架)
	private List<String> saleIds;
	// 品牌城市列表
	private List<City> cityList;
	// 城市ID(城市品牌2级联动用)
	private Integer cityId;
	// 品牌集合
	private List<Brand> brands; 
	// 是否是页面查询功能(默认不是)
	private Boolean isPageSearch = false;
	// 编辑和新增预售时，是否已经有线程在执行此功能
	private static boolean isRuning = false;
	
	private BasePagination<Sale> salePage;
	// 品牌service
	@Autowired
	private BrandService brandService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * @作者：罗典
	 * @描述：预售信息列表展示界面初始化
	 * @时间：2013-12-06
	 * */
	public String searchSales(){
		try {
			// 页面初始化加载时，默认查询一周的数据
			if (salePage == null) {
				salePage = new BasePagination<Sale>();
				Map<String, String> searchParams = new HashMap<String,String>();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				searchParams.put("startTime", format.format(new Date()));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_MONTH, 6); 
				searchParams.put("endTime",format.format(calendar.getTime()));
				salePage.setParams(searchParams);
			}
			saleService.searchSales(salePage);
			brands = brandService.listBrands();
		} catch (YiPinStoreException e) {
			logger.error("查询商品出错", e);
		} catch (Exception e) {
			logger.error("查询商品出错", e);
		}
		return SEARCH_SALES;
	}

	
	/**
	 * @作者：罗典
	 * @描述：预售信息新增界面初始化
	 * @时间：2013-12-06
	 * */
	public String load() throws YiPinStoreException {
		// saleId 不为空的时候则为编辑功能
		if(sale != null && sale.getSaleId()!= null){
			sale = saleService.getSaleById(sale.getSaleId());
			List<SaleProduct> saleProductList = saleProductService.listAllBySaleId(sale.getSaleId());
			Map<Integer,UserInfo> userMap = new HashMap<Integer,UserInfo>();
			for(SaleProduct saleProduct : saleProductList){
				Integer userId =  saleProduct.getUserInfo().getUser().getUserId();
				if(!userMap.containsKey(userId)){
					List<Product> productList = new ArrayList<Product>();
					saleProduct.getUserInfo().setProductList(productList);
					userMap.put(userId,saleProduct.getUserInfo());
				}
				UserInfo userInfo = userMap.get(userId);
//				saleProduct.getProduct().setSaleProduct(saleProduct);
				userInfo.getProductList().add(saleProduct.getProduct());
			}
			userInfos = new ArrayList<UserInfo>();
			userInfos.addAll(userMap.values());
		}
		cityList = cityService.searchAllCity();
		if(sale != null && sale.getSaleId() != null && sale.getSaleId() != 0){
			saleInfo = saleService.getSaleById(sale.getSaleId());
			cityId =brandService.searchCityIdByBrandId(sale.getBrandId()); 
			sale.setCityId(cityId);
			brands = brandService.listBrandByCityId(cityId);
		}
		return SUCCESS;
	}
	
	/**
	 * @作者：罗典
	 * @描述：根据城市ID查询所有品牌(界面品牌城市二级联动)
	 * @时间：2013-12-17
	 * */
	public String searchBrandsByCityId() throws YiPinStoreException {
		brands = brandService.listBrandByCityId(cityId);
		String json = JSONArray.fromObject(brands).toString();
		setMessage(json);
		return JSONMSG;
	}
	
	/**
	 * @作者：罗典
	 * @描述：预售信息新增/编辑
	 * @时间：2013-12-06
	 * */
	public String saveSale() {
		try{
			if(!isRuning){
				isRuning = true;
				// 只能存在一条有效的预售信息
				Sale saleCondition = new Sale(); 
				saleCondition.setBrandId(sale.getBrandId());
				// 商品状态
				List<Short> statusList = new ArrayList<Short>();
				statusList.add(Sale.STATUS_RELEASE);
				statusList.add(Sale.STATUS_UNRELEASE);
				saleCondition.setStatusList(statusList);
				List<Sale> list = saleService.listBySale(saleCondition);
				// 如果销售类型为预售，则清空预售时间字段
				if(Sale.TYPE_SELL.equals(sale.getSellType())){
					sale.setPreSelltime(new Date());
				}
				// 存在sale.saleId则代表为编辑操作，反之则为保存操作
				if(sale != null && sale.getSaleId() != null){
					Sale oldSale = saleService.getSaleById(sale.getSaleId());
					if(!oldSale.getBrandId().equals(sale.getBrandId()) && list != null && list.size() > 0 ){
						setMessage(Boolean.FALSE.toString(),"此品牌已存在有效的预售信息！");
					}else{
						if(Sale.TYPE_SELL.equals(oldSale.getSellType())){
							sale.setPreSelltime(oldSale.getPreSelltime());
						}
						saleService.updateSaleBySale(sale);
						setMessage(SUCCESS,"修改成功");
					}
				}else{
					if(list != null && list.size() > 0){
						setMessage(Boolean.FALSE.toString(),"此品牌已存在有效的预售信息！");
					}
					else{
						sale.setStatus(Sale.STATUS_UNRELEASE);
						saleService.saveSale(sale);
						setMessage(SUCCESS,"保存成功");
					}
				}
			}else{
				setMessage(Boolean.FALSE.toString(),"请稍后再试，谢谢配合~");
			}
			isRuning = false;
		}catch(YiPinStoreException e){
			isRuning = false;
			setMessage(Boolean.FALSE.toString(),e.getMessage());
			logger.error("预售信息新增", e);
		}catch(Exception e){
			isRuning = false;
			setMessage(Boolean.FALSE.toString(),e.getMessage());
			logger.error("未知异常：", e);
		}
		return JSONMSG;
	}

	/**
	 * @作者：罗典
	 * @描述：预售信息发布
	 * @时间：2013-12-10
	 * */
	public String publishSale() throws YiPinStoreException {
		if(saleIds != null && saleIds.size() > 0){
			saleService.updateStatusByIds(Sale.STATUS_RELEASE, saleIds);
			setMessage(SUCCESS, SUCCESS);
		}
		return JSONMSG;
	}
	
	/**
	 * @作者：罗典
	 * @描述：预售信息下架
	 * @时间：2013-12-10
	 * */
	public String offShelf() throws YiPinStoreException {
		saleService.updateStatusByIds(Sale.STATUS_OFF_SHELF, saleIds);
		setMessage(SUCCESS, SUCCESS);
		return JSONMSG;
	}
	/**
	 * @作者：罗典
	 * @描述：加载商品列表
	 * @时间：2013-12-06
	 * */
	public String loadProductList() throws YiPinStoreException {
		if(sale != null && sale.getSaleId()!= null){
			sale = saleService.getSaleById(sale.getSaleId());
		}
		List<UserInfo> userInfoResult= (List<UserInfo>)userInfoService.listSellersByBrandId(sale.getBrandId());
		userInfos = new ArrayList<UserInfo>();
		userInfos.addAll(userInfoResult);
		for (int i = 0;i<userInfoResult.size();i++) {
			UserInfo userInfo = userInfoResult.get(i);
			List<Product> productList = productService.listPassProBySellerId(userInfo.getUser().getUserId(),sale.getBrandId());
			userInfos.get(i).setProductList(productList);
		}
		return "searchProducts";
	}

	public List<City> getCityList() {
		return this.cityList;
	}
	
	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public List<String> getSaleIds() {
		return saleIds;
	}

	public void setSaleIds(List<String> saleIds) {
		this.saleIds = saleIds;
	}

	public BasePagination<Sale> getSalePage() {
		return salePage;
	}

	public void setSalePage(BasePagination<Sale> salePage) {
		this.salePage = salePage;
	}

	public Sale getSaleInfo() {
		return saleInfo;
	}

	public void setSaleInfo(Sale saleInfo) {
		this.saleInfo = saleInfo;
	}

	public Boolean getIsPageSearch() {
		return isPageSearch;
	}

	public void setIsPageSearch(Boolean isPageSearch) {
		this.isPageSearch = isPageSearch;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	
}
