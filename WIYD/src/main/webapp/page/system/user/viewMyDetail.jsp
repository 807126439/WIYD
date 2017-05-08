<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="renderer" content="webkit|ie-stand">
  <title>个人信息</title>


  <%@include file="/page/common/script/main-css.jsp" %>  
  <link href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
  <link href="<%=path %>/plug-in/web/common/upload/css/uploadButton.css" rel="stylesheet" type="text/css" />

  
</head>
  
 
 <body>
 	<div class="cl pd-20" style=" background-color:#5bacb6">
		
		
		
		
		<div class="row cl">
				<label class="form-label col-xs-3">头像：</label>
						
				<div class="formControls col-xs-9">
					 <div class="uploader-div">
					    <div class="uploader-list thelist">
					       <c:if test="${!empty userItem.photoId}">
						    	<div class="item mb-5">
							        <div class="pic-box">
							       	 <img  src="${userItem.headImgUrl}" width="96" height="96">
							        </div>
							    </div>	
							    
						    </c:if>				    
					    </div>
					    <div class="btns">					       
					       
					        <div id="picker1" class="picker">${empty model.photoFileId? '选择图片':'更换头像'}</div>
					        <a class="btn btn-default ctlBtn size-S" style="display:none">开始上传</a>
					        <button  class="btn btn-default btn-uploadstar radius ml-10 btn-cancel size-S" type="button" style="display:none">取消</button>
					    </div>
				    </div>
				</div>

				
		</div>
		
		
		
		
		
	</div>
	<div class="pd-20">
		<table class="table">
			<tbody>
				<tr>
					<th class="text-r" width="80">姓名：</th>
					<td>${userItem.chineseName}</td>
				</tr>
				<tr>
					<th class="text-r">手机：</th>
					<td>${userItem.phone}</td>
				</tr>
				<tr>
					<th class="text-r">邮箱：</th>
					<td>${userItem.email}</td>
				</tr>
				<tr>
					<th class="text-r">所属部门：</th>
					<td>${userItem.departName}</td>
				</tr>			
				<tr>
					<th class="text-r">注册时间：</th>
					<td><fmt:formatDate value="${userItem.gmtCreate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th class="text-r">积分：</th>
					<td>${userItem.score}</td>
				</tr>
				<tr>
					<th class="text-r">等级：</th>
					<td>${userItem.scoreName}</td>
				</tr>
			</tbody>
		</table>
	</div>

 
  <%@include file="/page/common/script/main-js.jsp" %>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script> 	
  <script type="text/javascript" src="<%=path %>/plug-in/web/common/upload/js/singal-upload-headimg.js"></script> 	
  <script type="text/javascript">
     var mf = initUploader("picker1",path+"/userController/changeHeadImg.do",{});	 
  </script>

  
</body>
 
 
 
</html>
