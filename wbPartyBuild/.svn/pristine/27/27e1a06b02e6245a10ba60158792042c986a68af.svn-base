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
    
    <title>文章列表</title>
    
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

		<%@include file="/page/common/nav/breadcrumb.jsp" %>
		<div class="pd-20">
				<div class="text-c">																								
					<input type="text" class="input-text" style="width:250px" placeholder="标题" id="ct-title" >&nbsp;
					<input type="text" class="input-text" style="width:250px" placeholder="来源" id="ct-source" name="">&nbsp;
					<input type="text" class="input-text" style="width:250px" placeholder="作者" id="ct-auhtor" name="">
					<button type="button" class="btn btn-danger radius" id="" name="" onclick="goSearch()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
				</div>
					<div class="cl pd-5 bg-1 bk-gray mt-20" > 
					 <span class="l">
					   	<m:a_top context="批量手机端置顶"  operCode="contentToTop"  funMethod="content_toTop()"  className="btn btn-primary radius"/>
					   	<m:a_top context="取消置顶"  operCode="cancleToTop"  funMethod="cancle_toTop()"  className="btn btn-warning radius"/>				 		 			     
					 	<m:a_top context="批量删除"  operCode="changeColumn"  funMethod="datadel()" className="btn btn-danger radius" />
					 	
					 </span>
					 	<m:a_top context="保存"  operCode="saveAppContentNum"  funMethod="saveAppContentNum()"  className="btn btn-primary radius f-r"/>
					 		<input type="text" name="contentNum" id="contentNum" value="${appContentSize }" class="f-r input-text ml-10 mr-10" style="width:250px" datatype="n" nullmsg="数量不能为空" />
						<span class="f-r mt-5" >
					 		手机端文章显示最大数量:
					 </span>
					</div>
				
				
				
			<div class="mt-20">
				<table id="content_table" class="table table-border table-bordered table-hover table-bg table-sort">
					<thead></thead>
					<tbody></tbody>
				</table>
				</div>
			
			
	  </div>
			
		
		 <div style="display: none;width: 660px;" id="upload-img-area" >
		 	  <div class="row cl pt-10" style="margin-left: 80px;">
		         
					<label class="form-label col-1"></label>
					<div class="formControls col-10">
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
				</div>
		</div>
		
		
		
		
		<div id="hide-tree" style="display: none;">
			<div class="row cl">
		      <div class="r pr-20 pt-10">
		        <m:b_button operCode="changeColumn" type="button" value="&nbsp;&nbsp;确定移动&nbsp;&nbsp;" funMethod="sumbitMove()" />
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
		
		<m:a_button operCode="queryContentDetail"  funMethod="content_edit('{0}')" context="编辑" varName="edit_content" />	
		<m:a_button operCode="delContent"  funMethod="content_del(this,'{0}')" context="删除" varName="del_content" className="t-btn btn size-MINI btn-danger-outline"/>
			

	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/appContent/content-list.js"></script> 
	    <script type="text/javascript" src="<%=path %>/plug-in/web/scripts/portals/appContent/content-move.js"></script> 
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
				  area: ['680px', ($(window).height() - 30)+'px'],
				  shade: 0.2,
				  content: $('#upload-img-area'),
				  end: function(index){ 
			 			goSearch();
						return true; 
					}
				});
	         
	         }
	       
       
	    
	    </script>
</body>
  
  
  
</html>
