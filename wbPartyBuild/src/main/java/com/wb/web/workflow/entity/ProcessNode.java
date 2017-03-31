package com.wb.web.workflow.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.wb.core.common.entity.UUIDEntity;


/**
 * 流程节点
 * 保存流程节点的信息，包括生成流程图的信息、需处理的事项 
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="flow_proc_node")
public class ProcessNode extends UUIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -468052513574606076L;
	
	public static final String jname = "name";
	public static final String jdes = "des";
	public static final String jtype = "type";
	public static final String jleft = "left";
	public static final String jtop = "top";
	public static final String jwidth = "width";
	public static final String jheight = "height";
	public static final String jdutyPerson = "dutyPerson";
	public static final String jdutyRole = "dutyRole";
	public static final String jfrom = "from";
	public static final String jto = "to";
	public static final String jlineName = "name";
	public static final String jlineType = "type";
	public static final String jlineCode = "lineCode";
	
	
	/**/
	public static final String START_TYPE = "start";
	public static final String CHAT_TYPE = "chat";
	
	private String nodeCode;		//节点标识符---
	private String nodeName;       //节点名称--
	private String description;     //描述--
	private Integer level;			//节点层级
	private String nodeType;		//节点类型
	private String leftPos;			//x坐标
	private String topPos;			//y坐标
	private String width;			//宽度
	private String height;			//长度
	private String lineType;		//连线的类型
	private String lineName;		//连线名称
	private String lineCode;		//连线标示符
	
	
	private Date createTime;		//创建时间
	private String updateBy;		//修改者 
	

	private ProcessDefinition definition; //所属的流程定义
	private ProcessNode preNode;	 	 //上一节点
	private Set<ProcessNode> nextNodes;	 //下一节点 

	public ProcessNode() {}
	
	public ProcessNode(String string){
		
		setId(string);
	}
	

	
	
	public ProcessNode(String nodeCode, String nodeName, String description,
		  String nodeType, String leftPos, String topPos,
			String width, String height, String updateBy,
			 ProcessDefinition definition) {
		super();
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.description = StringUtils.isBlank(description)?null:description;;
		this.nodeType = nodeType;
		this.leftPos = leftPos;
		this.topPos = topPos;
		this.width = width;
		this.height = height;
		this.createTime = new Date();
		this.updateBy = updateBy;
		this.definition = definition;
	}






	
	
	
	
	public void  updateLine(String lineCode,String lineType,String lineName,ProcessNode preNode){
		this.lineCode = lineCode;
		this.lineType = lineType;
		this.lineName = lineName;
		this.preNode = preNode;
		
	}
	
	
	
	
	
	@Column(name="node_code",length=80)
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
	
	@Column(name="node_name",length=80,nullable=false)
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	@Column(name="description",length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name="level")
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	
	@Column(name="node_type",length=30)
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	

	@Column(name="left_pos",length=10)
	public String getLeftPos() {
		return leftPos;
	}
	public void setLeftPos(String leftPos) {
		this.leftPos = leftPos;
	}
	
	@Column(name="top_pos",length=10)
	public String getTopPos() {
		return topPos;
	}
	public void setTopPos(String topPos) {
		this.topPos = topPos;
	}
	@Column(name="width",length=10)
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	@Column(name="height",length=10)
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
	
	@Column(name="line_type",length=30)
	public String getLineType() {
		return lineType;
	}
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	
	

	@Column(name="line_code",length=50)
	public String getLineCode() {
		return lineCode;
	}


	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}


	@Column(name="line_name",length=30)
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	
	
	
	@ManyToOne(targetEntity=ProcessDefinition.class)
	@JoinColumn(name="proc_defin_id")
	public ProcessDefinition getDefinition() {
		return definition;
	}
	public void setDefinition(ProcessDefinition definition) {
		this.definition = definition;
	}
	
	
	@ManyToOne(targetEntity=ProcessNode.class)
	@JoinColumn(name="pre_node_id")
	public ProcessNode getPreNode() {
		return preNode;
	}
	public void setPreNode(ProcessNode preNode) {
		this.preNode = preNode;
	}
	
	
	@OneToMany(targetEntity=ProcessNode.class,cascade={javax.persistence.CascadeType.REMOVE})
	@JoinColumn(name="pre_node_id")
	public Set<ProcessNode> getNextNodes() {
		return nextNodes;
	}
	public void setNextNodes(Set<ProcessNode> nextNodes) {
		this.nextNodes = nextNodes;
	}
	
	
	
	

	
	
	
	
	
}
