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
    
    <title>审核稿件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
 
  
  <body>
	<div class="pd-20">
	 <form action="<%=path %>/manuscriptController/checkManuscript.do" method="post" class="form form-horizontal" id="form-manuscript-check" >       
		<input id="msId" name="msId" value="${result.msId}" type="hidden">

		
		
		<div class="row cl">
			<div class="formControls col-8 skin-minimal">
				<div class="radio-box">
					<input type="radio"  name="status"  value="2" ${result.status eq 2? 'checked="checked"':'' }>
					<label>预审核</label>
				</div>
				<div class="radio-box">
					<input type="radio"  name="status"  value="1" ${result.status eq 1? 'checked="checked"':'' }>
					<label>通过</label>
				</div>
				<div class="radio-box">
					<input type="radio"  name="status" value="0"  ${result.status eq 0? 'checked="checked"':'' }>
					<label>不通过</label>
				</div>
			</div>
		</div>
		
	    
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="checkManuscript" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>					    
	  </form>	  
	</div>
	<input id="pageType" value="${pageType}" type="hidden">
	<%@include file="/page/common/script/operbuttom.jsp" %>
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/manuscript/manuscript-check.js"></script>
	
</body>
  
  
  
</html>
