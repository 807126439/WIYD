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
    
    <title>修改评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	

  </head>
 
  
  <body>
	<div class="pd-20">
	 <form action="<%=path %>/commentController/editComment.do" method="post" class="form form-horizontal" id="form-comment-edit" >       
	    <input type="hidden" name="id" value="${result.id}">
		<input type="hidden" name="type" value="1">
		
<%-- 		<div class="row cl">
	    	<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>评论日期：</label>
		    <div class="formControls col-xs-3 col-sm-3">
				<input type="text" name="commentTime" onfocus="WdatePicker()" class="input-text Wdate"  datatype="*8-20"  nullmsg="评论日期不能为空" value="<fmt:formatDate value="${result.commentTime}" pattern="yyyy-MM-dd"/>">					 
			</div>
		   <div class="col-xs-5 col-sm-5"></div>
		</div> --%>
  
	  
	    <div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">评论：</label>
			<div class="formControls col-xs-7 col-sm-7" style="width: 500px;"> 
				<script id="editor" type="text/plain" style="width:100%;height:200px;">${result.content}</script> 
			</div>
		</div>
	  
   
	   
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="editComment" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	    
	  </form>
	  	  
	</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script> 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/comment/comment-edit.js"></script>
		
	
</body>
  
  
  
</html>
