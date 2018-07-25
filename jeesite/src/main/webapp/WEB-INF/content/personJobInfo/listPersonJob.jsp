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
		 "companyid":$("#companyid").val()
	 });
}
	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		客户名称： <input id="name" name="name" type="text" class="easyui-textbox" >
		身份证号： <input id="idcard" name="idcard" type="text" class="easyui-textbox" >
		合作公司： <select id="companyid" name="companyid"  style="height: 30px" >
				<option value="">--请选择合作公司--</option>
			</select>
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addPersonJob()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deletePersonJob()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:editPersonJob()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
	</table>
</body>
	<script type="text/javascript">
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'business/personJob/getList.do',
        method:"post",
		toolbar:"#toolbar",
		rownumbers: true,  // 显示行号
		pagination:true,
		pageSize:1,
		pageList:[1,2],
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
    		{field:'companyName',title:'合作公司',width:100/* ,
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
    		{field:'jobtype',title:'工作类别',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "兼职";
    				}
    				if(value==1){
    					return "全职";
    				}
    				if(value==2){
    					return "外派";
    				}
    			}
    		},
    		{field:'companyprice',title:'公司单价',width:100},
    		{field:'personprice',title:'个人单价',width:100},
    		{field:'starttime',title:'开始时间',width:100/* ,align:'right',
    			formatter: function(value,row,index){
    				if (value==0){
    					return "正常";
    				} else {
    					return "删除";
    				}
    			} */
    		},
    		{field:'endtime',title:'结束时间',width:100},
        ]]
    });
 	
    /* 动态加载公司列表 */
    $.post(
    	"business/company/selectAll.do",
    	{},
    	function(data){
    		$.each(data.result,function(i,o){
    			var content =$("<option value='"+o.id+"' >"+o.name+"</option>");
				$("#companyid").append(content);
    		})
    	},
    	"json"
    );
	
    /* 增加按钮的事件函数 */
    function addPersonJob(){
    	parent.openTopWindow(
    			{"url":"business/personJob/toAddPersonJob.do",
    			"title":"增加劳务派遣信息",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    	
    }
    
    /* 编辑按钮的事件函数 */
    function editPersonJob(){
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
    			{"url":"business/personJob/toEditPersonJob.do?id="+id,
    			"title":"修改劳务派遣",
    			"width":1000,
    			"height":600,
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