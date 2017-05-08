<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String nodes = (String )request.getAttribute("nodes");
out.print("<script type='text/javascript'> var zNodes = "+nodes+";</script>");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">
		
  </head>
  <body>
  	 <div>
    	<div class="panel panel-default" style="border-bottom:none; border-left: none;border-right: none;">
    	<div class="panel-header">设置权限
	     <input class="btn btn-primary size-MINI  radius" type="button" value="确定" onclick="setSeeOrg()">
	     
    	 <a href="javascript:;" class="r c-primary" onclick="showCheckAttr()" style="display: none;">勾选属性</a>
    	 
    	 <a href="javascript:;" class="r pr-10 c-primary" onclick="checkAllNodes(this)" singal="0">全选</a>
    	 
    	</div>
    	<div class="panel-body">
    		<ul id="tree" class="ztree"></ul>
    	
    	</div>
	  </div>
    	
  </div>
  
  
  <div id="checkAttrs" style="display: none;" >
    <div class="pd-20">
	  	父子关联关系：<br/>
		被勾选时：<input type="checkbox" id="py" class="checkbox first" checked /><span>关联父</span>
		<input type="checkbox" id="sy" class="checkbox first" checked /><span>关联子</span><br/>
		取消勾选时：<input type="checkbox" id="pn" class="checkbox first" checked /><span>关联父</span>
		<input type="checkbox" id="sn" class="checkbox first" checked /><span>关联子</span><br/>

		
  	</div>
  </div>
  	
  	<%@include file="/page/common/script/operbuttom.jsp" %>
  	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
    
    <script src="<%=path %>/plug-in/web/scripts/portals/content/departmentTree.js" type="text/javascript"></script>
    
    
    
  </body>
</html>