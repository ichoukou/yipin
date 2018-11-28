package com.ytoxl.yipin.web.action.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.OrderCrm;
import com.ytoxl.module.yipin.order.service.OrderCrmService;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.yipin.web.action.BaseAction;

public class OrderCrmAction extends BaseAction {
	
	private static final long serialVersionUID = -31542334459833291L;
	private static Logger logger = LoggerFactory.getLogger(OrderCrmAction.class);

	@Autowired
	private OrderCrmService OrderCrmService;
	@Autowired
	private UserService userService;
	
	private OrderCrm orderCrm;
	private Integer orderId;
	private List<OrderCrm> orderCrmList;
	private CustomUserDetails customUser;
	
	
	/**根据订单Id查询客服操作记录*/
	public String listOrderCrmByOrderId(){
		try {
			orderCrmList = OrderCrmService.listOrderCrmByOrderId(orderId);
		} catch (YiPinStoreException e) {
			logger.error("listOrderCrmByOrderId YiPinStoreException", e);
		}
		return "listOrderCrm";
	}
	
	/**添加客服操作记录*/
	public String addOrderCrm(){
		try {
			customUser = userService.getCurrentUser();
			orderCrm.setUserId(customUser.getUserId());
			orderCrm.setUserName(customUser.getUsername());
			
			OrderCrmService.addOrderCrm(orderCrm);
			setMessage(Boolean.TRUE.toString(),customUser.getUsername());
		} catch (YiPinStoreException e) {
			logger.error("addOrderCrm YiPinStoreException", e);
		} catch (YtoxlUserException e) {
			logger.error("addOrderCrm YtoxlUserException", e);
		}
		return JSONMSG;
	}

	public OrderCrm getOrderCrm() {
		return orderCrm;
	}

	public void setOrderCrm(OrderCrm orderCrm) {
		this.orderCrm = orderCrm;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<OrderCrm> getOrderCrmList() {
		return orderCrmList;
	}

	public void setOrderCrmList(List<OrderCrm> orderCrmList) {
		this.orderCrmList = orderCrmList;
	}

	public CustomUserDetails getCurrentUser() {
		CustomUserDetails detail = null;
		try {
			detail = userService.getCurrentUser();
		} catch (YtoxlUserException e) {
			logger.error(e.getMessage());
		}
		return detail;
	}

	public CustomUserDetails getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUserDetails customUser) {
		this.customUser = customUser;
	}
	

}
