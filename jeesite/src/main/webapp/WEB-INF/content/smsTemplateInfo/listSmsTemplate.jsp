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
<div>
	<div>
		<span class="con-span">模板编码：<input class="easyui-textbox" id="templateCode" style="width:166px;height:35px;line-height:35px;" /></span>
		<span class="con-span">主题：<input class="easyui-textbox" id="subject" style="width:166px;height:35px;line-height:35px;" /></span>
		<a href="javascript:search();" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
		<a href="javascript:addTemplate();" class="easyui-linkbutton" iconCls="icon-add" data-options="selected:true">添加</a>
	</div>
	<table id="tb"></table>
</div>
</body>
<script type="text/javascript">
	$(function() {
		/* 加载邮件模块的列表中的记录 */
		$("#tb").datagrid({
			url:'system/smsTemplate/listSmsTemplate.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:1,
			pageList:[1,2],
			columns:[[
				{field:"id", title:'编号'},
				{field:"templateCode", title:'模板编码'},
				{field:"subject", title:'标题'},
				{field:"content", title:'内容'},
				{field:"orderId", title:'排序'},
				{field:"option", title:'操作',
					formatter: function(value,rowData,index){
						 return "<a href='javascript:void(0)' onclick='removeSmsTemplate("+rowData.id+")'>删除   </a>"
		                    +"<a href='javascript:void(0)' onclick='editSmsTemplate("+rowData.id+")' >修改  </a>"
					}	
				}
			]]
		})
	})
	
	function doSearch() {
		$("#tb").datagrid('load', {
			"templateCode":$("#templateCode").val(),
			"subject":$("#subject").val()
		})
	}
	
	/* 点击查询 */
	function search() {
		doSearch();
	}
	
	/* 点击添加 */
	function addTemplate() {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "添加短信模板",
            url : "system/smsTemplate/toAddTemplate.do"
        })
	}
	
	/* 点击修改 */
	function editSmsTemplate(id) {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "修改短信模板",
            url : "system/smsTemplate/toEditTemplate.do?id="+id
        })
	}
	
	/* 点击删除 */
	function removeSmsTemplate(id) {
		$.post("system/smsTemplate/removeTemplate.do?id="+id, "", function(data){
			alert(data.message)
		}, "json")
	}
	
	function openTopWindow(options) {
		 options = !options ? {} :options;
	        options.width = !options.width ? 500 : options.width;
	        options.height = !options.height ? 400 : options.height;
	        options.url = !options.url ? "404.html" : options.url;
	        options.title = !options.title ? "" : options.title;

	        parent.$("#topWindow").window({
	            title : options.title,
	            width: options.width,
	            height: options.height,
	            content : "<iframe scrolling='yes' frameborder='0' border='0' height='100%' width='100%' src='"+options.url+"'></iframe>",
	            modal:true,
	            resizable:false,
	            collapsible:false
	        });
	}
</script>
</html>