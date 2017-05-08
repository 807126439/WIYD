package com.spr.web.file.entity;

import java.io.Serializable;

import com.spr.core.common.entity.BaseEntity;

public class BaseFile extends BaseEntity implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = -4666496744649041140L;
	
	public static final short STATUS_NORMAL = 1;	//正常
	public static final short STATUS_DEL = 0;		//删除
	
	public static final short FILE_KIND_OTHER = 0;	//其他文件
	public static final short FILE_KIND_PICTURE = 1;//图片文件
	public static final short FILE_KIND_VIDEO = 2;  //视频文件
	public static final short FILE_KIND_AUDIO = 3;	//音频文件
	public static final short FILE_KIND_OFFICE= 4;  //办公文件
	
	public static final short TURN_STATUS_NOT_TURNED = 0;
	public static final short TURN_STATUS_FINISHED = 1;
	public static final short TURN_STATUS_NO_NEED = 2;

	private String fileName;

    private String pattern;

    private String bigPattern;

    private String contentType;

    private String createBy;

    private Short fileKind;

    private String fileSize;

    private String fileType;

    private Long length;

    private String md5Code;

    private String realName;

    private String savePath;

    private String transferPath; 

    private Long zonePathId;

    private Short turnStatus;

    private Long mainId;
    
    private Short status;
    
    public BaseFile(){}




  
    public BaseFile(String fileName, String pattern, String bigPattern,
			String contentType, String createBy,
			Short fileKind, String fileSize, String fileType, Long length,
			String md5Code, String realName, String savePath,
			String transferPath, 
			Long zonePathId, Short turnStatus) {
		super();
		this.fileName = fileName;
		this.pattern = pattern;
		this.bigPattern = bigPattern;
		this.contentType = contentType;
		this.createBy = createBy;
		this.fileKind = fileKind;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.length = length;
		this.md5Code = md5Code;
		this.realName = realName;
		this.savePath = savePath;
		this.transferPath = transferPath;
		this.zonePathId = zonePathId;
		this.turnStatus = turnStatus;
		this.status = STATUS_NORMAL;
	}


    
    
    



	public BaseFile(String fileName, String pattern, String createBy,
			 Short fileKind, String fileSize, String fileType,
			Long length, String savePath, Long zonePathId,
			Long mainId) {
		super();
		this.fileName = fileName;
		this.pattern = pattern;
		this.createBy = createBy;
		this.fileKind = fileKind;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.length = length;
		this.savePath = savePath;
		this.zonePathId = zonePathId;
		this.turnStatus = TURN_STATUS_NO_NEED;
		this.mainId = mainId;
	}





	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern == null ? null : pattern.trim();
    }

    public String getBigPattern() {
        return bigPattern;
    }

    public void setBigPattern(String bigPattern) {
        this.bigPattern = bigPattern == null ? null : bigPattern.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }


    public Short getFileKind() {
        return fileKind;
    }

    public void setFileKind(Short fileKind) {
        this.fileKind = fileKind;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code == null ? null : md5Code.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath == null ? null : savePath.trim();
    }

    public String getTransferPath() {
        return transferPath;
    }

    public void setTransferPath(String transferPath) {
        this.transferPath = transferPath == null ? null : transferPath.trim();
    }

    public Long getZonePathId() {
        return zonePathId;
    }

    public void setZonePathId(Long zonePathId) {
        this.zonePathId = zonePathId;
    }

    public Short getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(Short turnStatus) {
        this.turnStatus = turnStatus;
    }


	public Long getMainId() {
		return mainId;
	}


	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}





	public Short getStatus() {
		return status;
	}





	public void setStatus(Short status) {
		this.status = status;
	}

    
   
}