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
    
    <title>关联主题活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	 

  </head>
  
  
  <body>
	
	<div class="pd-20">
	  <form action="<%=path %>/contentController/ConnectActivity.do" method="post" class="form form-horizontal" id="form-ConnectActivity" >

		<input type="hidden" name="parId" value="${parId}">
	
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>城建人活动：</label>
				<span class="select-box " style="width:70% ">
  					<select class="select" size="1" name="id">
  						<c:forEach var="ThemeActivityDTO" items="${themeActivityItem}">
  							<option value="${ThemeActivityDTO.id}">${ThemeActivityDTO.activityName}</option>
  						</c:forEach>
  					</select>
				</span>
		</div>
		
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="addAwardWinningWorks" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	    
	    
	  </form>
	  
	  
	</div>
	
	
		

	
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/connectActivity.js"></script> 
		
</body>
  
  
  
</html>
