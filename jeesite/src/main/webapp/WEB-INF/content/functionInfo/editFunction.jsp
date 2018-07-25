<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0066)http://localhost:8080/jeesite/a/company/system/systemFunction/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>菜单管理 - Powered By JeeSite</title>
	<base href=<%=basePath%>>
	<!-- 引入easyui -->
	<%@include file="../script.html" %>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
	<!-- <script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script> -->
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<!-- <script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script> -->
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<!-- <script src="static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script> -->
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
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
				submitHandler: function(form){
					var status=$("#status").val();
					if(status==""){
						alert("请选择状态");
						return;
					}
					var parentid=$("#parent_Id").val();
					if(parentid==${fc.id }){
						alert("父节点不能为自己");
						return;
					}
					$.post(
						"system/function/edit.do",
						$("#inputForm").serialize(),
						function(data){
							if(data.success){
								alert(data.message);
							}else{
								
							}
							
						},
						"json"
					);
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
<br>
	<ul class="nav nav-tabs">
		<li ><a href="company/system/function.do">菜单列表</a></li>
		<li class="active"><a href="javascript:void(0)">菜单添加</a></li>
	</ul>
	<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="${fc.id }">
		<!--  这个功能需要放在服务器上才能使用,不支持本地的-->
		<div class="control-group">
			<label class="control-label">上级菜单:</label>
			<div class="controls">
    
<div class="input-append">
	<input id="parent_Id" name="parentid" class="required" type="hidden" value="${fc.parentfunction.id }">
	<input id="parent_Name"  readonly="readonly" type="text" value="${fc.parentfunction.funcname }" data-msg-required="" class="required" style="">
	<a id="parent_Button" href="javascript:void(0)" onclick="openWindow()" class="btn" style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
</div>
<script type="text/javascript">
	
	function openWindow(){
		parent.openTopWindow(
    			{"url":"toSelectFunction.do",
    			"title":"增加菜单",
    			'onClose':function(){
    				$("#parent_Id").val(parent.$("#parent_Id").val());
    				$("#parent_Name").val(parent.$("#parent_Name").val());
    			}
    			})
	}
	
	
	$(function(){
		/* 动态加载权限类型数据下拉框 */
		$.post(
				"system/dict/selectAll.do",
				{"name":"functype"},
				function(data){
					$.each(data,function(i,o){
						if(o.value==${fc.functype}){
							var content =$("<option value='"+o.value+"' selected='selected' >"+o.label+"</option>");
							$("#functype").append(content);
						}else{
							var content =$("<option value='"+o.value+"' selected='selected' >"+o.label+"</option>");
							$("#functype").append(content);
						}
						
					}
					)	
				},
				"json"
			);  
		
		/* 动态加载状态的下拉框 */
		$.post(
				"system/dict/selectAll.do",
				{"name":"del_flag"},
				function(data){
					$.each(data,function(i,o){
						if(o.value==${fc.status}){
							var content =$("<option value='"+o.value+"' selected='selected' >"+o.label+"</option>");
							$("#status").append(content);
						}else{
							var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
							$("#status").append(content);
						}
					})
				},
				"json"
			);  
	})
	
</script>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">权限名称：</label>
			<div class="controls">
				<input id="funcname" name="funcname" class="input-xlarge required" type="text" value="${fc.funcname }" maxlength="50">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权限链接：</label>
			<div class="controls">
				<input id="funcurl" name="funcurl" class="input-xlarge " type="text" value="${fc.funcurl }" maxlength="255">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权限类型：</label>
			<div class="controls">
				<select id="functype"  name="functype" class="input-xlarge">
					
					<!--  <option  value="0">菜单</option>
					<option value="1">按钮</option>  -->
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<input id="sortnum" name="sortnum" class="input-xlarge " type="number" value="${fc.sortnum }" maxlength="11">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<select id="status" name="status" class="input-xlarge" >
					<option value="" selected="selected">--请选择状态--</option>
					<!-- <option value="0">正常</option>
					<option value="1">删除</option> -->
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<input id="funcnote" name="funcnote" class="input-xlarge " type="text" value="${fc.funcnote }" maxlength="255">
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>

	<script type="text/javascript">
	
	</script>

</body></html>