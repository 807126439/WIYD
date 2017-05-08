<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/page/common/tag/mytags.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色列表</title>
    
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
			
					角色码：<input type="text"  class="input-text" style="width:200px" placeholder="角色码" id="ser_name" name="roleName">&nbsp;&nbsp;
					角色名：<input type="text"  class="input-text" style="width:200px" placeholder="角色名" id="ser_text" name="text">
					<button type="button" class="btn btn-danger" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				
				</div>
				
				<div class="t_ctrlbar">	
				 <span class="btns">
				   	  <m:a_top context="添加角色" operCode="skipAddRole"  funMethod="role_add('添加角色','roleController.do?add','850','510')" className="btn btn-primary radius"/>							
					  <m:a_top context="批量删除" operCode="delRole"  funMethod="datadel()" className="btn btn-danger radius"/>
					
				  </span>
				
				</div>
				
		
				<table id="role_table" class="table table-border table-bordered table-bg table-hover table-sort">				
				</table>
				
			</div>
		</div>		
	
	   <%@include file="/page/common/script/mybottom.jsp" %>
	 
	 
	   <m:a_button operCode="roleDetail"  funMethod="role_edit('编辑','{0}','850','510')" context="编辑" varName="edit_role" className="t-btn btn size-MINI btn-primary-outline"/>	   
	   <m:a_button operCode="delRole"  funMethod="role_del(this,'{0}')" context="删除" varName="del_role" className="t-btn btn size-MINI btn-danger-outline"/>
	 

	   <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/role/role-list.js"></script> 
		
</body>
  
  
  
</html>
