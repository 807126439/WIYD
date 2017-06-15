package com.wb.web.portals.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.DateUtil;
import com.wb.core.utils.PageUtil;
import com.wb.web.party.dto.JoinPartyInfoDTO;
import com.wb.web.party.dto.JoinPartyQueryDTO;
import com.wb.web.party.dto.JoinPartyStatsDTO;
import com.wb.web.party.service.IJoinPartyInfoService;
import com.wb.web.portals.dto.accessRecord.AccessRecordDTO;
import com.wb.web.portals.dto.awardWinningWorks.AwardMessage;
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.ColumnNavDTO;
import com.wb.web.portals.dto.column.IndexItem;
import com.wb.web.portals.dto.column.InnnerColumnDTO;
import com.wb.web.portals.dto.communication.CommunicationDTO;
import com.wb.web.portals.dto.communication.CommunicationQueryDTO;
import com.wb.web.portals.dto.communication.FiledDTO;
import com.wb.web.portals.dto.content.ContentDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.content.InnerContentDetailDTO;
import com.wb.web.portals.dto.content.InnerShowContentDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordDTO;
import com.wb.web.portals.dto.feelingRecord.FeelingRecordQueryDTO;
import com.wb.web.portals.dto.themeActivity.CityBuilderActivity;
import com.wb.web.portals.dto.themeActivity.PhotoActivity;
import com.wb.web.portals.dto.themeActivity.ThemeActivityDTO;
import com.wb.web.portals.entity.ColumnMu;
import com.wb.web.portals.service.IAccessRecordService;
import com.wb.web.portals.service.IAdvertisementService;
import com.wb.web.portals.service.IAwardWinningWorksService;
import com.wb.web.portals.service.IBanChunkService;
import com.wb.web.portals.service.IColumnMuService;
import com.wb.web.portals.service.ICommentService;
import com.wb.web.portals.service.ICommunicationService;
import com.wb.web.portals.service.IContentService;
import com.wb.web.portals.service.IContentStatService;
import com.wb.web.portals.service.IFeelingRecordService;
import com.wb.web.portals.service.IManuscriptService;
import com.wb.web.portals.service.IThemeActivityService;
import com.wb.web.study.dto.examPaper.OnlineTestDTO;
import com.wb.web.study.dto.studyData.StudyDataDTO;
import com.wb.web.study.dto.studyData.StudyDataQueryDTO;
import com.wb.web.study.dto.studyTask.StudyTaskDTO;
import com.wb.web.study.dto.studyTask.StudyTaskQueryDTO;
import com.wb.web.study.service.IExamPaperService;
import com.wb.web.study.service.IStudyDataService;
import com.wb.web.study.service.IStudyTaskService;
import com.wb.web.system.dto.department.DepartmentDTO2;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.service.IDepartmentService;
import com.wb.web.system.service.ILoginLogService;
import com.wb.web.system.service.IUserService;
import com.wb.web.system.service.IZonePathService;


@Controller
@Scope("prototype")
public class IndexController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3450480742411563877L;
	@Resource
	private IContentService contentService;
	@Resource
	private IColumnMuService columnMuService;
	@Resource
	private IAdvertisementService advertisementService;
	@Resource
	private ICommunicationService communicationService;
	@Resource
	private ICommentService commentService; 
	@Resource
	private IUserService userService;	
	@Resource
	private IThemeActivityService themeActivityService;
	@Resource
	private IManuscriptService manuscriptService;

	@Resource
	private IBanChunkService banChunkService;
	@Resource
	private IAwardWinningWorksService  awardWinningWorksService;
	@Resource
	private IDepartmentService departmentService;
	@Resource
	private IStudyTaskService studyTaskService;
	@Resource
	private ILoginLogService loginLogService;
	@Resource
	private IAccessRecordService accessRecordService;
	@Resource
	private IStudyDataService studyDataService;
	@Resource
	private IZonePathService zonePathService;
	@Resource
	private IExamPaperService examPaperService;
	@Resource
	private IJoinPartyInfoService joinPartyInfoService;
	@Resource
	private IFeelingRecordService feelingRecordService;
	@Resource
	private IContentStatService contentStatService;
	
	private static List<Object> adsCache = new ArrayList<Object>();
	
	/**
	 * 后台首页
	 * @param request
	 * @return
	 */
	@RequestMapping("index")
	public String login(HttpServletRequest request){
		return "index/index.jsp";
	}
		

	/**
	 * 网站首页
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/portals",method=RequestMethod.GET)
	public String portals(HttpServletRequest req){
		List<ColumnHead> heads = this.columnMuService.searchIndexHead();
		List<IndexItem> result =  this.contentService.searchContentForIndex();
		List<Object> ads = this.advertisementService.searchAdsForIndex();
		
		if(!ads.isEmpty()){
			adsCache = ads;
		}
		
		
		//登录统计
		AccessRecordDTO ardto=this.accessRecordService.getLastRecord();
		Long todayaccess=this.loginLogService.searchCountsAfterDate(ardto.getLastOperatorTime());
		req.setAttribute("totalAccess", ardto.getTotalAccess()+todayaccess);
		req.setAttribute("todayAccess", todayaccess);
		
		req.setAttribute("head", heads);
		req.setAttribute("result", result);		
		req.setAttribute("path",req.getContextPath());
		
		commonSetAttr(req);
		
	 	return "index/index.ftl";	
	}
	

	
	/**
	 * 注释三
	 * 可用于自动生成文档
	 *
	 */
	

	/**
	 * 内页文章列表显示
	 * @param id		栏目id
	 * @param curPage   显示栏目文章列表的当前页
	 * @return
	 */

	@RequestMapping(value="/subject/{id}.htm",method=RequestMethod.GET)
	public String innnerItem(@PathVariable Long id,HttpServletRequest req){
		if(id == null){
			throw new MyException("Can not find the record!");
		}
		List<ColumnHead> heads = this.columnMuService.searchIndexHead();
		InnnerColumnDTO result = this.columnMuService.searchForInnerShow(id,1);

		req.setAttribute("head", heads);
		req.setAttribute("result", result);
		req.setAttribute("path", req.getContextPath());
		
		commonSetAttr(req);
				
		if(result.getCurrColumn().getTypeId() ==  ColumnMu.IMG_LIST_TYPE || result.getCurrColumn().getTypeId() ==  ColumnMu.VIDEO_LIST_TYPE 
				|| result.getCurrColumn().getTypeId() ==  ColumnMu.MAGAZINE_TYPE){
			
			return "activity/piclist.ftl";
		
		}else{
			return "activity/activity.ftl";
		}		
		
	}
	
	
	/**
	 * 文章列表分页
	 * @param id
	 * @param curPage
	 * @return
	 */
	
	@RequestMapping(value="/subject/getContList")
	@ResponseBody
	public Page<InnerShowContentDTO> getContent(Long id,String title,Integer curPage){
		return  this.contentService.searchContentIndex(id,title,curPage,15,null);	
	}
	
	/**
	 * 学习任务列表分页
	 */
	@RequestMapping(value="/studyTask/getStudyTaskPage")
	@ResponseBody
	public Page<StudyTaskDTO> getStudyTaskPage(Integer curPage){
		
		StudyTaskQueryDTO queryDto=new StudyTaskQueryDTO();
		queryDto.setCurrentPage(curPage);
		queryDto.setPageSize(15);
		queryDto.initStartQueryFromCurPage();
		Page<StudyTaskDTO> dtoPage=this.studyTaskService.searchStudyTaskPage(queryDto);
		//判断学习任务List中用户完成了哪些
		this.studyTaskService.checkIfFinished(dtoPage.getList(),this.getNowUser().getId());
		
		return  dtoPage;	

	}
	/**
	 * 学习资料列表分页
	 */
	@RequestMapping(value="/studyData/getStudyDataPage")
	@ResponseBody
	public Page<StudyDataDTO> getStudyDataPage(Integer curPage){
		
		StudyDataQueryDTO queryDto=new StudyDataQueryDTO();
		queryDto.setCurrentPage(curPage);
		queryDto.setPageSize(15);
		queryDto.initStartQueryFromCurPage();
		Page<StudyDataDTO> dtoPage=this.studyDataService.searchStudyDataPage(queryDto);
		return  dtoPage;	

	}

	
	/**
	 * 心得体会列表分页
	 */
	@RequestMapping(value="/feelingRecord/getExperiencePage")
	@ResponseBody
	public Page<FeelingRecordDTO> getExperiencePage(Integer curPage){
		FeelingRecordQueryDTO queryDto=new FeelingRecordQueryDTO();
		queryDto.setCurrentPage(curPage);
		queryDto.setPageSize(5);
		queryDto.setOwnerId(this.getNowUser().getId());
		queryDto.initStartQueryFromCurPage();
		Page<FeelingRecordDTO> dtoPage=this.feelingRecordService.searchFeelingRecordByPage(queryDto);
		return dtoPage;	
	}


	/**
	 * 文章详细内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/article/{id}.htm")
	public String contentDetail(@PathVariable Long id,HttpServletRequest req){
		if(id == null){
			throw new MyException("Can not find the record！");
		}	
		
		List<ColumnHead> heads = this.columnMuService.searchIndexHead();
		InnerContentDetailDTO result = this.contentService.getInnerContentDetailById(id);
		
		req.setAttribute("head", heads);
		req.setAttribute("path",req.getContextPath());
		commonSetAttr(req);
		//进行查看权限判断
		if(null!=result.getSeeOrgId()&&result.getSeeOrgId()!=""){
			//未登录跳转登录
			if(!this.checkWheatherLogined()){
				return "../login.jsp";
			}
			
			if(!getNowUser().checkOrgsIsExist(result.getSeeOrgId())){
				//无权限
				ContentDTO cdto = this.contentService.getContentDTOById(id, false);
				InnnerColumnDTO result2 = this.columnMuService.searchForInnerShow(cdto.getColumnId(),1);
				req.setAttribute("result", result2);
					
				return "activity/noAuthority.ftl";
			}
		}
		
		req.setAttribute("result", result);
		
		//对登录用户进行阅读文章统计
		if(this.checkWheatherLogined()){
			this.contentStatService.addRecord(this.getNowUser().getId(),id);
		}
		
		if(result.getTypeId()!=null && result.getTypeId() == ColumnMu.IMG_LIST_TYPE){
			return "activity/pic2.ftl";	
			
		}else if(result.getTypeId()!=null && result.getTypeId() == ColumnMu.VIDEO_LIST_TYPE){	
			req.setAttribute("mPath", result.getViewPath());
			return "activity/movie.ftl";
			
		}else if(result.getTypeId()!=null && result.getTypeId() == ColumnMu.MAGAZINE_TYPE){
			req.setAttribute("imgSize", result.getImgList().size());
			return "activity/cityBuilder2.ftl";		
			
		}else{
			return "article/article.ftl";
		}
		
		
	}
	
	

	  

	  
	  //我要评论
	  @RequestMapping(value="/issue/{comId}.htm")
	  public String issue(@PathVariable Long comId,HttpServletRequest req) 
			  throws Exception {   
	   List<ColumnHead> heads = this.columnMuService.searchIndexHead();
	   
	   CommunicationDTO result  = this.communicationService.getCurent(comId);
	   if(result!=null){
		   req.setAttribute("result",result);
	       Integer totalPage = PageUtil.getTotalPage(this.commentService.countTotalSize(comId), 6);
	       req.setAttribute("totalPage",totalPage);
	   }  
	   	
	   req.setAttribute("head", heads);
       req.setAttribute("path",req.getSession().getServletContext().getContextPath());
       commonSetAttr(req);
       
       return "activity/communication/issue.ftl";         
   }  
	  
	  
	
	 
	  
	  //简介
	  @RequestMapping(value="introduction")
	  public String introduction(HttpServletRequest req) 
			  throws Exception {   
	   List<ColumnHead> heads = this.columnMuService.searchIndexHead();

	   req.setAttribute("head", heads);
       req.setAttribute("path",req.getSession().getServletContext().getContextPath());
       commonSetAttr(req);
       
       return "activity/orgStructure/introduction.ftl";         
   }  
	  
	  
	 
		  
	  
	  
	  //图片列表
	  @RequestMapping(value="piclist")
	  public String piclist(HttpServletRequest req) 
			  throws Exception {   
	   List<ColumnHead> heads = this.columnMuService.searchIndexHead();
	   
	   req.setAttribute("head", heads);
       req.setAttribute("path",req.getSession().getServletContext().getContextPath());
       commonSetAttr(req);
       
       return "activity/piclist.ftl";         
   }  
	  
	  
	  
	  
	  //互动交流-列表点击
	  @RequestMapping(value="/communication/{type}/{comId}.htm")
	  public String filed(@PathVariable Long type,@PathVariable Long comId,HttpServletRequest req) 
			  throws Exception {   
	   List<ColumnHead> heads = this.columnMuService.searchIndexHead();
	   req.setAttribute("head", heads);
       req.setAttribute("path",req.getSession().getServletContext().getContextPath());
       commonSetAttr(req);  
       //活跃议题
	   if(type == 1){		   
		  CommunicationDTO result  = this.communicationService.getCurent(comId);
          req.setAttribute("result",result);      
          
          CommunicationQueryDTO queryDTO = new CommunicationQueryDTO();          
          queryDTO.setStatus((short)1);       
          List<CommunicationDTO> currentList=this.communicationService.getListByCondition(queryDTO);
          
		  req.setAttribute("currentList",currentList);
          return "activity/communication/communication.ftl";      		  
	   }else{
		   //归档议题
		   FiledDTO filed = this.communicationService.getFiledByid(comId);
		   req.setAttribute("filed", filed); 
	   } 	   
	   return "activity/communication/filed.ftl";      
		      
   }  
	  

	  
	  //意见反馈--跳转建议征集
	  @RequestMapping(value="opinionManage")
	  public String opinionFeedbackManage(HttpServletRequest req) 
			  throws Exception {   
	   List<ColumnHead> heads = this.columnMuService.searchIndexHead();
	   PhotoActivity result = this.themeActivityService.getNowPhotoActivity();	   
	   if(result!=null){	   
		   req.setAttribute("result", result);
	   }	   
  	   req.setAttribute("head", heads);
       req.setAttribute("path",req.getSession().getServletContext().getContextPath());
       commonSetAttr(req);
       
       return "activity/reasonableAdvise.ftl";         
   }   
	  

	  
	  
	  //月月精彩活动页面
	  @RequestMapping(value="activePhotoActivity")
	  public String activePhotoActivity(HttpServletRequest req)  throws Exception {   	
		  
	       PhotoActivity result = this.themeActivityService.getNowPhotoActivity();		       
		   if(result!=null){	   	   			  
		       commonSetAttr(req);      
		   	   req.setAttribute("path",req.getSession().getServletContext().getContextPath());
		   	   req.setAttribute("result", result);
		       return "activity/contribution/activePhoto.ftl";   
		   }else{
			   throw new MyException("暂无摄影活动");			   
		   }
		   
	   }  
	  
	  
	  //上期月月精彩活动页面
	  @RequestMapping(value="lastPhotoActivity")
	  public String lastPhotoActivity(HttpServletRequest req,Long nowId)  throws Exception {   	
		  
	       PhotoActivity result = this.themeActivityService.getLastPhotoActivity(nowId);	       
		   if(result!=null){	   
			   req.setAttribute("result", result);	   			  
			   commonSetAttr(req);      
			   req.setAttribute("path",req.getSession().getServletContext().getContextPath());
		       return "activity/contribution/activePhoto.ftl";   
		   }else{
			   throw new MyException("暂无摄影活动");			   
		   }       
	   } 
	  
	  
	  //获奖作品页面
	  @RequestMapping(value="adwardMessage")
	  public String adwardMessage(HttpServletRequest req,Long activityId)  throws Exception {   	
		  
		   AwardMessage result = this.awardWinningWorksService.getAwardMessage(activityId);	       	       
	       req.setAttribute("result", result);
	       commonSetAttr(req);      
	       req.setAttribute("activityId",activityId);
		   req.setAttribute("path",req.getSession().getServletContext().getContextPath());
	       return "activity/contribution/awardMessage.ftl";   	       	      
	   } 
	  
	  
	  
	/**
	 * 自定义内页跳转
	 * @param id
	 * @param name
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/{name}/{id}.htm",method=RequestMethod.GET)
	public String customInnerItem(@PathVariable Long id,@PathVariable String name,String depId,Long taskId,Long studyDataId,Long testPaperId,
			Long eassayId,Integer curPage,String type,HttpServletRequest req){
		if(id == null || StringUtils.isBlank(name)){
			throw new MyException("Can not find the record!");
		}
		List<ColumnHead> heads = this.columnMuService.searchIndexHead();
		InnnerColumnDTO result = this.columnMuService.searchForInnerShow(id,1);
				
		req.setAttribute("head", heads);
		req.setAttribute("result", result);
		req.setAttribute("path", req.getContextPath());
		commonSetAttr(req);	

		if(name.equals("monthlyPhoto")){			
			   PhotoActivity photoActivity = this.themeActivityService.getNowPhotoActivity();	   
			   if(photoActivity!=null){	   
				   req.setAttribute("photoActivity", photoActivity);
			   }	   
		       name =  "contribution/"+name;         
			
		}
		else if(name.equals("introduction")){
			name="orgStructure/"+name;
		}
		//--------------------------组织架构获取结构树由页面渲染------------------------
		else if(name.equals("orgStructure")){
			name="orgStructure/orgStructure2";
			String departmentTree=this.departmentService.getDepartmentJsonTreeById(depId);
			req.setAttribute("departmentTree", departmentTree);
			System.out.print(departmentTree);
			
		}
		else if(name.equals("partyMemberList")||name.equals("union")||name.equals("communist")||name.equals("womenList")){
			DepartmentDTO2 dto=this.departmentService.getDepartmentById2(depId);
			if(dto == null){
				return "activity/common/empty-activity.ftl";
			}
			req.setAttribute("department", dto);
			name="orgStructure/"+name;
			
			
		}
		else if(name.equals("studyTaskList")){
			if(this.checkWheatherLogined()){
				StudyTaskQueryDTO queryDto=new StudyTaskQueryDTO();
				queryDto.setPageSize(15);
				queryDto.initStartQueryFromCurPage();
				Page<StudyTaskDTO> dtoPage=this.studyTaskService.searchStudyTaskPage(queryDto);
				//判断学习任务List中用户完成了哪些
				this.studyTaskService.checkIfFinished(dtoPage.getList(),this.getNowUser().getId());
				req.setAttribute("Page", dtoPage);
			}else {
				return "../login.jsp";
			}
		}
		else if(name.equals("studyTask")){
			StudyTaskDTO dto=this.studyTaskService.getStudyTaskByIdUseBaseDao(taskId);
			req.setAttribute("StudyTask", dto);
			req.setAttribute("columnId", id);
		}
		
		else if (name.equals("LearningVideoList")) {
			Assert.hasText(type, "type param could not be null");
				StudyDataQueryDTO queryDto=new StudyDataQueryDTO();
				if(type.equals("video")){
					queryDto.setIsVideo(true);
				}else if(type.equals("other")){
					queryDto.setIsVideo(false);
				}
				
				queryDto.setPageSize(15);
				queryDto.initStartQueryFromCurPage();
				Page<StudyDataDTO> dtoPage=this.studyDataService.searchStudyDataPage(queryDto);
				req.setAttribute("Page", dtoPage);
			
		}
		else if (name.equals("LearningVideo")) {
			Map<Long, String> cache = new HashMap<Long, String>();
			StudyDataDTO studyDataDTO= this.studyDataService.getFileStudyDataById(studyDataId);
			
			
			if (studyDataDTO.getZonePathId()!=null) {
				String prefixPath = null;
				ZonePathDTO zp = this.zonePathService.getZonePathById(studyDataDTO.getZonePathId());
				cache.put(studyDataDTO.getZonePathId(), zp.getVirtualPath());
				prefixPath = zp.getVirtualPath();
				studyDataDTO.setPattern(prefixPath + studyDataDTO.getPattern());
				req.setAttribute("mPath", studyDataDTO.getPattern());
				req.setAttribute("sdDTO", studyDataDTO);
			}
			
		}
		else if(name.equals("communicationList")){
			CommunicationQueryDTO queryDTO=new CommunicationQueryDTO();
			queryDTO.setCurrentPage(1);
			queryDTO.setPageSize(15);
			queryDTO.initStartQueryFromCurPage();
			Page<CommunicationDTO> dtoPage=this.communicationService.searchCommunicationByPage(queryDTO);
			req.setAttribute("Page",dtoPage);
			name="communication/"+name;				
		}
		
		else if(name.equals("conCityBuilder")){						  
			  CityBuilderActivity cityBuilder=new CityBuilderActivity();			  
			  //查询当前征稿的城建人征稿活动
			  ThemeActivityDTO tadto=this.themeActivityService.getNowCityBuilderActivity();
			  cityBuilder.setActivity(tadto);			  
			  //查询当前征稿活动的征稿板块
			  List<BanChunkDTO> banChunkList=this.banChunkService.getSolicitationBanChunk(tadto.getId());			  
			  cityBuilder.setBanChunkList(banChunkList);
			  if(result!=null){			  
				  req.setAttribute("cityBuilder", cityBuilder);
				  commonSetAttr(req);
			      name = "contribution/cityBuilder";			      
			  }else{
				   throw new MyException("暂无征稿活动");			   
			  } 						
		}
		else if(name.equals("themeEassayList")){
			req.setAttribute("columnId", id);
			req.setAttribute("totalPage",PageUtil.getTotalPage(this.themeActivityService.countListSize(3, 1), 12));
			name="contribution/"+name;

		}	
		else if(name.equals("personJoinPartyInfo")){//个人入党查询
			name="party/"+name;
			
			JoinPartyInfoDTO info =  this.joinPartyInfoService.getJoinProcessByUserId(getNowUser().getId());
			req.setAttribute("info", info);
			
		}
		else if(name.equals("statsJoinPartyInfo")){//入党信息统计
			name="party/"+name;
			
			Page<JoinPartyStatsDTO> pageResult = this.joinPartyInfoService.searchStatsByPage(new JoinPartyQueryDTO());
			req.setAttribute("page", pageResult);
		}
		else if(name.equals("experienceList")){//心得体会
			
			FeelingRecordQueryDTO queryDTO=new FeelingRecordQueryDTO();
			queryDTO.setPageSize(5);
			queryDTO.setOwnerId(this.getNowUser().getId());
			queryDTO.initStartQueryFromCurPage();
			Page<FeelingRecordDTO>  pageResult = this.feelingRecordService.searchFeelingRecordByPage(queryDTO);
			req.setAttribute("pageResult", pageResult);
		}
		else if(name.equals("onlineTest")){//心得体会
			
			StudyTaskDTO task = this.studyTaskService.getStudyTaskById(testPaperId);
			if(task!=null){
				OnlineTestDTO testDTO = this.examPaperService.getOnlinePaperById(task.getPaperid());
				req.setAttribute("testDTO", testDTO);
				req.setAttribute("taskId", task.getStId());
			}			
			
		}else if(name.equals("themeEassay")){
			name="contribution/"+name;
			ThemeActivityDTO dto = this.themeActivityService.getBySql(eassayId);				
			if(dto!=null){
				req.setAttribute("eassayActivity", dto);
			}			
			
		}
		
		
		return "activity/"+name+".ftl";
	}
	
	
	/**
	 * 页面头部查询条，先查询再跳转页面
	 * @param req
	 */
	@RequestMapping(value="goSearch",method=RequestMethod.POST)
	public String SearchColmnMu(HttpServletRequest request,ContentQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		//页面固定每页显示为20条
		queryDTO.setPageSize(20);
		
		
		request.setAttribute("title", queryDTO.getTitle());
		List<ColumnHead> heads = this.columnMuService.searchIndexHead();
		request.setAttribute("head", heads);
		request.setAttribute("path",request.getSession().getServletContext().getContextPath());
	    commonSetAttr(request);
		
		//查找有关键字的内容
		if("column".equals(queryDTO.getSearchType())){
			Page<ColumnHead> columns=this.columnMuService.searchColumnHeadByCondition(queryDTO);
			request.setAttribute("result", columns);
			return "activity/searchColumn.ftl";
		}else{
			Page<InnerShowContentDTO> pageResult = this.contentService.searchContentIndex(null, queryDTO.getTitle(), queryDTO.getCurrentPage(), queryDTO.getPageSize(),queryDTO.getDays());
			request.setAttribute("result", pageResult);
			return "activity/searchArtical.ftl";
		}
	}
	
	/**
	 * 查询文章内容的异步请求
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public AjaxJson searchContent(String title,Integer days,Integer curPage){
		Page<InnerShowContentDTO> idto=this.contentService.searchContentIndex(null,title,curPage,20,days);
		AjaxJson result=new AjaxJson("查询成功",AjaxJson.success,idto);
		return result;
	}
	
	/**
	 * 查询栏目列表的异步请求
	 * @return
	 */
	@RequestMapping("/searchColumn")
	@ResponseBody
	public AjaxJson searchColumn(String title,Integer curPage){
		ContentQueryDTO queryDTO=new ContentQueryDTO();
		//页面固定每页显示为20条
		queryDTO.setPageSize(20);
		
		queryDTO.setCurrentPage(curPage);
		queryDTO.setTitle(title);
		
		Page<ColumnHead> idto=this.columnMuService.searchColumnHeadByCondition(queryDTO);
		AjaxJson result=new AjaxJson("查询成功",AjaxJson.success,idto);
		return result;
	}

	
	
	/**
	 * 共有的属性设置
	 * @param req
	 */
	private void commonSetAttr(HttpServletRequest req) {
		if(this.checkWheatherLogined()){
			req.setAttribute("isLogined", true);
			req.setAttribute("name", this.getNowUser().getChineseName());
			
		}else{
			req.setAttribute("isLogined", false);
		}
	
		req.setAttribute("ads", adsCache);
		req.setAttribute("today", DateUtil.Date2ChineseStr(new Date()));
	}
	  
	  
	
	/**
	 * 网页导航列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/webSiteNav")
	public String websitenav(HttpServletRequest request){
		
		List<ColumnNavDTO> result = this.columnMuService.getAllColumnNav();
		request.setAttribute("data", result);		
		return "index/websitenav.ftl";
	}
	  
	  
	  
}
