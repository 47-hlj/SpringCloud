<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 页面顶部-->
<header id="top">
	<div id="logo" class="lf">
		<a href="/"> <img	src="/images/server/images/portal/header/logo.png" alt="logo" />
		</a>
	</div>
	<div id="top_input" class="lf">
		<c:choose>
			<c:when test="${not empty q}">
				<input id="input" type="text" placeholder="${q}" />
			</c:when>
			<c:otherwise>
				<input id="input" type="text" placeholder="请输入您要搜索的内容" />
			</c:otherwise>
		</c:choose>
		   <div class="seek" tabindex="-1">
            <div class="actived" ><span>分类搜索</span> <img src="/images/server/images/portal/header/header_normal.png" alt=""/></div>
            <div class="seek_content" >
                <div id="shcy" >生活餐饮</div>
                <div id="xxyp" >学习用品</div>
                <div id="srdz" >私人订制</div>
            </div>
        </div>
        
		<a href="javascript:void(0);" class="rt" onclick="search1()"><img id="search"
			src="/images/server/images/portal/header/search.png" alt="搜索"/></a>
	</div>
	<div class="rt">
		<ul class="lf" id="iul">
			<li><a href="/collect/toMyCollect.html" title="我的收藏"> <img class="care"
					src="/images/server/images/portal/header/care.png"
					alt="" />
			</a><b>|</b></li>
			<li><a href="/order/toMyOrder.html" title="我的订单"> <img class="order"
					src="/images/server/images/portal/header/order.png" alt="" />
			</a><b>|</b></li>
			<li><a href="/cart/toCart.html" title="我的购物车"> <img class="shopcar"
					src="/images/server/images/portal/header/shop_car.png" alt="" />
			</a><b>|</b></li>
			<li></li>
		</ul>
	</div>
	<br />
</header>
	<nav id="nav">
		<ul>
			<li><a href="/">首页</a></li>
			<li><a href="/food/toItemFood.html">生活餐饮</a></li>
			<li><a href="/toCate.html">学习用品</a></li>
			<li><a href="/lookforward.html">私人定制</a></li>
		</ul>
	</nav>
	<script src="/js/jquery-3.1.1.min.js"></script>
	<script src="/js/slide.js"></script>
	<script type="text/javascript">
		function logout() {
			
			$.ajax({
				url : '/user/logout.html',
				type : 'post',
				dataType:'json',
				success:function(result) {
					if (result != null && result != "" && result != undefined) {
						if (result.status == 200) {
							//alert(result.msg);
							window.location.href = "/user/toLogin.html";
						}else {
							alert(result.msg);
						}
					}
				},
				error:function() {
					alert('退出失败！');
				}
			});
		}
	</script>
	<script>
	$('#nav>ul>li').click(function(){
        $(this).children().addClass('active');
        $(this).siblings().children().removeClass('active');
    })
	</script>
	
	<script src="/js/jquery.cookie.js"></script>
	<script type="text/javascript">
		$(function () {
			//请求本网站checkLogin.html,checkLogin()用httpClient做代理去访问sso
			$.ajax({
				type:"POST",
				url:"/user/checkLogin.html",
				xhrFields:{withCredentials:true},
				dataType:"json",
				success:function(result){
					var user = result.data;
					console.log(result);
					if (result.status === 200) {
						$("#iul").append('<li><a href="/lookforward.html">'+user.username+'</a><b>|</b></li><li><a href="/address/list.html">地址管理</a> | <a href="javascript:;" onclick="logout()">退出</a></li>');
					}else if(result.status === 500){
						$("#iul").append('<li><a href="/user/toLogin.html">登录</a></li>');
					}
				},
				error:function(textStatus,XMLHttpRequest){
					//alert("系统异常！");
				}
				
			});
			//$.cookie出异常
			//var ticket = $.cookie("DN_TICKET");
			//服务器返回的是js,这种处理跨域的方式叫jsonp
			/* $.ajax({
				type:"post",
				url:"http://sso.ajstore.com:90/user/checkLoginForJsonp.html",
				dataType:"jsonp",
				jsonp:"jsonpCallback",//jsonpCallback是服务器端接收参数的参数名
				xhrFields:{withCredentials:true},//ajax默认不发送cookie
				//浏览器收到的是jquery(json字符串)
				//函数名jquery
				//执行函数jquery,得到的是json字符串，再调用success,把json字符串传过来了
				success:function(result){
					var user = result.data;
					console.log(result);
					if (result.status === 200) {
						$("#iul").append('<li><a href="/lookforward.html">'+user.username+'</a><b>|</b></li><li><a href="javascript:;" onclick="logout()">退出</a></li>');
					}else if(result.status === 500){
						$("#iul").append('<li><a href="http://sso.ajstore.com:90/user/toLogin.html?callback=http://www.ajstore.com">登录</a></li>');
					}
				},
				error:function(textStatus,XMLHttpRequest){
					alert("系统异常！"+JSON.stringify(textStatus)+" ------ "+XMLHttpRequest);
				}
			}); */
			
			//服务器返回的是json
			/* $.ajax({
				type:"post",
				url:"http://sso.ajstore.com:90/user/checkLogin.html",
				dataType:"json",//原先是jsonp要改成json
				xhrFields:{withCredentials:true},//ajax默认不发送cookie
				success:function(result){
					var user = result.data;
					console.log(result);
					if (result.status === 200) {
						$("#iul").append('<li><a href="/lookforward.html">'+user.username+'</a><b>|</b></li><li><a href="javascript:;" onclick="logout()">退出</a></li>');
					}else if(result.status === 500){
						$("#iul").append('<li><a href="http://sso.ajstore.com:90/user/toLogin.html?callback=http://www.ajstore.com">登录</a></li>');
					}
				},
				error:function(textStatus,XMLHttpRequest){
					alert("系统异常！"+JSON.stringify(textStatus)+" ------ "+XMLHttpRequest);
				}
			}); */
		})
</script>
<script>
function search1(){
	var q=$("#input").val();
	console.log(q);
	window.location.href = "/search/toSearch.html?key="+q;
}
</script>
<script type="text/javascript">   

    document.onkeydown=keyDownSearch;
   
    function keyDownSearch(e) { 
        var theEvent = e || window.event; 
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode; 
        if (code == 13) {  
        	search1();
            return false;
        }
        return true;
    }
</script>