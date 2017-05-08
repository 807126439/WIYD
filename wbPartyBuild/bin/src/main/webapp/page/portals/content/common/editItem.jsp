<%@ page language="java" pageEncoding="utf-8"%>

 		<div class="row cl">
 
	 		<label class="form-label col-xs-3 col-sm-3">上级板块：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" id="par-name" value="${banChunkItem.parentName == null? '无':banChunkItem.parentName}"  disabled="disabled">
		        <input type="hidden" name="parentId" id="dep-par">
		        <input type="hidden" name="level" id="chunk-lev">
		        <input type="hidden" name="activityId" id="chunk-activityId">
		        <input type="hidden" name="status" id="status" value=0>
		        <input type="hidden" name="linkContentId" id="linkContentId">
		        <input type="hidden" name="isLeaf" id="isLeaf" value=true>
		        <input type="hidden" name="path" id="path">
		        
		        
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
 
 		<div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>板块名称：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" id="dep-name" value="${banChunkItem.departName}"  name="chunkName" datatype="*2-50" nullmsg="板块名称不能为空">
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>板块备注：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${banChunkItem.orgCode}"  name="chunkMemo" datatype="*2-50" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	   	<div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">排序：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${banChunkItem.orgCode}"  name="sortNum" datatype="n" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	   	<div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">图片：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	      	<img alt="" id="LeafImg" src="" style="max-width:640px;max-height:320px;">
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	     <!--  
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">排序：</label>
	      <div class="formControls col-xs-3 col-sm-3">
	        <input type="text" class="input-text" value="${banChunkItem.sortNum}"  name="sortNum" datatype="n" ignore="ignore">
	      </div>
	      <div class="col-xs-6 col-sm-6"> </div>
	    </div>
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">是否公开：</label>
	      <div class="formControls col-xs-3 col-sm-3 radio-box">
	        <input type="radio"  name="status" checked="checked"  value="1">
	        <label for="radio-1">公开</label>
	      </div>
	      <div class="formControls col-xs-3 col-sm-3 radio-box">
	        <input type="radio"  name="status"  value="0" >
	        <label for="radio-1">不公开</label>
	      </div>
	      <div class="col-xs-6 col-sm-6"> </div>
	    </div>
	    -->
	    
    

	 
	    <!-- 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-3">描述：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea name="chunkMemo" cols="" rows="" class="textarea" datatype="*2-200"  ignore="ignore"  onKeyUp="textarealength(this,200)">${banChunkItem.description}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-xs-3 col-sm-3"> </div>
		</div>
 		-->