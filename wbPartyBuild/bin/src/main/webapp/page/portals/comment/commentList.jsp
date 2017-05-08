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
    <title>评论列表</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<%@include file="/page/common/script/mytop.jsp" %>
  </head>
  <body>

	<div class="Hui-article">
		<div class="pd-20">		
			<div class="text-c">																								
				<input type="text" class="input-text" style="width:250px" placeholder="评论人" id="username">&nbsp;				
				<button type="button" class="btn btn-success radius" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>		
							
			<div class="cl pd-5 bg-1 bk-gray mt-20 l"> 
			 <span class="l">				   	
			 	<m:a_top context="批量删除"  operCode="deleteComment" icon="&#xe6e2;" funMethod="datadel()" className="btn btn-danger radius" />				     
			 </span> 
			</div>	
						
			<div class="mt-20">
				<table id="comment_table" class="table table-border table-bordered table-hover table-bg table-sort">
					<thead></thead>
					<tbody></tbody>
				</table>
			</div> 
	     </div>
	 </div>
	     
	<%@include file="/page/common/script/mybottom.jsp" %>
	<m:a_button operCode="skipEditComment"  funMethod="comment_edit('{0}','','')" context="编辑" varName="edit_comment" />	
	<m:a_button operCode="deleteComment"  funMethod="comment_del(this,'{0}')" context="删除" varName="del_comment" />
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/comment/comment-list.js"></script> 	    
	<input id="comId" name="comId" value="${comId}" type="hidden">	
  </body> 
</html>