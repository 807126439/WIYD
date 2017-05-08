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
	
	<%@include file="/page/common/script/main-css.jsp" %>

  </head>
  
  
  <body>
	 <%@include file="/page/common/nav/navLine.jsp" %>
  
  
    <div class="ui-page-container">
	    <div class="ui-panel">
		
	      <div class="ui-panel-body">
	        <div class="ui-table-actions">
	          <div class="btns ui-btn-group">
	            <m:if_own_auth authCode="skipAddUser">
	            	<a href="javascript:void(0)" class="ui-btn ui-btn-normal" onclick="member_add('添加用户','800','600')">添加</a>
	            </m:if_own_auth>         
	            <a href="javascript:location.replace(location.href);" class="ui-btn ui-btn-secondary">刷新</a>	        
	          </div>
	          
	          <div class="inputs">
	            <input style="width:180px" name="search" type="text" class="ui-input" placeholder="用户名" id="user_name">
	            <input type="text"  class="ui-input" style="width:180px" placeholder="姓名" id="cn_name" >
	            <a href="javascript:void(0)" class="ui-btn" onclick="goSearch()"><i class="icon_search" ></i>查询</a>
	          </div>
	         
	        </div>
	        
	        <table id="user_table" class="ui-table ui-table-border-row ui-table-border-col ui-table-hover table_datatable" fixtable>         
	        </table>
	        
	      </div>
	    </div>
	  </div>
  

			
			
		<div style="display: none;" id="changge-pwd-div">
			<div class="pd-20">
	  			<form action="<%=path %>/userController/changePassword.do" method="post" class="form form-horizontal" id="form-change-password">
										
					<div class="row cl">
						<label class="form-label col-xs-4" style="text-align: right;"><span class="c-red">*</span>新密码：</label>
						<div class="formControls col-xs-4">
							<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd" id="new-password" datatype="*6-18" nullmsg="请输入新密码！" >
						</div>
						<div class="col-xs-4"></div>
					</div>
					
					<div class="row cl">
						<label class="form-label col-xs-4" style="text-align: right;"><span class="c-red">*</span>确认密码：</label>
						<div class="formControls col-xs-4">
							<input type="password" class="input-text" autocomplete="off" placeholder="" name="pwd2" id="new-password2" recheck="pwd" datatype="*6-18" errormsg="您两次输入的密码不一致！" nullmsg="请再次输入新密码！" >
						</div>
						<div class="col-xs-4"> </div>
					</div>
					
					<div class="row cl">
						<div class="col-xs-8 col-xs-offset-4">
							<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
						</div>
					</div>
					<input type="hidden" id="changPwd-id"  name="uid" >
					
				</form>
			</div>
		   
		</div>	
			
			
			

  <div class="popup popup_edit_tag">
   	<form action="<%=path %>/userController/addScore.do" method="post" class="form form-horizontal" id="form-addScore">
				
	    <div class="ui-form">
	      <div class="ui-form-item">
	        <label class="ui-form-label length-l">增加积分：</label>
	        <div class="ui-input-inline">
	          <input type="text" class="ui-input" id="add-score" name="score" style="width:250px" datatype="n" nullmsg="增加积分不能为空">
	        </div>
	      
	      </div>
	     
	      <div class="ui-form-item">
	       
	        <div class="ui-input-inline">
	          	<div class="skin-minimal">
					<div class="check-box">
						<input type="checkbox"  id="checkbox-1" value="true"  name="isIgnoreRank" checked="checked" >
						<label for="checkbox-1">增加积分后，该用户不再参与积分排名</label>
					</div>
				</div>
	          
	        </div>
	      </div>
	      
     </div>
     
     	<input type="hidden" id="add-score-userid"  name="userId" >
    </form>  
     
  </div>		
			
			
						
					
		 <%@include file="/page/common/script/main-js.jsp" %>
	
		
		<m:ljs_button operCode="getUserDetail"  funMethod="member_edit('编辑','{0}','800','600')" context="编辑" varName="user_edit" />
		<m:ljs_button operCode="addScore"  funMethod="addScore('{0}','{1}')" context="增加积分" varName="score_add" />		
		<m:ljs_button operCode="changePassword"  funMethod="change_password('{0}','{1}')" context="修改密码" varName="user_change_pwd" />
		<m:ljs_button operCode="deleteUser"  funMethod="user_del(this,'{0}')" context="删除" varName="del_user" />
	 	
		
		<script type="text/javascript" src="<%=path%>/plug-in/web/system/user/user-list.js"></script> 
		<script type="text/javascript">
		
		
		
			function addScore(id,name){
			 $("#add-score-userid").val(id);
			 $("#add-score").val("");		
			    bindForm("form-addScore",null,2,goSearch);
			
			
				 layer.open({
				    type:1,
				    shade:0.3,
				    shadeClose:true,
				    area:"500px",
				    title:"为"+name+"增加积分",
				    content:$(".popup_edit_tag"),
				    skin:"layer-skin-kb layer-skin-kb-bodered",
				    btn:['确定','取消'],
				    yes:function(index,overlay) {
				     sumbitForm("form-addScore"); 
				    }
				  });
		
			}
		
		</script>
		
</body>
  
  
  
</html>
