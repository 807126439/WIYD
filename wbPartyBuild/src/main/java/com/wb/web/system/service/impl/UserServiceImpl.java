package com.wb.web.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.utils.ShaPassEncodeUtil;
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.dto.user.UserDTO;
import com.wb.web.system.dto.user.UserQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.entity.EduDegree;
import com.wb.web.system.entity.Role;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IEduDegreeService;
import com.wb.web.system.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements IUserService{
	@Resource
	private IUserDao userDao;
	@Resource
	private IBaseDictDao baseDictDao;
	@Resource
	private IEduDegreeService eduDegreeService;
	
	
	 /**
     * 分页查询
     * @param 查询条件
     * @return
     * @author wb_java_zjr
     */
	public Page<UserDTO> searchUserByPage(UserQueryDTO queryDTO) {
	
		Page<User> result = this.userDao.searchUserByPage(queryDTO);
		List<User> list = result.getList();

		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		for (int i = 0; i < list.size(); i++) {
			UserDTO dto = new UserDTO();
			this.getMapper().map(list.get(i), dto);			
			
			dtoList.add(dto);
		}
		
		return new Page<UserDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}
	
	
	/**
	 * 查找单个用户
	 * @param id
	 * @return
	 * @author wb_java_zjr
	 */
	public UserDTO getUserById(String id){
		if(id == null){
			throw new MyException("param is null");
		}
		User user = this.userDao.getById(id);
		
		UserDTO dto = new UserDTO();
		this.getMapper().map(user, dto);
		
		
		return dto;
		
	}
	
	
	
	/**
	 * 新增用户
	 * @param dto
	 * @author wb_java_zjr
	 */
    public void addUser(UserDTO dto,String password,String password2,String[] roleIds){
    	
    	if(!StringUtils.isBlank(dto.getUserName())){
    		if(this.userDao.checkIsExistByProperty("userName", dto.getUserName(),null)){
    			throw new MyException("用户名："+dto.getUserName()+" 已存在！");
    		}
    	}else{
    		throw new MyException("用户名不可为空！");
    	}
    	
    	if(!StringUtils.isBlank(dto.getEmail())){   		
			if(this.userDao.checkIsExistByProperty("email", dto.getEmail(),null)){
    			throw new MyException("邮箱："+dto.getEmail()+" 已存在！");
    		
		   }
    	}
    	
    	if(!StringUtils.isBlank(dto.getPhone())){   		
			if(this.userDao.checkIsExistByProperty("phone", dto.getPhone(),null)){
    			throw new MyException("手机号码："+dto.getPhone()+" 已存在！");
    		
		   }
    	}
    	
    	if(!password.equals(password2)){
			throw new MyException("两次输入的密码不一样！");
		}
    	
    	
    	String encodePwd = ShaPassEncodeUtil.encodePassWord(dto.getUserName(), password);
    	
    	String limitOnceUpNum = this.baseDictDao.getDictVal(BaseDict.USER_DICT_TYPE, BaseDict.USER_DICT_ITEM_UP_NUM, BaseDict.useable);
    	String limitOnceUpCapacity = this.baseDictDao.getDictVal(BaseDict.USER_DICT_TYPE,BaseDict.USER_DICT_ITEM_UP_SIZE, BaseDict.useable);
    	
    	if(StringUtils.isBlank(limitOnceUpNum) || StringUtils.isBlank(limitOnceUpCapacity)){
    		throw new MyException("创建用户的数字字典未初始化！");
    	}
    	
    	
    	User user = new User(
    			dto.getUserName(),
    			dto.getChineseName(), 
    			encodePwd,
    			dto.getEmail(),
    			dto.getPhone(),
    			dto.getLinkAddress(),
    			dto.getTel(),
    			dto.getMemo(),
    			Integer.parseInt(limitOnceUpNum),
    			Long.parseLong(limitOnceUpCapacity),
    			dto.getIsIgnoreStat());
    	 
    	user.setPartyStatus(dto.getPartyStatus() == null ? null:dto.getPartyStatus());
    	user.setIsParty(dto.getIsParty() == null ? false:dto.getIsParty());
    	user.setAge(dto.getAge());
    	user.setSex(dto.getSex());
    	if(!dto.getEduDegreeId().isEmpty()){
    		EduDegree edu=this.eduDegreeService.getEduDegreeById(dto.getEduDegreeId());
        	user.setEduDegree(edu);
    	}
    	
    	for (int i = 0; i < roleIds.length; i++) {
    		user.getRoles().add(new Role(roleIds[i]));
		}
    	
    	
    	this.userDao.save(user);
    	
    }
	
	
  
    
	

	/**
	 * 修改用户
	 * @param dto
	 * @author wb_java_zjr
	 */
    public void updateUser(UserDTO dto,String[] roleIds){
    	User user = this.userDao.getById(dto.getId());

    	if(!StringUtils.isBlank(dto.getUserName())){
    		if(this.userDao.checkIsExistByProperty("userName", dto.getUserName(),user.getId())){
    			throw new MyException("用户名："+dto.getUserName()+" 已存在！");
    		}
    	}else{
    		throw new MyException("用户名不可为空！");
    	}
    	
    	if(!StringUtils.isBlank(dto.getEmail())){   
			if(this.userDao.checkIsExistByProperty("email", dto.getEmail(),user.getId())){
    			throw new MyException("邮箱："+dto.getEmail()+" 已存在！");
    		}    		
    	}
    	
    	if(!StringUtils.isBlank(dto.getPhone())){  		
			if(this.userDao.checkIsExistByProperty("phone", dto.getPhone(),user.getId())){
    			throw new MyException("手机号码："+dto.getPhone()+" 已存在！");
    		
		   }
    	}
	    	    	
    	user.setUserName(dto.getUserName());
    	user.setChineseName(dto.getChineseName());
    	user.setEmail(dto.getEmail());
    	user.setPhone(dto.getPhone());
    	user.setLinkAddress(dto.getLinkAddress());
    	user.setTel(dto.getTel());
    	user.setMemo(dto.getMemo());
    	/*user.setAccountNonExpired(dto.getAccountNonExpired() == null ? false:dto.getAccountNonExpired());
    	user.setAccountNonLocked(dto.getAccountNonLocked() == null ? false:dto.getAccountNonLocked());
    	user.setCredentialsNonExpired(dto.getCredentialsNonExpired() == null ? false:dto.getCredentialsNonExpired());*/
    	user.setEnabled(dto.getEnabled() == null ? false:dto.getEnabled());
    	
    	user.setPartyStatus(dto.getPartyStatus() == null ? null:dto.getPartyStatus());
    	user.setIsParty(dto.getIsParty() == null ? false:dto.getIsParty());
    	user.setAge(dto.getAge());
    	user.setSex(dto.getSex());
    	user.setIsIgnoreStat(dto.getIsIgnoreStat());
    	
    	if(!dto.getEduDegreeId().isEmpty()){
    		EduDegree edu=this.eduDegreeService.getEduDegreeById(dto.getEduDegreeId());
        	user.setEduDegree(edu);
    	}
    	
    	
    	user.getRoles().clear();
    	for (int i = 0; i < roleIds.length; i++) {
    		user.getRoles().add(new Role(roleIds[i]));
		}
    	
	    	
	    	
	    this.userDao.update(user);
    	
    }
	
	
    /**
	 * 管理员修改密码
	 * @param userId
	 * @param newPwd
	 * @author wb_java_zjr
	 */
	public void changePassWordByAdmin(String userId,String newPwd,String newPwd2){
		if(userId == null){
			throw new MyException("param is null");
		}
		if(!newPwd.equals(newPwd2)){
			throw new MyException("两次输入的密码不一样！");
		}
		User user = this.userDao.getById(userId);
		
		String encodePwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), newPwd);
		user.setPassword(encodePwd);
		
		this.userDao.update(user);
		
		
		
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
			throw new MyException("输入数据有误！");
		}
		if(!newPwd.equals(newPwd2)){
			throw new MyException("两次输入的密码不一样！");
		}
		User user = this.userDao.getById(getNowUserId());
		oldPwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), oldPwd);
		if(!oldPwd.equals(user.getPassword())){//检查输入的密码是否正确
			throw new MyException("您输入的密码不正确！");
		}
		String encodePwd = ShaPassEncodeUtil.encodePassWord(user.getUserName(), newPwd);//加密新的密码
		user.setPassword(encodePwd);
		
		this.userDao.update(user);
		
	}
	
	
	
   public void updateImportUser(String jsonData){
		JSONObject dataObj = JSONObject.fromObject(jsonData);	   
		JSONArray userArray = dataObj.getJSONArray("userlist");
	   
		String limitOnceUpNum = this.baseDictDao.getDictVal(BaseDict.USER_DICT_TYPE, BaseDict.USER_DICT_ITEM_UP_NUM, BaseDict.useable);
    	String limitOnceUpCapacity = this.baseDictDao.getDictVal(BaseDict.USER_DICT_TYPE,BaseDict.USER_DICT_ITEM_UP_SIZE, BaseDict.useable);
    	
    	if(StringUtils.isBlank(limitOnceUpNum) || StringUtils.isBlank(limitOnceUpCapacity)){
    		throw new MyException("创建用户的数字字典未初始化！");
    	}
		
		
		for (int i = 0; i < userArray.size(); i++) {
			JSONObject userObj = userArray.getJSONObject(i);
			
			String userName = userObj.getString("userid");
			String chineseName = userObj.getString("username").equals("null") ? null:userObj.getString("username");
			String phone = userObj.getString("phoneNumber").equals("null") ? null:userObj.getString("phoneNumber");
			String email = userObj.getString("email").equals("null") ? null:userObj.getString("email");
			
			
			String encodePwd = ShaPassEncodeUtil.encodePassWord(userName, "wanve123456");
	    	
			User user = new User(
					userName,
					chineseName, 
	    			encodePwd,
	    			email,
	    			phone,
	    			null,
	    			phone,
	    			null,
	    			Integer.parseInt(limitOnceUpNum), Long.parseLong(limitOnceUpCapacity),false);
			
						

	    	user.getRoles().add(new Role("9d8ae100347311e6b9a4507b9dae4454"));//普通会员
				    		    	
	    	this.userDao.save(user);
	    	
	    	    	
	    	if(i%20 == 0){
	    		this.userDao.flush();
	    		this.userDao.clear();
	    	}
		} 
    	
    }
   
   
   public void importByExcel(CommonsMultipartFile file){
	 String limitOnceUpNum = this.baseDictDao.getDictVal(BaseDict.USER_DICT_TYPE, BaseDict.USER_DICT_ITEM_UP_NUM, BaseDict.useable);
   	 String limitOnceUpCapacity = this.baseDictDao.getDictVal(BaseDict.USER_DICT_TYPE,BaseDict.USER_DICT_ITEM_UP_SIZE, BaseDict.useable);
   	
     if(StringUtils.isBlank(limitOnceUpNum) || StringUtils.isBlank(limitOnceUpCapacity)){
   		throw new MyException("创建用户的数字字典未初始化！");
   	 }
	   	     
     try {
		Workbook wb = new HSSFWorkbook(file.getInputStream());
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		while (it.hasNext()) {
			Row row = it.next();
			
			String userName = row.getCell(0).getStringCellValue();
			String chineseName = row.getCell(1).getStringCellValue();					
			String encodePwd = ShaPassEncodeUtil.encodePassWord(userName,userName);
	    	
			User user = new User(
					userName,
					chineseName, 
	    			encodePwd,
	    			null,
	    			null,
	    			null,
	    			null,
	    			null,
	    			Integer.parseInt(limitOnceUpNum), Long.parseLong(limitOnceUpCapacity),false);
			user.getRoles().add(new Role("9d8ae100347311e6b9a4507b9dae4454"));//普通会员
			this.userDao.save(user);
		}
		
		wb.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	   	
	   	
	   	
   }
   




	@Override
	public Map<String, Object> getStatistics() {
		Map<String,Object> resultList = this.userDao.getStatistics();
		return resultList;
	}


   public void deleteUserByChangeVal(String ids[]){
	   Assert.notEmpty(ids);
	   this.userDao.discardUser(ids);
   }
   
   
   
   
}
