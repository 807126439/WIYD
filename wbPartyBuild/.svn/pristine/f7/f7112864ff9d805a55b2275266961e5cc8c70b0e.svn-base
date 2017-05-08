<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>反馈意见</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>
	 <div class="Hui-article" >
		<div class="page-container">
				<div class="t_searchbar">			
					日期：<input type="text" placeholder="起止日期" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeDate\')||\'%y-%M-%d\'}'})" class="input-text Wdate" style="width:250px"id="beginTimeDate" name="" readonly="readonly">&nbsp;&nbsp;													
					-- &nbsp;&nbsp;	<input type="text" placeholder="终止日期" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTimeDate\')}',maxDate:'%y-%M-%d'})" class="input-text Wdate" style="width:250px"  id="endTimeDate" name="" readonly="readonly" >&nbsp;&nbsp;	&nbsp;&nbsp;	&nbsp;&nbsp;	&nbsp;&nbsp;	
					意见类别：<span class="select-box  mr-10" style="width: 250px">
						<select id="change-table" class="select" onchange="hiedden()">
							<option value="1" selected="selected">合理化建议征集</option>
							<option value="2">对智慧党建版块意见</option>
						
						</select>
					</span>	
				<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()" ><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				<br>
				<div class="t_ctrlbar"> 
				 <span class="btns">
				 	 <m:a_top operCode="opinionDel" context="批量删除" funMethod="datadel()" className="btn btn-danger radius" /> 
				 </span> 
				</div>	

					<table id="OpinionFeedback_table" class="table table-border table-bordered table-hover table-bg table-sort">
					</table>
			</div>
		</div>
		<%@include file="/page/common/script/mybottom.jsp" %>
		<m:a_button operCode="opinionque"  funMethod="query_opinion('{0}','','')" context="查看" varName="detail_opinion" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="opinionDel"  funMethod="opinion_del(this,'{0}')" context="删除" varName="delete_opinion" className="t-btn btn size-MINI btn-danger-outline"/>
	   <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/opinionFeedback/opinionFeedback-list.js"></script> 
</body>
  
  
  
</html>
