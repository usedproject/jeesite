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
			/* 
			/* var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{
						//beforeClick:function(id, node){
						//tree.checkNode(node, !node.checked, true, true);
						//return false; }
						onCheck:onCheck
					}};
			 */
			// 用户-菜单
			/* var zNodes=[
						{id:"1", pId:"0", name:"权限列表"},
						{id:"2", pId:"1", name:"用户管理"},
						{id:"3", pId:"1", name:"角色管理"},
						{id:"4", pId:"1", name:"菜单管理"},
						{id:"6", pId:"1", name:"数据字典"},
						{id:"7", pId:"1", name:"短信模板"},
						{id:"8", pId:"1", name:"邮件模板"},
					];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
			// 不选择父节点
			tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			var ids = "".split(",");
			for(var i=0; i<ids.length; i++) {
				var node = tree.getNodeByParam("id", ids[i]);
				try{tree.checkNode(node, true, false);}catch(e){}
			} */
			// 默认展开全部节点
			/* tree.expandAll(true);
			function onCheck(e,treeId,treeNode){
	            var treeObj=$.fn.zTree.getZTreeObj("menuTree");
	            var nodes=treeObj.getCheckedNodes(true);
	            var v="";
	            for(var i=0;i<nodes.length;i++){
	            	v+=nodes[i].id + ",";
	            }
	            $("#menu_id").val(v);
	        } 
	         */
		});
		
		/* function createOrgnizationOfTrainee() {
            var k= getTreeSelected();
            for (var i = 0; i < k.length;i++) {
                alert(k[i].text);
            }
            //alert(JSON.stringify( $('#org_tree').tree('getChecked')));
        } */
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
		<li class="active"><a href="saved_resource.html">角色信息添加</a></li>
	</ul>
	<br>
	<form id="inputForm" class="form-horizontal"  novalidate="novalidate">
		<input id="id" name="id" type="hidden" value="">
	<!-- <script type="text/javascript">top.$.jBox.closeTip();</script> -->
		<div class="control-group">
			<label class="control-label">角色名称：</label>
			<div class="controls">
				<input id="rolename" name="rolename" class="input-xlarge required" type="text" value="" maxlength="50">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<input id="sortnum" name="sortnum" class="input-xlarge " type="text" value="" maxlength="11">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<select id="status" name="status" class="input-xlarge">
					<option value="" selected="selected">请选择状态</option>
					<!-- <option value="0">正常</option>
					<option value="1">删除</option> -->
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

	<script type="text/javascript">
	/* 无框架时，左上角显示菜单图标按钮。
		if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
			$("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
			$("#btnMenu").click(function(){
				top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=ff9a71c594b14065828ca71d9cf465d6', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
				//if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
			});
		}// */
		
		 
	$(function(){
		/* 页面加载完成之后进行功能节点的动态获取 */
		 $('#tt').tree({
		    url: "function/list.do",  // 自动访问url
		    loadFilter: function(data){
				if (data.result){
					return data.result;
				} else {
					return data;
				}
		    }
		}); 
		
		/* 动态加载状态下拉框 */
		 /* 获得用户状态栏，进行动态的添加 */
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
	})	
	</script>

</body></html>