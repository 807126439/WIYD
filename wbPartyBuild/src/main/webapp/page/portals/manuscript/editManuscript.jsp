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
    
    <title>修改稿件</title>
    
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
	 <form action="<%=path %>/manuscriptController/editManuscript.do" method="post" class="form form-horizontal" id="form-manuscript-edit" >       
		<input id="msId" name="msId" value="${result.msId}" type="hidden">
	    <input id="activityId" name="activityId" value="${activityId}" type="hidden">
	    <input name="operateType" value="1" type="hidden">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text"  name="title" id="title" datatype="*2-80"  nullmsg="标题不能为空"  value="${result.title}">
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>作者：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text"  name="author" id="author" datatype="*2-80"  nullmsg="作者不能为空" value="${result.author}">
			</div>
			<div class="col-sm-4"></div>
		</div>
			
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">描述：</label>
			<div class="formControls col-xs-8 col-sm-8">
			<textarea name="description" class="textarea" id="description"   placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,800)" style="height:320px">${result.description}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/800</p>
			</div>
		</div>


		      
	    <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">图标：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<div class="uploader-thum-container">
					<div id="fileList" class="uploader-list">
						<div id="item" class="item">
					   	 	<div class="pic-box"><img src="${result.pattern}" width="96" height="96"></div>									
						</div>
					</div>
					<div id="filePicker">选择图片</div>
					<button id="btn-cancel" class="btn btn-default btn-uploadstar radius ml-10" type="button">撤销图片</button>
				</div>
			</div>
			<input id="pattern" value="${result.pattern}" type="hidden">
		</div>
		    	    
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="addManuscript" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>					    
	  </form>	  
	</div>
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/manuscript/manuscript-edit.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script> 	
	
</body>
  
  
  
</html>
