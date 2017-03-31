<%@ page language="java" pageEncoding="utf-8"%>
 		
 		<div class="row cl">
 
	 		<label class="form-label col-xs-3 col-sm-3">部门：</label>
		      <div class="formControls col-xs-6 col-sm-6">
		        <input type="text" class="input-text" id="dep-name" value="${relationItem.departName}"  disabled="disabled">
		        <input type="hidden" name="id" id="id" value="${relationItem.id}">
		        <input type="hidden" name="departId" id="dep-id" value="${relationItem.departId}">	  		        
		        
		      </div>
		      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
 
 
 		<div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>职务：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" id="job-name" value="${relationItem.jobName}" datatype="*"  nullmsg="职务不能为空" readonly="readonly" onclick="choiceJob()">
	    	<input type="hidden" id="job-id" value="${relationItem.jobId}"  name="jobId" >
	    	
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>人员：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" id="user-name" value="${relationItem.userName}"  readonly="readonly" onclick="choiceUser()">
	        <input type="hidden" id="user-id" value="${relationItem.userId}"  name="userId" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>部门内排序：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" id="sortnum" name="sortNum" value="${relationItem.sortNum}"  datatype="n" >
	      </div>
	      <div class="col-xs-3 col-sm-3"> </div>
	    </div>
	    

	 
	    
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-3">备注：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea name="memo" cols="" rows="" class="textarea" datatype="*2-100"  ignore="ignore"  onKeyUp="textarealength(this,100)">${relationItem.memo}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-xs-3 col-sm-3"> </div>
		</div>
