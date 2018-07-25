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
   <span class="con-span">社保卡号: </span><input class="easyui-textbox" type="text" id="sbcard" name="sbcard" style="width:166px;height:35px;line-height:35px;">
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
			url:'business/socialInsuranceRecord/list.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:5,
			pageList:[1,2,5],
			columns:[[
				{field:"customer", title:'客户名称',formatter:function(value, rowData, index){
						if(value!=null) {
							return value.name;
						} else {
							return "";
						}
					}
				},
				{field:"socialInsurance", title:'身份证号',formatter:function(value, rowData, index){
						if(value!=null) {
							return value.idcard;
						} else {
							return "";
						}
					}
				},
				{field:"sdcard", title:'社保卡号'},
				{field:"paymonth", title:'缴费时间'},
				{field:"yanglao", title:'养老保险'},
				{field:"yiliao", title:'医疗保险'},
				{field:"gongshang", title:'工商保险'},
				{field:"shiye", title:'失业保险'},
				{field:"shengyu", title:'生育保险'},
				{field:"paymoney", title:'缴费总金额'},
				{field:"status", title:'社保状态', formatter:function(value, rowData, index){
					if(value==0) {
						return "已交";
					} else {
						return "未交";
					}
				}}/* ,
				{field:"option", title:'操作',
					formatter: function(value,rowData,index){
						 return "<a href='javascript:void(0)' onclick='remove("+rowData.id+")'>删除   </a>"
		                    +"<a href='javascript:void(0)' onclick='edit("+rowData.id+")' >修改  </a>"
					}	
				}*/
			]] 
		})
	})
	
	function doSearch() {
		$("#tb").datagrid('load', {
			"customerName":$("#customerName").val(),
			"idcard":$("#idcard").val(),
			"sbcard":$("#sbcard").val()
		})
	}
	/* 点击查询 */
	function search() {
		doSearch();
	}
	
	/* 点击修改 */
	function edit(id) {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "添加社保信息",
            url : "business/socialInsuranceRecord/toEdit.do?id="+id
        })
	}
	
	
	
	/* 点击删除 */
	function remove(id) {
		$.post("business/socialInsuranceRecord/remove.do?id="+id, "", function(data){
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