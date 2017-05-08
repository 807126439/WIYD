package com.wb.web.system.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.spring.security.service.IMySecurityMetadataSource;
import com.wb.web.system.dao.IAuthorityDao;
import com.wb.web.system.dao.IRoleDao;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.dto.role.RoleDTO;
import com.wb.web.system.dto.role.RoleQueryDTO;
import com.wb.web.system.entity.Authority;
import com.wb.web.system.entity.Role;
import com.wb.web.system.service.IRoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseService implements IRoleService{
    @Resource
	private IRoleDao roleDao;
    @Resource
    private IAuthorityDao authorityDao;
    @Resource
    private IUserDao userDao;
    @Resource
	private IMySecurityMetadataSource myInvocationSecurityMetadataSource;
    
    
    public List<RoleDTO> loadAllRole(){
    	List<Role> resultList = this.roleDao.loadAll();
    	List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
    	for (int i = 0; i < resultList.size(); i++) {
    		RoleDTO dto = new RoleDTO();
    		this.getMapper().map(resultList.get(i), dto);
    		dtoList.add(dto);
		}
    	
		return dtoList;
	}
	
    /**
     * 根据条件查询对象集合
     */
    public List<RoleDTO> searchListByCondition(RoleQueryDTO queryDTO){
    	
    	List<Role> resultList = this.roleDao.searchListByCondition(queryDTO);
    	List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
    	for (int i = 0; i < resultList.size(); i++) {
    		RoleDTO dto = new RoleDTO();
    		this.getMapper().map(resultList.get(i), dto);
    		dtoList.add(dto);
		}
    	
    	return dtoList;
    			
    }
    
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	public Page<RoleDTO> searchRoleByPage(RoleQueryDTO queryDTO) {
		
		Page<Role> result = this.roleDao.searchRoleByPage(queryDTO);
		List<Role> list = result.getList();
		List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
		for (int i = 0; i < list.size(); i++) {
			RoleDTO dto = new RoleDTO();
			this.getMapper().map(list.get(i), dto);
			dtoList.add(dto);
		}
		
		return new Page<RoleDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}
    
	
	/**
	 * 查找单个角色
	 * @param id
	 * @return
	 */
	public RoleDTO getRoleById(String id){
		if(id == null){
			throw new MyException("param is null");
		}
		Role role = this.roleDao.getById(id);
		RoleDTO dto = new RoleDTO();
		this.getMapper().map(role, dto);
		
		return dto;
		
	}
	
	
	
	
	/**
	 * 添加角色，并设置权限
	 * @param dto
	 * @param auths
	 */
	public void addRole(RoleDTO dto,String[] auths){
		if(dto.getRoleName()!=null){
    		if(this.roleDao.checkIsExistByProperty("roleName", dto.getRoleName(),null)){
    			throw new MyException("角色码："+dto.getRoleName()+" 已存在！");
    		}
    	}else{
    		throw new MyException("角色码不可为空！");
    	}
		
		if(dto.getRoleText()!=null){
    		if(this.roleDao.checkIsExistByProperty("roleText", dto.getRoleText(),null)){
    			throw new MyException("角色名："+dto.getRoleText()+" 已存在！");
    		}
    	}else{
    		throw new MyException("角色名不可为空！");
    	}
		
		Role role = new Role();
		this.getMapper().map(dto, role);
		role.setFlag(1);
		
		if(auths!=null){
			Set<Authority> authSet = new HashSet<Authority>();
			for (int i = 0; i < auths.length; i++) {
				authSet.add(new Authority(auths[i]));
				
			}
			
			role.setAuthorities(authSet);
		}
	
	
		this.roleDao.save(role);
		
	}
	
	
	/**
	 * 修改角色
	 * @param dto
	 * @param auths
	 */
	public void updateRole(RoleDTO dto,String[] auths){
		Role role = this.roleDao.getById(dto.getId());
		if(role == null){
			throw new MyException("can't find the role");
		}
		
		if(dto.getRoleName()!=null){
    		if(this.roleDao.checkIsExistByProperty("roleName", dto.getRoleName(),role.getId())){
    			throw new MyException("角色名："+dto.getRoleName()+" 已存在！");
    		}
    	}else{
    		throw new MyException("角色名不可为空！");
    	}
		
		if(dto.getRoleText()!=null){
    		if(this.roleDao.checkIsExistByProperty("roleText", dto.getRoleText(),role.getId())){
    			throw new MyException("角色备注："+dto.getRoleText()+" 已存在！");
    		}
    	}else{
    		throw new MyException("角色备注不可为空！");
    	}
		
				
		this.getMapper().map(dto, role);
		role.setFlag(1);
		
		if(auths!=null){
			Set<Authority> authSet = new HashSet<Authority>();
			for (int i = 0; i < auths.length; i++) {
				authSet.add(new Authority(auths[i]));
			}
		
			role.getAuthorities().clear();
			role.setAuthorities(authSet);
		}else{
			role.getAuthorities().clear();
		}
		
		this.roleDao.update(role);
		
		
	}
	
	
	/**
	 * 删除
	 * @param ids
	 */
	public void deleteRoles(String[] ids){

		for (int i = 0; i < ids.length; i++) {
			Role role = this.roleDao.getById(ids[i]);
			this.roleDao.delete(role);			
			
		    if(i%20 == 0){
		    	this.roleDao.flush();
		    	this.roleDao.clear();
		    }
		}	
		

	}
	
	
	
	
	
	
	/**
	 * 重置角色权限缓存
	 */
	public void reloadAuthMap(){
		this.myInvocationSecurityMetadataSource.reloadResourceMap();
	}
	
	
	

	
	
	
    
}

	
	
	

	
	
	

