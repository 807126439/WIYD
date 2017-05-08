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
		
		<link href="<%=path%>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/main/css/login.css" rel="stylesheet" type="text/css" />


				
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		
		<title>后台系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		
	</head>

	
	<body>
	
	
	 <div class="login-header"><div class="login-logowrap"><!--  <img src="<%=path %>/plug-in/main/images/admin-login-tt.png">--></div></div>
	  <div class="login-body">
	    <div class="login-container">
	      <div class="login-img-container"><!--  <div class="login-img"></div>--></div>
	      <div class="login-form-container">
	        <div class="login-form-wrapper">
	          <form class="login-form" name="login-form" action="<%=path %>/j_spring_security_check" method="post">
	            <div class="login-form-tt">登录系统</div>
	            <div class="login-form-iptgroups">
	              <div class="login-form-iptgroup">
	                <label class="login-form-label"><i class="icon_login_user"></i></label>
	                <span class="login-form-iptwrap"><input id="user-name" type="text" name="j_username" placeholder="用户名" value="admin" onfocus="hideError(1)"></span>
	              </div>
	              <div class="login-form-iptgroup">
	                <label class="login-form-label"><i class="icon_login_password"></i></label>
	                <span class="login-form-iptwrap"><input id="pass-word" name="j_password" type="password" placeholder="密码" value="wanve123456" onfocus="hideError(1)"></span>
	              </div>
	              <div class="login-form-iptgroup">
	                <label class="login-form-label"><i class="icon_login_imgcode"></i></label>
	                <span class="login-form-iptwrap"><input id="captcha" name="j_captcha" class="ipt_imagecode" type="text" value="3" placeholder="验证码" onfocus="hideError(2)"></span>
	                <span class="login-form-imagecode"><img src="<%=path %>/Kaptcha.jpg" id="captchaImage"/></span>
	              </div>
	            </div>
	            <div class="login-form-misc">
	              <input type="checkbox" id=""><label for="">记住密码</label>
	              <a href="#">忘记密码？</a>
	              <a href="#">注册</a>
	            </div>
	            <div class="login-form-buttons">
	              <a href="javascript:;"  onclick="checkVal()">登录</a>
	            </div>
	            <div class="login-error">
	              <div class="login-error-match" id="loginError1">密码或者账号错误</div>
	              <div class="login-error-code" id="loginError2">验证码错误</div>
	            </div>
	          </form>
	        </div>
	      </div>
	    </div>
	  </div>
	  

  
	  <div class="login-footer"><span class="login-footer-copyright">CopyRight 2017 有限公司. All Rights Reserved.</span></div>
	
	
	
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 

				
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
		
		
		
		
  //showError(1);
 //showError(2);
  
  function showError(type,msg){
    var errorEl=document.getElementById('loginError'+(type||1));
    errorEl.innerHTML=msg; 
    if (!errorEl) return;
    if (errorEl.classList)
      errorEl.classList.add('showerror');
    else
      errorEl.className += ' ' + 'showerror';
  }

  function hideError(type){
    var errorEl=document.getElementById('loginError'+(type||1));
    if (!errorEl||errorEl.className.search('showerror')===-1) return;
    if (typeof errorEl.style.animationName!=='undefined'||typeof errorEl.style.animation!=='undefined'||typeof errorEl.style.WebkitAnimation!=='undefined'||typeof errorEl.style.MozAnimation!=='undefined'||typeof errorEl.style.OAnimation!=='undefined'||typeof errorEl.style.MsAnimation!=='undefined') {
      //支持动画
      errorEl.removeEventListener("webkitAnimationEnd", animationEnd);
      errorEl.removeEventListener("animationend", animationEnd);
      errorEl.addEventListener("webkitAnimationEnd", animationEnd);
      errorEl.addEventListener("animationend", animationEnd);
      if (errorEl.classList)
        errorEl.classList.add('hiddingerror');
      else
        errorEl.className += ' ' + 'hiddingerror';
    }else{
      //不支持动画
      if (errorEl.classList)
        errorEl.classList.remove('showerror');
      else
        errorEl.className=errorEl.className.replace(new RegExp('(^|\\b)' + 'showerror'.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
    }

    function animationEnd(){
      errorEl.removeEventListener("webkitAnimationEnd", animationEnd);
      errorEl.removeEventListener("animationend", animationEnd);
      if (errorEl.classList){
        errorEl.classList.remove('showerror');
        errorEl.classList.remove('hiddingerror');
      }
      else{
        errorEl.className=errorEl.className.replace(new RegExp('(^|\\b)' + 'showerror'.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
        errorEl.className=errorEl.className.replace(new RegExp('(^|\\b)' + 'hiddingerror'.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
      }
    }
}	
		
		
	</script>
	
	<c:if test="${!empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
	  	<script type="text/javascript">
	  		var errMsg =  '${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}';
	  		var errArray = errMsg.split(",");
	  		if(errArray.length>1){
	  		   var type = 1;
	  		   if(errArray[0] == "4006"){
	  		   	type = 2;
	  		   }
	  		   showError(type,errArray[1]);	  		   
	  		   
	  		}else{
	  			showError(1,errMsg);
	  		}
	  		
	  		
	 	</script>
	      
    </c:if>	
		
		
		
	</body>
</html>
