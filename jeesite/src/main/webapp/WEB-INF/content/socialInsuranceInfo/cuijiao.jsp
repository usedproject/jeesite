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

<script type="text/javascript">
	$(document).ready(function() {
				//$("#name").focus();
				$("#inputForm").validate({
					/* 自定义远端的查询验证方法 */
					rules:{  
						username:{  
			                remote:{  
			                    type:"POST",  
			                    url:"system/user/check.do", //请求地址  
			                    data:{  
			                    	username:function(){ return $("#username").val(); }  
			                    }  
			                }  
			            }  
			        },  
			        messages:{  
			            username:{  
			                remote:"用户名不存在"  
			            }  
			        },
					submitHandler : function(form) {
						$.post("business/emailRecord/addEmailRecord.do", 
								$("#inputForm").serialize(),
								function(data) {
									if (data.success) {
										alert(data.message);
										window.location.href="business/market/toMarket.do";
									} else {
										alert(data.message);
									}

								}, "json");
					},
					errorContainer : "#messageBox",
					errorPlacement : function(error, element) {
						$("#messageBox").text("输入有误，请先更正。");
						if (element.is(":checkbox")
								|| element.is(":radio")
								|| element.parent().is(
										".input-append")) {
							error.appendTo(element.parent()
									.parent());
						} else {
							error.insertAfter(element);
						}
					}
				});
				
				
				/* 这是进行离焦事件绑定 */
				$("#username").on("blur",function(){
					var username=$("#username").val();
					if (username==""){
						return
					}
					$.post(
						"system/user/getByName.do",
						{"username":username},
						function(data){
							if(data.success){
								$("#userId").val(data.result.id)
							}
						},
						"json"
					);
				}) 
				
				/* 进行选择模板按钮的点击事件 */
				$("#parentIframe").on("click",function(){
					 parent.openTopWindow(
							{"url":"business/emailRecord/toSelectEmailTemplate.do",
			    			"title":"选择邮件模板",
			    			'onClose':function(){
			    				$("#templateId").val(parent.$("#TemplateId").val());
			    				$("#subject").val(parent.$("#subject").val());
			    				$("#content").val(parent.$("#content").val())
			    				}
			    			}
							 )
				});
			});
</script>

</head>
<body>

	<br>
	<form id="inputForm" class="form-horizontal" action="#" method="post"
		novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="">
		<div class="control-group">
			<label class="control-label">发信人：</label>
			<div class="controls">
				<input id="username" name="username"  class="input-xlarge required " type="text" value="" maxlength="11">
				<span class="help-inline"><font color="red">*</font> </span>
				<input id="userId" name="userId" type="hidden">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收信人：</label>
			<div class="controls">
				<input id="toAddr" name="toAddr" class="input-xlarge required"
					type="text" value="" maxlength="256"> <span
					class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮件模板：</label>
			<div class="controls">
				<input type="text" id="templateId" name="templateId" readonly="readonly" value="" htmlescape="false"
					maxlength="512" class="input-xlarge "> <input
					type="button" name="chooseSms" value="选择" id="parentIframe">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<input id="subject" name="subject" class="input-xlarge required"
					type="text" value="催缴社保" maxlength="128"> <span
					class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<textarea id="content" name="content" maxlength="256"
					class="input-xxlarge required" rows="4">您好，您本月社保还未缴纳，请您尽快缴纳</textarea>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存">&nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>

</body>
</html>