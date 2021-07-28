package com.pd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pd.pojo.PdOrder;

@Controller
public class ExceptionController {

	@RequestMapping("/e/test1.html")
	public ModelAndView test1() throws Exception
	{
		PdOrder pdOrder=new PdOrder();
		pdOrder.setUserId(14L);
		ModelAndView modelAndView=new ModelAndView("index");
		return modelAndView;		
	}
	@RequestMapping("/e/test2.html")
	public ModelAndView test2() throws Exception
	{
		PdOrder pdOrder=new PdOrder();
		pdOrder.setUserId(null);
		ModelAndView modelAndView=new ModelAndView("index");
		return modelAndView;		
	}
	@RequestMapping("/e/test3.html")
	public ModelAndView test3() throws Exception
	{
		PdOrder pdOrder=null;
		pdOrder.toString();
		ModelAndView modelAndView=new ModelAndView("index");
		return modelAndView;		
	}
}
