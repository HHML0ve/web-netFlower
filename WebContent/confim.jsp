<%@page import="entity.Account"%>
<%@page import="dao.AccountDao"%>
<%@page import="entity.Cart"%>
<%@page import="dao.ItemsDao"%>
<%@page import="entity.Items"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认订单</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style type="text/css">
.right {
	float: right;
}
</style>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<a href="index.jsp">返回首页</a>
			<p>选择收货地址</p>
			<%
				Account account = (Account) session.getAttribute("user");
				String address = account.getAddress();
				out.print(account.getAddress());
			%>
			<form action="" method="post">
				<button type="button" class="btn btn-primary right">使用新地址</button>
			</form>
			<form action="BuyServlet" method="get">
				<%
					int id = Integer.parseInt(request.getParameter("id"));
					ItemsDao iDao = new ItemsDao();
					Items item = iDao.getItemsById(id);
					int number = Integer.parseInt(request.getParameter("number"));
				%>
				<table width="800px">
					<b>确认订单信息</b>
					<tr>
						<th>宝贝图片宝贝名称</th>
						<th>单价</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<tr>
						<td><img src="images/<%=item.getPicture()%>" width="200"
							height="160" /><a href="details.jsp?id=<%=item.getId()%>"><%=item.getName()%></a>
							<input type="hidden" name="id" value="<%=item.getId()%>" /></td>
						<td><input type="hidden" name="price"
							value="<%=item.getPrice()%>" /><%=item.getPrice()%></td>
						<td><input type="hidden" name="number" value="<%=number%>" /><%=number%></td>
						<td><%=number * item.getPrice()%></td>
					</tr>
					<div>
						付款方式：<input type="radio" value="balance" name="payWay"
							checked="checked" />余额 <input type="radio" value="Alipay"
							name="payWay" />支付宝 <input type="radio" value="weiChart"
							name="payWay" />微信
					</div>
					<div class="button">
						<button type="submit" class="btn btn-info right">结账</button>
					</div>
				</table>
			</form>
		</div>
	</div>
</body>
</html>