<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style type="text/css">
.session {
	background: url(images/login.jpg) 50%;
	background-repeat: no-repeat;
	background-size: 100% 100%;
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
<script type="text/javascript">
	function reCode() {
		regForm.CheckCode.src = "CheckCode";
	}
</script>
</head>
<body class="session">
	<div class="windowLogin">
		<form action="TestServlet" method="post" id="regForm" name="regForm"
			class="bs-example bs-example-form">
			<div class="input-group">
				<span class="input-group-addon">用户昵称：</span> <input type="text"
					name="name" class="form-control" placeholder="用户名">
			</div>
			<div class="input-group">
				<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
				<input type="text" name="password" class="form-control"
					placeholder="密码">
			</div>
			<div class="input-group">
				<span class="input-group-addon">确认密码：</span> <input type="text"
					name="confimpwd" class="form-control" placeholder="密码">
			</div>

			性别：<input type="radio" name="sex" value="m" checked="checked" />男 <input
				type="radio" name="sex" value="f" /> 女<br>

			<div class="input-group">
				<span class="input-group-addon">电&nbsp;&nbsp;&nbsp;话：</span> <input
					type="text" name="tel" class="form-control" placeholder="密码">
			</div>


			<div class="input-group">
				<span class="input-group-addon">地&nbsp;&nbsp;&nbsp;址：</span> <input
					type="text" name="address" class="form-control" placeholder="密码">
			</div>

			<div class="input-group">
				<span class="input-group-addon">验证码：</span> <input type="text"
					name="checkCode" id="checkCode" class="form-control"
					placeholder="验证码">
			</div>

			<img alt="验证码" src="CheckCode" name="CheckCode" width="166"
				height="60" border="1" id="CheckCode" onclick="reCode()"> <a
				href="#" onclick="reCode()">看不清，换一个</a><br>

			<button type="submit" class="btn btn-primary">注册</button>
			<a href="login.jsp">已有账号？点此登陆</a>
		</form>
	</div>

	<script type="text/javascript">
		function reCode() {
			regForm.CheckCode.src = "CheckCode";
		}
	</script>
</body>
</html>