package com.pd.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pd.pojo.PdItem;
import com.pd.pojo.PdResult;
import com.pd.pojo.DetailVO;
import com.pd.service.ApiService;

@Controller
@RequestMapping("/api")
public class ApiController {
	

	@Autowired
	ApiService apiService;
	
	@RequestMapping("/getIndexItem.html")
	@ResponseBody
	public PdResult getIndexItem(HttpServletResponse response) throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult=new PdResult();
		List<PdItem> list=apiService.getIndexItem();
		pdResult.setData(list);
		pdResult.setStatus(200);
		return pdResult;
		
	}
	
	//   http://172.211.100.32/api/getItemDetail
	//     .html?itemId=10000028
	@RequestMapping("/getItemDetail.html")
	@ResponseBody
	public PdResult getItemDetail(HttpServletResponse response,Long itemId) 
			throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult=new PdResult();
		DetailVO detailVO=apiService.getItemDetail(itemId);
		pdResult.setData(detailVO);
		pdResult.setStatus(200);
		return pdResult;
	}
	
	
}
