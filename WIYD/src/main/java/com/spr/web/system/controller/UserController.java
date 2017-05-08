package com.spr.web.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.controller.BaseController;
import com.spr.web.system.dto.baseDict.BaseDictDTO;
import com.spr.web.system.dto.role.RoleDTO;
import com.spr.web.system.dto.user.UserDTO;
import com.spr.web.system.dto.user.UserInfoDTO;
import com.spr.web.system.entity.BaseDict;
import com.spr.web.system.entity.Role;
import com.spr.web.system.service.IBaseDictService;
import com.spr.web.system.service.IRoleService;
import com.spr.web.system.service.IUserInfoService;
import com.spr.web.system.service.IUserService;


@Controller
@Scope("prototype")
@RequestMapping("/userController")
public class UserController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5177791084663689444L;
	@Resource
	private IUserService userService;
	@Resource
	private IRoleService roleService;
	@Resource
	private IBaseDictService baseDictService;
	@Resource
	private IUserInfoService userInfoService;
	
	/**
	 * 跳转到后端用户管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="viewPage",method={RequestMethod.GET})
	public String viewUserPage(HttpServletRequest request){
			
	    this.wrapMenuTitle(request);	
	    request.setAttribute("dataType", "back");
					
		return "system/user/userList.jsp";
	}
	

	
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value="getPageData",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> loadUserByPage(HttpServletRequest request,DataQuery dq,String userName,
						String chineseName,String dataType,String reqType){
		//Assert.hasText(dataType, "param [dataType] could not be null");
		
		this.wrapTableQueryParams(request, dq);
		dq.getQueryMap().put("userName", StringUtils.isBlank(userName)?null:"%"+userName+"%");
		dq.getQueryMap().put("chineseName", StringUtils.isBlank(chineseName)?null:"%"+chineseName+"%");
	
		
	
	
		
		Page<UserDTO> pageResult = this.userService.searchUserByPage(dq);
		 		
		
		return this.handlePageReult(pageResult);
	}
	
	

	
	@RequestMapping(value="/skipAddUser")
	public String skipAddUser(HttpServletRequest request){
		DataQuery dq = new DataQuery();
		dq.putToMap("roleType", Role.TYPE_BACK);
		List<RoleDTO> roleList = this.roleService.searchListByCondition(dq);
		request.setAttribute("roleList", roleList);
		
		
		dq.getQueryMap().put("dictType", BaseDict.USER_BACK_TYPE);
		dq.getQueryMap().put("dictFlag", true);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	
	    request.setAttribute("typeDicts", typeDicts);
		
	
		request.setAttribute("operation", "add");

		return "system/user/addUser.jsp";
	}
	
	
	
	
	
	/**
	 * 添加用户
	 * @param dto
	 * @param pwd
	 * @param pwd2
	 * @param roleCodes
	 * @return
	*/
	@RequestMapping(value="/addUser",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addUser(HttpServletRequest request,UserDTO dto,String pwd,String pwd2,String[] roleIds){
	
		this.userService.addUser(dto, pwd, pwd2,roleIds);
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	} 
	
	
	
	/**
	 * 查询用户详细信息
	 * @param request
	 * @param uid
	 * @param oper
	 * @return
	 */
	@RequestMapping(value="getDetail")
	public String loadUserDetail(HttpServletRequest request,@RequestParam(value="uid",required=true)String uid){
		
		UserDTO result = this.userService.getUserById(uid);	
		request.setAttribute("userItem", result);
		
		DataQuery dq = new DataQuery();
		dq.putToMap("roleType", Role.TYPE_BACK);
		
		List<RoleDTO> roleList = this.roleService.searchListByCondition(dq);
		List<RoleDTO> ownRoleList = this.roleService.selectRolesByUserId(result.getId());
		
		StringBuilder ownRoles = new StringBuilder();
		for (RoleDTO roleDTO : ownRoleList) {
			ownRoles.append(roleDTO.getRoleText() + ",");
		}
		
		if(ownRoles.length()>0){
			ownRoles.deleteCharAt(ownRoles.length()-1);
		}
		
		for (int i = 0; i < roleList.size(); i++) {
			for (int j = 0; j < ownRoleList.size(); j++) {
				if(roleList.get(i).getId().equals(ownRoleList.get(j).getId())){
					roleList.get(i).setCheck(true);
					break;
				}
			}
		}

		request.setAttribute("ownRoles", ownRoles.toString());	
		request.setAttribute("roleList", roleList);		
		
		dq.getQueryMap().put("dictType", BaseDict.USER_BACK_TYPE);
		dq.getQueryMap().put("dictFlag", true);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(dq);	
	    request.setAttribute("typeDicts", typeDicts);

		request.setAttribute("operation", "edit");

		
		return "system/user/editUser.jsp";
		
		
	
	}
	
	
	
	/**
	 * 修改用户信息
	 * @param dto
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value="/editUser",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateUser(UserDTO dto,String[] roleIds){

		this.userService.updateUser(dto,roleIds);
		
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
	/**
	 * 修改用户头像
	 * @param file
	 * @return
	 */
	@RequestMapping("/changeHeadImg")
	@ResponseBody
	public AjaxJson uploadFile(@RequestParam(value = "file") CommonsMultipartFile file) {

		this.userInfoService.changeHeadImg(getNowUser().getId(), file);
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}

	
	 
	/**
	 * 管理员修改密码
	 * @param uid
	 * @param pwd
	 * @param pwd2
	 * @return
	*/ 
	@RequestMapping(value="/changePassword",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson changePassword(@RequestParam(value="uid",required=true)String uid,String pwd,String pwd2){
		
		this.userService.changePassWordByAdmin(uid, pwd, pwd2);
		
		
		return new AjaxJson("修改密码成功！", AjaxJson.success);
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteUser",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson deelteUser(String[] ids){
		
		this.userService.deleteUser(ids);
		
		
		return new AjaxJson(this.DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	

  
	
	/**
	 * 停用或恢复账号
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/changeAccountEnable",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson changeAccountEnable(String userId){
				
		this.userService.changeAccountEnable(userId);
				
		return new AjaxJson(this.OPER_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	

	
	
	/**
	 * 加载当前用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="loadMyDetail")
	public String loadMyDetail(HttpServletRequest request,Integer model){
		
		UserDTO result = this.userService.getUserById(getNowUser().getId());	
		request.setAttribute("userItem", result);

		
		return "system/user/viewMyDetail.jsp";
		
		
	
	}
	
	
	@RequestMapping(value="/updateMyInfo",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateNowUserInfo(String chineseName,String phone,String email,String pwd,String pwd2){
		
		this.userService.updateNowUserInfo(chineseName,phone,email, pwd, pwd2);
				
		return new AjaxJson("修改成功！", AjaxJson.success);
	}
	
	
	
	
	@RequestMapping(value="/changePwd",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson changePwd(String oldPwd,String pwd,String pwd2){
		
		this.userService.updatePassWord(oldPwd, pwd, pwd2);
				
		return new AjaxJson("修改成功！", AjaxJson.success);
	}
	
	
	

	
	
	
	
	
}
