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
		 "name":$("#name").val(),
		 "idcard":$("#idcard").val(),
		 "jobintension":$("#jobintension").val(),
		 "foraddress":$("#foraddress").val()
	 });
}



	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		客户名称： <input id="name" name="name" type="text" class="easyui-textbox" >
		身份证号： <input id="idcard" name="idcard" type="text" class="easyui-textbox" >
		求职意向： <input id="jobintension" name="jobintension" class="easyui-textbox" style="height: 30px" >
		期望工作地：<input id="foraddress" name="foraddress" class="easyui-textbox" style="height: 30px" >
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addPerson()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deletePerson()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:editPerson()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
	</table>
	
</body>
	<script type="text/javascript">
	
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'business/person/getList.do',
        method:"post",
		toolbar:"#toolbar",
		rownumbers: true,  // 显示行号
		pagination:true,
		pageSize:5,
		pageList:[5,10],
        loadFilter: function(data){
    		return{"total":data.result.total,"rows":data.result.rows}	
    	}, 
        columns:[[
        	{field:'id',title:'用户id',width:100,checkbox:true},
        	{field:'ids',title:'编号',width:100,
        		 formatter: function(value,row,index){
    				return row.id;
    			} 
        	},
    		{field:'name',title:'客户名称',width:100},
    		{field:'idcard',title:'身份证号',width:100},
    		{field:'jobintension',title:'期望职位',width:100/* ,
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
    			} */
    		},
    		{field:'forprice',title:'期望薪资',width:100},
    		{field:'foraddress',title:'期望工作地',width:100},
    		{field:'resumeurl',title:'个人简历url',width:100,
    			formatter: function(value,row,index){
    				if(value){
    					return "<a href='business/person/download.do?name="+value+"' >下载简历</a>"
    				}else{
    					return "暂无简历";
    				}
    			}
    		},
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
   
    
    /* 增加按钮的事件函数 */
    function addPerson(){
    	parent.openTopWindow(
    			{"url":"business/person/toAddPerson.do",
    			"title":"添加人才信息",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    	
    }
   
    
    /* 编辑按钮的事件函数 */
    function editPerson(){
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
    			{"url":"business/person/toEditPerson.do?id="+id,
    			"title":"修改人才信息",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    }
    
    /* 删除按钮点击事件 */
   /*  function deleteuser(){
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
    	$.post(
    			"system/user/delete.do",
    			{"userIds":userIds},
    			function(data){
    				alert(data.message);
    				doSearch();
    			},
    			"json"
    	);
    } */
    
    /* 动态加载状态栏 */
  		/*   $.post(
					"system/dict/selectAll.do",
					{"name":"del_flag"},
					function(data){
						$.each(data,function(i,o){
							var content =$("<option value='"+o.value+"' >"+o.label+"</option>");
							$("#status").append(content);
						})
					},
					"json"
				);   */
	</script>
</html>