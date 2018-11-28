package com.ytoxl.yipin.web.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Point;
import com.ytoxl.module.yipin.base.service.PointService;
import com.ytoxl.yipin.web.action.BaseAction;

public class PointAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(PointAction.class);
	
	private Point point;
	
	private BasePagination<Point> pointPage;

	@Autowired
	public UserService userService;
	@Autowired
	public PointService pointService;

	/**
	 * 获取用户积分
	 * 
	 * @return
	 */
	public String searchPoints() {
		try {
			if (pointPage == null) {
				pointPage=new BasePagination<Point>();
			}
			point=pointService.getPointByUserId(pointPage);
		}catch (YiPinStoreException e) {
			logger.error("获取用户积分出现异常:",e.getMessage());
		}catch (YtoxlUserException e) {
			logger.error("获取用户积分出现异常:",e.getMessage());
		}

		return "success";
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	public BasePagination<Point> getPointPage() {
		return pointPage;
	}
	public void setPointPage(BasePagination<Point> pointPage) {
		this.pointPage = pointPage;
	}
	
}
