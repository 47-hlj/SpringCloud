package com.pd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdUserMapper;
import com.pd.pojo.PdUser;
import com.pd.pojo.PdUserExample;
import com.pd.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	// 
	@Autowired
	PdUserMapper pdUserMapper;

	
	public PdUser selectByUsername(String username) throws Exception {
		// where username=a
		PdUserExample example = new PdUserExample();
		PdUserExample.Criteria criteria = example.or();
		criteria.andUsernameEqualTo(username);
		List<PdUser> list = pdUserMapper.selectByExample(example);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}

	
	public int insert(PdUser pdUser) throws Exception {
		int row = pdUserMapper.insert(pdUser);
		return row;
	}

}
