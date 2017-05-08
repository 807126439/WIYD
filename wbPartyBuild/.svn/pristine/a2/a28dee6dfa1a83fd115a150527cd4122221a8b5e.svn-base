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
    
    <title>教育学历等级列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

	 <div class="Hui-article" >
		<div class="pd-20">
				<div class="text-c">																								
					学历等级：<input type="text" class="input-text" style="width: 500px;"  placeholder="学历等级" id="eduName" >&nbsp;
					<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				
				<div class="cl pd-5 bg-1 bk-gray mt-20 l"> 
				 <span class="l">
				   		
				   	 <m:a_top context="添加学历等级"  operCode="skipAddEduDegree"   funMethod="eduDegree_add('教育学历等级','','')"  className="btn btn-primary radius"/>				 						     
				 	 <m:a_top context="批量删除"  operCode="deleteEduDegree"  funMethod="datadel()" className="btn btn-danger radius" />
				     
				 </span> 
				 
			
				</div>
				
				
				
			<div class="mt-20">
				<table id="eduDegree_table" class="table table-border table-bordered table-hover table-bg table-sort">	
					<tbody></tbody>		
				</table>
			</div>
			
			</div>
			
		</div>
			
		<%@include file="/page/common/script/mybottom.jsp" %>
	
		
		<m:a_button operCode="eduDegreeDetail"  funMethod="eduDegree_edit('编辑','{0}','','')" context="编辑" varName="edit_EduDegree" />
		<m:a_button operCode="deleteEduDegree"  funMethod="eduDegree_del(this,'{0}')" context="删除" varName="del_EduDegree"  className="t-btn btn size-MINI btn-danger-outline"  />
			
	    <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/eduDegree/eduDegree-list.js"></script> 

</body>
  
  
  
</html>
