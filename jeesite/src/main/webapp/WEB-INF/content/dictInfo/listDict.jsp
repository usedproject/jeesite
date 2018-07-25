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
<%@include file="../script.html" %>
<title>Insert title here</title>
</head>
<body>
<div>
	<div>
		<span class="con-span">字典名称：<input class="easyui-textbox" id="dictName" style="width:166px;height:35px;line-height:35px;" /></span>
		状态：<select id="dictStatus" style="width:166px;height:35px;line-height:35px;">
				<option value="">请选择</option>
			</select>
		<a href="javascript:search();" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
		<a href="javascript:addDict();" class="easyui-linkbutton" iconCls="icon-add" data-options="selected:true">添加</a>
	</div>
<table id="tb"></table>
</div>
</body>
<script type="text/javascript">
	$(function() {
		/* 加载数据字典中的记录 */
		$("#tb").datagrid({
			url:'system/dict/getList.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:5,
			pageList:[1,2,5,10],
			columns:[[
				{field:"id", title:'ID号'},
				{field:"name", title:'字典名称'},
				{field:"value", title:'存储值'},
				{field:"label", title:'显示值'},
				{field:"description", title:'描述'},
				{field:"sort", title:'序号'},
				{field:"parentid", title:'上级'},
				{field:"status", title:'状态'},
				{field:"option", title:'操作',
					formatter: function(value,rowData,index){
						 return "<a href='javascript:void(0)' onclick='removeDict("+rowData.id+")'>删除   </a>"
		                    +"<a href='javascript:void(0)' onclick='editDict("+rowData.id+")' >修改  </a>"
					}	
    			}
			]]
		})
		
		/* 展示数据字典的状态字段的下拉列表 */
		$.post("system/dict/listDictStatus.do", "", function(data) {
			$.each(data, function(index, val){
				$("#dictStatus").append("<option value='"+val.status+"'>"+val.status+"</option>")
			})
		}),
		"json"
	})
	function doSearch() {
		$('#tb').datagrid('load', {
			"dictName": $("#dictName").val(),
			"dictStatus": $("#dictStatus").val()
 		});
	}
	
	/* 点击查询按钮 */
	function search() {
		doSearch();
	}
	
	/* 点击添加 按钮*/
	function addDict() {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "添加数据字典",
            url : "system/dict/toAddDict.do"
        })
	}
	
	/* 点击修改 */
	function editDict(id) {
		openTopWindow({
			width:1000,
			height:600,
			title:"修改数据字典",
			url:"system/dict/toEditDict.do?id="+id
		})
	}
	
	/* 点击删除 */
	function removeDict(id) {
		$.post("system/dict/removeDict.do?id="+id, "", function(data){
			alert(data.message);
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