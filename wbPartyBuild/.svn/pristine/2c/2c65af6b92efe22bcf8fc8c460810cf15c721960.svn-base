<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="renderer" content="webkit|ie-stand">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

	 <div class="Hui-article" >
			<div class="page-container">
				<div class="t_searchbar">																								
					用户名：<input type="text" class="input-text" style="width:250px" placeholder="用户名" id="user_name" >&nbsp;
					姓名：<input type="text" class="input-text" style="width:250px" placeholder="姓名" id="cn_name" name="">
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
				</div>
				
				<div class="t_ctrlbar">	
					<span class="btns">					   				     
						<m:a_top context="添加用户"  operCode="skipAddUser"  funMethod="member_add('添加用户','','')"  className="btn btn-primary radius"/>					 		 
						<m:a_top context="删除"  operCode="deleteUser"  funMethod="datadel()" className="btn btn-danger radius" />
					</span>									 			
				</div>
			
				
				<table id="user_table" class="table table-border table-bordered table-bg table-hover table-sort"></table>
				
				
			</div>
			
		</div>
			
		<%@include file="/page/common/script/mybottom.jsp" %>
	
		
		<m:a_button operCode="userDetail"  funMethod="member_edit('编辑','{0}','','')" context="编辑" varName="user_edit" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="changePassword"  funMethod="change_password('修改密码','{0}','600','270')" context="修改密码" varName="user_change_pwd" className="t-btn btn size-MINI btn-danger-outline" />
		<m:a_button operCode="deleteUser"  funMethod="del_user('{0}')" context="删除" varName="user_del" className="t-btn btn size-MINI btn-danger-outline" />
			

	   <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/user-list.js"></script> 
		
</body>
  
  
  
</html>
