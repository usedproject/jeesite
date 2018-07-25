<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0066)http://localhost:8080/jeesite/a/company/system/systemFunction/form -->
<html style="overflow-x: auto; overflow-y: auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理 - Powered By JeeSite</title>
<base href=<%=basePath%>>
<!-- 引入easyui -->
<%@include file="../script.html"%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://jeesite.com/" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<!-- <script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script> -->
<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<!-- <script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script> -->
<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css"
	type="text/css" rel="stylesheet" />
<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<!-- <script src="static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script> -->
<link href="static/jquery-validation/1.11.0/jquery.validate.min.css"
	type="text/css" rel="stylesheet" />
<script src="static/jquery-validation/1.11.0/jquery.validate.min.js"
	type="text/javascript"></script>
<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />
<!-- <script src="static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script> -->
<!-- <script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="static/common/mustache.min.js" type="text/javascript"></script> -->
<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
<!-- <script src="static/common/jeesite.js" type="text/javascript"></script> -->
<meta name="decorator" content="default">
</head>
<body>

	<br>
	<form id="inputForm" class="form-horizontal" action="#" method="post"
		novalidate="novalidate">
		
		<div class="control-group">
			<label class="control-label">发信人：</label>
			<div class="controls">
				<input id="username" name="username"  class="input-xlarge required " type="text" value="${emailRecord.userName }" maxlength="11">
				<span class="help-inline"><font color="red">*</font> </span>
				<input id="userId" name="userId" type="hidden">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收信人：</label>
			<div class="controls">
				<input id="toAddr" name="toAddr" class="input-xlarge required"
					type="text" value="${emailRecord.toAddr }" maxlength="256"> <span
					class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<input id="subject" name="subject" class="input-xlarge required"
					type="text" value="${emailRecord.subject }" maxlength="128"> <span
					class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<textarea id="content" name="content" maxlength="256"
					class="input-xxlarge required" rows="4">${emailRecord.content }</textarea>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</form>

</body>
</html>