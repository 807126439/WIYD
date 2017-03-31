package com.wb.web.party.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.PropertiesUtil;
import com.wb.web.party.dao.IJoinPartyInfoDao;
import com.wb.web.party.dto.JoinPartyInfoDTO;
import com.wb.web.party.dto.JoinPartyQueryDTO;
import com.wb.web.party.dto.JoinPartyStatsDTO;
import com.wb.web.party.dto.PartyFileDTO;
import com.wb.web.party.entity.JoinPartyInfo;
import com.wb.web.party.service.IJoinPartyInfoService;
import com.wb.web.system.entity.User;
import com.wb.web.workflow.dao.IAttachmentDao;
import com.wb.web.workflow.dao.IProcessExecutionDao;
import com.wb.web.workflow.dto.node.CurrNodeDTO;
import com.wb.web.workflow.dto.node.ProcessData;
import com.wb.web.workflow.entity.Attachment;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IProcessNodeService;
import com.wb.web.workflow.service.ITaskService;

@Service("joinPartyInfoService")
@Transactional
public class JoinPartyInfoServiceImpl extends BaseService implements IJoinPartyInfoService{

	@Resource
	private IJoinPartyInfoDao joinPartyInfoDao;
	@Resource
	private IProcessNodeService processNodeService;
	@Resource
	private IProcessExecutionDao processExecutionDao;
	@Resource
	private IAttachmentDao attachmentDao;
	@Resource
	private ITaskService taskService;
	
	public static String JOIN_PARTY_PROCESS_CODE = null;
	
	@PostConstruct
	public void init(){
		 String classBaseDir =  JoinPartyInfoServiceImpl.class.getClassLoader().getResource("/").getPath();		
		 String propertiesfile = classBaseDir+"systemConfig.properties";
			
			
		 String partyCode  = PropertiesUtil.GetValueByKey(propertiesfile, "party_process_code");
		 this.logger.info("GET partyCode value："+partyCode+" from properties");
		 if(!StringUtils.isBlank(partyCode)){
			 JOIN_PARTY_PROCESS_CODE = partyCode;
		 }else{
			 throw new RuntimeException("the value of  partyCode is empty in properties file");
		 }
			
		
	}
	
	
	public Page<JoinPartyInfoDTO> searchByPage(JoinPartyQueryDTO queryDTO){
		
		
		return this.joinPartyInfoDao.searchInfoByPage(queryDTO);
	}
	
	
	/**
	 * 入党信息统计
	 * @param queryDTO
	 * @return
	 */
	public Page<JoinPartyStatsDTO> searchStatsByPage(JoinPartyQueryDTO queryDTO){
		Page<JoinPartyStatsDTO> pageResult = this.joinPartyInfoDao.searchStatsByPage(queryDTO);
			
		List<Long> procInstIds = new ArrayList<Long>();
		for (JoinPartyStatsDTO st : pageResult.getList()) {
			if(st.getProcessInstanceId()!=null){
				procInstIds.add(st.getProcessInstanceId());
			}
			
		}
		
		if(!procInstIds.isEmpty()){
				
			List<CurrNodeDTO> currNodeDTOs = this.processExecutionDao.getCurrNodesByProcInstIds(procInstIds);
			List<Attachment> attachments = this.attachmentDao.getAttachmentByProcInstId(procInstIds);
			
			for (JoinPartyStatsDTO st : pageResult.getList()) {
				
				for (CurrNodeDTO cn : currNodeDTOs) {
					if(cn.getProcInstId().equals(st.getProcessInstanceId())){
						st.setCurrNode(cn.getNodeName());
						break;
					}
				}
				
				for (Attachment at : attachments) {
					if(at.getProcessInstanceId().equals(st.getProcessInstanceId())){
						if(at.getType().equals(Task.SQZ_TYPE)){
							st.getApplyFiles().add(new PartyFileDTO(at.getId(), at.getName()));
							
						}else if(at.getType().equals(Task.CLZ_TYPE)){
							st.getHandleFiles().add(new PartyFileDTO(at.getId(), at.getName()));
						}
					}
				}
				
				
			}
		}
		
		return pageResult;
	}
	
	
	
	
	
	
	
	
	public JoinPartyInfoDTO getDetailById(Long id){
		return this.joinPartyInfoDao.getDetailById(id);
	}
	
	
	
	/**
	 * 添加入党申请信息
	 * @param dto
	 */
	public void addJoinPartyInfo(JoinPartyInfoDTO dto){
		Assert.notNull(dto.getApplyUserId(), "申请人不能为空！");
		
		
		JoinPartyInfo info = new JoinPartyInfo(new User(dto.getApplyUserId()), dto.getDepartment(),
				dto.getMemo(), JoinPartyInfo.NO_START_STATUS);
		
		this.joinPartyInfoDao.save(info);
		
	}
	
	
	/**
	 * 修改入党申请信息
	 * @param dto
	 */
	public void updateJoinPartyInfo(JoinPartyInfoDTO dto){
		Assert.notNull(dto.getId(), "id could not be null");
		Assert.notNull(dto.getApplyUserId(), "申请人不能为空！");

		JoinPartyInfo info = this.joinPartyInfoDao.getById(dto.getId());
		if(info!=null){
			if(!dto.getApplyUserId().equals(info.getApplyUser().getId())){
				if(info.getProcessInstanceId()!=null){
					throw new MyException("申请已经在执行状态，不可修改申请人！");
				}							
			}
			
			info.setApplyUser(new User(dto.getApplyUserId()));
			info.setMemo(dto.getMemo());
			
		}
		
		
		this.joinPartyInfoDao.save(info);
		
	}
	
	
	/**
	 * 开始入党申请
	 * @param id
	 */
	public void startJoinPartyApply(Long id){
		Assert.notNull(id, "id could not be null");
		
		JoinPartyInfo info = this.joinPartyInfoDao.getById(id);
		if(info!=null){
			if(info.getProcessInstanceId() == null){
				
				Long procInstId = this.processNodeService.startProcess(JOIN_PARTY_PROCESS_CODE, info.getApplyUser().getId());
				
				info.setProcessInstanceId(procInstId);
				info.setStatus(JoinPartyInfo.RUNNING_STATUS);
				info.setStartTime(new Date());
				
				this.joinPartyInfoDao.save(info);
			}else{
				throw new MyException("申请已提交，不可重复提交！");
			}
			
		}
		
	}
	
	
	
	/**
	 * 删除入党申请信息
	 * @param ids
	 */
	public void deleteJoinPartyInfo(Long[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			JoinPartyInfo info = this.joinPartyInfoDao.getById(ids[i]);
			//删除有关流程的所有数据
			if(info!=null ){
				if(info.getProcessInstanceId()!=null){
					this.taskService.deleteProcessByProcInstId(info.getProcessInstanceId());
				}
				
				this.joinPartyInfoDao.delete(info);
			}
		}
		
	}
	
	
	/**
	 * 查询个人入党申请的流程信息
	 * @param userId
	 * @return
	 */
	public JoinPartyInfoDTO getJoinProcessByUserId(String userId){
		Assert.notNull(userId, "userId must not be null");
		
		JoinPartyInfoDTO info = this.joinPartyInfoDao.getDetailByApplyUserId(userId);
		if(info == null){
			return null;
		}
		ProcessData data = this.processNodeService.geSerialProcess(info.getProcessInstanceId(), info.getApplyUserId());
		info.setProcessData(data);
		
		return info;
	}
	
}
