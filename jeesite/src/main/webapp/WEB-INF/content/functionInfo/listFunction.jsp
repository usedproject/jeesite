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
<%@include file="../static.html" %>
<%@ include file="../script.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		/* 进行页面加载函数 */
		$(function(){
			$('#contentTable').datagrid({
			    url:'system/function/getList.do',
			    method:"post",
				toolbar:"#toolbar",
				idField:"id",
				pagination:true,
				pageSize:5,
				pageList:[5,10],
				loadFilter: function(data){
					return data.result;	
				},
			    columns:[[
			    	{field:'id',title:'菜单id',width:150,checkbox:true},
			    	{field:'ids',title:'菜单编号',width:150,
			    		formatter: function(value,row,index){
				    		if(row!=null){
				    			return row.id;
				    		}else{
				    			return "";
				    		}
				    	}
			    	},
			    	{field:'funcname',width:150,title:'菜单名称'},
				    {field:'funcurl',width:150,title:'菜单链接'},
				    {field:'parentfunction',width:150,title:'父菜单名称',
				    	formatter: function(value,row,index){
				    		if(value!=null){
				    			return value.funcname;
				    		}else{
				    			return "";
				    		}
				    	}
				    },
				    {field:'sortnum',width:150,title:'排序'},
				    {field:'status',title:'Status',width:150,
				    	formatter: function(value,row,index){
						if (value==0){
							return "正常";
						} else if(value==1){
							return "已删除";
						} else{
							return "已禁用";
						}
					}}
				]]
			});
		})
		
		/* 定义搜索按钮的功能 */
		function doSearch(){
			 $('#contentTable').datagrid('load',{
				"funcname":$("#funcname").val()
			 });
		}
		
		  /* 增加按钮的事件函数 */
	    function addFunction(){
			window.location.href="system/function/toAddFunction.do";	    	
	    }
	    
	    /* 编辑按钮的事件函数 */
	    function editFunction(){
	    	//获得被选中的用户信息
	    	var check=$('#contentTable').datagrid("getChecked");
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
	    			{"url":"system/user/toeditFunction.do?id="+id,
	    			"title":"修改功能菜单",
	    			'onClose':function(){
	    				doSearch()
	    				}
	    			})
	    }
	    
	    /* 删除按钮点击事件 */
	    function deleteFunction(){
	    	var check=$('#contentTable').datagrid("getChecked");
	    	if(check&&check.length<1){
	    		$.messager.alert('提示','请勾选菜单!','info');
	    		return;
	    	}
	    	//遍历数组，将数组的id信息封装到字符串中做为参数返回到新的jsp
	    	var array=new Array();
	    	$.each(check,function(i,n){
	    		array.push(n.id);
	    	})
	    	//将数组用逗号隔开内部内容
	    	var functionIds= array.join(",");
	    	
	    	$.messager.confirm('Confirm','是否删除选中记录?',function(r){
	    		$.post(
	    			"system/function/delete.do",
	    			{"functionIds":functionIds},
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
</head>
<body>
	<br>
	<ul class="nav nav-tabs" >
		<li class="active"><a href="javascript:void(0)">菜单列表</a></li>
		<li><a href="system/function/toAddFunction.do">菜单添加</a></li>
	</ul>
	
	<table id="contentTable" class="easyui-datagrid">
		菜单名称： <input id="funcname" name="funcname" type="text" class="easyui-textbox" >	
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addFunction()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteFunction()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:editFunction()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
	</table>
	

</body>
</html>