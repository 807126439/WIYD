<%@ page language="java" pageEncoding="utf-8"%>

 		<div class="row cl">
 
	 		<label class="form-label col-xs-3 col-sm-3">上级部门：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" id="par-name" value="${departItem.parentName == null? '无':departItem.parentName}"  disabled="disabled">
		        <input type="hidden" name="parentId" id="dep-par">	  
		        
		        
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
 
 		<div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>部门名称：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" id="dep-name" value="${departItem.departName}"  name="departName" datatype="*2-50" nullmsg="部门名称不能为空">
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>部门机构代码：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${departItem.orgCode}"  name="orgCode" datatype="*2-50" nullmsg="部门机构代码不能为空">
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	    
	       
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">排序：</label>
	      <div class="formControls col-xs-3 col-sm-3">
	        <input type="text" class="input-text" value="${departItem.sortNum}"  name="sortNum" datatype="n" ignore="ignore">
	      </div>
	      <div class="col-xs-6 col-sm-6"> </div>
	    </div>
	    
	    
        <div class="row cl">			
			<label class="form-label col-xs-3 col-sm-3">部门类型：</label>
			<div class="formControls col-xs-1 skin-minimal"> 
				<span class="select-box" style="width:160px;">
					<select class="select" name="orgTypeId" size="1">										
							<option value="1" ${departItem.orgTypeId eq 1 ? 'selected="selected"':''}>党委</option>
							<option value="2" ${departItem.orgTypeId eq 2 ? 'selected="selected"':''}>党支部</option>
							<option value="3" ${departItem.orgTypeId eq 3 ? 'selected="selected"':''}>党小组</option>
							<option value="0" ${departItem.orgTypeId eq 0 ? 'selected="selected"':''}>其他</option>
					</select>
				</span> 
			</div>
		</div>
    	
    	
    	<div class="row cl" id="showIsMain" style="display: none;">
	      <label class="form-label col-xs-3 col-sm-3">是否为主部门：</label>
	      <div class="formControls col-xs-2 col-sm-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" value=true   name="isMain" ${departItem.isMain == true? 'checked="checked"':''} >
					<label for="checkbox-1">&nbsp;</label>
				</div>
		  </div>
	      <div class="col-xs-7 col-sm-7"> </div>
   	  	</div>

	 
	    
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-3">描述：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea name="description" cols="" rows="" class="textarea" datatype="*2-200"  ignore="ignore"  onKeyUp="textarealength(this,200)">${departItem.description}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-xs-3 col-sm-3"> </div>
		</div>
