package com.taicang.mscz.report.core.service.util.template.callback;

/**
 * 统一管理模板回调接口
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PaasManageCallback.java, v 0.1 2013-3-7 下午11:45:40 narutoying09@gmail.com Exp $
 */
public interface CommonManageCallback {
    /**
     * 检测参数
     */
    public void checkParameter();

    /**
     * 执行管理操作
     */
    public void doManage();
}
