package com.wb.core.listener;




import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;




public class ContextLoaderListenerOverWrite extends ContextLoaderListener {  


   
  
    
    /** 
     * @description 重写ContextLoaderListener的contextInitialized方法 ,用于项目启动时加载需要的信息
     * @param event
     */  
    public void contextInitialized(ServletContextEvent event) {  
        super.contextInitialized(event);  
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()); 
        ServletContext application = event.getServletContext();
        
  
        
        
    } 
    
    
    
    
    
    
    
    
}