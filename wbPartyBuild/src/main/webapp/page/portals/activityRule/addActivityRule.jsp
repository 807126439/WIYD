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
    <title>添加活动规则</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<%@include file="/page/common/script/mytop.jsp" %>
  </head>
  
  
  <body>	
	<div class="pd-20">
	  <form action="<%=path %>/activityRuleController/addActivityRule.do" method="post" class="form form-horizontal" id="form-activityRule-add" >
		<input type="hidden" name="activityId" value="${activityId}">
			
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>排序：</label>
			<div class="formControls col-xs-7 col-sm-2">
				<input type="text" class="input-text"  name="num"  nullmsg="排序不能为空">
			</div>
			<div class="col-sm-2"></div>
			
			<label class="form-label col-xs-3 col-sm-2">主要规则：</label>		
			<div class="formControls col-xs-1 skin-minimal">
				<div class="check-box">
					<input type="checkbox"  value="true"  name="isMain" >
					<label for="checkbox-is_index">&nbsp;</label>
				</div>			
			</div>
			
		</div>
				
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>规则内容：</label>
			<div class="formControls col-xs-8 col-sm-8" style="width:650px;"> 
				<script id="editor" type="text/plain" style="width:100%;height:270px;"></script> 
			</div>
		</div>
		
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="addActivityRule" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>	  
	</div>
	

	
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
  	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script> 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/activityRule/activityRule-add.js"></script> 		
</body>
   
</html>