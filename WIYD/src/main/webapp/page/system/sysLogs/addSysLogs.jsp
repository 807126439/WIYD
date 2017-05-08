<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="renderer" content="webkit|ie-stand">
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="">
	
	<%@include file="/page/common/script/base-css.jsp" %>

  </head>
  
  
  <body>
  
		<div class="pd-20">
		  <form action="<%=path %>/sysLogsController/addSysLogs.do" method="post" class="form form-horizontal" id="form-model-add">
		    
		    <%@include file="/page/system/sysLogs/common/editItem.jsp" %>
		    
	 
		    <div class="row cl">
		      <div class="col-xs-9 col-xs-offset-3">
		        <m:b_button operCode="addSysLogs" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
		      </div>
		     
		    </div>
		  </form>
		        
		  
		</div>
			
		<%@include file="/page/common/script/base-js.jsp" %>
	
				
	    <script type="text/javascript" src="<%=path%>/plug-in/web/system/sysLogs/sysLogs-add.js"></script> 
		
		
  </body>
  
  
  
</html>