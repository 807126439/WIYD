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
    
    <title>修改栏目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<div class="pt-5 pl-20 pr-20">
	
		 <div id="tab_file" class="HuiTab" >
	 	    <div class="mytabBar cl"><span>编辑</span><span>设置上级栏目</span></div>  	
		    <div class="Hui-article" style="top:45px" >
		 
			    <div class="mytabCon" >
			    
			    	 <form action="<%=path %>/columnMuController/editColumnMu.do" method="post" class="form form-horizontal" id="form-column-edit">
		  	      
					    <%@include file="/page/portals/columnMu/common/edit-item.jsp" %>
					
					
						<input type="hidden"  name="id" value="${colMuItem.id}">
						
					  
					    <div class="row cl">
					      <div class="col-xs-9 col-xs-offset-3">
					         <m:b_button operCode="editColumnMu" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
					      </div>
					    </div>
					  </form>
			    				
			    </div>
				  
			    <div class="mytabCon" >
			  
			  		
			  		 <form action="<%=path %>/columnMuController/moveColumnMu.do" method="post" class="form form-horizontal" id="form-column-move">
		  	      
						<div class="row cl" >
					      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>上级分类：</label>
					      <div class="formControls col-xs-6 col-sm-6">
					     	<span class="select-box" >
								<select id="choice-par" class="select" name="parId" >
									<option value="" >顶级分类</option>
									<c:forEach items="${lanMuList}" var="l">
										<option value="${l.id}" title="${l.title}" ${par eq l.id? 'selected="selected"':'' }>${l.title}</option>
									</c:forEach>
								</select>
							</span> 
					     
					      </div>
					      <div class="col-xs-3 col-sm-3"> </div>
					    </div>
					
					
						<input type="hidden"  name="colId" value="${colMuItem.id}">
						
					  
					    <div class="row cl">
					      <div class="col-xs-9 col-xs-offset-3">
					         <m:b_button operCode="moveColumnMu" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
					      </div>
					    </div>
					  </form>
								
			 
			 	 
			  </div>
				  
			  			  
			  
	     </div>
	     
	  </div>
	
	
	
	
	
	 
	  
	  
	</div>
		

	
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript">
	 $(function(){
		$.Huitab("#tab_file .mytabBar span","#tab_file .mytabCon","current","click","0");
	});
	</script>
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/column/column-edit.js"></script> 
	
		
</body>
  
  
  
</html>
