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
    <a href="javascript:add();" class="easyui-linkbutton" iconCls="icon-add">添加</a>
    <a href="static2/model/shebaoModel.xls" class="easyui-linkbutton" iconCls="icon-download" >模板下载</a>
    <a href="javascript:importExcel();" class="easyui-linkbutton" iconCls="icon-import">导入</a>
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
			url:'business/socialInsurance/getList.do',
	        method:"post",
			toolbar:"#toolbar",
			rownumbers: true,  // 显示行号
			pagination:true,
			pageSize:5,
			pageList:[1,2,5],
			columns:[[
				{field:"option", title:'操作',
					formatter: function(value,rowData,index){
						 return "<a href='javascript:void(0)' onclick='remove("+rowData.id+")'>删除   </a>"
		                    +"<a href='javascript:void(0)' onclick='edit("+rowData.id+")' >修改  </a>"
					}	
				},
				{field:"customerName", title:'客户名称'},
				{field:"idcard", title:'身份证号'},
				{field:"sbcard", title:'社保卡号'},
				{field:"basepay", title:'缴费基数'},
				{field:"mustpay", title:'应缴金额'},
				{field:"personratio", title:'个人比率'},
				{field:"yanglao", title:'养老保险'},
				{field:"yiliao", title:'医疗保险'},
				{field:"gongshang", title:'工商保险'},
				{field:"shiye", title:'失业保险'},
				{field:"shengyu", title:'生育保险'},
				{field:"paydate", title:'预交款日'},
				{field:"proxyfee", title:'代理费用'}
				
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
	
	/* 点击添加 */
	function add() {
		openTopWindow({
            width : 1000,
            height : 600,
            title : "添加社保信息",
            url : "business/socialInsurance/toAdd.do"
        })
	}
	
	/* 点击修改 */
	function edit(id) {
		openTopWindow({
			width:1000,
			height:600,
			title:"修改社保信息",
			url:"business/socialInsurance/toEdit.do?id="+id
		})
	}
	
	/* 点击删除 */
	function remove(id) {
		$.post("business/socialInsurance/remove.do?id="+id, "", function(data){
			alert(data.message)
		}, "json")
	}
	
	 /* 导入功能实现 */
    function importExcel(){
    	parent.openTopWindow(
    			{"url":"business/socialInsurance/toSelectExcel.do",
    			"title":"选择对应的Excel模板",
    			'onClose':function(){doSearch()}
    			})
    }
	
	/* 导出的函数定义 */
    function exportExcel(){
		var customerName=$("#customerName").val();
		var idcard=$("#idcard").val();
		var sbcard=$("#sbcard").val();
    	var url = 'business/socialInsurance/exportToExcel.do?customerName='+customerName+'&idcard='+idcard+'&sbcard='+sbcard;
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
			    	a.download = '社保信息表.xlsx';
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