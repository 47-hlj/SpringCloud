package com.pd.common.utils;

import javax.servlet.http.HttpSession;

import com.pd.pojo.PdUser;

public class Tools {
	public static Long getUserId(HttpSession session) {
		Long userId = 0L;
		try {
			PdUser pdUser = (PdUser) session.getAttribute("user");
			userId = pdUser.getId();
		} catch (Exception e) {
		}
		return userId;
	}
}
