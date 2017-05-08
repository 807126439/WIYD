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
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

	 <div class="Hui-article" >
			<div class="page-container">
				<div class="t_searchbar">																								
					时间：<input type="text" onfocus="WdatePicker()" id="datemin" class="input-text Wdate" style="width:150px;">
					-
					<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}'})" id="datemax" class="input-text Wdate" style="width:150px;">
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
				</div>
				
				<div class="t_ctrlbar">	
					<span class="btns">					   				     				 		 
						<m:a_top context="删除"  operCode="deleteSysLogs"  funMethod="datadel()" className="btn btn-danger radius" />
					</span>									 			
				</div>
			
				
				<table id="sysLogs_table" class="table table-border table-bordered table-bg table-hover table-sort"></table>
				
				
			</div>
			
		</div>
			
		<%@include file="/page/common/script/base-js.jsp" %>
	
		
		<m:a_button operCode="deleteSysLogs"  funMethod="del_model(this,'{0}')" context="删除" varName="model_del" className="t-btn btn size-MINI btn-danger-outline"/>
			

	   <script type="text/javascript" src="<%=path%>/plug-in/web/system/sysLogs/sysLogs-list.js"></script> 
		
</body>
  
  
  
</html>