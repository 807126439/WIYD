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
    
    <title>活动规则列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>


	<div class="Hui-article" >
		<div class="page-container">
								
				<div class="t_ctrlbar"> 
					 <span class="btns">				   	
					    <m:a_top context="新增活动规则"  operCode="skipAddActivityRule"  funMethod="activityRule_add('新增活动规则','700','500')"  className="btn btn-primary radius"/>				 		 			     
					 	<m:a_top context="批量删除"  operCode="delActivityRule"  funMethod="datadel()" className="btn btn-danger radius" />				     
					 </span> 
				</div>
				

				<table id="activityRule_table" class="table table-border table-bordered table-hover table-bg table-sort">
				</table>
			</div> 
	  </div>
			
		<input type="hidden" id="activityId" value="${activityId}">
		<%@include file="/page/common/script/mybottom.jsp" %>
		<m:a_button operCode="skipEditActivityRule"  funMethod="activityRule_edit('{0}','','')" context="编辑" varName="edit_activityRule" />	
		<m:a_button operCode="delActivityRule"  funMethod="activityRule_del(this,'{0}')" context="删除" varName="del_activityRule" />
			

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/activityRule/activityRule-list.js"></script> 
																				    

</body>
  
  
  
</html>
