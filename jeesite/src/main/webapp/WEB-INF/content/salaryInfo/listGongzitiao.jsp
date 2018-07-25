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
    <a href="javascript:search();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
</div>
<div>
	<table id="tb"></table>
</div>
</body>
<script type="text/javascript">
	$(function() {
		/* 加载数据字典中的记录 */
		$("#tb").datagrid({
			url:'business/salary/listGongzitiao.do',
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
				{field:"payDate", title:'支付日期'},
				{field:"baseSalary", title:'基本工资'},
				{field:"bonusPay", title:'奖金'},
				{field:"overtimePay", title:'加班费'},
				
				{field:"yanglao", title:'养老保险'},
				{field:"yiliao", title:'医疗保险'},
				{field:"gongshang", title:'工商保险'},
				{field:"shiye", title:'失业保险'},
				{field:"shengyu", title:'生育保险'},
				
				{field:"totalPay", title:'应发工资'},
				{field:"mustPay", title:'实发工资'},
				{field:"proxyFee", title:'代理费用'}
				
			]]
		})
	})
	
	function doSearch() {
		$("#tb").datagrid('load', {
			"customerName":$("#customerName").val(),
			"idcard":$("#idcard").val()
		})
	}
	/* 点击查询 */
	function search() {
		doSearch();
	}
</script>
</html>