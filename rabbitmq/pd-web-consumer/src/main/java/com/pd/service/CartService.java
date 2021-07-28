package com.pd.service;

import java.util.List;

import com.pd.pojo.PdCartItem;
import com.pd.pojo.ItemVO;

public interface CartService {
	/**
	 * 
	 * @param userId
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public int batchDelete(Long userId,
			List<Long> itemIds)
			throws Exception;
	/**
	 * 
	 * @param pdCartItem
	 * @return
	 * @throws Exception
	 */
	public int updateNum(PdCartItem pdCartItem) 
			throws Exception;
	/**
	 * ﳵ
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ItemVO> selectByUserId(Long userId) 
			throws Exception;
	/**
	 * 
	 * @param pdCartItem
	 * @return
	 * @throws Exception
	 */
	public int insert(PdCartItem pdCartItem) 
			throws Exception;

}
