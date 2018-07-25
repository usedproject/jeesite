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
					$.post(
						"business/socialInsurance/edit.do?id="+${id},
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
		
		
		/* 自定义身份证号码验证 */
		jQuery.validator.addMethod("isIdCard", function(value, element) {   
		    var tel = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		    return this.optional(element) || (tel.test(value));
		}, "请正确填写您的身份证号码");
		
		
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
					<input type="text" class="input-xlarge" readonly="readonly" id="customerName" name="customerName" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td width="100px"><label class="control-label">身份证号：</label></td>
				<td>
					<input type="text" class="input-xlarge" readonly="readonly" id="idcard" name="idcard" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
			</tr>
			<tr>
				<td><label class="control-label">社保卡号：</label></td>
				<td>
					<input id="sbcard" name="sbcard" class="input-xlarge " type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">缴费基数：</label></td>
				<td><input id="basepay" name="basepay" class="input-xlarge " type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">应缴金额：</label></td>
				<td><input id="mustpay" name="mustpay" class="input-xlarge " type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">个人比率：</label></td>
				<td><input id="personratio" name="personratio" class="input-xlarge " type="text" value="" maxlength="30">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
			</tr>
			<tr>
				<td><label class="control-label">公司比率：</label></td>
				<td>
					<input id="companyratio" name="companyratio" class="input-xlarge " type="text" value="" maxlength="50">
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
				<td><label class="control-label">预交款日：</label></td>
				<td>
					<input id="paydate" name="paydate" class="input-xlarge required" type="date" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">代理费用：</label></td>
				<td>
					<input id="proxyfee" name="proxyfee" class="input-xlarge " type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			</tbody>
		</table>	

		<div class="form-actions" align="center">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>

	

</body>
	<script type="text/javascript">
	$(function(){
		/* 加载社保信息 */
		$.post("business/socialInsurance/selectAll.do","",function(data){
			$.each(data, function(index, val){
				if(val.id == ${id}) {
					$("#idcard").val(val.idcard);
					$("#sbcard").val(val.sbcard);
					$("#basepay").val(val.basepay);
					$("#mustpay").val(val.mustpay);
					$("#personratio").val(val.personratio);
					$("#companyratio").val(val.companyratio);
					$("#yanglao").val(val.yanglao);
					$("#yiliao").val(val.yiliao);
					$("#gongshang").val(val.gongshang);
					$("#shiye").val(val.shiye);
					$("#shengyu").val(val.shengyu);
					$("#paydate").val(val.paydate);
					$("#proxyfee").val(val.proxyfee);
					
					var idcard = val.idcard;
					/* 加载客户姓名 */
					$.post("business/customer/selectAll.do","",function(data){
						$.each(data, function(index, val){
							if(val.idcard == idcard) {
								$("#customerName").val(val.name)
							}
						})
					},"json")
				}
			})
		},"json")
		
	})
	
	
	</script>
</html>
