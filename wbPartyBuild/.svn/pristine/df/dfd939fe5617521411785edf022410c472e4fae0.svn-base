package com.wb.web.portals.service.impl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.DownLoadDTO;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.ICommentDao;
import com.wb.web.portals.dao.ICommunicationDao;
import com.wb.web.portals.dto.comment.CommentDTO;
import com.wb.web.portals.dto.comment.CommentQueryDTO;
import com.wb.web.portals.dto.comment.CountCommentDTO;
import com.wb.web.portals.entity.Comment;
import com.wb.web.portals.entity.Communication;
import com.wb.web.portals.service.ICommentService;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IZonePathService;

@Service("commentService")
@Transactional
public class CommentServiceImpl extends BaseService implements ICommentService{
	
	@Resource
	private ICommentDao commentDao;
	@Resource
	private IUserDao userDao;
	@Resource
	private ICommunicationDao communicationDao;
	@Resource
	private IZonePathService zonePathService;

	@Override
	public Page<CommentDTO> searchCommentByPage(CommentQueryDTO queryDTO) {
		return this.commentDao.searchEntityByPage(queryDTO);	
	}
	
	

	
	@Override
	public CommentDTO getCommentById(Long id) {
		Comment comment = this.commentDao.getById(id);
		CommentDTO commentDTO = new CommentDTO();
		this.getMapper().map(comment, commentDTO);	
		return commentDTO;
	}

	@Override
	public void addComment(CommentDTO dto) {
		
		if(!checkWheatherLogined()){			
			throw new MyException("请先登录再评论");
		}		
		Assert.notNull(dto.getContent(), "评论内容不能为空!");
		Comment com = new Comment();
		com.setCommentTime(new Date());
		com.setContent(dto.getContent());
		com.setCommunication(new Communication(dto.getComId()));
		com.setUser(new User(getNowUserId()));
		com.setLove(0);		
		com.setIsHavaChild(false);
		com.setTarget(null);	
		
		if(dto.getParentId()!=null){
			Comment parent = this.commentDao.getById(dto.getParentId());
			com.setParent(parent);
			if(parent.getIsHavaChild() == false){
				parent.setIsHavaChild(true);
				this.commentDao.update(parent);
			}
		}else{	
			com.setParent(null);
		}
		if(dto.getUserId()!=null&&dto.getUserId()!=""){
			com.setTarget(new User(dto.getUserId()));
		}		
		this.commentDao.save(com);
	
	}

	@Override
	public void deleteComment(Long[] ids) {
		
		for (int i = 0; i < ids.length; i++) {
			Comment comment = this.commentDao.getById(ids[i]);
			if(comment!=null){
				this.commentDao.delete(comment);
			}			
			if(i%20 == 0){
				this.commentDao.flush();
				this.commentDao.clear();
			}
		}
		
	}

	
	@Override
	public void updateComment(CommentDTO dto) {

		Assert.notNull(dto.getId(), "评论信息有误");
		Comment comment = this.commentDao.getById(dto.getId());	
		
		if(1 == dto.getType()){
			Assert.notNull(dto.getContent(), "评论内容不能为空!");	
			comment.setContent(dto.getContent());
		}else if(2 == dto.getType()){
			//点赞
			comment.getVoter().add(new User(this.getNowUserId()));
			comment.setLove(comment.getLove()+1);
		}else if(3 == dto.getType()){
			//取消点赞
			this.commentDao.deleteVote(dto.getId(), this.getNowUserId());	
			comment.setLove(comment.getLove()-1);
		}

		
		this.commentDao.update(comment);
	}
	
	


	@Override
	public Page<CommentDTO> getAllComment(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove) {	
		//先查出父评论
		Page<CommentDTO> page =  this.commentDao.getAllComment(comId,curPage,pageSize,byTime,byLove);
		
		
		for (CommentDTO commentDTO : page.getList()) {			
			//遍历父评论
			Comment comment = this.commentDao.getById(commentDTO.getId());
			//判断是否有voter
			if(comment.getVoter().size()>0){
				//遍历voterList,判断是否有当前用户
				int loveNum = comment.getVoter().size();
				for (User voter : comment.getVoter()) {
					if(voter.getId().equals(this.getNowUserId())){
						commentDTO.setLoving(true);
					}
				}
				commentDTO.setLove(loveNum);
			}else{
				commentDTO.setLove(0);
			}
			//判断是否有子评论
			if(true == commentDTO.getIsHaveChild()){
				List<CommentDTO> childComment = this.commentDao.getAllChildComment(comment.getId());
				//遍历子评论列表
				for (CommentDTO commentDTO2 : childComment){
					Comment comment2 = this.commentDao.getById(commentDTO2.getId());
					//遍历voterList,判断是否有当前用户
					if(comment2.getVoter().size()>0){	
						int loveNum = comment2.getVoter().size();
						for (User voter : comment2.getVoter()) {
							if(voter.getId().equals(this.getNowUserId())){
								commentDTO2.setLoving(true);
							}
						}
						
						commentDTO2.setLove(loveNum);
					}else{
						commentDTO.setLove(0);
					}
					if(commentDTO2.getTargetId()!=null){
						User user = this.userDao.getById(commentDTO2.getTargetId());
						if(user!=null){
							commentDTO2.setTargetUsername(user.getUserName());
						}
				     }
				}
				commentDTO.setChildComment(childComment);
			}			
		}				
		return page;
	}


	@Override
	public Integer conutComment(Long comId,Integer type) {
		return this.commentDao.conutComment(comId,type);
	}




	@Override
	public Integer countTotlaPage(Long comId) {
		return this.commentDao.countTotlaPage(comId);
	}




	@Override
	public Page<CommentDTO> getAllParentCommentApp(Long comId, Integer curPage,
			Integer pageSize, Boolean byTime, Boolean byLove) {
		Page<CommentDTO> page =  this.commentDao.getAllComment(comId,curPage,pageSize,byTime,byLove);
		
		for (CommentDTO dto : page.getList()) {
			dto.setChildCommentNums(this.commentDao.countChildComment(dto.getId()));  
			//遍历父评论
			Comment comment = this.commentDao.getById(dto.getId());
			//判断是否有voter
			if(comment.getVoter().size()>0){
				//遍历voterList,判断是否有当前用户
				int loveNum = comment.getVoter().size();
				for (User voter : comment.getVoter()) {
					if(voter.getId().equals(this.getNowUserId())){
						dto.setLoving(true);
					}
				}
				dto.setLove(loveNum);
			}else{
				dto.setLove(0);
			}		
		}
		
		return page;
	}



	@Override
	public Integer countTotalSize(Long comId) {
		// TODO Auto-generated method stub
		return this.commentDao.countTotalSize(comId);
	}




	@Override
	public List<CommentDTO> getAllChildComment(Long parentId) {
		
		List<CommentDTO> childList = this.commentDao.getAllChildComment(parentId); 
		
		//遍历子评论列表
		for (CommentDTO commentDTO : childList){
			Comment comment = this.commentDao.getById(commentDTO.getId());
			//遍历voterList,判断是否有当前用户
			if(comment.getVoter().size()>0){	
				int loveNum = comment.getVoter().size();
				for (User voter : comment.getVoter()) {
					if(voter.getId().equals(this.getNowUserId())){
						commentDTO.setLoving(true);
					}
				}
				commentDTO.setLove(loveNum);				
			}else{
				commentDTO.setLove(0);
			}
			if(commentDTO.getTargetId()!=null){
				User user = this.userDao.getById(commentDTO.getTargetId());
				if(user!=null){
					commentDTO.setTargetUsername(user.getUserName());
				}
		     }
		}
		return childList;
	}




	@Override
	public Page<CommentDTO> getAllChildCommentApp(Long comId, Integer curPage,
			Integer pageSize, Boolean byTime, Boolean byLove) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Page<CountCommentDTO> searchCountCommentByPage(CommentQueryDTO queryDTO) {
		return this.commentDao.searchCountCommentByPage(queryDTO);
	}




	@Override
	public DownLoadDTO exoprtCountComment() {
		
		List<CountCommentDTO> result =  this.commentDao.getCountCommentList();
		
		// TODO Auto-generated method stub
		 Date dt=new Date();
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
	     String fileName="评论数统计"+"(截至"+sdf.format(dt)+")";
	     
	     int currRow=1;	
			HSSFWorkbook wb =this.createGroupWorkbook();
			HSSFSheet sheet = wb.getSheet("sheet1");
			
			HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式        
	        
	        HSSFDataFormat format= wb.createDataFormat();
	        style.setDataFormat(format.getFormat("yyyy-mm-dd hh:mm:ss"));
	      
	        HSSFFont font = wb.createFont();      
	        font.setFontName("宋体");    
	        font.setFontHeightInPoints((short) 10);//设置字体大小    
	        style.setFont(font);
		

	    	//填充表格
			for (CountCommentDTO dto : result) {
				HSSFRow row = sheet.createRow((int)currRow);
				currRow++;
				
				HSSFCell cell = row.createCell(0);  
				cell.setCellValue(dto.getSort().toString()); 
				cell.setCellStyle(style);
				

				HSSFCell cell1 = row.createCell(1);  
				cell1.setCellValue(dto.getUsername()); 
				cell1.setCellStyle(style);
			    
			    HSSFCell cell2 = row.createCell(2);  
			    cell2.setCellValue(dto.getNum().toString());  
			    cell2.setCellStyle(style);
			    
			    HSSFCell cell3 = row.createCell(3);  
			    if(dto.getLastCommentTime()!=null){
			    	cell3.setCellValue(dto.getLastCommentTime()); 
			    }else{
			    	cell3.setCellValue("");
			    }
			    cell3.setCellStyle(style);
			}
			
		      //将文件存到指定位置  
		      try  
		      {  
		      	String path=this.zonePathService.getTempFileWholePath("xls");
		      	UUID uuid=UUID.randomUUID();
		      	FileOutputStream fout = new FileOutputStream(path+uuid.toString()+".xls");  
		          wb.write(fout);  
		          fout.close();
		          DownLoadDTO dto=new DownLoadDTO(fileName, path+uuid.toString()+".xls","xls");
		          return dto;
		      }  
		      catch (Exception e)  
		      {  
		          e.printStackTrace();  
		      }
		      return null;

	}


	public HSSFWorkbook createGroupWorkbook(){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet1"); 
        sheet.setColumnWidth(0, 15 * 256); 
        sheet.setColumnWidth(1, 15 * 256);     
        sheet.setColumnWidth(2, 15 * 256); 
        sheet.setColumnWidth(3, 25 * 256); 

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);
        
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式        
        
        HSSFFont font = wb.createFont();      
        font.setFontName("宋体");    
        font.setFontHeightInPoints((short) 11);//设置字体大小    
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示  
        style.setFont(font);
        
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("序号");  
        cell.setCellStyle(style);
        
   				
        HSSFCell cell1 = row.createCell(1);  
        cell1.setCellValue("评论人");  
        cell1.setCellStyle(style);

        
        HSSFCell cell2 = row.createCell(2);  
        cell2.setCellValue("评论数");  
        cell2.setCellStyle(style);
        
        HSSFCell cell3 = row.createCell(3);  
        cell3.setCellValue("最近一次评论时间");  
        cell3.setCellStyle(style);
        
        return wb;
	}



	
	
}
