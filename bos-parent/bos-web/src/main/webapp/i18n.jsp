<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="js/i18n.js"></script>
<head>
<base href=<%=basePath%>>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中英文切换</title>
</head>
<body>
	<div id="aa" onclick="aa()">点击事件</div>
	<div>eval("show".toMessage())</div>
</body>
</html>