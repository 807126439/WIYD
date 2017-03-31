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
    
    <title>流程定义列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	
	<input type="hidden" id="id" value="${id}"/>
   <div class="Hui-article" >
	<div class="pd-20">
			<div class="text-c">
		
				<input type="text"  class="input-text" style="width:250px" placeholder="节点标识符" id="nodeCode" >&nbsp;&nbsp;
				<input type="text"  class="input-text" style="width:250px" placeholder="节点名称" id="nodeName" >&nbsp;&nbsp;
				<input type="text"  class="input-text" style="width:150px" placeholder="节点类型" id="nodeType" >
				<button type="button" class="btn btn-success" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			
			</div>

			
			<table id="node_table" class="table table-border table-bordered table-bg">
			
			</table>
		</div>
				
		</div>					
		
		 <%@include file="/page/common/script/mybottom.jsp" %>
		
		
		<m:a_button operCode="getProcNodeDetail"  funMethod="node_edit('编辑流程图','{0}','','')" context="编辑" varName="edit_node" />
		


		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/work-flow/node/node-list.js"></script> 
		
</body>
  
  
  
</html>
