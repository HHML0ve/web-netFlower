<%@page import="entity.Items"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript">
	function delcfm() {
		if (!confirm("确认删除")) {
			window.event.returnValue = false;
		}
	}
</script>
</head>
<body>
	<h1>我的购物车</h1>

	<a href="index.jsp">首页</a>>>
	<a href="index.jsp">商品列表</a>
	<hr>
	<center>
		<form action="CartServlet?action=buy" method="post" name="cartform">
			<table>
				<%
					if (request.getSession().getAttribute("cart") == null) {
				%>
				<script type="text/javascript">
					alert("你的购物车还没有任何商品");
				</script>

				<%
					}
					if (request.getSession().getAttribute("cart") != null) {
				%>
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>小计</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<%
					Cart cart = (Cart) request.getSession().getAttribute("cart");
						HashMap<Items, Integer> goods = cart.getGoods();
						Set<Items> items = goods.keySet();
						Iterator<Items> it = items.iterator();

						while (it.hasNext()) {
							Items i = it.next();
				%>
				<tr name="products" id="product_id_1">
					<td class="thumb"><img src="images/<%=i.getPicture()%>"
						width="240" height="180" /><a
						href="details.jsp?id=<%=i.getId()%>"><%=i.getName()%></a></td>
					<td class="number"><%=i.getPrice()%></td>
					<td class="price" id="price_id_1"><span><%=i.getPrice() * goods.get(i)%></span>
						<input type="hidden" value="" /></td>
					<td class="number"><%=goods.get(i)%></td>
					<td class="delete"><a
						href="CartServlet?action=delete&id=<%=i.getId()%>"
						onclick="delcfm();">删除</a></td>
				</tr>

				<%
					}
				%>
			</table>
			<div class="total">
				<span id="total">总计：<%=cart.getTotalPrice()%>￥
				</span> 付款方式：<input type="radio" value="balance" name="payWay"
					checked="checked" />余额 <input type="radio" value="Alipay"
					name="payWay" />支付宝 <input type="radio" value="weiChart"
					name="payWay" />微信
			</div>
			<div class="button">
				<!-- <input type="submit" value="结账" />
				<a href="CartServlet?action=buy"><img alt="结账" src="images/buy_all.png"></a>
				 -->
				<img alt="提交按钮" src="images/buy_all.png" onclick="cartform.submit()">
			</div>
			<div class="input-group">
				<span class="input-group-addon">优惠券</span> <input type="text"
					name="vip" class="form-control" placeholder="打折：1012896**--返还：2012896**">
			</div>
			<%
				}
			%>

		</form>
	</center>

</body>
</html>