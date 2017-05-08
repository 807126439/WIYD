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

<title>编辑试卷</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/page/common/script/mytop.jsp"%>
<style>
.choosenlist{min-height:100px;height:auto;}
.choosenlist li{
	background-color: #5a98de;
    border: 1px solid #4b90de;
    border-radius: 2px;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    float: left;
    font-size: 14px;
    line-height: 26px;
    margin: 5px;
    position: relative;
    text-align: center;
    width: 120px;
}
.choosenlist li i{display:none}
.choosenlist li:hover i{display:inline-block}
.Hui-iconfont-close2:before { content: "\e706"; }
</style>
</head>

<body>
	<div class="pd-20" style="text-align: center;">
		<form action="<%=path%>/examPaperController/editExamPaper.do" method="post" class="form form-horizontal" id="form-examPaperC-add">
		
			
			<input type="hidden" name="paperType" value="2">
			<input type="hidden" name="id" value="${result.id}">
			<input id="choosentopic" name="choosentopic" value="${result.choosentopic}" type="hidden">
			
			<input id="topicScore"  value="${result.topicScore}" type="hidden">
			<input id="topicText"  value="${result.topicText}" type="hidden">
			<input id="topicTypeName"  value="${result.topicTypeName}" type="hidden">
			<input id="topicCName"  value="${result.topicCName}" type="hidden">
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-3 col-sm-2">试卷名称：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="paperName"  datatype="*1-100" nullmsg="请填写试卷名称" value="${result.paperName}">			
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-3 col-sm-2">考试时间：</label>
				<div class="formControls col-xs-3 col-sm-2">
					<input type="text" class="input-text" name="examMinute"  datatype="n" nullmsg="请填写考试时间" style="width:80%;margin-right:2px" value="${result.examMinute}">分钟
				</div>
			</div>


		 	
 
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>已选题目：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<div style="border:1px solid #ddd;min-height:120px;height:auto;">
					<ul class="choosenlist"></ul>
        			</div>
					</div>
				<div class="col-sm-2"></div>
			</div> 
	
	
			<div class="row cl" >
				<div class="col-xs-2 col-xs-offset-4 col-sm-offset-5" style="margin-top: 20px;">					
					<m:b_button operCode="addExamPaper"  type="submit"  value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />					
				</div>
			</div>
		
		</form>
	</div>
	
	
		
		
		 		<div class="page-container">
 				<div class="t_searchbar">	
		 			<span class="select-box" style="width:160px;">
						<select class="select" name="categoryId" size="1" id="categoryId">	
							<option value="">请选择题目类别</option>				
							<c:forEach items="${categoryList}" var="c">
							
								<option value="${c.id}">${c.cateName}</option>
							</c:forEach>
						</select>
					</span> 
					<span class="select-box" style="width:160px;">
						<select class="select" size="1" id="topicType">	
								<option value="">请选择题目类型</option>								
								<option value="1">单选</option>
								<option value="2">多选</option>
								<option value="3">判断</option>
								<option value="4">填空</option>				
						</select>
				    </span> 
				  	题目文本：<input type="text"  id="questionText" class="input-text" style="width:250px" placeholder="题目文本" >
			   <button type="button" class="btn btn-danger" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
			</div>
			
					
	
	
		
			<table id="topic_table" class="table table-border table-bordered table-hover table-bg table-sort">
			</table>
		</div> 
	</div>
	
	

		<%@include file="/page/common/script/operbuttom.jsp"%>
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
		<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/study/examPaper/editExamPaperC.js"></script>
		
	
</body>
</html>
