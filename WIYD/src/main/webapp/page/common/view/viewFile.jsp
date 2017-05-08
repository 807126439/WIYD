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
    
	  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	  <meta name="renderer" content="webkit|ie-stand">
	  <title>文件预览</title>


   	 <%@include file="/page/common/script/main-css.jsp" %>


</head>




<body>

	
	 <!--  <div class="topnav clearfix" >
	    <span  class="loc-text" >文件：${result.fileName}</span>
	   
	  </div>-->
	 
	  <div class="pl-20 mt-10">
			<div class="row cl" style="width: 1000px;margin: auto;">
		          
		             <div id="flashContent"> 
			        	<p> 
				        	To view this page ensure that Adobe Flash Player version 
							10.0.0 or greater is installed. 
						</p> 
		      	    </div>
		           
		          
	       </div>
	
		</div>


	
	
		<script type="text/javascript" src="<%=path %>/plug-in/main/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/plug-in/main/js/public.js"></script>

	    
	    <script type="text/javascript" src="<%=path %>/plug-in/flexpaper/js/swfobject/swfobject.js"></script>
		<script type="text/javascript" src="<%=path %>/plug-in/flexpaper/js/flexpaper_flash.js"></script>
		<script type="text/javascript"> 
         
            var swfVersionStr = "10.0.0";
           
            var xiSwfUrlStr = "playerProductInstall.swf";
            
            var flashvars = { 
                  SwfFile : "${result.filePath}",//swf文件路径
				  Scale : 0.6, 
				  ZoomTransition : "easeOut",
				  ZoomTime : 0.5,
  				  ZoomInterval : 0.1,
  				  FitPageOnLoad : false,
  				  FitWidthOnLoad : true,
  				  PrintEnabled : true,
  				  FullScreenAsMaxWindow : false,
  				  ProgressiveLoading : true,
  				  
  				  PrintToolsVisible : true,
  				  ViewModeToolsVisible : true,
  				  ZoomToolsVisible : true,
  				  FullScreenVisible : true,
  				  NavToolsVisible : true,
  				  CursorToolsVisible : true,
  				  SearchToolsVisible : true, 				  
  				  localeChain: "zh_CN"
				 };
				  
			 var params = {};
            params.quality = "high";
            params.bgcolor = "#ffffff";
            params.allowscriptaccess = "sameDomain";
            params.allowfullscreen = "true";
            var attributes = {};
            attributes.id = "FlexPaperViewer";
            attributes.name = "FlexPaperViewer";
            swfobject.embedSWF(
                "<%=path%>/plug-in/flexpaper/FlexPaperViewer.swf", "flashContent", 
                "900", "750",
                swfVersionStr, xiSwfUrlStr, 
                flashvars, params, attributes);
			swfobject.createCSS("#flashContent", "display:block;text-align:left;");
			
		
			
			
			
			
			
			
        </script> 
		
	
		
</body>
</html>
