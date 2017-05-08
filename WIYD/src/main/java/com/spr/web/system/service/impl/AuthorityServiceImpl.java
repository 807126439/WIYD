package com.spr.web.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spr.core.common.bean.CommonParam;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.service.BaseService;
import com.spr.core.common.utils.Assert;
import com.spr.web.system.constant.AuthTypeIconConsts;
import com.spr.web.system.dao.IAuthorityDao;
import com.spr.web.system.dto.authority.AuthZtreeDto;
import com.spr.web.system.dto.authority.AuthorityDTO;
import com.spr.web.system.entity.Authority;
import com.spr.web.system.service.IAuthorityService;

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
	public Page<AuthorityDTO> searchAuthByPage(DataQuery dq) {
		
		Long recTotal = this.authorityDao.countByCondition(dq.assemblePageOffset().getQueryMap());
		dq.assembleOrderInfo(Authority.class,"a");
		
		List<AuthorityDTO> list = this.authorityDao.selectListByCondition(dq.getQueryMap());
		DataQuery.wrapTableNo2(list, dq.getStartQuery());	
		
		return new Page<AuthorityDTO>(dq.getCurrentPage(), dq.getPageSize(), list, recTotal);
	}
    
	
   /**
     * 通过id查找权限
    */ 
	public AuthorityDTO getAuthById(String id){
		Assert.hasText(id, "id must not be null");		
		
		return this.authorityDao.selectWithLeftParentById(id);
	

	}
	
	
	/**
	 * 获取整棵权限树的结构，如果authId不为空，则在权限树选中对应的节点
	 * @param authId
	 * @return
	 */
	public String getWholeZtreeStructure(String authId){
		
		List<Authority> wholeAuths = this.authorityDao.selectAuthByCondition3(null);
		
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < wholeAuths.size(); i++) {
			Authority curr = wholeAuths.get(i);
			sb.append("{ id:\""+curr.getId()+"\"");
			if(StringUtils.isBlank(curr.getParentId())){
				sb.append(",pId:0");
			}else{
				sb.append(",pId:\""+curr.getParentId()+"\"");
			}
			
			sb.append(",name:\""+(curr.getAuthOrder() == null ? "0":curr.getAuthOrder())+"."+curr.getAuthText()+"\"");
			setAuthTypeIcon(sb, curr.getAuthType());
			if(curr.getId().equals(authId)){
		
				sb.append(",checked:true");				
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
	 * 获取所有的权限节点并生成树结构
	 * @param roleId  角色id
	 * @return
	*/
	public String getWholeZtreeWithCheck(String roleId){

		List<Authority> wholeAuths = this.authorityDao.selectAuthByCondition3(null);
		
		List<Authority> ownAuths = new ArrayList<Authority>();
		if(!StringUtils.isBlank(roleId)){
			 ownAuths =  this.authorityDao.selectAuthByCondition3(roleId);			
		}
		
		//{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < wholeAuths.size(); i++) {
			Authority curr = wholeAuths.get(i);
			sb.append("{ id:\""+curr.getId()+"\"");
			if(StringUtils.isBlank(curr.getParentId())){
				sb.append(",pId:0");
			}else{
				sb.append(",pId:\""+curr.getParentId()+"\"");
			}
			
			sb.append(",name:\""+(curr.getAuthOrder() == null ? "0":curr.getAuthOrder())+"."+curr.getAuthText()+"\"");			
			setAuthTypeIcon(sb, curr.getAuthType());
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


    private void setAuthTypeIcon(StringBuilder sb,Short authType){
    	switch (authType) {
		case  Authority.auth_acess:
			sb.append(",icon:\""+AuthTypeIconConsts.AUTH_ACCESS+"\"");
			
			break;
		case  Authority.auth_button:
			sb.append(",icon:\""+AuthTypeIconConsts.AUTH_BUTTON+"\"");
			
			break;
		case  Authority.auth_menu:
			sb.append(",icon:\""+AuthTypeIconConsts.AUTH_MENU+"\"");
			
			break;	

		default:
			break;
		}
    }
	
	
	
	
	
	/**
	 * 查询ztree权限树
	 * @param parentId
	 * @param level
	 * @return
	 */
	public List<AuthZtreeDto> searchAuthZtree(String parentId,Integer level){		
		List<Authority> authList = this.authorityDao.selectAuthByCondition4(
								      !StringUtils.isBlank(parentId)?parentId:null);
		List<AuthZtreeDto> dtoList = new ArrayList<AuthZtreeDto>();
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		for (int i = 0; i < authList.size(); i++) {
			Authority auth = authList.get(i);
			queryMap.put("parentId", auth.getId());
			AuthZtreeDto dto = new AuthZtreeDto(
							auth.getId(),
							(auth.getAuthOrder() == null?0:auth.getAuthOrder() )+"-"+auth.getAuthText(),
					        this.authorityDao.countByCondition(queryMap)>0, 
					        auth.getParentId(),
					        level == null ? 0 :level);
		
			dtoList.add(dto);
					
		}
		
				
		return dtoList;
	
	}
	
	
	
	
	
	/**
	 * 添加权限
	 * @param dto
	*/
	public String addAuth(AuthorityDTO dto){
		Assert.hasText(dto.getAuthCode(),"权限码不能为空！");
		Assert.hasText(dto.getAuthText(),"权限名不能为空！");
		
		Assert.isTrue(
				  this.authorityDao.checkUniqueIsExist(new CommonParam("auth_code", dto.getAuthCode()))==0,
				      "权限码已存在！");
		
		/*Assert.isTrue(
				this.authorityDao.checkUniqueIsExist(new CommonParam("auth_text", dto.getAuthText()))==0,
			      "权限名已存在！");*/
				
		
		
		Authority authority = new Authority(
							      dto.getAuthCode(), dto.getAuthText(), 
							      dto.getResourecesUrl(), dto.getAuthType(), 
							      dto.getFlag() == null?Authority.flag_normal:dto.getFlag(), dto.getIcon(),
							      dto.getAuthOrder(), dto.getParentId());
		
		
		this.authorityDao.insert(authority);
		
		return authority.getId();
	} 
	
	
	/**
	 * 修改权限
	 * @param dto
	 */
	public void updateAuth(AuthorityDTO dto){
		Assert.hasText(dto.getId(),"id must not be null");
		Assert.hasText(dto.getAuthCode(),"权限码不能为空！");
		Assert.hasText(dto.getAuthText(),"权限名不能为空！");
		
		Authority authority = this.authorityDao.getById(dto.getId());
		Assert.isTrue(
				  this.authorityDao.checkUniqueIsExist(new CommonParam("auth_code", dto.getAuthCode(),
						  authority.getId()))==0,
				      "权限码已存在！");
		
		/*Assert.isTrue(
				this.authorityDao.checkUniqueIsExist(new CommonParam("auth_text", dto.getAuthText(),
						authority.getId()))==0,
			      "权限名已存在！");*/
		
		authority.setAuthCode(dto.getAuthCode());
		authority.setAuthText(dto.getAuthText());
		authority.setResourecesUrl(dto.getResourecesUrl());
		authority.setAuthType(dto.getAuthType());
		authority.setFlag(dto.getFlag() == null?Authority.flag_normal:dto.getFlag());
		authority.setIcon(dto.getIcon());
		authority.setAuthOrder(dto.getAuthOrder());
		authority.setParentId(dto.getParentId());
		
	
		this.authorityDao.update(authority);
	}
	
	
	/**
	 * 删除权限
	 * @param ids
	 */
	public void deleteAuth(String[] ids){
		for (int i = 0; i < ids.length; i++) {
			
			this.authorityDao.deleteById(ids[i]);
		}
	}
	
    
	
	
	
	/**
	 * 获取权限的控制码
	 * @param roleList
	 * @return
	 */
	public List<String> getAuthControlItem(Set<String> roleIdList){
			
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("roleIds", roleIdList);
		queryMap.put("flag", Authority.flag_openControl);
		queryMap.put("type", new Short[]{Authority.auth_acess,Authority.auth_button});
		
		return this.authorityDao.getAuthControlItem(queryMap);
	}
	
	
	/**
	 * 获取用户的权限菜单
	 * @param roleIds
	 * @param contextName
	 * @return
	 */
	public String getAuthMenu(Set<String> roleIdList,String contextName){			
	
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("roleIds", roleIdList);
		queryMap.put("flag", Authority.flag_openControl);	
		queryMap.put("typelist", new short[] {Authority.auth_menu});//第一层菜单类型
		//不限制authType
		
		List<Authority> firsts = this.authorityDao.selectAuthByCondition2(queryMap);
		short[]  authTypes = new short[] {Authority.auth_acess,Authority.auth_menu};//第二层菜单类型或访问类型
		queryMap.put("typelist", authTypes);
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < firsts.size(); i++) {
			Authority auth = firsts.get(i);			
			sb.append("<li class=\"sidebar-menu-item\">");
			sb.append("<a href=\"javascript:;\" id=\"t_"+auth.getAuthCode()+"\" _href=\""+( StringUtils.isBlank(auth.getResourecesUrl())? "javascript:;":contextName+auth.getResourecesUrl())+"\" target=\"mainframe\" le=\"1\" title-val=\""+auth.getAuthText()+"\"><i class=\"icon_menu "+auth.getIcon()+"\"></i><span>"+auth.getAuthText()+"</span></a>");
			
			queryMap.put("parentId",auth.getId());
			List<Authority> seconds = this.authorityDao.selectAuthByCondition2(queryMap);	
			if(!seconds.isEmpty()){
			
				 sb.append("<ul class=\"sidebar-menu-innermenu\">");									
							
				 for (int j = 0; j < seconds.size(); j++) {
					 Authority child = seconds.get(j);
					 sb.append("<li class=\"sidebar-menu-item\"><a id=\"t_"+child.getAuthCode()+"\" _href=\""+contextName+child.getResourecesUrl()+"\" href=\"javascript:;\" title-val=\""+child.getAuthText()+"\" le=\"2\"><span>"+
						 		child.getAuthText()+"</span></a></li>");
				 }
				 sb.append("</ul>");
			}
						
			sb.append("</li>");
			
		}

		
		return sb.toString();
	}



	

	
	
	
}
