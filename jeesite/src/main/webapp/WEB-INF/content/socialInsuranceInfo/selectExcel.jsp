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
</head>
<body>
	<form id="inputForm">
		选择对应模板的文件：<input type="file" name="excel">
		<input type="button" id="submit"  value="提交">
	</form>
	<script type="text/javascript">
		$("#submit").on("click",function(){
			var formobj=document.getElementById("inputForm");
			var formdata=new window.FormData(formobj);
				$.ajax({
						url:"business/socialInsurance/uploadExcel.do",
						data:formdata,
						type:"post",
						cache:false,
						processData:false,
						contentType:false,
						success:function(data){
							alert(data.message);
							}
						})
		})
	</script>
</body>
</html>