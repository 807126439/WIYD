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
    
    <title>广告列表</title>
    
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
								
				<div class="t_ctrlbar"> 
					 <span class="btns">
					   	
					    <m:a_top context="发布广告"  operCode="skipAddAds"  funMethod="ads_add('广告发布','','')"  className="btn btn-primary radius"/>				 		 			     
					 	<m:a_top context="删除"  operCode="delAds" funMethod="datadel()" className="btn btn-danger radius" />
					     
					 </span> 
				 			
				</div>
				
			
			
				<table id="ads_table" class="table table-border table-bordered table-hover table-bg table-sort">			
				</table>
		
			
			
	  </div>
	</div>			

		<%@include file="/page/common/script/mybottom.jsp" %>
	
		
		<m:a_button operCode="queryAdsDetail"  funMethod="ads_edit('{0}','','')" context="编辑" varName="edit_ads" className="t-btn btn size-MINI btn-primary-outline"/>	
		<m:a_button operCode="delAds"  funMethod="ads_del(this,'{0}')" context="删除" varName="del_ads" className="t-btn btn size-MINI btn-danger-outline"/>
			

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/ads/ads-list.js"></script> 
	    
		
</body>
  
  
  
</html>
