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
   <span class="con-span">公积金账号: </span><input class="easyui-textbox" type="text" id="accountno" name="accountno" style="width:166px;height:35px;line-height:35px;">
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
			url:'business/accumulationFund/getStatistics.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:5,
			pageList:[1,2,5],
			columns:[[
				{field:"customername", title:'客户名称'},
				{field:"idcard", title:'身份证号'},
				{field:"accountno", title:'公积金账号'},
				{field:"companyname", title:'所属公司'},
				{field:"counts", title:'公积金月数'},
				{field:"paymoney", title:'公积金总额'},
				{field:"proxyfee", title:'代理费用总额'},
				{field:"status", title:'状态', formatter:function(value, rowData, index){
					if(value==0) {
						return "正常";
					} else {
						return "不正常";
					}
				}}
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
			"accountno":$("#accountno").val(),
			"companyName":$("#companyName").val()
		})
	}
	/* 点击查询 */
	function search() {
		doSearch();
	}
	
	/* 点击导出 */
	function exportExcel() {
		var url = 'business/accumulationFund/exportStatistics.do';
		var xhr = new XMLHttpRequest();
		xhr.open('GET', url, true); // 也可以使用POST方式，根据接口
		xhr.responseType = "blob"; // 返回类型blob
		// 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
		xhr.onload = function() {
		// 请求完成
			if (this.status === 200) {
				// 返回200
				var blob = this.response;
				var reader = new FileReader();
				reader.readAsDataURL(blob); // 转换为base64，可以直接放入a标签 href
				reader.onload = function(e) {
				// 转换完成，创建一个a标签用于下载
					var a = document.createElement('a');
					a.download = 'data.xlsx';
					a.href = e.target.result;
					$("body").append(a); // 修复firefox中无法触发click
					a.click();
					$(a).remove();
				}
			}
		};
		// 发送ajax请求
		xhr.send()
	}
</script>
</html>