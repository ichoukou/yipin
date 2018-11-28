package com.ytoxl.module.yipin.base.dataobject.tbl;


import java.util.Date;

/**
 * 积分表
 * 
 * @author zengzhiming
 * 
 */
public class PointTbl {
	/**主键	 */
	protected Integer pointId;
	/**用户id	 */
	protected Integer userId;
	/**总积分	 */
	protected Integer total;
	/**创建时间	 */
	protected Date createTime;
	/**更新时间	 */
	protected Date updateTime;

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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
