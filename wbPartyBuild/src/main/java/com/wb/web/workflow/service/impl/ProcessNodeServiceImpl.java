 package com.wb.web.workflow.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.party.dao.IJoinPartyInfoDao;
import com.wb.web.party.entity.JoinPartyInfo;
import com.wb.web.party.service.impl.JoinPartyInfoServiceImpl;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.entity.User;
import com.wb.web.workflow.dao.IProcessDefinitionDao;
import com.wb.web.workflow.dao.IProcessExecutionDao;
import com.wb.web.workflow.dao.IProcessIdentitylinkDao;
import com.wb.web.workflow.dao.IProcessNodeDao;
import com.wb.web.workflow.dao.IRunIdentitylinkDao;
import com.wb.web.workflow.dao.ITaskDao;
import com.wb.web.workflow.dto.node.ChoiceNodeDTO;
import com.wb.web.workflow.dto.node.NodeDTO;
import com.wb.web.workflow.dto.node.ProcessData;
import com.wb.web.workflow.dto.node.ProcessNodeDTO;
import com.wb.web.workflow.dto.node.ProcessNodeQueryDTO;
import com.wb.web.workflow.dto.node.UpdateProcessNodeDTO;
import com.wb.web.workflow.entity.ProcessDefinition;
import com.wb.web.workflow.entity.ProcessExecution;
import com.wb.web.workflow.entity.ProcessIdentitylink;
import com.wb.web.workflow.entity.ProcessNode;
import com.wb.web.workflow.entity.RunIdentitylink;
import com.wb.web.workflow.entity.Task;
import com.wb.web.workflow.service.IProcessDefinitionService;
import com.wb.web.workflow.service.IProcessNodeService;

@Service("processNodeService")
@Transactional
public class ProcessNodeServiceImpl extends BaseService implements IProcessNodeService{
	
	@Resource
	private IProcessNodeDao processNodeDao;
	@Resource
	private IProcessDefinitionDao processDefinitionDao;
	@Resource
	private IProcessExecutionDao processExecutionDao;
	@Resource
	private ITaskDao taskDao;
	@Resource
	private IProcessDefinitionService processDefinitionService;
	@Resource
	private IProcessIdentitylinkDao processIdentitylinkDao;
	@Resource
	private IRunIdentitylinkDao runIdentitylinkDao;
	@Resource
	private IUserDao userDao;
	@Resource
	private IJoinPartyInfoDao joinPartyInfoDao;
	
	
	
	/**
	 * 获取当前流程执行点的流程节点码
	 * @param procInstId
	 * @return
	 */
	public String getCurrExeNodeCode(Long procInstId){
		ProcessNode node = this.processExecutionDao.getCurrentProcessNodeByProcInstId(procInstId);
		
		if(node==null){
			throw new MyException("Could not find the record!");
		}
		
		return node.getNodeCode();
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 添加流程
	 * 流程图数据转化为数据库存储格式
	 */
	public void addProcess(Long procDefinId,String data){
		Assert.notNull(procDefinId, "procDefinId could not be null");
		
		ProcessDefinition processDefinition = this.processDefinitionDao.getById(procDefinId);
		//如果之前流程节点存在的（表示这次操作为修改）则新建相同流程标识符的流程定义
		if(this.processNodeDao.checkIsExistByProperty("definition.id", procDefinId, null)){
			
			//流程已经拥有实例的，重新创建一个流程定义
			if(this.processExecutionDao.checkIsExistByProperty("processDefinition.id", procDefinId, null)){
				processDefinition = this.processDefinitionService.addSameCodeDefinition(processDefinition.getId());
			
			}else{//删除流程定义
				
				this.processNodeDao.deleteByDefiniId(processDefinition.getId());
			}
			
		}
		
	
		
		if(processDefinition!=null){
			JSONObject model = JSONObject.fromObject(data);
			Assert.isTrue(model.containsKey("nodes"));
			Assert.isTrue(model.containsKey("lines"));
			JSONObject nodes = model.getJSONObject("nodes");
			JSONObject lines = model.getJSONObject("lines");
			
			
			Set<ProcessNode> nodesSet = new HashSet<ProcessNode>();
			@SuppressWarnings("unchecked")
			Iterator<Entry<String, Object>> nodeIterable = nodes.entrySet().iterator();
			while (nodeIterable.hasNext()) {	
				 Entry<String, Object> entry =  nodeIterable.next();
				 
				 JSONObject nd = JSONObject.fromObject(entry.getValue());
				
				 ProcessNode node = new ProcessNode(
						 entry.getKey(), nd.getString(ProcessNode.jname),
						 nd.containsKey(ProcessNode.jdes)?nd.getString(ProcessNode.jdes):null,
						 nd.getString(ProcessNode.jtype), nd.getString(ProcessNode.jleft), nd.getString(ProcessNode.jtop),
						 nd.getString(ProcessNode.jwidth), nd.getString(ProcessNode.jheight), 
						 getNowUser().getUsername(), 			
						processDefinition);
			 
				this.processNodeDao.save(node);  

				
				if(nd.containsKey(ProcessNode.jdutyPerson)){
					JSONArray personArray = nd.getJSONArray(ProcessNode.jdutyPerson);
					if(personArray.isArray()){
						for (Object obj : personArray) {
							String userId = (String) obj;
							if(!StringUtils.isBlank(userId)){
								ProcessIdentitylink link = new ProcessIdentitylink(node, userId, null,ProcessIdentitylink.assignee);
								this.processIdentitylinkDao.save(link);
							}
							
						}
					}
				}
						
				if(nd.containsKey(ProcessNode.jdutyRole)){
					JSONArray roleArray = nd.getJSONArray(ProcessNode.jdutyRole);
					if(roleArray.isArray()){
						for (Object obj : roleArray) {
							String roleId = (String) obj;
							if(!StringUtils.isBlank(roleId)){
								ProcessIdentitylink link = new ProcessIdentitylink(node, null, roleId,ProcessIdentitylink.assignee);
								this.processIdentitylinkDao.save(link);
							}
						}
					}	
				}	
				
				nodesSet.add(node); 
			}
			
			
			for (ProcessNode node : nodesSet) {
				JSONObject line = this.getPreNodeFromLines(lines, node.getNodeCode());
				if(line!=null){
					ProcessNode preNode = this.getNodeByCode(nodesSet, line.getString(ProcessNode.jfrom));
					if(preNode!=null){
						node.updateLine(line.getString(ProcessNode.jlineCode),
										line.getString(ProcessNode.jlineType), 
										line.getString(ProcessNode.jlineName), preNode);
					}
				}
				
				
			}
		
			
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private JSONObject getPreNodeFromLines(JSONObject lines,String nodeCode){
			
		  Iterator<Entry<String, Object>> iterable = lines.entrySet().iterator();
		  while (iterable.hasNext()) {
			 Entry<String, Object> entry =  iterable.next();
			 JSONObject line = JSONObject.fromObject(entry.getValue());
			 if(line.containsKey("to")){
				String to = line.getString("to");
				if(nodeCode.equals(to)){
					line.put(ProcessNode.jlineCode, entry.getKey());
					return line;
				}
			 }
		
			
		}	
		
		return null;
	}

	private ProcessNode getNodeByCode(Set<ProcessNode> nodes,String code){
		
		for (ProcessNode pn : nodes) {
			if(pn.getNodeCode().equals(code)){
				return pn;
			}
		}
		
		return null;
	}
	
	

	/**
	 * 获取流程图的数据
	 * 转化为流程图所需的格式数据
	 */
	public JSONObject getProcessData(Long procDefinId){
		Assert.notNull(procDefinId, "procDefinId could not be null");
		List<ProcessNode> nodes = this.processNodeDao.getNodesByProcDefinId(procDefinId);
		
		if(nodes.isEmpty()){
			return null;
		}
		
		JSONObject data = new JSONObject();
		data.put("title", "test");

		
		JSONObject jnodes = new JSONObject();
		for (ProcessNode pn : nodes) {
			JSONObject nd = new JSONObject();
			nd.put(ProcessNode.jname, pn.getNodeName());
					
			nd.put(ProcessNode.jleft,Integer.parseInt( pn.getLeftPos()));
			nd.put(ProcessNode.jtop, Integer.parseInt(pn.getTopPos()));
			nd.put(ProcessNode.jtype, pn.getNodeType());
			if(!StringUtils.isBlank(pn.getDescription())){
				nd.put(ProcessNode.jdes, pn.getDescription());
			}	
			nd.put(ProcessNode.jwidth,Integer.parseInt( pn.getWidth()));
			nd.put(ProcessNode.jheight, Integer.parseInt(pn.getHeight()));
			
			
			List<ProcessIdentitylink> persons = this.processIdentitylinkDao.getProcessIdentitylinkByNodeId(pn.getId(), true);
			if(!persons.isEmpty()){
				JSONArray array = new JSONArray();
				
				for (ProcessIdentitylink link : persons) {
					array.add(link.getUserId());
				}
				
				nd.put(ProcessNode.jdutyPerson, array);
			}
			
			List<ProcessIdentitylink> groups = this.processIdentitylinkDao.getProcessIdentitylinkByNodeId(pn.getId(), false);
			if(!groups.isEmpty()){
				JSONArray array = new JSONArray();
				
				for (ProcessIdentitylink link : groups) {
					array.add(link.getGroupId());
				}
				
				nd.put(ProcessNode.jdutyRole, array);
			}
	
		
			jnodes.put(pn.getNodeCode(), nd);
		}
		
		data.put("nodes", jnodes);
		
		JSONObject jlines= new JSONObject();
		for (ProcessNode pn : nodes) {
	
			if(pn.getPreNode()!=null){
				JSONObject line = new JSONObject();
				line.put(ProcessNode.jlineType, pn.getLineType());
				line.put(ProcessNode.jfrom, pn.getPreNode().getNodeCode());
				line.put(ProcessNode.jto, pn.getNodeCode());
				
				line.put(ProcessNode.jlineName, pn.getLineName());
				
				jlines.put(pn.getLineCode(), line);
			}
		}
		
		data.put("lines", jlines);
		
		
		data.put("areas", new JSONObject());
		data.put("initNum", jnodes.size() + jlines.size()+1);
		
		return data;
	}
	
	
	/**
	 * 启动流程
	 * @param processCode 流程码
	 * @param customerId  申请用户id
	 * @return 流程实例id
	 */
	public Long startProcess(String processCode,String customerId){
		Assert.hasText(processCode, "ProcessCode could not be null");
		Assert.hasText(customerId, "customerId could not be null");
		
		User owner = this.userDao.getById(customerId);
		Assert.notNull(owner, "Could not find the user!");
		
		ProcessDefinition definition = processDefinitionDao.getLastVersionDefinition(processCode);
		Assert.isTrue(definition!=null, "无相关的流程定义，启动流程失败!");
		if(definition.getStatus() == ProcessDefinition.NORMAL_STATUS){
			
			ProcessNode startNode = this.processNodeDao.getStartNodesByProcDefinId(definition.getId());
			Assert.isTrue(startNode!=null, "无相关的流程数据，启动流程失败!");		
				
			ProcessExecution execution = new ProcessExecution(definition,
										 null,null, startNode, true,owner.getUserName());
			
			this.processExecutionDao.save(execution);
			execution.setProcessInstance(execution);
			
			//检查申请人是否有任务
			List<ProcessNode> ownerTasks = this.processNodeDao.getOwnerTaskByPreNode(startNode.getId());
			for (ProcessNode pn : ownerTasks) {
				ProcessExecution ownExecution = new ProcessExecution(definition,
						 execution,execution, pn, false,owner.getUserName());
				this.processExecutionDao.save(ownExecution);
				 
				Task task = new Task(definition, execution, ownExecution,pn.getNodeName(),pn.getDescription(),
						Task.SQZ_TYPE, owner.getUserName(),null);
				
				this.taskDao.save(task);
			}
			
			Task task = new Task(definition, execution, execution,startNode.getNodeName(),startNode.getDescription(),
					Task.CLZ_TYPE,owner.getUserName(),null);
			this.taskDao.save(task);
			
			List<ProcessIdentitylink> links = this.processIdentitylinkDao.getProcessIdentitylinkByNodeId(startNode.getId(), null);
			for (ProcessIdentitylink lk : links) {
				RunIdentitylink rlk = new RunIdentitylink(lk.getGroupId(), lk.getUserId(), task, execution, definition, lk.getType());
				this.runIdentitylinkDao.save(rlk);
			}
			
	
	
			return execution.getId();
		
		}else{
			throw new MyException("流程已失效！");
		}
		
		
	}
	
	
	/**
	 * 进入下一步流程
	 * @param currExeId		当前流程执行id
	 * @param choiceNodeId  选择下一步流程节点id
	 */
	public void goNextStep(Long currExeId,String choiceNodeId){
		Assert.notNull(currExeId, "currExeId could not be null");
		Assert.hasText(choiceNodeId, "choiceNodeId could not be null");
		
		ProcessExecution currExecution = this.processExecutionDao.getById(currExeId);
		ProcessExecution processInstance =  currExecution.getProcessInstance();
		
		ProcessNode theNode = currExecution.getProcessNode();
		ProcessNode nextNode = this.processNodeDao.getById(choiceNodeId);
		
		Assert.notNull(nextNode.getPreNode(), "error choice node");
		Assert.isTrue(theNode.getId().equals(nextNode.getPreNode().getId()), "error choice node (parent is different)");
		
		currExecution.setStatus(ProcessExecution.OVER_STATUS);
		this.processExecutionDao.update(currExecution);
		
		//进入下一步流程执行
		ProcessExecution nextExecution = new ProcessExecution(
				nextNode.getDefinition(), currExecution.getProcessInstance(),currExecution, 
				nextNode,true, processInstance.getUserId());
		this.processExecutionDao.save(nextExecution);
		
		
		List<ProcessNode> ownerTasks = this.processNodeDao.getOwnerTaskByPreNode(nextNode.getId());
		for (ProcessNode pn : ownerTasks) {
			 ProcessExecution ownExecution = new ProcessExecution(nextNode.getDefinition(),
					 processInstance,nextExecution, pn, false,processInstance.getUserId());
			this.processExecutionDao.save(ownExecution);
			 
			Task task = new Task(nextNode.getDefinition(), processInstance, ownExecution,
					pn.getNodeName(),pn.getDescription(),Task.SQZ_TYPE,		
					processInstance.getUserId(),null);
			
			this.taskDao.save(task);
		}
		
		Task task = new Task(nextNode.getDefinition(), processInstance, nextExecution,nextNode.getNodeName(),nextNode.getDescription(),			
						Task.CLZ_TYPE, processInstance.getUserId(),null);
		
		this.taskDao.save(task);
		
		List<ProcessIdentitylink> links = this.processIdentitylinkDao.getProcessIdentitylinkByNodeId(nextNode.getId(), null);
		for (ProcessIdentitylink lk : links) {
			RunIdentitylink rlk = new RunIdentitylink(lk.getGroupId(), lk.getUserId(), task, processInstance, processInstance.getProcessDefinition(), lk.getType());
			this.runIdentitylinkDao.save(rlk);
		}
		
		
	}
	
	
	
	/**
	 * 结束流程
	 * @param currExeId 当前流程实例id
	 */
	public void endProcess(Long currExeId){
		Assert.notNull(currExeId, "currExeId could not be null");
		
		ProcessExecution currExecution = this.processExecutionDao.getById(currExeId);
		
		if(this.processNodeDao.isEndProcessNode(currExecution.getProcessNode().getId())){
			throw new MyException("存在下一步流程节点，无法结束流程！");
		}
		
		ProcessDefinition definition = currExecution.getProcessDefinition();
		if(definition.getProcessCode().equals(JoinPartyInfoServiceImpl.JOIN_PARTY_PROCESS_CODE)){
			JoinPartyInfo info = this.joinPartyInfoDao.getByProcInstId(currExecution.getProcessInstance().getId());
			info.setStatus(JoinPartyInfo.END_STATUS);
			
			this.joinPartyInfoDao.update(info);
		}
		
		
	}
	
	
	
	
	
	
	
	/**
	 * 获取直线流程过程
	 * @param procInstId 流程实例id
	 * @return
	 */
	public ProcessData geSerialProcess(Long procInstId,String applyUserId){
		Assert.notNull(procInstId, "procInstId could not be null");
		ProcessExecution currExecution = this.processExecutionDao.getCurrentExecutionByProcInstId(procInstId);
		
		
		List<ProcessNode> nodes = this.processNodeDao.getNodesByProcDefinId(currExecution.getProcessDefinition().getId());
		if(!nodes.isEmpty()){
			List<NodeDTO> result = new ArrayList<NodeDTO>();
			Assert.isTrue(nodes.get(0).getPreNode() == null, "流程定义开始节点错误！");
			
			int mark = 0;
					
			
			NodeDTO firstNode = new NodeDTO(nodes.get(0).getId(),
					nodes.get(0).getNodeName(), nodes.get(0).getNodeType(), 
					null,null);
			ProcessNode firstOwner = this.getNextByCycleNodes(nodes, nodes.get(0).getId(),false);
			if(firstOwner!=null){
				NodeDTO firstOwnerPn = new NodeDTO(firstOwner.getId(),
						firstOwner.getNodeName(), firstOwner.getNodeType(), 
						firstNode.getId(),null);
				
				firstNode.setOwnerNode(firstOwnerPn);
			}
			
			result.add(firstNode);
			
			NodeDTO tempNode = firstNode;		
			boolean singal = true;
			int i =0;
			while (singal) {
				i++;
								
				ProcessNode pn = this.getNextByCycleNodes(nodes, tempNode.getId(),true);
				if(pn!=null){
					if(currExecution.getProcessNode().getId().equals(pn.getId())){
						mark = i;
					}
					NodeDTO nd = new NodeDTO(pn.getId(),
							pn.getNodeName(), pn.getNodeType(), 
							tempNode.getId(),null);
					
					ProcessNode ndOwner = this.getNextByCycleNodes(nodes,pn.getId(),false);
					if(ndOwner!=null){
						NodeDTO ndOwnerPn = new NodeDTO(ndOwner.getId(),
								ndOwner.getNodeName(), ndOwner.getNodeType(), 
								ndOwner.getId(),null);
						
						nd.setOwnerNode(ndOwnerPn);
					}
					
				    result.add(nd);
				    tempNode = nd;
				}else{
					singal = false;
				}
				
			}
			
			for (int j = 0; j < result.size(); j++) {
				if(j<mark){
					result.get(j).setStatus(NodeDTO.HAS_DONE);
				}else if(j>mark){
					result.get(j).setStatus(NodeDTO.NO_DONE);
				}else{
					result.get(j).setStatus(NodeDTO.IS_CURR);
				}
			}
			
			
			return new ProcessData(currExecution.getId(), result);
			
		}else{
			throw new MyException("流程数据为空！");
		}
		
	}
	
	
	private ProcessNode getNextByCycleNodes(List<ProcessNode> nodes,String preNodeId,Boolean isMain){
		
		for (ProcessNode pn : nodes) {
			if(pn.getPreNode()!=null){
				if(pn.getPreNode().getId().equals(preNodeId)){
					if(isMain){
						if(!pn.getNodeType().equals(ProcessNode.CHAT_TYPE)){
							return pn;
						}
					}else{
						if(pn.getNodeType().equals(ProcessNode.CHAT_TYPE)){
							return pn;
						}
					}
					
				}
			}
		}
		
		return null;
	}
	
	
    /**
     * 获取下级节点信息
     * @param nodeId
     * @return
     */
	public List<ChoiceNodeDTO> getNextNodes(String nodeId){
		Assert.hasText(nodeId, "nodeId could not be null");
		
		return this.processNodeDao.getNextNodes(nodeId);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
	/*	String str = "{\"nodes\":{\"demo_node_1\":{\"name\":\"子树入口\",\"left\":295,\"top\":0,\"type\":\"start round\",\"width\":24,\"height\":24}," +
				
				"\"demo_node_2\":{\"name\":\"入口\",\"left\":295,\"top\":0,\"type\":\"start round\",\"width\":24,\"height\":24} } }";
	
	  JSONObject obj = JSONObject.fromObject(str);
	  JSONObject a = obj.getJSONObject("nodes");
	  Iterator<Entry<String, Object>> iterable = a.entrySet().iterator();
	  while (iterable.hasNext()) {
		 Entry<String, Object> entry = (Entry<String, Object>) iterable.next();
		 System.out.println(entry.getKey());
		 JSONObject b = JSONObject.fromObject(entry.getValue());
		 
		 System.out.println(b.get("name"));
		
	}*/
	  
	 
	
	}


	/**
	 * 分页查询列表
	 * @param queryDTO
	 * @return
	 */
	@Override
	public Page<ProcessNodeDTO> searchProcessNodeByCondition(
			ProcessNodeQueryDTO queryDTO) {
		return processNodeDao.searchProcessNodeByCondition(queryDTO);
	}


	/**
	 * 根据编号查找单条记录
	 * @param id
	 * @return
	 */
	@Override
	public ProcessNode getNodesById(String id) {
		Assert.hasText(id, "id could not be null");
		if (id!=null) {
			return processNodeDao.getById(id);
		}else {
			return null;
		}
		
	}

	/**
	 * 修改流程节点信息
	 * @param nodeId 	    流程节点对象
	 * @param roleIds	    角色表ID的集合
	 * @param userId	    用户表ID的集合
	 */
	@Override
	public void updateProcessNode(UpdateProcessNodeDTO dto, String[] roleIds,
			String[] userId) {
		Assert.hasText(dto.getId(), "id could not be null");
		Assert.hasText(dto.getNodeName2(), "节点名称不能为空");
		ProcessNode processNode = processNodeDao.getById(dto.getId());
		processNode.setNodeCode(dto.getNodeCode());
		processNode.setNodeName(dto.getNodeName2());
		processNode.setDescription(dto.getDescription());
		processNode.setNodeType(dto.getNodeType2());
		processNode.setLeftPos(dto.getLeftPos());
		processNode.setTopPos(dto.getTopPos());
		processNode.setWidth(dto.getWidth());
		processNode.setHeight(dto.getHeight());
		processNode.setLineType(dto.getLineType());
		processNode.setLineName(dto.getLineName());
		processNode.setLineCode(dto.getLineCode());
		processNode.setUpdateBy(this.getNowUser().getUsername());


		List<ProcessIdentitylink>  processIdentitylinks=processIdentitylinkDao.getProcessIdentitylinksById(processNode.getId());
		if (processIdentitylinks!=null) {
			for (ProcessIdentitylink processIdentitylink : processIdentitylinks) {
				this.processIdentitylinkDao.delete(processIdentitylink);
			}
			if (roleIds!=null) {
				for (String roleId : roleIds) {
					ProcessIdentitylink processIdentitylink2=new ProcessIdentitylink(
							new ProcessNode(processNode.getId()),
							null,
							roleId,
							ProcessIdentitylink.assignee
						);
					this.processIdentitylinkDao.save(processIdentitylink2);
				}
			}
			if (userId!=null) {
				for (String userid : userId) {
					ProcessIdentitylink processIdentitylink2=new ProcessIdentitylink(
							new ProcessNode(processNode.getId()),
							userid,
							null,
							ProcessIdentitylink.assignee
						);
					this.processIdentitylinkDao.save(processIdentitylink2);
				}
			}
			
		}
		this.processNodeDao.update(processNode);
		
	}



}
