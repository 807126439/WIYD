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
    
    <title>修改主题活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	

  </head>
 
  
  <body>
	<div class="pd-20">
	 <form action="<%=path %>/themeActivityController/editThemeActivity.do" method="post" class="form form-horizontal" id="form-themeActivity-edit" >       
	    <input name="id" value="${theme.id}" type="hidden">	    
  
	    <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>活动名称：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text"  name="activityName"  datatype="*2-80"  nullmsg="活动名称不能为空" value="${theme.activityName}">
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		   <div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>活动类型：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text"   readonly="readonly" value="${theme.activityTypeName}">				
			</div>
		</div>	
		
		<div class="row cl">
		    	<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>开始时间：</label>
			    <div class="formControls col-xs-3 col-sm-3">
					<input type="text" name="startDate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" 
				     class="input-text Wdate" id="startDate"  datatype="*8-20"  nullmsg="开始日期不能为空"  value="<fmt:formatDate value="${theme.startDate}" pattern="yyyy-MM-dd"/>">
				</div>
			   <div class="col-xs-5 col-sm-5"></div>
		</div>
		
			
		<div class="row cl">
		    	<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>结束时间：</label>
			    <div class="formControls col-xs-3 col-sm-3">
					<input type="text" name="endDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" 
				     class="input-text Wdate" id="endDate" datatype="*8-20"  nullmsg="结束日期不能为空"   value="<fmt:formatDate value="${theme.endDate}" pattern="yyyy-MM-dd"/>">
				</div>
			   <div class="col-xs-5 col-sm-5"></div>
		</div>
		
		
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">活动说明：</label>
			<div class="formControls col-xs-8 col-sm-8" style="width: 800px;"> 
				<script id="editor" type="text/plain" style="width:100%;height:270px;">${theme.content}</script> 
			</div>
		</div>
	   
	   
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="editThemeActivity" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>
	</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script> 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/themeActivity/themeActivity-edit.js"></script>
		
	
</body>
  
  
  
</html>
