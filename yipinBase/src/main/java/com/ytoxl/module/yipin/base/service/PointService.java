package com.ytoxl.module.yipin.base.service;

import java.math.BigDecimal;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Point;

public interface PointService {

	/**
	 * 获取用户积分信息
	 * 
	 * @return
	 */

	public Point getPointByUserId(BasePagination<Point> orderPage)
			throws YiPinStoreException,YtoxlUserException;

	/**
	 * 根据费用计算积分数，这里抽出来便于以后算法变化后只需要修改一处
	 * 
	 * @param money
	 * @return
	 * @throws UhomeStoreException
	 */
	public Integer computerPoint(BigDecimal money) throws YiPinStoreException;

	/**
	 * 添加积分明细
	 * 
	 * @param userId
	 *            用户ID
	 * @param pointSource
	 *            积分来源 0=订单 1=退货 2=注册 3=吐槽 4=发现
	 * @param point
	 *            积分
	 * @param type
	 *            增减积分 TYPE_MINUS = 0 扣减积分,TYPE_ADD = 1 增加积分
	 * @param sourceId
	 *            积分来源id
	 * @return true 增减积分成功，false增减积分失败
	 * @throws YiPinStoreException
	 */
	public boolean addPointByUserId(Integer userId, Short pointSource,
			Integer point, Short type, Integer sourceId)
			throws YiPinStoreException;

	/**
	 * 增减用户积分
	 * @param userId
	 * @param refundAccount
	 * @param type
	 */
	void updatePointByUserId(Integer userId, BigDecimal refundAccount, Short type,
			Short pointSource) throws YiPinStoreException;

}
