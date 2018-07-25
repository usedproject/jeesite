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
					$.post("business/salary/add.do",$("#inputForm").serialize(),function(data){
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
					<input type="text" class="input-xlarge dlist" id="customerName" name="customerName" list="customer">
					<datalist id="customer" ></datalist>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td width="100px"><label class="control-label">身份证号：</label></td>
				<td>
					<input id="idcard" name="idcard" class="input-xlarge dlist" type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">银行卡号：</label></td>
				<td>
					<input id="paycard" name="paycard" class="input-xlarge dlist" type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">基本工资：</label></td>
				<td>
					<input id="basesalary" name="basesalary" class="input-xlarge " type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
			</tr>
			
			<tr>
				<td><label class="control-label">奖金：</label></td>
				<td><input id="bonuspay" name="bonuspay" class="input-xlarge " type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">加班费：</label></td>
				<td><input id="overtimepay" name="overtimepay" class="input-xlarge " type="text" />
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">社保扣费：</label></td>
				<td><input id="shebaopay" name="shebaopay" class="input-xlarge " type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">公积金扣费：</label></td>
				<td><input id="gongjijinpay" name="gongjijinpay" class="input-xlarge " type="text" />
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">应交税款：</label></td>
				<td><input id="taxpay" name="taxpay" class="input-xlarge " type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">应发工资：</label></td>
				<td><input id="totalpay" name="totalpay" class="input-xlarge " type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">实发工资：</label></td>
				<td><input id="mustpay" name="mustpay" class="input-xlarge " type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">代理费用：</label></td>
				<td>
					<input id="proxyfee" name="proxyfee" class="input-xlarge" type="text" maxlength="50" >
					<span class="help-inline"><font color="red">*</font> </span>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">状态：</label></td>
				<td>
				<select class="input-xlarge" id="status" name="status">
						<option value="" selected="selected">---请选择状态---</option>
						<option value="1">未交</option>
						<option value="0">已交</option>
					</select>
				</td>
				<td><label class="control-label">支付日期：</label></td>
				<td>
					<input id="paydate" name="paydate" class="input-xlarge " type="date" value="" maxlength="50">
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
		/* 加载客户的 名称*/
		$.post("business/customer/selectAll.do", "", function(data){
			$.each(data, function(index, val) {
				$("#customer").append("<option value='"+val.name+"'>"+val.name+"</option>")
			})
		}, "json")
		
		/* 客户姓名的离焦事件 */
		$("#customerName").on('blur', function() {
			$("#paycard").val("");
			$("#basesalary").val("");
			$("#bonuspay").val("");
			$("#overtimepay").val("");
			$("#shebaopay").val("");
			$("#gongjijinpay").val("");
			$("#taxpay").val("");
			$("#totalpay").val("");
			$("#mustpay").val("");
			$("#proxyfee").val("");
			$("#paydate").val("");
			var customerName = $("#customerName").val();
			$.post("business/customer/selectAll.do", "", function(data){
				$.each(data, function(index, val) {
					if(val.name == customerName) {
						$("#idcard").val(val.idcard)
						var idcard = val.idcard;
						$.post("business/salary/selectAll.do", "", function(data){
							$.each(data, function(index, val) {
								if(val.idcard == idcard) {
									$("#paycard").val(val.paycard);
									$("#basesalary").val(val.basesalary);
									$("#bonuspay").val(val.bonuspay);
									$("#overtimepay").val(val.overtimepay);
									$("#shebaopay").val(val.shebaopay);
									$("#gongjijinpay").val(val.gongjijinpay);
									$("#taxpay").val(val.taxpay);
									$("#totalpay").val(val.totalpay);
									$("#mustpay").val(val.mustpay);
									$("#proxyfee").val(val.proxyfee);
									$("#paydate").val(val.paydate);
								
								}
							})
						}, "json")
					}
				})
			}, "json")
		})
		
		
	})
	
	
	</script>
</html>