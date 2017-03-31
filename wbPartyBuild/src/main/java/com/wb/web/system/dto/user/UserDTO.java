package com.wb.web.system.dto.user;


import java.util.Date;

import com.wb.core.common.dto.UUIDDto;
import com.wb.web.system.entity.EduDegree;

public class UserDTO extends UUIDDto{


	private String userName;			  	// 用户名
	private String chineseName;
    private String email;					// 邮箱地址
    private String phone;
    private String linkAddress;				// 联系地址
	private String tel;						// 座机电话
	private String memo;					// 备注
    private Integer limitOnceUpNum; 	    // 限制上传选择多个文件的数量
	private Long limitOnceUpCapacity;       // 限制单个文件上传的大小
	private Date createTime;				//创建时间  
	private Boolean accountNonExpired;		//账号是否未过期
    private Boolean accountNonLocked;		//账号是否未锁定
    private Boolean credentialsNonExpired;  //账号密码是否未失效
    private Boolean enabled;				//账号是否可用
    private Boolean check = false;     		//若存在多个可选人员，当前是否被选中
    
    private Boolean isParty;			//是否为党员
    private String sex;					//性别
    private Short age;					//年龄
    private EduDegree eduDegree;		//教育程度
    private Short partyStatus;		    //0:无   1 在册 2流动
    private String eduDegreeId;			//教育程度
    private Boolean isIgnoreStat;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getLimitOnceUpNum() {
		return limitOnceUpNum;
	}
	public void setLimitOnceUpNum(Integer limitOnceUpNum) {
		this.limitOnceUpNum = limitOnceUpNum;
	}
	public Long getLimitOnceUpCapacity() {
		return limitOnceUpCapacity;
	}
	public void setLimitOnceUpCapacity(Long limitOnceUpCapacity) {
		this.limitOnceUpCapacity = limitOnceUpCapacity;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public Boolean getIsParty() {
		return isParty;
	}
	public void setIsParty(Boolean isParty) {
		this.isParty = isParty;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public EduDegree getEduDegree() {
		return eduDegree;
	}
	public void setEduDegree(EduDegree eduDegree) {
		this.eduDegree = eduDegree;
	}
	public Short getPartyStatus() {
		return partyStatus;
	}
	public void setPartyStatus(Short partyStatus) {
		this.partyStatus = partyStatus;
	}
	public String getEduDegreeId() {
		return eduDegreeId;
	}
	public void setEduDegreeId(String eduDegreeId) {
		this.eduDegreeId = eduDegreeId;
	}
	public Boolean getIsIgnoreStat() {
		return isIgnoreStat == null?false:isIgnoreStat;
	}
	public void setIsIgnoreStat(Boolean isIgnoreStat) {
		this.isIgnoreStat = isIgnoreStat;
	}
	
    
   
	
    
	
	
}
