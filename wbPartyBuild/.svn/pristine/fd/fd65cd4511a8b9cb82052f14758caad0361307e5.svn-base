package com.wb.web.portals.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wb.core.common.bean.Page;
import com.wb.core.common.controller.BaseController;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.spring.security.gobal.MyRequestParam;
import com.wb.web.portals.dto.comment.CommentDTO;
import com.wb.web.portals.dto.comment.CommentQueryDTO;
import com.wb.web.portals.dto.comment.CountCommentDTO;
import com.wb.web.portals.service.ICommentService;


@Controller
@Scope("prototype")
@RequestMapping("/commentController")
public class CommentController  extends BaseController{

		@Resource
		private ICommentService commentService;
	
	
		private static final long serialVersionUID = -8311309487668094326L;
		
		@RequestMapping(params=MyRequestParam.viewPage,method={RequestMethod.GET})
		public String viewCommentPage(HttpServletRequest request,Long comId){		
			this.wrapMenuTitle(request);
			request.setAttribute("comId", comId);
			return "portals/comment/commentList.jsp";
			
		}

		@RequestMapping(value="/addComment",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson addComment(CommentDTO dto){
			this.commentService.addComment(dto);
			
			return new AjaxJson(this.ADD_SUCCESS_MESSAGE, AjaxJson.success);
		}
		
		
	
		@RequestMapping("pageList")
		@ResponseBody
		public Map<String, Object> loadCommentByPage(HttpServletRequest request,CommentQueryDTO queryDTO){
			
			this.wrapTableQueryParams(request, queryDTO);
			Page<CommentDTO> pageResult = this.commentService.searchCommentByPage(queryDTO);
			 
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("aaData", pageResult.getList());
			jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
			jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
			jsonMap.put("sEcho", queryDTO.getsEcho());
			
			
			return jsonMap;
		}
		
		
		@RequestMapping(value="deleteComment",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson delComment(Long[] ids){			
			this.commentService.deleteComment(ids);
			return new AjaxJson(DEL_SUCCESS_MESSAGE, AjaxJson.success);
		}	
		 	 	
	
		//分页查询一级评论
		@RequestMapping(value="getAllComment",method={RequestMethod.POST})
		@ResponseBody
		public Page<CommentDTO> getAllComment(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove){				
			Page<CommentDTO> result = this.commentService.getAllComment(comId,curPage,pageSize,byTime,byLove);
			return  result;				
		}	
			
		
		@RequestMapping(params=MyRequestParam.edit,method={RequestMethod.GET})
		public ModelAndView loadCommentDetail(HttpServletRequest request,@RequestParam(value="id",required=true)Long id){			
			CommentDTO result = this.commentService.getCommentById(id);
			request.setAttribute("result", result);		    
			return new ModelAndView("portals/comment/editComment.jsp");
				
		}
		
			
		@RequestMapping(value="/editComment",method={RequestMethod.POST})
		@ResponseBody
		public AjaxJson updateComment(CommentDTO dto){						
			this.commentService.updateComment(dto);		
			return new AjaxJson(this.EDIT_SUCCESS_MESSAGE, AjaxJson.success);
		}
		
		
		@RequestMapping(value="/countComment",method={RequestMethod.POST})
		@ResponseBody
		public Integer countComment(@RequestParam(value="comId",required=true)Long id,@RequestParam(value="type",required=true)Integer type){						
			return this.commentService.conutComment(id,type);
		}
		
		
		//app分页查询一级评论
		@RequestMapping(value="getAllParentCommentApp",method={RequestMethod.POST})
		@ResponseBody
		public Page<CommentDTO> getAllParentCommentApp(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove){				
			Page<CommentDTO> result = this.commentService.getAllParentCommentApp(comId,curPage,pageSize,byTime,byLove);
			return  result;				
		}	
		
		
		//app查询二级评论
		@RequestMapping(value="getAllChildCommentApp",method={RequestMethod.POST})
		@ResponseBody
		public List<CommentDTO> getAllChildCommentApp(Long parentId){				
			List<CommentDTO> result = this.commentService.getAllChildComment(parentId);
			return  result;				
		}	
		
		
		//跳转评论数统计页面
		@RequestMapping(value="skipCountComment",method={RequestMethod.GET})
		public String skipCountComment(HttpServletRequest request){		
			this.wrapMenuTitle(request);
			return "portals/comment/countComment.jsp";
			
		}
		
		
		
		//分页查询评论数统计
		@RequestMapping("queryCountComment")
		@ResponseBody
		public Map<String, Object> queryCountComment(HttpServletRequest request,CommentQueryDTO queryDTO){
			
			this.wrapTableQueryParams(request, queryDTO);
			Page<CountCommentDTO> pageResult = this.commentService.searchCountCommentByPage(queryDTO);
			 
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("aaData", pageResult.getList());
			jsonMap.put("iTotalDisplayRecords", pageResult.getRecTotal());//总记录数
			jsonMap.put("iTotalRecords", pageResult.getPageSize());		  //当前查询数量	
			jsonMap.put("sEcho", queryDTO.getsEcho());
			
			return jsonMap;
		}
		
		
		

		
		@RequestMapping(value="/exportCountComment",method={RequestMethod.GET})
		public void exportCountComment(HttpServletRequest request,HttpServletResponse response){
			DownLoadDTO dto= this.commentService.exoprtCountComment();
			this.createDownLoadStream2(request, response, dto);
		}
		

		
}
