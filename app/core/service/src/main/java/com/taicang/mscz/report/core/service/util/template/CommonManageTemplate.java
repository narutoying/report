/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.util.template;

import com.taicang.mscz.report.core.service.util.template.callback.CommonManageCallback;
import com.taicang.mscz.report.facade.model.CommonResult;

/**
 * Paas管理模版接口
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PaasManageTemplate.java, v 0.1 2013-3-7 下午11:45:52 narutoying09@gmail.com Exp $
 */
public interface CommonManageTemplate {

    /**
     * 模版方法，不具有事务功能，提供异常处理
     * 
     * @param result    操作结果
     * @param callback  回调接口
     */
    public void manage(CommonResult result, CommonManageCallback callback);

    /**
     * 模版方法,具有事务功能，适用于需要维护事务一致性的创建、修改、删除等管理方法，并提供异常处理
     * 事务传播性质沿用Spring事务模板默认配置
     * 
     * @param result    操作结果
     * @param callback  回调接口
     */
    public void manageWithTransaction(CommonResult result, CommonManageCallback callback);

}
