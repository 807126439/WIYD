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
    
    <title>心得体会数统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">

  </head>
  
  <body>
  <%@include file="/page/common/nav/breadcrumb.jsp" %>
  	 <div class="Hui-article" >
		<div class="page-container">
				<div class="t_searchbar">
					<form action="<%=path %>/feelingRecordController/exportExcel.do" id="exportForm">			
						日期：<input type="text" placeholder="起止日期" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeDate\')||\'%y-%M-%d\'}'})" class="input-text Wdate" style="width:150px"id="beginTimeDate" name="startTime" readonly="readonly">												
						- <input type="text" placeholder="终止日期" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTimeDate\')}',maxDate:'%y-%M-%d'})" class="input-text Wdate" style="width:150px"  id="endTimeDate" name="endTime" readonly="readonly" >&nbsp;
						用户名：<input type="text" class="input-text" style="width:150px" placeholder="用户名" id="userName" name="userName">
						<%@include file="/page/portals/common/selectDepartment.jsp" %>
					<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()" ><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
					</form>
				</div>
				
				<%@include file="/page/portals/common/selectDepartment2.jsp" %>
				<div class="t_ctrlbar"> 
					 <span class="btns">
					 	 <m:a_top context="导出"  operCode="expertFeelingCount"  funMethod="exportExcel()" className="btn btn-primary radius" />
					</span> 
				</div>	
		
					<table id="feelingRecord_table"  class="table table-border table-bordered table-hover table-bg table-sort">
		</div>	
		</div>
		
		<%@include file="/page/common/script/mybottom.jsp" %>
<%-- 		<m:a_button operCode="updateFeelingRecord"  funMethod="feelingRecord_edit('{0}','','')" context="修改" varName="update_feelingRecord" />
 --%>		<m:a_button operCode="detailedFeelingRecord"  funMethod="feelingRecord_detailed('{0}','','')" context="查看" varName="detailed_feelingRecord" className="t-btn btn size-MINI btn-primary-outline"/>
 			<m:a_button operCode="delFeelingRecord"  funMethod="feelingRecord_del(this,'{0}')" context="删除" varName="delete_feelingRecord" className="t-btn btn size-MINI btn-danger-outline"/>
 		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/FeelingRecord/feelingCount-list.js"></script>
 		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
 		<script type="text/javascript" src="<%=path %>/plug-in/common/js/selectDepartment.js"></script> 
  </body>
</html>
