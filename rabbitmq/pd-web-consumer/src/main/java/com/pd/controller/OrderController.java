package com.pd.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.common.utils.Tools;
import com.pd.mapper.PdOrderMapper;
import com.pd.pojo.PdOrder;
import com.pd.pojo.PdResult;
import com.pd.pojo.ItemVO;
import com.pd.pojo.OrderVO;
import com.pd.pojo.PdItem;
import com.github.pagehelper.PageInfo;
import com.pd.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	String pageSize = "20";

	@RequestMapping("/toPayment.html")
	public String toPayment(Model model, String orderId) throws Exception {
		//PdOrder pdOrder = orderService.selectById(orderId);
		PdOrder pdOrder=new PdOrder();
		pdOrder.setOrderId(orderId);
		pdOrder.setPayment(0.01D);
		model.addAttribute("pdOrder", pdOrder);
		return "/payment.jsp";
	}

	@RequestMapping("/cancelOrder.html")
	public ModelAndView cancelOrder(HttpSession session,String orderId, @RequestParam(defaultValue = "all") String status,
			@RequestParam(defaultValue = "1") int currentPage) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		PdOrder pdOrder = orderService.findOrderByOrderid(orderId);
		if (pdOrder != null && pdOrder.getUserId() == Tools.getUserId(session)) {
			orderService.updateOrderStatus(orderId, 8);
		}
		modelAndView.setViewName("redirect:/order/toMyOrder.html?status=" + status + "&currentPage=" + currentPage);
		return modelAndView;
	}

	@RequestMapping("/toMyOrder.html")
	public ModelAndView toMyOrder(HttpSession session,@RequestParam(defaultValue = "all") String status,
			@RequestParam(defaultValue = "1") int currentPage) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("status", status);

		int istatus = 0;
		switch (status) {
		case "waitPay":
			istatus = 1;
			break;
		case "waitReceive":
			istatus = 5;
			break;
		case "waitAssess":
			istatus = 6;
			break;
		case "all":
			istatus = 0;
			break;
		}
		
		List<OrderVO> orderVOs = orderService.selectByUserIdAndStatus(Tools.getUserId(session), istatus);
		modelAndView.addObject("orderVOs", orderVOs);

		modelAndView.setViewName("/myOrder.jsp");
		return modelAndView;
	}


	@RequestMapping("/submitOrder.html")
	// http://www.ajstore.com/order/submitOrder.html
	// ?addId=1&itemId=28&itemId=30
	@ResponseBody
	public PdResult submitOrder(HttpSession session,Long addId, Long[] itemId) throws Exception {
		List<Long> itemIdList = Arrays.asList(itemId);
		PdOrder pdOrder = new PdOrder();
		pdOrder.setAddId(addId);
		pdOrder.setUserId(Tools.getUserId(session));
		pdOrder.setItemIdList(itemIdList);
		String orderId=orderService.saveOrder(pdOrder);

		PdResult pdResult=new PdResult();
		if (orderId!=null)
		{
			pdResult.setStatus(200);
			pdResult.setData(orderId);
			
		}else
		{
			pdResult.setStatus(500);
		}
		return pdResult;
	}

	@RequestMapping("/confirmOrder.html")
	public ModelAndView confirmOrder(HttpSession session,String itemIds) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String[] array = itemIds.split("\\,");
		List<Long> itemIdList = new ArrayList<>();
		for (String string : array) {
			itemIdList.add(Long.parseLong(string));
		}
		List<ItemVO> itemVOs = orderService.selectCartItemByUseridAndItemIds(Tools.getUserId(session), itemIdList);
		modelAndView.addObject("itemVOs", itemVOs);

		modelAndView.setViewName("/orderConfirm.jsp");
		return modelAndView;
	}

	// http://www.ajstore.com/select.html
	@RequestMapping("/select.html")
	@ResponseBody
	public PdOrder select(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdOrder pdOrder = new PdOrder();
		pdOrder.setOrderId("1111");
		return pdOrder;
	}

	// http://www.ajstore.com/show.html
	@RequestMapping("/show.html")
	public ModelAndView show() throws Exception {

		ModelAndView modelAndView = new ModelAndView("/order.jsp");
		PdOrder pdOrder = orderService.selectById("20161001490667343075");
		modelAndView.addObject("payment", pdOrder.getPayment());
		return modelAndView;
	}

}
