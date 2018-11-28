package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.yipin.base.common.utils.excel.ExcelField;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;

/**用户信息结合商家表
 * @author zengzhiming
 *
 */
public class UserInfoTbl {
	/**	主键 */
	protected Integer userInfoId;
	/**性别	 */
	protected Short gender;
	/**生日	 */
	protected Date birthday;
	/**好友集合	 */
	protected String friendIds;
	/**用户类型  0 管理员 1 商家用户 2 买家	 */
	protected Integer userType;
	protected Integer userId;
	@ExcelField(filedName="业务联系人", sort=4)
	protected String contactName;    //联系人
	protected String tel;            //电话 通用
	@ExcelField(filedName="联系电话", sort=5)
	protected String mobile;          //手机号码
	protected String qq; 
	protected String email;          //邮箱
	protected String fax;            //传真
	@ExcelField(filedName="商家名称" ,sort=2)
	protected String companyName;    // 公司名称 
	protected String companyType;    //公司类型
	protected String supplierType;    //供应商类型
	protected Date registerDate;      //成立日期
	protected BigDecimal registerCapital;   //注册金额
	protected BigDecimal paidUpCapital;     //实收资本
	protected String businessScope;         //经营范围
	protected String companyWebSite;        //公司网址
	protected String shiperAddress;          //发货地址
	protected Integer shiperRegionId;        //发货区域
	protected String receiverAddress;       //退货地址
	protected Integer receiverRegionId; 
	@ExcelField(filedName="法人代表",sort=3)// 退货地区
	protected String companyCorporation; // 公司法人
	protected String licenseImageUrl;    //营业执照图片地址
	protected String taxNo;               //税务登记号
	protected String companyCode;         //营业执照编号活其他编号
	protected String companyNo;           //企业编码
	@ExcelField(filedName="支付宝账号", sort=6)
	protected String alipayNo;            //支付宝账号
	protected String alipayName;          //注册支付宝姓名
	protected String bankName;             //开户银行
	protected String bankAccount;          //开户银行账户
	protected String companyAddress;       // 公司地址
	protected Integer companyRegionId;      //公司区域
	protected String remark; // 备注
	protected Date createTime;
	protected Date updateTime;
	protected User user;
	
	//新的地址插件
	private RegionModel regionModel;
	private String province;  
	private String area;//列表显示省份
//	private List<Brand> subscriptionBrand;
	// 用户所属商品信息集合
	private List<Product> productList;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}
	public Short getGender() {
		return gender;
	}
	public void setGender(Short gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getFriendIds() {
		return friendIds;
	}
	public void setFriendIds(String friendIds) {
		this.friendIds = friendIds;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public BigDecimal getRegisterCapital() {
		return registerCapital;
	}
	public void setRegisterCapital(BigDecimal registerCapital) {
		this.registerCapital = registerCapital;
	}
	public BigDecimal getPaidUpCapital() {
		return paidUpCapital;
	}
	public void setPaidUpCapital(BigDecimal paidUpCapital) {
		this.paidUpCapital = paidUpCapital;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getCompanyWebSite() {
		return companyWebSite;
	}
	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}
	public String getShiperAddress() {
		return shiperAddress;
	}
	public void setShiperAddress(String shiperAddress) {
		this.shiperAddress = shiperAddress;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getCompanyCorporation() {
		return companyCorporation;
	}
	public void setCompanyCorporation(String companyCorporation) {
		this.companyCorporation = companyCorporation;
	}
	public String getLicenseImageUrl() {
		return licenseImageUrl;
	}
	public void setLicenseImageUrl(String licenseImageUrl) {
		this.licenseImageUrl = licenseImageUrl;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getAlipayNo() {
		return alipayNo;
	}
	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}
	public String getAlipayName() {
		return alipayName;
	}
	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public Integer getShiperRegionId() {
		return shiperRegionId;
	}
	public void setShiperRegionId(Integer shiperRegionId) {
		this.shiperRegionId = shiperRegionId;
	}
	public Integer getReceiverRegionId() {
		return receiverRegionId;
	}
	public void setReceiverRegionId(Integer receiverRegionId) {
		this.receiverRegionId = receiverRegionId;
	}
	public Integer getCompanyRegionId() {
		return companyRegionId;
	}
	public void setCompanyRegionId(Integer companyRegionId) {
		this.companyRegionId = companyRegionId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public RegionModel getRegionModel() {
		return regionModel;
	}
	public void setRegionModel(RegionModel regionModel) {
		this.regionModel = regionModel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
