<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加编辑权限</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/page/common/script/mytop.jsp" %>
	<style type="text/css">
		span img:HOVER{
		cursor: pointer}
		
		.choosenlist{min-height:100px;height:auto;}
		.choosenlist li{
			background-color: #5a98de;
		    border: 1px solid #4b90de;
		    border-radius: 4px;
		    box-shadow: 1px 1px 1px #2b6ebb;
		    color: #fff;
		    cursor: pointer;
		    display: inline-block;
		    float: left;
		    font-size: 16px;
		    line-height: 26px;
		    margin: 5px;
		    position: relative;
		    text-align: center;
		    width: 88px;
		}
		.choosenlist li i{display:none}
		.choosenlist li:hover i{display:inline-block;vertical-align: top;}
		.Hui-iconfont-close2:before { content: "\e706"; }
	</style>

  </head>
  
  <body>
	    <div class="pd-20" style="text-align: center;">
		  <form action="<%=path %>/columnMuController/addEditer.do" method="post" class="form form-horizontal  clearfix" id="form-Editer" >
			<input type="hidden" value="${id }" name="id"/>
			
		    
		    <div class="row cl" >
				<label class="form-label col-xs-4 col-sm-2">已选可操作人员：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<div style="border:1px solid #ddd;min-height:120px;height:auto;">
					<ul class="choosenlist">
						<c:forEach items="${userList}" step="1" var="item" varStatus="it">
							<li id='${item.id }' dataid='${item.id }'>${item.userName }<input type="hidden"  value="${item.id}" name='userId' ><i class='Hui-iconfont Hui-iconfont-close2'></i></li>
						</c:forEach>
					</ul>
        			</div>
					</div>
				<div class="col-sm-2"></div>
			</div>
			
		    <div class="row cl" >
				<div class="col-xs-2 col-xs-offset-4 col-sm-offset-5" style="margin-top: 20px;">					
					<m:b_button operCode="addColumnEditer"  type="submit"  value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />					
				</div>
			</div>
			

		  </form>	  
		</div>
		
		  <div class="text-c">
					<input type="text" class="input-text" style="width:200px" placeholder="用户名" id="user_name" >&nbsp;		
					<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		  </div>
		  
			<div class="mt-20" style="width:90%;margin:0 auto">
				<table id="user_table" class="table table-border table-bordered table-hover table-bg table-sort">
					<thead></thead>
					<tbody></tbody>
				</table>
			</div>
		
	<%@include file="/page/common/script/allbuttom.jsp" %>
	<script type="text/javascript" src="<%=path%>/plug-in/web/scripts/portals/column/editer-add.js"></script>
  </body>
</html>