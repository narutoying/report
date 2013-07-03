/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service;

import java.util.List;

import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.Unit;
import com.taicang.mscz.report.core.model.query.ReportQueryCondition;

/**
 * 报表查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportQueryService.java, v 0.1 2013-7-2 ����4:55:12
 *          narutoying09@gmail.com Exp $
 */
public interface ReportQueryService {

	Report getReport(int reportId);

	/**
	 * 查询报表维度
	 * 
	 * @param reportId
	 *            报表id
	 * @return
	 */
	List<ReportDimension> queryReportDimensions(int reportId);

	/**
	 * 按维度查询报表数据
	 * 
	 * @param reportId
	 * @param queryCondition
	 * @return
	 */
	List<Unit> queryReportUnits(int reportId,
			ReportQueryCondition queryCondition);

}
