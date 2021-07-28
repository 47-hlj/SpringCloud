package com.pd.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.common.utils.Tools;
import com.pd.pojo.PdResult;
import com.pd.pojo.PdShipping;
import com.pd.pojo.PdUser;
import com.pd.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	AddressService addressService;

	@RequestMapping("/address/getDefault.html")
	@ResponseBody
	public PdResult getDefault(HttpSession session, HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult = new PdResult();
		
		PdShipping pdShipping = addressService.getDefaultByUserId(Tools.getUserId(session));
		if (pdShipping != null) {
			pdResult.setStatus(200);
			pdResult.setData(pdShipping);
		} else {
			pdResult.setStatus(500);
		}
		return pdResult;
	}

	@RequestMapping("/address/setDefault.html")
	@ResponseBody
	// http://www.ajstore.com
	// /address/setDefault.html?addId=1
	public PdResult setDefault(HttpSession session,HttpServletResponse response, Long addId) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult = new PdResult();
		int row = addressService.setDefault(Tools.getUserId(session), addId);
		if (row >= 1) {
			pdResult.setStatus(200);
		} else {
			pdResult.setStatus(500);
		}
		return pdResult;

	}

	@RequestMapping("/address/delete.html")
	@ResponseBody
	// http://www.ajstore.com/address/delete.html?addId=3
	public PdResult delete(HttpServletResponse response, Long addId) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult = new PdResult();
		int row = addressService.delete(addId);
		if (row >= 1) {
			pdResult.setStatus(200);
		} else {
			pdResult.setStatus(500);
		}
		return pdResult;
	}

	@RequestMapping("/address/insert.html")
	// http://www.ajstore.com/address/insert.html
	public ModelAndView Insert(HttpSession session,PdShipping pdShipping) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/addressAdmin.jsp");
		pdShipping.setUserId(Tools.getUserId(session));
		pdShipping.setStatus((byte) 1);
		pdShipping.setIsDefault(true);

		addressService.insert(pdShipping);
		List<PdShipping> list = addressService.selectByUserId(Tools.getUserId(session));
		System.out.println(list);
		modelAndView.addObject("list", list);
		return modelAndView;
	}

	// http://www.ajstore.com/address/list.html
	@RequestMapping("/address/list.html")
	public ModelAndView list(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/addressAdmin.jsp");
		List<PdShipping> list = addressService.selectByUserId(Tools.getUserId(session));
		System.out.println(list);
		modelAndView.addObject("list", list);
		return modelAndView;
	}

}
