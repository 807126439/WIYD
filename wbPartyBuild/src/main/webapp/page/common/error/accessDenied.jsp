<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%
	String path = this.getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
    <title>禁止页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <%@include file="/page/common/script/mytop.jsp" %>
	
</head>

<body>

	<section class="page-404 minWP text-c">
	  <p class="error-title"><i class="Hui-iconfont va-m" style="font-size:80px">&#xe656;</i><span class="va-m">错误</span></p>
	  <p class="error-description">不好意思，您无权访问页面</p>
	  <p class="error-info">您可以：<a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a><span class="ml-20">|</span><a href="<%=path %>" target="_top" class="c-primary ml-20">去首页 &gt;</a></p>
	</section>
	
</body>

</html>
