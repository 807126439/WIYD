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
    
    <title>添加字典</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/dictController/addDict.do" method="post" class="form form-horizontal" id="form-dict-add">
	    
	    <%@include file="/page/system/dict/common/editItem.jsp" %>
	    
 
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">
	        <m:b_button operCode="addDict" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	     
	    </div>
	  </form>
	        
	  
	</div>
		

	
	  <%@include file="/page/common/script/base-js.jsp" %>
	    
	<script type="text/javascript" src="<%=path%>/plug-in/web/system/dict/dict-add.js"></script> 
		
		
		
</body>
  
  
  
</html>
