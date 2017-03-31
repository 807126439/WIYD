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
    
    <title>入党申请信息列表</title>
    
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
		
				申请人：<input type="text"  class="input-text" style="width:250px" placeholder="申请人" id="applyUser" >&nbsp;&nbsp;
				<button type="button" class="btn btn-danger radius" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			
			</div>
			
			<div class="t_ctrlbar">		
			  <span class="btns">
			
			    <m:a_top context="添加入党申请信息" operCode="skipAddJoinPartyInfo"  funMethod="info_add('添加入党申请信息','','')"  className="btn btn-primary radius"/>
		
			  </span>
			
			</div>
			
		  
			<table id="info_table" class="table table-border table-bordered table-bg">			
			</table>
	
			
		</div>
				
		</div>					
		
		 <%@include file="/page/common/script/mybottom.jsp" %>
		
		
		<m:a_button operCode="joinPartyInfoDetail"  funMethod="info_edit('编辑','{0}','','480')" context="编辑" varName="edit_info" />
		<m:a_button operCode="startJoinPartyApply"  funMethod="join_start('{0}')" context="提交申请" varName="start_join" />
		<m:a_button operCode="delJoinPartyInfo"  funMethod="info_del(this,'{0}')" context="删除" varName="del_info" className="t-btn btn size-MINI btn-danger-outline"/>	
        <m:a_button operCode="getCurrProcess"  funMethod="process_view('{0}')" context="流程图" varName="view_process" />	
        <m:a_button operCode="viewAttachment"  funMethod="atta_view('{0}','{1}')" context="流程附件" varName="view_atta" />	
       

		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/party/join/join-party-list.js"></script> 
		
</body>
  
  
  
</html>
