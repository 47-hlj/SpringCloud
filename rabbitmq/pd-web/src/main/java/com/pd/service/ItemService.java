package com.pd.service;

import java.util.List;


import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemDesc;
import com.pd.pojo.PdItemParam;
import com.pd.pojo.PdItemParamItem;

public interface ItemService {
	/**
	 * 
	 * @param color
	 * @param model
	 * @param itemParamId
	 * @return
	 * @throws Exception
	 */
	public Long selectItemByParams(String color
			,String model,Long itemParamId)
			throws Exception;
	/**	
	 * @param itemParamId
	 * @return
	 * @throws Exception
	 */
	public PdItemParam selectTypeParamByTypeId
	(Long itemParamId) throws Exception;
	
	/**
	 * 
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public PdItemDesc selectDescById(Long itemId) 
			throws Exception;
	/**
	 * 
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public PdItem selectItemById(Long itemId) 
			throws Exception;
	/**
	 * 
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public PdItemParamItem 
	getParamDataById(Long itemId) throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
public List<PdItem> selectAll() throws Exception;
}
