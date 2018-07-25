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
			//$("#name").focus();
			$("#inputForm").validate({
				/* 自定义远端的查询验证方法 */
				rules:{  
					name:{  
		                required:true,   
		                remote:{  
		                    type:"POST",  
		                    url:"business/company/check.do", //请求地址  
		                    data:{  
		                    	name:function(){ return $("#name").val(); }  
		                    }  
		                }  
		            }  
		        },  
		        messages:{  
		            name:{  
		                required:"用户名必填",  
		                remote:"用户名已存在"  
		            }  
		        },
		        /*------- 分割线  --------*/
				submitHandler: function(form){
					
					
				$.post(
						"business/company/add.do",
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
		}, "法人姓名为2-4位中文字符");
		
		/* 自定义 */
		
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
	
	<!-- <ul class="nav nav-tabs">
		<li><a href="../companyList/saved_resource.html">公司客户列表</a></li>
		<li class="active"><a href="saved_resource.html">公司客户添加</a></li>
	</ul> -->
	<br>
	<form id="inputForm" class="form-horizontal"  novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="">
	<!-- <script type="text/javascript">top.$.jBox.closeTip();</script> -->
	
		<table class="table table-bordered table-condensed" width="800px">
			<tbody><tr>
				<td width="100px"><label class="control-label">公司名称：</label></td>
				<td>
					<input id="name" name="name" class="input-xlarge required" type="text" value="" maxlength="100">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>
					<label class="control-label">统一信用号：</label>
				</td>
				<td>
					<input id="companyno" name="companyno" class="input-xlarge required creditcard" type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">电话：</label></td>
				<td><input id="telphone" name="telphone" class="input-xlarge required" type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">邮编：</label></td>
				<td><input id="zipcode" name="zipcode" class="input-xlarge required isZipCode" type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">法人：</label></td>
				<td><input id="owner" name="owner" class="input-xlarge required isName" type="text" value="" maxlength="30">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">身份证号：</label></td>
				<td><input id="idcard" name="idcard" class="input-xlarge required isIdCard" type="text" value="" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">手机：</label></td>
				<td><input id="phone" name="phone" class="input-xlarge required isPhone" type="text" value="" maxlength="13">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">性别：</label></td>
				<td><input id="sex" name="sex" class="input-xlarge " type="radio" value="男" checked="checked">男
					<input id="sex" name="sex" class="input-xlarge " type="radio" value="女" >女
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">电子邮件：</label></td>
				<td><input id="email" name="email" class="input-xlarge required email" type="text" value="" maxlength="50">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">公司性质：</label></td>
				<td><!-- <input id="ownership" name="ownership" class="input-xlarge required" type="text" value="" maxlength="150"> -->
					<select class="input-xlarge required" id="ownership" name="ownership">
						<option value="" selected="selected">---请选择公司性质---</option>
					</select>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">状态：</label></td>
				<td>
					<select id="status" name="status" class="input-xlarge required" tabindex="-1">
						<option value="" selected="selected">--请选择状态--</option>
						<!-- <option value="0">正常</option>
						<option value="1">删除</option> -->
					</select>
				</td>
				<td><label class="control-label">公司类别：</label></td>
				<td>
					<select id="companyType" name="companyType" class="input-xlarge required" tabindex="-1">
						<option value="">--请选择公司类别--</option>
						<!-- <option value="0">全业务客户</option>
						<option value="1">社保客户</option>
						<option value="2">公积金客户</option>
						<option value="3">工资代发客户</option>
						<option value="4">外包合作客户</option> -->
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">公司地址：</label></td>
				<td colspan="3"><textarea id="address" name="address" maxlength="256" class="input-xxlarge " rows="2"></textarea></td>
			</tr>
			
			<tr>
				<td><label class="control-label">备注：</label></td>
				<td colspan="3"><textarea id="remark" name="remark" maxlength="512" class="input-xxlarge " rows="2"></textarea></td>
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
		/* 获得用户状态栏，进行动态的添加 */
		  $.post(
			"system/dict/selectAll.do",
			{"name":"del_flag"},
			function(data){
				$.each(data,function(i,o){
					var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
					$("#status").append(content);
				})
			},
			"json"
		);  
		
		/* 公司类别下拉框动态加载 */
		  $.post(
					"system/dict/selectAll.do",
					{"name":"companyType"},
					function(data){
						$.each(data,function(i,o){
							var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
							$("#companyType").append(content);
						})
					},
					"json"
				);  
		
		/* 公司性质下拉框动态加载 */
		  $.post(
					"system/dict/selectAll.do",
					{"name":"companyProperty"},
					function(data){
						$.each(data,function(i,o){
							var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
							$("#ownership").append(content);
						})
					},
					"json"
				);  
	})
	
	
	</script>
</html>