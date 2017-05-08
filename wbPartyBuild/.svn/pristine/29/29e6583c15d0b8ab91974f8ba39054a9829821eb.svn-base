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
    
    <title>附件列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>


	<div class="pd-20">
			<div class="text-c">
		
				<input type="text"  class="input-text" style="width:250px" placeholder="附件名称" id="attaName" >
				<button type="button" class="btn btn-success" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			
			</div>

			<div class="cl pd-5 bg-1 bk-gray mt-20">
			 <span class="l">
				  <m:a_top context="设置公开" operCode="setAttaStatus" icon="" funMethod="dataSet('open')" className="btn btn-success radius" />
				  <m:a_top context="设置隐藏" operCode="setAttaStatus" icon="" funMethod="dataSet('hide')"  className="btn btn-warning radius"/>		  
			 </span>
			   
			</div>
			
			<table id="file_table" class="table table-border table-bordered table-bg">
			
			</table>
			
			<input type="hidden" id="procInstId" value="${procInstId}">
		</div>
				
					
		
		 <%@include file="/page/common/script/mybottom.jsp" %>
		
		
		<m:a_button operCode="downAttachment"  funMethod="downFile('{0}')" context="下载" varName="file_down" />
		
		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/work-flow/attachment/attachment-list.js"></script> 
		
</body>
  
  
  
</html>
