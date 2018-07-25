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
<script src="static/common/jeesite.js" type="text/javascript"></script>
	<meta name="decorator" content="default">
	<script type="text/javascript">
		$(document).ready(function() {
			/* 进行页面加载函数 */
				$('#contentTable').datagrid({
				    url:'business/emailRecord/getList.do',
				    method:"post",
					//toolbar:"#toolbar",
					idField:"id",
					pagination:true,
					pageSize:5,
					pageList:[5,10],
					loadFilter: function(data){
						return {"rows":data.result.rows,"total":data.result.total}	
					},
				    columns:[[
				    	{field:'id',title:'菜单id',width:150,checkbox:true},
				    	{field:'ids',title:'编号',width:150,
				    		formatter: function(value,row,index){
					    		if(row!=null){
					    			return row.id;
					    		}else{
					    			return "";
					    		}
					    	}
				    	},
				    	{field:'userName',width:150,title:'发信人'},
				    	{field:'toAddr',width:150,title:'收件人'},
					    {field:'subject',width:150,title:'标题'},
					    {field:'sendtime',width:150,title:'发送时间'/* ,
					    	formatter: function(value,row,index){
					    		if(value!=null){
					    			return value.funcname;
					    		}else{
					    			return "";
					    		}
					    	} */
					    },
					    {field:'status',title:'状态',width:150,
						    	formatter: function(value,row,index){
								if (value==0){
									return "已发送";
								} else {
									return "发送失败";
								} 
							}
					    }
					]]
				});
		});
		
			/* 定义搜索按钮的功能 */
			function doSearch(){
				 $('#contentTable').datagrid('load',{
					"userName":$("#userName").val(),
					"toAddr":$("#toAddr").val()
				 });
			}
			
			 /* 增加按钮的事件函数 */
	   		function addEmailRecord(){
				window.location.href="business/emailRecord/toAddEmailRecord.do";	    	
	    	}
			 
			 /* 定义详情按钮的查看功能 */
			 function detailEmailRecord(){
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
			    			{"url":"business/emailRecord/toDetailEmailRecord.do?id="+id,
			    			"title":"邮件发送详情",
			    			"width":700,
			    			'onClose':function(){
			    				doSearch()
			    				}
			    			})
			 }
	</script>
</head>
<body>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="saved_resource.html">邮件发送列表</a></li>
		<li><a href="business/smsRecord/listSmsRecord.do">短信发送列表</a></li>
	</ul>
		
	<table id="contentTable" >
		发信人： <input id="userName" name="userName" type="text" class="easyui-textbox" >
		收信人 <input id="toAddr" name="toAddr" type="text" class="easyui-textbox" >
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addEmailRecord()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteEmailRecord()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:detailEmailRecord()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">详情</a>	
		</tbody>
	</table>
</body></html>