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
    
    <title>权限列表</title>
    
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
			
					<input type="text"  class="input-text" style="width:180px" placeholder="权限名称" id="ser_name" >&nbsp;&nbsp;
					<input type="text"  class="input-text" style="width:180px" placeholder="权限码" id="ser_code" >&nbsp;&nbsp;
					<input type="text"  class="input-text" style="width:150px" placeholder="父节点名称" id="ser_par_name" >
					<button type="button" class="btn btn-success" id="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜权限节点</button>
				    <br><br>
				    <div class="text-c">
						<span class="select-box" style="width: 180px;">
							<select class="select" id="auth-type" >
								<option value="">权限类型...</option>
								<c:forEach items="${typeDict}" var="t">
									<option value="${t.dictValue}" >${t.dictItem}</option>
								</c:forEach>
							</select>
						</span> 
					
						<span class="select-box" style="width: 180px;">
							<select class="select" id="auth-flag" >
								<option value="">状态...</option>
								<c:forEach items="${flagDict}" var="t">
									<option value="${t.dictValue}" >${t.dictItem}</option>
								</c:forEach>
							</select>
						</span> 
						
						
					</div>
					
					
				</div>
				
				<div class="t_ctrlbar">		
					 <span class="btns">				 
					   <m:a_top context="添加权限节点" operCode="skipAddAuth"  funMethod="auth_add('添加权限节点','authController/skipAddAuth.do','850','510')"  className="btn btn-primary radius"/>
				  	   <m:a_top context="删除" operCode="delAuth"  funMethod="datadel()" className="btn btn-danger radius" />
					 </span>
				   
				</div>
				
				<table id="auth_table" class="table table-border table-bordered table-bg">
				</table>
			</div>
					
		</div>			
	
		<%@include file="/page/common/script/base-js.jsp" %>
		
		<m:a_button operCode="getAuthDetail"  funMethod="auth_edit('编辑','{0}','850','510')" context="编辑" varName="edit_auth" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="delAuth" funMethod="auth_del(this,'{0}')" context="删除" varName="del_auth" className="t-btn btn size-MINI btn-danger-outline"/>	
       

		<script type="text/javascript" src="<%=path%>/plug-in/web/system/auth/auth-list.js"></script>
	    <script type="text/javascript" src="<%=path %>/plug-in/web/common/js/common-table.js"></script> 
		
</body>
  
  
  
</html>
