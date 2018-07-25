<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人信息 - Powered By JeeSite</title>
	<base href=<%=basePath%>>
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="../static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="../static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="../static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
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
<script type="text/javascript">var ctx = '../a', ctxStatic='../static';</script>
	<!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>
<style type="text/css">
#formerror{
	color: red;
}

</style>
	
	<meta name="decorator" content="default">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					if(${user.id==1000 }){
						$.messager.alert('Warning','admin用户不能修改');
						parent.closeTopWindow();
						return;
					}
					$.post(
							"system/edituser.do",
							$("#inputForm").serialize(),
							function(data){
								if(data.success){
									alert("修改信息成功")
								}
							},
							"json"
					)
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		}); 
	</script>

</head>
<body>
	<br><br>
	<form id="inputForm" class="form-horizontal"  novalidate="novalidate">
		<div class="control-group">
			<input id="id" name="id" type="hidden" value="${user.id }" >
			<div id="formerror"></div>
			<label class="control-label">姓名:</label>
			<div class="controls">
				<input id="username" style="height: 30px" name="username" class="required"  type="text" value="${user.username }" maxlength="50">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<input id="email" name="email" style="height: 30px" class="required email" type="text" value="${user.email }" maxlength="50">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<input style="height: 30px"  id="phone" name="phone" class="required " type="text" value="${user.phone }" maxlength="50">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<textarea id="usernote" name="usernote" value="${user.usernote }" maxlength="200" class="input-xlarge" rows="3"></textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<c:forEach items="${roles }" var="role">
					<label class="lbl">${role.rolename }</label>
				</c:forEach>
				
			</div>
		</div>
		<div class="control-group">
			<c:if test="${lastlogin==null }">
				<label class="control-label">本次登录为首次登录</label>
			</c:if>
			<c:if test="${lastlogin!=null }">
				<label class="control-label">上次登录:</label>
				<div class="controls">
					<label class="lbl">IP: ${lastip} &nbsp;&nbsp;&nbsp;&nbsp;时间：${lastlogin }</label>
				</div>
			</c:if>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">
		</div>
	</form>


</body></html>