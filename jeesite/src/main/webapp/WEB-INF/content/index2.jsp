<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	
<title>Insert title here</title>

<%@include file="script.html" %>
<style type="text/css">
#title{height:70px;background:green;font-size: 2.5em;
	   color:#FFFFFF;padding-top: 25px;padding-left: 10px
	   }
</style>
</head>
<body class="easyui-layout">
    <div id="title" data-options="region:'north',split:true" >
    	安思普惠人事代理系统
    	<div style="float: right;" align="right"><a href="login.jsp" >退出</a> </div>
    </div>
    <div id="menu" data-options="region:'west',split:true" style="width:200px;">
    	<ul id="tt" ></ul>
    </div>
    <div id="tabs"  class="easyui-tabs" data-options="region:'center'" style="padding:5px;background:#eee;height:100%;width: 100%;overflow:hidden">   	  	
    </div>
    <div id="topWindow" style="overflow: hidden;"></div>
    <input type="hidden" id="parent_Id">
    <input type="hidden" id="parent_Name">
    <input type="hidden" id="subject">
    <input type="hidden" id="content">
    <input type="hidden" id="TemplateId">
</body>


<script type="text/javascript">
	$('#tt').tree({
	    url: "menu/list.do",  // 自动访问url
	    loadFilter: function(data){
			if (data.result){
				return data.result;
			} else {
				return data;
			}
	    },
	    onClick:function(node){
		    if(node.attributes && node.attributes.url){
				// 在center中打开一个标签页
		    	openTab( "#tabs", node.text, node.attributes.url);
		    }
		}
		
	});

	function openTab(tabsId,title, url){
		// 判断一个标签页是否存在 ，如果存在，根据标签页的标题或索引， 选中已存在的标签页  ,如果不存在，创建新的标签页
		var exists = $(tabsId).tabs("exists",title);
		if(exists){
			// 选中已存在的标签页 
			$(tabsId).tabs("select",title);	
		}else{
			// 创建新的标签页
			$(tabsId).tabs('add',{
			    title: title ,
			    fit:true,
			    content: '<iframe width="100%" height="100%"   frameborder="0" src="'+ url + '.do" ></iframe>' ,
			    closable:true
			});
		}			
	}

	//打开一个窗口
	function openTopWindow(options){
		 options.width = options.width ?  options.width : 600;
		 options.height= options.height ?  options.height : 600;
		 options.title = options.title ? options.title : "";
		 options.onClose=options.onClose ? options.onClose:function(){};
		$("#topWindow").window({
			title:options.title,
			width:options.width,
			height:options.height,
			modal:true, // 模态
			content:"<iframe width='100%' height='100%' src='"+options.url+"'> </iframe>",			
			onClose:options.onClose
			});
	}

	//关闭窗口
	function closeTopWindow(){
		$("#topWindow").window("close");
		}
	
	function enterParent(id,name){
		$("#parent_Id").val(id);
		$("#parent_Name").val(name);
	}
	
	function enterSms(subject,content,id){
		$("#subject").val(subject);
		$("#content").val(content);
		$("#TemplateId").val(id);
	}
</script>
</html>
