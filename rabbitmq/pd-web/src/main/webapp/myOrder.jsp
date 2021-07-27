<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的订单 - 拼多商城</title>
<link href="../css/myOrder.css" rel="Stylesheet" />
<link href="../css/header.css" rel="Stylesheet" />
<link href="../css/footer.css" rel="Stylesheet" />
</head>
<body>
	<jsp:include page="commons/header.jsp"></jsp:include>
	<!-- 我的订单导航栏-->
	<div id="nav_order">
		<ul>
			<li><a href="">首页<span>&gt;</span>订单管理
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
					<a ${status=="all"?"style='color:#0aa1ed'":"" }
						href="/order/toMyOrder.html">全部订单</a>
				</dd>
				<dd>
					<a ${status=="waitPay"?"style='color:#0aa1ed'":"" }
						href="/order/toMyOrder.html?status=waitPay"> 待付款 <span>
							<!--待付款数量-->
					</span>
					</a>
				</dd>
				<dd>
					<a ${status=="waitReceive"?"style='color:#0aa1ed'":"" }
						href="/order/toMyOrder.html?status=waitReceive"> 待收货 <span>
							<!--待收货数量-->1
					</span>
					</a>
				</dd>
				<dd>
					<a ${status=="waitAssess"?"style='color:#0aa1ed'":"" }
						href="/order/toMyOrder.html?status=waitAssess"> 待评价<span>
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
					<a href="personal_password.html">安全管理</a>
				</dd>
			</dl>
		</div>
		-->
		<!-- 右边栏-->
		<div class="rightsidebar_box rt">
			<!-- 商品信息标题-->
			<table id="order_list_title" cellpadding="0" cellspacing="0">
				<tr>
					<th width="345">商品</th>
					<th width="82">单价（元）</th>
					<th width="50">数量</th>
					<th width="82">售后</th>
					<th width="100">实付款（元）</th>
					<th width="90">交易状态</th>
					<th width="92">操作</th>
				</tr>
			</table>
			<!-- 订单列表项 -->
			<c:forEach items="${orderVOs}" var="orderVO">

				<div id="orderItem">
					<p class="orderItem_title">
						<span id="order_id"> &nbsp;&nbsp;订单编号: <a href="#">${orderVO.pdOrder.orderId}</a>
						</span> &nbsp;&nbsp;成交时间：
						<fmt:formatDate value="${orderVO.pdOrder.createTime}"
							pattern="yyyy-MM-dd HH:mm" />
						&nbsp;&nbsp; <span> <a href="#" class="servie"> 联系客服<img
								src="../images/myOrder/kefuf.gif" />
						</a> <a href="javascript:void(0)"
							onClick="cancelOrder('${orderVO.pdOrder.orderId}')"> 
							取消订单
							</a>
							<c:if test="${orderVO.pdOrder.status==1}">
							<a href="/order/toPayment.html?orderId=${orderVO.pdOrder.orderId}">
							去付款
							</a>
							</c:if>
						</span>
					</p>
				</div>
				<c:forEach items="${orderVO.pdOrderItems}" var="pdOrderItem">
					<div id="orderItem_detail">
						<ul>
							<li class="product"><b><a href="#"><img
										src="../images/myOrder/product_img1.png" /></a></b> <b
								class="product_name lf"> <a href="">${pdOrderItem.title}</a>
									<br />
							</b> <c:forEach items="${pdOrderItem.paramsList}" var="itemParam">
									<b class="product_color ">
										${itemParam.key}：${itemParam.values[0]} </b>
								</c:forEach></li>
							<li class="unit_price">专属价 <br /> ￥${pdOrderItem.price}
							</li>
							<li class="count">${pdOrderItem.num}件</li>
							<li class="sale_support">退款/退货 <br /> 我要维权
							</li>
							<li class=" payments_received">
								￥${pdOrderItem.num*pdOrderItem.price}</li>
							<li class="trading_status"><c:if
									test="${orderVO.pdOrder.status==1}">
                          待付款
                          </c:if> <c:if test="${orderVO.pdOrder.status==5}">
                          待收货
                          </c:if> <c:if test="${orderVO.pdOrder.status==6}">
                          待评价
                          </c:if> <c:if test="${orderVO.pdOrder.status==8}">
                                                         已取消
                          </c:if> <br /> <a href="orderInfo.html">订单详情</a> <br />
							</li>
							<li class="operate"><a href="#">确认收货</a></li>
						</ul>
					</div>
				</c:forEach>
			</c:forEach>
			<!--分页器-->
			<div class="tcdPageCode"></div>

		</div>
	</div>

	<!--<iframe src="order_status.html" width="1000" height=500""></iframe>-->
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
					<li><a href="#">关于达内</a></li>
					<li><a href="#">联系我们</a></li>
					<li><img src="../images/footer/wechat.png" alt="" /> <img
						src="../images/footer/sinablog.png" alt="" /></li>
				</ul>
			</div>
			<div class="service">
				<p>达内商城客户端</p>
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
<script src="../js/index.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/order.js?version=2"></script>
</html>
<script language="javascript">
	$(function() {
		var $div = $(".tcdPageCode");
		var status = "${status}"
		$div.createPage({
			pageCount : "${pageInfo.pages}",
			current : "${pageInfo.pageNum}",
			backFn : function(clickPage) {
				//alert(clickPage);
				//向服务器请求某一页数据
				var url = "/order/toMyOrder.html?status=" + status
						+ "&currentPage=" + clickPage;
				location.href = url;
			}
		});
	});

	function cancelOrder(orderId) {
		//alert(orderId);
		if (confirm("你真的要取消订单吗?")) {

			//必须加"" ${status}的值是个字符串
			var status = "${status}";
			//eclipse 格式后，不加"",出错，推荐js代码块中的el表达式加""
			var currentPage = "${pageInfo.pageNum}";
			
			//判断当前页是不是只有一个订单
			var $orderTitle=$(".orderItem_title");
			if ($orderTitle.length==1)
				{
				//只有一个订单
				//判断是不是第一页
				if (currentPage>=2)
					{
					currentPage--;
					}
				}
			
			
			var url = "/order/cancelOrder.html?orderId=" + orderId;
			if (status) {
				url = url + "&status=" + status;
			}
			if (currentPage) {
				url = url + "&currentPage=" + currentPage;
			}
			location.href = url;
		}
	}
</script>