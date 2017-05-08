package com.spr.web.system.entity;

import java.io.Serializable;

import com.spr.core.annotations.DbField;
import com.spr.core.common.entity.UUIDEntity;

/**
 * 用户
 * @author wb_java_zjr
 *
 */
public class User extends UUIDEntity implements Serializable{

   

    /**
	 * 
	 */
	private static final long serialVersionUID = 70102558602794410L;
	
	public static final short CUST_USER = 1;
	public static final short BACK_USER = 0;

	private String userName;

    private String chineseName;

    private String passWord;

    private String email;

    private String phone;

    private String memo;

    private Boolean enabled;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

    private Boolean accountNonExpired;

    private Short userType;


    
    public User(){}
    
    
    
    
    
    
    
    
   /**
    * 
    * @param userName
    * @param chineseName
    * @param passWord
    * @param email
    * @param userType
    * @param phone
    * @param memo
    */
    public User(String userName, String chineseName, String passWord,
			String email, Short userType, String phone, String memo) {
		super();
		this.userName = userName;
		this.chineseName = chineseName;
		this.passWord = passWord;
		this.email = email;
		this.userType = userType;
		this.phone = phone;
		this.memo = memo;
		this.enabled = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.accountNonExpired = true;
	
		
	}


    



	




	@DbField(name="user_name")
	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @DbField(name="chinese_name")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    @DbField(name="pass_word")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    @DbField(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @DbField(name="user_type")
    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    @DbField(name="phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @DbField(name="memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    @DbField(name="enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @DbField(name="credentials_non_expired")
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }


    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @DbField(name="account_non_locked")
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @DbField(name="account_non_expired")
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

  

    
    

    
    	
}