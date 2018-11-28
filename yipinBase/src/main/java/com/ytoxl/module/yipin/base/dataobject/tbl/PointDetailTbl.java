package com.ytoxl.module.yipin.base.dataobject.tbl;


import java.util.Date;

/**
 * 积分明细
 * 
 * @author zengzhiming
 * 
 */
public class PointDetailTbl {
	/**积分明细主键	 */
	protected Integer pointDetailId;
	/**积分主键	 */
	protected Integer pointId;
	/**积分来源:0=订单  1=退货  2=注册 3=吐槽 4=发现	 */
	protected Short pointSource; // 
	/**分数	 */
	protected Integer point;
	/**创建时间	 */
	protected Date createTime;
	/**积分来源id	 */
	protected Integer sourceId;
	
	
	

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getPointDetailId() {
		return pointDetailId;
	}

	public void setPointDetailId(Integer pointDetailId) {
		this.pointDetailId = pointDetailId;
	}

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}



	public Short getPointSource() {
		return pointSource;
	}

	public void setPointSource(Short pointSource) {
		this.pointSource = pointSource;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
