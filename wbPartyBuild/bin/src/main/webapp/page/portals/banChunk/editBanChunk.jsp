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
    
    <title>修改板块</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	

  </head>
 
  
  <body>
	<div class="pd-20">
	 <form action="<%=path %>/banChunkController/editBanChunk.do" method="post" class="form form-horizontal" id="form-commun-edit" >       
	    <input name="id" value="${BanChunkItem.id}" type="hidden">
	    
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>板块名称：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text"  name="chunkName"  datatype="*2-80"  nullmsg="名称不能为空" value="${BanChunkItem.chunkName}">
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>板块备注：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text"  name="chunkMemo"  datatype="*2-80"  nullmsg="备注不能为空" value="${BanChunkItem.chunkMemo}">
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<div class="row cl">
	      <label class="form-label col-xs-4 col-sm-2">排序：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${BanChunkItem.sortNum}"  name="sortNum" datatype="n" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
		
	    <div class="row cl">
	      <label class="form-label col-xs-4 col-sm-2">是否公开：</label>
	      <div class="formControls col-xs-6 col-sm-6 skin-minimal">
	        	<div class="check-box">
					<input type="checkbox" id="checkbox-1" value=1   name="status" ${BanChunkItem.status==1?'checked="checked"':''} />
					<label for="checkbox-1">&nbsp;</label>
				</div>
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	    
		
		

			
		
			
		
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="addCommun" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
				

	  
	   
	    
	    
	  </form>
	  
	  
	</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/banChunk/banChunk-edit.js"></script>
		
	
</body>
  
  
  
</html>
