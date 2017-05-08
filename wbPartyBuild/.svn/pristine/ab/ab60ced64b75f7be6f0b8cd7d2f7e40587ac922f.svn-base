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
    
    <title>修改互动议题</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	

  </head>
 
  
  <body>
	<div class="pd-20">
	 <form action="<%=path %>/communController/editCommun.do" method="post" class="form form-horizontal" id="form-commun-edit" >       
	    <input name="id" value="${comItem.id}" type="hidden">
	    <input name="type" value="2" type="hidden">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>议题：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text"  name="title"  datatype="*2-80"  nullmsg="议题不能为空" value="${comItem.title}">
			</div>
			<div class="col-sm-2"></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>发起人：</label>
			<div class="formControls col-xs-4 col-sm-3">
				<input type="text" class="input-text"  name="sponsor"  datatype="*2-30"  nullmsg="发起人不能为空" value="${comItem.sponsor}">
			</div>
			<div class="col-sm-3"></div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">议题简述：</label>
			<div class="formControls col-xs-7 col-sm-7" style="width: 900px;"> 
				<script id="editor" type="text/plain" style="width:100%;height:400px;">${comItem.content}</script> 
			</div>
		</div>

			
		<div class="row cl">
		    	<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>开始日期：</label>    
			    <div class="formControls col-xs-3 col-sm-3">
					<input type="text" name="startDate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" 
						 class="input-text Wdate" id="startDate"  datatype="*8-20"  nullmsg="开始日期不能为空"  readonly="readonly" value="<fmt:formatDate value="${comItem.startDate}" pattern="yyyy-MM-dd"/>">
				</div>
			   <div class="col-xs-5 col-sm-5"></div>
		</div>
		
			
		<div class="row cl">
		    	<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>结束日期：</label>
			    <div class="formControls col-xs-3 col-sm-3">
					<input type="text" name="endDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" 
						 class="input-text Wdate" id="endDate" datatype="*8-20"  nullmsg="结束日期不能为空"  readonly="readonly" value="<fmt:formatDate value="${comItem.endDate}" pattern="yyyy-MM-dd"/>">
				</div>
			   <div class="col-xs-5 col-sm-5"></div>
		</div>
		

	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="editCommun" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
				
	    
	    
	  </form>
	  
	  
	</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script> 
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/communication/commun-edit.js"></script>
		
	
</body>
  
  
  
</html>
