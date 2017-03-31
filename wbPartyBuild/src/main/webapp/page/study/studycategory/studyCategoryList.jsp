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
    
    <title>类别管理</title>
    
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
					类别编号：<input type="text" class="input-text" style="width:250px" placeholder="类别编号" id="cnum" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					类别名称：<input type="text" class="input-text" style="width:250px" placeholder="类别名称" id="cname">&nbsp;
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				
				
				<div class="t_ctrlbar"> 
				 <span class="btns">
				   	<m:a_top context="新增类别"  operCode="addCategory"  funMethod="category_add('发布试卷类别','','')"  className="btn btn-primary radius"/>
				 	 <m:a_top operCode="categoryDel" context="删除"  funMethod="datadel()" className="btn btn-danger radius" /> 
					
				</span> 
				</div>	
		
					<table id="studyCategory_table" class="table table-border table-bordered table-hover table-bg table-sort">				
					</table>
				
		</div>	
		</div>
		<%@include file="/page/common/script/mybottom.jsp" %>
		
		<m:a_button operCode="updatecategory"  funMethod="category_edit('{0}','','')" context="修改" varName="update_category" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="categoryDel"  funMethod="category_del(this,'{0}')" context="删除" varName="del_category" className="t-btn btn size-MINI btn-danger-outline"/>
 <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/study/studycategory/studyCategoryList.js"></script>
  </body>
</html>
