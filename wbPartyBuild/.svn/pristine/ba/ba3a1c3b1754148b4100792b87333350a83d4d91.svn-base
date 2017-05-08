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
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.ads.AdvertisementDTO;
import com.wb.web.portals.dto.ads.AdvertisementQueryDTO;
import com.wb.web.portals.service.IAdvertisementService;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IBaseDictService;

@Controller
@Scope("prototype")
@RequestMapping("/advertisementController")
public class AdvertisementController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7567469276935018211L;
	@Resource
	private IAdvertisementService advertisementService;
	@Resource
	private IBaseDictService baseDictService;

	
	/**
	 * 跳转查看分页查询
	 * @param request
	 * @param sf 
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
	public String viewAdsPage(HttpServletRequest request){
		
		this.wrapMenuTitle(request);
				
		return "portals/ads/adsList.jsp";
		
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping("pageList")
	@ResponseBody
	public Map<String, Object> loadAdsByPage(HttpServletRequest request,AdvertisementQueryDTO queryDTO){
		
		this.wrapTableQueryParams(request, queryDTO);
		Page<AdvertisementDTO> pageResult = this.advertisementService.searchAdvertisementByPage(queryDTO);
		 
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("aaData", pageResult.getList());
		jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
		jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
		jsonMap.put("sEcho", queryDTO.getsEcho());
		
		
		return jsonMap;
	}
	
	
	/**
	 * 跳转到添加广告
	 * @param request
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.add,method={RequestMethod.GET})
	public String skipAddAds(HttpServletRequest request){
				
		Long totalNum = this.advertisementService.countTotalNum() + 1;	
		request.setAttribute("sortNum", totalNum);
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.ADS_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);	  
		
	    request.setAttribute("typeDict", typeDicts);
				
		return "portals/ads/addAds.jsp";
	}
	
	
	
	/**
	 * 添加广告
	 * @param dto
	 * @param pwd
	 * @param pwd2
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value="/addAds",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson addAds(AdvertisementDTO dto,@RequestParam(required=false)CommonsMultipartFile file,
			Integer isCompress){
		
		this.advertisementService.addAdvertisement(dto,file,isCompress);
			
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	/**
	 * 查询广告详细信息
	 * @param request
	 * @param uid
	 * @param oper
	 * @return
	 */
	@RequestMapping(params=MyRequestParam.detail,method={RequestMethod.GET})
	public ModelAndView loadAdsetail(HttpServletRequest request,@RequestParam(value="adsId",required=true)Long adsId){
		
		AdvertisementDTO result = this.advertisementService.getAdvertisementById(adsId);
		
		BaseDictQueryDTO bdqd = new BaseDictQueryDTO();
	    bdqd.setDictType(BaseDict.ADS_TYPE);
	    List<BaseDictDTO> typeDicts = this.baseDictService.searchListByCondition(bdqd);	  
		
	    request.setAttribute("typeDict", typeDicts);	    
		request.setAttribute("sortNum", result.getSortNum());
		request.setAttribute("adsItem", result);
			
		return new ModelAndView("portals/ads/editAds.jsp");
			
	}
	
	
	/**
	 * 修改广告信息
	 * @param dto
	 * @param roleCodes
	 * @return
	 */
	@RequestMapping(value="/editAds",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson updateAds(AdvertisementDTO dto,@RequestParam(required=false)CommonsMultipartFile file,Integer isCompress){

		
		this.advertisementService.updateAdvertisement(dto, file,isCompress);		
		
		return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
	
	
	
	
	@RequestMapping(value="delAds",method={RequestMethod.POST})
	@ResponseBody
	public AjaxJson delAds(Long[] ids){
		
		this.advertisementService.deleteAdvertisement(ids);
		return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
	}
	
	
}
