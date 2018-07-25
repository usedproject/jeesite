<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath%>>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
	<script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
	<!--[if lte IE 7]><link href="./static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
	<!--[if lte IE 6]><link href="./static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
	<script src="./static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<script src="static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="static/common/mustache.min.js" type="text/javascript"></script>
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<script src="static/common/jeesite.js" type="text/javascript"></script>
	<meta name="decorator" content="blank">
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    </style>
<script type="text/javascript">
	function changeTheam(color){
		$("body").css("background",color);
	}
	
	$(function(){
		$("#submit").on("click",function(e){
			e.preventDefault();
			$("#loginError").html("");
			var username=$("#username").val();
			var password=$("#password").val();
			if(username==""){
				$("#loginError").html("请输入用户名");
				$("#loginError").css("color","red");
				return;
			}
			if(password==""){
				$("#loginError").html("请输入密码");
				$("#loginError").css("color","red");
				return;
			}
			$.post(
				"system/login.do",
				$("#loginform").serialize(),
				function(data){
					if(data.success){
						window.location.href="toindex.do";
					}else{
						$("#loginError").html(data.message);
						$("#loginError").css("color","red");
					}
				}
			);
		})
	});
	
</script>	
<style type="text/css">
body{
	height:600px;
}

</style>
</head>
<body><style type="text/css">.com-zoom-flash{position:absolute;bottom:0;right:0;height:10px;width:10px;}.com-zoom-tips-hack{height:2em;display:none;}.com-zoom-warn{display:none;position:fixed;top:0;left:0;width:100%;line-height:2em;text-align:center;background-color:#ffffcc;font-size:1.2em;color:#8e0000;z-index:9999;}.com-zoom-close{float:right;width:2em;height:1.7em;line-height:1.7em;font-weight:bold;font-family:"Tahoma";color:#8e0000;text-decoration:none;}.com-zoom-close:hover{color:#d8ab57;}.com-zoom-notip{color:#3e3e3e;text-decoration:underline;}.com-zoom-notip:hover{text-decoration:none;}.com-zoom-text{margin:0;}</style><div class="com-zoom-flash"><object codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="10" height="10"><param name="movie" value="/jeesite/static/flash/zoom.swf"><param name="quality" value="high"><param name="wmode" value="transparent"><embed src="/jeesite/static/flash/zoom.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="10" height="10" wmode="transparent"></object></div><div class="com-zoom-tips-hack"><div class="com-zoom-warn" id="com-zoom-warn"><a href="javascript:void(0);" class="com-zoom-close" title="关闭" hidefocus="true" target="_self">x</a><p class="com-zoom-text">您的浏览器目前处于缩放状态，会导致网页显示不正常，您可以键盘按“ctrl+数字0”组合键恢复初始状态。<a href="javascript:void(0);" class="com-zoom-notip" target="_self" hidefocus="true">不再提示</a></p></div></div>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<!-- <div class="header">
		<div id="messageBox" class="alert alert-error hide"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error"></label>
		</div>
	</div> -->
	<h1 class="form-signin-heading">安思普惠人事代理系统</h1>
	<form id="loginform" class="form-signin"  novalidate="novalidate">
		<div id="loginError"></div>
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username" class="input-block-level required" value="${cookie.username.value}">
		
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required" value="${cookie.password.value}">
		
		<input class="btn btn-large btn-primary" type="submit" id="submit" value="登 录">&nbsp;&nbsp;
		<label for="rememberMe" title="下次不需要再登录">
		<input type="checkbox" id="rememberMe" name="rememberMe"> 记住我（公共场所慎用）</label>
		
		<div id="themeSwitch" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="http://localhost:8080/jeesite/a/login;JSESSIONID=7b0a567f7ec04124837646d979ead83c#">默认主题<b class="caret"></b></a>
			<ul class="dropdown-menu">
			  <li><a href="javascript::void(0)" onclick="changeTheam('white')">默认主题</a></li>
			  <li><a href="javascript::void(0)" onclick="changeTheam('skyblue')">天蓝主题</a></li>
			  <li><a href="javascript::void(0)" onclick="changeTheam('orange')">橙色主题</a></li>			  
			</ul>
			<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
		</div>
	</form>
	<div class="footer">
		Copyright © 2012-2017 <a href="http://localhost:8080/jeesite/f">安思普惠人事代理系统</a> - Powered By <a href="http://www.zhidisoft.com/" target="_blank">zhidisoft</a> V1.2.7 
	</div>

</body></html>