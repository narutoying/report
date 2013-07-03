package com.taicang.mscz.report.facade.model;

import java.io.Serializable;

/**
 * 系统统一结果类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: CrmResult.java, v 0.1 2013-3-4 下午8:53:07 narutoying09@gmail.com Exp $
 */
public class CommonResult implements Serializable {

    /**  */
    private static final long serialVersionUID = 2130730860098888652L;

    /**
     * 是否成功
     */
    private boolean           success;

    /**
     * 结果码
     */
    private String            resultCode;

    /**
     * 结果描述信息
     */
    private String            resultMessage;

    /**
     * 创建一个ZPaasResult
     */
    public CommonResult() {
        super();
    }

    /**
     * 创建一个AppInfoResult
     * @param isSuccess 是否成功
     * @param resultCode 结果码
     * @param resultMessage 结果描述信息
     */
    public CommonResult(boolean isSuccess, String resultCode, String resultMessage) {
        super();
        this.success = isSuccess;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    /**
     * Getter method for property <tt>isSuccess</tt>.
     * 
     * @return property value of isSuccess
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>isSuccess</tt>.
     * 
     * @param isSuccess value to be assigned to property isSuccess
     */
    public void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     * 
     * @return property value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     * 
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultMessage</tt>.
     * 
     * @return property value of resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Setter method for property <tt>resultMessage</tt>.
     * 
     * @param resultMessage value to be assigned to property resultMessage
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * 组装操作结果
     * 
     * @param result 操作结果
     * @param isSuccess 是否成功
     * @param resultCode 结果值
     * @param resultMessage 结果信息
     */
    public static void buildResult(CommonResult result, boolean isSuccess, String resultCode,
                                   String resultMessage) {
        result.setSuccess(isSuccess);
        result.setResultCode(resultCode);
        result.setResultMessage(resultMessage);
    }

    /**
     * 组装操作结果
     * 
     * @param result 操作结果
     * @param isSuccess 是否成功
     * @param resultMessage 结果信息
     */
    public static void buildResult(CommonResult result, boolean isSuccess, String resultMessage) {
        result.setSuccess(isSuccess);
        result.setResultMessage(resultMessage);
    }

}
