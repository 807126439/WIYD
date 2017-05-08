package com.spr.core.spring.security.acess.intercept;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.event.AuthenticationCredentialsNotFoundEvent;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.access.event.AuthorizedEvent;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.spr.core.spring.security.service.IMySecurityMetadataSource;



/**
 * 重写org.springframework.security.access.intercept.AbstractSecurityInterceptor方法
 * @author wb_java_zjr
 *
 */
public abstract class MyAbstractSecurityInterceptor
  implements InitializingBean, ApplicationEventPublisherAware, MessageSourceAware{
	
  protected final Log logger = LogFactory.getLog(getClass());

  protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
  private ApplicationEventPublisher eventPublisher;
  private AccessDecisionManager accessDecisionManager;
  private AfterInvocationManager afterInvocationManager;
  private AuthenticationManager authenticationManager;
  private RunAsManager runAsManager = new NullRunAsManager();

  private boolean alwaysReauthenticate = false;
  private boolean rejectPublicInvocations = false;
  private boolean validateConfigAttributes = true;
  private boolean publishAuthorizationSuccess = false;

  public void afterPropertiesSet() throws Exception {
    Assert.notNull(getSecureObjectClass(), "Subclass must provide a non-null response to getSecureObjectClass()");
    Assert.notNull(this.messages, "A message source must be set");
    Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");
    Assert.notNull(this.accessDecisionManager, "An AccessDecisionManager is required");
    Assert.notNull(this.runAsManager, "A RunAsManager is required");
    Assert.notNull(obtainSecurityMetadataSource(), "An SecurityMetadataSource is required");
    Assert.isTrue(obtainSecurityMetadataSource().supports(getSecureObjectClass()), "SecurityMetadataSource does not support secure object class: " + getSecureObjectClass());

    Assert.isTrue(this.runAsManager.supports(getSecureObjectClass()), "RunAsManager does not support secure object class: " + getSecureObjectClass());

    Assert.isTrue(this.accessDecisionManager.supports(getSecureObjectClass()), "AccessDecisionManager does not support secure object class: " + getSecureObjectClass());

    if (this.afterInvocationManager != null) {
      Assert.isTrue(this.afterInvocationManager.supports(getSecureObjectClass()), "AfterInvocationManager does not support secure object class: " + getSecureObjectClass());
    }

    if (this.validateConfigAttributes) {
      Collection<ConfigAttribute> attributeDefs = obtainSecurityMetadataSource().getAllConfigAttributes();

      if (attributeDefs == null) {
        this.logger.warn("Could not validate configuration attributes as the SecurityMetadataSource did not return any attributes from getAllConfigAttributes()");

        return;
      }

      Set<ConfigAttribute> unsupportedAttrs = new HashSet<ConfigAttribute>();

      for (ConfigAttribute attr : attributeDefs) {
        if ((!this.runAsManager.supports(attr)) && (!this.accessDecisionManager.supports(attr)) && ((this.afterInvocationManager == null) || (!this.afterInvocationManager.supports(attr))))
        {
          unsupportedAttrs.add(attr);
        }
      }

      if (unsupportedAttrs.size() != 0) {
        throw new IllegalArgumentException("Unsupported configuration attributes: " + unsupportedAttrs);
      }

      this.logger.debug("Validated configuration attributes");
    }
  }

  
  
  
  protected InterceptorStatusToken beforeInvocation(Object object) {
    Assert.notNull(object, "Object was null");
    boolean debug = this.logger.isDebugEnabled();

    if (!getSecureObjectClass().isAssignableFrom(object.getClass())) {
      throw new IllegalArgumentException("Security invocation attempted for object " + object.getClass().getName() + " but AbstractSecurityInterceptor only configured to support secure objects of type: " + getSecureObjectClass());
    }

   Collection<ConfigAttribute> attributes = obtainSecurityMetadataSource().getAttributes(object);
  

    if (debug) {
      this.logger.debug("Secure object: " + object + "; Attributes: " + attributes);
    }

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      credentialsNotFound(this.messages.getMessage("AbstractSecurityInterceptor.authenticationNotFound", "An Authentication object was not found in the SecurityContext"), object, attributes);
    }

    Authentication authenticated = authenticateIfRequired();
    try {
      /*重写判断器*/	
      this.accessDecisionManager.decide(authenticated, object,attributes);
      
    }catch (AccessDeniedException accessDeniedException) {
      publishEvent(new AuthorizationFailureEvent(object, attributes, authenticated, accessDeniedException));

      throw accessDeniedException;
    }

    if (debug) {
      this.logger.debug("Authorization successful");
    }

    if (this.publishAuthorizationSuccess) {
      publishEvent(new AuthorizedEvent(object, attributes, authenticated));
    }

    Authentication runAs = this.runAsManager.buildRunAs(authenticated, object, attributes);

    if (runAs == null) {
      if (debug) {
        this.logger.debug("RunAsManager did not change Authentication object");
      }

      return new InterceptorStatusToken(SecurityContextHolder.getContext(), false, attributes, object);
    }
    if (debug) {
      this.logger.debug("Switching to RunAs Authentication: " + runAs);
    }

    SecurityContext origCtx = SecurityContextHolder.getContext();
    SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
    SecurityContextHolder.getContext().setAuthentication(runAs);

    return new InterceptorStatusToken(origCtx, true, attributes, object);
  }

  protected void finallyInvocation(InterceptorStatusToken token){
    if ((token != null) && (token.isContextHolderRefreshRequired())) {
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("Reverting to original Authentication: " + token.getSecurityContext().getAuthentication());
      }

      SecurityContextHolder.setContext(token.getSecurityContext());
    }
  }

  protected Object afterInvocation(InterceptorStatusToken token, Object returnedObject){
    if (token == null)
    {
      return returnedObject;
    }

    finallyInvocation(token);

    if (this.afterInvocationManager != null) {
      try
      {
        returnedObject = this.afterInvocationManager.decide(token.getSecurityContext().getAuthentication(), token.getSecureObject(), token.getAttributes(), returnedObject);
      }
      catch (AccessDeniedException accessDeniedException)
      {
        AuthorizationFailureEvent event = new AuthorizationFailureEvent(token.getSecureObject(), token.getAttributes(), token.getSecurityContext().getAuthentication(), accessDeniedException);

        publishEvent(event);

        throw accessDeniedException;
      }
    }

    return returnedObject;
  }

  private Authentication authenticateIfRequired(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if ((authentication.isAuthenticated()) && (!this.alwaysReauthenticate)) {
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("Previously Authenticated: " + authentication);
      }

      return authentication;
    }

    authentication = this.authenticationManager.authenticate(authentication);

    if (this.logger.isDebugEnabled()) {
      this.logger.debug("Successfully Authenticated: " + authentication);
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return authentication;
  }

  private void credentialsNotFound(String reason, Object secureObject, Collection<ConfigAttribute> configAttribs) {
    AuthenticationCredentialsNotFoundException exception = new AuthenticationCredentialsNotFoundException(reason);

    AuthenticationCredentialsNotFoundEvent event = new AuthenticationCredentialsNotFoundEvent(secureObject, configAttribs, exception);

    publishEvent(event);

    throw exception;
  }

  public AccessDecisionManager getAccessDecisionManager() {
    return this.accessDecisionManager;
  }

  public AfterInvocationManager getAfterInvocationManager() {
    return this.afterInvocationManager;
  }

  public AuthenticationManager getAuthenticationManager() {
    return this.authenticationManager;
  }

  public RunAsManager getRunAsManager() {
    return this.runAsManager;
  }

  public abstract Class<?> getSecureObjectClass();

  public boolean isAlwaysReauthenticate(){
    return this.alwaysReauthenticate;
  }

  public boolean isRejectPublicInvocations() {
    return this.rejectPublicInvocations;
  }

  public boolean isValidateConfigAttributes() {
    return this.validateConfigAttributes;
  }

  /**
   * 使用了自定义的SecurityMetadataSource
   * @return
   */
  public abstract IMySecurityMetadataSource obtainSecurityMetadataSource();

  public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
    this.accessDecisionManager = accessDecisionManager;
  }

  public void setAfterInvocationManager(AfterInvocationManager afterInvocationManager) {
    this.afterInvocationManager = afterInvocationManager;
  }

  public void setAlwaysReauthenticate(boolean alwaysReauthenticate){
    this.alwaysReauthenticate = alwaysReauthenticate;
  }

  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.eventPublisher = applicationEventPublisher;
  }

  public void setAuthenticationManager(AuthenticationManager newManager) {
    this.authenticationManager = newManager;
  }

  public void setMessageSource(MessageSource messageSource) {
    this.messages = new MessageSourceAccessor(messageSource);
  }

  public void setPublishAuthorizationSuccess(boolean publishAuthorizationSuccess){
    this.publishAuthorizationSuccess = publishAuthorizationSuccess;
  }

  public void setRejectPublicInvocations(boolean rejectPublicInvocations){
    this.rejectPublicInvocations = rejectPublicInvocations;
  }

  public void setRunAsManager(RunAsManager runAsManager) {
    this.runAsManager = runAsManager;
  }

  public void setValidateConfigAttributes(boolean validateConfigAttributes) {
    this.validateConfigAttributes = validateConfigAttributes;
  }

  private void publishEvent(ApplicationEvent event) {
    if (this.eventPublisher != null)
      this.eventPublisher.publishEvent(event);
  }
}
