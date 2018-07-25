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
		 "accountno":$("#accountno").val(),
		 "idcard":$("#idcard").val(),
	 });
}
	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		身份证号： <input id="idcard" name="idcard" type="text" class="easyui-textbox" >
		公积金号 <input id="accountno" name="accountno" type="text" class="easyui-textbox" >
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addAccumulationFund()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteAccumulationFund()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:editAccumulationFund()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
		<a href="static2/model/公积金模板.xls" class="easyui-linkbutton" iconCls="icon-download" >模板下载</a>
    	<a href="javascript:importExcel();" class="easyui-linkbutton" iconCls="icon-import">导入</a>
    	<a href="javascript:exportExcel();" class="easyui-linkbutton" iconCls="icon-export">导出</a>
	</table>
</body>
	<script type="text/javascript">
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'business/accumulationFund/getList.do',
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
        	{field:'id',title:'客户id',width:100,checkbox:true},
        	{field:'ids',title:'客户编号',width:100,
        		formatter: function(value,row,index){
    				return row.id;
    			} 
        	},
        	{field:'name',title:'客户名称',width:100},
        	{field:'accountno',title:'公积金号',width:100},
    		{field:'idcard',title:'身份证号',width:100},
    		{field:'paydate',title:'缴费期间',width:100},
    		{field:'paymoney',title:'缴费金额',width:100,
    			/* formatter: function(value,row,index){
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
    		{field:'proxyfee',title:'代理费用',width:100},
    		{field:'status',title:'状态',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "已交款";
    				}else{
    					return "未交款";
    				}
    			}
    		}
    		
    		/* {field:'school',title:'毕业学校',width:100},
    		{field:'specialty',title:'所学专业',width:100},
    		{field:'graduation',title:'毕业时间',width:100},
    		{field:'issalary',title:'代缴工资',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "是";
    				}else if(value==1){
    					return "否";
    				}else{
    					return "待定";
    				}
    			}
    		},
    		{field:'isshebao',title:'代缴社保',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "是";
    				}else if(value==1){
    					return "否";
    				}else{
    					return "待定";
    				}
    			}
    		},
    		{field:'isgongjijin',title:'代缴公积金',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "是";
    				}else if(value==1){
    					return "否";
    				}else{
    					return "待定";
    				}
    			}
    		} */
        ]]
    });
   
    
    /* 增加按钮的事件函数 */
    function addAccumulationFund(){
    	parent.openTopWindow(
    			{"url":"business/accumulationFund/toAddAccumulationFund.do",
    			"title":"公积金缴费",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    	
    }
    
    /* 编辑按钮的事件函数 */
    function editAccumulationFund(){
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
    			{"url":"business/accumulationFund/toEditAccumulationFund.do?id="+id,
    			"title":"修改公积金缴费情况",
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
    /* 导出的函数定义 */
    function exportExcel(){
    	var check=$('#dg').datagrid("getChecked");
    	if(check&&check.length<1){
    		$.messager.alert('提示','请勾选公积金信息!','info');
    		return;
    	}
    	//遍历数组，将数组的id信息封装到字符串中做为参数返回到新的jsp
    	var array=new Array();
    	$.each(check,function(i,n){
    		array.push(n.id);
    	})
    	//将数组用逗号隔开内部内容
    	var recordIds= array.join(",");
    	var url = 'business/accumulationFund/export.do?recordIds='+recordIds;
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
			    	a.download = '公积金信息.xlsx';
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
    			{"url":"business/accumulationFund/toSelectExcel.do",
    			"title":"选择对应的Excel模板",
    			'onClose':function(){doSearch()}
    			})
    }
   
	</script>
</html>