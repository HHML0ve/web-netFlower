<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>重新获取登录密码</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
	<h1>修改密码:</h1>
	<hr>
<div class="container">
	<div class="jumbotron">

			<form action="ChangeServlet" method="post">
			
				<div class="input-group">
					<span class="input-group-addon">用户名：</span>
					<input type="text" name="username" class="form-control" placeholder="username">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">电&nbsp;&nbsp;&nbsp;话：</span>
					<input type="text" name="tel" class="form-control" placeholder="telphone">
				</div>
				<br>
					<div class="input-group">
					<span class="input-group-addon">验证码:&nbsp;&nbsp;</span>
					<input type="text" name="checkCode" class="form-control" placeholder="checkCode">
				</div>
				<br>
					<div class="input-group">
					<span class="input-group-addon">新密码：</span>
					<input type="text" name="newpwd" class="form-control" placeholder="newPassword">
				</div>
				<br>
		<input type="submit" value="提交申请" style="float: right;" class="btn btn-primary btn-lg">
	</form>

	</div>
</div>
</body>
</html>