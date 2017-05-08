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

<title>测试次数统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>
<!-- ==========department tree========== -->
<link
	href="<%=path%>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css"
	type="text/css" rel="stylesheet">
<!-- ==========END OF department tree========== -->
</head>

<body>
	<%@include file="/page/common/nav/breadcrumb.jsp"%>
	<div class="Hui-article">
		<div class="page-container">
			<div class="t_searchbar">
				测试时间： <input style="width:120px" type="text" id="beginTime"
					class="input-text radius Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> - <input
					style="width:120px" type="text" id="endTime"
					class="input-text radius Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">&nbsp;&nbsp;用户名：<input
					style="width:150px" type="text" id="userName" class="input-text"
					placeholder="用户名">
				<!-- ==========department tree========== -->
				<%@include file="/page/portals/common/selectDepartment.jsp"%>
				<!-- ==========END OF department tree========== -->
				<button type="button" class="btn btn-danger"
					onclick="goSearch()">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>

			<!-- ==========department tree========== -->
			<%@include file="/page/portals/common/selectDepartment2.jsp"%>
			<!-- ==========END OF department tree========== -->

			<!-- =================EXPORT===================== -->
			<div class="t_ctrlbar">
				<span class="btns"><m:a_top context="导出"
						operCode="countLoginFreq" funMethod="exportAll()"
						className="btn btn-primary radius" /> </span>
			</div>
			<!-- ===================END OF EXPORT=================== -->

			<table id="studyCount_table"
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead></thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
	<%@include file="/page/common/script/mybottom.jsp"%>
	<!-- ==========department tree========== -->
	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/common/js/selectDepartment.js"></script>
	<!-- ==========END OF department tree========== -->
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/system/count/studyFrequency-list.js"></script>
</body>
</html>
