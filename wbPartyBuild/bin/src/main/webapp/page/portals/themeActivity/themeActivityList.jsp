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
    
    <title>主题活动列表</title>
    
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
					<span class="select-box" style="width:160px;">
					 <select class="select" id="activityType"  size="1">	
					    <option value="">请选择活动类型</option>			
						<c:forEach items="${flagDicts}" var="d">
							<option value="${d.dictValue}">${d.dictItem}</option>
						</c:forEach>
					</select>
					</span> 
				  	活动名称：<input type="text"  id="activityName" class="input-text" style="width:250px" placeholder="活动名称" >				
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
		

						
				<div class="t_ctrlbar"> 
				 <span class="btns">			   	
				    <m:a_top context="新增主题活动"  operCode="skipAddThemeActivity"  funMethod="themeActivity_add('新增主题活动','','')"  className="btn btn-primary radius"/>				 		 			     
				 	<m:a_top context="批量删除"  operCode="delThemeActivity"  funMethod="datadel()" className="btn btn-danger radius" />				     
				 </span> 
				 
			
				</div>
				
				
				
			
				<table id="themeActivity_table" class="table table-border table-bordered table-hover table-bg table-sort">
				
				</table>
			 
			
			
	  </div>
	  	</div>
			

		<%@include file="/page/common/script/mybottom.jsp" %>
		
		<m:a_button operCode="ActivityActivate"  funMethod="themeActivity_active(this,'{0}')" context="激活" varName="manage_activate" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="manuscriptManage"  funMethod="manuscript_manage('{0}','{1}','{2}','','')" context="稿件管理" varName="manage_manuscript" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="awardWinningWorksManage"  funMethod="award_winningWorks('{0}','','')" context="评奖" varName="winningWorks_award" className="t-btn btn size-MINI btn-primary-outline"/>	
		<m:a_button operCode="awardsSettingManage"  funMethod="awards_setting('{0}','','')" context="设定奖项" varName="setting_awards" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="activityRuleManage"  funMethod="activityRule_manage('{0}','','')" context="活动规则" varName="manage_activityRule" className="t-btn btn size-MINI btn-primary-outline"/>	
		<m:a_button operCode="skipEditThemeActivity"  funMethod="themeActivity_edit('{0}','','')" context="编辑" varName="edit_themeActivity" className="t-btn btn size-MINI btn-primary-outline"/>	
		<m:a_button operCode="delThemeActivity"  funMethod="themeActivity_del(this,'{0}')" context="删除" varName="del_themeActivity" className="t-btn btn size-MINI btn-danger-outline"/>
			

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/themeActivity/themeActivity-list.js"></script> 
	    
		
</body>
  
  
  
</html>
