package com.pd.controller;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.pojo.PdResult;
import com.pd.pojo.PdUser;
import com.pd.common.GlobalConst;
import com.pd.common.utils.CookieUtils;
import com.pd.common.utils.DesUtil;
import com.pd.common.utils.MD5Encrypt;
import com.pd.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	//http://localhost:8090/user/login.html?username=b&password=123457
	@RequestMapping("/user/login.html")
	@ResponseBody
	public PdResult Login
	(HttpSession session,HttpServletRequest request,HttpServletResponse response,String username,String password) throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		//password=DesUtil.encrypt(password, "88889999");
		password=MD5Encrypt.MD5Encode(password);
		
		
		PdResult pdResult=new PdResult();
		pdResult.setStatus(500);
		
		PdUser dbPdUser=userService
				.selectByUsername(username);
		if (dbPdUser!=null && 
				password.equals(dbPdUser.getPassword()))
		{
			pdResult.setStatus(200);
			session.setAttribute("user", dbPdUser);
			String id=UUID.randomUUID().toString();
			CookieUtils.setCookie(request, response,
					GlobalConst.COOKIE_NAME	, id+"");
			session.setMaxInactiveInterval(24*60 * 60);
		}
		
		return pdResult;
	}
	@RequestMapping("/user/checkLogin.html")
	@ResponseBody
	public PdResult checkLogin
	(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception
	{
		//往响应头添加信息
		//给浏览器看的，如果是用js跨域访问，
		//浏览器只会把结果交给www.ajstore.com
		//允许所有网站访问跨域访问checkLogin，用*
		//如果有非80端口号必须加上http://www.ajstore.com:9090
		//80不能加，加上报错
		response.addHeader("Access-Control-Allow-Origin",
				"http://www.ajstore.com");
		//允许浏览器处理cookie
		response.addHeader("Access-Control-Allow-credentials",
				"true");
		
		
		PdResult pdResult=new PdResult();
		
		String ticket=CookieUtils.getCookieValue(request, 
				GlobalConst.COOKIE_NAME);
		if (StringUtils.isEmpty(ticket))
		{
			//用户没有登录
			pdResult.setStatus(500);
		}else
		{
			PdUser pdUser=(PdUser) 
					session.getAttribute("user");
			if (pdUser!=null){
				pdResult.setStatus(200);
				//用户的密码，手机号不能返回到浏览器
				pdUser.setPassword("");
				pdUser.setPhone("");
				pdUser.setEmail("");
				//最好创建一个新类，没有password,phone,email属性
				pdResult.setData(pdUser);
			}else
			{
				//登录过期
				pdResult.setStatus(500);
			}
			
		}
		return pdResult;
	}
	@RequestMapping("/user/toLogin.html")
	public ModelAndView toLogin() throws Exception
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/login.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/user/register.html")
	@ResponseBody
	//http://ip/user/register.html
	//?username=180501&password=123456&phone=13691481001&email=1@qq.com
	public PdResult register(
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			PdUser pdUser) 
			throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult=new PdResult();
		pdResult.setStatus(500);
		String password=pdUser.getPassword();
		//password=DesUtil.encrypt(password, "88889999");
		password=MD5Encrypt.MD5Encode(password);
		
		pdUser.setPassword(password);
		
		String username=pdUser.getUsername();
		PdUser dbPdUser=userService
				.selectByUsername(username);
		if (dbPdUser==null)
		{
			int row=userService.insert(pdUser);
			if (row>=1)
			{
				pdUser=userService.selectByUsername(username);
				pdResult.setStatus(200);
				session.setAttribute("user", pdUser);
				String id=UUID.randomUUID().toString();
				CookieUtils.setCookie(request, response,
						GlobalConst.COOKIE_NAME	, id+"");
			}
		}
		return pdResult;
	}

	@RequestMapping("/user/logout.html")
	@ResponseBody	
	public PdResult logout(HttpSession session,HttpServletRequest request,HttpServletResponse response,PdUser pdUser) 
			throws Exception
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-credentials", "true");
		PdResult pdResult=new PdResult();
		pdResult.setStatus(200);		
				session.setAttribute("user", null);				
				CookieUtils.deleteCookie(request, response,
						GlobalConst.COOKIE_NAME	);
			
		return pdResult;
	}

	@RequestMapping("/user/toRegister.html")
	public ModelAndView toRegister()
	{
		ModelAndView modelAndView=
				new ModelAndView("/register.jsp");
		return modelAndView;
	}
}
