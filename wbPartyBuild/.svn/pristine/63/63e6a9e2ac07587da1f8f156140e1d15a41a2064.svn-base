package com.wb.core.spring.security.authentication.dao;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.wb.core.spring.security.authentication.SSOAuthenticationToken;
import com.wb.core.utils.PropertiesUtil;
import com.wb.core.utils.http.HttpUtils;


/**
 * 
 * @author wb_java_zjr
 *
 */
public class SSODaoAuthenticationProvider implements AuthenticationProvider, InitializingBean,
			MessageSourceAware{
   

		protected final Log logger = LogFactory.getLog(getClass());

		//~ Instance fields ================================================================================================

		protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
		private UserDetailsService userDetailsService;
		
		private boolean forcePrincipalAsString = false;
	    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
	    private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
	    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	   
	    private String SubSystemID;
	    private String secret;
	    private String tokenVerificationUrl;
	    private String getTokenUrl;
    
	    
		public SSODaoAuthenticationProvider() {
			
		    String classBaseDir =  this.getClass().getResource("/").getPath();		
			String propertiesfile = classBaseDir+"systemConfig.properties";
			
			SubSystemID = PropertiesUtil.GetValueByKey(propertiesfile, "SubSystemID");
			secret = PropertiesUtil.GetValueByKey(propertiesfile, "secret");
			tokenVerificationUrl = PropertiesUtil.GetValueByKey(propertiesfile, "tokenVerificationUrl");
			getTokenUrl = PropertiesUtil.GetValueByKey(propertiesfile, "getTokenUrl");
			
			Assert.hasText(SubSystemID);
			Assert.hasText(secret);
			Assert.hasText(tokenVerificationUrl);
			Assert.hasText(getTokenUrl);
		}





		public Authentication authenticate(Authentication authentication)
				throws AuthenticationException {
			
			String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
			
					       
			UserDetails user = null;
            try {
            	 user = retrieveUser(username, (SSOAuthenticationToken) authentication);
            	 
            } catch (UsernameNotFoundException notFound) {
                logger.debug("User '" + username + "' not found");
            
                 throw notFound;              
                
            }

		    Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
		        

	        try {
	            preAuthenticationChecks.check(user);
	            additionalAuthenticationChecks(user, (SSOAuthenticationToken) authentication);
	        } catch (AuthenticationException exception) {
	            
	               throw exception;
	            
	        }

	        postAuthenticationChecks.check(user);

	        

	        Object principalToReturn = user;

	        if (forcePrincipalAsString) {
	            principalToReturn = user.getUsername();
	        }

	        return createSuccessAuthentication(principalToReturn, authentication, user);
			
		
		}
		
		
		
		
		
	  private void additionalAuthenticationChecks(UserDetails userDetails,SSOAuthenticationToken authentication) throws AuthenticationException{
		  String aceesToken = authentication.getCredentials().toString();
		  
		  Map<String, String> params = new HashMap<String, String>();
		  params.put("SubSystemID",SubSystemID);
		  params.put("secret",secret);
		  
		  byte[] tokenByte  = null;
		  
		  try {
			  tokenByte = HttpUtils.sendGETRequest(getTokenUrl, params, "utf-8");
			 
			
		   } catch (Exception e) {
				
				throw new AuthenticationServiceException("Could't not Connent SSO Service");
		   }
		  
		  
		  
		  if(tokenByte == null){
			  throw new AuthenticationServiceException("got empty result from SSO Service"); 
		  }
		  
		  
		  JSONObject obj = JSONObject.fromObject(new String(tokenByte));
		  
		  if(obj.containsKey("access_token")){
			  String ssoServiceToken = obj.getString("access_token");
			  
			  if(!aceesToken.equals(ssoServiceToken)){
				  throw new BadCredentialsException("Invaild AccessToken");				  
			  }
			  
		  }else{
		
			  throw new AuthenticationServiceException("Could't not get token From SSO Service");
		  }
		  
		  
		  
		 
	  
	  }
		
	  
	  
	  
	  
	  public void setPreAuthenticationChecks(UserDetailsChecker preAuthenticationChecks) {
	        this.preAuthenticationChecks = preAuthenticationChecks;
	   }

	  protected UserDetailsChecker getPostAuthenticationChecks() {
	       return postAuthenticationChecks;
	   }
	
	  public void setPostAuthenticationChecks(UserDetailsChecker postAuthenticationChecks) {
	       this.postAuthenticationChecks = postAuthenticationChecks;
	   }	
	
		
	  protected final UserDetails retrieveUser(String username, SSOAuthenticationToken authentication)
	            throws AuthenticationException {
	        UserDetails loadedUser;

	        try {
	            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
	            
	        } catch (UsernameNotFoundException notFound) {
	           
	            throw notFound;
	        } catch (Exception repositoryProblem) {
	            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
	        }

	        if (loadedUser == null) {
	            throw new InternalAuthenticationServiceException(
	                    "UserDetailsService returned null, which is an interface contract violation");
	        }
	        return loadedUser;
	    }
		
		
		

		public void setMessageSource(MessageSource messageSource) {
		        this.messages = new MessageSourceAccessor(messageSource);
		   }


	
	   public final void afterPropertiesSet() throws Exception {
	        Assert.notNull(this.messages, "A message source must be set");
	        doAfterPropertiesSet();
	    }

	    protected void doAfterPropertiesSet() throws Exception {}
		
		
		

		public UserDetailsService getUserDetailsService() {
			return userDetailsService;
		}



		public void setUserDetailsService(UserDetailsService userDetailsService) {
			this.userDetailsService = userDetailsService;
		}

	    public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
	        this.authoritiesMapper = authoritiesMapper;
	    }
   
	    
		public boolean isForcePrincipalAsString() {
			return forcePrincipalAsString;
		}



		public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
			this.forcePrincipalAsString = forcePrincipalAsString;
		}



		public boolean supports(Class<?> authentication) {
			
			return (SSOAuthenticationToken.class.isAssignableFrom(authentication));
	    
		}
	
		
	
		
   public void setSubSystemID(String subSystemID) {
		SubSystemID = subSystemID;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public void setTokenVerificationUrl(String tokenVerificationUrl) {
		this.tokenVerificationUrl = tokenVerificationUrl;
	}

	public void setGetTokenUrl(String getTokenUrl) {
		this.getTokenUrl = getTokenUrl;
	}





	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
	            UserDetails user) {
	        // Ensure we return the original credentials the user supplied,
	        // so subsequent attempts are successful even with encoded passwords.
	        // Also ensure we return the original getDetails(), so that future
	        // authentication events after cache expiry contain the details
	        SSOAuthenticationToken result = new SSOAuthenticationToken(principal,
	                authentication.getCredentials(), authoritiesMapper.mapAuthorities(user.getAuthorities()));
	        result.setDetails(authentication.getDetails());

	        return result;
	    }
		
		
	    
		  private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
		        @SuppressWarnings("deprecation")
				public void check(UserDetails user) {
		            if (!user.isAccountNonLocked()) {
		                logger.debug("User account is locked");

		                throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
		                        "User account is locked"), user);
		            }

		            if (!user.isEnabled()) {
		                logger.debug("User account is disabled");

		                throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled",
		                        "User is disabled"), user);
		            }

		            if (!user.isAccountNonExpired()) {
		                logger.debug("User account is expired");

		                throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired",
		                        "User account has expired"), user);
		            }
		        }
		    }

		    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
		        @SuppressWarnings("deprecation")
				public void check(UserDetails user) {
		            if (!user.isCredentialsNonExpired()) {
		                logger.debug("User account credentials have expired");

		                throw new CredentialsExpiredException(messages.getMessage(
		                        "AbstractUserDetailsAuthenticationProvider.credentialsExpired",
		                        "User credentials have expired"), user);
		            }
		        }
		    }
}
