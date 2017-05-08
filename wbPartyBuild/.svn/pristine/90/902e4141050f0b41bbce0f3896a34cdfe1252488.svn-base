 <%@ page language="java"  pageEncoding="utf-8"%>
 
 		    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>排序号：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${sortNum}" name="sortNum" datatype="n"  nullmsg="排序号不能为空"  />
	      </div>
		 <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>标题：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${colMuItem.title}" name="title"  datatype="*2-80"  nullmsg="标题不能为空">
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">别名：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${colMuItem.alias}" name="alias"  datatype="*2-100"  ignore="ignore" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	   
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>栏目类型：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	       	<select id="select-typeId" class="select" name="typeId"  datatype="n"  nullmsg="请选择栏目类型">
					<option value="">-请选择-</option>
					<c:forEach items="${typeDicts}" var="d">
						<option value="${d.dictValue}" title="${d.dictItem}" ${d.dictValue eq colMuItem.typeId? 'selected="selected"':'' }>${d.dictItem}</option>
					</c:forEach>
				</select>
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	
	    <div id="input-linkUrl" class="row cl"  >
	      <label class="form-label col-xs-3 col-sm-3">链接地址：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${colMuItem.linkUrl}" name="linkUrl"  datatype="url"   ignore="ignore" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	   	   
	    
	    
	    <div class="row cl">
			<label class="form-label col-xs-3 col-sm-3">置于首页：</label>
			
			<div class="formControls col-xs-1 col-sm-1 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-is-index" value="true"  name="isIndex"  ${colMuItem.isIndex eq true? 'checked="checked"':''} >
					<label for="checkbox-is_index">&nbsp;</label>
				</div>
			
			</div>

			
			<label class="form-label col-xs-3 col-sm-3">首页排序号：</label>
			<div class="formControls col-xs-2 col-sm-2">
				<input type="text" id="index-num" class="input-text" value="${colMuItem.indexNum}"  name="indexNum" datatype="n"  ignore="ignore"  />
			</div>			
			
					
			<div class="col-xs-3 col-sm-3"> </div>
		</div>    
	    
	    
	    <div class="row cl">
			<label class="form-label col-xs-3 col-sm-3">首页导航项：</label>
			
			<div class="formControls col-xs-1 col-sm-1 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-is-index" value="true"  name="isIndexNav"  ${colMuItem.isIndexNav eq true? 'checked="checked"':''} >
					<label for="checkbox-is_index">&nbsp;</label>
				</div>
			
			</div>
		
					
			<label class="form-label col-xs-3 col-sm-3">忽略上级属性：</label>
			
			<div class="formControls col-xs-1 col-sm-1 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-is-ignore-pre" value="true"  name="isIgnorePre"  ${colMuItem.isIgnorePre eq true? 'checked="checked"':''} >
					<label for="checkbox-is_index">&nbsp;</label>
				</div>
			
			</div>
		
					
			<div class="col-xs-4 col-sm-4"> </div>
		</div>   
	    
	    <c:if test="${!empty showTypeDicts}">
		   <div class="row cl">
		    	 <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>显示类型：</label>
		         <div class="formControls col-xs-6 col-sm-6">
			       	 <select id="select-show-typeId" class="select" name="showType"  datatype="n"  nullmsg="请选择显示类型">
							<c:forEach items="${showTypeDicts}" var="d">
								<option value="${d.dictValue}" title="${d.dictItem}" ${d.dictValue eq colMuItem.showType? 'selected="selected"':'' }>${d.dictItem}</option>
							</c:forEach>
					 </select>
		        </div>
		        
		        <div class="col-xs-3 col-sm-3"> </div>
		     </div>
	   </c:if>
	    
	    
	    
	     <div class="row cl">
	    	 <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>首页文章数量：</label>
	         <div class="formControls col-xs-6 col-sm-6">
		       	 <select id="select-typeId" class="select" name="maxContentSize"  datatype="n"  nullmsg="请选择文章数量">
						<c:forEach items="${sizeDicts}" var="d">
							<option value="${d.dictValue}" title="${d.dictItem}" ${d.dictValue eq colMuItem.maxContentSize? 'selected="selected"':'' }>${d.dictItem}</option>
						</c:forEach>
				 </select>
	        </div>
	        
	        <div class="col-xs-3 col-sm-3"> </div>
	     </div>
	   
	   
	 
		   

	     <div class="row cl">
			<label class="form-label col-xs-3 col-sm-3">刷新父窗口：</label>
			
			<div class="formControls col-xs-2 col-sm-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-refresh-parent" value="true"   >
					<label for="checkbox-refresh-parent">&nbsp;</label>
				</div>
			
			</div>
						
					
			<div class="col-xs-7 col-sm-7"></div>
		</div> 