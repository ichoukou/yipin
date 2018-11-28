package com.ytoxl.module.yipin.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.mapper.OrderAddressMapper;
import com.ytoxl.module.yipin.order.mapper.OrderHeadMapper;
import com.ytoxl.module.yipin.order.service.OrderService4Timer;

@Service
public class OrderService4TimerImpl implements OrderService4Timer {
	
	private static Logger logger = LoggerFactory.getLogger(OrderService4TimerImpl.class);

	@Autowired
	private OrderHeadMapper<OrderHead> orderHeadMapper;
	@Autowired
	private OrderAddressMapper<OrderAddress> orderAddressMapper;
//	@Autowired
//	private OrderItemMapper<OrderItem> orderItemMapper;
//	@Autowired
//	private ProductService productService;
	
	@Value("${defalut_limit}")
	private Integer limit;
	@Value("${grap_limit}")
	private Integer grapLimit;
	@Value("${order_exptime}")
	private Integer orderExptime;
	@Value("${grap_order_exptime}")
	private Integer grapOrderExptime;
	
	/**
	 * 更改订单状态为取消
	 */
	@Override
	public Integer updateStatusToCanceled() throws YiPinStoreException {
		logger.error("开始订单状态自动取消");
		//查询24小时内未支付的最新的100张订单
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", OrderHead.STATUS_CANCEL);
		map.put("limit", limit);
		map.put("expTime", orderExptime);
		List<Integer> orderIds = orderHeadMapper.listNotPayOrders(map);
		for(Integer id:orderIds){
			map.put("orderId", id);
			orderHeadMapper.updateOrderStatus(map);
//			List<OrderItem> listItem = orderItemMapper.listOrderItemCancelNum(id);
//			for(OrderItem item : listItem){
//				productService.updateInventory4Revert(item.getProductSkuId(), item.getNum());
//			}
		}
		logger.error("结束订单状态自动取消");
		return orderIds.size();
	}

	/**
	 * 更改订单状态为已完成
	 */
	@Override
	public Integer updateStatusToFinished() throws YiPinStoreException {
		logger.error("开始订单状态自动更新为完成");
		//查询包裹处于“未收货”状态，订单处于“待发货”或“已发货”状态，且包裹发货时间超过15天的包裹信息
		//(按照订单id倒序排列，取前100条记录)
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", OrderHead.STATUS_FINISHED);
		List<OrderAddress> packageList = orderAddressMapper.listOrderPackagesBySendTime(limit);
		for(OrderAddress oaddr:packageList){
			//首先更新包裹收货状态为“已收货”
			orderAddressMapper
					.updateOrderPackageStatusByOrderAdderssId(
							oaddr.getOrderAddressId(),
							OrderAddress.STATUS_RECEIVED);
			//检查该包裹所属订单下是否还有“未收货”的包裹
			Integer receiveNum = orderAddressMapper.countNotReceivePackageNumByOrderId(oaddr.getOrderId());
			if(receiveNum == 0){
				map.put("orderId", oaddr.getOrderId());	
				orderHeadMapper.updateOrderStatus(map);
			}
		}
		logger.error("结束订单状态自动更新为完成");
		return packageList.size();
	}

	/**
	 * 更改促销订单状态为取消
	 */
	@Override
	public Integer updateGrapStatusToCancel() throws YiPinStoreException {
		logger.error("开始促销订单状态自动取消");
		//查询40分钟内未支付的最新的100张促销订单
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", OrderHead.STATUS_CANCEL);
		map.put("limit", grapLimit);
		map.put("grapOrderExptime", grapOrderExptime);
		List<Integer> orderIds = orderHeadMapper.listNotPayGrapOrders(map);
		for(Integer id:orderIds){
			map.put("orderId", id);
			orderHeadMapper.updateOrderStatus(map);
//			List<OrderItem> listItem = orderItemMapper.listOrderItemCancelNum(id);
//			for(OrderItem item : listItem){
//				productService.updateInventory4Revert(item.getProductSkuId(), item.getNum());
//			}
		}
		logger.error("结束促销订单状态自动取消");
		return orderIds.size();
	}

}
