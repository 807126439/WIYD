<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>详细列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  <body>
	<div class="pd-20">
	  <form action="<%=path %>/OpinionFeedbackConteroller/downloadFeedback.do?id=${dto.id}" method="post" class="form form-horizontal" id="form-user-edit">	  

			
	  		<table class="table table-border table-bordered " style="width: 595px;" align=center valign=middle>
	  			<tr >
	  				<td class="text-c" colspan="4"><h3>市城建局工会反馈建议征集表</h3></td>
	  			</tr>
	  			<tr class="removes">
	  				<td class="text-c"  style="width: 100px;">姓  名:</td>
	  				<td >${dto.chineseName}</td>
	  				<td class="text-c"  style="width: 100px;">科  室:</td>
	  				<td >${dto.department}</td>
	  			</tr>
	  			<tr >
	  				<td class="removes text-c" class="text-c" style="width: 100px;">岗  位:</td>
	  				<td class="removes" >${dto.post}</td>
	  				<td class="text-c" style="width: 100px;">申报时间:</td>
	  				<td  >${dto.feedbackTime}</td>
	  			</tr>
	  			<tr >
	  				<td class="text-c" style="width: 100px;" >申报主题:</td>
	  				<td style="height: 150px;" colspan="3">${dto.theme}</td>
	  			</tr>
	  			<tr>
	  				<td class="text-c"  style="width: 100px;"><h5>反馈意见:</h5></td>
	  				<td style="height: 150px;" colspan="3">${dto.content}</td>
	  				
	  			</tr>
	  			<tr class="removes">
	  				<td class="text-c" style="width: 100px;"><h5>效益预测:</h5></td>
	  				<td style="height: 150px;" colspan="3">${dto.forecast}</td>
	  			</tr>
	  				 
	  			<tr class="removes">
	  				<td class="text-c"  style="width: 100px;"><h5>采纳处理结果:</h5></td>
	  				<td style="height: 150px;" colspan="3"></td>
	  			</tr>
	  			<tr class="removes">
	  				<td class="text-c" colspan="4"><span id="downloadid"><m:b_button  operCode="download" type="submit" value="下载"/></span></td>
	  			</tr>
	  				
	  		</table>

	 
	  </form>
	   <input id="typeid" value="${dto.typeId}" type="hidden" />
	</div>
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript">
	$(function(){
		var typeid=$("#typeid").val();
		if (typeid==2) {
			$(".removes").remove();
		}
	});
	
	</script>
</body>
  
  
</html>
