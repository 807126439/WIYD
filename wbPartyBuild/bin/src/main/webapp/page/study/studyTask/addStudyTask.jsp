<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>发布学习任务</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>
<link href="<%=path%>/plug-in/common/css/showFile.css" rel="stylesheet"
	type="text/css" />
<link
	href="<%=path%>/plug-in/web/scripts/study/studyTask/common/editItem.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="pd-20">
		<form action="<%=path%>/studyTaskController/addStudyTask.do"
			method="post" class="form form-horizontal" id="form-StydyTask">
			<%@include file="/page/study/studyTask/common/editItem.jsp"%>
			<div class="row cl">
				<div class="col-xs-8 col-xs-offset-2 col-sm-offset-2">
					<m:b_button operCode="addStudyData" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
				</div>
			</div>
		</form>
	</div>





	<%@include file="/page/study/studyTask/common/paper-item.jsp"%>
	<%@include file="/page/study/studyTask/common/data-item.jsp"%>





	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
	<%@include file="/page/common/script/allbuttom.jsp"%>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/study/studyTask/studyTask-add.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/study/studyTask/common/paper-item.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/study/studyTask/common/data-item.js"></script>
</body>
</html>
