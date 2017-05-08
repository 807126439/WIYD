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
    
    <title>修改广告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	 <link href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />

  </head>
 
  
  <body>
	<div class="pd-20">
	  <form  action="<%=path %>/advertisementController/editAds.do" method="post" class="form form-horizontal" id="form-ads" >
	  	
	
		<%@include file="/page/portals/ads/common/edit-item.jsp" %>
		
		<input type="hidden" id="ads-id" name="adsId" value="${adsItem.adsId}">
	  
	    <div class="row cl">
	      <div class="col-xs-8 col-xs-offset-2 col-sm-offset-2" >
	         <m:b_button operCode="editAds" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	    
	    
	  </form>
	  
	  
	</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
			
	
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script> 	
 	<script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/ads/thumb-upload.js"></script> 		  
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/ads/ads-edit.js"></script>
		

		
		
</body>
  
  
  
</html>
