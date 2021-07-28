package com.pd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdCartItemMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.mapper.PdItemParamItemMapper;
import com.pd.pojo.PdCartItem;
import com.pd.pojo.PdCartItemExample;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemParamItem;
import com.pd.pojo.PdItemParamItemExample;
import com.pd.pojo.ItemVO;
import com.pd.pojo.paramData.Params;
import com.pd.pojo.paramData.PdItemParamData;
import com.pd.common.utils.JsonUtils;
import com.pd.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	PdCartItemMapper pdCartItemMapper;

	@Autowired
	PdItemMapper pdItemMapper;

	@Autowired
	PdItemParamItemMapper pdItemParamItemMapper;

	@Override
	public List<ItemVO> selectByUserId(Long userId) throws Exception {
		PdCartItemExample cartItemExample = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		List<PdCartItem> cartItems = pdCartItemMapper.selectByExample(cartItemExample);
		List<ItemVO> itemVOs = new ArrayList<>();
		for (PdCartItem pdCartItem : cartItems) {
			Long itemId = pdCartItem.getItemId();
			PdItem pdItem = pdItemMapper.selectByPrimaryKey(itemId);
			PdItemParamItemExample paramExample = new PdItemParamItemExample();
			PdItemParamItemExample.Criteria paramCriteria = paramExample.or();
			paramCriteria.andItemIdEqualTo(itemId);
			List<PdItemParamItem> paramItems = pdItemParamItemMapper.selectByExampleWithBLOBs(paramExample);
			List<Params> paramsList = new ArrayList<>();
			if (paramItems != null && paramItems.size() >= 1) {
				PdItemParamItem pdItemParamItem = paramItems.get(0);
				String param_data = pdItemParamItem.getParamData();
				List<com.pd.pojo.paramData.PdItemParamData> paramDataList = JsonUtils.jsonToList(param_data, com.pd.pojo.paramData.PdItemParamData.class);
				paramsList = paramDataList.get(0).getParams();
			}
			ItemVO itemVO = new ItemVO();
			itemVO.setPdCartItem(pdCartItem);
			itemVO.setPdItem(pdItem);
			itemVO.setParamsList(paramsList);
			itemVOs.add(itemVO);
		}
		return itemVOs;
	}

	@Override
	public int insert(PdCartItem pdCartItem) throws Exception {
		// where userid=14 and item_id=28 and status=1
		PdCartItemExample example = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(pdCartItem.getUserId());
		criteria.andItemIdEqualTo(pdCartItem.getItemId());
		criteria.andStatusEqualTo(pdCartItem.getStatus());
		List<PdCartItem> list = pdCartItemMapper.selectByExample(example);
		int row = 0;
		if (list != null && list.size() >= 1) {
			PdCartItem dbItem = list.get(0);
			pdCartItem.setNum(dbItem.getNum() + pdCartItem.getNum());
			row = pdCartItemMapper.updateByExampleSelective(pdCartItem, example);
		} else {
			row = pdCartItemMapper.insert(pdCartItem);
		}
		return row;
	}

	@Override
	public int updateNum(PdCartItem pdCartItem) throws Exception {
		// update pd_cart_item set num=5
		// where userId=14 and item_id=28 and status=1
		PdCartItemExample example = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(pdCartItem.getUserId());
		criteria.andItemIdEqualTo(pdCartItem.getItemId());
		criteria.andStatusEqualTo(pdCartItem.getStatus());
		int row = pdCartItemMapper.updateByExampleSelective(pdCartItem, example);

		return row;
	}

	@Override
	public int batchDelete(Long userId, List<Long> itemIds) throws Exception {
		PdCartItemExample example = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andItemIdIn(itemIds);

		PdCartItem pdCartItem = new PdCartItem();
		pdCartItem.setStatus(2);

		int row = pdCartItemMapper.updateByExampleSelective(pdCartItem, example);

		return row;
	}

}
