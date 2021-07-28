package com.pd.service;

import java.util.List;
import com.pd.pojo.*;

public interface ApiService {
	/**
	 * 
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public DetailVO getItemDetail(Long itemId) 
			throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PdItem> getIndexItem() 
			throws Exception;
}
