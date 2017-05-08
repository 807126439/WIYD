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

<title>学习任务管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>

</head>

<body>
	<%@include file="/page/common/nav/breadcrumb.jsp"%>
	<div class="Hui-article">
		<div class="page-container">
			<div class="t_searchbar">
				任务编号：<input type="text" class="input-text" style="width:250px"
					placeholder="任务编号" id="taskNum">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				任务名称：<input type="text" class="input-text" style="width:250px"
					placeholder="任务名称" id="taskName">&nbsp;
				<button type="button" class="btn btn-danger radius"
					onclick="goSearch()">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>

			<div class="t_ctrlbar">
				<span class="btns"><m:a_top context="发布学习任务"
						operCode="addStudyTask" funMethod="studyTask_add('发布学习任务','','')"
						className="btn btn-primary radius" /> <m:a_top context="批量删除"
						operCode="delStudyTask" funMethod="datadel()"
						className="btn btn-danger radius" /> </span>
			</div>

			<table id="studyTask_table"
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead></thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
	<%@include file="/page/common/script/mybottom.jsp"%>
	<m:a_button operCode="updateStudyTask"
		funMethod="studyTask_edit('{0}','','')" context="修改"
		varName="update_StudyTask"
		className="t-btn btn size-MINI btn-primary-outline" />
	<%-- <m:a_button operCode="delStudyTask"  funMethod="studyTask_del(this,'{0}')" context="删除" varName="delete_StudyTask" /> --%>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/study/studyTask/studyTask-list.js"></script>
</body>
</html>
