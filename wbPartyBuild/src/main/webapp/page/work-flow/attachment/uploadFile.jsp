<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传附件</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="renderer" content="webkit|ie-stand">
	
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />

  </head>
  
  
  <body>
	<div class="pt-5" >
	
            <input type="hidden" id="taskId" name="taskId" value="${taskId}">
           
			<div class="col-1 col-xs-1"></div>
			<div class="col-10 col-xs-10">
				<div id="uploader" class="uploader-list-container"> 
					<div class="queueList">
						<div id="dndArea" class="placeholder" style="min-height: 160px;">
							<div id="filePicker-1"></div>
							
						</div>
					</div>
					<div class="statusBar" style="display:none;">
						<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
						<div class="info"></div>
						<div class="btns">
							<div id="filePicker2"></div>
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-1 col-xs-1"></div>
	
	
	        
	  
	</div>
		
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>	
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/work-flow/attachment/uploadFile.js"></script>
	

</body>
  
  
  
</html>
