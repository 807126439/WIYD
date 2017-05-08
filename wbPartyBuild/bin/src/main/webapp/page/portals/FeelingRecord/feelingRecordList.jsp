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
    
    <title>心得体会管理</title>
    
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
					标题：<input type="text" class="input-text" style="width:250px" placeholder="标题" id="title" >&nbsp;
					<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				<br>
				<div class="t_ctrlbar"> 
					 <span class="btns">
					 	 <m:a_top operCode="delFeelingRecord" context="批量删除" funMethod="datadel()" className="btn btn-danger radius" /> 
					</span> 
				</div>	
		
					<table id="feelingRecord_table"  class="table table-border table-bordered table-hover table-bg table-sort">
				</div>
		</div>	
		</div>
		<%@include file="/page/common/script/mybottom.jsp" %>
<%-- 		<m:a_button operCode="updateFeelingRecord"  funMethod="feelingRecord_edit('{0}','','')" context="修改" varName="update_feelingRecord" />
 --%>		<m:a_button operCode="detailedFeelingRecord"  funMethod="feelingRecord_detailed('{0}','','')" context="查看" varName="detailed_feelingRecord" className="t-btn btn size-MINI btn-primary-outline"/>
 			<m:a_button operCode="delFeelingRecord"  funMethod="feelingRecord_del(this,'{0}')" context="删除" varName="delete_feelingRecord" className="t-btn btn size-MINI btn-danger-outline"/>
 		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/FeelingRecord/feelingRecord-list.js"></script>
  </body>
</html>
