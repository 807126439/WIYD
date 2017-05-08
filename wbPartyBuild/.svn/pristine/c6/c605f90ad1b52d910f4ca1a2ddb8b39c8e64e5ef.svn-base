package com.wb.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.web.system.dao.IAuthorityDao;
import com.wb.web.system.dto.authority.AuthLightDto;
import com.wb.web.system.dto.authority.AuthQueryDTO;
import com.wb.web.system.dto.authority.AuthTreeQueryDTO;
import com.wb.web.system.dto.authority.AuthZtreeDto;
import com.wb.web.system.dto.authority.AuthorityDTO;
import com.wb.web.system.entity.Authority;
import com.wb.web.system.service.IAuthorityService;

@Service("authorityService")
@Transactional
public class AuthorityServiceImpl extends BaseService implements IAuthorityService{
    @Resource
	private IAuthorityDao authorityDao;
	
    
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	public Page<AuthorityDTO> searchAuthByPage(AuthQueryDTO queryDTO) {
		
		Page<Authority> result = this.authorityDao.searchAuthorityByPage(queryDTO);
		List<Authority> list = result.getList();
		List<AuthorityDTO> dtoList = new ArrayList<AuthorityDTO>();
		for (int i = 0; i < list.size(); i++) {
			AuthorityDTO dto = new AuthorityDTO();
			this.getMapper().map(list.get(i), dto);
			if(list.get(i).getParent()!=null){
				dto.setParentId(list.get(i).getParent().getId());
				dto.setParentName(list.get(i).getParent().getAuthText());
			}						
			
			dtoList.add(dto);
		}
		
		return new Page<AuthorityDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}
    
	
    /**
     * 通过id查找权限
     */
	public AuthorityDTO getAuthById(String id){
		if(id == null){
			throw new MyException("param is null");
		}
		Authority authority = this.authorityDao.getById(id);
		AuthorityDTO dto = new AuthorityDTO();
		this.getMapper().map(authority, dto);
		
		if(authority.getParent()!=null){
			dto.setParentId(authority.getParent().getId());
			dto.setParentName(authority.getParent().getAuthText());
		}
       
	    return dto;
	}
	
	

	
	
	/**
	 * 获取所有的权限节点并生成树结构
	 * @param roleId  角色id
	 * @return
	 */
	public String getWholeZtreeWithCheck(String roleId){

		List<Authority> wholeAuths = this.authorityDao.findListBySql("SELECT * FROM c_authority ORDER BY parent_id");
		
		List<Authority> ownAuths = new ArrayList<Authority>();
		if(!StringUtils.isBlank(roleId)){
			 ownAuths =  this.authorityDao.searchAuthoritiesByRole(roleId,null);			
		}
		
		//{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < wholeAuths.size(); i++) {
			Authority curr = wholeAuths.get(i);
			sb.append("{ id:\""+curr.getId()+"\"");
			if(curr.getParent() == null){
				sb.append(",pId:0");
			}else{
				sb.append(",pId:\""+curr.getParent().getId()+"\"");
			}
			
			sb.append(",name:\""+curr.getAuthText()+"\"");
			
			if(!ownAuths.isEmpty()){
				if(ownAuths.contains(curr)){
					sb.append(",checked:true");
				}
			}
			
			sb.append(", open:true},");
		}
		
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);	
			sb.append("]");
		}
	
	
		return sb.toString();
		

	}



	
	
	
	
	
	/**
	 * 查询ztree权限树
	 * @param queryDTO
	 * @return
	 */
	public List<AuthZtreeDto> searchAuthZtree(AuthTreeQueryDTO queryDTO){
		List<Authority> authList = this.authorityDao.searchTreeByCondition(queryDTO);
		List<AuthZtreeDto> dtoList = new ArrayList<AuthZtreeDto>();
		for (int i = 0; i < authList.size(); i++) {
			Authority auth = authList.get(i);
			AuthZtreeDto dto = new AuthZtreeDto(auth.getId(),(auth.getAuthOrder() == null?0:auth.getAuthOrder() )+"-"+auth.getAuthText(),
					auth.getChildren().size()>0, auth.getParent() == null?null:auth.getParent().getId(),
							queryDTO.getLevel()== null ? 0 :queryDTO.getLevel());
		
			dtoList.add(dto);
					
		}
		
				
		return dtoList;
	
	}
	
	
	
	
	
	/**
	 * 添加权限
	 * @param dto
	 */
	public void addAuth(AuthorityDTO dto){
		if(this.authorityDao.checkIsExistByProperty("authCode", dto.getAuthCode(), null)){
			throw new MyException("权限码已存在！");
		}
		
		if(this.authorityDao.checkIsExistByProperty("authText", dto.getAuthText(), null)){
			throw new MyException("权限名已存在！");
		}
		
		Authority authority = new Authority();
		this.getMapper().map(dto,authority);
		authority.setParent(StringUtils.isBlank(dto.getParentId()) ? null:new Authority(dto.getParentId()));

		if(authority.getResourecesUrl()!=null){
			authority.setResourecesUrl(authority.getResourecesUrl().trim().isEmpty()? null:authority.getResourecesUrl().trim());
		}
		
		this.authorityDao.save(authority);
	}
	
	
	/**
	 * 修改权限
	 * @param dto
	 */
	public void updateAuth(AuthorityDTO dto){
		if(dto.getId() == null){
			throw new MyException("param id is null");
		}
		Authority authority = this.authorityDao.getById(dto.getId());
		if(this.authorityDao.checkIsExistByProperty("authCode", dto.getAuthCode(), dto.getId())){
			throw new MyException("权限码已存在！");
		}
		
		if(this.authorityDao.checkIsExistByProperty("authText", dto.getAuthText(), dto.getId())){
			throw new MyException("权限名已存在！");
		}
		
		this.getMapper().map(dto,authority);
		authority.setParent(StringUtils.isBlank(dto.getParentId()) ? null:new Authority(dto.getParentId()));

		if(authority.getResourecesUrl()!=null){
			authority.setResourecesUrl(authority.getResourecesUrl().trim().isEmpty()? null:authority.getResourecesUrl().trim());
		}
			
		this.authorityDao.update(authority);
	}
	
	
	/**
	 * 删除权限
	 * @param ids
	 */
	public void deleteAuth(String[] ids){
		for (int i = 0; i < ids.length; i++) {
			Authority auth = this.authorityDao.getById(ids[i]);
			if(auth!=null){
				if(auth.getParent()!=null){
					auth.setParent(null);
				}
				this.authorityDao.delete(auth);
			}
			
			
		}
	}
	
    
	
	
	
	/**
	 * 获取权限的控制码
	 * @param roleList
	 * @return
	 */
	public List<String> getAuthControlItem(String roleList){

		
		return this.authorityDao.getAuthControlItem(roleList);
	}
	
	
	/**
	 * 获取用户的权限菜单
	 * @param roleList
	 * @param contextName
	 * @return
	 */
	public String getAuthMenu(String roleList,String contextName){			
						
			List<AuthLightDto> firsts = this.authorityDao.getAuthsByRoleAndParent(roleList,null,Authority.flag_openControl,Authority.auth_menu);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < firsts.size(); i++) {
				AuthLightDto auth = firsts.get(i);
				sb.append("<dl id=\"menu-"+i+"\"><dt>");
				sb.append("<i class=\"Hui-iconfont\">"+auth.getIcon()+"</i>  <a href=\"javascript:;\">"+auth.getText()+"</a> <i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i>");
				sb.append("</dt><dd>");
				
				List<AuthLightDto> seconds = this.authorityDao.getAuthsByRoleAndParent(roleList, auth.getId(),Authority.flag_openControl,Authority.auth_acess);
				if(!seconds.isEmpty()){
					sb.append("<ul>");
					 for (int j = 0; j < seconds.size(); j++) {
						 AuthLightDto child = seconds.get(j);
						 sb.append("<li><a _href=\""+contextName+child.getResourecesUrl()+"\" href=\"javascript:;\" data-title=\""+child.getText()+"\">"+
						 		child.getText()+"</a></li>");
						 
					 }
					 sb.append("</ul>");
				}
				
				sb.append("</dd></dl>");
			}

			
			return sb.toString();
		}
	
	
	/**
	 * @author wb_java_zjr
	 */
	public List<Authority> searchAuthoritiesByRole(String roleId,Short flag, Short... authTypes){
		
		return this.authorityDao.searchAuthoritiesByRole(roleId,flag,authTypes);
	}
	
	
	
}
