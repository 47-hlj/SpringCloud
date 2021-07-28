package com.pd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.pojo.PdCartItem;
import com.pd.pojo.PdResult;
import com.pd.common.utils.Tools;
import com.pd.pojo.ItemVO;
import com.pd.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@RequestMapping("/batchDelete.html")
	@ResponseBody
	//www.ajstore.com/cart/batchDelete.html
	//?itemId=10000028,10000030
	public PdResult batchDelete(HttpSession session,HttpServletResponse response,String itemIds) throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult=new PdResult();
		//itemId=28,30
		String[] array=itemIds.split("\\,");
		List<Long> list=new ArrayList<>();
		for (String string:array)
		{
			list.add(Long.parseLong(string));
		}
		int row=cartService.batchDelete(Tools.getUserId(session), list);
		if (row>=1)
		{
			pdResult.setStatus(200);
		}else {
			pdResult.setStatus(500);
		}
		return pdResult;
	}
	
	@RequestMapping("/changeNum.html")
	@ResponseBody
	public PdResult changeNum(HttpSession session,HttpServletResponse response,Long itemId,int num)
			throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult=new PdResult();
		PdCartItem pdCartItem=new PdCartItem();
		pdCartItem.setUserId(Tools.getUserId(session));
		pdCartItem.setItemId(itemId);
		pdCartItem.setNum(num);
		pdCartItem.setStatus(1);
		pdCartItem.setUpdated(new Date());
		int row=cartService.updateNum(pdCartItem);
		if (row>=1)
		{
			pdResult.setStatus(200);
		}else{
			pdResult.setStatus(500);
		}
		return pdResult;
	}

	@RequestMapping("/toCart.html")
	public ModelAndView toCart(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/cart.jsp");
		List<ItemVO> itemVOs = cartService.selectByUserId(Tools.getUserId(session));
		modelAndView.addObject("itemVOs", itemVOs);
		return modelAndView;
	}

	@RequestMapping("/insert.html")
	@ResponseBody
	public PdResult insert(HttpSession session,HttpServletResponse response,Long itemId, int num) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult = new PdResult();
		if (Tools.getUserId(session)==0L){
			pdResult.setStatus(300);//用户没有登录
			return pdResult;
		}
		
		
		PdCartItem pdCartItem = new PdCartItem();
		pdCartItem.setStatus(1);
		pdCartItem.setUserId(Tools.getUserId(session));
		pdCartItem.setItemId(itemId);
		pdCartItem.setNum(num);
		pdCartItem.setCreated(new Date());
		int row = cartService.insert(pdCartItem);
		if (row >= 1) {
			pdResult.setStatus(200);
		} else {
			pdResult.setStatus(500);
		}
		return pdResult;
	}
}
