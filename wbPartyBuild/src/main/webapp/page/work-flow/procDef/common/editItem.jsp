<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


 <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>流程标识符：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text"  value="${defItem.processCode}" name="processCode" datatype="*2-80" nullmsg="流程标识符不能为空">
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>流程名称：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${defItem.processName}"   name="processName" datatype="*2-80" nullmsg="流程名称不能为空">
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>描述：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	      	<textarea name="description" class="textarea" id="description"  placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,1000)" ignore="ignore" >${defItem.description}</textarea>
			<p class="textarea-numberbar"><em class="textarea-length">0</em>/1000</p>
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
   
	  <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>是否有效：</label>
	      <div class="formControls col-xs-2 col-sm-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" value="1"  name="status" ${defItem.status == 1? 'checked="checked"':''} >
					<label for="checkbox-1">&nbsp;</label>
				</div>
		  </div>
	      <div class="col-xs-7 col-sm-7"> </div>
   	  </div>