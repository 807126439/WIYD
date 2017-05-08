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
    
    <title>区间路径列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>
	
	

  </head>
  
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

   <div class="Hui-article" >
		<div class="page-container">
			<div class="t_ctrlbar">	
			  <span class="btns">	
			  	<m:a_top context="添加路径" operCode="skipAddZonePath"  funMethod="zone_path_add('添加路径','zonePathController/skipAddZonePath.do','800','480')"  className="btn btn-primary radius"/>		
				<m:a_top context="生成目录" operCode="createFileDirectory"  funMethod="data_create_dir()" className="btn btn-success radius" />
				<m:a_top context="批量删除" operCode="delZonePath"  funMethod="datadel()" className="btn btn-danger radius" />
			   
			  </span>
			
			</div>
			
			<table id="zone_path_table" class="table table-border table-bordered table-bg">
			
			</table>
		</div>
				
		</div>					
		
		 <%@include file="/page/common/script/base-js.jsp" %>
		
		
		<m:a_button operCode="getZonePathDetail"  funMethod="zone_path_edit('编辑','{0}','800','480')" context="编辑" varName="edit_zone_path" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="delZonePath"  funMethod="zone_path_del(this,'{0}')" context="删除" varName="del_zone_path" className="t-btn btn size-MINI btn-danger-outline"/>	
       

		<script type="text/javascript" src="<%=path%>/plug-in/web/system/zone/zone-path-list.js"></script> 
		<script type="text/javascript" src="<%=path %>/plug-in/common/js/common-table.js"></script> 
		
</body>
  
  
  
</html>
