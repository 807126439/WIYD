package com.spr.web.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.util.AssertException;
import com.spr.core.common.bean.CommonParam;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.DataQuery.OrderPair;
import com.spr.core.common.bean.Page;
import com.spr.core.common.dto.DownLoadDTO;
import com.spr.core.common.exception.BusinessException;
import com.spr.core.common.service.BaseService;
import com.spr.core.common.utils.Assert;
import com.spr.core.gobal.GobalVal;
import com.spr.core.utils.ShaPassEncodeUtil;
import com.spr.core.utils.excel.ExcelUtils;
import com.spr.core.utils.excel.ExcelUtils.TitleSet;
import com.spr.core.utils.excel.WrapExcelData;
import com.spr.web.file.service.IBaseFileService;
import com.spr.web.file.service.IZonePathService;
import com.spr.web.system.dao.IRoleDao;
import com.spr.web.system.dao.IUserDao;
import com.spr.web.system.dao.IUserInfoDao;
import com.spr.web.system.dto.user.UserDTO;
import com.spr.web.system.dto.user.UserInfoDTO;
import com.spr.web.system.dto.user.WeiUserDTO;
import com.spr.web.system.entity.Role;
import com.spr.web.system.entity.User;
import com.spr.web.system.entity.UserInfo;
import com.spr.web.system.service.IBaseDictService;
import com.spr.web.system.service.IRoleService;
import com.spr.web.system.service.IUserInfoService;
import com.spr.web.system.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements IUserService{
	@Resource
	private IUserDao userDao;
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IBaseDictService baseDictService;
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IZonePathService zonePathService;
	@Resource
	private IRoleService roleService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IUserInfoDao userInfoDao;
	
	@PostConstruct
	public void init(){

		
//		 String classBaseDir =  UserServiceImpl.class.getClassLoader().getResource("/").getPath();		
//		 String propertiesfile = classBaseDir+"systemConfig.properties";
//						
//		 String register_role  = PropertiesUtil.GetValueByKey(propertiesfile, "register_role");
		
		 
	}
	
	

	/**
     * 分页查询
     * @param 查询条件
     * @return
     * @author wb_java_zjr
     */
	public Page<UserDTO> searchUserByPage(DataQuery dq) {
		
				
		Long recTotal = this.userDao.countByCondition(dq.assemblePageOffset().getQueryMap());
		dq.assembleOrderInfo(new OrderPair[]{new OrderPair(User.class, "u"),new OrderPair(UserInfo.class, "uf")}, "u");
	
		List<UserDTO> userlist = this.userDao.selectListByCondition(dq.getQueryMap());
	
		
		
		int start = dq.getStartQuery();
		for (UserDTO ud : userlist) {
			ud.setSort((long) ++start);
			
		}

	
		return new Page<UserDTO>(dq.getCurrentPage(), dq.getPageSize(), userlist, recTotal);
	}
	
	
	/**
	 * 查找单个用户
	 * @param id
	 * @return
	 * @author wb_java_zjr
	*/ 
	public UserDTO getUserById(String id){
		Assert.notNull(id, "id must not be null");
		
		UserDTO result = this.userDao.getDetailById(id);
		Assert.notNull(result, Assert.EMPTY_REOCRD_STR);
		
		if(result.getPhotoId()!=null){
			DownLoadDTO  fileInfo = this.baseFileService.getViewFileInfo(result.getPhotoId());
			result.setHeadImgUrl(fileInfo.getFilePath());
		}
	
		return result;
		
	}
	
	
	
	/**
	 * 新增用户
	 * @param dto
	 * @author wb_java_zjr
	 */
    public void addUser(UserDTO dto,String password,String password2,String[] roleIds){
    	Assert.hasText(dto.getUserName(), "用户名不能为空！");
       	Assert.hasText(dto.getDepartName(), "所属部门不能为空！");
    	    
		if(this.userDao.checkUniqueIsExist(new CommonParam("user_name", dto.getUserName()))>0){
			throw new BusinessException("用户名："+dto.getUserName()+" 已存在！");
		}    		
    	
    	
    	if(!StringUtils.isBlank(dto.getEmail())){ 
    		if(this.userDao.checkUniqueIsExist(new CommonParam("email", dto.getEmail()))>0){
    			throw new BusinessException("邮箱："+dto.getEmail()+" 已存在！");
    		
		    }
    	}
    	
    	if(!StringUtils.isBlank(dto.getPhone())){   	
    		if(this.userDao.checkUniqueIsExist(new CommonParam("phone", dto.getPhone()))>0){
    			throw new BusinessException("手机号码："+dto.getPhone()+" 已存在！");
    		
		   }
    	}
    	
    	if(!password.equals(password2)){
			throw new BusinessException("两次输入的密码不一致！");
		}
    	
    	
    	String encodePwd = ShaPassEncodeUtil.encodePassWord(dto.getUserName(), password);
    	    	   	
    	User user = new User(
    				dto.getUserName(), dto.getChineseName(), encodePwd, dto.getEmail(), 
    				dto.getUserType() , dto.getPhone(), dto.getMemo());
    				
    	this.userDao.insert(user);
    	
    	if(roleIds!=null && roleIds.length>0){
    		Map<String, Object> queryMap = new HashMap<String, Object>();
  		    queryMap.put("userId", user.getId());
  		    queryMap.put("roleIds", roleIds);
  		    
    		this.userDao.bathInsertUserAndRole(queryMap);
    	}
    	
    	
    	if(this.logger.isInfoEnabled()){
    		this.logger.info("Add "+user.toString());
    	}
    }
	
	
	

	/**
	 * 修改用户
	 * @param dto
	 * @author wb_java_zjr
	*/
    public void updateUser(UserDTO dto,String[] roleIds){
    	Assert.notNull(dto.getId(), "id must not be null");
    	Assert.hasText(dto.getChineseName(), "用户名不能为空！");
    	Assert.hasText(dto.getDepartName(), "所属部门不能为空！");
    	
    	User user = this.userDao.getById(dto.getId());

 
		if(this.userDao.checkUniqueIsExist(new CommonParam("user_name", dto.getUserName(),dto.getId()))>0){
			throw new BusinessException("用户名："+dto.getUserName()+" 已存在！");
		}    		
    	
    	
    	if(!StringUtils.isBlank(dto.getEmail())){ 
    		if(this.userDao.checkUniqueIsExist(new CommonParam("email", dto.getEmail(),dto.getId()))>0){
    			throw new BusinessException("邮箱："+dto.getEmail()+" 已存在！");
    		
		   }
    	}
    	
    	if(!StringUtils.isBlank(dto.getPhone())){   	
    		if(this.userDao.checkUniqueIsExist(new CommonParam("phone", dto.getPhone(),dto.getId()))>0){
    			throw new BusinessException("手机号码："+dto.getPhone()+" 已存在！");
    		
		   }
    	}
	    	    	
    	user.setUserName(dto.getUserName());
    	user.setChineseName(dto.getChineseName());
    	user.setEmail(dto.getEmail());
    	user.setPhone(dto.getPhone());

    	user.setMemo(dto.getMemo());
    	user.setEnabled(dto.getEnabled() == null ? false:dto.getEnabled());   	
    	
    	this.userDao.update(user);
    	
    	//删除用户和角色的关联
    	this.userDao.deleteUserAndRoleByUserId(user.getId());
 
    	if(roleIds!=null && roleIds.length>0){
    		Map<String, Object> queryMap = new HashMap<String, Object>();
  		    queryMap.put("userId", user.getId());
  		    queryMap.put("roleIds", roleIds);
  		    
    		this.userDao.bathInsertUserAndRole(queryMap);
    	}
    	
    	if(this.logger.isInfoEnabled()){
    		this.logger.info("Update "+user.toString());
    	}
    	
    	UserInfo userInfo = this.userInfoDao.getByUserId(user.getId());
    	if(!dto.getDepartName().equals(userInfo.getDepartName())){//更新部门
    		userInfo.setDepartName(dto.getDepartName());
    		this.userInfoDao.updateSelective(userInfo);
    	}
    	
    } 
	
    /**
     * 更新当前用户信息
     * @param chineseName
     * @param newPwd
     * @param newPwd2
     */
    public void updateNowUserInfo(String chineseName,String phone,String email,String newPwd,String newPwd2){
    	  	
    	User user = this.userDao.getById(getNowUserId());	
    	
    	if(!StringUtils.isBlank(newPwd) && !StringUtils.isBlank(newPwd2)){
    		if(!newPwd.equals(newPwd2)){
    			throw new BusinessException("两次输入的密码不一致！");
    		}
    		String encodePwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), newPwd);
    		user.setPassWord(encodePwd);
    	}
    	
    	if(!StringUtils.isBlank(email)){ 
    		if(this.userDao.checkUniqueIsExist(new CommonParam("email",email,getNowUserId()))>0){
    			throw new BusinessException("邮箱："+email+" 已存在！");
    		
		   }
    	}
    	
    	if(!StringUtils.isBlank(phone)){   	
    		if(this.userDao.checkUniqueIsExist(new CommonParam("phone", phone,getNowUserId()))>0){
    			throw new BusinessException("手机号码："+phone+" 已存在！");
    		
		   }
    	}
		
	
		user.setChineseName(chineseName);
		user.setPhone(phone);
		user.setEmail(email);


		
		this.userDao.update(user);
    }
    
    
    /**
     * 删除用户
     * @param ids
     * @param isCjf
     */
    public void deleteUser(String[] ids){
    	for (String id : ids) {
    		User user = this.userDao.getById(id);
    		if(user!=null){
    			
    			this.userDao.deleteById(id);
    			
    		}
		
		}
    }
	
    
  
    
   
    
    
    
    /**
	 * 管理员修改密码
	 * @param userId
	 * @param newPwd
	 * @author wb_java_zjr
	 */
	public void changePassWordByAdmin(String userId,String newPwd,String newPwd2){
		Assert.notNull(userId, "userId must not be null");
				
		if(!newPwd.equals(newPwd2)){
			throw new BusinessException("两次输入的密码不一致！");
		}
		User user = this.userDao.getById(userId);
		
		String encodePwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), newPwd);
	
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", user.getId());
		values.put("pwd", encodePwd);
		
		this.userDao.changePassword(values);
		
		
		
	} 
	
	
	/**
	 * 用户修改密码
	 * @param oldPwd  旧密码
	 * @param newPwd  新密码
	 * @param newPwd2  新密码
	 * @author wb_java_zjr
	*/ 
	public void updatePassWord(String oldPwd,String newPwd, String newPwd2){
		if(StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd) 
				|| StringUtils.isBlank(newPwd2) ){
			throw new BusinessException("输入数据有误！");
		}
		if(!newPwd.equals(newPwd2)){
			throw new BusinessException("两次输入的密码不一致！");
		}
		
		User user = this.userDao.getById(getNowUserId());
		oldPwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), oldPwd);
		if(!oldPwd.equals(user.getPassWord())){//检查输入的密码是否正确
			throw new BusinessException("您输入的密码不正确！");
		}
		String encodePwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), newPwd);//加密新的密码
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", user.getId());
		values.put("pwd", encodePwd);
		
		this.userDao.changePassword(values);
		
	}
	
	

   
   
     public List<WeiUserDTO> selectWeiUser(DataQuery dq){
    	 return this.userDao.selectWeiUser(dq.getQueryMap());
     }



     /**
      * 用户注册
      * @param dto
      * @param password
      * @param password2
      * @param registerType
      * @param ucode
      */
     public void registerUser(UserDTO dto,String password,String password2,Short registerType,String ucode){
    	 Assert.notNull(registerType, "registerType could not be null");
    	 Assert.hasText(dto.getUserName(), "用户名不能为空");
    	 Assert.hasText(password, "密码不能为空");
    	 Assert.hasText(password2, "密码不能为空");
    	 Assert.isTrue(password.equals(password2), "两次输入的密码不相同");
    	 
    	 String encodePwd = ShaPassEncodeUtil.encodePassWord(dto.getUserName(), password);
        	   	 
    	    	 
    	 if(this.userDao.checkUniqueIsExist(new CommonParam("user_name", dto.getUserName()))>0){
 			throw new BusinessException("用户名："+dto.getUserName()+" 已存在！");
 		 }  
    	 
    	
    	 
    	 
     }
	

     
    
	
     
     
    
     
     

     
     
     /**
      * 导出excel
      */
     public DownLoadDTO exportExcel(DataQuery dq,String dataType){
    	 Assert.hasText(dataType, Assert.NULL_PARAM_STR("dataType"));
    	   	 
    	 //查询导出数据
    	 List<UserDTO> userlist = new ArrayList<UserDTO>();
    	 
		 Assert.notEmpty(userlist, "无导出的数据！");	
		 WrapExcelData wrapExcelData = null;	
    	
		 String fileName = null;
    	 TitleSet[] titleArray = null;   //表头
    	 if("unit".equals(dataType)){
    		 fileName = "单位注册用户列表";
    		 titleArray = new TitleSet[]{
    				 new TitleSet("序号", 20),new TitleSet("申请单位", 40),new TitleSet("单位性质", 20),new TitleSet("单位级别", 20),
    				 new TitleSet("经营范围", 50),new TitleSet("法人代表", 30),new TitleSet("联系人", 20),new TitleSet("联系电话", 30),
    				 new TitleSet("办公地址",50),new TitleSet("用户名",30),new TitleSet("忘记密码提示问题", 40),new TitleSet("忘记密码提示答案", 40),
    				 new TitleSet("提交时间", 30),new TitleSet("最后修改时间", 30),new TitleSet("附件", 80),new TitleSet("状态", 30),
    				 new TitleSet("审核意见", 60),new TitleSet("审核人", 30),new TitleSet("审核时间", 30)
    		 }; 
	 
    		 wrapExcelData = new WrapExcelData<UserDTO>() {
				
				public void wrapData(UserDTO obj, HSSFRow row) {
					row.createCell(0).setCellValue(obj.getSort());
					
				}
			};
    		 
    	 }
    		 
    	
    		 
    		 
    	 
    	
    			
    	 
    	 String savePath = this.zonePathService.getTempFileWholePath("xls");
    	 ExcelUtils<UserDTO> excelUtils = new ExcelUtils<UserDTO>(titleArray,savePath,userlist,wrapExcelData);
    	
    	 if(excelUtils.generateExecl()){
    		 
    		 return new DownLoadDTO(fileName+".xls", savePath);
    	 }else{
    		 throw new BusinessException("导出失败！");
    	 }
    	 
    	
     }
     
     
     /**
      * 停用或恢复账号
      * @param userId
      */
     public void changeAccountEnable(String userId){
    	 Assert.hasText(userId, Assert.NULL_PARAM_STR("userId"));
    	 
    	 User account = this.userDao.getById(userId);
    	 if(account!=null){
    		 if(account.getEnabled().booleanValue()){
        		 account.setEnabled(false);
        	 }else{
        		 account.setEnabled(true);
        	 }      		
    		 
    		 this.userDao.update(account);
    		 
    	 }
 	  	 
     }
   
     
     
   
     /**
      * 添加角色到用户
      * @param userId      用户id
      * @param roleNames   角色码
      */
     public void addRolesToUser(String userId,String[] roleCodes){
    	 Assert.hasText(userId, Assert.NULL_PARAM_STR("userId"));
    	 Assert.notEmpty(roleCodes);
    	 
    	 List<Role> roles = this.roleDao.selectByRoleCodes(roleCodes);
    	 List<String> roleIds = new ArrayList<String>(roles.size());
    	 for (Role role : roles) {
    		 roleIds.add(role.getId());
		 }
    	 
    	 if(!roleIds.isEmpty()){
			 Map<String, Object> queryMap = new HashMap<String, Object>();
		     queryMap.put("userId", userId);
		     queryMap.put("roleIds", roleIds);
		    
			 this.userDao.bathInsertUserAndRole(queryMap);
    	 }
     }
     
   
     /**
      * 删除用户的角色
      * @param userId      用户id
      * @param roleNames   角色码
      */
     public void deleteRolesToUser(String userId,String[] roleCodes){
    	 Assert.hasText(userId, Assert.NULL_PARAM_STR("userId"));
    	 Assert.notEmpty(roleCodes);
    	 
    	 List<Role> roles = this.roleDao.selectByRoleCodes(roleCodes);
    	 List<String> roleIds = new ArrayList<String>(roles.size());
    	 for (Role role : roles) {
    		 roleIds.add(role.getId());
		 }
    
    	 if(!roleIds.isEmpty()){
    		 this.userDao.deleteUserAndRoleRelation(userId, roleIds);
    	 }
		 
    	 
     }
     
     
     
}
