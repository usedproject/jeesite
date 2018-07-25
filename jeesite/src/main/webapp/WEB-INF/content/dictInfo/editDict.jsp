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
<div title="修改数据字典" data-options="closable:false" class="basic-info" style="margin-left: auto; margin-right: auto" align="center">
		<form id="ff">
			<table style="border-collapse:separate; border-spacing:0px 20px;">
				<tr>
					<td>字典名称：</td>
					<td>
						<input id="name" name="name" list="dictName" style="width:300px;height:35px;line-height:35px;border:0.5px solid #aaaaaa" />
						<datalist id="dictName">
							<option value="">请选择</option>
						</datalist>
					</td>
				</tr>
				<tr>
					<td>存&nbsp;储&nbsp;&nbsp;值：</td>
					<td><input class="easyui-textbox" id="dictValue" name="value" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
					<td>显&nbsp;示&nbsp;&nbsp;值：</td>
					<td><input class="easyui-textbox" id="dictLabel" name="label" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
					<td>描&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
					<td><input class="easyui-textbox" id="dictSubscription" name="description" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
					<td>排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
					<td><input class="easyui-textbox" id="dictSort" name="sort" style="width:300px;height:35px;line-height:35px;" /></td>
				</tr>
				<tr>
					<td>状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
					<td>
						<select id="dictStatus" name="status" style="width:300px;height:35px;line-height:35px;">
							<option value="">请选择</option>
							<option value="0">删除状态</option>
							<option value="1">正常状态</option>
						</select>
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<a href="javascript:editDict();" class="easyui-linkbutton" id="editBtn" data-options="selected:true">保存</a>
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
		/* 根据id自动生成数据字典信息 */
		$.post("system/dict/listDictName.do", "", function(data){
			$.each(data, function(index, val) {
				$("#dictName").append("<option value='"+val.name+"'>"+val.name+"</option>")
			})
		}, "json");
		
		$.post("system/dict/selectAll.do", "", function(data) {
			$.each(data, function(index, val) {
				if(val.id == ${id}) {
					$("#name").val(val.name);
					$("#dictValue").textbox('setValue', val.value);
					$("#dictLabel").textbox('setValue', val.label);
					$("#dictSubscription").textbox('setValue', val.description);
					$("#dictSort").textbox('setValue', val.sort);
					$("#dictStatus").val(val.status);
				}
			})
		}, "json")
	})
	
	function editDict() {
		$.post("system/dict/editDict.do?id="+${id}, $("#ff").serialize(), function(data){
			alert(data.message);
		}, "json")
	}
</script>
</html>