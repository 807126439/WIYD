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
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/userController/editUser.do" method="post" class="form form-horizontal" id="form-user-edit">	  
	
	  	<h4>基本信息</h4>
	    <div class="line"></div> 
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>用户名：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text" value="${userItem.userName}"  placeholder="" id="user-name" name="userName" datatype="*2-16" nullmsg="用户名不能为空">
	      </div>
	   	<div class="col-xs-3"> </div>
	    </div>
	    	   
	    <div class="row cl">
	      <label class="form-label col-xs-3">中文名：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  value="${userItem.chineseName}" id="user-name" name="chineseName" datatype="*2-16" ignore="ignore">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>手机号码：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text" value="${userItem.phone}"   name="phone" id="phone" datatype="m"  ignore="ignore"   />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    
	       
	     <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>选择角色：</label>
	      <div class="formControls col-xs-6"  >
	        <input type="text" class="input-text" id="selected-roles" value="${ownRoles}"  datatype="*" nullmsg="请选择角色！"  readonly="readonly" onclick="choiceRoles()"/>	     	     	
	     	<%@include file="/page/system/user/common/roleList.jsp" %>
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3">邮箱：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text" value="${userItem.email}" placeholder="@" name="email" id="email" datatype="e" ignore="ignore">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">	       			
			<label class="form-label col-xs-3">是否可用：</label>
			<div class="formControls col-xs-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" value="true"  name="enabled"  ${userItem.enabled == true? 'checked="checked"':''}>
					<label for="checkbox-1">&nbsp;</label>
				</div>
			</div>
			<div class="col-xs-7"> </div>
		</div>
	    
	   <h4>账号信息</h4>
	   <div class="line"></div>   
	   <div class="row cl">
	      <label class="form-label col-xs-3">座机号码：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  name="tel" value="${userItem.tel}" datatype="n6-20" nullmsg="请输入座机号码！" ignore="ignore"  />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	   
	    <div class="row cl">
	      <label class="form-label col-xs-3">联系地址：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  name="linkAddress"  value="${userItem.linkAddress}" datatype="*6-120" nullmsg="请输入座机号码！" ignore="ignore"   />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3">年龄：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  name="age"  value="${userItem.age}" datatype="n" ignore="ignore"   />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3">性别：</label>
	      <div class="formControls col-xs-6">
		        <span class="select-box">
					  <select class="select" size="1" name="sex">
					    <option value="男" ${userItem.sex == '男'? 'selected="selected"':''}>男</option>
					    <option value="女" ${userItem.sex == '女'? 'selected="selected"':''}>女</option>
					  </select>
				</span>
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3">受教育程度：</label>
	      <div class="formControls col-xs-6">
		        <span class="select-box">
					  <select class="select" size="1" name="eduDegreeId">
					  	 <option value=""></option>
						  <c:forEach items="${requestScope.eduDict}" var="f" varStatus="vs">
						    <option value="${f.id}" ${userItem.eduDegree.id == f.id? 'selected="selected"':''}>${f.eduName}</option>
						  </c:forEach>  
					  </select>
				</span>
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    <div class="row cl">
	        <label class="form-label col-xs-3">是否为党员：</label>
			<div class="formControls col-xs-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" value="true"  name="isParty" ${userItem.isParty == true? 'checked="checked"':''} >
					<label for="checkbox-1">&nbsp;</label>
				</div>
			</div>
		</div>
	    
	    
	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3">党员状态：</label>
		      <div class="formControls col-xs-5 skin-minimal">
		      	<c:forEach items="${requestScope.flagDict}" var="f" varStatus="vs">
		      	    <div class="radio-box">
		      	      <c:choose>
		      	      	<c:when test="${vs.index==1}">
		      	      		<input type="radio" id="st-${vs.index}" class="cl-flag" name=partyStatus value="${f.dictValue}"  ${userItem.partyStatus == f.dictValue? 'checked="checked"':''}>
		      	      	</c:when>
		      	      	<c:otherwise>
		      	      		 <input type="radio" id="st-${vs.index}" class="cl-flag" name="partyStatus" value="${f.dictValue}" ${userItem.partyStatus == f.dictValue? 'checked="checked"':''}>
		      	      	</c:otherwise>
		      	      </c:choose>
			          <label for="flag-${vs.index}">${f.dictItem}</label>
		        	</div>
		      	</c:forEach>
		      </div>
		      <div class="col-xs-4 col-sm-4"> </div>
	   	  </div>
	    
	     	
	   	 <div class="row cl">
	        <label class="form-label col-xs-3">是否忽略统计：</label>
			<div class="formControls col-xs-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" value="true"  name="isIgnoreStat"  ${userItem.isIgnoreStat == true? 'checked="checked"':''}>
					<label for="checkbox-1">&nbsp;</label>
				</div>
			</div>
		</div> 
	    
	    
		<div class="row cl">
			<label class="form-label col-xs-3">备注：</label>
			<div class="formControls col-xs-6 ">
				<textarea name="memo" cols="" rows="" class="textarea" datatype="*2-255"  ignore="ignore"  onKeyUp="textarealength(this,100)">${userItem.memo}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/255</p>
			</div>
			<div class="col-xs-3"> </div>
		</div>

	
		
	
	   
	   
	    
	   <input type="hidden" name="id" value="${userItem.id}" >
	  
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">	        
	       	 <m:b_button operCode="editUser"  type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>
	  
	  
	</div>
		
		
		
	<%@include file="/page/common/script/allbuttom.jsp" %>
	
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/user-edit.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/role-for-user.js"></script> 
		
		
		
		
</body>
  
  
</html>
