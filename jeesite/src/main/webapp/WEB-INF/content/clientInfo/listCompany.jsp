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
		 "companyno":$("#companyno").val(),
		 "idcard":$("#idcard").val()
	 });
}
	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		公司名称： <input id="name" name="name" type="text" class="easyui-textbox" >
		信用号码： <input id="companyno" name="companyno" type="text" class="easyui-textbox" >
		身份证号： <input id="idcard" name="idcard" type="text" class="easyui-textbox" >
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addCompany()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteCompany()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:editCompany()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
		<a href="static2/model/companyModel.xls" class="easyui-linkbutton" iconCls="icon-download" >模板下载</a>
    	<a href="javascript:importExcel();" class="easyui-linkbutton" iconCls="icon-import">导入</a>
    	<a href="javascript:exportExcel();" class="easyui-linkbutton" iconCls="icon-export">导出</a>
	</table>
</body>
	<script type="text/javascript">
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'business/company/getList.do',
        method:"post",
		toolbar:"#toolbar",
		rownumbers: true,  // 显示行号
		pagination:true,
		pageSize:5,
		pageList:[5,10],
    	/* loadFilter: function(data){
    		return{"total":data.result.total,"rows":data.result.rows}	
    	}, */
        columns:[[
        	{field:'id',title:'公司id',width:100,checkbox:true},
        	{field:'name',title:'公司名称',width:150},
    		{field:'telphone',title:'电话',width:150},
    		{field:'companyno',title:'统一信用号',width:150},
    		{field:'owner',title:'法人',width:150},
    		{field:'phone',title:'手机',width:150},
    		{field:'email',title:'电子邮件',width:150}
        ]],
    	loadFilter: function(data){
    		return {"total":data.result.total,"rows":data.result.rows}
    	}
    });
   
    /* 增加按钮的事件函数 */
    function addCompany(){
    	parent.openTopWindow(
    			{"url":"business/company/toAddCompany.do",
    			"title":"增加公司用户",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    	
    }
    
    /* 编辑按钮的事件函数 */
    function editCompany(){
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
    			{"url":"business/company/toEditCompany/"+id+".do",
    			"title":"修改公司客户",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    }
    
    /* 删除按钮点击事件 */
    function deleteCompany(){
    	 var check=$('#dg').datagrid("getChecked");
    	if(check&&check.length<1){
    		$.messager.alert('提示','请勾选公司!','info');
    		return;
    	}
    	//遍历数组，将数组的id信息封装到字符串中做为参数返回到新的jsp
    	var array=new Array();
    	$.each(check,function(i,n){
    		array.push(n.id);
    	})
    	//将数组用逗号隔开内部内容
    	var userIds= array.join(",");
    	$.messager.confirm('Confirm','是否删除选中记录?',function(r){
	    	$.post(
	    			"business/company/delete.do",
	    			{"userIds":userIds},
	    			function(data){
	    				alert(data.message);
	    				doSearch();
	    			},
	    			"json"
	    	);
    	})
    }
    
    /* 导出的函数定义 */
    function exportExcel(){
    	var check=$('#dg').datagrid("getChecked");
    	if(check&&check.length<1){
    		$.messager.alert('提示','请勾选公司信息!','info');
    		return;
    	}
    	//遍历数组，将数组的id信息封装到字符串中做为参数返回到新的jsp
    	var array=new Array();
    	$.each(check,function(i,n){
    		array.push(n.id);
    	})
    	//将数组用逗号隔开内部内容
    	var recordIds= array.join(",");
    	var url = 'business/company/export.do?recordIds='+recordIds;
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
			    	a.download = '公司信息表.xlsx';
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
    
    /* 导入功能实现 */
    function importExcel(){
    	parent.openTopWindow(
    			{"url":"business/company/toSelectExcel.do",
    			"title":"选择对应的Excel模板",
    			'onClose':function(){doSearch()}
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
</html>