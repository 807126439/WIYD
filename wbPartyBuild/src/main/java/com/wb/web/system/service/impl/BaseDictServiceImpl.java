package com.wb.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.dto.baseDict.BaseDictDTO;
import com.wb.web.system.dto.baseDict.BaseDictQueryDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.service.IBaseDictService;

@Service("baseDictService")
public class BaseDictServiceImpl extends BaseService implements IBaseDictService{

    @Resource
	private IBaseDictDao baseDictDao;
    
    /**
     * 查询集合
     * @param queryDTO
     */
    public List<BaseDictDTO> searchListByCondition(BaseDictQueryDTO queryDTO){
    	List<BaseDict> resultList = this.baseDictDao.searcheDcitListByCondition(queryDTO);
    	List<BaseDictDTO> dtoList = new ArrayList<BaseDictDTO>();
    	for (int i = 0; i < resultList.size(); i++) {
    		BaseDictDTO dto = new BaseDictDTO();
    		this.getMapper().map(resultList.get(i), dto);
    		dtoList.add(dto);
		}
    	
    	return dtoList;
    }
    
    
    
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	public Page<BaseDictDTO> searchBaseDictByPage(BaseDictQueryDTO queryDTO) {
		
		Page<BaseDict> result = this.baseDictDao.searchBaseDictByPage(queryDTO);
		List<BaseDict> list = result.getList();
		List<BaseDictDTO> dtoList = new ArrayList<BaseDictDTO>();
		for (int i = 0; i < list.size(); i++) {
			BaseDictDTO dto = new BaseDictDTO();
			this.getMapper().map(list.get(i), dto);
			dtoList.add(dto);
		}
		
		return new Page<BaseDictDTO>(result.getCurrentPage(), result.getPageSize(), dtoList, result.getRecTotal());
	}
    

    
    
    /**
     * 通过id查找字典
     * @param id
     * @return
     */
    public BaseDictDTO getDictById(String id){
    	if(id == null){
    		throw new NullPointerException("id is null");
    	}
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
    	BaseDict dict = new BaseDict();
    	this.getMapper().map(dto, dict);
    	if(dict.getDictFlag() == null){
    		dict.setDictFlag(BaseDict.unuseable);
    	}
    	this.baseDictDao.save(dict);
    }
    
    /**
     * 修改字典
     * @param dto
     */
    public void updateDict(BaseDictDTO dto){
    	BaseDict dict = this.baseDictDao.getById(dto.getId());
    	this.getMapper().map(dto, dict);
    	if(dict.getDictFlag() == null){
    		dict.setDictFlag(BaseDict.unuseable);
    	}
    	this.baseDictDao.update(dict);
    }
    
    
    /**
     * 删除字典
     * @param ids
     */
    public void deleteDicts(String[] ids){
    	for (int i = 0; i < ids.length; i++) {
			BaseDict delItem = this.baseDictDao.getById(ids[i]);
			if(delItem!=null){
				this.baseDictDao.delete(delItem);
			}
			
			if(i%20 == 0){
				this.baseDictDao.flush();
				this.baseDictDao.clear();
			}
		}
    }
    
    
    
    
}
