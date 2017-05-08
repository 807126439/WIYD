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
    
    <title>日志列表</title>
    
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
			
					时间段：<input type="text" onfocus="WdatePicker()" id="datemin" class="input-text Wdate" style="width:120px;">
					-
					<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}'})" id="datemax" class="input-text Wdate" style="width:120px;">
					&nbsp;&nbsp;用户名：<input type="text" class="input-text" style="width:250px" placeholder="用户名" id="log-user" name="">
					<button type="button" class="btn btn-danger" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				
				</div>
				
				<div class="t_ctrlbar">
				 <span class="btns">
				 	<m:a_top context="删除" operCode="delLoginLog" funMethod="datadel()" className="btn btn-danger radius" />
				  
				  </span>
				
				</div>
				
			
				<table id="log_table" class="table table-border table-bordered table-bg">
				</table>
					
			</div>
					
		</div>			
	
		
		<%@include file="/page/common/script/mybottom.jsp" %>
		
	
		<m:a_button operCode="delLoginLog"  funMethod="log_del(this,'{0}')" context="删除" varName="del_log" className="t-btn btn size-MINI btn-danger-outline"/>


		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/log/log-list.js"></script> 
		
</body>
  
  
  
</html>
