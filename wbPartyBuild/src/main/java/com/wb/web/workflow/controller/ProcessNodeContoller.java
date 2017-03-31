package com.wb.web.workflow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.exception.MyException;
import com.wb.web.system.dto.role.RoleDTO;
import com.wb.web.system.dto.role.RoleQueryDTO;
import com.wb.web.system.dto.user.UserIDAndNameDTO;
import com.wb.web.system.service.IRoleService;
import com.wb.web.system.service.IUserService;
import com.wb.web.workflow.dto.node.ProcessNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeQueryDTO;
import com.wb.web.workflow.dto.node.UpdateProcessNodeDTO;
import com.wb.web.workflow.entity.ProcessIdentitylink;
import com.wb.web.workflow.entity.ProcessNode;
import com.wb.web.workflow.service.IProcessDefinitionService;
import com.wb.web.workflow.service.IProcessIdentitylinkService;
import com.wb.web.workflow.service.IProcessNodeService;


@Controller
@Scope("prototype")
@RequestMapping("/procNode")
public class ProcessNodeContoller extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8350869609281346916L;
	
	@Resource
	private IProcessNodeService processNodeService;
	@Resource
	private IProcessDefinitionService processDefinitionService;
	@Resource
	private IRoleService roleService;
	@Resource 
	private IProcessIdentitylinkService processIdentitylinkService;
	@Resource
	private IUserService userService;
	
	/**
	 * 跳转查看分页查询
	 * 
	 * @param request
	 * @param sf
	 * @return
	 */
	@RequestMapping(value="/viewPage", method = { RequestMethod.GET})
	public String viewProcNodePage(HttpServletRequest request,@RequestParam(value = "id", required = true) Long id) {
		this.wrapMenuTitle(request);
		request.setAttribute("id", id);
		return "work-flow/node/nodeList.jsp";
	}
	
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "pageList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> loadProcNodeByPage(HttpServletRequest request,
			ProcessNodeQueryDTO queryDTO) {
		this.wrapTableQueryParams(request, queryDTO);
		Page<ProcessNodeDTO> pageResult = this.processNodeService.searchProcessNodeByCondition(queryDTO);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		return jsonMap;
	}
	
	/**
	 * 单击修改跳转页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getDetail",method={RequestMethod.GET})
	public String detailProcessNode(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id){
		this.wrapMenuTitle(request);
		
		ProcessNode processNode = processNodeService.getNodesById(id);
		List<ProcessIdentitylink> processIdentitylinks=processIdentitylinkService.getProcessIdentitylinksById(id);
		StringBuilder ownRoles = new StringBuilder();
		RoleQueryDTO rqd= new RoleQueryDTO();
		List<RoleDTO> roleList=this.roleService.searchListByCondition(rqd);
		List<UserIDAndNameDTO> userList=new ArrayList<UserIDAndNameDTO>();

		for (ProcessIdentitylink processIdentitylink : processIdentitylinks) {
			
			if (processIdentitylink.getGroupId()!=null) {
				RoleDTO ownRoleList = this.roleService.getRoleById(processIdentitylink.getGroupId());
				ownRoles.append(ownRoleList.getRoleText() + ",");
				for (int i = 0; i < roleList.size(); i++) {
						if(roleList.get(i).getId().equals(ownRoleList.getId())){
							roleList.get(i).setCheck(true);
							break;
						}
				}
			}else {
				UserIDAndNameDTO user= processIdentitylinkService.getAllEditer(processIdentitylink.getUserId());
				userList.add(user);
			}
		}
		if(ownRoles.length()>0){
			ownRoles.deleteCharAt(ownRoles.length()-1);
		}
		request.setAttribute("userList", userList);
		request.setAttribute("ownRoles", ownRoles.toString());		
		request.setAttribute("roleList", roleList);
		request.setAttribute("Dto", processNode);
		return "work-flow/node/editNode.jsp";
	}
	
	
	@RequestMapping(value="/editProcessNode",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateProcessNode(UpdateProcessNodeDTO dto, String[] roleIds,String[] userId){
		this.processNodeService.updateProcessNode(dto, roleIds, userId);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	/**
	 * 设计流程图
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/designProcess")
	public String designProcess(HttpServletRequest request,@RequestParam(required=true)Long definId){
		
  	
		JSONObject data = this.processNodeService.getProcessData(definId);
		if(data!=null){
			request.setAttribute("processData", data.toString());
		}
		
		request.setAttribute("procDefinIdId",definId);
		return "work-flow/design/model.jsp";
	}
	
	
	/**
	 * 获取流程进度图
	 * @param request
	 * @param definId
	 * @return
	 */
	@RequestMapping(value="/getCurrProcess")
	public String getCurrProcess(HttpServletRequest request,@RequestParam(required=true)Long procInstId){
		
		Long definId = this.processDefinitionService.getProcessDefiniIdByProcInstId(procInstId);
		JSONObject data = this.processNodeService.getProcessData(definId);
		if(data!=null){
			request.setAttribute("processData", data.toString());
			
			String nodeCode = this.processNodeService.getCurrExeNodeCode(procInstId);
			
			request.setAttribute("nodeCode", nodeCode);
		}else{
			throw new MyException("empty process");
		}
		
		return "work-flow/design/view-model.jsp";
	}
	
	
	@RequestMapping("/addProcess")
	@ResponseBody
	public AjaxJson addProcess(Long procDefinId,String data){
	
		this.processNodeService.addProcess(procDefinId, data);
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}

	
	
	/*@RequestMapping("/getProcessData")
	@ResponseBody
	public AjaxJson getProcessData(Long procDefinId){
		
		JSONObject result = this.processNodeService.getProcessData(procDefinId);
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success,result);
	}*/
}
