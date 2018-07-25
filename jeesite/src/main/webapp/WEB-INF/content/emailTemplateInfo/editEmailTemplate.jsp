<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../script.html" %>
</head>
<body>
<div title="修改邮件模板" data-options="closable:false" class="basic-info" style="margin-left: auto; margin-right: auto" align="center">
	<form id="ff">
			<table style="border-collapse:separate; border-spacing:0px 20px;">
				<tr>
					<td>标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
					<td><input class="easyui-textbox" id="subject" name="subject" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
					<td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
					<td><input class="easyui-textbox" id="content" name="content" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
					<td>排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
					<td><input class="easyui-textbox" id="orderId" name="orderId" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="addBtn" data-options="selected:true">保存</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" class="easyui-linkbutton" id="resetBtn" data-options="selected:true">重置</a>
				</td>
				</tr>
			</table>
		</form>
</div>
</body>
<script type="text/javascript">
	$(function() {
		/* 加载当前id下的邮件模板记录 */
		$.post("system/emailTemplate/selectAllTemplate.do", "", function(data){
			$.each(data, function(index, val) {
				if(val.id == ${id}) {
					$("#subject").textbox('setValue', val.subject);
					$("#content").textbox('setValue', val.content);
					$("#orderId").textbox('setValue', val.orderId);
				}
			})
		}, "json")
		
		/* 点击保存 */
		$("#saveBtn").on('click', function() {
			$.post("system/listEmailTemplate/editTemplate.do?id="+${id}, $("#ff").serialize(), function(data){
				alert(data.message)
			}, "json")
		})
		
	})
	
	
	
</script>
</html>