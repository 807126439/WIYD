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
    
    <title>流程定义列表</title>
    
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
		
				流程标识符：<input type="text"  class="input-text" style="width:250px" placeholder="流程标识符" id="ser_code" >&nbsp;&nbsp;
				流程名：<input type="text"  class="input-text" style="width:150px" placeholder="流程名" id="ser_name" >
				<button type="button" class="btn btn-danger radius" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			
			</div>
			
			<div class="t_ctrlbar">		
			  <span class="btns">
			
			    <m:a_top context="创建流程定义" operCode="skipAddProcessDefinition" funMethod="procDef_add('创建流程定义','procDefinition/skipAddProcessDefinition.do','','480')"  className="btn btn-primary radius"/>
		
			  </span>
			
			</div>
			
		
				<table id="def_table" class="table table-border table-bordered table-bg">			
				</table>
			
		</div>
				
		</div>					
		
		 <%@include file="/page/common/script/mybottom.jsp" %>
		
		
		<m:a_button operCode="processDefinitionDetail"  funMethod="procDef_edit('编辑','{0}','','480')" context="编辑" varName="edit_procDef"  className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="designProcess"  funMethod="proc_design('设计流程图','{0}')" context="设计流程图" varName="design_proc"  className="t-btn btn size-MINI btn-success-outline"/>
		<m:a_button operCode="delProcessDefinition"  funMethod="procDef_del(this,'{0}')" context="删除" varName="del_procDef" className="t-btn btn size-MINI btn-danger-outline"/>	
		<m:a_button operCode="getProcNodeList"  funMethod="proc_manage('管理流程图','{0}')" context="管理流程图" varName="manage_proc"  className="t-btn btn size-MINI btn-primary-outline"/>
       

		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/work-flow/procDef/procDef-list.js"></script> 
		
</body>
  
  
  
</html>
