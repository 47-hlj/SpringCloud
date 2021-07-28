package com.pd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pd.pojo.PdOrder;
import com.pd.payment.utils.PaymentUtil;
import com.pd.service.OrderService;

@Controller
@RequestMapping("/yibao")
public class PaymentController {
	@Autowired
	private OrderService orderService;
	//商户编号，易宝提供的
	private String accountID="10001126856";
	//加密用的，易宝提供
	private String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
	
	//返回string 就是返回网页
	@RequestMapping("/paymentSend.html")
	public String paymentSend(String orderId, String bankId, Model model) throws Exception{
		//整理易宝支付所需要的所有请求参数
		String message="";
		if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(bankId) ) {
			message = "您的订单状态好像有点问题哦！";
			model.addAttribute("message", message);
			return "pay_fail";
		}
		
		PdOrder order = orderService.selectById(orderId);
		if(order == null || order.getStatus()!=1){
			message = "您的订单状态好像有点问题哦！";
			model.addAttribute("message", message);
			return "pay_fail";
		}
		
		// 测试商户：商户接收支付成功数据的地址
		String accountCallbackURL = "http://payment.ajstore.com/yibao/checkPayment.html";
		String businessType = "Buy"; // 业务类型。Buy为在线支付
		String currency = "CNY"; // 交易币种。CNY为人民币
		String productDesc = ""; // 商品描述
		String productCategory = ""; // 商品种类
		String productID = ""; // 商品ID
		String addressFlag = ""; // 送货地址。0为不需要，1为需要
		String accountMoreInfo = ""; // 商户扩展信息
		String pr_NeedResponse = "0"; // 应答机制
		String payment = order.getPayment()+""; //订单价格
		//测试数据payment=0.01
		payment = 0.01+"";
		String md5hmac = PaymentUtil.buildHmac(businessType, accountID, orderId, payment, currency, productID,
				productCategory, productDesc, accountCallbackURL, addressFlag, accountMoreInfo, bankId,
				pr_NeedResponse, keyValue);
		model.addAttribute("businessType", businessType);
		model.addAttribute("accountID", accountID);
		model.addAttribute("orderID", orderId);
		model.addAttribute("amount", payment);
		model.addAttribute("currency", currency);
		model.addAttribute("productID", productID);
		model.addAttribute("productCategory", productCategory);
		model.addAttribute("productDesc", productDesc);
		model.addAttribute("accountCallbackURL", accountCallbackURL);
		model.addAttribute("addressFlag", addressFlag);
		model.addAttribute("accountMoreInfo", accountMoreInfo);
		model.addAttribute("accountBankID", bankId);
		model.addAttribute("needResponse", pr_NeedResponse);
		//相当于modelAndView.addObject()
		model.addAttribute("md5hmac", md5hmac);
		//modelAndView.setViewName()
		return "/connection.jsp";
	}
	
}






