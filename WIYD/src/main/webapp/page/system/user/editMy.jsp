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
    
    <title>修改信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/userController/updateMyInfo.do" method="post" class="form form-horizontal" id="form-user-edit">	  
	
	  
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>账号名：</label>
	      <div class="formControls col-xs-6">
	     	 ${userItem.userName}     
	     </div>
	   	<div class="col-xs-3"> </div>
	    </div>
	    	   
	    <div class="row cl">
	      <label class="form-label col-xs-3">${userItem.userType != 0?'单位名称':'中文名：'}</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  value="${userItem.chineseName}" id="chineseName" name="chineseName" datatype="*2-100" ignore="ignore">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3">手机号码：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text" value="${userItem.phone}"   name="phone" id="phone" datatype="m"  ignore="ignore"   />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
   
	    <div class="row cl">
	      <label class="form-label col-xs-3">邮箱：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text" value="${userItem.email}" placeholder="@" name="email" id="email" datatype="e" ignore="ignore">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	    
	    <div class="row cl">
			<label class="form-label col-xs-3">新密码：</label>
			<div class="formControls col-xs-6">
				<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd" id="new-password" datatype="*6-18" ignore="ignore" >
			</div>
			<div class="col-xs-3"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3" >确认密码：</label>
			<div class="formControls col-xs-6">
				<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd2" id="new-password2" recheck="pwd" datatype="*6-18" errormsg="您两次输入的密码不一致！" ignore="ignore" >
			</div>
			<div class="col-xs-3"> </div>
		</div>
    
	   <input type="hidden" name="id" value="${userItem.id}" >
	  
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">	        
	       	 <m:b_button operCode="updateMyInfo" className="btn btn-success radius"  type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>
	  
	  
	</div>
		
		
		
	<%@include file="/page/common/script/base-js.jsp" %>
	
	<script type="text/javascript" src="<%=path%>/plug-in/web/system/user/my-edit.js"></script> 

		
		
		
		
</body>
  
  
</html>
