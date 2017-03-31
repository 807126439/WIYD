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

<title>修改奖项设定</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<%@include file="/page/common/script/mytop.jsp"%>
<link href="<%=path%>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
	
</head>


<body>
	<div class="pd-20">
		<form action="<%=path%>/awardsSettingController/editAwardsSetting.do"
			method="post" class="form form-horizontal"
			id="form-awardsSetting-edit">
			<input type="hidden" id="asId" name="asId" value="${result.asId}"> 
			<input type="hidden" id="activityId" name="activityId" value="${activityId}">


			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2"><span
					class="c-red">*</span>奖项名称：</label>
				<div class="formControls col-xs-7 col-sm-4">
					<input type="text" class="input-text" id="awardsName" name="awardsName"
						 nullmsg="奖项名称不能为空" value="${result.awardsName}">
				</div>
				<div class="col-sm-2"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2"><span
					class="c-red">*</span>奖金/品：</label>
				<div class="formControls col-xs-7 col-sm-4">
					<input type="text" class="input-text" id="prize" name="prize" 
						nullmsg="奖金/奖品不能为空" value="${result.prize}">
				</div>
				<div class="col-sm-2"></div>
			</div>


			<!-- 上传图片组件 -->
			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">上传图片：</label>
				<div class="formControls col-xs-7 col-sm-4">
					<div class="uploader-thum-container">
						<div id="fileList" class="uploader-list">
							<div class="item" >
								<c:if test="${!empty result.pattern}">
									<div class="pic-box">
										<img src="${result.pattern}" width="64" height="64">
									</div>
								</c:if>
							</div>
						</div>
						<div id="filePicker">选择图片</div>
						<button id="btn-cancel"
							class="btn btn-default btn-uploadstar radius ml-10" type="button">撤销图片</button>
					</div>
				</div>
				<div class="col-xs-2 col-sm-2"></div>
			</div>

			
			
			<!-- 上传图片组件 -->



			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2"><span
					class="c-red">*</span>数量：</label>
				<div class="formControls col-xs-7 col-sm-4">
					<input type="text" class="input-text" id="amount" name="amount" datatype="n"
						nullmsg="数量不能为空" value="${result.amount}">
				</div>
				<div class="col-sm-2"></div>
			</div>

			<div class="row cl">
	      		<label class="form-label col-xs-2 col-sm-2">排序：</label>
	      		<div class="formControls col-xs-7 col-sm-4">
	      	 		<input type="text" class="input-text" value="${result.sortNum}" id="sortNum" name="sortNum" datatype="n" nullmsg="请填入排序号">
	      		</div>
	      		<div class="col-xs-3 col-sm-3"> </div>
	    	</div>

			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">备注：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<textarea id="memo" name="memo" class="textarea"
						placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,200)"
						style="height:160px">${result.memo}</textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/200
					</p>
				</div>
			</div>






			<div class="row cl">
				<div class="col-xs-2 col-xs-offset-3 col-sm-offset-5">
					<m:b_button operCode="editAwardsSetting" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
				</div>
			</div>

		</form>


	</div>



	<%@include file="/page/common/script/operbuttom.jsp"%>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/portals/awardsSetting/awardsSetting-edit.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>


</body>



</html>
