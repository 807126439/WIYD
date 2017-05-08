<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		
		<!--[if lt IE 9]>
		<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/html5.js"></script>
		<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/respond.min.js"></script>
		<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/PIE_IE678.js"></script>
		<![endif]-->
		
		<link href="<%=path%>/plug-in/h-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/h-ui/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/h-ui/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/h-ui/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/h-ui/mod/css/myskin.css" rel="stylesheet" type="text/css" />
				
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		
		<title>城建智慧党建系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		
	</head>

	
	<body>
		<input type="hidden" id="TenantId" name="TenantId" value="" />
		<div class="login-header"><img src="<%=path %>/plug-in/h-ui/mod/images/admin-login-tt.png"></div>
			<div class="loginWraper">
			  <div id="loginform" class="loginBox">
			    <form class="form form-horizontal" name="login-form" action="<%=path %>/j_spring_security_check" method="post">
			      <div class="row cl">
			        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
			        <div class="formControls col-xs-8">
			          <input id="user-name" name="UserID" type="text" placeholder="账户" class="input-text size-L" value="super">
			        </div>
			      </div>
			      <div class="row cl">
			        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
			        <div class="formControls col-xs-8">
			          <input id="pass-word" name="j_password" type="password" placeholder="密码" class="input-text size-L" value="wanve123456">
			        </div>
			      </div>
			      
			      <div class="row cl">
			         <div class="formControls col-8 col-offset-3">
				          <input id="captcha" name="j_captcha" class="input-text size-L" type="text" placeholder="验证码" style="width:150px;" value="3" />
				          <img src="<%=path %>/Kaptcha.jpg" id="captchaImage"/>			          
			         </div>
			       </div> 
			       
			      <div class="row cl">
			        <div class="formControls col-xs-8 col-xs-offset-3">
			          <label for="online">
			            <input type="checkbox" name="online" id="online" value="">
			            使我保持登录状态</label>
			        </div>
			      </div>
			      
			      <div class="row cl text-c">
       				 <input type="button" class="btn-login-submit btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" onclick="checkVal()">
     			
     				  <div class="col-4">
				         <c:if test="${!empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
					        <div class="Huialert Huialert-danger"><i class="Hui-iconfont">&#xe6a6;</i>
					        	<span class="f-16">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</span>
					        </div>
				          </c:if>	 
				        </div>	
     			
     			 </div>
			      
			  
			    </form>
			  </div>
			</div>
		
		
		
		<div class="footer">东莞市城建工程管理局信息管理部开发(版权所有)</div>
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/static/h-ui/js/H-ui.js"></script> 
		<script type="text/javascript">
			$(function(){
					var $captchaImage = $("#captchaImage");
					// 刷新验证码
					$captchaImage.click(function() {
						var timestamp = (new Date()).valueOf();
						var imageSrc = $captchaImage.attr("src");
						if (imageSrc.indexOf("?") >= 0) {
							imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
						}
						imageSrc = imageSrc + "?timestamp=" + timestamp;
						$captchaImage.attr("src", imageSrc);
					}); 
					
					
					$(document).keydown(function(event){
						if(event.keyCode==13){
							checkVal();
						}
					}); 
		
			});
		
		
		
		function checkVal(){
		  var username = $("#user-name").val();
		  if(username == "undefined" || username == null || username == ""){
		  	alert("请输入账号或手机号码！");
		  	return false;
		  }
		  var pwd = $("#pass-word").val();
		  if(pwd == "undefined" || pwd == null || pwd == ""){
		  	alert("请输入密码！");
		  	return false;
		  }
		  var captcha =  $("#captcha").val();
		  if(captcha == "undefined" || captcha == null || captcha == ""){
		  	alert("请输入验证码！");
		  	return false;
		  }
		  
		  $("form[name=login-form]").submit();
		}
		
		
		
		
		</script>
		
		
		
	</body>
</html>
