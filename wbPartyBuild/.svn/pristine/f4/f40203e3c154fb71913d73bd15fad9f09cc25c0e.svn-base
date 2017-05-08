package com.wb.web.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;


/**
 * 基础文件信息
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="m_base_file")
public class BaseFile extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7212362396764721112L;
	public static final short FILE_KIND_COMMON = 0;	//其他文件
	public static final short FILE_KIND_PICTURE = 1;//图片文件
	public static final short FILE_KIND_VIDEO = 2;  //视频文件
	public static final short FILE_KIND_AUDIO = 3;	//音频文件
	public static final short FILE_KIND_OFFICE= 4;  //办公文件
	
	public static final char  STATUS_UNTURN = '0';
	public static final char STATUS_FINISH = '1';
	public static final char STATUS_UNNEED = '2';
	
	private String fileName;		//文件名
	private String realName;		//文件存储名
	private Date createTime;		//创建时间
	private String fileType;		//文件类型
	private String contentType;		//MIME文件类型
	private String fileSize;		//文件大小
	private Long length;			//文件长度
	private String md5Code;			//文件md5
	private Short fileKind;			//文件种类  0:普通文件;1:图片;2.视频
	private String pattern;			//文件显示图案
	private String bigPattern;		//文件显示大图案
	private String heigth;			//高度
	private String width;			//宽度
	private String heigthRatio;  	//水平分辨率
	private String widthRatio;	    //垂直分辨率
	private String marker;		    //制造商
	private String modelNum;	    //设备型号	
	private String videoLength;		//视频时长
	private Long duration;
	private String savePath;		//文件保存位置
	private String srcPath;			//原路径
	private String transferPath;	//转换格式文件保存位置
	private Long zonePathId;		//文件保存所在区间
	private String createBy;		//创建者
	private Character turnStatus;	
	
	public BaseFile(){}
	
	
	public BaseFile(Long id){
		setId(id);
	}
	
	
	public BaseFile(String fileName, String realName,
			String fileType, String contentType, String fileSize, Long length,
			String md5Code, Short fileKind, String pattern, String bigPattern,
			String width,String heigth,String widthRatio,String heigthRatio, 
			String marker,String modelNum,
			String savePath,String transferPath, Long zonePathId,
			Character turnStatus,String createBy) {
		super();
		this.fileName = fileName;
		this.realName = realName;
		this.createTime = new Date();
		this.fileType = fileType;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.length = length;
		this.md5Code = md5Code;
		this.fileKind = fileKind;
		this.pattern = pattern;
		this.bigPattern = bigPattern;
		this.width = width;
		this.heigth = heigth;
		this.widthRatio = widthRatio;
		this.heigthRatio = heigthRatio;
		this.marker = marker;
		this.modelNum = modelNum;
		this.savePath = savePath;
		this.srcPath = savePath;
		this.transferPath = transferPath;
		this.zonePathId = zonePathId;
		this.createBy = createBy;
		this.turnStatus = turnStatus;
	}
	
	
	
	@Column(name="file_name",length=150,nullable=false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name="real_name",length=80)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="file_type",length=10,nullable=false)
	public String getFileType() {
		return fileType;
	}	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name="content_type",length=35)
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Column(name="file_size",length=30,nullable=false)
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	@Column(name="length",nullable=false)
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	
	@Column(name="md5_code",length=32,nullable=false)
	public String getMd5Code() {
		return md5Code;
	}
	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}
	
	@Column(name="file_kind",nullable=false)
	public Short getFileKind() {
		return fileKind;
	}
	public void setFileKind(Short fileKind) {
		this.fileKind = fileKind;
	}
	
	@Column(name="pattern",nullable=false)
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Column(name="big_pattern")
	public String getBigPattern() {
		return bigPattern;
	}
	public void setBigPattern(String bigPattern) {
		this.bigPattern = bigPattern;
	}
	
			
	@Column(name="heigth",length=20)
	public String getHeigth() {
		return heigth;
	}

	public void setHeigth(String heigth) {
		this.heigth = heigth;
	}

	@Column(name="width",length=20)
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

		
	
	@Column(name="marker",length=50)
	public String getMarker() {
		return marker;
	}


	public void setMarker(String marker) {
		this.marker = marker;
	}

	@Column(name="model_num",length=50)
	public String getModelNum() {
		return modelNum;
	}


	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}


	@Column(name="width_ratio",length=50)
	public String getWidthRatio() {
		return widthRatio;
	}

	public void setWidthRatio(String widthRatio) {
		this.widthRatio = widthRatio;
	}
	
	@Column(name="heigth_ratio",length=50)
	public String getHeigthRatio() {
		return heigthRatio;
	}

	public void setHeigthRatio(String heigthRatio) {
		this.heigthRatio = heigthRatio;
	}
	
	@Column(name="video_length",length=20)
	public String getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(String videoLength) {
		this.videoLength = videoLength;
	}

	@Column(name="duration")
	public Long getDuration() {
		return duration;
	}


	public void setDuration(Long duration) {
		this.duration = duration;
	}


	@Column(name="save_path",length=255,nullable=false)
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
	
	@Column(name="src_path",length=255,nullable=false)
	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}


	@Column(name="transfer_path",length=255,nullable=false)
	public String getTransferPath() {
		return transferPath;
	}
	public void setTransferPath(String transferPath) {
		this.transferPath = transferPath;
	}
	
	@Column(name="zone_path_id",nullable=false)
	public Long getZonePathId() {
		return zonePathId;
	}
	public void setZonePathId(Long zonePathId) {
		this.zonePathId = zonePathId;
	}
	
	@Column(name="create_by")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name="turn_status",length=1)
	public Character getTurnStatus() {
		return turnStatus;
	}


	public void setTurnStatus(Character turnStatus) {
		this.turnStatus = turnStatus;
	}

	
	

	
	
	
	
	
}
