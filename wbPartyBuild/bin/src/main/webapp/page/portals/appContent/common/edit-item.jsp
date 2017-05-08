 <%@ page language="java"  pageEncoding="utf-8"%>
 
<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2 col-md-2">栏目分类：</label>
			<div class="formControls col-xs-8 col-sm-8 col-md-8">
				${colName}
			</div>
			<input id="ct-columnId" type="hidden" name="columnId" value="${columId}">
			<div class="col-xs-2 col-sm-2"></div>
			
		</div>	      
	   
	   <c:if test="${typeId eq 3 || typeId eq 6}">
	   	  <div class="row cl">
					
			<label class="form-label col-xs-2 col-sm-2">上传图片：</label>
			<div class="formControls col-xs-2 col-sm-8">
				<div class="uploader-thum-container">
					<div id="fileList" class="uploader-list">
					<div  class="item">
					   <c:if test="${!empty contentItem.pattern}">
					   	 <div class="pic-box"><img src="${contentItem.pattern}" width="64" height="64"></div>					
						
					   </c:if>
						
					</div>
											
					
					</div>
					<div id="filePicker">选择图片</div>
					<button id="btn-cancel" class="btn btn-default btn-uploadstar radius ml-10" type="button">撤销图片</button>
				</div>
			</div>	
			<div class="col-xs-2 col-sm-2"></div>
			
		 </div>
	   	   
	   </c:if>
	   
	   
	   <c:if test="${typeId eq 5}">
	   	  <div class="row cl">
					
			<label class="form-label col-xs-2 col-sm-2">视频：</label>
			<div class="formControls col-xs-8 col-sm-8">
				
				<c:choose>
					<c:when test="${!empty contentItem.viewPath}">
						<div id="paly" class="pd-20"></div>  	
					</c:when>
				    <c:otherwise>				    	
						    		
						<div class="uploader-thum-container">
							<div id="fileList" class="uploader-list">														
							
							</div>
							<div id="filePicker">选择视频</div>
							<button id="btn-cancel" class="btn btn-default btn-uploadstar radius ml-10" type="button">撤销</button>
						</div>
				    	
				    </c:otherwise>
				</c:choose>
			
				
			</div>	
			
			<div class="col-xs-2 col-sm-2"></div>
		 </div>
	   	   
	   </c:if>
	   
	 <!--  
	   <div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">标题前缀：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${contentItem.titlePrefix}" id="ct-title-prefix"  name="titlePrefix"  datatype="*2-20"  ignore="ignore">
			</div>
			
			<div class="col-xs-2 col-sm-2"></div>
		</div>
	   -->
	   
	   <div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${contentItem.title}" id="ct-title"  name="title"  datatype="*2-80"  nullmsg="标题不能为空">
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
		
	
	
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>序号：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${sortNum}" datatype="n"  nullmsg="序号不能为空" id="ct-sortNum" name="sortNum">
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
	
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">作者：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${contentItem.author}" placeholder="" id="ct-author" name="author">
			</div>
			
			<label class="form-label col-xs-2 col-sm-2">来源：</label>
			<div class="formControls col-xs-3 col-sm-3">
				<input type="text" class="input-text" value="${contentItem.source}" placeholder="" id="ct-source" name="source">
			</div>
			
			<div class="col-xs-2 col-sm-2"></div>
			
		</div>
		
		<div class="row cl">
		    	<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>发布日期：</label>
			    <div class="formControls col-xs-3 col-sm-3">
					<input type="text" name="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
						id="add-date" class="input-text Wdate" value="${contentItem.createTime}" datatype="*8-20"  nullmsg="发布日期不能为空"  readonly="readonly">
				</div>
			   <div class="col-xs-7 col-sm-7"></div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">置于首页：</label>
			<div class="formControls col-xs-8 col-sm-8 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="indexFlag" name="indexFlag" value="1" ${contentItem.indexFlag eq 1? 'checked="checked"':'' } >
					<label for="checkbox-pinglun">&nbsp;</label>
				</div>
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">设置部门查看权限：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${seeOrgName }" onclick="choiceOrg()" id="seeOrgName"  name="seeOrgName" >
				<input type="hidden"  value="${contentItem.seeOrgId }" id="seeOrgId"  name="seeOrgId" >
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>

		
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">文章内容：</label>
			<div class="formControls col-xs-8 col-sm-8" style="width: 1016px;"> 
				<script id="editor" type="text/plain" style="width:100%;height:400px;">${contentItem.content}</script> 
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
		
		
				
		<input type="hidden" id="action-way" name="action" value="simple">