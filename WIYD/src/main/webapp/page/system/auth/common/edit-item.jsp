<%@ page language="java"  pageEncoding="utf-8"%>


 <div class="row cl">
   <label class="form-label col-xs-3"><span class="c-red">*</span>父节点：</label>
   <div class="formControls col-xs-6">
     <input type="text" class="input-text"  placeholder="父节点"  id="parName" value="${authItem.parentName}" readonly="readonly">	  
     <input type="hidden" name="parentId" id="parId" value="${authItem.parentId}" >

   </div>
   <div class="col-xs-3"><a href="javascript:;" onclick="setParNull()" >设置为空</a></div>
 </div> 



<div class="row cl">
      <label class="form-label col-xs-3"><span class="c-red">*</span>权限名：</label>
      <div class="formControls col-xs-6">
        <input type="text" class="input-text" value="${authItem.authText}"  placeholder="" id="auth-name" name="authText" datatype="*3-50" nullmsg="权限名不能为空">
      </div>
      <div class="col-xs-3"> </div>
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-3"><span class="c-red">*</span>权限码：</label>
      <div class="formControls col-xs-6">
        <input type="text" class="input-text" value="${authItem.authCode}"   placeholder="" id="auth-code" name="authCode" datatype="*2-35" nullmsg="权限码不能为空">
      </div>
      <div class="col-xs-3"> </div>
    </div>
    
    <div class="row cl">
      <label class="form-label col-xs-3"><span class="c-red">*</span>资源地址：</label>
      <div class="formControls col-xs-6">
        <input type="text" class="input-text" value="${authItem.resourecesUrl}"  placeholder="" id="auth-url" name="resourecesUrl" datatype="*2-256"  ignore="ignore" >
      </div>
      <div class="col-xs-3"> </div>
    </div>
  
  <div class="row cl">
      <label class="form-label col-xs-3">开启控制：</label>
      <div class="formControls col-xs-6 skin-minimal">
         	
		<div class="check-box">
			<input type="checkbox" id="checkbox-1" value="1"  name="flag"  ${authItem.flag == 1? 'checked="checked"':''}>
			<label for="checkbox-1">&nbsp;</label>
		</div>
	  
      
      </div>
      <div class="col-xs-3"> </div>
  	  </div>
    
    
 <div class="row cl">
      <label class="form-label col-xs-3"><span class="c-red">*</span>权限类型：</label>
      <div class="formControls col-xs-7 skin-minimal">
      	<c:forEach items="${requestScope.typeDict}" var="f" varStatus="vs">
      	    <div class="radio-box">
      	      <c:choose>
      	      	<c:when test="${vs.index==1}">
      	      		<input type="radio" id="authType-${vs.index}" class="cl-type"  name="authType" value="${f.dictValue}" datatype="*" nullmsg="请选择权限类型！" ${authItem.authType == f.dictValue? 'checked="checked"':''}>
      	      	</c:when>
      	      	<c:otherwise>
      	      		  <input type="radio" id="authType-${vs.index}" class="cl-type" name="authType" value="${f.dictValue}" ${authItem.authType == f.dictValue? 'checked="checked"':''}>
      	      	</c:otherwise>
      	      	      	      
      	      </c:choose>
      	             
	          <label for="authType-${vs.index}">${f.dictItem}</label>
        	</div>
      			      	
      	</c:forEach>
     
      </div>
      <div class="col-xs-2"> </div>
  	  </div>  

  
  
    
    
     <div class="row cl">
      <label class="form-label col-xs-3">排序：</label>
      <div class="formControls col-xs-6">
        <input type="text" class="input-text" value="${authItem.authOrder}"   id="auth-order" name="authOrder" datatype="n" ignore="ignore" >
      </div>
      <div class="col-xs-3"> </div>
    </div>  
    

	 <div class="row cl">
      <label class="form-label col-xs-3">图案：</label>
      <div class="formControls col-xs-6">
        <input type="text" class="input-text" value="${authItem.icon}"   placeholder="" id="auth-icon" name="icon" datatype="*" ignore="ignore" >
      </div>
      <i class="Hui-iconfont">${authItem.icon}</i> 
      <div class="col-xs-3"> </div>
    </div>  