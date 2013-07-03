/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.util;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.util.CollectionUtils;

import com.taicang.mscz.report.common.util.ArrayUtil;
import com.taicang.mscz.report.common.util.StringUtil;
import com.taicang.mscz.report.core.service.exception.CommonException;
import com.taicang.mscz.report.core.service.exception.ResultCodeEnum;

/**
 * 参数检测工具类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ParaCheckUtil.java, v 0.1 2013-3-9 下午12:41:22 narutoying09@gmail.com Exp $
 */
public class ParaCheckUtil {
    /**
     * 检测对象类型
     * 
     * @param object 对象
     */
    public static void checkParaNotNull(Object object) {
        if (object == null) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    /**
     * 检测字符串类型
     * 
     * @param param 字符串
     */
    public static void checkParaNotBlank(String param) {
        if (StringUtil.isBlank(param)) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    /**
     * 检测数值不是负数
     * 
     * @param param 数值
     */
    public static void checkParaNotNegative(Integer param) {
        if (param == null || param < 0) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    /**
     * 检测集合类型
     * 
     * @param collection 集合
     */
    public static void checkParaNotEmpty(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    public static void checkParaNotEmpty(Object[] arr) {
        if (arr == null || arr.length == 0) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    /**
     * 检测数组类型
     * 
     * @param array 数组
     */
    public static void checkArrayNotEmpty(Object[] array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    /**
     * 检查两个对象是否相等
     * 
     * @param obj1
     * @param obj2
     */
    public static void checkEquals(Object obj1, Object obj2) {
        if (!ObjectUtils.equals(obj1, obj2)) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL);
        }
    }

    /**
     * 检查主机名是否为长域名
     * 例如：trade-60-10.zue.alipay.com
     * 
     * @param hostname
     * @return 如果返回值为null，则说明服务器主机名验证无误，反之则有误，且返回值即错误信息
     */
    public static void checkHostnameStyle(List<String> hostnames) {
        StringBuilder sb = null;
        if (!CollectionUtils.isEmpty(hostnames)) {
            for (String hostname : hostnames) {
                if (!isHostnameCorrect(hostname)) {
                    if (sb == null) {
                        sb = new StringBuilder();
                        sb.append("服务器主机名[");
                    }
                    sb.append(hostname + ",");
                }
            }
        }
        if (sb != null) {
            String checkMessage = sb.substring(0, sb.length() - 1)
                                  + "]不符合长域名规范，格式如：monitorweb-60-10.zue.alipay.com";
            throw new CommonException(ResultCodeEnum.PARAMETER_ILLEGAL, checkMessage);
        }
    }

    /**
     * 长域名格式xxx-数字-数字.xxx.xxx.xxx
     * 
     * @param hostname
     * @return
     */
    private static boolean isHostnameCorrect(String hostname) {
        if (StringUtil.isNotBlank(hostname)) {
            Pattern pattern = Pattern
                .compile("[a-zA-Z]{1,}-[0-9]{1,}-[0-9]{1,}\\.[a-zA-Z]{1,}\\.[a-zA-Z]{1,}\\.[a-zA-Z]{1,}");
            Matcher matcher = pattern.matcher(hostname);
            if (!matcher.find()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean isMatchRegx(String target, String regx) {
        if (target == null) {
            return false;
        }
        return target.matches(regx);
    }

}
