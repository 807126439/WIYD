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
    
    <title>数字字典列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>
	
	

  </head>
  
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

   <div class="Hui-article" >
		<div class="page-container">
			<div class="t_searchbar">	
				<c:choose>	
				 <c:when test="${model eq 'profile' }">
				 	 <input type="hidden"  id="dictType"  value="${dictType}">
				 	 <input type="hidden"  id="dict-model"  value="${model}">
				 </c:when>
				 <c:otherwise>
				 	<input type="text"  class="input-text" style="width:250px" placeholder="字典类别" id="dictType" >&nbsp;&nbsp;				 
				 </c:otherwise>
				</c:choose>
				

				<input type="text"  class="input-text" style="width:150px" placeholder="字典条目" id="dictItem" >
				<button type="button" class="btn btn-success" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			
			</div>
			
			
			<div class="t_ctrlbar">	
				<span class="btns">				
			    <m:a_top context="添加字典" operCode="skipAddDict"  funMethod="dict_add('添加字典','720','380')"  className="btn btn-primary radius"/>
				<m:a_top context="批量删除" operCode="delDict"  funMethod="datadel()" className="btn btn-danger radius" />
			  </span>
			
			</div>
				
			<table id="dict_table" class="table table-border table-bordered table-bg"></table>
				
			
		</div>
				
		</div>					
		
		 <%@include file="/page/common/script/base-js.jsp" %>
		
		
		<m:a_button operCode="getDictDetail"  funMethod="dict_edit('编辑','{0}','720','380')" context="编辑" varName="edit_dict" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="delDict"  funMethod="dict_del(this,'{0}')" context="删除" varName="del_dict" className="t-btn btn size-MINI btn-danger-outline"/>	
       

		<script type="text/javascript" src="<%=path%>/plug-in/web/system/dict/dict-list.js"></script> 
		<script type="text/javascript" src="<%=path %>/plug-in/web/common/js/common-table.js"></script> 
		
</body>
  
  
  
</html>
