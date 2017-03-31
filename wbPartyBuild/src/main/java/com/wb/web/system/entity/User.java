package com.wb.web.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.UUIDEntity;

@Entity
@Table(name="c_user")
public class User extends UUIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7540227577957229838L;
	
  
	private String userName;			  	//用户名
	private String chineseName;				//姓名
	private String password;				// 密码
    private String email;					// 邮箱地址
	private String phone;					// 手机号码
	private String linkAddress;				// 联系地址
	private String tel;						// 座机电话
	private String memo;					// 备注
    private Integer limitOnceUpNum = 5; 	// 限制上传选择多个文件的数量
	private Long limitOnceUpCapacity = 52428800l; // 限制单个文件上传的大小
	private Date createTime;				//创建时间  
	private Boolean accountNonExpired = true;		//账号是否未过期
    private Boolean accountNonLocked = true;		//账号是否未锁定
    private Boolean credentialsNonExpired = true;  //账号密码是否未失效
    private Boolean enabled = true;				//账号是否可用
    private Set<Role> roles = new HashSet<Role>();
    
    private Boolean isDel;	//账号是否删除
    private Boolean isIgnoreStat; //是否忽略统计
    
    private Boolean isParty;			//是否为党员
    private String sex;					//性别
    private Short age;					//年龄
    private EduDegree eduDegree;			//教育程度
    private Short partyStatus;		    //0:无   1 在册 2流动
    
	
    public User(){}
    
    public User(String id){
    	setId(id);
    }
    
	public User(String userName,String chineseName,String password,String email, String phone, 
			String linkAddress,String tel,String memo,Integer limitOnceUpNum, Long limitOnceUpCapacity,Boolean isIgnoreStat) {
		super();
		this.userName = userName;
		this.chineseName = chineseName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.linkAddress = linkAddress;
		this.tel = tel;
		this.memo = memo;
		this.limitOnceUpNum = limitOnceUpNum;
		this.limitOnceUpCapacity = limitOnceUpCapacity;
		this.createTime = new Date();
		this.roles = new HashSet<Role>();
		this.isDel = false;
		this.isIgnoreStat = isIgnoreStat;
		this.isParty = false;
	}
    
    @Column(name="user_name",length=50,nullable=false,unique=true)
    public String getUserName() {
		return userName;
	}
    
	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
		
	@Column(name="chinese_name",length=50)
    public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name="pass_word",length=64,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="email",length=100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="phone",length=20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="link_address",length=120)
	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	@Column(name="tel",length=20)	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name="memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name="limit_once_up_num")
	public Integer getLimitOnceUpNum() {
		return limitOnceUpNum;
	}
	public void setLimitOnceUpNum(Integer limitOnceUpNum) {
		this.limitOnceUpNum = limitOnceUpNum;
	}
	
	@Column(name="limit_once_up_capacity")
	public Long getLimitOnceUpCapacity() {
		return limitOnceUpCapacity;
	}
	public void setLimitOnceUpCapacity(Long limitOnceUpCapacity) {
		this.limitOnceUpCapacity = limitOnceUpCapacity;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="account_non_expired",nullable=false)
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	@Column(name="account_non_locked",nullable=false)
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	@Column(name="credentials_non_expired",nullable=false)
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	@Column(name="enabled",nullable=false)
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToMany(targetEntity=Role.class)
	@JoinTable(name="c_user_role",joinColumns={@JoinColumn(name="user_id")},
	   inverseJoinColumns={@JoinColumn(name="role_id")})
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(name="is_party",nullable=false)
	public Boolean getIsParty() {
		return isParty;
	}

	public void setIsParty(Boolean isParty) {
		this.isParty = isParty;
	}

	@Column(name="sex",length=1)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="age")
	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}


	@Column(name="party_status")
	public Short getPartyStatus() {
		return partyStatus;
	}

	public void setPartyStatus(Short partyStatus) {
		this.partyStatus = partyStatus;
	}

	@ManyToOne(targetEntity=EduDegree.class)
	@JoinColumn(name="edu_degree_id")
	public EduDegree getEduDegree() {
		return eduDegree;
	}

	public void setEduDegree(EduDegree eduDegree) {
		this.eduDegree = eduDegree;
	}

	@Column(name="is_del",nullable=false)
	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Column(name="is_ignore_stat")
	public Boolean getIsIgnoreStat() {
		return isIgnoreStat;
	}

	public void setIsIgnoreStat(Boolean isIgnoreStat) {
		this.isIgnoreStat = isIgnoreStat;
	}
	
	
	
	
	

	
}
