package com.pd.service;

import com.pd.pojo.PdUser;

public interface UserService {
	/**
	 * 
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public PdUser selectByUsername(String username)
			throws Exception;
	
	/**
	 * 
	 * @param pdUser
	 * @return
	 * @throws Exception
	 */
	public int insert (PdUser pdUser) 
			throws Exception;
}
