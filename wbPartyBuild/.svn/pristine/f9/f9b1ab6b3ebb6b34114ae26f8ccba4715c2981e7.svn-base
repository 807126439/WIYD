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
    
    <title>添加用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/userController/addUser.do" method="post" class="form form-horizontal" id="form-user-add">
	  	<h4>基本信息</h4>
	    <div class="line"></div>      
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>用户名：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  placeholder="" id="user-name" name="userName" datatype="*2-16" nullmsg="用户名不能为空">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3">中文名：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  placeholder="" id="user-name" name="chineseName" datatype="*2-16" ignore="ignore">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>密码：</label>
	      <div class="formControls col-xs-6">
	        <input type="password" class="input-text"  placeholder="" id="user-pwd" name="pwd" datatype="*6-16" nullmsg="密码不能为空">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    <div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-6">
				<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd2" id="new-password2" recheck="pwd" datatype="*6-18" errormsg="您两次输入的密码不一致！" nullmsg="请再次输入新密码！" >
			</div>
			<div class="col-xs-3"> </div>
		</div>    
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>手机号码：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  name="phone" id="phone" datatype="m" ignore="ignore"  />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3"><span class="c-red">*</span>选择角色：</label>
	      <div class="formControls col-xs-6"  >
	        <input type="text" class="input-text" id="selected-roles"  datatype="*" nullmsg="请选择角色！"  readonly="readonly" onclick="choiceRoles()"/>	     	     	
	     	<%@include file="/page/system/user/common/roleList.jsp" %>
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3">邮箱：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text" placeholder="@" name="email" id="email" datatype="e" ignore="ignore">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>
	    
	    <h4>账号信息</h4>
	    <div class="line"></div>
	    
	   <div class="row cl">
	      <label class="form-label col-xs-3">座机号码：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  name="tel"  datatype="n6-20" nullmsg="请输入座机号码！" ignore="ignore"  />
	      </div>
		 <div class="col-xs-3"> </div>
	    </div>
	   
	    <div class="row cl">
	      <label class="form-label col-xs-3">联系地址：</label>
	      <div class="formControls col-xs-6">
	        <input type="text" class="input-text"  name="linkAddress" datatype="*6-120" nullmsg="请输入座机号码！" ignore="ignore"   />
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
					    <option value="男" selected="selected">男</option>
					    <option value="女" >女</option>
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
						    <option value="${f.id}">${f.eduName}</option>
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
					<input type="checkbox" id="checkbox-1" value="true"  name="isParty"  >
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
		      	      		<input type="radio" id="st-${vs.index}" class="cl-flag" name="partyStatus" value="${f.dictValue}"  >
		      	      	</c:when>
		      	      	<c:otherwise>
		      	      		 <input type="radio" id="st-${vs.index}" class="cl-flag" name="partyStatus" value="${f.dictValue}" >
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
					<input type="checkbox" id="checkbox-1" value="true"  name="isIgnoreStat"  >
					<label for="checkbox-1">&nbsp;</label>
				</div>
			</div>
		</div>
	   	
	    
		<div class="row cl">
			<label class="form-label col-xs-3">备注：</label>
			<div class="formControls col-xs-6 ">
				<textarea name="memo" cols="" rows="" class="textarea" datatype="*2-255"  ignore="ignore"  onKeyUp="textarealength(this,100)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/255</p>
			</div>
			<div class="col-xs-3"> </div>
		</div>
	
	  
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">
	         <m:b_button operCode="addUser" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>
	  
	  
	</div>
		

	
	
	<%@include file="/page/common/script/allbuttom.jsp" %>
	
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/user-add.js"></script> 
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/role-for-user.js"></script> 	
		
</body>
  
  
  
</html>
