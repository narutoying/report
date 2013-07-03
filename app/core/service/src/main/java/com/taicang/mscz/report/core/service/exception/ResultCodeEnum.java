package com.taicang.mscz.report.core.service.exception;

/**
 * 操作结果枚举类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ResultCodeEnum.java, v 0.1 2013-3-7 下午11:49:12 narutoying09@gmail.com Exp $
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),
    /** 
     * 非法参数 
     */
    PARAMETER_ILLEGAL("PARAMETER_ILLEGAL", "非法参数"),

    /**
     * 键重复
     */
    DUPLICATE_KEY("DUPLICATE_KEY", "键重复"),

    /**
     * 状态不合法
     */
    STATUS_ILLEGAL("STATUS_ILLEGAL", "状态不合法"),

    /**
     * 没有匹配的状态集合
     */
    NO_MATCH_STATUS_MAP("NO_MATCH_STATUS_MAP", "没有匹配的状态集合"),

    /**
     * 没有匹配的convert方法
     */
    NO_MATCH_CONVERT_METHOD("NO_MATCH_CONVERT_METHOD", "没有匹配的convert方法"),

    /**
     * 没找到相应记录
     */
    CANNOT_FIND_RECORD("CANNOT_FIND_RECORD", "没找到相应记录"),

    /**
     * 反射异常
     */
    REFLECT_ERROR("REFLECT_ERROR", "反射异常"),

    /**
     * 数据库异常
     */
    DATAACCESS_ERROE("DATAACCESS_ERROE", "数据库操作异常"),
    /**
     * 运行时异常
     */
    RUNTIME_EXCEPTION("RUNTIME_EXCEPTION", "运行时异常"),
    /** 
     * 系统异常
     */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),

    /** 操作失败 */
    FAILED("FAILED", "操作失败"),

    /** 应用不存在 */
    APP_NOT_EXIST("APP_NOT_EXIST", "应用不存在"),

    /** 员工未找到 */
    EMPLOYEE_NOT_FOUND("EMPLOYEE_NOT_FOUND", "员工未找到"),

    // ------------------ 发布相关结果码 starts ---------------------
    /**  */
    RELEASE_EXCEPTION("RELEASE_EXCEPTION", "发布任务异常"),
    /**  */
    RELEASE_CONFIG_EXCEPTION("RELEASE_CONFIG_EXCEPTION", "发布配置异常"),
    /**  */
    RELEASE_GROUP_EXCEPTION("RELEASE_GROUP_EXCEPTION", "发布子任务异常"),
    /**  */
    RELEASE_GROUP_ORDER_EXCEPTION("RELEASE_GROUP_ORDER_EXCEPTION", "发布顺序异常"),
    /**  */
    RELEASE_PACKAGE_EXCEPTION("RELEASE_PACKAGE_EXCEPTION", "发布包管理异常"),
    /**  */
    RELEASE_APP_RELEASE_EXCEPTION("RELEASE_APP_RELEASE_EXCEPTION", "应用发布异常"),
    /**  */
    RELEASE_APP_BETA_RELEASE_EXCEPTION("RELEASE_APP_BETA_RELEASE_EXCEPTION", "应用BETA发布异常"),
    /**  */
    RELEASE_APP_GROUP_EXCEPTION("RELEASE_APP_GROUP_EXCEPTION", "应用内部分组执行异常"),
    /**  */
    RELEASE_APP_ROLLBACK_EXCEPTION("RELEASE_APP_ROLLBACK_EXCEPTION", "应用回滚异常"),
    /**  */
    RELEASE_SERVER_EXCEPTION("RELEASE_SERVER_EXCEPTION", "服务器异常"),
    /**  */
    RELEASE_APP_QUEUE_EXCEPTION("RELEASE_APP_QUEUE_EXCEPTION", "发布队列异常"),
    /** */
    REQUIREMENT_EXCEPTION("REQUIREMENT_EXCEPTION", "需求处理异常"),

    // ------------------ 发布相关结果码 ends ---------------------

    /**
     * 表达式不合法
     */
    EXPRESSION_ILLEGAL("EXPRESSION_ILLEGAL", "表达式不合法");

    /** 
     * 错误码
     */
    private final String resultCode;

    /** 
     * 错误信息
     */
    private final String resultMessage;

    /**
     * 创建一个ErrorCodeEnum
     * @param errorCode 错误码
     * @param errorMessage 错误信息
     */
    private ResultCodeEnum(String errorCode, String errorMessage) {
        this.resultCode = errorCode;
        this.resultMessage = errorMessage;
    }

    /**
     * 将字符串解析成枚举值
     * 
     * @param code
     * @return
     */
    public static ResultCodeEnum getEnum(String code) {
        try {
            return ResultCodeEnum.valueOf(code);
        } catch (Exception e) {
            return null;
        }

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
     * Getter method for property <tt>resultMessage</tt>.
     * 
     * @return property value of resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

}
