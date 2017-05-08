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
    
    <title>我的任务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

   <div class="Hui-article" >
	<div class="page-container">
			<div class="t_searchbar">
		
				任务名称：<input type="text"  class="input-text" style="width:250px" placeholder="任务名称" id="taskName" >
				<button type="button" class="btn btn-danger radius" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			
			</div>

			
			<table id="task_table" class="table table-border table-bordered table-bg">			
			</table>
		</div>
				
		</div>					
		
		 <%@include file="/page/common/script/mybottom.jsp" %>
		
		
		<m:a_button operCode="handleTask"  funMethod="task_handle('任务办理','{0}','','')" context="办理" varName="handle_task" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="rollBackTask"  funMethod="task_roll('{0}')" context="回到上一步" varName="roll_task" className="t-btn btn size-MINI btn-danger-outline"/>
		<m:a_button operCode="getCurrProcess"  funMethod="process_view('{0}')" context="流程图" varName="view_process" className="t-btn btn size-MINI btn-primary-outline"/>	
       

		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/work-flow/task/task-list.js"></script> 
		
</body>
  
  
  
</html>
