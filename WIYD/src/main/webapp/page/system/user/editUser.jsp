<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/userController/editUser.do" method="post" class="form form-horizontal" id="form-user-edit">	  
		    <%@include file="/page/system/user/common/edit-item.jsp" %>  
     	    
	    	<input type="hidden" name="id" value="${userItem.id}" >
	  	 
	  </form>
	  
	  
	</div>
		
		
		
	<%@include file="/page/common/script/base-js.jsp" %>
	
	<script type="text/javascript" src="<%=path%>/plug-in/web/system/user/user-edit.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/web/system/user/role-for-user.js"></script> 
		
		
		
		
</body>
  
  
</html>
