<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>
  <head>
     <base href="<%=basePath%>">
    
	  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	  <meta name="renderer" content="webkit|ie-stand">
	  <title>文件预览</title>


   	 <%@include file="/page/common/script/main-css.jsp" %>
	<style type="text/css">
	 .pic{max-height: 700px}
	
	</style>

</head>




<body>


	   <div class="pl-20 mt-10" >
			<div class="row cl" style="width: 1000px;margin: auto;">		          		          
			    <img alt="" src="${result.filePath}" class="pic">		          
	       </div>
	
		</div>


	
	
		<script type="text/javascript" src="<%=path %>/plug-in/main/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/plug-in/main/js/public.js"></script>

		
</body>
</html>
