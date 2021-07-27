package com.pd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdCartItemMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.mapper.PdItemParamItemMapper;
import com.pd.mapper.PdOrderItemMapper;
import com.pd.mapper.PdOrderMapper;
import com.pd.mapper.PdShippingMapper;
import com.pd.pojo.PdCartItem;
import com.pd.pojo.PdCartItemExample;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemParamItem;
import com.pd.pojo.PdItemParamItemExample;
import com.pd.pojo.PdOrder;
import com.pd.pojo.PdOrderExample;
import com.pd.pojo.PdOrderItem;
import com.pd.pojo.PdOrderItemExample;
import com.pd.pojo.PdShipping;
import com.pd.pojo.ItemVO;
import com.pd.pojo.OrderVO;
import com.pd.pojo.paramData.PdItemParamData;
import com.pd.pojo.paramData.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.common.utils.JsonUtils;
import com.pd.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	PdOrderMapper pdOrderMapper;

	@Autowired
	PdCartItemMapper pdCartItemMapper;

	@Autowired
	PdItemMapper pdItemMapper;

	@Autowired
	PdItemParamItemMapper pdItemParamItemMapper;

	@Autowired
	PdShippingMapper pdShippingMapper;

	@Autowired
	PdOrderItemMapper pdOrderItemMapper;



	
	public String saveOrder(PdOrder pdOrder) throws Exception {
		String orderId = generateId();
		pdOrder.setOrderId(orderId);

		
		PdShipping pdShipping = pdShippingMapper.selectByPrimaryKey(pdOrder.getAddId());
		pdOrder.setShippingName(pdShipping.getReceiverName());
		pdOrder.setShippingCode(pdShipping.getReceiverAddress());
		pdOrder.setStatus(1);// 
		pdOrder.setPaymentType(1);
		pdOrder.setPostFee(10D);
		pdOrder.setCreateTime(new Date());

		double payment = 0;
		List<ItemVO> itemVOs = selectCartItemByUseridAndItemIds(pdOrder.getUserId(), pdOrder.getItemIdList());
		for (ItemVO itemVO : itemVOs) {
			PdOrderItem pdOrderItem = new PdOrderItem();
			String id = generateId();
			//String id="2";
			pdOrderItem.setId(id);
			pdOrderItem.setOrderId(orderId);
			pdOrderItem.setItemId("" + itemVO.getPdItem().getId());
			pdOrderItem.setTitle(itemVO.getPdItem().getTitle());
			pdOrderItem.setPrice(itemVO.getPdItem().getPrice());
			pdOrderItem.setNum(itemVO.getPdCartItem().getNum());

			payment = payment + itemVO.getPdCartItem().getNum() * itemVO.getPdItem().getPrice();
			pdOrderItemMapper.insert(pdOrderItem);
		}
		pdOrder.setPayment(payment);
		pdOrderMapper.insert(pdOrder);
		return orderId;
	}

	public synchronized String generateId() {
		Random random = new Random();
		int number = random.nextInt(9000000) + 1000000;
		return "" + System.currentTimeMillis() + number;
	}
	public List<ItemVO> selectCartItemByUseridAndItemIds(Long userId, List<Long> itemIds) throws Exception {
		PdCartItemExample cartItemExample = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andItemIdIn(itemIds);
		criteria.andStatusEqualTo(1);

		List<PdCartItem> cartItems = pdCartItemMapper.selectByExample(cartItemExample);
		List<ItemVO> itemVOs = new ArrayList<>();
		for (PdCartItem pdCartItem : cartItems) {
			Long itemId = pdCartItem.getItemId();
			PdItem pdItem = pdItemMapper.selectByPrimaryKey(itemId);
			PdItemParamItemExample paramExample = new PdItemParamItemExample();
			PdItemParamItemExample.Criteria paramCriteria = paramExample.or();
			paramCriteria.andItemIdEqualTo(itemId);
			List<PdItemParamItem> items = pdItemParamItemMapper.selectByExampleWithBLOBs(paramExample);
			List<Params> paramsList = new ArrayList<>();
			if (items != null && items.size() >= 1) {
				PdItemParamItem item = items.get(0);
				String paramData = item.getParamData();
				List<PdItemParamData> list = JsonUtils.jsonToList(paramData, PdItemParamData.class);
				paramsList = list.get(0).getParams();
			}
			ItemVO itemVO = new ItemVO();
			itemVO.setPdCartItem(pdCartItem);
			itemVO.setPdItem(pdItem);
			itemVO.setParamsList(paramsList);
			itemVOs.add(itemVO);
		}
		return itemVOs;
	}

	
	public PdOrder selectById(String orderId) throws Exception {
		PdOrder pdOrder = pdOrderMapper.selectByPrimaryKey(orderId);
		return pdOrder;
	}

	
	public List<OrderVO> selectByUserIdAndStatus
	(Long userId, int status) throws Exception {
		//where user_id=14 and status!=9 order by create_time desc
		PdOrderExample orderExample=new PdOrderExample();
		PdOrderExample.Criteria criteria=orderExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusNotEqualTo(9);//9閿熸枻鎷风ず閿熸枻鎷烽敓鏂ゆ嫹閿熺獤鎾呮嫹鍒犻敓鏂ゆ嫹
		if (status!=0)
		{
			criteria.andStatusEqualTo(status);
		}
		orderExample.setOrderByClause("create_time desc");
		List<PdOrder> orders=pdOrderMapper
				.selectByExample(orderExample);
		
		List<OrderVO> orderVOs=new ArrayList<>();
		for (PdOrder pdOrder:orders)
		{
			//from pd_order_item where order_id=30;
			PdOrderItemExample itemExample=new PdOrderItemExample();
			PdOrderItemExample.Criteria itemCriteria=itemExample.or();
			itemCriteria.andOrderIdEqualTo(pdOrder.getOrderId());
			List<PdOrderItem> orderItems=
					pdOrderItemMapper
					.selectByExample(itemExample);
			for (PdOrderItem pdOrderItem:orderItems)
			{
				
			//3.3.1 example,criteria
				PdItemParamItemExample example=
						new PdItemParamItemExample();
				PdItemParamItemExample.Criteria paramCriteria=
						example.or();
				long itemId=Long.parseLong(pdOrderItem
						.getItemId());
				paramCriteria.andItemIdEqualTo(itemId);
				
				List<PdItemParamItem> paramItemList=pdItemParamItemMapper
						.selectByExampleWithBLOBs(example);
				List<Params> paramsList=new ArrayList<>();
				if (paramItemList.size()>=1)
				{
			//3.3.3 鍙杙aram_data
					PdItemParamItem pdItemParamItem=
							paramItemList.get(0);
					String paramData=pdItemParamItem
							.getParamData();					
					List<PdItemParamData> paramDataList=JsonUtils
							.jsonToList(paramData, 
									PdItemParamData.class);
					paramsList=paramDataList.get(0).getParams();
				}
			pdOrderItem.setParamsList(paramsList);	
			}
			OrderVO orderVO=new OrderVO();
			orderVO.setPdOrder(pdOrder);
			orderVO.setPdOrderItems(orderItems);
			
			orderVOs.add(orderVO);
		}
		return orderVOs;
	}

	
	

	
	public PdOrder findOrderByOrderid(String orderId) throws Exception {
PdOrder pdOrder=pdOrderMapper
.selectByPrimaryKey(orderId);
		return pdOrder;
	}

	@Override
	public int updateOrderStatus(String orderId, int status) throws Exception {
//update pd_order set status=8 where orderId=100
		PdOrder pdOrder=new PdOrder();
		pdOrder.setStatus(status);
		
		PdOrderExample example=new PdOrderExample();
		PdOrderExample.Criteria criteria=example.or();
		criteria.andOrderIdEqualTo(orderId);
		
		int row=pdOrderMapper
				.updateByExampleSelective
				(pdOrder, example); 
		return row;
	}

}
