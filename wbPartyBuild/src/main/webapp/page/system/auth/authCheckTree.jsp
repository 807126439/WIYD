<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String roleId ="";

if(request.getParameter("roleId")!=null){
	roleId = request.getParameter("roleId");
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<%=basePath%>">
	<title>权限树</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/tree/css/treeAuth.css" rel="stylesheet" type="text/css" />
	
	


</head>




<body>

  	<div>
	   <div class="prettyprint linenums" style="width:378px;height: 322px;overflow:scroll;">
		  <div id="tree"></div>
		  <input type="hidden" id="roleId" value="<%=roleId%>">
		</div>
	</div>

	     

		  	
	
	<script src="<%=path %>/plug-in/tree/src/jquery.js" type="text/javascript"></script>
    <script src="<%=path %>/plug-in/tree/src/Plugins/jquery.authtree.js" type="text/javascript"></script>
	<script src="<%=path %>/plug-in/web/system/auth/auth.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/static/h-ui/js/H-ui.js"></script>

		
</body>
</html>
