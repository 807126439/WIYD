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
    
    <title>添加互动议题</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	 

  </head>
  
  
  <body>
	
	<div class="pd-20">
	  <form action="<%=path %>/feelingRecordController/addFeelingRecord.do" method="post" class="form form-horizontal" id="form-commun-add" >
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2" ><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text"  name="title"  datatype="*2-80"  nullmsg="标题不能为空">
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">内容：</label>
			<div class="formControls col-xs-8 col-sm-8" > 
				<script id="editor" type="text/plain" style="width:100%;height:300px;"></script> 
			</div>
		</div>
		
	  
	    <div class="row cl">
	      <div class=" col-xs-offset-3" style="margin-left:42%;">
	      <input class="btn btn-primary radius " style="margin-top: 10px;border-style:none;margin-bottom: 10px;background-color: #C82619;" type="submit" value="提&nbsp;&nbsp;&nbsp;&nbsp;交">
	      </div>
	    </div>
	    
	    
	  </form>
	  
	  
	</div>
	
	
		

	
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
 	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script> 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/FeelingRecord/feelingRecord-add.js"></script> 
</body>
  
  
  
</html>
