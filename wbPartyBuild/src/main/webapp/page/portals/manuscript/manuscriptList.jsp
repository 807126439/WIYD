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
    
    <title>稿件列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<style type="text/css">
	  .authorbox{position: absolute;bottom: 0;left: 0;right: 0;color: #777;font-size: 12px;line-height: 16px;text-align: center;background-color: #eee;}
	  .item-area li{height: 178px;}
	  .item-area li .itembox{height: 168px;}
	  .item-area li .textbox {bottom: 16px;background-color: #eee;}
    </style>

  </head>
  <body>
	<div class="page-container">
		<div class="t_ctrlbar"> 
			 <span class="btns">			   	
			 	<m:a_top context="批量审核"  operCode="skipCheckManuscript" funMethod="datacheck()" className="btn btn-primary radius" />
			 	<m:a_top context="批量删除"  operCode="delManuscript" funMethod="datadel()" className="btn btn-danger radius" />				     
			 </span> 
		</div>
		<table id="manuscript_table" class="table table-border table-bordered table-hover table-bg table-sort"></table>			
	</div>  
	<%@include file="/page/common/script/mybottom.jsp" %>	
	<input type="hidden" id="activityId" value="${activityId}">
	<m:a_button operCode="skipCheckManuscript"  funMethod="manuscript_check('{0}','','')" context="审核" varName="check_manuscript" />	
	<m:a_button operCode="skipEditManuscript"  funMethod="manuscript_edit('{0}','','')" context="编辑" varName="edit_manuscript" />	
	<m:a_button operCode="delManuscript"  funMethod="manuscript_del(this,'{0}')" context="删除" varName="del_manuscript" />
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/manuscript/manuscript-list.js"></script> 
</body>   
</html>