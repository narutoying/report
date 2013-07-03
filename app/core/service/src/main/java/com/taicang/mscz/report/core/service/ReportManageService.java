/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service;

import java.util.List;
import java.util.Map;

import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportDimension;

/**
 * 报表管理服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportService.java, v 0.1 2013-7-2 ����4:54:09
 *          narutoying09@gmail.com Exp $
 */
public interface ReportManageService {

	/**
	 * 接收并初始化报表相关数据
	 * 1. 检查报表是否能够被接收
	 * 	1.1 数据完整性
	 * 	1.2 报表是否已存在（报表名字唯一）
	 * 2. 检查通过后，进行以下操作：
	 * 	2.1 初始化报表 
	 *  2.2 初始化报表维度（与值）
	 *  2.3 初始化字段映射关系
	 *  2.4 初始化基础数据单元
	 * 
	 * @param report 报表基本信息
	 * @param conditionsWithValues 维度列与值
	 * @param unitDatas 单元数据
	 */
	void receiveReport(Report report,
			List<ReportDimension> conditionsWithValues,
			List<Map<String/*维度名*/, String/*真实值*/>> unitDatas);

}
