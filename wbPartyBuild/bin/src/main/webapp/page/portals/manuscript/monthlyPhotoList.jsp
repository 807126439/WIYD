<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

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
  <title>月月摄影-稿件管理</title>  
  <%@include file="/page/common/script/mytop.jsp" %>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/common/css/showFile.css">
  	<style type="text/css">
	  .authorbox{position: absolute;bottom: 0;left: 0;right: 0;color: #777;font-size: 12px;line-height: 16px;text-align: center;background-color: #eee;}
	  .item-area li{height: 178px;}
	  .item-area li .itembox{height: 168px;}
	  .item-area li .textbox {bottom: 16px;background-color: #eee;}
    </style>
</head>


<body>	
	<div class="Hui-article" style="top:10px;z-index: 1">
	
		 <div class="t_searchbar">																								
		  	作品名称：<input type="text"  id="title" class="input-text" style="width:250px" placeholder="作品名称" >		
		  	作者：<input type="text"  id="author" class="input-text" style="width:250px" placeholder="作者" >			
			<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</div>
	
		<div class="pd-10 clearfix">
			<span class="l">
				<m:a_top context="批量审核"  operCode="skipCheckManuscript" funMethod="datacheck()" className="btn btn-primary radius" />
			 	<m:a_top context="批量删除"  operCode="delManuscript" funMethod="datadel()" className="btn btn-danger radius" />
			</span>
		</div>
		<div class="pd-20">
			<div class="item-contrl">
				<span class="checksAll">
				  <a href="javascript:selectAll()" class="selectAll">全选</a>|
				  <a href="javascript:cancelAll()" class="cancelAll">全不选</a>
				</span>
			</div>
			<div class="item-content">
				<ul class="cl item-area" id="show-list"></ul>
			</div>
			<div id="item-page" class="pt-20 pr-10 r"></div>
		</div>
	</div>
	
	<div id="edit-pic-box" style="display: none;" class="module-edit-name module-edit-name-grid">
		<div class="new-dir-item"></div>
	</div>	

  <input id="activityId" value="${activityId}" type="hidden">
  <%@include file="/page/common/script/mybottom.jsp" %>  
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/manuscript/monthlyPhoto-list.js"></script> 
</body>
</html>