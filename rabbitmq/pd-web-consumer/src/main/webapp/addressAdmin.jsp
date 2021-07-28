<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page isELIgnored="false" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta charset="UTF-8">
<title>我的订单 - 拼多商城</title>
<link href="../css/myOrder.css" rel="Stylesheet" />
<link href="../css/header.css" rel="Stylesheet" />
<link href="../css/footer.css" rel="Stylesheet" />
<link href="../css/personage.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="commons/header.jsp"></jsp:include>
	<!-- 我的订单导航栏-->
	<div id="nav_order">
		<ul>
			<li><a href="">首页<span>&gt;</span>个人中心
			</a></li>
		</ul>
	</div>
	<!--我的订单内容区域 #container-->
	<div id="container" class="clearfix">
		<!-- 左边栏-->
		<div id="leftsidebar_box" class="lf">
			<div class="line"></div>
			<dl class="my_order">
				<dt onClick="changeImage()">
					我的订单 <img src="../images/myOrder/myOrder2.png">
				</dt>
				<dd class="first_dd">
					<a href="myOrder.html">全部订单</a>
				</dd>
				<dd>
					<a href="#"> 待付款 <span>
							<!--待付款数量-->
					</span>
					</a>
				</dd>
				<dd>
					<a href="#"> 待收货 <span>
							<!--待收货数量-->1
					</span>
					</a>
				</dd>
				<dd>
					<a href="#"> 待评价<span>
							<!--待评价数量-->
					</span>
					</a>
				</dd>
				<dd>
					<a href="#">退货退款</a>
				</dd>
			</dl>

			<dl class="footMark">
				<dt onClick="changeImage()">
					我的优惠卷<img src="../images/myOrder/myOrder1.png">
				</dt>
			</dl>
			<dl class="address">
				<dt>
					收货地址<img src="../images/myOrder/myOrder1.png">
				</dt>
				<dd>
					<a href="addressAdmin.html">地址管理</a>
				</dd>
			</dl>
			<dl class="count_managment">
				<dt onClick="changeImage()">
					帐号管理<img src="../images/myOrder/myOrder1.png">
				</dt>
				<dd class="first_dd">
					<a href="personage.html">我的信息</a>
				</dd>
				<dd>
					<a href="personal_icon.html">个人头像</a>
				</dd>
				<dd>
					<a href="personal_password.html">安全管理</a>
				</dd>
			</dl>
		</div>
		<!-- 右边栏-->
		<div class="rightsidebar_box rt">
			<!--标题栏-->
			<div class="rs_header">
				<span class="address_title">收获地址管理</span>
			</div>
			<!--收货人信息填写栏-->
			<div class="rs_content">
				<form method="post" action="/address/insert.html" id="addressForm">
					<!--收货人姓名-->
					<div class="recipients">
						<span class="red">*</span><span class="kuan">收货人：</span><input
							type="text" name="receiverName" id="receiverName" />
					</div>
					<!--收货人所在城市等信息-->
					<div data-toggle="distpicker" class="address_content">
						<span class="red">*</span><span class="kuan">省&nbsp;&nbsp;份：</span>
						<select
							data-province="---- 选择省 ----" id="receiverState" name="receiverState">
							</select> 
							城市：<select
							data-city="---- 选择市 ----" id="receiverCity" name="receiverCity">
							</select>
							 区/县：<select
							data-district="---- 选择区 ----" id="receiverDistrict" name="receiverDistrict"></select>
					</div>


					<!--收货人详细地址-->
					<div class="address_particular">
						<span class="red">*</span><span class="kuan">详细地址：</span>
						<textarea name="receiverAddress" id="receiverAddress" cols="60"
							rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
					</div>
					<!--收货人地址-->
					<div class="address_tel">
						<span class="red">*</span><span class="kuan">手机号码：</span><input
							type="tel" id="receiverMobile" name="receiverMobile" />固定电话：<input
							type="text" name="receiverPhone" id="receiverPhone" />
					</div>
					<!--邮政编码-->
					<div class="address_postcode">
						<span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input
							type="text" name="receiverZip" />
					</div>
					<!--地址名称-->
					<div class="address_name">
						<span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input
							type="text" id="addressName" name="addressName" />如：<span
							class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
					</div>
					<!--保存收货人信息-->
					<div class="save_recipient">保存收货人信息</div>

				</form>
				<!--已有地址栏-->
				<div class="address_information_manage">
					<div class="aim_title">
						<span class="dzmc dzmc_title">地址名称</span><span
							class="dzxm dzxm_title">姓名</span><span class="dzxq dzxq_title">地址详情</span><span
							class="lxdh lxdh_title">联系电话</span><span
							class="operation operation_title">操作</span>
					</div>
					<c:forEach items="${list}" var="pdShipping">
					<div class="aim_content_one ${pdShipping.isDefault?'aim_active':'' }">
						<span class="dzmc ${pdShipping.isDefault?'dzmc_active':'dzmc_normal' }"">办公室</span> <span
							class="dzxm dzxm_normal">
							${pdShipping.receiverName}							
							</span> 
							<span class="dzxq dzxq_normal">
							${pdShipping.receiverState}
							${pdShipping.receiverCity}
							${pdShipping.receiverDistrict}
							${pdShipping.receiverAddress}
							</span>
						<span class="lxdh lxdh_normal">
						${pdShipping.receiverPhone}</span> <span
							class="operation operation_normal"> <span
							class="aco_change">修改</span>|
							<span id="${pdShipping.addId}" 
							class="aco_delete">删除</span>
						</span> 
						<!-- 以data开头，浏览器不做任何处理，给js用 -->
						<span class="swmr swmr_normal"
						data_addressId="${pdShipping.addId}"
						>
						${pdShipping.isDefault?"":"设为默认"}
						</span>
					</div>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>

	<!-- 品质保障，私人定制等-->
	<div id="foot_box">
		<div class="icon1 lf">
			<img src="../images/footer/icon1.png" alt="" />

			<h3>品质保障</h3>
		</div>
		<div class="icon2 lf">
			<img src="../images/footer/icon2.png" alt="" />

			<h3>私人定制</h3>
		</div>
		<div class="icon3 lf">
			<img src="../images/footer/icon3.png" alt="" />

			<h3>学员特供</h3>
		</div>
		<div class="icon4 lf">
			<img src="../images/footer/icon4.png" alt="" />

			<h3>专属特权</h3>
		</div>
	</div>
	<!-- 页面底部-->
	<div class="foot_bj">
		<div id="foot">
			<div class="lf">
				<p class="footer1">
					<img src="../images/footer/logo.png" alt="" class=" footLogo" />
				</p>
				<p class="footer2">
					<img src="../images/footer/footerFont.png" alt="" />
				</p>
			</div>
			<div class="foot_left lf">
				<ul>
					<li><a href="#"><h3>买家帮助</h3></a></li>
					<li><a href="#">新手指南</a></li>
					<li><a href="#">服务保障</a></li>
					<li><a href="#">常见问题</a></li>
				</ul>
				<ul>
					<li><a href="#"><h3>商家帮助</h3></a></li>
					<li><a href="#">商家入驻</a></li>
					<li><a href="#">商家后台</a></li>
				</ul>
				<ul>
					<li><a href="#"><h3>关于我们</h3></a></li>
					<li><a href="#">关于拼多</a></li>
					<li><a href="#">联系我们</a></li>
					<li><img src="../images/footer/wechat.png" alt="" /> <img
						src="../images/footer/sinablog.png" alt="" /></li>
				</ul>
			</div>
			<div class="service">
				<p>拼多商城客户端</p>
				<img src="../images/footer/ios.png" class="lf"> <img
					src="../images/footer/android.png" alt="" class="lf" />
			</div>
			<div class="download">
				<img src="../images/footer/erweima.png">
			</div>
			<!-- 页面底部-备案号 #footer -->
			<div class="record">&copy;2017 拼多集团有限公司 版权所有 京ICP证xxxxxxxxxxx</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/order.js"></script>
<script type="text/javascript" src="../js/distpicker.data.js"></script>
<script type="text/javascript" src="../js/distpicker.js"></script>
<script type="text/javascript" src="../js/personal.js?version=3"></script>
<script type="text/javascript">
	$(".lxdh_normal").each(function(i, e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
	//网页加载完执行function
	$(function() {
		//找到form中保存收货人信息div
		var $div = $(".save_recipient");
		//给div加事件处理
		$div.click(function() {
			//用代码提交form
			var $form = $("#addressForm");
			$form.submit();
		});
	});
</script>
</html>





