package com.spr.web.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spr.core.common.bean.DataQuery;
import com.spr.core.common.bean.Page;
import com.spr.core.common.service.BaseService;
import com.spr.core.common.utils.Assert;
import com.spr.web.system.dao.ISysLogsDao;
import com.spr.web.system.dto.log.SysLogsDTO;
import com.spr.web.system.entity.SysLogs;
import com.spr.web.system.service.ISysLogsService;

@Service("sysLogsService")
@Transactional
public class SysLogsServiceImpl extends BaseService implements ISysLogsService {

	
   @Resource
   private ISysLogsDao sysLogsDao;
   
   
   /**
    * 分页查询
    * @param dq
    */
   public Page<SysLogsDTO> searchByPage(DataQuery dq) {
   		
   		Long recTotal = this.sysLogsDao.countByCondition(dq.assemblePageOffset().getQueryMap());
		dq.assembleOrderInfo(SysLogs.class, null);
		List<SysLogsDTO> resultlist = this.sysLogsDao.selectListByCondition(dq.getQueryMap());
   
   		return new Page<SysLogsDTO>(dq.getCurrentPage(), dq.getPageSize(), resultlist, recTotal);
   }
   
   
   /**
    * 查询详细
    * @param dq
    */
   public SysLogsDTO getDetailById(Long id){
   	   Assert.notNull(id, Assert.NULL_PARAM_STR("id")); 
   	   
   
   	   SysLogsDTO result = this.sysLogsDao.getDetailById(id);
   	   Assert.notNull(result,Assert.EMPTY_REOCRD_STR);
   	   
   	   return result;
   }
   
   
   
   
  
   
   
    /**
   	 * 删除
   	 * @param dto
   	 */
    public void deleteSysLogss(Long[] ids){
    	for (int i = 0; i < ids.length; i++) {
			this.sysLogsDao.deleteById(ids[i]);
			
			if(this.logger.isInfoEnabled()){
	    	  this.logger.info("Delete: ");
	        }
		}
    }
   

}
