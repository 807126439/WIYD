<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>

<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="renderer" content="webkit|ie-stand">
<title></title>

<%@include file="/page/common/script/main-css.jsp"%>

</head>


<body>
	<%@include file="/page/common/nav/navLine.jsp"%>


	<div class="ui-page-container">
		<div class="ui-panel">
			<div class="ui-panel-body">
				<div class="ui-table-actions">
					<div class="btns ui-btn-group">
						<a href="javascript:location.replace(location.href);"
							class="ui-btn ui-btn-secondary">刷新</a>
					</div>

					<div class="inputs">
						<input style="width:190px" name="search" type="text"
							class="ui-input" placeholder="请输入标题" id="field_1"> <a
							href="javascript:void(0)" class="ui-btn" onclick="goSearch()"><i
							class="icon_search"></i>查询</a>
					</div>

					<div class="filters">
						<span class="filters-title">分类：</span>
						<ul class="ui-btntag">
							<li>全部<input type="hidden" /></li>
							<c:forEach items="${categorys}" var="category" varStatus="vs">
								<li>${category.categoryName}<input type="hidden"
									value="${category.id}" /></li>
							</c:forEach>
						</ul>
					</div>

					<div class="filters">
						<span class="filters-title">排序方式：</span>
						<ul class="ui-btntag ui-btntag_sort">
							<li>按时间<input type="hidden" value="gmtModified" /></li>
							<li>按热度<input type="hidden" value="viewTime" /></li>
						</ul>
					</div>
				</div>

				<table id="recordView_table"
					class="ui-table ui-table-border-row ui-table-border-col ui-table-hover table_datatable"
					fixtable>
				</table>
			</div>
		</div>
	</div>



	<%@include file="/page/common/script/main-js.jsp"%>

	<m:ljs_button operCode="skipDocumentFile_check"
		funMethod="check_model('查看','{0}','800','')" context="查看"
		varName="model_check" />
	<m:ljs_button operCode="skipDocumentFile_checkQuestion"
		funMethod="checkQuestion_model('查看问题详情','{0}','800','')"
		context="查看问题详情" varName="model_checkQuestion" />

	<script type="text/javascript"
		src="<%=path%>/plug-in/web/index/recordView-list.js"></script>

</body>



</html>