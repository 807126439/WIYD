<%@ page language="java" pageEncoding="utf-8"%>

 		<div class="row cl">
	      <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>学历：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${EduDegree.eduName}"  name="eduName" datatype="*2-50" nullmsg="学历等级不能为空">
	      </div>
	      <div class="col-xs-4"> </div>
	    </div>
	     <div class="row cl">
	      <label class="form-label col-xs-2 col-sm-2"><span class="c-red">*</span>序号：</label>
	      <div class="formControls col-xs-6 col-sm-6">
	        <input type="text" class="input-text" value="${EduDegree.sort}"  name="sort" datatype="n1-8" nullmsg="序号不能为空">
	      </div>
	      <div class="col-xs-3"> </div>
	    </div>

	    
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-2">备注：</label>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea name="eduText" cols="" rows="" class="textarea" datatype="*2-255"  ignore="ignore"  onKeyUp="textarealength(this,255)">${EduDegree.eduText}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/255</p>
			</div>
			<div class="col-xs-3 col-sm-2"> </div>
		</div>
