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
		 "jobname":$("#jobname").val(),
		 "jobtype":$("#jobtype").val(),
		 "industry":$("#industry").val(),
		 "companyid":$("#companyid").val()
	 });
}
	
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid">
		需求名称： <input id="jobname" name="jobname" type="text" class="easyui-textbox" >
		需求职位： <input id="jobtype" name="jobtype" type="text" class="easyui-textbox" >
		所属行业： <select id="industry" name="industry"  style="height: 30px" >
					<option value="">请选择所属行业</option>
					<option value="0">软件互联网</option>
					<option value="1">建筑房地产</option>
					<option value="2">商业服务业</option>
					<option value="3">金融业</option>
					<option value="4">贸易批发零售</option>
					<option value="5">文体教育传媒</option>
					<option value="6">加工制造</option>
					<option value="7">农林牧副渔</option>
					<option value="8">其他</option>
			   </select>
		发布公司：<select id="companyid" name="companyid"  style="height: 30px" >
					<option value="">请选择发布公司</option>
			   </select>
		<a href="javascript:doSearch()"  class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'large',plain:true">搜索</a>
		<a href="javascript:addNeedJob()" class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'large',plain:true" >增加</a>
		<a href="javascript:deleteNeedJob()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',size:'large',plain:true">删除</a>
		<a href="javascript:editNeedJob()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'large',plain:true">编辑</a>
	</table>
</body>
	<script type="text/javascript">
	/* -------------------数据加载------------------------------ */
    $('#dg').datagrid({
        url:'business/needJob/getList.do',
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
        	{field:'id',title:'需求id',width:100,checkbox:true},
        	{field:'ids',title:'编号',width:100,
        		 formatter: function(value,row,index){
    				return row.id;
    			} 
        	},
    		{field:'jobname',title:'需求职位',width:100},
    		{field:'industry',title:'所属行业',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "软件互联网";
    				}
    				if(value==1){
    					return "建筑房地产";
    				}
    				if(value==2){
    					return "商业服务业";
    				}
    				if(value==3){
    					return "金融业";
    				}
    				if(value==4){
    					return "贸易批发零售";
    				}
    				if(value==5){
    					return "文体教育传媒";
    				}
    				if(value==6){
    					return "加工制造";
    				}
    				if(value==7){
    					return "农林牧副渔";
    				}
    				return "其他";
    			}
    		
    		
    		},
    		{field:'needperson',title:'需求人数',width:100},
    		{field:'paytype',title:'支付方式',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "月结";
    				}
    				if(value==1){
    					return "日结";
    				}
    				if(value==2){
    					return "其他";
    				}
    			}
    		},
    		{field:'price',title:'需求单价',width:100},
    		{field:'companyName',title:'发布公司',width:100},
    		{field:'starttime',title:'开始日期',width:100},
    		{field:'endtime',title:'结束日期',width:100},
    		{field:'address',title:'工作地点',width:100},
    		{field:'status',title:'需求状态',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "有效";
    				}
    				if(value==1){
    					return "无效";
    				}
    			}
    		},
    		{field:'infotype',title:'信息类型',width:100,
    			formatter: function(value,row,index){
    				if(value==0){
    					return "本公司招聘";
    				}
    				if(value==1){
    					return "合作公司招聘";
    				}
    			}
    		},
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
    function addNeedJob(){
    	parent.openTopWindow(
    			{"url":"business/needJob/toAddNeedJob.do",
    			"title":"增加用户",
    			"width":1000,
    			"height":600,
    			'onClose':function(){doSearch()}
    			})
    	
    }
    
    /* 编辑按钮的事件函数 */
    function editNeedJob(){
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
    			{"url":"business/needJob/toEditNeedJob.do?id="+id,
    			"title":"修改招聘信息",
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