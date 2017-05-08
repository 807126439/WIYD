<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>error</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <%@include file="/page/common/script/mytop.jsp" %>

  

  </head>
  
 
<body >
  
    <div class="pd-20" style="padding-top:20px;">
         <div style="margin: 0 10px 0 10px">
		    <p style="font-size: 14px;">
			    <div class="Huialert Huialert-danger">
			    	${ex}		    	
			    </div>		    
		    </p>
		    <p>
		      <div class="btn-group">
			      <a class="btn btn-danger radius" href="javascript:;" onclick="showErrorContext()">查看错误信息</a>			     
			      <a class="btn btn-warning radius" href="javascript:history.go(-1);" target="_parent">返回</a>
			    
         
   			  </div>
		    </p> 
		    <div id="errorContext" style="display: none;">
			    <p>
			     <div class="Huialert Huialert-info" style="font-size: 14px;line-height: 32px;">
					${exceptionMessage}	
			     </div>
		     </div>
		    </p>
	    </div>
    </div>
    <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
      function showErrorContext(){
        if($("#errorContext").is(":hidden")){
           $("#errorContext").show();
        }else{
          $("#errorContext").hide();
        }
         
      
      }
    
    </script>
   

</body>
</html>
