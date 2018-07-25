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
	<!-- 引入easyui -->
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
	<!-- Baidu tongji analytics -->
	
	
	<meta name="decorator" content="default">
	<script type="text/javascript">
			
			$(function(){
				/* 页面加载获得角色列表 */
				$.post(
					"system/role/getroleList.do",
					{},
					function(data){
						var content="";
						$.each(data.result,function(i,o){
							content +=	"<input id='userRoleList'"+i+"  name='userRoleList' class='required' type='checkbox' value="+o.id+"><label for='userRoleList'"+i+">"+o.rolename+"</label>";
						})
						$("#userRole").html(content);
					},
					"json"
				);
				
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
				
				/* 表单校验 */
				$("#inputForm").validate({
					submitHandler: function(form){
						if(${editUser.id==1000 }){
							alert('admin用户不能修改');
							parent.closeTopWindow();
							return;
						}
						if($("#status").val()==""){
							alert("请选择状态")
							return ;
						}
						$.post(
							"system/user/add.do",
							$("#inputForm").serialize(),
							function(data){
								alert(data.message);
								parent.closeTopWindow();
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
			
				
			});
		
	</script>

</head>
<body>
	<form id="inputForm" class="form-horizontal"  novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="${editUser.id }">
		<div class="control-group" style="margin-top: 40px">
			<label class="control-label">用户名称：</label>
			<div class="controls">
				<input id="username" name="username" class="input-xlarge required" type="text" value="${editUser.username }" maxlength="50">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户密码：</label>
			<div class="controls">
				<c:if test="${editUser!=null }">
					<input id="id1" type="hidden" name="ids" value="${editUser.id }">
					<input id="password" name="password" class="input-xlarge required" type="text" value="${editUser.password }" readonly="readonly" maxlength="50">
				</c:if>
				<c:if test="${editUser==null }">
					<input id="password" name="password" class="input-xlarge required" type="text" value="" maxlength="50">
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电子邮件：</label>
			<div class="controls">
				<input id="email" name="email" class="input-xlarge " type="text" value="${editUser.email }" maxlength="50">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<input id="phone" name="phone" class="input-xlarge " type="text" value="${editUser.phone }" maxlength="13">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<input id="sortnum" name="sortnum" class="input-xlarge " type="text" value="${editUser.sortnum }" maxlength="11">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<div class="select2-container input-xlarge" id="s2id_status">
				<select id="status" name="status" class="input-xlarge  select2-offscreen" tabindex="-1">
					<option value="" >请选择状态</option>
					<!-- <option value="0">正常</option>
					<option value="1">删除</option> -->
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<span id="userRole">
				<!-- <input id="userRoleList1" name="userRoleList" class="required" type="checkbox" value="1"><label for="userRoleList1">系统管理员</label>
					
					<input id="userRoleList2" name="userRoleList" class="required" type="checkbox" value="2"><label for="userRoleList2">部门管理员</label>
					<input id="userRoleList3" name="userRoleList" class="required" type="checkbox" value="3"><label for="userRoleList3">test</label>--> 
				</span>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<input id="usernote" name="usernote" class="input-xlarge " type="text" value="${editUser.sortnum }" maxlength="255">
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${editUser==null }">
				<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="newvalidate()" value="保 存">&nbsp;
			</c:if>
			<c:if test="${editUser!=null }">
				<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="newvalidate()" value="修改">&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:window.close()">
		</div>
	</form>
	<c:if test="${editUser!=null }">
	<script type="text/javascript">
	/* //<!-- 无框架时，左上角显示菜单图标按钮。
		if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
			$("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
			$("#btnMenu").click(function(){
				top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=ff9a71c594b14065828ca71d9cf465d6', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
				//if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
			});
		}//--> */
		
		/* 根据id动态勾选角色状态 */
		//alert(${editUser.id});
		$(function(){
			$.post(
					"system/role/rolelist.do?userIds="+${editUser.id},
					{},
					function(data){
						$.each(data.result,function(i,o){
							var id=o.id;
							$(":checkbox[value='"+id+"']").prop("checked",true);
						})
					},
					"json"
			);
		});
	</script>
	</c:if>
</body></html>