<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>导入题目</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>
<link href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="pd-20" style="text-align: center;">
		<form action="<%=path%>/topicController/importTopic.do"  method="post"  class="form form-horizontal" id="form-import-topic">
			
			<div class="row cl">			
			<label class="form-label col-xs-3 col-sm-2">题目类别：</label>
			<div class="formControls col-xs-1 skin-minimal"> 
				<span class="select-box" style="width:160px;">
					<select class="select" name="categoryId" size="1" id="categoryId">				
						<c:forEach items="${categoryList}" var="c">
							<option value="${c.id}">${c.cateName}</option>
						</c:forEach>
					</select>
				</span> 
			</div>
			</div>
			
			
		    <div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">文件：</label>
				<div class="formControls col-xs-6 col-sm-8">
					<div class="uploader-thum-container">
						<div id="fileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						<button id="btn-cancel" class="btn btn-default btn-uploadstar radius" style="height:30px;font-size:14px" type="button">撤销文件</button>
					</div>
				</div>
				<div class="col-xs-2 col-sm-2"></div>
			</div>	  
			


			<div class="row cl">
				<div class="col-xs-2 col-xs-offset-4 col-sm-offset-5" style="margin-top: 20px;">					
					<m:b_button operCode="importTopic" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />					
				</div>
			</div>
		</form>
	</div>

	<%@include file="/page/common/script/operbuttom.jsp"%>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script> 
	<script type="text/javascript"  src="<%=path%>/plug-in/web/scripts/study/topic/importTopic.js"></script>

</body>
</html>
