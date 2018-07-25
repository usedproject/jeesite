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
					$.post("business/socialInsuranceRecord/add.do",$("#inputForm").serialize(),function(data){
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
					<input id="idcard" name="idcard" class="input-xlarge dlist" type="text" value="" maxlength="20" list="idcards">
					<datalist id="idcards" ></datalist>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">社保卡号：</label></td>
				<td>
					<input id="sbcard" name="sdcard" class="input-xlarge dlist" type="text" value="" maxlength="20" list="sbcards">
					<datalist id="sbcards" ></datalist>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">所属公司：</label></td>
				<td>
					<input id="company" name="company" class="input-xlarge " type="text" value="" maxlength="20" readonly="readonly">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
			</tr>
			
			<tr>
				<td><label class="control-label">手机号码：</label></td>
				<td><input id="phone" name="phone" class="input-xlarge " type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">现在住址：</label></td>
				<td><input id="address" name="address" class="input-xlarge " type="text" />
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
				<td><label class="control-label">养老保险：</label></td>
				<td><input id="yanglao" name="yanglao" class="input-xlarge " type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">缴费状态：</label></td>
				<td>
					<select class="input-xlarge" id="status" name="status">
						<option value="" selected="selected">---请选择状态---</option>
						<option value="1">未交</option>
						<option value="0">已交</option>
					</select>
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
					<input id="paymoney" name="paymoney" class="input-xlarge " type="text" value="" maxlength="50">
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
		
		/* 加载身份证号,社保卡号 */
		$.post("business/socialInsurance/selectAll.do", "", function(data){
			$.each(data, function(index, val) {
				$("#idcards").append("<option value='"+val.idcard+"'>"+val.idcard+"</option>");
				$("#sbcards").append("<option value='"+val.sbcard+"'>"+val.sbcard+"</option>");
			})
		}, "json")
		
		/* 客户姓名的离焦事件 */
		$("#customerName").on('blur', function() {
			var customerName = $("#customerName").val();
			$.post("business/customer/selectAll.do", "", function(data){
				$.each(data, function(index, val) {
					if(val.name == customerName) {
						$("#company").val(val.companyid);
						$("#phone").val(val.phone);
						$("#address").val(val.address);
						$("#idcard").val(val.idcard)
						var idcard = val.idcard;
						$.post("business/socialInsurance/selectAll.do", "", function(data){
							$.each(data, function(index, val) {
								if(val.idcard == idcard) {
									$("#sbcard").val(val.sbcard);
									$("#yiliao").val(val.yiliao);
									$("#gongshang").val(val.gongshang);
									$("#shiye").val(val.shiye);
									$("#shengyu").val(val.shengyu);
									$("#yanglao").val(val.yanglao);
								}
							})
						}, "json")
					}
				})
			}, "json")
		})
		
		/* 身份证号离焦事件 */
		$("#idcard").on('blur', function() {
			var idcard = $("#idcard").val();
			$.post("business/socialInsurance/selectAll.do", "", function(data){
				$.each(data, function(index, val) {
					if(val.idcard == idcard) {
						$.post("business/customer/selectAll.do", "", function(data){
							$.each(data, function(index, val) {
								if(val.idcard == idcard) {
									$("#customerName").val(val.name);
									$("#company").val(val.companyid);
									$("#phone").val(val.phone);
									$("#address").val(val.address);
								}
							})
						}, "json")
						$("#sbcard").val(val.sbcard);
						$("#yiliao").val(val.yiliao);
						$("#gongshang").val(val.gongshang);
						$("#shiye").val(val.shiye);
						$("#shengyu").val(val.shengyu);
						$("#yanglao").val(val.yanglao);
					}
				})
			}, "json")
		})
		
		/* 社保卡号离焦事件 */
		$("#sbcard").on('blur', function() {
			var sbcard = $("#sbcard").val();
			$.post("business/socialInsurance/selectAll.do", "", function(data){
				$.each(data, function(index, val) {
					if(val.sbcard == sbcard) {
						$("#idcard").val(val.idcard);
						var idcard = val.idcard;
						$("#yiliao").val(val.yiliao);
						$("#gongshang").val(val.gongshang);
						$("#shiye").val(val.shiye);
						$("#shengyu").val(val.shengyu);
						$("#yanglao").val(val.yanglao);
						
						$.post("business/customer/selectAll.do", "", function(data){
							$.each(data, function(index, val) {
								if(val.idcard == idcard) {
									$("#customerName").val(val.name);
									$("#company").val(val.companyid);
									$("#phone").val(val.phone);
									$("#address").val(val.address);
								}
							})
						},"json")
					}
				})
			},"json")
		})
	})
	
	
	</script>
</html>