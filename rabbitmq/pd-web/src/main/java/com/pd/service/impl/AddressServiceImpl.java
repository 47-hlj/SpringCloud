package com.pd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdShippingMapper;
import com.pd.pojo.PdShipping;
import com.pd.pojo.PdShippingExample;
import com.pd.service.AddressService;

//
@Service
public class AddressServiceImpl implements AddressService{

	//
	@Autowired
	PdShippingMapper pdShippingMapper;
	@Override
	public int insert(PdShipping pdShipping) throws Exception {
		//
		int row=pdShippingMapper.insert(pdShipping);
		PdShippingExample example=new 
				PdShippingExample();
		PdShippingExample.Criteria criteria=
				example.or();
		Long userId=pdShipping.getUserId();
		criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("add_id desc");
		List<PdShipping> list=pdShippingMapper
				.selectByExample(example);	
		if (list.size()>=1){
			Long addId=list.get(0).getAddId();
			setDefault(userId, addId);
		}
		
		return row;
	}
	@Override
	public List<PdShipping> selectByUserId(Long userId) throws Exception {
// from pd_shipping where user_id=14 and status=1
		PdShippingExample example=new 
				PdShippingExample();
		
		PdShippingExample.Criteria criteria=
				example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo((byte)1);
		
		List<PdShipping> list=pdShippingMapper
				.selectByExample(example);		
		
		return list;
	}
	@Override
	public int setDefault(Long userId, Long addId) throws Exception {
//
		// update pd_shipping set is_default=0
		//where user_=14;
		PdShippingExample example=
				new PdShippingExample();
		
		PdShippingExample.Criteria criteria=example.or();
		criteria.andUserIdEqualTo(userId);
		
		PdShipping pdShipping=new PdShipping();
		pdShipping.setIsDefault(false);
		//set receiverName=null,phone=null
		
		int row=pdShippingMapper
				.updateByExampleSelective(pdShipping, example);
		

		criteria.andAddIdEqualTo(addId);
		pdShipping.setIsDefault(true);
		pdShippingMapper
		.updateByExampleSelective(pdShipping, example);
		return row;
	}
	@Override
	public int delete(Long addId) throws Exception {
		int row = pdShippingMapper.deleteByPrimaryKey(addId);
		return row;
	}
	@Override
	public PdShipping getDefaultByUserId(Long userId) throws Exception {
PdShippingExample example=new PdShippingExample();
PdShippingExample.Criteria criteria=example.or();
criteria.andUserIdEqualTo(userId);
criteria.andIsDefaultEqualTo(true);

List<PdShipping> pdShippings=pdShippingMapper
.selectByExample(example);

	if (pdShippings!=null 
			&& pdShippings.size()>=1)
	{
		return pdShippings.get(0);
	}
		return null;
	}
}
