<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0066)http://localhost:8080/jeesite/a/company/system/systemFunction/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>菜单管理 - Powered By JeeSite</title>
	<base href=<%=basePath%>>
	<%@include file="../script.html" %>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<!-- <script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script> -->
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
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
		        /*------- 分割线  --------*/
				submitHandler: function(form){
					var formobj=document.getElementById("inputForm");
					var formdata=new window.FormData(formobj);
						$.ajax({
								url:"business/personJob/edit.do",
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
		})
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
	
	<br><br>
	<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="${personJob.id }">
		<table class="table table-bordered table-condensed">
			<tbody><tr>
				<td><label class="control-label">客户名称：</label></td>
				<td>
					<input type="text" id="name" name="name" list="names" value="${personJob.name }" class="input-xlarge required">
					<span class="help-inline"><font color="red">*</font> </span>
					<datalist id="names">
					
				</td>
				<td><label class="control-label">身份证号：</label></td>
				<td>
					<input id="idcard" name="idcard" list="idcards" class="input-xlarge required" type="text" value="${personJob.idcard }" maxlength="20">
					<span class="help-inline"><font color="red">*</font> </span>
					<datalist id="idcards">
					
				</td>
			</tr>
		
			<tr>
				<td><label class="control-label">合作公司：</label></td>
				<td>
					<select id="companyid" name="companyid" class="input-xlarge required select2-offscreen" tabindex="-1">
					</select>
				</td>
				<td>
					<label class="control-label">工作类型：</label>
				</td><td>
					<select name="jobtype" style="width:280px;" tabindex="-1" class="select2-offscreen">
						<c:if test="${personJob.jobtype==0 }">
							<option value="0" selected="selected">兼职</option>
							<option value="1">全职</option>
							<option value="2">外派</option>
						</c:if>
						<c:if test="${personJob.jobtype==1 }">
							<option value="0" >兼职</option>
							<option value="1" selected="selected">全职</option>
							<option value="2">外派</option>
						</c:if>
						<c:if test="${personJob.jobtype==2 }">
							<option value="0" >兼职</option>
							<option value="1" >全职</option>
							<option value="2" selected="selected">外派</option>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">公司单价：</label></td>
				<td>
					<input id="companyprice" name="companyprice" class="input-xlarge required number" type="text" value="${personJob.companyprice }">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">个人单价：</label></td>
				<td>
					<input id="personprice" name="personprice" class="input-xlarge required number" type="text" value="${personJob.personprice }">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">开始时间：</label></td>
				<td>
					<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value="<fmt:formatDate value='${personJob.starttime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});" style="width:270px;">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">结束时间：</label></td>
				<td>
					<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value="<fmt:formatDate value='${personJob.endtime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});" style="width:270px;">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">合同上传：</label></td>
				<td>
					<input  name="contract"  class="input-xlarge " type="file" >
					<input id="contracturl" name="contracturl" type="hidden" value="${personJob.contracturl }" >
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">合作状态：</label></td>
				<td>
					<select name="status" style="width:280px;" tabindex="-1" class="select2-offscreen">
						<c:if test="${personJob.status==0 }">
							<option value="0" selected="selected">正常</option>
							<option value="1">停止</option>
							<option value="2">结束</option>
						</c:if>
						<c:if test="${personJob.status==1 }">
							<option value="0" >正常</option>
							<option value="1" selected="selected">停止</option>
							<option value="2">结束</option>
						</c:if>
						<c:if test="${personJob.status==2 }">
							<option value="0" >正常</option>
							<option value="1" >停止</option>
							<option value="2" selected="selected">结束</option>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">工作内容：</label></td>
				<td colspan="3"><textarea id="jobcontent" name="jobcontent" maxlength="256" class="input-xxlarge " rows="2">${person.jobcontent }</textarea></td>
			</tr>
			<tr>
				<td><label class="control-label">备注信息：</label></td>
				<td colspan="3">
					<textarea id="remark" name="remark" maxlength="256" class="input-xxlarge " rows="2">${person.remark }</textarea>
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
	 /* 动态加载公司列表 */
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
	 
    /* datalist动态加载时间 */
	$.post(
		"business/customer/selectAll.do",
		{},
		function(data){
			$.each(data,function(i,o){
				var idcards=$("<option value='"+o.idcard+"'>");
				var names=$("<option value='"+o.name+"'>");
				$("#idcards").append(idcards);
				$("#names").append(names);
			})
		},
		"json"
	);
    
    /* 离焦事件 */
    $("#name,#idcard").on("blur",function(){
    	var customerName=$("#name").val();
    	var idcard=$("#idcard").val();
    	if(this.value==""){
    		return
    	}
    	$.post(
	    		"business/customer/selectByIdOrName.do",
	    		{"name" :customerName,
	    		"idcard":idcard	
	    		},
	    		function(data){
	    			if(data.success){
	    				$("#name").val(data.result.name);
	    				$("#idcard").val(data.result.idcard);
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
	</script>
</html>