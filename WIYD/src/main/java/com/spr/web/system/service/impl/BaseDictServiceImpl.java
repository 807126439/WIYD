package com.spr.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.service.BaseService;
import com.spr.core.common.utils.Assert;
import com.spr.web.system.dao.IBaseDictDao;
import com.spr.web.system.dto.baseDict.BaseDictDTO;
import com.spr.web.system.entity.BaseDict;
import com.spr.web.system.service.IBaseDictService;

@Service("baseDictService")
public class BaseDictServiceImpl extends BaseService implements IBaseDictService{

   @Resource
   private IBaseDictDao baseDictDao;
    
    
    
    /**
     * 查询集合
     * @param queryDTO
     */
    public List<BaseDictDTO> searchListByCondition(DataQuery dq){
    	
    	dq.setNotQueryPage().assembleOrderInfo(BaseDict.class, null);
    	List<BaseDict> resultList = this.baseDictDao.selectListByCondition(dq.getQueryMap());
    	List<BaseDictDTO> dtoList = new ArrayList<BaseDictDTO>();
    	for (int i = 0; i < resultList.size(); i++) {
    		BaseDictDTO dto = new BaseDictDTO();
    		this.getMapper().map(resultList.get(i), dto);
    		dtoList.add(dto);
		}
    	
    	return dtoList;
    }
    
    
    
    public BaseDictDTO getByCondition(DataQuery dq){
    	
    	List<BaseDict> resultList = this.baseDictDao.selectListByCondition(dq.getQueryMap());
    	List<BaseDictDTO> dtoList = new ArrayList<BaseDictDTO>();
    	for (int i = 0; i < resultList.size(); i++) {
    		BaseDictDTO dto = new BaseDictDTO();
    		this.getMapper().map(resultList.get(i), dto);
    		dtoList.add(dto);
		}
    	
    	BaseDictDTO result = null;
    	if(!dtoList.isEmpty()){
    		result = dtoList.get(0);
    	}
    	return result;
    }
    
    
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	public Page<BaseDictDTO> searchBaseDictByPage(DataQuery dq) {
		
				
		Long recTotal = this.baseDictDao.countByCondition(dq.assemblePageOffset().getQueryMap());
		dq.assembleOrderInfo(BaseDict.class, null);
		List<BaseDict> list = this.baseDictDao.selectListByCondition(dq.getQueryMap());
		
		List<BaseDictDTO> dtoList = new ArrayList<BaseDictDTO>();
		for (int i = 0; i < list.size(); i++) {
			BaseDictDTO dto = new BaseDictDTO();
			this.getMapper().map(list.get(i), dto);
			dtoList.add(dto);
		}
		
		return new Page<BaseDictDTO>(dq.getCurrentPage(), dq.getPageSize(), dtoList, recTotal);
	}
    

    
    
    /**
     * 通过id查找字典
     * @param id
     * @return
     */
    public BaseDictDTO getDictById(String id){
    	Assert.hasText(id, "id must not be null");
    	
    	BaseDict dict = this.baseDictDao.getById(id);
    	BaseDictDTO dto = new BaseDictDTO();
    	this.getMapper().map(dict, dto);
    	
    	return dto;
    }
    
    
    /**
     * 保存字典
     * @param dto
     */
    public void addDict(BaseDictDTO dto){
    	Assert.hasText(dto.getDictItem(), "字典项不能为空！");
    	Assert.hasText(dto.getDictType(), "字典类别不能为空！");
    	Assert.hasText(dto.getDictValue(), "字典值不能为空！");
    	
    	BaseDict dict = new BaseDict(dto.getDictItem(), dto.getDictType(), dto.getDictValue(), dto.getDictFlag());     	
    	
    	this.baseDictDao.insert(dict);
    }
    
    /**
     * 修改字典
     * @param dto
     */
    public void updateDict(BaseDictDTO dto){
    	Assert.hasText(dto.getId(), Assert.NULL_PARAM_STR("id"));
    	Assert.hasText(dto.getDictItem(), "字典项不能为空！");
    	Assert.hasText(dto.getDictType(), "字典类别不能为空！");
    	Assert.hasText(dto.getDictValue(), "字典值不能为空！");
    	
    	BaseDict dict = this.baseDictDao.getById(dto.getId());
    	dict.setDictItem(dto.getDictItem());
    	dict.setDictType(dto.getDictType());
    	dict.setDictValue(dto.getDictValue());
    	dict.setDictFlag(dto.getDictFlag());
    	
    	
    	this.baseDictDao.update(dict);
    }
    
    
    /**
     * 删除字典
     * @param ids
     */
    public void deleteDicts(String[] ids){
    	for (int i = 0; i < ids.length; i++) {
			this.baseDictDao.deleteById(ids[i]);
		}
    }
    
    
    
    
}
