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
    <title>修改流程图</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/page/common/script/mytop.jsp" %>

  </head>
  
  
  <body>
  
	<div class="pd-20">
	 <form action="<%=path %>/procNode/editProcessNode.do" method="post" class="form form-horizontal" id="form-node-edit" >
	   	<input type="hidden" name="id" value="${Dto.id}"/>
	   	
	  	<div class="row cl">
	      <label class="form-label col-xs-3">责任角色：</label>
	      <div class="formControls col-xs-6"  >
	        <input type="text" class="input-text" id="selected-roles" value="${ownRoles}"   readonly="readonly" onclick="choiceRoles()"/>	
	        	<%@include file="/page/system/user/common/roleList.jsp" %>     	     	
	      </div>
	    </div>
	   			
	   <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3">责任人：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		     <div id="user">
				<c:forEach items="${userList}" var="list">
				     <div class="divclass"  style="width:183px;margin: 10px;float: left;border: 1px solid #AFD1E3;">
						<input  style="height: 24px;width: 154px;float: left;" readonly="readonly" type="text" value="${list.userName }" />
						<input class="taskStuids" type="hidden"  value="${list.id}" name='userId' id="sdid_${list.id }">
						<span  onclick="checkSpan(this)"  style="float: right;"><img src="<%=path%>/plug-in/web/portals/image/cancel.png" width="24px" height="26px"/></span>
					</div>
				</c:forEach>
		     </div>
		        <input style="margin-top: 5px;" class="btn btn-primary radius r" type="button" value="选择人员" onclick="choiceUser()"/>
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	  </div>
	   
 		<div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>节点标识符：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text"  value="${Dto.nodeCode}" id="nodeCode" name="nodeCode" datatype="*2-80" nullmsg="节点标识符不能为空"/>
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>节点名称：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.nodeName}"  id="nodeName2"  name="nodeName2" datatype="*2-80" nullmsg="节点名称不能为空"/>
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>

	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>节点类型：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		  
		      <input type="text" class="input-text"  value="${Dto.nodeType}" id="nodeType2" name="nodeType2"  datatype="*2-30" nullmsg="节点类型不能为空" />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>

	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>x坐标：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.leftPos}" id="leftPos"   name="leftPos" datatype="n" nullmsg="x坐标不能为空" />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	     <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>y坐标：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.topPos}" id="topPos"  name="topPos" datatype="n" nullmsg="y坐标不能为空" />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>宽度：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.width}" id="width"   name="width" datatype="n" nullmsg="宽度不能为空" />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	   <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>高度：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.height}" id="height"   name="height" datatype="n" nullmsg="高度不能为空" />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>连线类型：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.lineType}" id="lineType"  name="lineType" datatype="*2-30"  ignore="ignore"  />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3">连线名称：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.lineName}" id="lineName"  name="lineName" />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	     <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>连线标示符：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" value="${Dto.lineCode}" id="lineCode"  name="lineCode" datatype="*2-50"  ignore="ignore"  />
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>

	    <div class="row cl">
		      <label class="form-label col-xs-3 col-sm-3">描述：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		      	<textarea name="description" class="textarea" id="description"  placeholder="说点什么..." onKeyUp="textarealength(this,1000)" ignore="ignore" >${Dto.description}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/1000</p>
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    

 
	    <div class="row cl">
	      <div class="col-xs-9 col-xs-offset-3">
	           <m:b_button operCode="editProcessNode" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
	      </div>
	     
	    </div> 
	    
	    
	  </form>
	        
	  
	</div>
	<%@include file="/page/system/user/common/userList.jsp" %>
	<%@include file="/page/common/script/allbuttom.jsp" %>

	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/system/user/role-for-user.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/column/common/editUser-list.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/work-flow/node/node-edit.js"></script>

</body>
  
  
  
</html>
