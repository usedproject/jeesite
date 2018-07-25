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
<div class="conditions">
   <span class="con-span">客户名称: </span><input class="easyui-textbox" type="text" id="customerName" name="customerName" style="width:166px;height:35px;line-height:35px;">
   <span class="con-span">身份证号: </span><input class="easyui-textbox" type="text" id="idcard" name="idcard" style="width:166px;height:35px;line-height:35px;">
   <span class="con-span">银行卡号: </span><input class="easyui-textbox" type="text" id="paycard" name="paycard" style="width:166px;height:35px;line-height:35px;">
   <span class="con-span">公司名称: </span>
   <select id="companyName" name="companyName" style="width:166px;height:35px;line-height:35px;">
   	<option value="">--请选择--</option>
   </select>
    <a href="javascript:search();" class="easyui-linkbutton" iconCls="icon-search">统计</a>
    <a href="javascript:exportExcel();" class="easyui-linkbutton" iconCls="icon-export">导出</a>
</div>
<div>
	<table id="tb"></table>
</div>
</body>
<script type="text/javascript">
	$(function() {
		/* 加载数据字典中的记录 */
		$("#tb").datagrid({
			url:'business/salary/getStatistics.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:5,
			pageList:[1,2,5],
			columns:[[
				{field:"customerName", title:'客户名称'},
				{field:"idCard", title:'身份证号'},
				{field:"payCard", title:'银行卡号'},
				{field:"companyName", title:'所属公司'},
				{field:"months", title:'工资代发月数'},
				{field:"sal", title:'工资代发总额'},
				{field:"tax", title:'个人交税总额'},
				{field:"proxy", title:'代理费用总额'}
			]]
		})
		
		/* 加载公司名称 */
		$.post("business/company/selectAll.do", "", function(data){
			$.each(data.result, function(index, val){
				$("#companyName").append("<option value='"+val.id+"'>"+val.name+"</option>")
			})
		}, "json")
	})
	
	function doSearch() {
		$("#tb").datagrid('load', {
			"customerName":$("#customerName").val(),
			"idcard":$("#idcard").val(),
			"paycard":$("#paycard").val(),
			"companyName":$("#companyName").val()
		})
	}
	/* 点击查询 */
	function search() {
		doSearch();
	}
	
	

</script>
</html>