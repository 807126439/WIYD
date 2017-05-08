package com.spr.core.spring.security.service;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public interface IMySecurityMetadataSource extends FilterInvocationSecurityMetadataSource{

	public void reloadResourceMap();
	
}
