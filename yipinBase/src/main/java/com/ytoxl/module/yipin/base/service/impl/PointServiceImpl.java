package com.ytoxl.module.yipin.base.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.DateUtil;
import com.ytoxl.module.yipin.base.dataobject.Point;
import com.ytoxl.module.yipin.base.dataobject.PointDetail;
import com.ytoxl.module.yipin.base.mapper.PointDetailMapper;
import com.ytoxl.module.yipin.base.mapper.PointMapper;
import com.ytoxl.module.yipin.base.service.PointService;
@Service
public class PointServiceImpl implements PointService {
	@Value("${limit_user_suggest}")
	private Integer defaultLimit;
	@Autowired
	public PointMapper<Point> pointMapper;
	@Autowired
	public PointDetailMapper<PointDetail> pointDetailMapper;
	
	@Autowired
	public UserService userService;

	/**
	 * 获取用户积分信息
	 * 
	 * @return
	 */
	@Override
	public Point getPointByUserId(BasePagination<Point> pointPage)
			throws YiPinStoreException, YtoxlUserException {
		pointPage.setLimit(defaultLimit);
		Integer userId = userService.getCurrentUser().getUserId();
		Date queryTime = DateUtil.add(new Date(), Calendar.MONTH, -1);
		// 获取用户积分信息
		Map<String, Object> searchParams = pointPage.getSearchParamsMap();
		searchParams.put("userId", userId);
		searchParams.put("queryTime", queryTime);
		Point point = pointMapper.getPointByUserId(userId);
		if(point!=null){
			searchParams.put("pointId", point.getPointId());
			List<PointDetail> pointDetails = pointDetailMapper
					.listPointDetailsByPointId(searchParams);
			point.setPointDetails(pointDetails);
			Integer total = pointDetailMapper
					.countPointDetailsByPointId(searchParams);
			pointPage.setTotal(total);
		}
		return point;
	}

	/**
	 * 根据费用计算积分数
	 * 
	 * @param money
	 * @return
	 * @throws UhomeStoreException
	 */
	@Override
	public Integer computerPoint(BigDecimal money) throws YiPinStoreException {
		return getPoint(money);
	}

	public static Integer computerPoint4Front(BigDecimal money) {
		return getPoint(money);
	}

	/**
	 * 计算积分
	 * 
	 * @param money
	 * @return
	 */
	private static Integer getPoint(BigDecimal money) {
		return money.setScale(0, RoundingMode.DOWN).intValue();
	}

	
	
	
	@Override
	public boolean addPointByUserId(Integer userId, Short pointSource,
			Integer pointMon,Short type,Integer sourceId) throws YiPinStoreException{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		PointDetail pointDetail = new PointDetail();
		Point point = new Point();
		point.setUserId(userId);
		point.setTotal(pointMon);
		pointDetail.setPointSource(pointSource);
		pointDetail.setPoint(pointMon);
		pointDetail.setSourceId(sourceId);
		Point source=pointMapper.getPointByUserId(userId);
		if(source==null){
			//添加
			pointMapper.add(point);
			pointDetail.setPointId(point.getPointId());
			
		}else{
			//更新积分
			int totalPoint=0;
			if(type==0){
				totalPoint=source.getTotal()-pointMon;
			}else{
				totalPoint=source.getTotal()+pointMon;
			}
			
			pointDetail.setPointId(source.getPointId());
			pointMapper.updatePointByUserId(userId, totalPoint, type);
		}
		if(type==0){
			pointDetail.setPoint(0-pointMon);
		}
		else{
			pointDetail.setPoint(pointMon);
		}
		pointDetailMapper.add(pointDetail);
		return true;
	}
	
	/**
	 * 增减用户积分
	 * @param userId
	 * @param money
	 * @param type
	 */
	@Override
	public void updatePointByUserId(Integer userId, BigDecimal money, Short type, Short pointSource)
			throws YiPinStoreException {
		Integer num = computerPoint(money);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		Point point = pointMapper.getPointByUserId(userId);
		if(point == null){
			point = new Point();
			point.setUserId(userId);
			point.setTotal(num);
			pointMapper.add(point);
		} else {
			int totalPoint=0;
			if(type==0){
				totalPoint=point.getTotal()-num;
			}else{
				totalPoint=point.getTotal()+num;
			}
			pointMapper.updatePointByUserId(userId, totalPoint, type);
		}
		
		//积分明细
		Integer pointId = point.getPointId();
		PointDetail pointDetail = new PointDetail();
		pointDetail.setPointId(pointId);
		pointDetail.setPointSource(pointSource);
		if(type==0){
			pointDetail.setPoint(0-num);
		}
		else{
			pointDetail.setPoint(num);
		}
		
		pointDetailMapper.add(pointDetail);
	}



}
