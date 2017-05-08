<%@ page language="java"  pageEncoding="utf-8"%>

   <div class="row cl">
			      <label class="form-label col-xs-3"><span class="c-red">*</span>角色码：</label>
			      <div class="formControls col-xs-8">
			        <input type="text" class="input-text"  value="${roleItem.roleName}" id="role-name" name="roleName" datatype="*3-50" nullmsg="角色名不能为空">
			      </div>
			      <div class="col-xs-1"> </div>
			    </div>
			    
			    <div class="row cl">
			      <label class="form-label col-xs-3"><span class="c-red">*</span>角色名：</label>
			      <div class="formControls col-xs-8">
			        <input type="text" class="input-text"   value="${roleItem.roleText}" id="role-text" name="roleText" datatype="*2-35" nullmsg="角色备注不能为空">
			      </div>
			      <div class="col-xs-1"> </div>
			    </div>
			    
			
			    
			    
			    <div class="row cl">
			      <label class="form-label col-xs-3">角色描述：</label>
			      <div class="formControls col-xs-8">			      
			    	<textarea style="height: 180px" name=roleMemo cols="" rows="" class="textarea"   datatype="*1-255" ignore="ignore" onKeyUp="textarealength(this,255)">${roleItem.roleMemo}</textarea>
					<p class="textarea-numberbar"><em class="textarea-length">0</em>/255</p>			    		    
			      </div>
			      <div class="col-xs-1"> </div>
			    </div>
		   
		      
			    <div class="row cl">
			      <div class="col-xs-3 col-xs-offset-3">
			        	<m:b_button operCode="addRole,editRole" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
			      </div>
			      <label class="form-label col-xs-4">是否重置缓存：</label>
					<div class="formControls col-xs-1 skin-minimal">
						<div class="check-box">
							<input type="checkbox" id="checkbox-1" value="true"   name="isRefresh" >
							<label for="checkbox-1">&nbsp;</label>
						</div>
					</div>
					
					<div class="col-xs-1"></div>
			    </div>
			    
			    <div id="check_req"></div>
			    
			    
			    
