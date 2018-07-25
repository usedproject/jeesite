<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
		 "rolename":$("#rolename").val(),
	 });
}
	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		角色名称： <input id="rolename" name="rolename" type="text" class="easyui-textbox" >
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addrole()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteuser()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:edituser()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
	</table>
</body>
	<script type="text/javascript">
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'system/role/list.do',
        method:"post",
		//toolbar:"#toolbar",
		rownumbers: true,  // 显示行号
		pagination:true,
		pageSize:1,
		pageList:[1,2],
    	/* loadFilter: function(data){
    		return{"total":data.result.total,"rows":data.result.rows}	
    	}, */
		loadFilter: function(data){
			return data.result;	
		},
        columns:[[
        	{field:'id',title:'角色id',width:100,checkbox:true},
        	{field:'ids',title:'角色编号',width:100,
        		 formatter: function(value,row,index){
    				return row.id;
    			} 
        	},
    		{field:'rolename',title:'角色名称',width:100},
    		{field:'sortnum',title:'排序',width:100},
    		{field:'status',title:'状态',width:100,
    			formatter: function(value,row,index){
    				if (value==0){
    					return "正常";
    				} else {
    					return "删除";
    				}
    			}
    		},
    		{field:'rolenote',title:'备注',width:100},
        ]]
        
    });
   
    
    /* 增加按钮的事件函数 */
    function addrole(){
    	parent.openTopWindow(
    			{"url":"system/role/toaddrole.do",
    			"title":"增加角色",
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
    			{"url":"system/role/toeditRole.do?rid="+id,
    			"title":"修改角色",
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
    		if(n.id==1){
    			alert("系统管理员这个角色不能删除");
    			return ;
    		}else if(n.id==2){
    			alert("部门管理员这个角色不能删除");
    			return ;
    		}else{
    			array.push(n.id);
    		}
    	})
    	//将数组用逗号隔开内部内容
    	var roles= array.join(",");
    	$.messager.confirm('Confirm','是否删除选中记录?',function(r){
	    	$.post(
	    			"system/role/delete.do",
	    			{"roles":roles},
	    			function(data){
	    				alert(data.message);
	    				doSearch();
	    			},
	    			"json"
	    	);
    	})
    }
    
    /* 动态加载状态栏 */
  		  $.post(
					"system/dict/selectAll.do",
					{},
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