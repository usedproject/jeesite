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
<script type="text/javascript">
/*------------------定义dosearch页面数据刷新功能----------------------------  */
function doSearch(){
	 $('#dg').datagrid('load',{
		 "username":$("#username").val(),
		 "phone":$("#phone").val(),
		 "status":$("#status").val()
	 });
}
	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		用户名： <input id="username" name="username" type="text" class="easyui-textbox" >
		手机号码： <input id="phone" name="phone" type="text" class="easyui-textbox" >
		状态： <select id="status" name="status"  style="height: 30px" >
			<option value="">------请选择状态-------</option>
		</select>
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:adduser()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteuser()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:edituser()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
		<a href="#" id="rolefunctionBtn" class="easyui-linkbutton" 
		data-options="iconCls:'icon-man',size:'large',plain:true">分配角色</a>
	</table>
</body>
	<script type="text/javascript">
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'system/user/getList.do',
        method:"post",
		toolbar:"#toolbar",
		rownumbers: true,  // 显示行号
		pagination:true,
		pageSize:1,
		pageList:[1,2],
    	/* loadFilter: function(data){
    		return{"total":data.result.total,"rows":data.result.rows}	
    	}, */
        columns:[[
        	{field:'id',title:'用户id',width:100,checkbox:true},
        	{field:'ids',title:'用户编号',width:100,
        		 formatter: function(value,row,index){
    				return row.id;
    			} 
        	},
    		{field:'username',title:'用户名',width:100},
    		{field:'password',title:'用户密码',width:100},
    		{field:'roles',title:'角色',width:100,
    			formatter: function(value,row,index){
    				var roleslist=new Array();
    				if (value&&value.length>0){
    					$.each(value,function(i,n){
    						roleslist.push(n.rolename)
    					})
    					return roleslist.join(",");
    				} else {
    					return "";
    				}
    			}
    		},
    		{field:'email',title:'电子邮件',width:100},
    		{field:'phone',title:'手机号码',width:100},
    		{field:'sortnum',title:'排序',width:100},
    		{field:'status',title:'状态',width:100,align:'right',
    			formatter: function(value,row,index){
    				if (value==0){
    					return "正常";
    				} else {
    					return "删除";
    				}
    			}
    		}
        ]]
    });
    /*-----------------分配角色按钮功能------------------------------  */
    $("#rolefunctionBtn").on("click",function(e){
    	e.preventDefault();
    	var check=$('#dg').datagrid("getChecked");
    	if(check&&check.length<1){
    		$.messager.alert('提示','请勾选用户!','info');
    		return;
    	}
    	//遍历数组，将数组的id信息封装到字符串中做为参数返回到新的jsp
    	var array=new Array();
    	$.each(check,function(i,n){
    		array.push(n.id);
    	})
    	//将数组用逗号隔开内部内容
    	var userIds= array.join(",");
    	
    	//将参数传递到新的jsp
    	parent.openTopWindow(
    			{"url":"system/role/toroleList.do?userIds="+userIds,
    			"title":"角色分配",
    			'onClose':function(){doSearch()}
    			})
    	/* parent.$("#topWindow").window({
			onBeforeClose: function () {
				doSearch();
			} 
 	});*/
    }); 
    
    /* 增加按钮的事件函数 */
    function adduser(){
    	parent.openTopWindow(
    			{"url":"system/user/toadduser.do",
    			"title":"增加用户",
    			'onClose':function(){doSearch()}
    			})
    	
    }
    
    /* 编辑按钮的事件函数 */
    function edituser(){
    	//获得被选中的用户信息
    	var check=$('#dg').datagrid("getChecked");
    	if(check&&check.length<1){
    		$.messager.alert('提示','请勾选用户!','info');
    		return;
    	}
    	if(check.length>1){
    		$.messager.alert('提示','只能勾选一个用户!','info');
    		return;
    	}
    	var id=check[0].id;
    	
    	parent.openTopWindow(
    			{"url":"system/user/toedituser.do?id="+id,
    			"title":"修改用户",
    			'onClose':function(){doSearch()}
    			})
    }
    
    /* 删除按钮点击事件 */
    function deleteuser(){
    	var check=$('#dg').datagrid("getChecked");
    	if(check&&check.length<1){
    		$.messager.alert('提示','请勾选用户!','info');
    		return;
    	}
    	//遍历数组，将数组的id信息封装到字符串中做为参数返回到新的jsp
    	var array=new Array();
    	$.each(check,function(i,n){
    		if(n.id==1000){
    			$.messager.alert('Warning','admin用户不能删除');
    			return;
    		}
    		array.push(n.id);
    	})
    	//将数组用逗号隔开内部内容
    	var userIds= array.join(",");
    	$.messager.confirm('Confirm','是否删除选中记录?',function(r){
    	    if (r){
    	    	$.post(
    	    			"system/user/delete.do",
    	    			{"userIds":userIds},
    	    			function(data){
    	    				alert(data.message);
    	    				doSearch();
    	    			},
    	    			"json"
    	    	);
    	    }
    	});
    }
    
    /* 动态加载状态栏 */
  		  $.post(
					"system/dict/selectAll.do",
					{"name":"del_flag"},
					function(data){
						$.each(data,function(i,o){
							var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
							$("#status").append(content);
						})
					},
					"json"
				);  
	</script>
</html>