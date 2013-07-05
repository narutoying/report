package com.taicang.mscz.report.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截所有htm请求，设置页面共用的属性
 * 
 * @author narutoying09@gmail.com
 * @version $Id: AppInfoSystemInterceptor.java, v 0.1 2013-3-9 下午7:28:11
 *          narutoying09@gmail.com Exp $
 */
public class CommonWebInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("contextPath", request.getContextPath());
	}
}
