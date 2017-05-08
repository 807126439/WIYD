<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<LINK rel="Bookmark" href="/favicon.ico" >
		<LINK rel="Shortcut Icon" href="/favicon.ico" />
		
		<!--[if lt IE 9]>
		<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/html5.js"></script>
		<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/respond.min.js"></script>
		<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/PIE_IE678.js"></script>
		<![endif]-->
		
		<link href="<%=path%>/plug-in/h-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/h-ui/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
		<link href="<%=path%>/plug-in/h-ui/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/plug-in/h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
		<link href="<%=path%>/plug-in/h-ui/static/h-ui.admin/skin/default/skin.css" id="skin" rel="stylesheet" type="text/css"/>
		<link href="<%=path%>/plug-in/h-ui/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css"/>
		<link href="<%=path%>/plug-in/h-ui/mod/css/myskin.css" rel="stylesheet" type="text/css" />
		
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		
		<title>城建局党建管理系统</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
	</head>
	
	<body>
	
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10" href="<%=path %>/index.do">	<img src="<%=path %>/plug-in/h-ui/mod/images/header-tt.png"></a>
				<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
				<div class="my-header-user clearfix">
		      <div class="my-header-user-avatar"><img src="<%=path %>/plug-in/h-ui/mod/images/user-default.png"></div>
		      <div class="my-header-user-row">你好！</div>
		      <div class="my-header-user-nickname"><sec:authentication property="principal.username"/></div>
		      <div class="my-header-display" onClick="displaynavbar(this)"><i class="Hui-iconfont">&#xe6f5;</i></div>
		    </div>
				<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			    <!-- <form class="my-header-search-form" action="javascript:;">
			        <button class="my-header-search-btn"><i class="Hui-iconfont">&#xe665;</i></button>
			        <input class="my-header-search-box" type="text" placeholder="搜索内容...">
			    </form> -->
					<ul class="cl">
						<!--  <li id="Hui-msg"> <a _href="message-receive.html" data-title="收件箱" onclick="Hui_admin_tab(this)" title="消息"><span class="badge badge-success">1</span><i class="Hui-iconfont" style="font-size:18px"></i></a> </li>
						<li id="Hui-notice"> <a href="#" title="提醒"><span class="badge badge-warning">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe622;</i></a> </li>-->
						<li><a href="<%=path%>/portals.do" target="_blank"><i class="Hui-iconfont" style="font-size:18px;margin-right:3px">&#xe625;</i>门户</a></li>
						<li><a href="<%=path%>/appController/index.do" target="_blank"><i class="Hui-iconfont" style="font-size:18px;margin-right:3px">&#xe696;</i>手机版</a></li>
						<li><a href="javascript:void(0)" onclick="logout()"><i class="Hui-iconfont" style="font-size:18px;margin-right:3px">&#xe6de;</i>退出</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
			
			 <% 
				   String menu = (String)session.getAttribute("menu");
				   out.print(menu);
			 %>
			
			
		</div>
		
	</aside>

	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="<%=path %>/page/index/welcome.html">我的桌面</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="<%=path %>/page/index/welcome.html"></iframe>
			</div>
		</div>
	</section>
	
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/static/h-ui.admin/js/H-ui.admin.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/mod/js/rightClickMenu.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/common/js/common.js"></script>
	
	
	</body>
</html>
