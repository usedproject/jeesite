<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath%>>
<!-- 引入easyui -->
<%@include file="../script.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="dg">
	
	</table>
</body>
<script type="text/javascript">
$("#dg").datagrid({
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
				 return "<a href='javascript:void(0)' onclick=\"selectSmsTemplate('"+rowData.subject+"','"+rowData.content+"',"+rowData.templateCode+")\">选择   </a>"
			}	
		}
	]]
})

	/* 定义选择的点击事件 */
	function selectSmsTemplate(subject,content,id){
		parent.enterSms(subject,content,id);
		parent.closeTopWindow();
	}
</script>
</html>