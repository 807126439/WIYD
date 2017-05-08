 <%@ page language="java"  pageEncoding="utf-8"%>
 
	      	   

   	  <div class="row cl">
				
		<label class="form-label col-xs-2 col-sm-2">上传图片：</label>
		<div class="formControls col-xs-8 col-sm-8">
			<div class="uploader-thum-container">
				<div id="fileList" class="uploader-list">
				<div  class="item">
				   <c:if test="${!empty adsItem.pattern}">
				   	 <div class="pic-box"><img src="${adsItem.pattern}" width="96" height="96"></div>					
					
				   </c:if>
					
				</div>
										
				
				</div>
				<div id="filePicker">选择图片</div>
				<button id="btn-cancel" class="btn btn-default btn-uploadstar radius ml-10" type="button">撤销图片</button>
			</div>
		</div>	
		
		<div class="col-xs-2 col-sm-2"></div>
		
	 </div>

	   
	   	<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">压缩图片：</label>
			<div class="formControls col-xs-8 col-sm-8 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="isCompress" name="isCompress" value="1" >
					<label for="checkbox-pinglun">&nbsp;</label>
				</div>
			</div>
			<div class="col-xs-2 col-sm-2"></div>
		</div>
   
	   
	   <div class="row cl">
			<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${adsItem.title}" id="ct-title"  name="title"  datatype="*2-80"  nullmsg="标题不能为空">
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
		      <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>类型：</label>
		      <div class="formControls col-xs-8 col-sm-8">
		       	<select id="select-typeId" class="select" name="typeId"  datatype="n"  nullmsg="请选择类型">
						<option value="">-请选择-</option>
						<c:forEach items="${typeDict}" var="d">
							<option value="${d.dictValue}" title="${d.dictItem}" ${d.dictValue eq adsItem.typeId? 'selected="selected"':'' }>${d.dictItem}</option>
						</c:forEach>
					</select>
		      </div>
		     <div class="col-xs-2 col-sm-2"></div>
	    </div>
	    
	
	    <div id="input-linkUrl" class="row cl"  >
	      <label class="form-label col-xs-2 col-sm-2">链接地址：</label>
	      <div class="formControls col-xs-8 col-sm-8">
	        <input type="text" id="ct-linkUrl" class="input-text" value="${adsItem.linkUrl}" name="linkUrl"   datatype="url"   ignore="ignore" >
	      </div>
	      	<div class="col-xs-2 col-sm-2"></div>
	    </div>
		
		
	
		
		<div class="row cl">
		    	<label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>发布日期：</label>
			    <div class="formControls col-xs-4 col-sm-4">
					<input type="text" name="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
						id="add-date" class="input-text Wdate"  value="<fmt:formatDate value="${adsItem.createTime}" pattern="yyyy-MM-dd"/>" datatype="*8-20"  nullmsg="发布日期不能为空"  readonly="readonly">
				</div>
			   <div class="col-xs-6 col-sm-6"></div>
		</div>
		
		
	
		

		
	
		
		
				
