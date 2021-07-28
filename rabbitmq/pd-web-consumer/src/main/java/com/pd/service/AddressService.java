package com.pd.service;

import java.util.List;

import com.pd.pojo.PdShipping;

public interface AddressService {
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public PdShipping getDefaultByUserId(Long userId)
			throws Exception;
	/**
	 * 
	 * @param userId
	 * @param addId
	 * @return
	 * @throws Exception
	 */
	public int setDefault(Long userId,Long addId)
			throws Exception;
	/**
	 * 
	 * @param addId
	 * @return
	 * @throws Exception
	 */
	public int delete(Long addId) throws Exception;
	/**
	 *ַ
	 * @param pdShipping 
	 * @return int 
	 * @throws Exception
	 */
	public int insert(PdShipping pdShipping) 
			throws Exception;
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<PdShipping> selectByUserId(Long userId)
			throws Exception;

}
