package com.taicang.mscz.report.core.service.exception;

/**
 * 统一业务异常类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PaasException.java, v 0.1 2013-3-7 下午11:49:33 narutoying09@gmail.com Exp $
 */
public class CommonException extends RuntimeException {

    /**
     * UID
     */
    private static final long serialVersionUID = 1658517572605339832L;

    /**
     * 结果码
     */
    private ResultCodeEnum    resultCodeEnum;

    /**
     * 构造方法
     */
    public CommonException() {
        super();
    }

    /**
     * 构造方法
     * @param resultCodeEnum 结果码
     */
    public CommonException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getResultMessage());
        this.resultCodeEnum = resultCodeEnum;
    }

    /**
     * 构造方法
     * @param resultCodeEnum    结果码
     * @param additionalMessage 附加信息
     */
    public CommonException(ResultCodeEnum resultCodeEnum, String additionalMessage) {
        super(resultCodeEnum.getResultMessage() + ":" + additionalMessage);
        this.resultCodeEnum = resultCodeEnum;
    }

    public CommonException(String resultMessage) {
        super(resultMessage);
    }

    /**
     * 构造方法
     * @param resultCodeEnum 结果码
     * @param cause 可抛类
     */
    public CommonException(ResultCodeEnum resultCodeEnum, Throwable cause) {
        super(resultCodeEnum.getResultMessage(), cause);
        this.resultCodeEnum = resultCodeEnum;
    }

    /**
     * Getter method for property <tt>resultCodeEnum</tt>.
     * 
     * @return property value of resultCodeEnum
     */
    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }

    /**
     * Setter method for property <tt>resultCodeEnum</tt>.
     * 
     * @param resultCodeEnum value to be assigned to property resultCodeEnum
     */
    public void setResultCodeEnum(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

}
