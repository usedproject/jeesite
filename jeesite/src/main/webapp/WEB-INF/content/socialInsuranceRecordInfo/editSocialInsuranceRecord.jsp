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
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="static/common/mustache.min.js" type="text/javascript"></script> 
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<meta name="decorator" content="default">
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
		        /*------- 分割线  --------*/
				submitHandler: function(form){
					$.post("business/socialInsuranceRecord/edit.do?id="+${id},$("#inputForm").serialize(),function(data){
						if(data.success){
							alert(data.message);
						}
					},"json");
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
			})
		});
	</script>
	
	<style type="text/css">
		#table1{
			margin:1px;
			padding:1px;
		}
		#table1 tr td{
			margin:1px;
			padding:1px;
		}
	</style>
</head>
<body>
	<br>
	<form id="inputForm" class="form-horizontal"  novalidate="novalidate">
		<table class="table table-bordered table-condensed" width="800px">
			<tbody>
			<tr>
				<td><label class="control-label">客户姓名：</label></td>
				<td>
					<input type="text" class="input-xlarge required" id="customerName" name="customerName" readonly="readonly">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td width="100px"><label class="control-label">身份证号：</label></td>
				<td>
					<input id="idcard" name="idcard" class="input-xlarge " type="text" value="" maxlength="20" readonly="readonly">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">社保卡号：</label></td>
				<td>
					<input id="sdcard" name="sdcard" class="input-xlarge " type="text" value="" maxlength="20" readonly="readonly">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">养老保险：</label></td>
				<td><input id="yanglao" name="yanglao" class="input-xlarge " type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">医疗保险：</label></td>
				<td><input id="yiliao" name="yiliao" class="input-xlarge " type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">工商保险：</label></td>
				<td><input id="gongshang" name="gongshang" class="input-xlarge " type="text" />
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">失业保险：</label></td>
				<td><input id="shiye" name="shiye" class="input-xlarge " type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">生育保险：</label></td>
				<td><input id="shengyu" name="shengyu" class="input-xlarge " type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">缴费时间：</label></td>
				<td>
					<input id="paymonth" name="paymonth" class="input-xlarge" type="text" maxlength="50" >
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">缴费总金额：</label></td>
				<td>
					<input id="paymoney" name="paymoney" class="input-xlarge " type="text" value="" maxlength="50" placeholder="XX年XX月">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
		</tbody></table>	
		<div class="form-actions" align="center">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>
</body>
	<script type="text/javascript">
	$(function(){
		/* 通过id加载基本信息 */
		$.post("business/socialInsuranceRecord/selectAll.do", "", function(data){
			$.each(data, function(index, val){
				if(val.id == ${id}) {
					$("#sdcard").val(val.sdcard);
					$("#yanglao").val(val.yanglao);
					$("#yiliao").val(val.yiliao);
					$("#gongshang").val(val.gongshang);
					$("#shiye").val(val.shiye);
					$("#shengyu").val(val.shengyu);
					$("#paymoney").val(val.paymoney);
					$("#paymonth").val(val.paymonth);
					var sdcard = val.sdcard;
					/* 加载 身份证号*/
					$.post("business/socialInsurance/selectAll.do", "", function(data){
						$.each(data, function(index, val){
							if(val.sbcard == sdcard) {
								$("#idcard").val(val.idcard);
								/* 加载客户名称 */
								var idcard = val.idcard;
								$.post("business/customer/selectAll.do", "", function(data){
									$.each(data, function(index, val){
										if(val.idcard == idcard) {
											$("#customerName").val(val.name);
										}
									})
								}, "json")
							}
						})
					}, "json")
				}
			})
		}, "json")
	})
	
	
	</script>
</html>