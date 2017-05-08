<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String nodes = (String )request.getAttribute("nodes");
out.print("<script type='text/javascript'> var zNodes = "+nodes+";</script>");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加角色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">

  </head>
  
  
  <body class="pos-r">
  
  	<div class="pos-a" style="width:480px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; ">
		  <div class="pd-20">
			  <form action="<%=path %>/roleController/addRole.do" method="post" class="form form-horizontal" id="form-role-add">
			  	<%@include file="/page/system/role/common/edit-item.jsp" %>
	
			  </form>
			        
			  
			</div>  
		
		
 	 </div>
  
    <%@include file="/page/system/role/common/wholeAuthTree.jsp" %>
  
		
	<%@include file="/page/common/script/base-js.jsp" %>	
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
	  
 
	<script type="text/javascript" src="<%=path %>/plug-in/web/system/role/common-edit.js"></script>   
	<script type="text/javascript" src="<%=path %>/plug-in/web/system/role/role-add.js"></script>
  	
		
		
</body>
  
  
  
</html>
