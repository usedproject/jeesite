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
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
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
	<script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="static/common/mustache.min.js" type="text/javascript"></script>
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<script src="static/common/jeesite.js" type="text/javascript"></script>
	<meta name="decorator" content="default">
	<script type="text/javascript">
			$(function(){
				 /* 动态加载所属公司下拉框 */
				  $.post(
							"business/company/selectAll.do",
							{},
							function(data){
								$.each(data.result,function(i,o){
									var content =$("<option value='"+o.id+"' >"+o.name+"</option>");
									$("#companyid").append(content);
								})
							},
							"json"
						);  
			    
				/* 获得用户状态栏，进行动态的添加 */
				/*   $.post(
					"system/dict/selectAll.do",
					{"name":"del_flag"},
					function(data){
						$.each(data,function(i,o){
							var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
							$("#status").append(content);
						})
					},
					"json"
				);   */
				
				/* 表单校验 */
				$("#inputForm").validate({
					submitHandler: function(form){
						if($("#status").val()==""){
							alert("请选择状态")
							return ;
						}
						$.post(
							"business/customer/add.do",
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
				/* 自定义邮编验证 */
				jQuery.validator.addMethod("isZipCode", function(value, element) {   
				    var tel = /^[0-9]{6}$/;
				    return this.optional(element) || (tel.test(value));
				}, "请正确填写您的邮政编码");
				
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
	<br><br>
	<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="">
		<table class="table table-bordered table-condensed">
			<tbody><tr>
				<td><label class="control-label">客户名称：</label></td>
				<td>
					<input id="name" name="name" class="input-xlarge required isName" type="text" value="${name }" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">身份证号：</label></td>
				<td><input id="idcard" name="idcard" class="input-xlarge required isIdCard" type="text" value="${idcard }" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
		
			<tr>
				<td><label class="control-label">客户性别：</label></td>
				<td>
					<input id="sex" name="sex" class="input-xlarge " type="radio" value="男" checked="checked">男<input id="sex" name="sex" class="input-xlarge " type="radio" value="女" >女
				</td>
				<td><label class="control-label">出生年月：</label></td>
				<td>
					<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value="" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});">
				</td>
			</tr>
			<tr>
				<td><label class="control-label">手机号码：</label></td>
				<td>
					<input id="phone" name="phone" class="input-xlarge required isPhone" type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">电子邮件：</label></td>
				<td><input id="email" name="email" class="input-xlarge required email" type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">现在住址：</label></td>
				<td><input id="address" name="address" class="input-xlarge " type="text" value="" maxlength="100"></td>
				<td><label class="control-label">邮政编码：</label></td>
				<td><input id="zipcode" name="zipcode" class="input-xlarge isZipCode" type="text" value="" maxlength="20"></td>
			</tr>
			
			<tr>
				<td><label class="control-label">毕业学校：</label></td>
				<td><input id="school" name="school" class="input-xlarge " type="text" value="" maxlength="20"></td>
				<td><label class="control-label">所学专业：</label></td>
				<td><input id="specialty" name="specialty" class="input-xlarge " type="text" value="" maxlength="20"></td>
			</tr>
			
			<tr>
				<td><label class="control-label">毕业时间：</label></td>
				<td><input name="graduation" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value="" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});"></td>
				<td><label class="control-label">所属公司：</label></td>
				<td>
					<select id="companyid" name="companyid" class="input-xlarge  select2-offscreen" tabindex="-1">
						<option value="" selected="selected">请选择所属公司</option>
						
					</select>
				</td>
			</tr>
			
			
			<tr>
				<td><label class="control-label">客户类别：</label></td>

				<td>
					<select id="customerType" name="customerType" class="input-xlarge  select2-offscreen" tabindex="-1">
						<option value="0" >本公司员工</option>
						<option value="1" >代理公司</option>
						<option value="2" >个人客户</option>
						<option value="3" >外包客户</option>
					</select>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">代发工资：</label></td>

				<td><input type="checkbox" name="isSalary"></td>
			</tr>
			
			<tr>
				<td><label class="control-label">代缴社保：</label></td>
				<td><input type="checkbox" name="isshebao"></td>

				<td><label class="control-label">代缴公积金：</label></td>
				<td>
					<c:if test="${name!=null }">
						<input type="checkbox" name="isgongjijin" checked="checked">
					</c:if>
					<c:if test="${name==null }">
						<input type="checkbox" name="isgongjijin" >
					</c:if>
				</td>

			</tr>
			<tr>
				<td><label class="control-label">状态：</label></td>
				<td>
					<select name="status" class="input-xlarge  select2-offscreen" tabindex="-1">
						<option value="0">正常</option>
						<option value="1">封存</option>
					</select>
				</td>
				<td><label class="control-label">备注：</label></td>
				<td><input id="remark" name="remark" class="input-xlarge " type="text" value="" maxlength="256"></td>
			</tr>
			</table>
		<div class="form-actions" align="center">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>

</body></html>