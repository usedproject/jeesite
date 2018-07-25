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
					customerName:{  
		                required:true,   
		                remote:{  
		                    type:"POST",  
		                    url:"business/customer/check.do", //请求地址  
		                    data:{  
		                    	name:function(){ return $("#customerName").val(); }  
		                    }  
		                }  
		            }  
		        },  
		        messages:{  
		        	customerName:{  
		                required:"用户名必填",  
		                remote:"用户名不存在"  
		            }  
		        },
							
		        /*------- 分割线  --------*/
				submitHandler: function(form){
					var formobj=document.getElementById("inputForm");
					var formdata=new window.FormData(formobj);
						$.ajax({
								url:"business/person/edit.do",
								data:formdata,
								type:"post",
								cache:false,
								processData:false,
								contentType:false,
								success:function(data){
									alert(data.message);
									}
								})
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
	
	<br>
	<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="${person.id }">
	
		<table class="table table-bordered table-condensed">
			<tbody><tr>
				<td><label class="control-label">客户名称：</label></td>
				<td>
					<input type="text" id="customerName" name="customerName" value="${person.name }" list="names" class="input-xlarge required">
					<datalist id="names"></datalist>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">身份证号：</label></td>
				<td>
					<input id="idcard" name="idcard" list="idcards" class="input-xlarge required" type="text" value="${person.idcard }" maxlength="20">
					<datalist id="idcards"></datalist>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
		
			<tr>
				<td><label class="control-label">求职意向：</label></td>
				<td>
					<input id="jobintension" name="jobintension" class="input-xlarge required" type="text" value="${person.jobintension }" maxlength="256">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>
					<label class="control-label">工作类型：</label>
				</td>
				<td>
					<select name="jobtype" tabindex="-1" value="">
					<c:if test="${person.jobtype==0 }">
						<option value="0" selected="selected">兼职</option>
						<option value="1">全职</option>
						<option value="2">外派</option>
					</c:if>
					<c:if test="${person.jobtype==1 }">
						<option value="0" >兼职</option>
						<option value="1" selected="selected">全职</option>
						<option value="2">外派</option>
					</c:if>
					<c:if test="${person.jobtype==2 }">
						<option value="0" >兼职</option>
						<option value="1" >全职</option>
						<option value="2" selected="selected">外派</option>
					</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">期望月薪：</label></td>
				<td>
					<input id="forprice" name="forprice" class="input-xlarge required" type="text" value="${person.forprice }">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">期望工作地：</label></td>
				<td>
					<input id="foraddress" name="foraddress" class="input-xlarge " type="text" value="${person.foraddress }" maxlength="20">
				</td>
			</tr>
			<tr>
				<td><label class="control-label">工作经历：</label></td>
				<td colspan="3"><textarea id="worked" name="worked" maxlength="256" class="input-xxlarge " value="${person.worked }" rows="2"></textarea></td>
			</tr>
			<tr>
				<td><label class="control-label">自我介绍：</label></td>
				<td colspan="3">
					<textarea id="personinfo" name="personinfo" maxlength="256" class="input-xxlarge " value="${person.personinfo }" rows="3"></textarea>
				</td>
			</tr>
						
			<tr>
				<td><label class="control-label">个人简历：</label></td>
				<td colspan="3">
					<input id="resumeurl" name="resume" maxlength="256" class="input-xlarge " type="file" >
					<input type="hidden" name="url" value="${person.resumeurl }">
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">备注信息：</label></td>
				<td colspan="3">
					<textarea id="remark" name="remark" maxlength="256" class="input-xxlarge " value="${person.remark }" rows="2"></textarea>
				</td>
			</tr>
		</tbody></table>		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>


</body>
	<script type="text/javascript">
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
		/* 离焦事件 */ 
	     $("#idcard,#customerName").on("blur",function(){
	    	if(this.value==""){
	    		return
	    	};
	    	$.post(
	    		"business/person/selectByIdOrName.do",
	    		{"name" : $("#customerName").val(),
	    		"idcard":$("#idcard").val()	
	    		},
	    		function(data){
	    			if(data.success){
	    				$("#customerName").val(data.result.name);
	    				$("#idcard").val(data.result.idcard);
	    			}else{
	    				$.messager.confirm('Confirm','该用户不存在，是否进行页面跳转添加该用户?',function(r){
	    				    if (r){
	    						window.location.href="business/customer/toAddCustomer.do?name="+$("#customerName").val()+"&idcard="+$("#idcard").val();
	    				    }
	    				});
	    			}
	    		},
	    		"json"
	    	);
	    }) 
	</script>
</html>