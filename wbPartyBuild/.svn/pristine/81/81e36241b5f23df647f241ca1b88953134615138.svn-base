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
    <title>评论数统计</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">
	
  </head> 
  <body>
  	<%@include file="/page/common/nav/breadcrumb.jsp" %>
	<div class="Hui-article" >
		<div class="page-container">		
			<div class="t_searchbar">				
				 评论时间：<input id="commentTimeBegin" class="input-text Wdate" onfocus="WdatePicker()"  style="width:120px;" type="text">-
				<input id="commentTimeEnd" class="input-text Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'commentTimeBegin\')}'})" style="width:120px;" type="text">		
			  	姓名：<input type="text"  id="username" class="input-text" style="width:150px" placeholder="姓名" >		
			  	<%@include file="/page/portals/common/selectDepartment.jsp" %>		
				<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			
			<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
			 	<ul id="departmentTree" class="departmentTree ztree" style="margin-top:0;  height: 300px;"></ul>
			</div>
								
			<div class="t_ctrlbar"> 
			 <span class="btns">
			   <m:a_top context="导出" operCode="exportCountComment" funMethod="exportExcel()"  className="btn btn-primary radius"/>
			 </span> 
			</div>	
		
										
		    <table id="commentCount_table" class="table table-border table-bordered table-hover table-bg table-sort"></table>
		</div> 
	</div>	     
	<%@include file="/page/common/script/mybottom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/common/js/selectDepartment.js"></script> 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/comment/commentCount-list.js"></script> 	    
  </body> 
</html>