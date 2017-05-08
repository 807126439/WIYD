package com.spr.core.spring.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.spr.core.spring.security.user.extend.MyUser;
import com.spr.core.utils.IpUtils;
import com.spr.web.system.dao.ILoginLogDao;
import com.spr.web.system.entity.LoginLog;
import com.spr.web.system.service.IAuthorityService;


public class MyRememberMeAuthenticationFilter extends GenericFilterBean implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher eventPublisher;
	private AuthenticationManager authenticationManager;
	private RememberMeServices rememberMeServices;
	@Resource
	private IAuthorityService authorityService;
	@Resource
	private ILoginLogDao loginLogDao;
	private static final String LogDetails = "用户成功登录系统(By remember me)";
	
	public void afterPropertiesSet() {
		Assert.notNull(this.authenticationManager,
				"authenticationManager must be specified");
		Assert.notNull(this.rememberMeServices,
				"rememberMeServices must be specified");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			Authentication rememberMeAuth = this.rememberMeServices.autoLogin(
					request, response);

			if (rememberMeAuth != null) {
				try {
					
					rememberMeAuth = this.authenticationManager.authenticate(rememberMeAuth);

					SecurityContextHolder.getContext().setAuthentication(rememberMeAuth);
				
					onSuccessfulAuthentication(request, response,rememberMeAuth);
					
					/*---------------------添加-----------------------*/
					MyUser user = (MyUser) rememberMeAuth.getPrincipal();										
				
					/*获取用户权限菜单*/
					String menu = this.authorityService.getAuthMenu(user.getRoleIds(),request.getContextPath());
					request.getSession().setAttribute("menu", menu);
					/*获取用户权限控制码*/
					List<String> authCodes = this.authorityService.getAuthControlItem(user.getRoleIds());
					user.setAuthCodes(authCodes);
										
					//记录登录日志
					LoginLog log = new LoginLog(user.getUsername(),
							       LogDetails,IpUtils.ipToLong(request.getRemoteAddr()));
					this.loginLogDao.insert(log);
				    /*--------------------------------------------*/
					
					if (this.logger.isDebugEnabled()) {
						this.logger
								.debug("SecurityContextHolder populated with remember-me token: '"
										+ SecurityContextHolder.getContext()
												.getAuthentication() + "'");
					}

					if (this.eventPublisher != null)
						this.eventPublisher
								.publishEvent(new InteractiveAuthenticationSuccessEvent(
										SecurityContextHolder.getContext()
												.getAuthentication(),
										getClass()));
				} catch (AuthenticationException authenticationException) {
					if (this.logger.isDebugEnabled()) {
						this.logger
								.debug("SecurityContextHolder not populated with remember-me token, as AuthenticationManager rejected Authentication returned by RememberMeServices: '"
										+ rememberMeAuth
										+ "'; invalidating remember-me token",
										authenticationException);
					}

					this.rememberMeServices.loginFail(request, response);

					onUnsuccessfulAuthentication(request, response,
							authenticationException);
				}
			}

			chain.doFilter(request, response);
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger
						.debug("SecurityContextHolder not populated with remember-me token, as it already contained: '"
								+ SecurityContextHolder.getContext()
										.getAuthentication() + "'");
			}

			chain.doFilter(request, response);
		}
	}

	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) {
	}

	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed) {
	}

	public RememberMeServices getRememberMeServices() {
		return this.rememberMeServices;
	}

	public void setApplicationEventPublisher(
			ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		this.rememberMeServices = rememberMeServices;
	}



	
	
	
}
