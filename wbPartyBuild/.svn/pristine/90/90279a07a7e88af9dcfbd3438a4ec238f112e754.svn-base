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

  </head>
  
  
  <body>


				<div class="Hui-article" >
					<div class="page-container">
		
			 		 	
								
				<div class="t_ctrlbar"> 
				 <span class="btns">
				   	
				<%--<m:a_top context="上传作品"  operCode="skipAddManuscript" icon="&#xe600;" funMethod="manuscript_add('互动议题','','')"  className="btn btn-primary radius"/>--%>		 		 			     
				 	<m:a_top context="批量删除"  operCode="delManuscript" icon="&#xe6e2;" funMethod="datadel()" className="btn btn-danger radius" />
				     
				 </span> 
				 
			
				</div>
				
				
				
			
				<table id="manuscript_table" class="table table-border table-bordered table-hover table-bg table-sort">
				</table>
			
			
			
	  </div>
			

		<%@include file="/page/common/script/mybottom.jsp" %>
	
		<input type="hidden" id="activityId" value="${activityId}">
		<input type="hidden" id="banChunkId" value="${banChunkId}">
		<m:a_button operCode="skipCheckManuscript"  funMethod="manuscript_check('{0}','','')" context="审核" varName="check_manuscript" />	
		<m:a_button operCode="skipEditManuscript"  funMethod="manuscript_edit('{0}','','')" context="编辑" varName="edit_manuscript" />	
		<m:a_button operCode="delManuscript"  funMethod="manuscript_del(this,'{0}')" context="删除" varName="del_manuscript" />
		<m:a_button operCode="downLoadManuscript"  funMethod="manuscript_downLoad(this,'{0}')" context="下载文件" varName="downLoad_manuscript" />	

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/manuscript/cityBuilderManuscript-list.js"></script> 
	    
		
</body>
  
  
  
</html>
