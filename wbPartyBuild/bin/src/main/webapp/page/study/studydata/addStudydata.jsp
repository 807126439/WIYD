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
    
    <title>添加资料管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  
<div class="pd-20">
	  <form action="<%=path %>/studyDataController/addStudyData.do" method="post" class="form form-horizontal" id="form-StydyData" >
	
		 <div class="row cl" id="row">
			<label class="form-label col-xs-2 col-sm-2">资料文件：</label>
			<div class="formControls col-xs-8 col-sm-8">
				
				<c:choose>
					<c:when test="${!empty contentItem.viewPath}">
						<div id="paly" class="pd-20"></div>  	
					</c:when>
				    <c:otherwise>				    	
						    		
						<div class="uploader-thum-container">
							<div id="fileList" class="uploader-list">														
						</div>
							<div id="filePicker">选择文件</div>
							<button id="btn-cancel" class="btn btn-default btn-uploadstar radius ml-10" type="button">撤销</button>
						</div>
				    	
				    </c:otherwise>
				</c:choose>
			</div>	
			<div class="col-xs-2 col-sm-2"></div>
		 </div>
		 
		 <div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">学习类别：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select id="change-table" class="select" style="width: 500px;height: 30px" name="cateId">
					<c:forEach items="${scsList}" var="list">
						<option value="${list.id}" selected="selected">【${list.cateNum}】${list.cateName}</option>
					</c:forEach>
				</select>
			</div>
		</div>

	   	<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>资料编号：</label>
			<div class="formControls col-xs-8 col-sm-8">
			<input type="text" class="input-text" id="dataNum" value="${Dto.dataNum}"  datatype="*2-20"  nullmsg="资料编号不能为空">
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
		
		
	   	<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>资料名称：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" id="dataName" value="${Dto.dataName}"  datatype="*2-80"  nullmsg="资料名称不能为空">
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
			 
			   	   
	   	<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>资料备注：</label>
			<div class="formControls col-xs-8 col-sm-8">
				
				<textarea style="height:200px; resize:none;"   placeholder="说点什么...."  onchange="if(value.length>200)value=value.substr(0,200)" class="input-text" id="dataMemo" cols="30" rows="10">${Dto.dataMemo}</textarea>
			
			</div>
		</div>
	    <div class="row cl">
	      <div class="col-xs-8 col-xs-offset-2 col-sm-offset-2" >
	         <m:b_button operCode="addStudyData" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	  </form>	  
</div>
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.min.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/lang/zh-cn/zh-cn.js"></script> 
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/web/scripts/study/studyData/studyData-add.js"></script>
  </body>
</html>
