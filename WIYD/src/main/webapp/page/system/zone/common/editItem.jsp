<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


 <div class="row cl">
	     <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>路径说明：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text"  value="${zonePathItem.text}" placeholder=""  name="text" datatype="*1-255" nullmsg="路径说明不能为空">
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>物理路径：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${zonePathItem.path}"  placeholder=""  name="path" datatype="*1-255" nullmsg="物理路径不能为空">
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>类型：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	     	<span class="select-box" >
				<select  class="select" name="type" >
					<c:forEach items="${typeDict}" var="t">
						<option value="${t.dictValue}" title="${t.dictItem}" ${zonePathItem.type eq t.dictValue? 'selected="selected"':'' }>${t.dictItem}</option>
					</c:forEach>
				</select>
			</span> 
	     
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>优先级：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${zonePathItem.priority}"  placeholder="" name="priority" datatype="n" nullmsg="优先级不能为空"  >
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	      <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">虚拟目录：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${zonePathItem.viewPath}"  placeholder="" name="viewPath" datatype="*1-255" ignore="ignore"  >
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">虚拟物理路径：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${zonePathItem.virtualPath}"  placeholder="" name="virtualPath" datatype="*1-255" ignore="ignore"  >
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    
	     <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3">警告值：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${zonePathItem.warmValue}"  placeholder="" name="warmValue" datatype="n" ignore="ignore"  >
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
   
	