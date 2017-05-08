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
    
    <title>内容列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">
	<link href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
	

  </head>
  
  
  <body>


		<div class="page-container">
				<div class="t_searchbar">																								
					标题：<input type="text" class="input-text" style="width:250px" placeholder="标题" id="ct-title" >&nbsp;
					来源：<input type="text" class="input-text" style="width:250px" placeholder="来源" id="ct-source" name="">&nbsp;
					作者：<input type="text" class="input-text" style="width:250px" placeholder="作者" id="ct-auhtor" name="">
					<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
				
				<div class="t_ctrlbar"> 
				 <span class="btns">
				   	<c:choose>
				   		<c:when test="${typeId ==3}">
				   			  <m:a_top context="上传图片"  operCode="addContent"  funMethod="upload_img('上传图片')"  className="btn btn-primary radius"/>				 		 			     				   		
		   		
				   		</c:when>
				   		<c:otherwise>
				   			  <m:a_top context="发布文章"  operCode="skipAddContent"  funMethod="content_add('发布文章')"  className="btn btn-primary radius"/>				 		 			     
				   		
				   		</c:otherwise>
				   	</c:choose>
				   	
				  	<m:a_top context="移动"  operCode="changeColumn"  funMethod="dataMove()" className="btn btn-warning radius" />
				 	<m:a_top context="删除"  operCode="delContent"  funMethod="datadel()" className="btn btn-danger radius" />
				     
				 </span> 
				 
			
				</div>
				
				
				
		
				<table id="content_table" class="table table-border table-bordered table-hover table-bg table-sort">					
				</table>
			
			
			
	  </div>
			
		
		 <div style="display: none;width: 660px;margin: 0 auto;" id="upload-img-area" >	 	 
				
					<div class="uploader-list-container"> 
						<div class="queueList">
							<div id="dndArea" class="placeholder" style="min-height: 160px;">
								<div id="filePicker-2"></div>
								<p>或将照片拖到这里，单次最多可选300张</p>
							</div>
						</div>
						<div class="statusBar" style="display:none;">
							<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
				
		</div>
		
		
		
		
		<div id="hide-tree" style="display: none;">
			<div class="row cl">
		      <div class="r pr-10 pt-10">
		        <m:b_button operCode="changeColumn" type="button" value="&nbsp;&nbsp;确定移动&nbsp;&nbsp;" funMethod="sumbitMove()" className="btn btn-primary radius size-MINI"/>
		      </div>
		    </div>
		    
			<div class="pd-10">
				<ul id="tree" class="ztree"></ul>				
				
			</div>
			
		
		</div>
		
			
		<input type="hidden" id="columId" value="${columId}">
		<input type="hidden" id="typeId" value="${typeId}">	
		<%@include file="/page/common/script/mybottom.jsp" %>
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
		
		<m:a_button operCode="queryContentDetail"  funMethod="content_edit('{0}')" context="编辑" varName="edit_content" className="t-btn btn size-MINI btn-primary-outline"/>	
		<m:a_button operCode="changeColumn"  funMethod="content_move_one('{0}')" context="移动" varName="move_content" className="t-btn btn size-MINI btn-warning-outline"/>			
		<m:a_button operCode="queryContentDetail"  funMethod="innnert_edit('{0}','{1}')" context="内页管理" varName="edit_inner" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="delContent"  funMethod="content_del(this,'{0}')" context="删除" varName="del_content" className="t-btn btn size-MINI btn-danger-outline"/>
		<m:a_button operCode="connectActivity"  funMethod="content_connect('{0}','{1}')" context="关联活动" varName="connect_content" className="t-btn btn size-MINI btn-primary-outline"/>
			

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/content-list.js"></script> 
	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/content-move.js"></script> 
	    <script type="text/javascript">
	    	 var isInitUpload = false; 
	         function upload_img(title){
	          	if(!isInitUpload){
		    		initUploadComnpent(); 
		    		isInitUpload = true;
		    	}
								
		   		layer.open({
				  type: 1,
				  title: title,
				  area: ['680px','500px'],
				  shade: 0.2,
				  content: $('#upload-img-area'),
				  end: function(index){ 
			 			goSearch();
						return true; 
					}
				});
	         
	         }
	       
       
	    
	    </script>
	   
	    <c:if test="${typeId eq 3}">
			<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>
		 	<script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/content/multi-img-upload-list.js"></script>
	
	    </c:if>	
	    
	    
	   
		
</body>
  
  
  
</html>
