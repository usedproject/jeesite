<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>安思普惠人事代理系统</title><!--  - Powered By JeeSite -->
<base href=<%=basePath%>>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<script src="static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="./static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="./static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
	<script src="./static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<script src="static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="static/common/mustache.min.js" type="text/javascript"></script>
	<link href="static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<script src="static/common/jeesite.js" type="text/javascript"></script>
	<script type="text/javascript">var ctx = './a', ctxStatic='./static';</script>
	<!-- Baidu tongji analytics -->
	<!-- <script>var _hmt=_hmt||[];
	(function(){
		var hm=document.createElement("script");
		hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";
		var s=document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm,s);})();
	</script> -->
	<meta name="decorator" content="blank">
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// 
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// 
					return true;
				}
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left .accordion").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if (!$(menuId + " .accordion-body:first").hasClass('in')){
						$(menuId + " .accordion-heading:first a").click();
					}
					if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
						$(menuId + " .accordion-body a:first i").click();
					}
					// 初始化点击第一个三级菜单
					$(menuId + " .accordion-body li:first li:first a:first i").click();
				}else{
					// 获取二级菜单数据
					$.get($(this).attr("data-href"), function(data){
						if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "/jeesite/a";
							return false;
						}
						$("#left .accordion").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
							}
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).parent().addClass("active");
							$(this).children("i").addClass("icon-white");
						});
						// 展现三级
						$(menuId + " .accordion-inner a").click(function(){
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
								return false;
							}
							// 
						});
						// 默认选中第一个菜单
						$(menuId + " .accordion-body a:first i").click();
						$(menuId + " .accordion-body li:first li:first a:first i").click();
					});
				}
				// 大小宽度调整
				wSizeWidth();
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:first span").click();
			// 
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
			// 获取通知数目  
			function getNotifyNum(){
				$.get("/jeesite/a/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
					var num = parseFloat(data);
					if (num > 0){
						$("#notifyNum,#notifyNum2").show().html("("+num+")");
					}else{
						$("#notifyNum,#notifyNum2").hide()
					}
				});
			}
			getNotifyNum(); //
			setInterval(getNotifyNum, 60000); //
		});
		// 
	</script>

</head>
<body>
	
	<div id="main" style="width: auto;">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand"><span id="productName">安思普惠人事代理系统</span></div>
				<ul id="userControl" class="nav pull-right">
					<li><a href="index" target="_blank" title="访问网站主页"><i class="icon-home"></i></a></li>
					<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<li><a href="#" onclick="location=&#39;/jeesite/theme/default?url=&#39;+location.href">默认主题</a></li>
							<li><a href="#" onclick="location=&#39;/jeesite/theme/cerulean?url=&#39;+location.href">天蓝主题</a></li>
							<li><a href="#" onclick="location=&#39;/jeesite/theme/readable?url=&#39;+location.href">橙色主题</a></li>
							<li><a href="#" onclick="location=&#39;/jeesite/theme/united?url=&#39;+location.href">红色主题</a></li>
							<li><a href="#" onclick="location=&#39;/jeesite/theme/flat?url=&#39;+location.href">Flat主题</a></li>
							<li><a href="javascript:cookie(&#39;tabmode&#39;,&#39;1&#39;);location=location.href">开启页签模式</a></li>
						</ul>
						<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</li>
					<li id="userInfo" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, ${user.username }&nbsp;<span id="notifyNum" class="label label-info hide" style="display: none;"></span></a>
						<ul class="dropdown-menu">
							<li><a href="userInfo.html" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="updatePassword.html" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
							<li><a href="myAdvice.html" target="mainFrame"><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide" style="display: none;"></span></a></li>
						</ul>
					</li>
					<li><a href="login.html" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>
				
				<div class="nav-collapse">
					<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
								<li class="menu">
										<a class="menu" href="javascript:" data-href="#" data-id="c082a1303cf14573916c963071bdddc4"><span>业务菜单</span></a>
								</li>
								<li class="menu active">
										<a class="menu" href="javascript:" data-href="#" data-id="2"><span>系统设置</span></a>
								</li>
						
					</ul>
				</div><!--/.nav-collapse -->
			</div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left" style="width: 160px; height: 510px;">
				

	<div class="accordion" id="menu-c082a1303cf14573916c963071bdddc4" style="display: none;">
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-28" href="#collapse-28" title=""><i class="icon-chevron-down"></i>&nbsp;个人信息</a>
		    </div>
		    <div id="collapse-28" class="accordion-body collapse in">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li class="active"><a data-href=".menu3-29" href="toedituser.do" target="mainFrame"><i class="icon-user icon-white"></i>&nbsp;个人信息</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							</ul></li>
						<li><a data-href=".menu3-30" href="tomodifypassword.do" target="mainFrame"><i class="icon-lock"></i>&nbsp;修改密码</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-bc4518efa5d043958b69590f6cca5ec1" href="#collapse-bc4518efa5d043958b69590f6cca5ec1" title=""><i class="icon-chevron-right"></i>&nbsp;客户管理</a>
		    </div>
		    <div id="collapse-bc4518efa5d043958b69590f6cca5ec1" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-be189f93d8fe4f77a6fde050f3ddc7b7" href="companyList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;公司客户</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							
							</ul></li>
						<li><a data-href=".menu3-01eabbf5bc83468f9397c560e0db8a75" href="customerList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;个人客户</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-d44fe7ff911341d5bb7e5c501f1d6d27" href="#collapse-d44fe7ff911341d5bb7e5c501f1d6d27" title=""><i class="icon-chevron-right"></i>&nbsp;社保管理</a>
		    </div>
		    <div id="collapse-d44fe7ff911341d5bb7e5c501f1d6d27" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-d01bab3ae2854fcf81b9c5f063f9056d" href="shebaoList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;社保信息</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-786133f2fdf547be8bd8c4e213c20ff3" href="shebaoRecordList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;社保记录</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							</ul></li>
						<li><a data-href=".menu3-8d338395c6d2477ab1028e9789dd3dab" href="addShebaoRecord/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;社保缴费</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-60702152789349b895607523f623d46b" href="smsSend/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;社保催交</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-4e35e5ef243f48b5a4d81cb2920214a6" href="#collapse-4e35e5ef243f48b5a4d81cb2920214a6" title=""><i class="icon-chevron-right"></i>&nbsp;公积金管理</a>
		    </div>
		    <div id="collapse-4e35e5ef243f48b5a4d81cb2920214a6" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-059199c2d9804ef8883822dd1adb7935" href="gongjijinList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;公积金查询</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-b9fd18369098410aa7ce9f76710df4a9" href="addGongjijin/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;公积金缴费</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-27e37abcf1284c309569eed2c9027a11" href="smsSend/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;公积金催交</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-b4fc47cd82fd4fe09121453bd38a66bd" href="#collapse-b4fc47cd82fd4fe09121453bd38a66bd" title=""><i class="icon-chevron-right"></i>&nbsp;薪酬管理</a>
		    </div>
		    <div id="collapse-b4fc47cd82fd4fe09121453bd38a66bd" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-2c765c9e8732493b871991c61e6b9f79" href="salaryList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;工资查询</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-d4b19671327649c9bb365ad8ca91ae5b" href="addSalary/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;代发工资</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-669903bb7774467993f46bd981e5f72d" href="salaryPer/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;工资条</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-2d661bc23f10434ab743ea292c22edcc" href="#collapse-2d661bc23f10434ab743ea292c22edcc" title=""><i class="icon-chevron-right"></i>&nbsp;人才服务</a>
		    </div>
		    <div id="collapse-2d661bc23f10434ab743ea292c22edcc" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-92cef87c45734b0592953b544d55818b" href="zhaopinList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;招聘信息</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-3556301c31ac4747965b53ca3c940191" href="personList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;人才信息</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-5cbbb1056ebf471b8922e69f157455c5" href="laowuList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;劳务派遣</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-822de1f5c7594e7f9586b0685c028541" href="#collapse-822de1f5c7594e7f9586b0685c028541" title=""><i class="icon-chevron-right"></i>&nbsp;营销管理</a>
		    </div>
		    <div id="collapse-822de1f5c7594e7f9586b0685c028541" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-9ff71084c3c44748a3a699d05d124ff9" href="emailList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;营销查询</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-9fd2bbad28414d7b9df3d871cda6543c" href="smsSend/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;短信营销</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-23e1d105dd774ba492ea4f1006d3bc73" href="emailSend/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;邮件营销</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-07070e9f5f504140ac5327d9ea387ab1" href="#collapse-07070e9f5f504140ac5327d9ea387ab1" title=""><i class="icon-chevron-right"></i>&nbsp;报表统计</a>
		    </div>
		    <div id="collapse-07070e9f5f504140ac5327d9ea387ab1" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-2a119dc5be3348a4991ebbd565e9d783" href="shebaoCount/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;社保费用统计</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-2f138487b1ba4ee09f0d67a45ae57c67" href="gongjijinCount/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;公积金统计</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-bd2d297719a246ce90137b7c9ca6b909" href="salaryCount/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;工资费用统计</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-d97120cbc31f424dbd12959da541fbda" href="personCount/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;人才外包统计</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-aa3843cca0254bcb807123e8284bb193" href="caiwuCount/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;财务报表统计</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-c082a1303cf14573916c963071bdddc4" data-href="#collapse-b82f37a26c1f413588db85b2eaf782e2" href="#collapse-b82f37a26c1f413588db85b2eaf782e2" title=""><i class="icon-chevron-right"></i>&nbsp;内容管理</a>
		    </div>
		    <div id="collapse-b82f37a26c1f413588db85b2eaf782e2" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a data-href=".menu3-c96dfbfd2691492f82c8b7de876f7c92" href="newsList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;新闻管理</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-d6d92d0fe5504a79953aaabf7b68497f" href="messageList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;留言管理</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-ac5ab060930f4b479f2f462b437e1631" href="replayList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;回复管理</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	</div>

	<div class="accordion" id="menu-2">
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-2" data-href="#collapse-7c8cd0b6c9e3409aae7e9ad9bd2e74b1" href="#collapse-7c8cd0b6c9e3409aae7e9ad9bd2e74b1" title=""><i class="icon-chevron-down"></i>&nbsp;系统管理</a>
		    </div>
		    <div id="collapse-7c8cd0b6c9e3409aae7e9ad9bd2e74b1" class="accordion-body collapse in">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li class="active"><a data-href=".menu3-bfeacdcd326b476181423fe8ec0cb527" href="index/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right icon-white"></i>&nbsp;用户管理</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-309beca7d55648b2a3a7388495baafa6" href="roleList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;角色管理</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-f6451cba50e5423bbf9121236a878ef6" href="menuList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;菜单管理</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-fd53dc0e386d4230be276ef19a8cf5b4" href="dictList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;数据字典</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-c9d372c1a9c84bbe8c46686c41d67cfb" href="smsTemplateList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;短信模板</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li>
						<li><a data-href=".menu3-aad070d5b282458996dcf847542926b8" href="emailTemplateList/saved_resource.html" target="mainFrame"><i class="icon-circle-arrow-right"></i>&nbsp;邮件模板</a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0;">
							
							</ul></li></ul>
				</div>
		    </div>
		</div>
	</div></div>
				<div id="openClose" class="close" style="height: 505px;">&nbsp;</div>
				<div id="right" style="height: 510px; width: 1185px;">
					<iframe id="mainFrame" name="mainFrame" src="./index/saved_resource.html" style="overflow: visible; height: 510px;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    <div id="footer" class="row-fluid">
	            Copyright © 2012-2017 安思普惠人事代理系统 - Powered By <a href="http://jeesite.com/" target="_blank">JeeSite</a> V1.2.7
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// 
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}// 
	</script>
	<script src="./index/wsize.min.js" type="text/javascript"></script>
</body></html>