<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href=<%=basePath%>>
	<title>用户信息管理 - Powered By JeeSite</title>
	<%@include file="../script.html" %>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<!-- <script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script> -->
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<!-- <script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script> -->
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="../static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="../static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
	<script src="static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<script src="static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<!-- <script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="static/common/mustache.min.js" type="text/javascript"></script> -->
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<script src="static/common/jeesite.js" type="text/javascript"></script>
	<meta name="decorator" content="default">
	
	<script type="text/javascript">
	$(function(){
				/* 离焦事件 */ 
			     $("#idcard,#name").on("blur",function(){
			    	if(this.value==""){
			    		return
			    	};
			    	$.post(
			    		"business/accumulationFund/selectByIdOrName.do",
			    		{"name" : $("#name").val(),
			    		"idcard":$("#idcard").val()	
			    		},
			    		function(data){
			    			if(data.success){
			    				$("#id").val(data.result.id);
			    				$("#name").val(data.result.name);
			    				$("#idcard").val(data.result.idcard);
			    				$("#accountno").val(data.result.accountno);
			    				$("#paymoney").val(data.result.paymoney);
			    			}else{
			    				$.messager.confirm('Confirm','该用户不存在，是否进行页面跳转添加该用户?',function(r){
			    				    if (r){
			    						window.location.href="business/customer/toAddCustomer.do?name="+$("#name").val()+"&idcard="+$("#idcard").val();
			    				    }
			    				});
			    			}
			    		},
			    		"json"
			    	);
			    }) 
			    /* datalist动态加载时间 */
				$.post(
					"business/accumulationFund/selectAll.do",
					{},
					function(data){
						$.each(data.result,function(i,o){
							var idcards=$("<option value='"+o.idcard+"'>");
							var names=$("<option value='"+o.name+"'>");
							$("#idcards").append(idcards);
							$("#names").append(names);
						})
					},
					"json"
				);
				/* 表单校验 */
				$("#inputForm").validate({
					submitHandler: function(form){
						
						$.post(
							"business/accumulationFund/add.do",
							$("#inputForm").serialize(),
							function(data){
								alert(data.message)
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
					},
					
				});
				/* 自定义时间格式验证 */
				jQuery.validator.addMethod("isDate", function(value, element) {   
				    var tel = /^((19|20)\d{2})-(0?[123456789]|1[012])$/;
				    return this.optional(element) || (tel.test(value));
				}, "请按照格式填写时间，如2017-12");
				
				/* 自定义手机号码验证 */
				jQuery.validator.addMethod("isPhone", function(value, element) {   
				    var tel = /^1[34578][0-9]{9}$/;
				    return this.optional(element) || (tel.test(value));
				}, "请正确填写您的手机号码");
				
				/* 自定义身份证号码验证 */
				jQuery.validator.addMethod("isIdCard", function(value, element) {   
				    var tel = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
				    return this.optional(element) || (tel.test(value));
				}, "请正确填写您的身份证号码");
				
				/* 自定义法人姓名为汉字2-4 */
				jQuery.validator.addMethod("isName", function(value, element) {   
				    var tel = /^[\u4E00-\u9FA5]{2,32}$/;
				    return this.optional(element) || (tel.test(value));
				}, "客户姓名为2-4位中文字符");
	});
		
	</script>

</head>
<body>
	
	<br><br><br>
	<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="${af.id }">
		<div class="control-group">
			<label class="control-label">客户名称：</label>
			<div class="controls">
				<input id="name" name="name" list="names" class="input-xlarge required" type="text" value="${af.name }" maxlength="20">
				<datalist id="names">
				</datalist>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<input id="idcard" list="idcards" name="idcard" class="input-xlarge required" type="text" value="${af.idcard }" maxlength="20">
				<datalist id="idcards">
				</datalist>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公积金号：</label>
			<div class="controls">
				<input id="accountno" name="accountno" class="input-xlarge required" type="text" value="${af.accountno }" maxlength="20">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缴费期间：</label>
			<div class="controls">
				<input id="paydate" name="paydate" class="input-xlarge required isDate" type="text" value="${af.paydate }" maxlength="20">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缴费金额：</label>
			<div class="controls">
				<input id="paymoney" name="paymoney" class="input-xlarge required" type="text" value="${af.paymoney }">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理费：</label>
			<div class="controls">
				<input id="proxyfee" name="proxyfee" class="input-xlarge " type="text" value="${af.proxyfee }">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<select name="status" class="input-xlarge  select2-offscreen" tabindex="-1">
						<option value="0">已交
						</option><option value="1">未交
					</option></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<input id="remark" name="remark" class="input-xlarge " type="text" value="${af.remark }" maxlength="256">
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>


</body></html>