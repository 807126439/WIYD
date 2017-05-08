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
    
    <title>题库管理</title>
    
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
		<div class="page-container">
 				<div class="t_searchbar">																								
					<span class="select-box" style="width:160px;">
						<select class="select" name="categoryId" size="1" id="categoryId">	
							<option value="">请选择题目类别</option>				
							<c:forEach items="${categoryList}" var="c">
							
								<option value="${c.id}">${c.cateName}</option>
							</c:forEach>
						</select>
					</span> 
					<span class="select-box" style="width:160px;">
						<select class="select" size="1" id="topicType">	
								<option value="">请选择题目类型</option>								
								<option value="1">单选</option>
								<option value="2">多选</option>
								<option value="3">判断</option>
								<option value="4">填空</option>				
						</select>
				    </span> 
				  	题目文本：<input type="text"  id="questionText" class="input-text" style="width:250px" placeholder="题目文本" >
					
					<button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				
					
				
				<div class="t_ctrlbar"> 
				 <span class="btns">
				   	<m:a_top context="导入题目"  operCode="skipImportTopic"  funMethod="topic_import('上传题目','','')"  className="btn btn-success radius"/>
				   	<m:a_top context="录入题目"  operCode="skipAddTopic"  funMethod="topic_add('上传题目','','')"  className="btn btn-primary radius"/>				  
				   	<m:a_top context="删除题目"  operCode="deleteTopic"  funMethod="datadel('删除题目','','')"  className="btn btn-danger radius"/>
				</span> 
				</div>	
		
			
					<table id="topic_table" class="table table-border table-bordered table-hover table-bg table-sort">					
					</table>
			
		</div>	
		</div>
		<%@include file="/page/common/script/mybottom.jsp" %>
		
		<m:a_button operCode="skipEditTopic"  funMethod="topic_edit('{0}')" context="修改" varName="edit_topic" />
		<m:a_button operCode="deleteTopic"  funMethod="topic_delete(this,'{0}')" context="删除" varName="delete_topic" />
 <script type="text/javascript" src="<%=path%>/plug-in/web/scripts/study/topic/topicList.js"></script>
  </body>
</html>
