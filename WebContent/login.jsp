<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript">
	function reCode() {
		regForm.CheckCode.src = "CheckCode";
	}
</script>
<style type="text/css">
.session {
	background: url(images/login.jpg) 50%;
	background-repeat: no-repeat;
	/*background-size: 100% 100%;*/
	background-size: cover;
}

.windowLogin {
	width: 400px;
	margin-left: 40%;
	margin-top: 13%;
	opacity: 0.7;
	background-color: #fff;
	font-family: "微软雅黑";
	font-weight: bolder;
	padding: 40px;
	line-height: 40px;
}
</style>
</head>
<body class="session">
	<div class="windowLogin">
		<form action="LoginServlet" method="post"
			class="bs-example bs-example-form">
			<div class="input-group">
				<span class="input-group-addon">用户名：</span> <input type="text"
					name="name" class="form-control" placeholder="用户名">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码：</span> <input
					type="text" name="password" class="form-control" placeholder="密码">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon">验证码：</span> <input type="text"
					name="checkCode" id="checkCode" class="form-control"
					placeholder="验证码">
			</div>

			<img alt="验证码" src="CheckCode" name="CheckCode" width="166"
				height="60" border="1" id="CheckCode" onclick="reCode()"> <a
				href="#" onclick="reCode()">看不清，换一个</a><br>
			<!-- <input type="submit" value="登陆" class="btn btn-default" /> -->
			<button type="submit" class="btn btn-primary">登录</button>
			<a href="getPwd.jsp">忘记密码？点击这里</a><br> <strong>第三方的登陆</strong><a
				href="QQ.jsp">QQ登陆</a><a href="SH.jsp">微博登陆</a><a href="aL.jsp">支付宝登陆</a>
		</form>
	</div>

</body>
<script type="text/javascript">
	function reCode() {
		regForm.CheckCode.src = "CheckCode";
	}
</script>
</body>
</html>