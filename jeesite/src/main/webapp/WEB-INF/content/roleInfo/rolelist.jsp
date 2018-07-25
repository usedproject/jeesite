<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath%>>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../script.html" %>
</head>
<body>
	<input type="hidden" id="userIds" name="userIds" value="${userIds }">
	
	<table id="dg"></table>
	<a href="javaScript::void(0)" class="easyui-linkbutton" id="saveBtn" iconCls="icon-man" style="width:100%;height:32px">分配角色</a>
</body>
<script type="text/javascript">
$('#dg').datagrid({
    url:'system/role/getroleList.do',
    method:"post",
	toolbar:"#toolbar",
	idField:"id",
	loadFilter: function(data){
		return data.result;	
	},
    columns:[[
    	{field:'id',title:'角色id',width:100,checkbox:true},
    	{field:'rolename',title:'角色名称'},
	    {field:'createtime',title:'创建时间'},
	    {field:'status',title:'Status',formatter: function(value,row,index){
			if (value==0){
				return "正常";
			} else if(value==1){
				return "已删除";
			} else{
				return "已禁用";
			}
		}}
	]],
	/* ---------数据加载完成之后，马上进行判断是否为单个用户，如果为单个用户，则获取用户角色信息 */
	onLoadSuccess:function(){
		var userIds=$("#userIds").val();
		if(userIds.indexOf(",")!=-1){
			return
		}else{
			$.post(
				"system/role/rolelist.do",
				{"userIds":userIds},
				function(data){
					$.each(data.result,function(i,n){
						$("#dg").datagrid("selectRecord",n.id);
					})
				}
			);
		}
	}
});



 

	$("#saveBtn").on("click",function(){
		//1、获取隐藏的字段
		var userIds=$("input[name='userIds']").val();
		//alert(userIds);
		//2、获得选中的选项框
		var check=$('#dg').datagrid("getChecked");
		var array=new Array();
    	$.each(check,function(i,n){
    		array.push(n.id);
    	})
    	var roles=array.join(",");
    	$.post(
    		"system/user_roles/save.do",
    		{"userIds":userIds,"roles":roles},
    		function(data){
    			if(data.success){
    				parent.$.messager.show({
    					title:'操作提示',
    					msg:'保存成功，本窗口将在5秒后关闭',
    					timeout:5000,
    					showType:'slide'
    				});
    				parent.closewindow();
    			}else{
    				$.messager.alert('警告','保存失败!','warn');
    			}
    		},
    		"json"
    	);
	})
</script>
</html>