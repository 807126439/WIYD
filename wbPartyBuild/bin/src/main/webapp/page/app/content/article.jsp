<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="renderer" content="webkit|ie-stand">
  <meta name="viewport" content="initial-scale=1.0,maximum-scale=1,user-scalable=no">
  <meta content="telephone=no,email=no" name="format-detection">
  <title>文章阅读</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body class="noheader">
  <%@include file="/page/app/common/header.jsp" %>
  <div class="maincontainer">
    <div class="mainbg-1"></div>
    <article class="article">
      <header class="article-hd">
        <h1 class="article-tt">${contentItem.title}</h1>
        <div class="article-subtt">
          <span class="pdate">${contentItem.createTime}</span>
          <span class="author">${contentItem.author}</span>
        </div>
      </header>
      <div class="article-bd">
      	${contentItem.content}
      </div>
      <!--  
      <footer class="article-ft">
        <span class="serial_number">2016年第38期 （总第268期）</span>
      </footer>
      -->
    </article>
  </div>
  <!-- footerbar's btns -->
 	<%@include file="/page/app/common/footer.jsp" %>
</body>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">

$(function() {
	$(".article-bd *").not('table,tr,th,td,p,span').removeAttr("style");	
	$(".article-bd br").remove();
	  $(".article-bd span").each(function(){
	    if($(this).find('img').length===0&&$(this).text().replace(/\s/g,'')==='')
	      $(this).remove();
	  });
	  $(".article-bd p").each(function(){
	    if($(this).find('img').length===0&&$(this).text().replace(/\s/g,'')==='')
	      $(this).remove();
	  });
})

</script>

</html>
