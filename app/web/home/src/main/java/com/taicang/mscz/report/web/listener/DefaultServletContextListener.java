/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.taicang.mscz.report.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alibaba.dubbo.config.ProtocolConfig;

/**
 * 
 * @author cheng.dai@alipay.com
 * @version $Id: DefaultServletContextListener.java, v 0.1 2012-12-7 上午11:47:45 cheng.dai@alipay.com Exp $
 */
public class DefaultServletContextListener implements ServletContextListener {

    /** 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ProtocolConfig.destroyAll();
    }

}
