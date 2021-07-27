package com.pd.service;

import java.util.List;

import com.pd.pojo.PdOrder;
import com.pd.pojo.ItemVO;
import com.pd.pojo.OrderVO;
import com.github.pagehelper.PageInfo;

public interface OrderService {
	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public PdOrder findOrderByOrderid
	(String orderId) throws Exception;
	
	/**
	 * 
	 * @param orderId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int updateOrderStatus
	(String orderId,int status) throws Exception;

	
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<OrderVO> selectByUserIdAndStatus
	(Long userId,int status) 
			throws Exception;
	/**
	 * 
	 * @param addid
	 * @param userId
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public String saveOrder
	(PdOrder pdOrder)
			throws Exception;
	/**
	 * 
	 * @param userId
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public List<ItemVO> 
	selectCartItemByUseridAndItemIds
	(Long userId,List<Long> itemIds) 
			throws Exception;
//
	public PdOrder selectById(String orderId)
			throws Exception;
}
