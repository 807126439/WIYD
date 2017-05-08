<%@ page language="java" pageEncoding="utf-8"%>

<div style="display: none;" id="hide_role" class="pd-10">
    		<table id="role_table" class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" id="checkAll"></th>
						<th>角色名</th>		
						<th>角色描述</th>					
					</tr>							
				</thead>
			<tbody>
				<c:forEach items="${roleList}" var="r" varStatus="vs"> 
					<tr class="text-c">
						<td>
							<input type="checkbox" class="roleBox" value="${r.id}" data-name="${r.roleText}" name="roleIds" ${r.check == true? 'checked="checked"':''  } >
						</td>
						<td>${r.roleText}</td>
						<td>${r.roleMemo}</td>							
					</tr>					
				</c:forEach>					
							
			</tbody>								
	 	</table>	     	
    		     	
 </div>
