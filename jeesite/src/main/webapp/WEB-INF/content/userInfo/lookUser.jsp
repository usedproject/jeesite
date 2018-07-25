<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../script.html" %>
</head>
<body>
	<br>
	<br>
	<br>
	<form  id="inputForm">
	<table style="margin-left: auto; margin-right: auto" align="center" > 
   <tbody>
    <tr> 
     <td>用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名:<input type="hidden" name="id" value="${user.id }"> </td> 
     <td><input class="easyui-textbox" id="username" name="username" style="width:300px;height:35px;line-height:35px;" value="${user.username }" /></td> 
    </tr> 
    <tr>
     <td> 邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
     <td><input class="easyui-textbox" id="email" name="email" style="width:300px;height:35px;line-height:35px;" value="${user.email }"/> </td>
    </tr> 
    <tr>
     <td> 电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
     <td><input class="easyui-textbox" id="phone" name="phone" style="width:300px;height:35px;line-height:35px;" value="${user.phone }" /> </td>
    </tr> 
    <tr>
    	<td colspan="2" align="center">
    	<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-save" >保存</a>
    	<a href="javascript:clear()" class="easyui-linkbutton" iconCls="icon-clear" >复原</a>
    	 </td>
    </tr>   
   </tbody>
  </table>
  	</form>
</body>
<script type="text/javascript">
	function save(){
		var username=$("#username").val();
		var email=$("#email").val();
		var phone=$("#phone").val();
		if(username==""){
			alert("请输入用户名！！！")
			return;
		}
		if(email==""){
			alert("请输入邮箱");
			return;
		}
		if(phone==""){
			alert("请输入手机号码");
			return ;
		}
		$.post(
			"system/user/editUser/"+${user.id}+".do",
			$("#inputForm").serialize(),
			function(data){
				if(data.success){
					alert(data.message);
				}else{
					alert(data.message);
				}
			},
			"json"
		);
	}
	
	function clear(){
		
	}
</script>
</html>