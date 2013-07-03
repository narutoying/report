package com.taicang.mscz.report.core.service.util.template.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.taicang.mscz.report.core.service.exception.CommonException;
import com.taicang.mscz.report.core.service.exception.ResultCodeEnum;
import com.taicang.mscz.report.core.service.util.template.CommonManageTemplate;
import com.taicang.mscz.report.core.service.util.template.callback.CommonManageCallback;
import com.taicang.mscz.report.facade.model.CommonResult;

/**
 * 统一业务管理模版接口实现类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PaasManageTemplateImpl.java, v 0.1 2013-3-7 下午11:52:10 narutoying09@gmail.com Exp $
 */
public class CommonManageTemplateImpl implements CommonManageTemplate {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonManageTemplateImpl.class);

    /**
     * 事务模板
     */
    private TransactionTemplate transactionTemplate;

    /**
     * 
     * @see com.CommonManageTemplate.zpaas.biz.template.PaasManageTemplate#manage(com.alipay.zpaas.zappinfo.result.AppInfoResult, com.CommonManageCallback.zpaas.biz.template.callback.PaasManageCallback)
     */
    public void manage(CommonResult result, CommonManageCallback callback) {
        try {
            //检验参数
            callback.checkParameter();
            //执行管理方法
            callback.doManage();
        } catch (CommonException e) {
            logger.warn("业务管理类操作出现异常:", e);
            CommonResult.buildResult(result, false, e.getResultCodeEnum().getResultCode(),
                e.getMessage());
        } catch (DataAccessException e) {
            logger.error("业务管理类操作出现DB异常:", e);
            CommonResult.buildResult(result, false, ResultCodeEnum.DATAACCESS_ERROE.getResultCode(),
                "业务管理类操作DB异常");
        } catch (Exception e) {
            logger.error("业务管理类操作出现系统未知异常:", e);
            CommonResult.buildResult(result, false, ResultCodeEnum.SYSTEM_ERROR.getResultCode(),
                "业务管理类操作系统未知异常");
        }
    }

    /** 
     * @see com.CommonManageTemplate.zpaas.biz.template.PaasManageTemplate#manageWithTransaction(com.alipay.zpaas.zappinfo.result.AppInfoResult, com.CommonManageCallback.zpaas.biz.template.callback.PaasManageCallback)
     */
    public void manageWithTransaction(final CommonResult result, final CommonManageCallback callback) {
        doInTransactionTemplate(result, callback, true, false);
    }

    private void doInTransactionTemplate(final CommonResult result,
                                         final CommonManageCallback callback,
                                         final boolean needSetRollbackStatus,
                                         final boolean needThrowException) {
        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                try {
                    //检验参数
                    callback.checkParameter();
                    //执行管理方法
                    callback.doManage();

                    return null;

                } catch (CommonException e) {
                    if (needSetRollbackStatus) {
                        status.setRollbackOnly();
                    }
                    logger.warn("业务管理类操作出现异常，执行回滚:", e);
                    CommonResult.buildResult(result, false, (e.getResultCodeEnum() != null) ? e
                        .getResultCodeEnum().getResultCode() : "", e.getMessage());
                    if (needThrowException) {
                        throw e;
                    }
                    return null;
                } catch (DataAccessException e) {
                    if (needSetRollbackStatus) {
                        status.setRollbackOnly();
                    }
                    logger.error("业务管理类操作出现DB异常，执行回滚:", e);
                    CommonResult.buildResult(result, false,
                        ResultCodeEnum.DATAACCESS_ERROE.getResultCode(), "业务管理类操作DB异常");
                    if (needThrowException) {
                        throw e;
                    }
                    return null;
                } catch (RuntimeException e) {
                    if (needSetRollbackStatus) {
                        status.setRollbackOnly();
                    }
                    logger.error("业务管理类操作出现运行时异常，执行回滚:", e);
                    CommonResult.buildResult(result, false,
                        ResultCodeEnum.RUNTIME_EXCEPTION.getResultCode(),
                        "业务管理类操作运行时异常：" + e.getMessage());
                    if (needThrowException) {
                        throw e;
                    }
                    return null;
                } catch (Exception e) {
                    if (needSetRollbackStatus) {
                        status.setRollbackOnly();
                    }
                    logger.error("业务管理类操作出现系统未知异常，执行回滚:", e);
                    CommonResult.buildResult(result, false,
                        ResultCodeEnum.SYSTEM_ERROR.getResultCode(), "业务管理类操作系统未知异常");
                    return null;
                }
            }
        });
    }

    /**
     * Setter method for property <tt>transactionTemplate</tt>.
     * 
     * @param transactionTemplate value to be assigned to property transactionTemplate
     */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

}
