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
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
					
		        /*------- 分割线  --------*/
				submitHandler: function(form){
					var formobj=document.getElementById("inputForm");
					var formdata=new window.FormData(formobj);
						$.ajax({
								url:"business/needJob/editNeedJob.do",
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
		<input id="id" name="id" type="hidden" value="${needJob.id }">
		<table class="table table-bordered table-condensed">
			<tbody><tr>
				<td><label class="control-label">需求名称：</label></td>
				<td>
					<input id="jobname" name="jobname" class="input-xlarge required" type="text" value="${needJob.jobname }" maxlength="100">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>
					<label class="control-label">需求职位：</label>
				</td>
				<td>
					<input id="jobtype" name="jobtype" class="input-xlarge required" type="text" value="${needJob.jobtype }" maxlength="100">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
		
			<tr>
				<td><label class="control-label">所属行业：</label></td>
				<td>
					<select name="industry" htmlescape="false" maxlength="2" class="input-xlarge  " tabindex="-1">
						<c:if test="${needJob.industry==0 }">
						<option value="0" selected="selected">软件互联网</option>
						<option value="1">建筑房地产</option>
						<option value="2">商业服务业</option>
						<option value="3">金融业</option>
						<option value="4">贸易批发零售</option>
						<option value="5">文体教育传媒</option>
						<option value="6">加工制造</option>
						<option value="7">农林牧副渔</option>
						<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==1 }">
						<option value="0" >软件互联网</option>
						<option value="1" selected="selected">建筑房地产</option>
						<option value="2">商业服务业</option>
						<option value="3">金融业</option>
						<option value="4">贸易批发零售</option>
						<option value="5">文体教育传媒</option>
						<option value="6">加工制造</option>
						<option value="7">农林牧副渔</option>
						<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==2 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  selected="selected">商业服务业</option>
							<option value="3">金融业</option>
							<option value="4">贸易批发零售</option>
							<option value="5">文体教育传媒</option>
							<option value="6">加工制造</option>
							<option value="7">农林牧副渔</option>
							<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==3 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  >商业服务业</option>
							<option value="3" selected="selected">金融业</option>
							<option value="4">贸易批发零售</option>
							<option value="5">文体教育传媒</option>
							<option value="6">加工制造</option>
							<option value="7">农林牧副渔</option>
							<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==4 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  >商业服务业</option>
							<option value="3" >金融业</option>
							<option value="4" selected="selected">贸易批发零售</option>
							<option value="5">文体教育传媒</option>
							<option value="6">加工制造</option>
							<option value="7">农林牧副渔</option>
							<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==5 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  >商业服务业</option>
							<option value="3" >金融业</option>
							<option value="4" >贸易批发零售</option>
							<option value="5" selected="selected">文体教育传媒</option>
							<option value="6">加工制造</option>
							<option value="7">农林牧副渔</option>
							<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==6 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  >商业服务业</option>
							<option value="3" >金融业</option>
							<option value="4" >贸易批发零售</option>
							<option value="5" >文体教育传媒</option>
							<option value="6" selected="selected">加工制造</option>
							<option value="7">农林牧副渔</option>
							<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==7 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  >商业服务业</option>
							<option value="3" >金融业</option>
							<option value="4" >贸易批发零售</option>
							<option value="5" >文体教育传媒</option>
							<option value="6" >加工制造</option>
							<option value="7" selected="selected">农林牧副渔</option>
							<option value="8">其他</option>
						</c:if>
						<c:if test="${needJob.industry==8 }">
							<option value="0" >软件互联网</option>
							<option value="1">建筑房地产</option>
							<option value="2"  >商业服务业</option>
							<option value="3" >金融业</option>
							<option value="4" >贸易批发零售</option>
							<option value="5" >文体教育传媒</option>
							<option value="6" >加工制造</option>
							<option value="7" >农林牧副渔</option>
							<option value="8" selected="selected">其他</option>
						</c:if>
					</select>
				</td>
				<td>
					<label class="control-label">发布公司：</label>
				</td><td>
					<select id="companyid" name="companyid" class="input-xlarge  required" style="width:270px" tabindex="-1">
						<!-- <option value="" label="">
							智递科技
						</option> -->
					</select>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				
			</tr>
			<tr>
				<td><label class="control-label">需求人数：</label></td>
				<td>
					<input id="needperson" name="needperson" class="input-xlarge required number" type="text" value="${needJob.needperson }" maxlength="11">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td>
					<label class="control-label">结算方式：</label>
					
				</td>
				<td>
					<select id="paytype" name="paytype"  style="width:270px" tabindex="-1">
						<c:if test="${needJob.paytype==0 }">
							<option value="0" selected="selected">月结</option>
							<option value="1">日结</option>
							<option value="2">其他</option>
						</c:if>
						<c:if test="${needJob.paytype==1 }">
							<option value="0" >月结</option>
							<option value="1" selected="selected">日结</option>
							<option value="2">其他</option>
						</c:if>
						<c:if test="${needJob.paytype==2 }">
							<option value="0" >月结</option>
							<option value="1" >日结</option>
							<option value="2" selected="selected">其他</option>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">需求单价：</label></td>
				<td>
					<input id="price" name="price" class="input-xlarge required number" type="text" value="${needJob.price }">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">工作地点：</label></td>
				<td>
					<input id="address" name="address" class="input-xlarge required" type="text" value="${needJob.address }" maxlength="2">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">开始日期：</label></td>
				<td>
					<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value='<fmt:formatDate value="${needJob.starttime }" pattern='yyyy-MM-dd HH:mm:ss'/>' onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td><label class="control-label">结束日期：</label></td>
				<td>
					<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" value='<fmt:formatDate value="${needJob.endtime }" pattern='yyyy-MM-dd HH:mm:ss'/>' onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});">
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
						
			<tr>
				<td><label class="control-label">信息类型：</label></td>
				<td>
					<select name="infotype" style="width:270px;" tabindex="-1" class="select2-offscreen">
						<c:if test="${needJob.infotype==1 }">
						<option value="1" selected="selected">合作公司招聘</option>
						<option value="0">本公司招聘</option>
						</c:if>
						<c:if test="${needJob.infotype==0}">
						<option value="1" >合作公司招聘</option>
						<option value="0" selected="selected">本公司招聘</option>
						</c:if>
					</select>
				</td>
				<td><label class="control-label">需求状态：</label></td>
				<td>
					<select name="status" style="width:270px;" tabindex="-1" class="select2-offscreen">
						<c:if test="${needJob.status==0 }">
							<option value="0" selected="selected">有效</option>
							<option value="1">无效</option>
						</c:if>
						<c:if test="${needJob.status==1}">
							<option value="0" >有效</option>
							<option value="1" selected="selected">无效</option>
						</c:if>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label class="control-label">需求详细：</label></td>
				<td>

					<textarea id="jobcontent" name="jobcontent" class="input-xlarge " rows="4" cols="30">${needJob.jobcontent }</textarea>
					
				</td>
				<td><label class="control-label">备注信息：</label></td>
				<td>

					<textarea id="remark" name="remark" class="input-xlarge " rows="4" cols="30">${needJob.remark }</textarea>
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
    			if(o.id==${needJob.companyid}){
    				var content =$("<option value='"+o.id+"' selected='selected' >"+o.name+"</option>");
    			}else{
    				var content =$("<option value='"+o.id+"'>"+o.name+"</option>");
    			}
				$("#companyid").append(content);
    		})
    	},
    	"json"
    );
	 	/* 工作内容的信息回显 */
	    var textobj=document.getElementById('jobcontent');  
		textobj.innerHTML=${needJob.jobcontent };  
	</script>
</html>