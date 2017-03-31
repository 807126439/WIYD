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

<title>录入试卷</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>

</head>

<body>
	<div class="pd-20" style="text-align: center;">
		<form action="<%=path%>/examPaperController/addExamPaper.do" method="post" class="form form-horizontal" id="form-examPaper-add">
			
			<input type="hidden" name="paperType" value="1">
			
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-3 col-sm-2">试卷名称：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="paperName"  datatype="*1-100" nullmsg="请填写试卷名称" >			
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-3 col-sm-2">考试时间：</label>
				<div class="formControls col-xs-3 col-sm-2">
					<input type="text" class="input-text" name="examMinute"  datatype="n" nullmsg="请填写考试时间" style="width:70%;margin-right:2px">分钟
				</div>
			</div>
						
			<div class="row cl" id="cateSelectBox">
			<label class="form-label col-xs-3 col-sm-3">题目类别：</label>
			<div class="formControls col-xs-3 col-sm-2">
			    <span class="select-box" style="width:160px;">
					<select class="select" name="categoryId" size="1">	
						<option value="">请选择题目类别</option>				
						<c:forEach items="${categoryList}" var="c">						
							<option value="${c.id}">${c.cateName}</option>
						</c:forEach>
					</select>
				</span> 
				</div>
			</div>
			
				<div class="row cl" style="margin-top: 20px;">
					<label class="form-label col-xs-3 col-sm-2">单选题数目：</label>
					<div class="formControls col-xs-2 col-sm-2">
						<input type="text" class="input-text" name="singleNum"  datatype="n" ignore="ignore" >
					</div>
					
					<label class="form-label col-xs-3 col-sm-2">多选题数目：</label>
					<div class="formControls col-xs-2 col-sm-2">
						<input type="text" class="input-text" name="multiNum"  datatype="n" ignore="ignore">
					</div>
				
				</div>

				<div class="row cl" style="margin-top: 20px;">
					<label class="form-label col-xs-3 col-sm-2">填空题数目：</label>
					<div class="formControls col-xs-2 col-sm-2">
						<input type="text" class="input-text" name="blankNum"  datatype="n" ignore="ignore">
					</div>
				
					<label class="form-label col-xs-3 col-sm-2">判断题数目：</label>
					<div class="formControls col-xs-2 col-sm-2">
						<input type="text" class="input-text" name="judgeNum"  datatype="n" ignore="ignore">
					</div>
	
				</div>
	
	
			<div class="row cl">
				<div class="col-xs-2 col-xs-offset-4 col-sm-offset-5" style="margin-top: 20px;">					
					<m:b_button operCode="addExamPaper"  type="submit"  value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />					
				</div>
			</div>
		</form>
	</div>

	<%@include file="/page/common/script/operbuttom.jsp"%>
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/study/examPaper/addExamPaperR.js"></script>
		
</body>
</html>
