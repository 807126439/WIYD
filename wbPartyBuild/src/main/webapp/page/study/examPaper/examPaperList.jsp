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
    <title>试卷管理</title>  
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
					<select  class="select"  id="paperType" >
					    <option value="">试卷类型</option>
						<option value="1">随机生成</option>
						<option value="2">指定生成</option>
					</select>
				</span>
					试卷名称：<input type="text" class="input-text" style="width:250px" placeholder="试卷名称" id="paperName">&nbsp;
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				
				
				<div class="t_ctrlbar"> 
				 <span class="btns">
				   	<m:a_top context="随机生成试卷"  operCode="skipAddExamPaper"  funMethod="examPaper_add('生成随机试卷','','',1)"  className="btn btn-primary radius"/>				  
				   	<m:a_top context="指定生成试卷"  operCode="skipAddExamPaper" funMethod="examPaper_add('生成指定试卷','','',2)"  className="btn btn-success radius"/>				  			
				   	<m:a_top context="删除"  operCode="deleteExamPaper" funMethod="datadel('删除题目','','')"  className="btn btn-danger radius"/>
				</span> 
				</div>	
		
			
					<table id="examPaper_table" class="table table-border table-bordered table-hover table-bg table-sort">
					</table>
				
		</div>	
		</div>
		<%@include file="/page/common/script/mybottom.jsp" %>
		
		<m:a_button operCode="skipEditExamPaper"  funMethod="examPaper_edit('{0}',{1})" context="修改" varName="edit_examPaper" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="deleteExamPaper"  funMethod="examPaper_delete(this,'{0}')" context="删除" varName="delete_examPaper" className="t-btn btn size-MINI btn-danger-outline"/>
 <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/study/examPaper/examPaperList.js"></script>
  </body>
</html>
