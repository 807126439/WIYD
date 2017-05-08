package com.spr.core.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.utils.json.JSONHelper;

/**
 * 全局异常解析处理器
 * @author wb_java_zjr
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {


	public Logger logger = Logger.getLogger(getClass());	
	private Set<?> mappedHandlers;
	private Class[] mappedHandlerClasses;
	private boolean isHandleAllError = true;

	public void setMappedHandlers(Set<?> mappedHandlers) {
		this.mappedHandlers = mappedHandlers;
	}

	public void setMappedHandlerClasses(Class[] mappedHandlerClasses) {
		this.mappedHandlerClasses = mappedHandlerClasses;
	}

	

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		if (shouldApplyTo(request, handler)) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Resolving exception from handler ["
						+ handler + "]: " + ex);
			}
			setHandleErrorVal(ex);      //检测并设置是否处理全部异常
			logException(ex, request);	//记录日志
			
			return doResolveException(request, response, handler, ex);
		}

		return null;
	}

	
	private ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
				
    	if(isAjaxRequest(request)){
			return this.processAjax(request, response, handler, ex);
    		    		
		}else{
			return processNotAjax(request, response, handler, ex);
		}

	}

	
	
	/**
	 * ajax异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @return
	 */
	private ModelAndView processAjax(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception ex){
		
		ModelAndView empty = new ModelAndView();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");  
		preventCaching(response);
		AjaxJson json = new AjaxJson();
		json.setStatus(AjaxJson.error);
		
		if(this.isHandleAllError){
			json.setInfo(deepestException(ex).getMessage());
		}else{
			json.setInfo(ex.getMessage());
		}

		try {
			PrintWriter pw = response.getWriter();	
			pw.write(JSONHelper.bean2json(json));
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		empty.clear();
		return empty;
	}
	
	
	
	private ModelAndView processNotAjax(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		preventCaching(response);
		
		if(this.isHandleAllError){//返回所有的异常信息
			String exceptionMessage = getThrowableMessage(ex);
			model.put("exceptionMessage", exceptionMessage);
			model.put("ex", ex);
			return new ModelAndView("common/error/error-exception.jsp", model);
	
		}else{//业务异常，只返回异常开始头的信息，不返回详细信息
			model.put("exceptionMessage",ex.getMessage());
		
			return new ModelAndView("common/error/error-business.jsp", model);
		}
		
		
	}
	
	
	
	/**
	 * 返回错误信息字符串
	 * 
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public String getThrowableMessage(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		//ex.printStackTrace(pw);
		return sw.toString();
	}
	
	
	
	
	
	protected boolean shouldApplyTo(HttpServletRequest request, Object handler) {
		if (handler != null) {
			if ((this.mappedHandlers != null)
					&& (this.mappedHandlers.contains(handler))) {
				return true;
			}
			if (this.mappedHandlerClasses != null) {
				for (Class<?> handlerClass : this.mappedHandlerClasses) {
					if (handlerClass.isInstance(handler)) {
						return true;
					}
				}
			}
		}

		return (this.mappedHandlers == null)
				&& (this.mappedHandlerClasses == null);
	}

	/**
	 * 记录异常日志
	 * @param ex
	 * @param request
	 */
	protected void logException(Exception ex, HttpServletRequest request) {		
		if(this.isHandleAllError){
			ex.printStackTrace();
			logger.error("error", ex);
		}
	
	}

	/**
	 * 获取最原始的异常出处，即最初抛出异常的地方
	 */
    private Throwable deepestException(Throwable e){
        Throwable tmp = e;
        int breakPoint = 0;
        while(tmp.getCause()!=null){
            if(tmp.equals(tmp.getCause())){
                break;
            }
            tmp=tmp.getCause();
            breakPoint++;
            if(breakPoint>1000){
                break;
            }
        } 
        return tmp;
    }
	
	
	
	protected String buildLogMessage(Exception ex, HttpServletRequest request) {
		return "Handler execution resulted in exception";
	}

	

	protected void preventCaching(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 1L);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
	}

	
	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {  
        String header = request.getHeader("X-Requested-With");  
        if (header != null && "XMLHttpRequest".equals(header)){
            return true;  
            
        }else  {
            return false;  
        }
    } 
	
	

	public void setHandleErrorVal(Exception ex) {
		
		if( (ex instanceof BusinessException) || (ex instanceof IllegalArgumentException)){
			isHandleAllError = false;
		}else{
			isHandleAllError = true;
		}
	}

	
	
	

}
