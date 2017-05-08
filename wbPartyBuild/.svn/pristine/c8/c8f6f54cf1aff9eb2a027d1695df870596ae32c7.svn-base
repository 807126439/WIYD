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
    
    <title>修改获奖作品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	

  </head>
 
  
  <body>
	<div class="pd-20">
	 <form action="<%=path %>/awardWinningWorksController/editAwardWinningWorks.do" method="post" class="form form-horizontal" id="form-themeActivity-edit" >       
	    <input type="hidden" name="awwId" value="${result.awwId}">
	    <input type="hidden" name="activityId" value="${activityId}">
	
	
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">作品名称:</label>
			<div class="formControls col-xs-7 col-sm-4">
				<input type="text" class="input-text"  name="awardsName"   value="${result.awardsName}" disabled="disabled">
			</div>
			<div class="col-sm-2"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">作者:</label>
			<div class="formControls col-xs-7 col-sm-4">
				<input type="text" class="input-text"  name="manuscriptUsername"   value="${result.manuscriptUsername}" disabled="disabled">
			</div>
			<div class="col-sm-2"></div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>奖项：</label>
				<span class="select-box " style="width:50% ">
  					<select class="select" size="1" name="awardsSettingId">
    					<c:forEach var="awardSetting" items="${awardSettingList}">
  							<option value="${awardSetting.asId}">奖项:${awardSetting.awardsName} | 价值:${awardSetting.prize}(剩余名额:${awardSetting.amountleft})</option>
  						</c:forEach>
  					</select>
				</span>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">点评：</label>
			<div class="formControls col-xs-9 col-sm-9">
				<textarea name="comment" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,200)" style="height:250px">${result.comment}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
		
	    <div class="row cl">
	      <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5" >
	         <m:b_button operCode="editAwardWinningWorks" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	    </div>
	    
	  </form>
	  
	  
	</div>
		

	
	<%@include file="/page/common/script/operbuttom.jsp" %>
    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/themeActivity/themeActivity-edit.js"></script>
		
	
</body>
  
  
  
</html>
