<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%-- <%@include file="script.html" %> --%>
<%@include file="../static.html" %>
<%@ include file="../script.html" %>

</head>
<script type="text/javascript">
		
			$(function(){

				$.post("company/system/getZtreeNode.do",
						function(data){
					var setting = {
							check:{enable:true,nocheckInherit:true,chkStyle: "radio",radioType : "all" },
							view:{selectedMulti:false},
							data:{simpleData:{enable:true}},
							callback:{
						//beforeClick:function(id, node){
						//tree.checkNode(node, !node.checked, true, true);
						//return false; }
						onCheck:onCheck
					}};
			
		

			// 初始化树结构
			var tree = $.fn.zTree.init($("#menuTree"), setting, data);
			// 不选择父节点
			//tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			//获取此角色的权限
			var ids= $("#ids").val();
			var ids=ids.split(",")
			
			// 默认选择节点
			//var ids = ids.split(",");
			for(var i=0; i<ids.length; i++) {
		
				var node = tree.getNodeByParam("id", ids[i]);
				try{tree.checkNode(node, true, false);}catch(e){}
				
				// 展开选中的节点
				tree.expandNode(node,true);
			}
	

					}
						,
						"json"
						)
				
					
			function onCheck(e,treeId,treeNode){
	            var treeObj=$.fn.zTree.getZTreeObj("menuTree");
	            var nodes=treeObj.getCheckedNodes(true);	           	                      
	            $("#menu_id").val(nodes[0].id)
	        }


})

					
					
		        
			
			
			
					
	</script>

</head>
<body>
	

	
	<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
		<input id="ids" name="ids" type="hidden" value="${requestScope.functionId }">
		<div class="control-group" >
			<label class="control-label"></label>
			<div class="controls">
				<div id="menuTree" class="ztree"
					style="margin-top: 3px; float: left;"></div>
				<input id="menu_id" type="hidden" value="">
			</div>
		</div>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="确定"  >&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" ">
		</div>
	</form>

</body>
	<script type="text/javascript">
	//返回按钮
	$("#btnCancel").click(function(){												
			parent.closeTopWindow();
	})
	
	//确定按钮 ,获取菜单名
	$("#btnSubmit").click(function(){
		var menu_id=$("#menu_id").val();
		if(menu_id==null){
			alert("请勾选上级菜单");
			return
		}
		$.post("company/system/getFuncNameById.do",
				{"id":menu_id},
				function(data){
					parent.enterParent(menu_id,data.result);
					parent.closeTopWindow();
					},
				"json"
					
				)
		

		})

	</script>
</html>
