<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>新增类别</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>

</head>

<body>
	<div class="pd-20" style="text-align: center;">
		<form action="<%=path%>/studyCategoryController/addCategory.do" method="post" class="form form-horizontal" id="form-edit">
			<input id="msId" name="id" value="${categoryDto.id}" type="hidden">
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>类别编号：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="cateNum" datatype="*2-20" nullmsg="类别编号不能为空" >
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>类别名称：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="cateName" datatype="*2-50" nullmsg="类别名称不能为空" >
				</div>
				<div class="col-sm-2"></div>
			</div>


			<div class="row cl">
				<div class="col-xs-34" style="margin:0 auto;text-align:center">					
					<m:b_button operCode="addCategory" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />					
				</div>
			</div>
		</form>
	</div>

	<%@include file="/page/common/script/operbuttom.jsp"%>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/study/studycategory/addStudyCategory.js"></script>
</body>
</html>
