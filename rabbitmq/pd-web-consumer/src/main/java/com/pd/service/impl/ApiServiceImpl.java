package com.pd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdItemDescMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemDesc;
import com.pd.pojo.PdItemExample;
import com.pd.pojo.DetailVO;
import com.pd.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	PdItemMapper pdItemMapper;

	@Autowired
	PdItemDescMapper pdItemDescMapper;

	@Override
	public List<PdItem> getIndexItem() throws Exception {
		// select * from pd_item where item_id in(10000028,1000029)
		ArrayList<Long> itemIds = new ArrayList<>();
		itemIds.add(830972L);
		itemIds.add(832739L);
		itemIds.add(844022L);
		itemIds.add(847276L);

		PdItemExample example = new PdItemExample();
		PdItemExample.Criteria criteria = example.or();
		criteria.andIdIn(itemIds);

		List<PdItem> list = pdItemMapper.selectByExample(example);

		return list;
	}

	@Override
	public DetailVO getItemDetail(Long itemId) throws Exception {
		DetailVO detailVO = new DetailVO();

		PdItem pdItem = pdItemMapper
				.selectByPrimaryKey(itemId);
		PdItemDesc pdItemDesc = pdItemDescMapper
				.selectByPrimaryKey(itemId);

		//detailVO.setPdItem(pdItem);
		//detailVO.setPdItemDesc(pdItemDesc);
		detailVO.setId(pdItem.getId());
		detailVO.setTitle(pdItem.getTitle());
		detailVO.setSellPoint(pdItem.getSellPoint());
		detailVO.setImage(pdItem.getImage());
		detailVO.setDesc(pdItemDesc.getItemDesc());

		return detailVO;
	}

}
