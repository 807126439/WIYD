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
			<form action="<%=path %>/userController/changePassword.do" method="post" class="form form-horizontal" id="form-change-password">
				<div class="row cl">
					<label class="form-label col-xs-4 text-r" style="text-align: right;"><span class="c-red">*</span>账户：</label>
					<div class="formControls col-xs-4" id="changePwd-name">${requestScope.userItem.userName}</div>
					<div class="col-xs-4"></div>
				</div>
				
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
				<input type="hidden" id="changPwd-id"  name="uid" value="${requestScope.userItem.id}">
				
			</form>
		</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript">
	$(function(){
			
			$("#form-change-password").Validform({
				tiptype:2,
				ajaxPost:true,//ajax方式提交表单数据
				callback:function(data){
					if(data.status == "y" || data.status == "Y"){			
					      setTimeout(function(){
					      	var index = parent.layer.getFrameIndex(window.name);						
							parent.layer.close(index);
							      					      
					      },1000); 
					
					}
				}
			});		
	
	});
	</script>
		
		
</body>
  
  
  
</html>
