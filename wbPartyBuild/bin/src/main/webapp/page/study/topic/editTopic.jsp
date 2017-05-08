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
    
    <title>修改题目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  <body>
	<div class="pd-20" style="text-align: center;">
	 <form action="<%=path %>/topicController/editTopic.do" method="post" class="form form-horizontal" id="form-topic-edit" >       
		<input name="topicId" value="${result.topicId}" type="hidden">
		
		
	    <div class="row cl">			
			<label class="form-label col-xs-3 col-sm-2">题目类别：</label>
			<div class="formControls col-xs-1 skin-minimal"> 
				<span class="select-box" style="width:160px;">
					<select class="select" name="categoryId" size="1" id="categoryId">				
						<c:forEach items="${categoryList}" var="c">
							<option value="${c.id}"  ${c.id eq result.categoryId ? 'selected="selected"':'' }>${c.cateName}</option>
						</c:forEach>
					</select>
				</span> 
			</div>
			</div>
			
			<div class="row cl">			
			<label class="form-label col-xs-3 col-sm-2">题目类型：</label>
			<div class="formControls col-xs-1 skin-minimal"> 
				<span class="select-box" style="width:160px;">
					<select class="select" name=topicType size="1" id="categoryId">									
							<option value="1" ${1 eq result.topicType ? 'selected="selected"':'' }>单选</option>
							<option value="2" ${2 eq result.topicType ? 'selected="selected"':'' }>多选</option>
							<option value="3" ${3 eq result.topicType ? 'selected="selected"':'' }>判断</option>
							<option value="4" ${4 eq result.topicType ? 'selected="selected"':'' }>填空</option>				
					</select>
				</span> 
			</div>
			</div>
			

	
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>题目文本：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<textarea name="questionText" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,300)" style="height:100px">${result.questionText}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/300</p>
			
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">题目分值：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="score"  datatype="n" nullmsg="题目分值不能为空" value="${result.score}">
				</div>
				<div class="col-sm-2"></div>
			</div>


			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">答案：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="answer"  datatype="*1-350" nullmsg="答案不能为空" value="${result.answer}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项1：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionA" datatype="*1-100" ignore="ignore" value="${result.optionA}">
				</div>
				<div class="col-sm-2"></div>
			</div>

	

			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项2：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionB" datatype="*1-100" ignore="ignore" value="${result.optionB}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项3：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionC" datatype="*1-100" ignore="ignore" value="${result.optionC}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项4：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionD" datatype="*1-100" ignore="ignore" value="${result.optionD}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项5：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionE" datatype="*1-100" ignore="ignore" value="${result.optionE}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项6：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionF" datatype="*1-100" ignore="ignore" value="${result.optionF}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项7：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="optionG" datatype="*1-100" ignore="ignore" value="${result.optionG}">
				</div>
				<div class="col-sm-2"></div>
			</div>

			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项1解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainA" datatype="*1-300" ignore="ignore" value="${result.explainA}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项2解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainB" datatype="*1-300" ignore="ignore" value="${result.explainB}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项3解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainC" datatype="*1-300" ignore="ignore" value="${result.explainC}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项4解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainD" datatype="*1-300" ignore="ignore" value="${result.explainD}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项5解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainE" datatype="*1-300" ignore="ignore" value="${result.explainE}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项6解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainF" datatype="*1-300" ignore="ignore" value="${result.explainF}">
				</div>
				<div class="col-sm-2"></div>
			</div>
			
			<div class="row cl" style="margin-top: 20px;">
				<label class="form-label col-xs-4 col-sm-2">选项7解释：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="text" class="input-text" name="explainG" datatype="*1-300" ignore="ignore" value="${result.explainG}">
				</div>
				<div class="col-sm-2"></div>
			</div>
		
		

			<div class="row cl">
				<div class="col-xs-2 col-xs-offset-4 col-sm-offset-5" style="margin-top: 20px;">					
					<m:b_button operCode="editTopic"  type="submit"  value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />					
				</div>
			</div>
	    
	    				    
	  </form>	  
	</div>
	
	<%@include file="/page/common/script/operbuttom.jsp" %>
	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/study/topic/editTopic.js"></script>
  </body>
</html>
