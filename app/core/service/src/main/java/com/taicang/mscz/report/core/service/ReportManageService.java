/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service;

import java.util.List;

import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportFieldMapping;
import com.taicang.mscz.report.core.model.ReportQueryCondition;
import com.taicang.mscz.report.core.model.Unit;

/**
 * 报表管理服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportService.java, v 0.1 2013-7-2 下午4:54:09
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
	 *  2.2 初始化查询维度（与值）
	 *  2.3 初始化字段映射关系
	 *  2.4 初始化基础数据单元
	 * 
	 * @param report
	 * @param conditionsWithValues
	 * @param fieldMapping
	 * @param units
	 */
	void receiveReport(Report report,
			List<ReportQueryCondition> conditionsWithValues,
			ReportFieldMapping fieldMapping, List<Unit> units);

}
