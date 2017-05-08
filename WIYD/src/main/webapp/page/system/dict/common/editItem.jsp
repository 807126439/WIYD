<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


 <div class="row cl">
 		<c:choose>	
			 <c:when test="${model eq 'profile' }">
			 	 <input type="hidden"  name="dictType"  value="${dictType}">
			 </c:when>
			 <c:otherwise>
			 	 <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>字典类别：</label>
			      <div class="formControls col-xs-5 col-sm-5">
			        <input type="text" class="input-text"  value="${dictItem.dictType}" placeholder="" id="dict-cate" name="dictType" datatype="*1-50" nullmsg="字典类别不能为空">
			      </div>
			      <div class="col-xs-4 col-sm-4"> </div>
			    </div>		 
			 </c:otherwise>
			</c:choose>
	      
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>字典条目：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${dictItem.dictItem}"  placeholder="" id="dict-item" name="dictItem" datatype="*1-50" nullmsg="字典条目不能为空">
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
	    
	    <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>字典值：</label>
	      <div class="formControls col-xs-5 col-sm-5">
	        <input type="text" class="input-text" value="${dictItem.dictValue}"  placeholder="" id="dict-val" name="dictValue" datatype="*1-50" nullmsg="字典值不能为空" >
	      </div>
	      <div class="col-xs-4 col-sm-4"> </div>
	    </div>
   
	  <div class="row cl">
	      <label class="form-label col-xs-3 col-sm-3"><span class="c-red">*</span>是否有效：</label>
	      <div class="formControls col-xs-2 col-sm-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" value="true"   name="dictFlag" ${dictItem.dictFlag? 'checked="checked"':''} >
					<label for="checkbox-1">&nbsp;</label>
				</div>
		  </div>
	      <div class="col-xs-7 col-sm-7"> </div>
   	  </div>