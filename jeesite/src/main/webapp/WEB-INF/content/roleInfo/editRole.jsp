<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色信息管理 - Powered By JeeSite</title>
	<base href=<%=basePath%>>
	<!-- 引入easyui -->
	<%@include file="../script.html" %>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<!-- <script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script> -->
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<!-- <script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script> -->
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="../static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="../static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="../static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<!-- <script src="static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script> -->
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<!-- <script src="static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="static/common/mustache.min.js" type="text/javascript"></script> -->
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<!-- <script src="static/common/jeesite.js" type="text/javascript"></script> -->
	<!-- <script type="text/javascript">
		var ctx = '../a',
		ctxStatic='../static';
	</script> -->
	<!-- Baidu tongji analytics -->
	<!-- <script>
		var _hmt=_hmt||[];
		(function(){
			var hm=document.createElement("script");
			hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";
			var s=document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm,s);})();
	</script> -->
	

	<meta name="decorator" content="default">
	<!-- <link href="static/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css">
	<script src="static/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script> -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			 $("#inputForm").validate({
				submitHandler: function(form){
					/*  loading('正在提交，请稍等...');
					form.submit(); */
					//alert("正在提交");
					//获得被选择的节点的id
					var trees= getTreeSelected();
					var funct=new Array();
					$.each(trees,function(i,n){
						funct.push(n.id);
					})
					var functionIds=funct.join(",");
					var statu=$("#status").val();
					if(statu==""){
						alert("请选择状态");
						return;
					}
					$.post(
							"system/role/add.do",
							{"rolename":$("#rolename").val(),
							 "sortnum":$("#sortnum").val(),
							 "status":$("#status").val(),
							 "id":$("#id").val(),
							"functionIds":functionIds
							},
							function(data){
								alert(data.message);
								parent.closeTopWindow();
							},
							"json"
						);
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			}); 
			
			 $(function(){
					/* 页面加载完成之后进行功能节点的动态获取 */
					 $('#tt').tree({
					    url: "function/listAll.do?rid="+${role.id},  // 自动访问url
					    loadFilter: function(data){
							if (data.result){
								return data.result;
							} else {
								return data;
							}
					    },
					    onlyLeafCheck:true
					}); 
					
					//alert(tree);
					/* 动态加载状态下拉框 */
					 /* 获得用户状态栏，进行动态的添加 */
					  $.post(
						"system/dict/selectAll.do",
						{"name":"del_flag"},
						function(data){
							$.each(data,function(i,o){
								if(o.value==${role.status}){
									var content =$("<option value='"+o.value+"' selected='selected'  >"+o.label+"</option>");
								}else{
									var content =$("<option value='"+o.value+"'  >"+o.label+"</option>");
								}
								$("#status").append(content);
							});
						},
						"json"
					); 
					
				})	
		});
		
        //获原始树结构所选节点的数据数组
        function getTreeSelected(){
            var nodes = $('#tt').tree('getChecked');
            var arr = new Array();
            for (var i = 0; i < nodes.length;i++) {
                arr.push(nodes[i]);
                myFuc(nodes[i]);
            }
            return arr;

            //内部递归函数
            function myFuc(n) {
                var parent = $('#tt').tree('getParent', n.target);
                if (parent == null) return;
                if (isExistItem(parent)) return;
                arr.push(parent);
                myFuc(parent);
            }
            //验证节点是否已存在数组中
            function isExistItem(item)
            {
                var flag = false;
                for (var i = 0; i < arr.length;i++)
                {
                    if (arr[i] == item) {
                        flag = true; break;
                    }
                }
                return flag;
            }
        }
	</script>

</head>
<body>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="saved_resource.html">角色信息修改</a></li>
	</ul>
	<br>
	<form id="inputForm" class="form-horizontal"  novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="${role.id}">
		<div class="control-group">
			<label class="control-label">角色名称：</label>
			<div class="controls">
				<input id="rolename" name="rolename" class="input-xlarge required" type="text" value="${role.rolename}" maxlength="50">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<input id="sortnum" name="sortnum" class="input-xlarge " type="text" value="${role.sortnum}" maxlength="11">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<select id="status" name="status" class="input-xlarge">
					<option value="" >请选择状态</option>
				</select>
			</div>
		</div>
		<div >
			<label class="control-label">角色授权:</label>
			<div class="controls">
				<!-- <div id="menuTree" class="ztree"
					style="margin-top: 3px; float: left;"></div>
				<input id="menu_id" name="menuIds" type="hidden" value="1,2"/> -->
				<ul id="tt" class="easyui-tree" checkbox="true"></ul>
			</div>
			
		</div>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
		</div>
	</form>

</body></html>