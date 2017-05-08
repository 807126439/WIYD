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
    
    <title>用户列表</title>
    
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
				 																								
					<label>用户名</label>&nbsp;&nbsp;<input type="text" class="input-text" style="width:250px" placeholder="用户名" id="user_name" >&nbsp;			
					<label>姓名</label>&nbsp;&nbsp;<input type="text" class="input-text" style="width:250px" placeholder="姓名" id="cn_name" name="">
			 	
					<button type="button" class="btn btn-success" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
				</div>
				
				<div class="t_ctrlbar">		
					 <span class="btns">
					     <m:a_top context="添加用户"  operCode="skipAddUser"  funMethod="member_add('添加用户','800','600')"  className="btn btn-primary radius"/>				 		 
					 </span> 				 			
				</div>
											
		
				<table id="user_table" class="table table-border table-bordered table-hover table-bg table-sort">
				</table>
					
			 		
			</div>
			
		</div>
			
			
		<div style="display: none;" id="changge-pwd-div">
			<div class="pd-20">
	  			<form action="<%=path %>/userController/changePassword.do" method="post" class="form form-horizontal" id="form-change-password">
										
					<div class="row cl">
						<label class="form-label col-xs-4" style="text-align: right;"><span class="c-red">*</span>新密码：</label>
						<div class="formControls col-xs-4">
							<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd" id="new-password" datatype="*6-18" nullmsg="请输入新密码！" >
						</div>
						<div class="col-xs-4"></div>
					</div>
					
					<div class="row cl">
						<label class="form-label col-xs-4" style="text-align: right;"><span class="c-red">*</span>确认密码：</label>
						<div class="formControls col-xs-4">
							<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd2" id="new-password2" recheck="pwd" datatype="*6-18" errormsg="您两次输入的密码不一致！" nullmsg="请再次输入新密码！" >
						</div>
						<div class="col-xs-4"> </div>
					</div>
					
					<div class="row cl">
						<div class="col-xs-8 col-xs-offset-4">
							<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
						</div>
					</div>
					<input type="hidden" id="changPwd-id"  name="uid" >
					
				</form>
			</div>
		   
		</div>	
			
			
			
			
		<%@include file="/page/common/script/base-js.jsp" %>
	
		
		<m:a_button operCode="getUserDetail"  funMethod="member_edit('编辑','{0}','800','600')" context="编辑" varName="user_edit" className="t-btn btn size-MINI btn-primary-outline"/>		
		<m:a_button operCode="changePassword"  funMethod="change_password('{0}','{1}')" context="修改密码" varName="user_change_pwd" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="deleteUser"  funMethod="user_del(this,'{0}')" context="删除" varName="del_user" className="t-btn btn size-MINI btn-danger-outline"/>
	 	
		
		<script type="text/javascript" src="<%=path%>/plug-in/web/system/user/user-list.js"></script> 
		
		
</body>
  
  
  
</html>
