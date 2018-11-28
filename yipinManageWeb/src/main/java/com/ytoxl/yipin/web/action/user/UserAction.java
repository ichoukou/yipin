package com.ytoxl.yipin.web.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.icu.text.SimpleDateFormat;
import com.opensymphony.xwork2.ActionContext;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.core.common.utils.DateUtil;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.excel.ExportExcel;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.City;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.CityService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * 账号管理
 * 
 * @author zengzhiming
 * 
 */
public class UserAction extends BaseAction {
	private static final long serialVersionUID = -1683456422079577697L;
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private CityService cityService;

	private BasePagination<UserInfo> buyerPage;
	private static final String SELLER_SINGLE = "sellerDetailSingle";
	private static final String SELLER_SINGLE_PASSWORD = "sellerDetailSingleWord";
	private static final String SEARCH_SELLERS = "searchSellers";
	private static final String REDIRECT_SELLERS = "redirectSellers";
	private static final String SELLERS_WILDCARD = "sellerWildcard";
	private static final String EDITPASSWORD = "editpassword";
	private static final String SELLER = "seller";
	private static final String STATUS_DENIY = "1";// 账号被禁用被禁用 、用户的账号重复
	private static final String STATUS_ACCESS = "2";// 账号良好
	private static final String SEARCH_BUYERS = "searchBuyers";
	/** 商家实体 */
	private UserInfo userInfo;
	private User user;
	private String nextAction;
	private BasePagination<UserInfo> sellerPage;

	private UserInfo buyer;
	private InputStream excelStream;
	private String fileName;
	private String keyName;
	private String email;
	private Integer userId;
	private Map<String, List<Brand>> brandMap;
	private Map<Object, Object> selectBrandMap;

	/**
	 * 分页查询商家信息
	 * 
	 * @return
	 */
	public String sellerManage() {
		if (sellerPage == null) {
			sellerPage = new BasePagination<UserInfo>();
		}
		try {
			userInfoService.searchSellers(sellerPage);
		} catch (YiPinStoreException e) {
			logger.error("查询商家信息出现异常", e.getMessage());
		}
		return SEARCH_SELLERS;
	}

	/**
	 * @return
	 */
	public String savePassword() {
		try {
			userInfoService.savePassword(this.userInfo);
			setMessage(Boolean.TRUE.toString(), "修改密码成功");
		} catch (YiPinStoreException e) {
			e.printStackTrace();
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		}
		return JSONMSG;
	}

	/**
	 * 验证用户密码一致性
	 * 
	 * @return
	 */
	public String validPassword() {
		try {
			String userName = userService.getCurrentUser().getUsername();
			String password = userInfo.getUser().getPassword();
			boolean flag = userService.checkPassword(userName, password);
			if (flag) {
				setMessage(Boolean.TRUE.toString());
			} else {
				setMessage(Boolean.FALSE.toString());
			}
		} catch (YtoxlUserException e) {
			logger.error(e.getMessage());
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		}
		return JSONMSG;
	}

	/**
	 * 查看商家
	 * 
	 * @return
	 */
	public String sellerDetailSeller() {
		try {
			try {
				this.userInfo = userInfoService.getSellerByUserId(userService
						.getCurrentUser().getUserId(), false);
			} catch (YtoxlUserException e) {
				logger.error(
						"UserAction sellerDetailSeller YtoxlUserException :",
						e.getMessage());
			}
		} catch (YiPinStoreException e) {
			logger.error("UserAction sellerDetailSeller YiPinStoreException :",
					e.getMessage());
		}
		return SELLER_SINGLE;
	}

	/**
	 * 密码修改 更改用户状态
	 * 
	 * @return
	 */
	public String editPassword() {
		return EDITPASSWORD;
	}

	/**
	 * 更改用户状态
	 * 
	 * @return
	 */
	public String updateUserStatus() {
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(this.getUser().getUserId());
		try {
			userInfoService.updateUserStauts(userIds, this.getUser()
					.getStatus());
		} catch (YiPinStoreException e) {
			logger.error("更改用户状态出现异常", e.getMessage());
		}
		this.nextAction = "user-sellerManage.htm";
		return REDIRECT_SELLERS;
	}

	public String getPasswordSell() {
		return SELLER_SINGLE_PASSWORD;
	}

	/**
	 * 重置用户密码
	 * 
	 * @return
	 */
	public String resetPassword() {
		try {
			userInfoService.resetPassword(this.getUser().getUserId());
			setMessage(Boolean.TRUE.toString(), "重置密码成功");
		} catch (YiPinStoreException e) {
			logger.error("更新密码失败", e.getMessage());
			setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
		}
		return JSONMSG;
	}

	/**
	 * 商家基本信息界面
	 * 
	 * @return
	 */
	public String seller() {
		try {
			if (userInfo != null && userInfo.getUserInfoId() != null
					&& userInfo.getUser().getUserId() != null) {
				this.userInfo = userInfoService.getSellerByUserId(userInfo
						.getUser().getUserId(), true);
				List<Brand> brands = brandService.listBrandsBySellerId(userInfo
						.getUser().getUserId());
				brandMap = new HashMap<String, List<Brand>>();
				selectBrandMap= new HashMap<Object, Object>();
				for (Brand brand : brands) {
					Integer brandId=brand.getBrandId();
					String name=brand.getName();
					selectBrandMap.put(brandId, name);
					brand.setUserInfo(userInfo);
					String firstLetter = brand.getBrandPinYin();
					if (!brandMap.containsKey(firstLetter)) {
						List<Brand> list = brandService
								.listBrandsByBrandPinYin(brand);
						brandMap.put(firstLetter, list);
					}
				}
			}
		} catch (YiPinStoreException e) {
			logger.error("查询商家基本信息失败", e.getMessage());
		}
		return SELLER;
	}

	/**
	 * 查看商家
	 * 
	 * @return
	 */
	public String sellerDetail() {
		try {
			this.userInfo = userInfoService.getSellerByUserId(userInfo.getUser()
					.getUserId(), false);
		} catch (YiPinStoreException e) {
			logger.error("查看商家出现异常", e.getMessage());
		}
		return SELLERS_WILDCARD;
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @return
	 */
	public String searchBuyers() {
		if (buyerPage == null) {
			buyerPage = new BasePagination<UserInfo>();
			Map<String, String> params = new HashMap<String, String>();
			// 默认交易日期近一周
			Date curDate = new Date();
			String startTime = DateUtil.toDay(DateUtil.add(curDate,Calendar.DATE, -6));
			String endTime = DateUtil.toDay(curDate);
			params.put("startTime", startTime);
			params.put("endTime", endTime);
			buyerPage.setParams(params);
			buyerPage.setSort("u.createTime");
			buyerPage.setDir("desc");
		}
		try {
			userInfoService.searchBuyers(buyerPage);
		} catch (YiPinStoreException e) {
			logger.error("查询买家信息：", e.getMessage());
		}
		return SEARCH_BUYERS;
	}

	/**
	 * 导出商家信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String listExportSellers() throws IOException {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("userType", 1);
			map.put("name", keyName);
			List<UserInfo> sellers = userInfoService.listExportSellers(map);
			ExportExcel<UserInfo> ee = new ExportExcel<UserInfo>(sellers);
			ActionContext ac = ActionContext.getContext();
			ServletContext sc = (ServletContext) ac
					.get(ServletActionContext.SERVLET_CONTEXT);
			String x = sc.getRealPath("/") + "seller.xls";
			ee.setMerge(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.setFileName(sdf.format(new Date()).replace("-", "")
					.replace(" ", "").replace(":", ""));
			ee.export(x);
			File file = new File(x);
			this.setExcelStream(new FileInputStream(file));
		} catch (YiPinStoreException e) {
			logger.error("导出商家出现异常", e.getMessage());
		}
		return "excel";
	}

	/**
	 * 保存商家
	 * 
	 * @return
	 */
	public String saveSeller() {
		try {
			if (userInfo != null && userInfo.getUserInfoId() != null) {
				userInfoService.updateSeller(this.userInfo);
			} else {
				//添加类型为商家
				this.userInfo.setUserType(1);
				userInfoService.addSeller(this.userInfo);
			}
		} catch (YiPinStoreException e) {
			logger.error("保存商家出现异常", e.getMessage());
		}
		this.setNextAction("user-sellerManage.htm");
		return REDIRECT_SELLERS;
	}
	/**
	 * 获取城市列表
	 * @return
	 */
	public String getCity(){
		try {
			List<City> city=cityService.searchAllCity();
			JSONArray json = JSONArray.fromObject(city);
			setMessage(json.toString());
		} catch (YiPinStoreException e) {
			logger.error("获取城市出现异常", e.getMessage());
		}
		return JSONMSG;
	}
	/**
	 * 验证添加商家登录名
	 * 
	 * @return
	 */
	public String validLoginName() {
		if (!userService.repeatUsername(this.getUserInfo().getUser()
				.getUsername())) {
			setMessage(STATUS_DENIY);// 用户账号重复
		} else {
			setMessage(STATUS_ACCESS);
		}
		return JSONMSG;
	}

	/**
	 * 增加的时候判断邮箱是否重复
	 * 
	 * @return
	 */
	public String validateEmailIsRepate() {
		boolean flag = userInfoService.validateEmailIsRepate(email, userId);
		setMessage(Boolean.toString(flag), null, null);
		return JSONMSG;
	}
	public Short[] getSupplierTypes(){
		return UserInfo.SUPPLIER_TYPES;
	}
	public BasePagination<UserInfo> getBuyerPage() {
		return buyerPage;
	}

	public void setBuyerPage(BasePagination<UserInfo> buyerPage) {
		this.buyerPage = buyerPage;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public BasePagination<UserInfo> getSellerPage() {
		return sellerPage;
	}

	public void setSellerPage(BasePagination<UserInfo> sellerPage) {
		this.sellerPage = sellerPage;
	}

	public UserInfo getBuyer() {
		return buyer;
	}

	public void setBuyer(UserInfo buyer) {
		this.buyer = buyer;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public Map<Object, Object> getSelectBrandMap() {
		return selectBrandMap;
	}

	public void setSelectBrandMap(Map<Object, Object> selectBrandMap) {
		this.selectBrandMap = selectBrandMap;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public static String getSellerSingle() {
		return SELLER_SINGLE;
	}

	public static String getSellerSinglePassword() {
		return SELLER_SINGLE_PASSWORD;
	}

	public static String getSearchSellers() {
		return SEARCH_SELLERS;
	}

	public static String getRedirectSellers() {
		return REDIRECT_SELLERS;
	}

	public static String getSellersWildcard() {
		return SELLERS_WILDCARD;
	}

	public static String getSeller() {
		return SELLER;
	}

	public static String getStatusDeniy() {
		return STATUS_DENIY;
	}

	public static String getStatusAccess() {
		return STATUS_ACCESS;
	}

	public static String getSearchBuyers() {
		return SEARCH_BUYERS;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
}
