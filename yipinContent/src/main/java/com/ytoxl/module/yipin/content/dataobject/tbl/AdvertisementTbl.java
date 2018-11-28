package com.ytoxl.module.yipin.content.dataobject.tbl;

import java.util.Date;

/** 广告明细表
 * @author zengzhiming
 *
 */
public class AdvertisementTbl {
	
	/**主键	 */
	private Integer advertisementId;
	/**	广告跳转的url */
	private String url;
	/**	广告排序 */
	private Integer rank;
	/**	广告图片 */
	private String imageUrl;
	/**	广告描述 */
	private String advertisementName;
	/**	广告打开的方式  1-在本页面打开；2-另开页面 */
	private Integer target;
	/**	广告位主键  多对一 */
	private Integer positionId;
	/**	广告中 商品 所在地 所属的来源id */
	private Integer advertisementPositionId;
	/**	创建者 */
	private Integer createUserId;
	/**	创建时间 */
	private Date createTime;
	/**	更新时间 */
	private Date updateTime;
	/** 状态 */
	private Integer status;
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Integer getAdvertisementPositionId() {
		return advertisementPositionId;
	}
	public void setAdvertisementPositionId(Integer advertisementPositionId) {
		this.advertisementPositionId = advertisementPositionId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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
	
	
	
}
