<%@ page language="java" pageEncoding="utf-8"%>

	<div class="row cl">
	  <label class="form-label col-xs-3"><span class="c-red">*</span>thread：</label>
	  <div class="formControls col-xs-6">    
	     <input type="text" class="input-text"  id="" name="thread" value="${model.thread}" datatype="*1-100" nullmsg="thread不能为空">
	  </div>
	  <div class="col-xs-3"></div>
	</div>
	
	<div class="row cl">
	  <label class="form-label col-xs-3"><span class="c-red">*</span>className：</label>
	  <div class="formControls col-xs-6">    
	     <input type="text" class="input-text"  id="" name="className" value="${model.className}" datatype="*1-200" nullmsg="className不能为空">
	  </div>
	  <div class="col-xs-3"></div>
	</div>
	
	<div class="row cl">
	  <label class="form-label col-xs-3"><span class="c-red">*</span>method：</label>
	  <div class="formControls col-xs-6">    
	     <input type="text" class="input-text"  id="" name="method" value="${model.method}" datatype="*1-100" nullmsg="method不能为空">
	  </div>
	  <div class="col-xs-3"></div>
	</div>
	
	<div class="row cl">
	  <label class="form-label col-xs-3"><span class="c-red">*</span>createTime：</label>
	  <div class="formControls col-xs-6">    
	    <input type="text" id="" class="input-text radius Wdate"  name="createTime"  value="<fmt:formatDate value="${model.createTime}" pattern="yyyy-MM-dd"/>" 
	     onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  datatype="*"  nullmsg="createTime不能为空"  readonly="readonly" >
	  </div>
	  <div class="col-xs-3"></div>
	</div>
	
	<div class="row cl">
	  <label class="form-label col-xs-3"><span class="c-red">*</span>loglevel：</label>
	  <div class="formControls col-xs-6">    
	     <input type="text" class="input-text"  id="" name="loglevel" value="${model.loglevel}" datatype="*1-10" nullmsg="loglevel不能为空">
	  </div>
	  <div class="col-xs-3"></div>
	</div>
	
	<div class="row cl">
	  <label class="form-label col-xs-3"><span class="c-red">*</span>msg：</label>
	  <div class="formControls col-xs-6">    
	    <textarea name="msg"  cols="" rows="" class="textarea"   datatype="*1-5,000" ignore="ignore" onKeyUp="textarealength(this,5,000)">${model.msg}</textarea>
		<p class="textarea-numberbar"><em class="textarea-length">0</em>/5,000</p>
	  </div>
	  <div class="col-xs-3"></div>
	</div>
	
