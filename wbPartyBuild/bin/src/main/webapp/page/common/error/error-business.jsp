<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误</title>
    
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  <body>
 		<br><br><br><br><br>
		<div class="row cl">
			<div class="col-xs-2 col-sm-2"></div>
			<div class="col-xs-8 col-sm-8">
			   <p class="mt-10"><input class="btn btn-block btn-primary radius" value="信息提示" type="button"></p>			 
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
		
		
		
      
       
    	<div class="row cl">
    			
			   <div class="col-xs-2 col-sm-2"></div>
				<div class="col-xs-8 col-sm-8">
					<div class="Huialert Huialert-info" style="height: 100px;">
					   ${exceptionMessage}					 				  
					</div>						
					
				</div>					
				
				<div class="col-xs-2 col-sm-2"></div>
		</div>
  
         <div class="row cl">
        	 <div class="col-xs-2 col-sm-2"></div>
			   <div class="col-xs-8 col-sm-8">
			        <a class=" btn btn-primary radius" href="javascript:history.go(-1);" target="_parent" style="float: right;">返回</a>
			   </div>
			   <div class="col-xs-2 col-sm-2"></div>
				
		</div>


</body>
</html>
