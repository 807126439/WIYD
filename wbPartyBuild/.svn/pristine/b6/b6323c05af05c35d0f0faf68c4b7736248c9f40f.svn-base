package com.wb.web.portals.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.utils.Assert;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.ColumnMuDTO;
import com.wb.web.portals.dto.content.ContentDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.content.HistoryContentDTO;
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.entity.ColumnMu;
import com.wb.web.portals.service.IColumnMuService;
import com.wb.web.portals.service.IContentService;
import com.wb.web.portals.service.IThemeActivityService;
import com.wb.web.system.dto.department.DepartmentDTO;
import com.wb.web.system.service.IDepartmentService;

@Controller
@Scope("prototype")
@RequestMapping("/contentController")
public class ContentController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8741994306682912365L;
	@Resource
	private IContentService contentService;
	@Resource
	private IColumnMuService columnMuService;
	@Resource
	private IThemeActivityService themeActivityService;
	@Resource
	private IDepartmentService departmentService;
	
	

	/**
	 * 跳转查看分页查询
	 * @param request
	 * @param columId  栏目id
	 * @param parId    上级文章id   
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewContenPage(HttpServletRequest request,Long columId,Long parId){
		
		
		if(columId!=null){//文章列表			
			ColumnMuDTO columnMuDTO = this.columnMuService.getColumnMuDTOById(columId);
			request.setAttribute("columId", columnMuDTO.getId());	
			request.setAttribute("typeId", columnMuDTO.getTypeId());	
		
			if(columnMuDTO.getTypeId() == ColumnMu.SINGAL_PAGE_TYPE){//栏目单页显示
													
					ContentDTO result = this.contentService.getContentDTOById(columId,true);
					
					if(result == null){
						Long totalNum = this.contentService.countTotalNum() + 1;
						
						request.setAttribute("colName", columnMuDTO.getTitle());
						request.setAttribute("sortNum", totalNum);
						
						return "portals/content/addContent.jsp";
					}else{
						request.setAttribute("contentItem", result);
						request.setAttribute("colName", result.getColumnName());
						request.setAttribute("sortNum", result.getSortNum());
					
						return "portals/content/editContent.jsp";
					}
					
			
				
				
			}else{
				
				return "portals/content/contentList.jsp";
			}
			
			
			
		}else if(parId!=null){//内页管理
			request.setAttribute("parId", parId);
			request.setAttribute("activityId",this.contentService.getContentDTOById(parId, false).getActivityId());
			return "portals/content/magazineImgList.jsp";
		}
		
	   throw new MyException("error request!");
		
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadContentByPage(HttpServletRequest request,ContentQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<ContentDTO> pageResult = this.contentService.searchEntityByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		jsonMap.put("totalPage", pageResult.getTotalPage());
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加栏目内容
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddContent(HttpServletRequest request,Long columId){
		if(columId == null){
			throw new MyException("colId must not be null");
		}
		
		Long totalNum = this.contentService.countTotalNum() + 1;
		ColumnMuDTO columnMuDTO = this.columnMuService.getColumnMuDTOById(columId);
		
		request.setAttribute("columId", columnMuDTO.getId());
		request.setAttribute("typeId", columnMuDTO.getTypeId());
		request.setAttribute("colName", columnMuDTO.getTitle());
		request.setAttribute("sortNum", totalNum);
				
		return "portals/content/addContent.jsp";
	}
	
	
	
	/**
	 * 添加栏目内容
	 * @param dto
	 * @param pwd
	 * @param pwd2
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value="/addContent",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addContent(ContentDTO dto,@RequestParam(required=false)CommonsMultipartFile file,String action){
	
		if("simple".equals(action)){
			this.contentService.addContent(dto, file);
			
		}else if("img".equals(action)){
			this.contentService.addImgContent(dto.getColumnId(), file);
		
		}else if("magazine".equals(action)){
			this.contentService.addMagazineContent(dto.getCtId(), file);
		
		}else{
			
			return new AjaxJson("It is error request!", AjaxJson.error);
		}
	
		
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	/**
	 * 查询栏目内容详细信息
	 * @param request
	 * @param uid
	 * @param oper
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.detail,method={RequestMethod.GET})
	public ModelAndView loadUserDetail(HttpServletRequest request,@RequestParam(value="cid",required=true)Long cid){
		
		ContentDTO result = this.contentService.getContentDTOById(cid,false);
		String seeOrgName=this.contentService.getSeeOrgName(cid);
		
		request.setAttribute("contentItem", result);
		request.setAttribute("seeOrgName", seeOrgName);
		request.setAttribute("columId", result.getColumnId());
		request.setAttribute("typeId", result.getTypeId());
		request.setAttribute("colName", result.getColumnName());
		request.setAttribute("sortNum", result.getSortNum());
	
			
		return new ModelAndView("portals/content/editContent.jsp");
		
	
	}
	
	/**
	 * 修改栏目内容信息
	 * @param dto
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value="/editContent",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateContent(ContentDTO dto,@RequestParam(required=false)CommonsMultipartFile file,
			String type){

		if(type!=null && type.equals("num")){
			this.contentService.updateContentSortNum(dto.getCtId(), dto.getSortNum());
			
		}else{
			this.contentService.updateContent(dto, file);
		}
		
		
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	@RequestMapping(value="delContent",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson delContent(Long[] ids){
		
		this.contentService.deleteContent(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(params=MyRequestParam.viewHistoryPage)
	public String viewHistoryContent(HttpServletRequest request){
		
				
		return "portals/content/historyContentList.jsp";
	}
	/**
	 * 跳转关联主题页面
	 */
	@RequestMapping(params="Connect",method={RequestMethod.GET})
	public ModelAndView skipConnectActivity(HttpServletRequest request,@RequestParam(value="parId",required=true)Long parId){
		List<ThemeActivityDTO> dtolist=this.themeActivityService.getThemeActivityByType((short) 2);
		request.setAttribute("parId", parId);
		request.setAttribute("themeActivityItem", dtolist);
		
		return new ModelAndView("portals/content/ConnectActivity.jsp");
	}
	/**
	 * 关联主题活动
	 * @return
	 */
	@RequestMapping(value="/ConnectActivity",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson connectActivity(ThemeActivityDTO dto,@RequestParam(value="parId",required=true)Long parId){
		ContentDTO cdto=this.contentService.getContentDTOById(parId, false);
		cdto.setActivityId(dto.getId());
		this.contentService.updateContent(cdto, null);
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	/**
	 * 保存板块与相关文章关系
	 * @param banChunkId
	 * @param contentId
	 * @return
	 */
	@RequestMapping(value="saveContent",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson saveContent(Long banChunkId,Long contentId){
		
		this.contentService.saveContent(banChunkId,contentId);
		return new AjaxJson(EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	/**
	 * 清除板块与内容关系
	 * @return
	 */
	@RequestMapping("/CutContentRelationship")
	@ResponseBody
	public AjaxJson cutContentRelationship(Long banChunkId){
		this.contentService.cutContentRelationship(banChunkId);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	/**
	 * 跳转添加查看部门权限
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(params="seeOrg",method={RequestMethod.GET})
	public ModelAndView skipAddSeeOrg(HttpServletRequest request,@RequestParam(value="seeOrgId",required=false)String seeOrgId){
		String nodes=null;
		String[] seeOrgIds=null; 
		if(null!=seeOrgId){
			seeOrgIds=seeOrgId.split(",");
		}
		nodes=this.departmentService.getZtreeNodesByContentId(seeOrgIds);
		request.setAttribute("nodes", nodes);
		
		return new ModelAndView("portals/content/seeOrg.jsp");
	}
	
	@RequestMapping("historyPageList")
	@ResponseBody
	public Map<String, Object> loadHistoryContentByPage(HttpServletRequest request,ContentQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<HistoryContentDTO> pageResult = this.contentService.searchHistoryContentByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		jsonMap.put("totalPage", pageResult.getTotalPage());
		
		return jsonMap;
	}
	
	
	

	@RequestMapping(value="recoverContent",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson recoverContent(Long[] ids,Long columId){
		
		this.contentService.updateRecoverContent(ids, columId);
		return new AjaxJson("恢复成功！", AjaxJson.success);
	}
	
	
	
	@RequestMapping(value="shiftDelContent",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson shiftDeleteContent(Long[] ids){
		
		this.contentService.deleteContentWithShift(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	@RequestMapping(value="changeColumn",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson changeColumn(Long[] ids,Long colId){
		
		this.contentService.updateContentColumn(ids, colId);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	/**
	 * 门户管理-手机端文章管理  主要为手机端显示进行设置
	 */
	@RequestMapping(value="contentList",method={RequestMethod.GET})
	public String skipContentList(HttpServletRequest request){
		this.wrapMenuTitle(request);
		request.setAttribute("appContentSize", this.contentService.getAPP_CONTENT_SIZE());
		return "portals/appContent/contentList.jsp";
	}
	/**
	 * 分页查询手机端显示文章
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("appPageList")
	@ResponseBody
	public Map<String, Object> getAppContent(HttpServletRequest request,ContentQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<ContentDTO> pageResult = this.contentService.searchAppEntityByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		jsonMap.put("totalPage", pageResult.getTotalPage());
		
		return jsonMap;
	}
	
	/**
	 * 保存手机端最大文章数量
	 */
	@RequestMapping(value="saveAppContentNum",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson saveAppContentNum(Integer contentNum){
		Assert.notNull(contentNum, "AppContentNum must not be null");
		this.contentService.saveAppContentNum(contentNum);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	/**
	 * 批量置顶手机端文章
	 */
	@RequestMapping(value="sumbitToTop",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson sumbitToTop(Long[] ids){
		
		this.contentService.sumbitToTop(ids);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	/**
	 * 批量取消置顶
	 */
	@RequestMapping(value="cancleToTop",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson cancleToTop(Long[] ids){
		
		this.contentService.cancleToTop(ids);
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
}
