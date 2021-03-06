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
  <title>城建先锋</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">

</head>

<body class="heightheader">

  <%@include file="/page/app/common/header.jsp" %>

  <div class="maincontainer">
    <div class="mainbg-1"></div>
    <section class="menubd">
      <ul class="menunav clearfix">
        <li>
          <a href="<%=path %>/appController/contentList.do">
            <div><img src="<%=path %>/plug-in/app/images/icon_article.png"></div>
            <div class="itemtt">文章阅读</div>
          </a>
        </li>
        <li>
          <a href="<%=path %>/appController/testList.do">
            <div><img src="<%=path %>/plug-in/app/images/icon_test.png"></div>
            <div class="itemtt">在线测试</div>
          </a>
        </li>
        <li>
          <a href="<%=path %>/appController/topicList.do">
            <div><img src="<%=path %>/plug-in/app/images/icon_interact.png"></div>
            <div class="itemtt">互动交流</div>
          </a>
        </li>
       <li>
          <a href="<%=path %>/appController/skipAddOpinion.do"">
            <div><img src="<%=path %>/plug-in/app/images/icon_advise.png"></div>
            <div class="itemtt">金点子建议</div>
          </a>
        </li>
        <li>
          <a href="<%=path %>/appController/photography.do">
            <div><img src="<%=path %>/plug-in/app/images/icon_photography.png"></div>
            <div class="itemtt">摄影投稿</div>
          </a>
        </li>    
         <li>
          <a href="<%=path %>/appController/skipAddExperience.do">
            <div><img src="<%=path %>/plug-in/app/images/icon_experience.png"></div>
            <div class="itemtt">心得体会</div>
          </a>
         </li>
         <li>
          <a href="<%=path %>/appController/movieList.do">
            <div><img src="<%=path %>/plug-in/app/images/icon_video.png"></div>
            <div class="itemtt">在线视频</div>
          </a>
        </li>
      </ul>
    </section>
  </div>

 <div class="footerbar">
	 <a href="<%=path %>/appController/index.do" class="btn_home"><i class="icon_home"></i>主 页</a>
 </div>

</body>

</html>

