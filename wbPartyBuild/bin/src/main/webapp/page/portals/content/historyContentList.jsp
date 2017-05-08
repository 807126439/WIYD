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
    
    <title>历史文章列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">

  </head>
  
  
  <body>

	<div class="Hui-article" >
		<div class="page-container">
				<div class="t_searchbar">																								
					标题：<input type="text" class="input-text" style="width:250px" placeholder="标题" id="ct-title" >&nbsp;
					来源：<input type="text" class="input-text" style="width:250px" placeholder="来源" id="ct-source" name="">&nbsp;
					作者：<input type="text" class="input-text" style="width:250px" placeholder="作者" id="ct-auhtor" name="">
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				
				<div class="t_ctrlbar"> 
				 <span class="btns">
				   	
				    <m:a_top context="恢复"  operCode="recoverContent" icon="&#xe600;" funMethod="dataRecover()"  className="btn btn-primary radius"/>				 		 			     
				 	<m:a_top context="删除"  operCode="shiftDelContent" icon="&#xe6e2;" funMethod="datadel()" className="btn btn-danger radius" />
				     
				 </span> 
				 
			
				</div>
				
				
				
		
				<table id="content_table" class="table table-border table-bordered table-hover table-bg table-sort">
				</table>
		
			
			
	  </div>
	  
	
	</div>  
			
		<div id="hide-tree" style="display: none;">
			<div class="row cl">
		      <div class="r pr-20 pt-10">
		        <m:b_button operCode="recoverContent" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" funMethod="sumbitRecover()" className="btn btn-primary radius size-MINI"/>
		      </div>
		    </div>
		    
			<div class="pd-10">
				<ul id="tree" class="ztree"></ul>				
				
			</div>
			
		
		</div>
			
	
		<%@include file="/page/common/script/mybottom.jsp" %>
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
	  
		
		<m:a_button operCode="recoverContent"  funMethod="content_one_recover('{0}')" context="恢复" varName="recover_content"  className="t-btn btn size-MINI btn-primary-outline"/>	
		<m:a_button operCode="shiftDelContent"  funMethod="content_del(this,'{0}')" context="彻底删除" varName="del_content" className="t-btn btn size-MINI btn-danger-outline"/>
			

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/history-content-list.js"></script> 
	   
		
</body>
  
  
  
</html>
