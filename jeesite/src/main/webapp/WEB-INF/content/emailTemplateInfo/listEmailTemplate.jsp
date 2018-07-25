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
		<span class="con-span">标题：<input class="easyui-textbox" id="subject" style="width:166px;height:35px;line-height:35px;" /></span>
		<a href="javascript:search();" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
		<a href="javascript:addTemplate();" class="easyui-linkbutton" iconCls="icon-add" data-options="selected:true">添加</a>
	</div>
	<table id="tb"></table>
</div>
</body>
<script type="text/javascript">
	$(function() {
		/* 加载数据字典中的记录 */
		$("#tb").datagrid({
			url:'company/system/listEmailTemplate.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:1,
			pageList:[1,2],
			columns:[[
				{field:"id", title:'编号'},
				{field:"subject", title:'标题'},
				{field:"content", title:'内容'},
				{field:"orderId", title:'排序'},
				{field:"option", title:'操作',
					formatter: function(value,rowData,index){
						 return "<a href='javascript:void(0)' onclick='removeEmailTemplate("+rowData.id+")'>删除   </a>"
		                    +"<a href='javascript:void(0)' onclick='editEmailTemplate("+rowData.id+")' >修改  </a>"
					}	
				}
			]]
		})
	})
	
	function doSearch() {
		$("#tb").datagrid('load', {
			"subject":$("#subject").val()
		})
	}
	
	/* 点击查询 */
	function search(){
		doSearch();
	}
	
	/* 点击添加 */
	function addTemplate() {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "添加邮件模板",
            url : "system/emailTemplate/toAddTemplate.do"
        })
	}
	
	/* 点击修改 */
	function editEmailTemplate(id) {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "编辑邮件模板",
            url : "system/emailTemplate/toEditTemplate.do?id="+id
        })
	}
	
	/* 点击删除 */
	function removeEmailTemplate(id) {
		$.post("system/emailTemplate/removeTemplate.do?id="+id, "", function(data){
			alert(data.message)
		}, "json")
	}
	
	/* 打开在父窗口中打开window的函数 */
    function openTopWindow(options){
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