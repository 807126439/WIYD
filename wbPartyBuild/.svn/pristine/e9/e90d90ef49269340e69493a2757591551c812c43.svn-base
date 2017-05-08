package com.wb.web.portals.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.column.ColumnMuDTO;
import com.wb.web.portals.dto.column.ColumnZtreeDTO;
import com.wb.web.portals.service.IColumnMuService;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.dto.user.UserDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IBaseDictService;


@Controller
@Scope("prototype")
@RequestMapping("/columnMuController")
public class ColumnMuController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3843515177884150852L;
	@Resource
	private IColumnMuService columnMuService;
	@Resource
	private IBaseDictService baseDictService;
	
	
	@RequestMapping(value="getAllColumnMus",method={RequestMethod.GET})
	public String viewAllColumnMu(HttpServletRequest request){	
		
		this.wrapMenuTitle(request);
		List<ColumnMuDTO> resultList = this.columnMuService.getAllColumnByLevel();
		request.setAttribute("lanMuList", resultList);
		
	
			 
		return "portals/columnMu/columnMuList.jsp";
	}
	
	
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddColumnMu(HttpServletRequest request,Long par){	
		
		List<ColumnMuDTO> resultList = this.columnMuService.getAllParentEntity(null);
		Long totalNum = this.columnMuService.countTotalNum() + 1;
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.LANMU_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);	  
	    
	    bdqd.setDictType(BaseDict.CONTENT_MAX_SIZE_TYPE);
	    List<BaseDictDTO> sizeDicts = this.baseDictService.searchListByCondition(bdqd);	
	    
	   	    
	    request.setAttribute("typeDicts", typeDicts);
	    request.setAttribute("sizeDicts", sizeDicts);   
		request.setAttribute("lanMuList", resultList);
		request.setAttribute("par", par);
		request.setAttribute("sortNum", totalNum);

		
			 
		return "portals/columnMu/addColumnMu.jsp";
	}
	
	
	@RequestMapping(params=MyRequestParam.detail,method={RequestMethod.GET})
	public String loadColumnMuDetail(HttpServletRequest request,@RequestParam(required=true)Long id){	
		
		ColumnMuDTO dto = this.columnMuService.getColumnMuDTOById(id);
		
		List<ColumnMuDTO> resultList = this.columnMuService.getAllParentEntity(id);
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.LANMU_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);	    
	   	
	    bdqd.setDictType(BaseDict.CONTENT_MAX_SIZE_TYPE);
	    List<BaseDictDTO> sizeDicts = this.baseDictService.searchListByCondition(bdqd);	
	    
	    bdqd.setDictType(BaseDict.COLUMN_SHOW_TYPE);
	    List<BaseDictDTO> showTypeDicts = this.baseDictService.searchListByCondition(bdqd);	   
	    
	    request.setAttribute("colMuItem", dto);
	    request.setAttribute("typeDicts", typeDicts);
	    request.setAttribute("sizeDicts", sizeDicts);
	    request.setAttribute("showTypeDicts", showTypeDicts);
		request.setAttribute("lanMuList", resultList);
		request.setAttribute("par", dto.getParentId());
		request.setAttribute("sortNum", dto.getSortNum());

			 
		return "portals/columnMu/editColumnMu.jsp";
	}
	/**
	 * 跳转添加栏目编辑者权限
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params="skipAddEditer",method={RequestMethod.GET})
	public String skipAddColumnMuEditer(HttpServletRequest request,@RequestParam(required=true)Long id){
		
		List<UserDTO> userList=new ArrayList<UserDTO>();
		userList=this.columnMuService.getAllEditer(id);
		
		request.setAttribute("id", id);
		request.setAttribute("userList", userList);
		return "portals/columnMu/addEditer.jsp";
	}
	/**
	 * 确认具有栏目编辑者权限的用户
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="addEditer",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addEditer(Long id,String[] userId){
		this.columnMuService.saveAllowUserId(id,userId);
		return new AjaxJson(ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	@RequestMapping(value="addColumnMu",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addColumnMu(ColumnMuDTO dto){
		
		this.columnMuService.addColmnMu(dto);
		return new AjaxJson(ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="moveColumnMu",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson moveColumnMu(Long colId,Long parId){
		
		this.columnMuService.updateParentColumn(colId, parId);
		return new AjaxJson(EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	@RequestMapping(value="editColumnMu",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson editColumnMu(ColumnMuDTO dto){
		
		this.columnMuService.updateColmnMu(dto);
		return new AjaxJson(EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="delColumnMu",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson delColumnMu(Long[] ids){
		
		this.columnMuService.deleteColmnMu(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	@RequestMapping(value="getColumTreeNode")
	@ResponseBody
	public List<ColumnZtreeDTO> getAllColumsTree(){
		
		return this.columnMuService.getAllEntityTransformTree();
	}

}
