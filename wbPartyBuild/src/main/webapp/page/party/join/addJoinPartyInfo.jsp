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
    
    <title>添加入党申请信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/joinPartyInfoController/addJoinPartyInfo.do" method="post" class="form form-horizontal" id="form-join-party-add">
	    
	    <%@include file="/page/party/join/common/editItem.jsp" %>
	    
 
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">
	        <m:b_button operCode="addJoinPartyInfo" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	     
	    </div>
	  </form>
	        
	  
	</div>
		
 	<%@include file="/page/system/user/common/userList.jsp" %>
	<%@include file="/page/common/script/allbuttom.jsp" %>
	    
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/party/join/join-party-add.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/common/user-list.js"></script>		
		
		
</body>
  
  
  
</html>
