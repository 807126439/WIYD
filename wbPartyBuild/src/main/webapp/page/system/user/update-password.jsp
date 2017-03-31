<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	
	


  </head>
  
  
  <body>
  
		<div class="pd-20">
			<form action="<%=path %>/updatePassword.do" method="post" class="form form-horizontal" id="form-change-password">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>原来密码：</label>
					<div class="formControls col-6" id="old-pwd">
						<input type="password" class="input-text" autocomplete="off" placeholder="" name="code" id="new-password" datatype="*6-18" nullmsg="请输入原来密码！" >
					</div>
					<div class="col-3"> </div>
				</div>
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>新密码：</label>
					<div class="formControls col-6">
						<input type="password" class="input-text"  plugin="passwordStrength"  autocomplete="off" placeholder="" name="code1" id="new-password" datatype="*6-18" nullmsg="请输入新密码！" >
						<div class="passwordStrength">密码强度： <span>弱</span><span>中</span><span class="last">强</span></div>											
						
					</div>
					<div class="col-3"> </div>
				</div>
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
					<div class="formControls col-6">
						<input type="password" class="input-text" autocomplete="off" placeholder="" name="code2" id="new-password2" recheck="code1" datatype="*6-18" errormsg="您两次输入的密码不一致！" nullmsg="请再次输入新密码！" >
					</div>
					<div class="col-3"> </div>
				</div>
				<div class="row cl">
					<div class="col-9 col-offset-3">
						<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
					</div>
				</div>
			
			</form>
		</div>
	
	
	
		
		
		
		
	
	
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript">
	$(function(){
			
			$("#form-change-password").Validform({
				tiptype:2,
				ajaxPost:true,//ajax方式提交表单数据
				usePlugin:{
					passwordstrength:{
						minLen:6,
						maxLen:18
					}
				},				
				callback:function(data){
					if(data.status == "y" || data.status == "Y"){			
					      setTimeout(function(){
					      	var index = parent.layer.getFrameIndex(window.name);						
							parent.layer.close(index);
							      					      
					      },1800); 
					
					}
				}
			});		
	
	});
	</script>
		
		
</body>
  
  
  
</html>
