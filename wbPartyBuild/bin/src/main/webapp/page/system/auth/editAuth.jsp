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
    
    <title>修改权限</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/authController/editAuth.do" method="post" class="form form-horizontal" id="form-auth-edit">
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>权限名：</label>
	      <div class="formControls col-xs-5">
	        <input type="text" class="input-text" value="${authItem.authText}"  placeholder="" id="auth-name" name="authText" datatype="*3-50" nullmsg="权限名不能为空">
	      </div>
	      <div class="col-xs-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>权限码：</label>
	      <div class="formControls col-xs-5">
	        <input type="text" class="input-text" value="${authItem.authCode}"   placeholder="" id="auth-text" name="authCode" datatype="*2-35" nullmsg="权限码不能为空">
	      </div>
	      <div class="col-xs-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>资源地址：</label>
	      <div class="formControls col-xs-5">
	        <input type="text" class="input-text" value="${authItem.resourecesUrl}"  placeholder="" id="auth-url" name="resourecesUrl" datatype="*2-256"  ignore="ignore" >
	      </div>
	      <div class="col-xs-4"> </div>
	    </div>
   
	  <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>状态：</label>
	      <div class="formControls col-xs-5 skin-minimal">
	      
	      	<c:forEach items="${requestScope.flagDict}" var="f" varStatus="vs">
	      	    <div class="radio-box">
	      	      <c:choose>
	      	      	<c:when test="${vs.index==1}">
	      	      		<input type="radio" id="flag-${vs.index}" class="cl-flag" name="flag" value="${f.dictValue}" datatype="*" nullmsg="请选择状态！" ${authItem.flag == f.dictValue? 'checked="checked"':''}>
	      	      	</c:when>
	      	      	<c:otherwise>
	      	      		 <input type="radio" id="flag-${vs.index}" class="cl-flag" name="flag" value="${f.dictValue}" ${authItem.flag == f.dictValue? 'checked="checked"':''}>
	      	      	</c:otherwise>
	      	      	      	      
	      	      </c:choose>
	      	             
		          <label for="flag-${vs.index}">${f.dictItem}</label>
	        	</div>
	      			      	
	      	</c:forEach>
	      
      
	      
	      </div>
	      <div class="col-xs-4"> </div>
   	  </div>
	    
	    
	 <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>权限类型：</label>
	      <div class="formControls col-xs-5 skin-minimal">
	      	<c:forEach items="${requestScope.typeDict}" var="f" varStatus="vs">
	      	    <div class="radio-box">
	      	      <c:choose>
	      	      	<c:when test="${vs.index==1}">
	      	      		<input type="radio" id="authType-${vs.index}" class="cl-type"  name="authType" value="${f.dictValue}" datatype="*" nullmsg="请选择权限类型！" ${authItem.authType == f.dictValue? 'checked="checked"':''}>
	      	      	</c:when>
	      	      	<c:otherwise>
	      	      		  <input type="radio" id="authType-${vs.index}" class="cl-type" name="authType" value="${f.dictValue}" ${authItem.authType == f.dictValue? 'checked="checked"':''}>
	      	      	</c:otherwise>
	      	      	      	      
	      	      </c:choose>
	      	             
		          <label for="authType-${vs.index}">${f.dictItem}</label>
	        	</div>
	      			      	
	      	</c:forEach>
      
	      </div>
	      <div class="col-xs-4"> </div>
   	  </div>  

	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>父节点：</label>
	      <div class="formControls col-xs-5">
	        <input type="text" class="input-text"  placeholder="父节点"  id="par-name" value="${authItem.parentName}" readonly="readonly">	  
	        <input type="hidden" name="parentId" id="auth-par" value="${authItem.parentId}" >
	     	
	     	<div id="iframe_dir" style="width: 395px;height: 341px;padding-top:1px;display: none;">	        	
	        </div>
	     
	      </div>
	      <div class="col-xs-4"><a href="javascript:;" onclick="showDir(this)" id="open_key">展开目录</a><a href="javascript:;" onclick="setParNull()" class="ml-5">设置为空</a> </div>
	    </div>   
	    
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>排序：</label>
	      <div class="formControls col-xs-5">
	        <input type="text" class="input-text" value="${authItem.authOrder}"  placeholder="" id="auth-order" name="authOrder" datatype="n" ignore="ignore" >
	      </div>
	      <div class="col-xs-4"> </div>
	    </div>  
	    
	
		 <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>图案：</label>
	      <div class="formControls col-xs-5">
	        <input type="text" class="input-text" value="${authItem.icon}"   placeholder="" id="auth-icon" name="icon" datatype="*" ignore="ignore" >
	      </div>
	      <i class="Hui-iconfont">${authItem.icon}</i> 
	      <div class="col-xs-4"> </div>
	    </div>  
	
	     <input type="hidden" name="id" value="${authItem.id}" />
	  
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">
	        <m:b_button operCode="editAuth" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>
	  
	  
	</div>
		
		
		
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	
	
	
		
	<script type="text/javascript" src="<%=path %>/plug-in/web/scripts/system/auth/auth-edit.js"></script>
		
		
</body>
  
  
</html>
