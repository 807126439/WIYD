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
	<link rel="stylesheet" href="<%=path%>/plug-in/web/portals/css/opinionFeedback/addOpinionfeedback.css" />
	<%@include file="/page/common/script/mytop.jsp" %>
	<style type="text/css">

        input{outline:none;}
		input:focus { outline: none; } 
	</style>

  </head>
  
  <body>

	<div class="pd-20">
	  <form action="<%=path %>/OpinionFeedbackConteroller/addOpinion.do" method="post" class="form form-horizontal" name="form-Opinionfeedback">	  
		
		<div class="Hui-article">
	  		<div><h3 class="title">市城建局工会反馈建议征集表</h3></div>
	  		<div class="id"><strong>&nbsp;编号</strong><span></span></div>
	  		<table class="text-c" border="1">
	  			<tr>
	  				<td colspan="2" class="a"><strong>姓  名</strong></td>
	  				<td colspan="2" class="a1"><input  type="text" style="font-size: 16px;outline:none;border-style:none" class="input-text text"  value="${chinesename}" readonly="readonly"/></td>
	  				<td colspan="2" class="a"><strong>科  室</strong></td>
	  				<td colspan="2" class="a1"><input   type="text" style="font-size: 16px;outline:none;border-style:none" class="input-text text" name="department" id="department" /></td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" class="a"><strong>岗  位</strong></td>
	  				<td colspan="2" class="a1"><input type="text" style="font-size: 16px;outline:none;border-style:none" class="input-text text" name="post" id="post" /></td>
	  				<td colspan="2" class="a"><strong>申报时间</strong></td>
	  				<td colspan="2" class="a1"><input type="text" style="font-size: 16px;outline:none;border-style:none" id="date" class="input-text text" name="feedbackTime" readonly="readonly"/></td>
	  			</tr>
	  			<tr>
	  				<td class="a"><strong>申报主题</strong></td>
	  				<td colspan="7"><input style="font-size: 16px;border-style:none" type="text" class="input-text text" name="theme" id="theme" /></td>
	  			</tr>
	  			<tr>
	  				<td class="title1"><h5><strong>反馈意见</strong></h5></td>
	  				<td  colspan="7" class="jianyi"><textarea style="font-size: 16px;border-style:none;resize:none;" placeholder="说点什么..." onchange="if(value.length>500)value=value.substr(0,500)" class="jianyi" name="content" class="input-text" id="content" cols="30" rows="10"></textarea></td>
	  				
	  			</tr>
	  			<tr>
	  				<td class="title1"><h5><strong>效益预测</strong></h5></td>
	  				<td  colspan="7" class="jianyi"><textarea style="border-style:none;font-size: 16px;resize:none;" placeholder="期望处理结果..." onchange="if(value.length>200)value=value.substr(0,200)" class="jianyi"  name="forecast" class="input-text" id="" cols="30" rows="10"></textarea></td>
	  			</tr>
	  				 
	  			<tr>
	  				<td class="title1"><h5><strong>采纳处理结果</strong></h5></td>
	  				<td  colspan="7" class="jianyi"><span>(由待处理人填写)</span></td>
	  			</tr>
	  			<tr style="text-align: center;">
	  				<td  colspan="8">
	  				<input class="btn btn-primary radius "  style="margin-top: 10px;border-style:none;margin-bottom: 10px;background-color: #C82619;" type="button" value="提&nbsp;&nbsp;&nbsp;&nbsp;交" onclick="checkVal()">
	  			</tr>
	  				
	  		</table>
	  		
		</div>
		
	     <input type="hidden" value="1" name="typeId" />
	  </form>
	  <style type="text/css"></style>
	 
  

	  
	</div> 
	<%@include file="/page/common/script/operbuttom.jsp" %>
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/opinionFeedback/detailOpinionFeedback.js"></script> 
	<script type="text/javascript">
			var date = new Date();
			var str=date.getFullYear()+"-"+((date.getMonth()+1)<10?"0":"")+(date.getMonth()+1)+"-"+(date.getDate()<10?"0":"")+date.getDate();
			$("#date").val(str);

			function checkVal(){
			  var department = $("#department").val();
			  if(department == "undefined" || department == null || department == ""){
			  	alert("请输入科室！");
			  	return false;
			  }
			   var post = $("#post").val();
			  if(post == "undefined" || post == null || post == ""){
			  	alert("请输入岗位！");
			  	return false;
			  }
			  var theme =  $("#theme").val();
			  if(theme == "undefined" || theme == null || theme == ""){
			  	alert("请输入申报主题！");
			  	return false;
			  }
			 var content =  $("#content").val();
			  if(content == "undefined" || content == null || content == ""){
			  	alert("请输入反馈内容！");
			  	return false;
			  }
			  $("form[name=form-Opinionfeedback]").submit();
			}
			
			$("form[name=form-Opinionfeedback]").Validform({
				tiptype:2,
				ajaxPost:true,//ajax方式提交表单数据
				beforeSubmit:function(curform){
				  
				     return true;
				   	
				},
				callback:function(data){				
							if(data.status == "y" || data.status == "Y"){			
								      setTimeout(function(){
								    
										var index = parent.layer.getFrameIndex(window.name);
										parent.layer.close(index);
										      					      
								      },800); 
								
							}
						}
			});
	</script>
</body>
  
  
</html>
