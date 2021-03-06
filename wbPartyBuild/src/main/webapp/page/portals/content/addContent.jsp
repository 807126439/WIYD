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
    
    <title>添加内容</title>
    
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
	  <form action="<%=path %>/contentController/addContent.do" method="post" class="form form-horizontal" id="form-content" >
	  	
	
		<%@include file="/page/portals/content/common/edit-item.jsp" %>
		

	  
	    <div class="row cl">
	      <div class="col-xs-8 col-xs-offset-2 col-sm-offset-2" >
	         <m:b_button operCode="addContent" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	    
	    
	  </form>
	  <input type="hidden" id="ct-id" value="";>
	  
	</div>
	
	
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/lang/zh-cn/zh-cn.js"></script> 
	
				
	<c:if test="${typeId eq 3 || typeId eq 6}"><!-- 图片和周刊类型 -->
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>
	 	<script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/thumb-upload.js"></script> 		
    </c:if>	
    
    <c:if test="${typeId eq 5}"><!-- 视频类型 -->
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>
	 	<script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/video-upload.js"></script> 	
    </c:if>	
    
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/content-add.js"></script> 
</body>
  
  
  
</html>
