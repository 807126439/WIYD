package com.wb.web.portals.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.DateUtil;
import com.wb.core.utils.Html2Word;
import com.wb.core.utils.PropertiesUtil;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dao.IColumnMuDao;
import com.wb.web.portals.dao.IContentDao;
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.IndexItem;
import com.wb.web.portals.dto.content.ContentDTO;
import com.wb.web.portals.dto.content.ContentItem;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.content.HistoryContentDTO;
import com.wb.web.portals.dto.content.InnerContentDetailDTO;
import com.wb.web.portals.dto.content.InnerShowContentDTO;
import com.wb.web.portals.dto.content.ShowContentDTO;
import com.wb.web.portals.entity.ColumnMu;
import com.wb.web.portals.entity.Content;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IBanChunkService;
import com.wb.web.portals.service.IContentService;
import com.wb.web.system.dto.department.DepartmentDTO;
import com.wb.web.system.service.IDepartmentService;

@Service("contentService")
@Transactional
public class ContentServiceImpl extends BaseService implements IContentService{
	@Resource
	private IContentDao contentDao;
	@Resource
	private IColumnMuDao columnMuDao;
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IBanChunkService banChunkService;
	@Resource
	private IDepartmentService DepartmentService;
	
	
	private Integer APP_CONTENT_SIZE = 30;
	
	public Integer getAPP_CONTENT_SIZE(){
			String classBaseDir =  this.getClass().getResource("/").getPath();		
		 String propertiesfile = classBaseDir+"systemConfig.properties";
			
			
		 String appContentSize  = PropertiesUtil.GetValueByKey(propertiesfile, "appContentSize");
		 if(!StringUtils.isBlank(appContentSize)){
			 return APP_CONTENT_SIZE = Integer.parseInt(appContentSize);
		 }else{
			 throw new RuntimeException("the value of  key:appContentSize is empty in properties file");
		 }
	}
	
	
	/**
	 * 保存APP文章最大数量
	 */
	@Override
	public void saveAppContentNum(Integer contentNum) {
		 String classBaseDir =  this.getClass().getResource("/").getPath();		
		 String propertiesfile = classBaseDir+"systemConfig.properties";
		 try {
			PropertiesUtil.WriteProperties(propertiesfile, "appContentSize", contentNum.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	public Page<ContentDTO> searchEntityByPage(ContentQueryDTO queryDTO){
		
		return this.contentDao.searchEntityByPage(queryDTO);
	}
	
	
	/**
	 * 查询单个对象
	 * @param id
	 * @param isFormColumn  是否根据栏目id查询文章列表的第一条记录
	 * @return
	 */
	public ContentDTO getContentDTOById(Long id,boolean isFormColumn){
		Assert.notNull("id", "id must not be null");
		ContentDTO result = null;
				
		if(!isFormColumn){
			result =  this.contentDao.getContentDTOById(id);
				
			if(null!=result.getTypeId()&&result.getTypeId() == ColumnMu.VIDEO_LIST_TYPE && result.getBaseFileId()!=null){
				String viewPath = this.baseFileService.getFileViewPath(result.getBaseFileId());
				result.setViewPath(viewPath);
			}
			
		}else{
			
			result =  this.contentDao.getContentDTOByColumnId(id);
		}
		
				
				
		return result;
	}
	
	
	/**
	 * 添加栏目杂志内容
	 * @param dto
	 * @param uploadFile
	 */
	public void addMagazineContent(Long parContentId,CommonsMultipartFile uploadFile){
		Assert.notNull(parContentId, "文章父节点id不能为空！");

		Content parent = this.contentDao.getById(parContentId);
		this.contentDao.countTotalNum();
		
		Content content = new Content(
						  parent.getColumnMu(), 
						  parent.getTitlePrefix(),
						  parent.getTitle(),						  
						  parent.getAuthor(),
						  parent.getSource(), 
						   (this.contentDao.countTotalNum().intValue() + 1), 
						  Content.NOT_INDEX_FLAG,
						  parent,
						  parent.getCreateTime(),
						  getNowUser().getUsername());
		
		
		if(uploadFile!=null && !uploadFile.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
			content.setPattern(aResult.getPattern());
			content.setViewPath(aResult.getBigPattern());
			content.setBaseFileId(aResult.getId());
			content.setTitle(uploadFile.getOriginalFilename().substring(0, uploadFile.getOriginalFilename().lastIndexOf(".")));			
		}
		
		
		this.contentDao.save(content);
		
		
	}
	
	
	/**
	 * 上传图片文章
	 * @param columnId
	 * @param uploadFile
	 */
	public void addImgContent(Long columnId,CommonsMultipartFile uploadFile){
		Assert.notNull(columnId, "栏目id不能为空！");
		if(uploadFile == null || uploadFile.isEmpty()){
			throw new MyException("上传文件不能为空！");
		}
		
		ColumnMu columnMu = this.columnMuDao.getById(columnId);
		Content content = new Content();
		
			
		SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
		content.setPattern(aResult.getPattern());
		content.setViewPath(aResult.getBigPattern());
		content.setBaseFileId(aResult.getId());
		content.setTitle(uploadFile.getOriginalFilename().substring(0, uploadFile.getOriginalFilename().lastIndexOf('.')));		
		content.setSortNum(this.contentDao.countTotalNum().intValue()+1);
		content.setColumnMu(columnMu);
		content.setCreateTime(new Date());
		content.setUpdateBy(getNowUser().getUsername());
		content.setStatus(Content.NORMAL_STATUS);
		
							
		if(content.getColumnMu().getIsIndex()){//自动把新添加的文章置于首页显示
															
			handleContextIndex(columnMu, content,false);
			
		}else{
			content.setIndexFlag(Content.NOT_INDEX_FLAG);
		}
			

		
		this.contentDao.save(content);
		
		
	}
	
	
	
	
	
	
	/**
	 * 添加栏目内容
	 * @param dto
	 * @param uploadFile
	 */
	public void addContent(ContentDTO dto,CommonsMultipartFile uploadFile){
		Assert.notNull(dto.getColumnId(), "栏目id不能为空！");
		Assert.hasText(dto.getTitle(), "标题不能为空！");
		
		ColumnMu columnMu = this.columnMuDao.getById(dto.getColumnId());
		Content content = new Content(
							  columnMu, 
							  dto.getTitlePrefix(),
							  dto.getTitle(),
							  dto.getContent(), 
							  null,
							  dto.getAuthor(),
							  dto.getSource(), 
							  dto.getSortNum(), 
							  dto.getIndexFlag(),
							  dto.getCreateTime(),
							  getNowUser().getUsername());
		
		if(null!=dto.getSeeOrgId()&&dto.getSeeOrgId().trim()!=""){
			content.setSeeOrgId(dto.getSeeOrgId());
		}
		
		if(uploadFile!=null && !uploadFile.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
			content.setPattern(aResult.getPattern());
			content.setViewPath(aResult.getBigPattern());
			content.setBaseFileId(aResult.getId());
					
		}
		
		
		if(dto.getIndexFlag() == Content.IN_INDEX_FLAG){//文章置顶，进行同一栏目下文章置于首页处理
			
			if(content.getColumnMu().getIsIndex()){	
				
				 handleContextIndex(columnMu, content,true);
			
			}else{
				
				if(!columnMu.getIsIgnorePre()){
					
					if(columnMu.getParent()!=null){//还存有上级栏目
						ColumnMu top = content.getColumnMu().getParent();
												
						if(top.getIsIndex() && (top.getShowType() == ColumnMu.TAB_SHOW_TYPE || 
									top.getShowType() == ColumnMu.LIST_SHOW_TYPE )){//顶级栏目是置顶且显示子栏目信息
							
							handleContextIndex(columnMu, content,true);
							 
						}else{
							throw new MyException("上级栏目没有置于首页，置顶操作失败！");
						}											
						
					}else{
						
						throw new MyException("所属的栏目没有设置置于首页，所以文章置顶操作失败！");
						
					}
					
					
				}else{
					throw new MyException("无法置顶！");
				}
				
			}
			
		}else{
			
			if(columnMu.getIsIndex()){//自动把新添加的文章置于首页显示
																
				handleAutoIndex(columnMu, content);
				
			}else{
				
				if(!columnMu.getIsIgnorePre()){
					
					if(columnMu.getParent()!=null){
						ColumnMu top = columnMu.getParent();
						
						if(top.getShowType() == ColumnMu.TAB_SHOW_TYPE){
							List<Long> tabIdList = this.columnMuDao.getTabColumnsIds(top.getId(), ColumnMu.MAX_CHILD_NUM);
							
							if(tabIdList.contains(columnMu.getId())){															
								handleAutoIndex(columnMu, content);																							
							}else{
								content.setIndexFlag(Content.NOT_INDEX_FLAG);
							}
							
							
						}else if(top.getShowType() == ColumnMu.LIST_SHOW_TYPE){
						
								List<Content> cts = this.contentDao.getListShowTypeContent(top.getId(),Content.NOT_INDEX_FLAG);
								if(cts.size()<top.getMaxContentSize()){
									content.setIndexFlag(Content.AUTO_IN_INDEX_FLAG);
								
								}else{
									
									for (Content t : cts) {
										if(t.getIndexFlag() == Content.AUTO_IN_INDEX_FLAG){
											t.setIndexFlag(Content.NOT_INDEX_FLAG);
											content.setIndexFlag(Content.IN_INDEX_FLAG);
											
											this.contentDao.update(t);
											
											break;
										}
									}
									
									
								}
							
						}
						
					}
					
					
				}
				
				
				
			}
			
			
		}
		
		
	
		
		this.contentDao.save(content);
		
		
	}

	
	/**
	 * 处理文章的自动置顶
	 * @param columnMu
	 * @param content
	 */
	private void handleAutoIndex(ColumnMu columnMu, Content content) {
		Long count  = this.contentDao.getIndexContentNumByColum(content.getColumnMu().getId());
		if(count < content.getColumnMu().getMaxContentSize()){//首页显示的数量未饱满
			content.setIndexFlag(Content.AUTO_IN_INDEX_FLAG);					
		
		}else{
			List<Content> autoIndexList = this.contentDao.getIndexContentInColumMu(columnMu.getId(), Content.AUTO_IN_INDEX_FLAG);//系统自动置顶的文章
			
			if(!autoIndexList.isEmpty()){//设置最旧的自动置顶为不置顶

				if(content.getCreateTime().after(autoIndexList.get(0).getCreateTime())){//新增文章时间在此之后
					
					autoIndexList.get(0).setIndexFlag(Content.NOT_INDEX_FLAG);
					this.contentDao.update(autoIndexList.get(0));
					
					content.setIndexFlag(Content.AUTO_IN_INDEX_FLAG);
				}else{
					content.setIndexFlag(Content.NOT_INDEX_FLAG);
					
				}
					
			}
		}
	}

	/**
	 * 栏目文章置顶处理
	 * @param columnMu
	 * @param content
	 */
	private void handleContextIndex(ColumnMu columnMu, Content content,boolean isThrowError) {
		Long count  = this.contentDao.getIndexContentNumByColum(columnMu.getId());//获取置顶文章的总数量（包括手动和自动置顶）
		
		if(count >= columnMu.getMaxContentSize()){//置顶文章数量已到达最大值
			
			List<Content> autoIndexList = this.contentDao.getIndexContentInColumMu(columnMu.getId(), Content.AUTO_IN_INDEX_FLAG);//系统自动置顶的文章
			
			if(!autoIndexList.isEmpty()){//设置系统自动置顶的文章集合中的第一个文章为
				autoIndexList.get(0).setIndexFlag(Content.NOT_INDEX_FLAG);
				this.contentDao.update(autoIndexList.get(0));
				content.setIndexFlag(Content.IN_INDEX_FLAG);
				
			}else{
				if(isThrowError){
					throw new MyException("置顶文章的数量已到达最大值，置顶失败");
				}else{
					content.setIndexFlag(Content.IN_INDEX_FLAG);
				}
				
			}
			
		}else{
			content.setIndexFlag(Content.IN_INDEX_FLAG);
		}
		
						
		
	}
	
	/**
	 * 更新文章编号
	 * @param id
	 * @param sortNum
	 */
	public void updateContentSortNum(Long id,Integer sortNum){
		Assert.notNull(id, "id must not be null");
		Assert.notNull(sortNum, "sortNum must not be null");
		
		Content content = this.contentDao.getById(id);
		content.setSortNum(sortNum);
		
		this.contentDao.update(content);
	}
	
	
	
	
	
	/**
	 * 修改文章信息
	 */
	public void updateContent(ContentDTO dto,CommonsMultipartFile uploadFile){
		Assert.notNull(dto.getCtId(), "id must not be null");
		Assert.hasText(dto.getTitle(), "标题不能为空！");
		
		Content content = this.contentDao.getById(dto.getCtId());
		
		//如果文章原来是系统设置的置顶，且修改值是不置顶，则保持原有值不变
		if(content.getIndexFlag() == Content.AUTO_IN_INDEX_FLAG && dto.getIndexFlag() == Content.NOT_INDEX_FLAG){
			dto.setIndexFlag(Content.AUTO_IN_INDEX_FLAG);
		}
		
		content.setSortNum(dto.getSortNum());
		content.setTitlePrefix(dto.getTitlePrefix());
		content.setTitle(dto.getTitle());
		content.setContent(dto.getContent());
		content.setAuthor(dto.getAuthor());
		content.setSource(dto.getSource());
		content.setCreateTime(dto.getCreateTime());
		content.setUpdateBy(getNowUser().getUsername());
		content.setLastOperatorTime(new Date());
		content.setSeeOrgId(dto.getSeeOrgId());
		content.setAppStatus(dto.getAppStatus());
		if(dto.getActivityId()!=null){
			content.setActivity(new ThemeActivity(dto.getActivityId()));
		}

		 
		
		
		if(uploadFile!=null && !uploadFile.isEmpty()){
			Long oldBaseFileId = content.getBaseFileId();
			SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
			content.setPattern(aResult.getPattern());
			content.setViewPath(aResult.getBigPattern());
			content.setBaseFileId(aResult.getId());
			
			//删除原来的文件
			this.baseFileService.deleteBaseFile(oldBaseFileId);
		}
		
		
		/*处理置于首页*/
		if(!content.getIndexFlag().equals(dto.getIndexFlag())){
			
			if(dto.getIndexFlag() == Content.IN_INDEX_FLAG){
				
				if(content.getColumnMu().getIsIndex()){
					
					handleContextIndex(content.getColumnMu(), content,true);
					
				}else{
					
					if(!content.getColumnMu().getIsIgnorePre()){
						if(content.getColumnMu().getParent()!=null){//还存有上级栏目
							ColumnMu top = content.getColumnMu().getParent();
													
							if(top.getIsIndex() && (top.getShowType() == ColumnMu.TAB_SHOW_TYPE || 
									top.getShowType() == ColumnMu.LIST_SHOW_TYPE )){	//顶级栏目是置顶且显示子栏目
								
								handleContextIndex(content.getColumnMu(), content,true);
							}else{
								
								throw new MyException("上级栏目没有置于首页，置顶操作失败！");
							}
							
						}else{
							
							throw new MyException("所属的栏目没有设置置于首页，置顶操作失败！");
						}
					}
					
				}
				
			}else{
				boolean isHandle = false;//是否处理其他文章的自动置顶
				
				if(content.getColumnMu().getIsIndex()){
					isHandle = true;
					
				}else if(content.getColumnMu().getParent()!=null){
				
					ColumnMu top = content.getColumnMu().getParent();
					
					if(top.getShowType() == ColumnMu.TAB_SHOW_TYPE || top.getShowType() == ColumnMu.LIST_SHOW_TYPE){
						isHandle = true;
					}
						
				}
				
				if(isHandle){
					
					//设置最新的不置顶文章为系统自动置顶
					Content newestCt = this.contentDao.findObjectBySql("select * from cy_content where columnmu_id=? " +
							"AND index_flag=? ORDER BY create_time DESC LIMIT 0,1", content.getColumnMu().getId(),Content.NOT_INDEX_FLAG);
					
					if(newestCt!=null){
						newestCt.setIndexFlag(Content.AUTO_IN_INDEX_FLAG);
						this.contentDao.update(newestCt);
					}
				}
								
				
				content.setIndexFlag(Content.NOT_INDEX_FLAG);
			}
			
		}
		
		
		this.contentDao.update(content);
		
		try {
			new Html2Word().htmlToWord2(content.getContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteContent(Long[] ids){
		if(ids.length>0){
					
			for (int i = 0; i < ids.length; i++) {
				Content content = this.contentDao.getById(ids[i]);
				if(content!=null){
					content.setLastOperatorTime(new Date());
					content.setStatus(Content.DEL_STATUS);					
					content.setHistoryColumn(content.getColumnMu() == null ? null:content.getColumnMu().getTitle());
					content.setColumnMu(null);
					content.setParent(null);
					
					if(content.getChildren().size() > 0){
						this.contentDao.executeSql("UPDATE cy_content SET status=?,columnmu_id =NULL WHERE parent_id=? AND status=?",
												    Content.DEL_STATUS,	content.getId(),Content.NORMAL_STATUS);
					}
					
					
				}
			
				
				this.contentDao.update(content);
			}
					
		
			
		}
	

	}
	
	public Long countTotalNum(){
		return this.contentDao.countTotalNum();
	}
	
	
	
	
	
	
	
	/**
	 * 查询首页显示的栏目和文章
	 */
	public List<IndexItem> searchContentForIndex(){
		List<ColumnHead> indexHead = this.columnMuDao.searchIndexColum();//置于首页显示的栏目
		List<IndexItem> resultList = new ArrayList<IndexItem>();
		
			
		List<ShowContentDTO> contentList = this.contentDao.searchContentDTOForIndex();
		
		Map<ColumnHead, Object> resultMap = new LinkedHashMap<ColumnHead, Object>();
		
		for (ColumnHead head : indexHead) {
			
			if(head.getShowType()!=null && head.getShowType() == ColumnMu.TAB_SHOW_TYPE){
				Map<ColumnHead, List<ShowContentDTO>>  innerMap = new TreeMap<ColumnHead, List<ShowContentDTO>>(new Comparator<ColumnHead>() {
					//定义ColumnHead的index为排序依据
					public int compare(ColumnHead o1, ColumnHead o2) {							
												
						return o1.getIndexNum().compareTo(o2.getIndexNum());
					}												
				});
												
				resultMap.put(head, innerMap);
				
			}else{
				
				resultMap.put(head, new ArrayList<ShowContentDTO>());
			}			
				
		}
		
		
		
		Date now = new Date();
		for (ShowContentDTO ct : contentList) {
			if(ct.getCreateTime()!=null){//转换时间为月日格式
				ct.setChanggTime(DateUtil.Date2StrByMD(ct.getCreateTime()));
				ct.setIsNew(DateUtil.checkDatesIsInRange(now, ct.getCreateTime(), (long) (10*24*3600*1000)));//10天
			}
									
			if(!ct.getClIsIndex() && ct.getShowType()!=null && ct.getShowType() == ColumnMu.TAB_SHOW_TYPE){//文章的最顶级栏目是'显示子栏目'的属性,将最顶级栏目信息放入head中
				ColumnHead head = new ColumnHead(ct.getClParId());
				
				if(resultMap.containsKey(head)){//resultMap已经存在
					Map<ColumnHead, List<ShowContentDTO>>  innerMap =  (Map<ColumnHead, List<ShowContentDTO>>) resultMap.get(head);
					ColumnHead innerHead = new ColumnHead(ct.getClSortNum(), ct.getClId(), ct.getClTitle());
									
					if(innerMap.containsKey(innerHead)){//innerMap已经存在
						innerMap.get(innerHead).add(ct);
					}else{
						List<ShowContentDTO> innerList = new ArrayList<ShowContentDTO>();
						innerList.add(ct);
						innerMap.put(innerHead, innerList);
					}
															
				}
				
			
			}else if(!ct.getClIsIndex() && ct.getShowType()!=null && ct.getShowType() == ColumnMu.LIST_SHOW_TYPE){
				ColumnHead head = new ColumnHead(ct.getClParId());
				
				if(resultMap.containsKey(head)){//resultMap已经存在
					((List<ShowContentDTO>) resultMap.get(head) ).add(ct);
										
				}
				
			}else{//把文章直属的栏目信息放入head中
				ColumnHead head = new ColumnHead(ct.getClId());
				
				if(resultMap.containsKey(head)){//resultMap已经存在
					((List<ShowContentDTO>) resultMap.get(head) ).add(ct);
										
				}
				
			}
						
			
		}
		
	
		for (Entry<ColumnHead, Object> it : resultMap.entrySet()) {
			
			if(it.getValue() instanceof TreeMap){
				List<IndexItem> innnerList = new ArrayList<IndexItem>();
			
				for (Entry<ColumnHead, Object> it2 : ((Map<ColumnHead, Object>)it.getValue()).entrySet()) {
					innnerList.add(new IndexItem(it2.getKey(), it2.getValue(),false));
				}
				
				resultList.add(new IndexItem(it.getKey(),innnerList,true));
			
			}else{
				
				List<ShowContentDTO> list = (List<ShowContentDTO>) it.getValue();
				Collections.sort(list, new Comparator<ShowContentDTO>() {

					public int compare(ShowContentDTO o1, ShowContentDTO o2) {
						
						if(o1.getCtIndexFlag().equals(o2.getCtIndexFlag())){
							return 0;
						
						}else if(o1.getCtIndexFlag() > o2.getCtIndexFlag()){
							
							if(o1.getCtIndexFlag() == 2 &&  o2.getCtIndexFlag() == 1){
								return 1;
								
							}else{
								return -1;
							}
							
						}else{
							
							if(o1.getCtIndexFlag() == 1 &&  o2.getCtIndexFlag() == 2){
								return -1;
								
							}else{
								return 1;
							}
							
						}
						
					
					}
				});
				
				resultList.add(new IndexItem(it.getKey(), it.getValue(),false));
			}
			
		
						
		}
			
		return resultList;
		
	}
	
	
	
	
	
	
	
    /**
     * 内页文章详细内容展示处理
     * @param id
     * @return
     */
	public InnerContentDetailDTO getInnerContentDetailById(Long id){		
		InnerContentDetailDTO result = this.contentDao.getContentDetailById(id);
		
		if(result == null){
			throw new MyException("Can not find the record！");
		}
		
		
		if(result.getTypeId()!=null && result.getTypeId() == ColumnMu.IMG_LIST_TYPE){			
			List<ContentItem> imgList = this.contentDao.getContentItemByCondition(result.getColumnId(), null);
			result.setImgList(imgList);
						
		}else if(result.getTypeId()!=null && result.getTypeId() == ColumnMu.MAGAZINE_TYPE){
			List<ContentItem> imgList = this.contentDao.getContentItemByCondition(null, result.getId());
			//由content获取活动ID，再获取活动下所有板块的信息
			List<BanChunkDTO> dtolist=this.banChunkService.getPublishedBanChunk(result.getActivityId());
			
			result.setBanChunkList(dtolist);
			
			imgList.add(0, new ContentItem(result.getId(), result.getTitle(), result.getViewPath()));			
			result.setImgList(imgList);
			
			List<ContentItem> preAndNext = this.contentDao.getPreAndNextContent(id,result.getColumnId());
			for (ContentItem it : preAndNext) {
				if(it.getId()<id){
					result.setPre(it);
					
				}else if(it.getId()>id){
					result.setNext(it);
				}
			}
						
		}else{
			
			List<ContentItem> preAndNext = this.contentDao.getPreAndNextContent(id,result.getColumnId());
			for (ContentItem it : preAndNext) {
				if(it.getId()<id){
					result.setPre(it);
					
				}else if(it.getId()>id){
					result.setNext(it);
				}
			}
			
		}		
		
			
		return result;
	}
	
	
	
	/**
	 * 修改文章的栏目
	 * @param idss
	 * @param colId
	 */
	public void updateContentColumn(Long[] ids,Long colId){
		Assert.notNull(colId, "栏目节点不能为空！");
		
		ColumnMu columnMu = this.columnMuDao.getById(colId);
		
		Assert.notNull(columnMu);
		
		for (int i = 0; i < ids.length; i++) {			
		
			Content ct = this.contentDao.getById(ids[i]);
			if(ct!=null){
				
				if(!ct.getColumnMu().equals(columnMu)){
					
					if(ct.getColumnMu().getTypeId().equals(columnMu.getTypeId())){
						ct.setLastOperatorTime(new Date());
						ct.setIndexFlag(Content.NOT_INDEX_FLAG);
						ct.setColumnMu(columnMu);
						
						this.columnMuDao.update(ct);
					
					}else{
						throw new MyException("移动目标的栏目的类型与原来栏目不一致，移动失败！");
					}
						
				}					
				
			}
			
			if(i%20 == 0){
				this.contentDao.flush();
				this.contentDao.clear();
			}
			
		}
		
	}
	
	
	
	
	
	
	
	/**
	 * 查询历史的文章（已被删除的）
	 * @param queryDTO
	 * @return
	 */
	public Page<HistoryContentDTO> searchHistoryContentByPage(ContentQueryDTO queryDTO){
		
		return this.contentDao.searchHistoryContenByPage(queryDTO);
	}
	
	
	
	
	/**
	 * 恢复删除的文章
	 * @param ids
	 * @param columId
	 */
	public void updateRecoverContent(Long[] ids,Long columId){
		Assert.notNull(columId, "columId must not be null");
		
		if(ids!=null){
			
			for (int i = 0; i < ids.length; i++) {
				Content ct = this.contentDao.getById(ids[i]);
				if(ct!=null){
					ct.setLastOperatorTime(new Date());
					ct.setIndexFlag(Content.NOT_INDEX_FLAG);//不置顶
					ct.setStatus(Content.NORMAL_STATUS);
					ct.setColumnMu(new ColumnMu(columId));
					
					this.contentDao.update(ct);
					
				}
			}
		}
		
		
	}
	
	
	/**
	 * 彻底删除文章
	 * @param ids
	 */
	public void deleteContentWithShift(Long[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			Content ct = this.contentDao.getById(ids[i]);
			if(ct!=null){								
				
				this.contentDao.delete(ct);
				
				if(ct.getBaseFileId()!=null){
					this.baseFileService.deleteBaseFile(ct.getBaseFileId());
				}
				
			}
			
			if(i%20 == 0){
				this.contentDao.flush();
				this.contentDao.clear();
			}
		}
		
		
	}


	
	
	
	/**
	 * 门户首页获取文章列表
	 * @param columnId
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Page<InnerShowContentDTO> searchContentIndex(Long columnId,String title,Integer curPage,Integer pageSize,Integer days){
	
		return this.contentDao.searchEntityByCondtion(columnId,title,curPage,pageSize,days);
		
	}


	/**
	 * 保存文章与板块关联关系
	 */
	@Override
	public void saveContent(Long banChunkId, Long contentId) {
		BanChunkDTO dto=this.banChunkService.getBanChunkById(banChunkId.toString());
		dto.setLinkContentId(contentId);
		this.banChunkService.updateBanChunk(dto);
	}

	/**
	 * 删除板块与文章的关联关系
	 */
	@Override
	public void cutContentRelationship(Long banChunkId) {
		BanChunkDTO dto=this.banChunkService.getBanChunkById(banChunkId.toString());
		dto.setLinkContentId(null);
		this.banChunkService.updateBanChunk(dto);
	}

	/**
	 * 将允许查看的部门ID转换为部门名称输出
	 */
	@Override
	public String getSeeOrgName(Long cid) {
		ContentDTO dto = this.contentDao.getContentDTOById(cid);
		StringBuilder sb = new StringBuilder();
		if(!StringUtils.isBlank(dto.getSeeOrgId())){
			String[] seeOrgIds = dto.getSeeOrgId().split(",");
			for(int i=0;i<seeOrgIds.length;i++){
				DepartmentDTO deptdto = DepartmentService.getDepartmentById(seeOrgIds[i]);
				if(null!=deptdto){
					sb.append(deptdto.getDepartName()+",");
				}
			}
			
			if(sb.length()>0){
				sb.deleteCharAt(sb.length()-1);
			}
			
		}
		
		
		return (sb.toString()==null?"":sb.toString());
	}
	@Override
	public void sumbitToTop(Long[] ids) {
		if(ids.length>0){
			for (int i = 0; i < ids.length; i++) {
				Content content = this.contentDao.getById(ids[i]);
				if(content!=null){
					content.setLastOperatorTime(new Date());
					content.setAppStatus((short)1);
				}
				this.contentDao.update(content);
			}
		}
	}
	/**
	 * 手机端文章显示列表
	 */
	@Override
	public List<ContentDTO> getAppContent(ContentQueryDTO queryDTO) {
		Page<ContentDTO> contentPage = this.contentDao.searchAppContent(queryDTO);
		return contentPage.getList();
	}
	/**
	 * 后台手机端文章管理显示内容
	 */
	@Override
	public Page<ContentDTO> searchAppEntityByPage(ContentQueryDTO queryDTO) {
		
		Page<ContentDTO> contentPage=this.contentDao.searchAppContent(queryDTO);
		
		return contentPage;
	}
	@Override
	public void cancleToTop(Long[] ids) {
		if(ids.length>0){
			for (int i = 0; i < ids.length; i++) {
				Content content = this.contentDao.getById(ids[i]);
				if(content!=null){
					content.setLastOperatorTime(new Date());
					content.setAppStatus((short)0);
				}
				this.contentDao.update(content);
			}
		}
	}

	
	
	
	
}
